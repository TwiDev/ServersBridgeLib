package net.smartbridge.spigot;

import net.smartbridge.api.SmartBridgeAPI;
import net.smartbridge.api.SmartBridgePlugin;
import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.api.drivers.RedissonDriverConfig;
import net.smartbridge.api.exceptions.BridgeException;
import net.smartbridge.api.exceptions.ConfigException;
import net.smartbridge.api.exceptions.DatabaseException;
import net.smartbridge.api.groups.ISmartGroup;
import net.smartbridge.api.servers.ServerType;
import net.smartbridge.api.util.ServerIP;
import net.smartbridge.common.SmartBridgeImplementation;
import net.smartbridge.common.config.DatabasesConfig;
import net.smartbridge.common.config.ServerConfig;
import net.smartbridge.common.util.ToolsFile;
import net.smartbridge.spigot.util.ServerProperties;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class CoreSpigot extends JavaPlugin implements SmartBridgePlugin {

    private SmartBridgeImplementation smartBridgeImplementation;

    private DatabasesConfig databasesConfig;
    private ServerConfig serverConfig;

    private ServerIP serverIP;

    private ServerType serverType;

    private String serverName;

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
        ServerProperties.Properties properties = new ServerProperties.Properties(this);

        this.serverConfig = new ServerConfig().parseConfig(
                new ToolsFile("config.json", getClassLoader())
        );

        this.serverType = serverConfig.getServer().getType();

        if(serverType == ServerType.BALANCER || serverType == ServerType.DYNAMIC) {
            String group = serverConfig.getServer().getGroup();

            if(group == null) {
                throw new ConfigException("the group property is not defined in the server configuration for a BALANCER or DYNAMIC type server");
            }

            if(serverType == ServerType.BALANCER) {
                this.serverName = group;
            }

            ISmartGroup smartGroup = SmartBridgeImplementation.getInstance().getGroupManager().getGroup(group);

            this.serverName = group + "-" + smartGroup.getCurrentServices().size() + 1;
        }else{
            this.serverName = serverConfig.getServer().getName();
        }

        if(serverName == null || serverName.isEmpty()) {
            SmartBridgeAPI.getInstance().getLogger().log("Plugin cannot load : Bridge properties are empty in config.json.", Level.SEVERE);
            this.setEnabled(false);
            return;
        }

        if(SmartBridgeImplementation.getInstance().getServerManager().isServerExists(serverName)) {
            this.setEnabled(false);
            throw new BridgeException("cannot create a server, there is already an server with the same name " + serverName);
        }

        this.serverIP = new ServerIP(
                properties.get(ServerProperties.SERVER_IP),
                properties.getInt(ServerProperties.SERVER_PORT)
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
        return serverName;
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
