package org.kindness.api;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

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
    private String receiveValue(String key) {
        return jedis.get(key);
    }

    // Object Getter
    public <T> T receiveObject(String key, Class<T> clazz) {
        return gson.fromJson(receiveValue(key), clazz);
    }

    // Close connection
    public void closeConnection() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
