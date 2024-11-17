package org.kindness.api.messenger;

public interface RedisMessageEvent {
    void onMessage(RedisMessage message);
}
