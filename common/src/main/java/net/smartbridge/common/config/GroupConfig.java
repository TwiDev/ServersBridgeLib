package net.smartbridge.common.config;

import net.smartbridge.api.bridge.ServerSide;

import java.util.ArrayList;
import java.util.List;

@ConfigInfo(fileName = "group.json", serverSide = ServerSide.BUNGEECORD)
public class GroupConfig extends Config<GroupConfig> {

    List<GroupObject> groups = new ArrayList<>();

    public List<GroupObject> getGroups() {
        return groups;
    }

    public static class GroupObject {

        private final String name;

        private final String[] templates;

        private final int minimumServicesOnline;

        public GroupObject(String name, String[] templates, int minimumServicesOnline) {
            this.name = name;
            this.templates = templates;
            this.minimumServicesOnline = minimumServicesOnline;
        }

        public String getName() {
            return name;
        }

        public String[] getTemplates() {
            return templates;
        }

        public int getMinimumServicesOnline() {
            return minimumServicesOnline;
        }
    }

}
