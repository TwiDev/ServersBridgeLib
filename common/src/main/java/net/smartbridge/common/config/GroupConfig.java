package net.smartbridge.common.config;

import lombok.Getter;
import net.smartbridge.api.bridge.ServerSide;

import java.util.ArrayList;
import java.util.List;

@Getter
@ConfigInfo(fileName = "group.json", serverSide = ServerSide.BUNGEECORD)
public class GroupConfig extends Config<GroupConfig> {

    List<GroupObject> groups = new ArrayList<>();

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
