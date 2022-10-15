package net.smartbridge.common.drivers.redisson.messengers;

import com.google.gson.Gson;
import lombok.Getter;
import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.common.SmartBridgeImplementation;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;

@Getter
public abstract class RedissonMessageListener {

    private final ServerSide serverSide;

    private final StaticsTopics staticsTopics;

    private final RTopic rTopic, broadcastTopic;

    public RedissonMessageListener(ServerSide serverSide) {
        this.serverSide = serverSide;

        RedissonClient redissonClient = SmartBridgeImplementation.getInstance().getRedissonDriver().getConnection();

        this.staticsTopics = RedissonMessengersManager.getAssociateTopics().get(serverSide);

        this.rTopic = redissonClient.getTopic(
            staticsTopics.getTopicName()
        );

        this.broadcastTopic = redissonClient.getTopic(StaticsTopics.BROADCAST.getTopicName());

        this.rTopic.addListener(String.class, (charSequence, s) -> analyseMessage(s));
        this.broadcastTopic.addListener(String.class, (charSequence, s) -> analyseMessage(s));

    }

    public void analyseMessage(String s) {
        try {
            RedissonMessage redissonMessage = new Gson().fromJson(s, RedissonMessage.class);

            if(redissonMessage == null) {
                throw new NullPointerException("Cannot read a redisson message " + s);
            }

            onMessageReceived(staticsTopics, redissonMessage);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public abstract void onMessageReceived(StaticsTopics staticsTopics, RedissonMessage redissonMessage);
}
