package net.smartbridge.common.drivers.redisson.messengers;

public abstract class RedissonMessage {

    private final String test;

    public RedissonMessage(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }
}
