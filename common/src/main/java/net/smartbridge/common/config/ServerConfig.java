package net.smartbridge.common.config;

import lombok.Getter;
import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.api.servers.ServerType;

@Getter
@ConfigInfo(fileName = "config.json", serverSide = ServerSide.SPIGOT)
public class ServerConfig extends Config<ServerConfig> {

    private BridgeProperties bridge;

    private ServerProperties server;

    public static class BridgeProperties {

        private final String linkToken;

        public BridgeProperties(String linkToken) {
            this.linkToken = linkToken;
        }

        public String getLinkToken() {
            return linkToken;
        }
    }

    public static class ServerProperties {

        private final String type;
        private String group = "";
        private String name = "";

        public ServerProperties(String type) {
            this.type = type;
        }

        public ServerProperties(String type, String group) {
            this.type = type;
            this.group = group;
        }

        public ServerType getType() {
            return ServerType.valueOf(type);
        }

        public String getGroup() {
            return group;
        }

        public String getName() {
            return name;
        }
    }


}
