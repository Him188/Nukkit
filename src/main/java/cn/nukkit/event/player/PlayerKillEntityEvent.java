package cn.nukkit.event.player;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityLiving;
import cn.nukkit.event.HandlerList;
import lombok.Getter;

/**
 * @author Him188moe @ NukkitX Project
 */
public class PlayerKillEntityEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    @Getter
    private final EntityLiving entity;

    public PlayerKillEntityEvent(Player player, EntityLiving entity) {
        this.entity = entity;
        this.player = player;
    }
}
