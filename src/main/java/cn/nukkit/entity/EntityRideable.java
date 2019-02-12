package cn.nukkit.entity;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.item.EntityVehicle;
import cn.nukkit.event.entity.EntityVehicleEnterEvent;
import cn.nukkit.event.entity.EntityVehicleExitEvent;
import cn.nukkit.network.protocol.SetEntityLinkPacket;

import java.util.Objects;

import static cn.nukkit.entity.Entity.DATA_FLAGS;
import static cn.nukkit.entity.Entity.DATA_FLAG_RIDING;
import static cn.nukkit.network.protocol.SetEntityLinkPacket.TYPE_PASSENGER;
import static cn.nukkit.network.protocol.SetEntityLinkPacket.TYPE_REMOVE;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public interface EntityRideable extends IEntity {

    /**
     * Mount or Dismounts an Entity from a rideable entity
     *
     * @param rider The target Entity
     *
     * @return {@code true} if the mounting successful
     */
    @SuppressWarnings("Duplicates")
    default boolean mountEntity(Entity rider) {
        Objects.requireNonNull(rider, "The target of the mounting rider can't be null");
        ((Entity) this).PitchDelta = 0.0D;
        ((Entity) this).YawDelta = 0.0D;
        // TODO: Check if its necessary to check if player is dead (So the vehicle wont think that there is rider riding).
        // Check if the rider is riding some sort of vehicle
        // and check if the rider is not dead yet
        if (rider.getRiding() != null) {
            // Run the events
            if (this instanceof EntityVehicle) {
                EntityVehicleExitEvent ev = new EntityVehicleExitEvent(rider, ((EntityVehicle) this));
                Server.getInstance().getPluginManager().callEvent(ev);
                if (ev.isCancelled()) {
                    return false;
                }
            }

            // New Packet
            SetEntityLinkPacket pk;
            pk = new SetEntityLinkPacket();
            pk.rider = getId();         // To the?
            pk.riding = rider.getId(); // From who?
            pk.type = TYPE_REMOVE;      // Byte for leave
            Server.broadcastPacket(((Entity) this).hasSpawned.values(), pk);

            // Broadcast to player
            if (rider instanceof Player) {
                pk = new SetEntityLinkPacket();
                pk.rider = 0;               // To the place of?
                pk.riding = rider.getId(); // From what
                pk.type = TYPE_REMOVE;      // Another byte for leave
                ((Player) rider).dataPacket(pk);
            }

            // Refurbish the rider
            rider.setRiding(null);
            rider.setDataFlag(DATA_FLAGS, DATA_FLAG_RIDING, false);
            this.setLinkedEntity(null);
            ((Entity) this).updateRiderPosition(0);
        } else {
            // Entity entering a vehicle
            if (this instanceof EntityVehicle) {
                EntityVehicleEnterEvent ev = new EntityVehicleEnterEvent(rider, ((EntityVehicle) this));
                Server.getInstance().getPluginManager().callEvent(ev);
                if (ev.isCancelled()) {
                    return false;
                }
            }

            // New Packet
            SetEntityLinkPacket pk;
            pk = new SetEntityLinkPacket();
            pk.rider = getId();         // To the?
            pk.riding = rider.getId(); // From who?
            pk.type = TYPE_PASSENGER;   // Type
            Server.broadcastPacket(((Entity) this).hasSpawned.values(), pk);

            // Broadcast to player
            if (rider instanceof Player) {
                pk = new SetEntityLinkPacket();
                pk.rider = 0;               // To the place of?
                pk.riding = rider.getId(); // From what
                pk.type = TYPE_PASSENGER;   // Byte
                ((Player) rider).dataPacket(pk);
            }

            // Add variables to rider
            rider.setRiding((Entity) this);
            rider.setDataFlag(DATA_FLAGS, DATA_FLAG_RIDING, true);
            this.setLinkedEntity(rider);
            ((Entity) this).updateRiderPosition(getMountedYOffset());
        }
        return true;
    }

}
