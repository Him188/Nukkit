package cn.nukkit.entity;

import cn.nukkit.Player;

/**
 * Author: BeYkeRYkt
 * Nukkit Project
 */
public interface EntityOwnable extends IEntity {
    String getOwnerName();

    void setOwnerName(String playerName);

    Player getOwner();
}
