package net.smartbridge.spigot.redisson;

import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.common.drivers.redisson.messengers.RedissonMessage;
import net.smartbridge.common.drivers.redisson.messengers.RedissonMessageListener;
import net.smartbridge.common.drivers.redisson.messengers.StaticsTopics;

public class BungeecordMessageListener extends RedissonMessageListener {

    public BungeecordMessageListener() {
        super(ServerSide.BUNGEECORD);
    }

    @Override
    public void onMessageReceived(StaticsTopics staticsTopics, RedissonMessage redissonMessage) {
        switch (redissonMessage.getRedissonMessageType()) {
            case CONFIRM_SYNC_BUNGEECORD:
                // TODO: CONFIRM SYNC STATE
                break;
        }
    }
}
