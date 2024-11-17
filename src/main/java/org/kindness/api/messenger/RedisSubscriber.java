package org.kindness.api.messenger;

import lombok.Getter;
import org.kindness.api.RedisConnectionManager;
import redis.clients.jedis.JedisPubSub;

@Getter
public class RedisSubscriber {

    private final Subscriber subscriber;
    private final String channel;

    public RedisSubscriber(RedisConnectionManager connectionManager, RedisMessageEvent messageEvent,String channel) {
        this.channel = channel;
        connectionManager.getConnection().subscribe(this.subscriber = new Subscriber(){
            @Override
            public void onMessage(String channel, String message) {
                 messageEvent.onMessage(new RedisMessage(channel, message));
            }
        }, channel);
    }

    public void unsubscribe() {
        subscriber.unsubscribe();
    }

    public static class Subscriber extends JedisPubSub {
    }

}
