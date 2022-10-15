package net.smartbridge.common.drivers.redisson.messengers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StaticsTopics {

    TO_BUNGEECORD("smartBungeecord"),
    TO_SPIGOT("smartSpigot"),
    BROADCAST("smartBroadcast");

    final String topicName;

}
