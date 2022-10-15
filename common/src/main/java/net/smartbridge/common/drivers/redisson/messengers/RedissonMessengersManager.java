package net.smartbridge.common.drivers.redisson.messengers;

import lombok.Getter;
import net.smartbridge.api.bridge.ServerSide;

import java.util.HashMap;

public class RedissonMessengersManager {

    @Getter
    private static final HashMap<ServerSide, StaticsTopics> associateTopics = new HashMap<ServerSide, StaticsTopics>(){{
        put(ServerSide.BUNGEECORD, StaticsTopics.TO_BUNGEECORD);
        put(ServerSide.SPIGOT, StaticsTopics.TO_SPIGOT);
    }};

    public static void sendMessage(StaticsTopics staticsTopics) {

    }
}
