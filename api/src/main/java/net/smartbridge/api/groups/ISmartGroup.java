package net.smartbridge.api.groups;

import java.util.List;
import java.util.UUID;

public interface ISmartGroup {

    String getName();

    List<UUID> getCurrentServices();

}
