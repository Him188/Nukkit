package cn.nukkit.event.player;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityLiving;
import lombok.Getter;

/**
 * @author Him188moe @ NukkitX Project
 */
public class PlayerKillEntityEvent extends PlayerEvent {

    @Getter
    private final EntityLiving entity;

    public PlayerKillEntityEvent(Player player, EntityLiving entity) {
        this.entity = entity;
        this.player = player;
    }
}
