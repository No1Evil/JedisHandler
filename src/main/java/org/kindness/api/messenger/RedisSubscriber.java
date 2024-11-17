package org.kindness.api.messenger;

import lombok.Getter;
import org.kindness.api.RedisConnectionManager;
import redis.clients.jedis.JedisPubSub;

@Getter
public class RedisSubscriber {

    private final Subscriber subscriber;

    public RedisSubscriber(RedisConnectionManager connectionManager, RedisMessageEvent messageEvent) {
        connectionManager.getConnection().subscribe(this.subscriber = new Subscriber(){
            @Override
            public void onMessage(String channel, String message) {
                 messageEvent.onMessage(new RedisMessage(channel, message));
            }
        }, messageEvent.getChannel());
    }

    public void unsubscribe() {
        subscriber.unsubscribe();
    }

    public static class Subscriber extends JedisPubSub {
    }

}
