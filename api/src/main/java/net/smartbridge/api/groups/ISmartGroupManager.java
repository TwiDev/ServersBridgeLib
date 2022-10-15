package net.smartbridge.api.groups;

import net.smartbridge.api.servers.ISmartServer;

public interface ISmartGroupManager {

    ISmartGroup getGroup(String name);

    ISmartGroup createGroup(String name);

    boolean isGroupExists(String name);

    void registerSmartServer(ISmartServer iSmartServer, String group);

    void delSmartServer(ISmartServer iSmartServer, String group);

}
