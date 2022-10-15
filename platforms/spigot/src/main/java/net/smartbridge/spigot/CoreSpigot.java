package net.smartbridge.spigot;

import net.smartbridge.api.SmartBridgePlugin;
import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.api.drivers.RedissonDriverConfig;
import net.smartbridge.api.exceptions.BridgeException;
import net.smartbridge.api.exceptions.DatabaseException;
import net.smartbridge.api.util.ServerIP;
import net.smartbridge.common.SmartBridgeImplementation;
import net.smartbridge.common.config.DatabasesConfig;
import net.smartbridge.common.util.ToolsFile;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreSpigot extends JavaPlugin implements SmartBridgePlugin {

    private SmartBridgeImplementation smartBridgeImplementation;

    private DatabasesConfig databasesConfig;
    private ServerIP serverIP;

    @Override
    public void onEnable() {
        try {
            this.initServer();
        } catch (BridgeException e) {
            throw new RuntimeException(e);
        }

        super.onEnable();
    }

    @Override
    public void initServer() throws BridgeException {
        this.serverIP = new ServerIP(
                "127.0.0.1", 25565
        );

        this.databasesConfig = new DatabasesConfig().parseConfig(
                new ToolsFile("databases.json", this.getClassLoader())
        );

        if(databasesConfig == null || !databasesConfig.exists()) {
            throw new DatabaseException("Cannot read database configuration file");
        }

        this.smartBridgeImplementation = new SmartBridgeImplementation(this);

    }

    @Override
    public ServerSide getPluginSide() {
        return ServerSide.SPIGOT;
    }

    @Override
    public String getServerName() {
        return this.getServer().getServerName();
    }

    @Override
    public Object getPlugin() {
        return this;
    }

    @Override
    public ServerIP getServerIP() {
        return serverIP;
    }

    @Override
    public RedissonDriverConfig getRedissonConfig() {
        return databasesConfig.getRedis();
    }

    public SmartBridgeImplementation getSmartBridgeImplementation() {
        return smartBridgeImplementation;
    }
}
