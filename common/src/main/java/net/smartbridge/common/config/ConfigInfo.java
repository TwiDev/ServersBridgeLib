package net.smartbridge.common.config;

import net.smartbridge.api.bridge.ServerSide;

public @interface ConfigInfo {

    String fileName();

    ServerSide serverSide();

}
