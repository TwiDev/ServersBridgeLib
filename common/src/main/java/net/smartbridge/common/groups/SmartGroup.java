package net.smartbridge.common.groups;

import net.smartbridge.api.groups.ISmartGroup;
import net.smartbridge.api.servers.ISmartServer;
import net.smartbridge.common.SmartBridgeImplementation;
import net.smartbridge.common.drivers.redisson.RedissonKeys;
import net.smartbridge.common.drivers.redisson.RedissonModel;
import net.smartbridge.common.servers.SmartServer;
import org.redisson.api.RedissonClient;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SmartGroup implements ISmartGroup {

    private static final String groupsMap = "smartgroups/map";

    private final RedissonModel<GroupKeys> groupModel;

    public SmartGroup(String name) {
        RedissonClient redissonClient = SmartBridgeImplementation.getInstance().getRedissonDriver().getConnection();

        if(redissonClient.getMap(groupsMap).containsKey(name)) {
            this.groupModel = new RedissonModel<>(GroupKeys.class, name, redissonClient);
        }else{
            this.groupModel = new RedissonModel<>(GroupKeys.class, name, new HashMap<GroupKeys, Object>(){{
                put(GroupKeys.NAME, name);
                put(GroupKeys.SERVICES, Collections.emptyList());
            }}, redissonClient);
        }
    }

    public RedissonModel<GroupKeys> getGroupModel() {
        return groupModel;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<UUID> getCurrentServices() {
        return null;
    }


    @RedissonKeys(name = "smartgroup")
    private enum GroupKeys {

        NAME,
        SERVICES;

    }
}
