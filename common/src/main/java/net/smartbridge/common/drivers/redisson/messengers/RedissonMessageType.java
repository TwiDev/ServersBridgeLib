package net.smartbridge.common.drivers.redisson.messengers;

public enum RedissonMessageType {

    START_SERVER(StaticsTopics.TO_BUNGEECORD),
    SHUTDOWN_SERVER(StaticsTopics.TO_BUNGEECORD),
    CONFIG(StaticsTopics.TO_BUNGEECORD, StaticsTopics.TO_SPIGOT),
    UNKNOWN(StaticsTopics.BROADCAST);

    final StaticsTopics[] serverSides;

    RedissonMessageType(StaticsTopics... serverSides) {
        this.serverSides = serverSides;
    }
}
