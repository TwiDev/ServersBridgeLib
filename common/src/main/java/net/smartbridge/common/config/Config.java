package net.smartbridge.common.config;

import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.common.util.JsonLib;
import net.smartbridge.common.util.ToolsFile;

public abstract class Config<T extends Config<T>> {

    private static final JsonLib parser = new JsonLib();

    public T parseConfig(ToolsFile toolsFile) {
        Config<?> config = parser.read(this.getClass(), toolsFile);

        return (T) config;
    }
    
    public void share(ServerSide... serverSide) {
        for (ServerSide side : serverSide) {

        }
        
    }

}
