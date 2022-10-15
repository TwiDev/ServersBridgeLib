package net.smartbridge.common.drivers.redisson.messengers;

import com.google.gson.Gson;
import lombok.Getter;
import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.common.SmartBridgeImplementation;
import org.redisson.api.RedissonClient;

import java.util.HashMap;

public class RedissonMessengersManager {

    @Getter
    private static final HashMap<ServerSide, StaticsTopics> associateTopics = new HashMap<ServerSide, StaticsTopics>(){{
        put(ServerSide.BUNGEECORD, StaticsTopics.TO_BUNGEECORD);
        put(ServerSide.SPIGOT, StaticsTopics.TO_SPIGOT);
    }};

    public static long sendMessage(StaticsTopics staticsTopics, RedissonMessage redissonMessage) {
        RedissonClient redissonClient = SmartBridgeImplementation.getInstance().getRedissonDriver().getConnection();

        return redissonClient.getTopic(staticsTopics.getTopicName()).publish(
                new Gson().toJson(redissonMessage)
        );
    }
}
