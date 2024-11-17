package org.kindness.api.messenger;

public interface RedisMessageEvent {

    String getChannel();

    void onMessage(RedisMessage message);
}
