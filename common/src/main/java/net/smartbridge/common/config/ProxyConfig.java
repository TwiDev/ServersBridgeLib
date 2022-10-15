package net.smartbridge.common.config;

import lombok.Getter;
import net.smartbridge.api.bridge.ServerSide;

@Getter
@ConfigInfo(fileName = "config.json", serverSide = ServerSide.BUNGEECORD)
public class ProxyConfig {

    ServerConfig.BridgeProperties bridgeProperties;

}
