package org.kindness.api.messenger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RedisMessage {
    private final String channel;
    private final String message;
}
