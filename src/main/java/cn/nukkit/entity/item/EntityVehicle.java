package cn.nukkit.entity.item;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityInteractable;
import cn.nukkit.entity.EntityRideable;
import cn.nukkit.entity.data.IntEntityData;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.vehicle.VehicleDamageEvent;
import cn.nukkit.event.vehicle.VehicleDestroyEvent;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public abstract class EntityVehicle extends Entity implements EntityRideable, EntityInteractable {

    public EntityVehicle(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    public int getRollingAmplitude() {
        return this.getDataPropertyInt(DATA_HURT_TIME);
    }

    public void setRollingAmplitude(int time) {
        this.setDataProperty(new IntEntityData(DATA_HURT_TIME, time));
    }

    public int getRollingDirection() {
        return this.getDataPropertyInt(DATA_HURT_DIRECTION);
    }

    public void setRollingDirection(int direction) {
        this.setDataProperty(new IntEntityData(DATA_HURT_DIRECTION, direction));
    }

    public int getDamage() {
        return this.getDataPropertyInt(DATA_HEALTH); // false data name (should be DATA_DAMAGE_TAKEN)
    }

    public void setDamage(int damage) {
        this.setDataProperty(new IntEntityData(DATA_HEALTH, damage));
    }

    @Override
    public String getInteractButtonText() {
        return "Mount";
    }

    @Override
    public boolean canDoInteraction() {
        return passengers.isEmpty();
    }

    /**
     * Mount or Dismounts an Entity from a/into vehicle
     *
     * @param entity The target Entity
     * @return {@code true} if the mounting successful
     */
    @Override
    public boolean mountEntity(Entity entity) {
        Objects.requireNonNull(entity, "The target of the mounting entity can't be null");
        this.PitchDelta = 0.0D;
        this.YawDelta = 0.0D;
        // TODO: Check if its necessary to check if player is dead (So the vehicle wont think that there is entity riding).
        // Check if the entity is riding some sort of vehicle
        // and check if the entity is not dead yet
        if (entity.riding != null) {
            // Run the events
            EntityVehicleExitEvent ev = new EntityVehicleExitEvent(entity, this);
            server.getPluginManager().callEvent(ev);
            if (ev.isCancelled()) {
                return false;
            }
            // New Packet
            SetEntityLinkPacket pk;

            pk = new SetEntityLinkPacket();
            pk.rider = getId();         // To the?
            pk.riding = entity.getId(); // From who?
            pk.type = TYPE_REMOVE;      // Byte for leave
            Server.broadcastPacket(this.hasSpawned.values(), pk);

            // Broadcast to player
            if (entity instanceof Player) {
                pk = new SetEntityLinkPacket();
                pk.rider = 0;               // To the place of?
                pk.riding = entity.getId(); // From what
                pk.type = TYPE_REMOVE;      // Another byte for leave
                ((Player) entity).dataPacket(pk);
            }

            // Refurbish the entity
            entity.riding = null;
            entity.setDataFlag(DATA_FLAGS, DATA_FLAG_RIDING, false);
            linkedEntity = null;
            updateRiderPosition(0);
        } else {
            // Entity entering a vehicle
            EntityVehicleEnterEvent ev = new EntityVehicleEnterEvent(entity, this);
            server.getPluginManager().callEvent(ev);
            if (ev.isCancelled()) {
                return false;
            }

            // New Packet
            SetEntityLinkPacket pk;

            pk = new SetEntityLinkPacket();
            pk.rider = getId();         // To the?
            pk.riding = entity.getId(); // From who?
            pk.type = TYPE_PASSENGER;   // Type
            Server.broadcastPacket(this.hasSpawned.values(), pk);

            // Broadcast to player
            if (entity instanceof Player) {
                pk = new SetEntityLinkPacket();
                pk.rider = 0;               // To the place of?
                pk.riding = entity.getId(); // From what
                pk.type = TYPE_PASSENGER;   // Byte
                ((Player) entity).dataPacket(pk);
            }

            // Add variables to entity
            entity.riding = this;
            entity.setDataFlag(DATA_FLAGS, DATA_FLAG_RIDING, true);
            linkedEntity = entity;
            updateRiderPosition(getMountedYOffset());
        }
        return true;
    }

    @Override
    public boolean onUpdate(int currentTick) {
        // The rolling amplitude
        if (getRollingAmplitude() > 0) {
            setRollingAmplitude(getRollingAmplitude() - 1);
        }

        // A killer task
        if (y < -16) {
            kill();
        }
        // Movement code
        updateMovement();
        return true;
    }

    protected boolean rollingDirection = true;

    protected boolean performHurtAnimation() {
        setRollingAmplitude(9);
        setRollingDirection(rollingDirection ? 1 : -1);
        rollingDirection = !rollingDirection;
        return true;
    }

    @Override
    public boolean attack(EntityDamageEvent source) {
        VehicleDamageEvent event = new VehicleDamageEvent(this, source.getEntity(), source.getFinalDamage());
        getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return false;
        }

        boolean instantKill = false;

        if (source instanceof EntityDamageByEntityEvent) {
            Entity damager = ((EntityDamageByEntityEvent) source).getDamager();
            instantKill = damager instanceof Player && ((Player) damager).isCreative();
        }

        if (instantKill || getHealth() - source.getFinalDamage() < 1) {
            VehicleDestroyEvent event2 = new VehicleDestroyEvent(this, source.getEntity());
            getServer().getPluginManager().callEvent(event2);

            if (event2.isCancelled()) {
                return false;
            }
        }

        if (instantKill) {
            source.setDamage(1000);
        }

        return super.attack(source);
    }
}
