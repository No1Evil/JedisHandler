package org.kindness.api;

import redis.clients.jedis.Jedis;

public class RedisConnectionManager extends RedisJsonClient {

    // Constructor
    public RedisConnectionManager() {
        this("localhost");
    }

    public RedisConnectionManager(String host) {
        this(host, 6379);
    }

    public RedisConnectionManager(String host, int port) {
        try {
            this.jedis = new Jedis(host, port);
            this.jedis.connect();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    // Connection getter
    public Jedis getConnection() {
        return jedis;
    }
}
