package net.smartbridge.common.servers;

import com.google.gson.Gson;
import lombok.Getter;
import net.smartbridge.api.servers.ISmartServer;
import net.smartbridge.api.servers.ServerType;
import net.smartbridge.api.util.ServerIP;
import net.smartbridge.common.SmartBridgeImplementation;
import net.smartbridge.common.drivers.redisson.RedissonKeys;
import net.smartbridge.common.drivers.redisson.RedissonModel;
import org.redisson.api.RedissonClient;

import java.util.HashMap;
import java.util.UUID;

public class SmartServer implements ISmartServer {

    @Getter
    private static final String serversMap = "smartservers/map";

    private final RedissonModel<ServersKey> serverModel;

    private final UUID uuid;

    public SmartServer(String uniqueName) {
        RedissonClient redissonClient = SmartBridgeImplementation.getInstance().getRedissonDriver().getConnection();

        if(redissonClient.getMap(serversMap).containsKey(uniqueName)) {
            this.uuid = UUID.fromString(
                    redissonClient.getMap(serversMap).get(uniqueName).toString()
            );

            this.serverModel = new RedissonModel<>(ServersKey.class, uuid.toString(), redissonClient);
        }else{
            this.uuid = UUID.randomUUID();

            this.serverModel = new RedissonModel<>(ServersKey.class, uuid.toString(), new HashMap<ServersKey, Object>(){{
                put(ServersKey.UUID, uuid.toString());
                put(ServersKey.NAME, uniqueName);
                put(ServersKey.PLAYERS, 0);
                put(ServersKey.TYPE, ServerType.UNKNOWN.toString());
                put(ServersKey.GROUP, ServerType.UNKNOWN.toString());
                put(ServersKey.IP, ServerType.UNKNOWN.toString());
            }}, redissonClient);
        }
    }

    public SmartServer(UUID uuid) {
        this.uuid = uuid;

        RedissonClient redissonClient = SmartBridgeImplementation.getInstance().getRedissonDriver().getConnection();

        this.serverModel = new RedissonModel<>(ServersKey.class, uuid.toString(), redissonClient);
    }

    public RedissonModel<ServersKey> getServerModel() {
        return serverModel;
    }

    @Override
    @Deprecated
    public void initServer() {

    }

    @Override
    public void disconnectServer() {
        serverModel.clearCache();
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String getName() {
        return serverModel.getString(ServersKey.NAME);
    }

    @Override
    public String getGroup() {
        return serverModel.getString(ServersKey.GROUP);
    }

    @Override
    public ServerIP getServerIP() {
        return ServerIP.parse(
                serverModel.getString(ServersKey.IP)
        );
    }

    @Override
    public ServerType getServerType() {
        return ServerType.valueOf(
                serverModel.getString(ServersKey.TYPE)
        );
    }

    @Override
    public void setServerIP(ServerIP serverIP) {
        serverModel.set(ServersKey.IP, new Gson().toJson(serverIP));
    }

    @Override
    public void setGroup(String group) {
        serverModel.set(ServersKey.GROUP, group);
    }

    @Override
    public void setServerType(ServerType serverType) {
        serverModel.set(ServersKey.TYPE, serverType.toString());
    }

    @Override
    public long getOnlinePlayers() {
        return serverModel.getLong(ServersKey.PLAYERS);
    }

    @Override
    public boolean isLoaded() {
        return serverModel.isExists();
    }

    @RedissonKeys(name = "smartserver")
    private enum ServersKey {

        UUID,
        NAME,
        PLAYERS,
        TYPE,
        GROUP,
        IP

    }
}
