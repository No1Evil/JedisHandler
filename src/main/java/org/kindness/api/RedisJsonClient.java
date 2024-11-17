package org.kindness.api;

import com.google.gson.Gson;
import org.kindness.api.messenger.RedisMessage;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public abstract class RedisJsonClient {

    private final Gson gson;
    protected Jedis jedis;

    // Constructor
    public RedisJsonClient() {
        this.gson = new Gson();
    }

    // JSON Sender
    public void sendJson(String key, Object object) {
        String json = gson.toJson(object);
        jedis.set(key, json);
    }

    // JSON Getter
    private String getValue(String key) {
        return jedis.get(key);
    }

    // Object Getter
    public <T> T getObject(String key, Class<T> clazz) {
        return gson.fromJson(getValue(key), clazz);
    }

    public void publishMessage(RedisMessage message) {
        jedis.publish(message.getChannel(), message.getMessage());
    }

    public void subscribeChannel(JedisPubSub subscriber, String channel) {
        jedis.subscribe(subscriber, channel);
    }

    public void unsubscribeChannel(JedisPubSub subscriber) {
        subscriber.unsubscribe();
    }

    // Close connection
    public void closeConnection() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
