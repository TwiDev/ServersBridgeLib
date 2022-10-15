package net.smartbridge.common.drivers.redisson.messengers;

public class RedissonMessage {

    private final RedissonMessageType redissonMessageType;

    private final String[] data;

    public RedissonMessage(RedissonMessageType redissonMessageType, String[] data) {
        this.redissonMessageType = redissonMessageType;
        this.data = data;
    }

    public RedissonMessageType getRedissonMessageType() {
        return redissonMessageType;
    }

    public String[] getData() {
        return data;
    }
}
