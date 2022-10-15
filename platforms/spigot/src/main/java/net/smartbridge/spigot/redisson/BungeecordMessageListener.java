package net.smartbridge.spigot.redisson;

import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.common.drivers.redisson.messengers.RedissonMessage;
import net.smartbridge.common.drivers.redisson.messengers.RedissonMessageListener;
import net.smartbridge.common.drivers.redisson.messengers.StaticsTopics;
import net.smartbridge.spigot.CoreSpigot;

import java.util.UUID;

public class BungeecordMessageListener extends RedissonMessageListener {

    public BungeecordMessageListener() {
        super(ServerSide.SPIGOT);
    }

    @Override
    public void onMessageReceived(StaticsTopics staticsTopics, RedissonMessage redissonMessage) {
        switch (redissonMessage.getRedissonMessageType()) {
            case CONFIRM_SYNC_BUNGEECORD:
                CoreSpigot.getInstance().establishSync(
                        UUID.fromString(redissonMessage.getData()[0])
                );
                break;
        }
    }
}
