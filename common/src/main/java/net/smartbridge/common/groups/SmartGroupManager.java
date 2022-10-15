package net.smartbridge.common.groups;

import net.smartbridge.api.groups.ISmartGroup;
import net.smartbridge.api.groups.ISmartGroupManager;
import net.smartbridge.api.servers.ISmartServer;

public class SmartGroupManager implements ISmartGroupManager {
    @Override
    public ISmartGroup getGroup(String name) {
        return null;
    }

    @Override
    public ISmartGroup createGroup(String name) {
        return null;
    }

    @Override
    public boolean isGroupExists(String name) {
        return false;
    }

    @Override
    public void registerSmartServer(ISmartServer iSmartServer, String group) {

    }

    @Override
    public void delSmartServer(ISmartServer iSmartServer, String group) {

    }
}
