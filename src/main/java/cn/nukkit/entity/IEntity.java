package cn.nukkit.entity;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.entity.data.EntityData;
import cn.nukkit.entity.data.EntityMetadata;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityRegainHealthEvent;
import cn.nukkit.event.player.PlayerTeleportEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector2;
import cn.nukkit.math.Vector3;
import cn.nukkit.metadata.MetadataValue;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.potion.Effect;

import java.util.List;
import java.util.Map;

/**
 * 映射 Entity 中的方法
 *
 * @author Him188moe @ NukkitX Project
 */
public interface IEntity {

    float getHeight();

    float getEyeHeight();

    float getWidth();

    float getLength();

    double getStepHeight();

    float getBaseOffset();

    boolean canCollide();

    float getGravity();

    float getDrag();

    boolean hasCustomName();

    String getNameTag();

    boolean isNameTagVisible();

    boolean isNameTagAlwaysVisible();

    void setNameTag(String name);

    void setNameTagVisible();

    void setNameTagVisible(boolean value);

    void setNameTagAlwaysVisible();

    void setNameTagAlwaysVisible(boolean value);

    boolean isSneaking();

    void setSneaking();

    void setSneaking(boolean value);

    boolean isSwimming();

    void setSwimming();

    void setSwimming(boolean value);

    boolean isSprinting();

    void setSprinting();

    void setSprinting(boolean value);

    boolean isGliding();

    void setGliding();

    void setGliding(boolean value);

    boolean isImmobile();

    void setImmobile();

    void setImmobile(boolean value);

    boolean canClimb();

    void setCanClimb();

    void setCanClimb(boolean value);

    boolean canClimbWalls();

    void setCanClimbWalls();

    void setCanClimbWalls(boolean value);

    void setScale(float scale);

    float getScale();

    Entity getLinkedEntity();

    void setLinkedEntity(Entity entity);

    Map<Integer, Effect> getEffects();

    void removeAllEffects();

    void removeEffect(int effectId);

    Effect getEffect(int effectId);

    boolean hasEffect(int effectId);

    void addEffect(Effect effect);

    void collidingWith(Entity ent);

    void saveNBT();

    String getName();

    String getSaveId();

    void spawnTo(Player player);

    Map<Integer, Player> getViewers();

    void sendPotionEffects(Player player);

    void sendData(Player player);

    void sendData(Player player, EntityMetadata data);

    void sendData(Player[] players);

    void sendData(Player[] players, EntityMetadata data);

    void despawnFrom(Player player);

    boolean attack(EntityDamageEvent source);

    boolean attack(float damage);

    void heal(EntityRegainHealthEvent source);

    void heal(float amount);

    float getHealth();

    boolean isAlive();

    boolean isClosed();

    void setHealth(float health);

    void setLastDamageCause(EntityDamageEvent type);

    EntityDamageEvent getLastDamageCause();

    int getMaxHealth();

    void setMaxHealth(int maxHealth);

    boolean canCollideWith(Entity entity);

    boolean entityBaseTick();

    boolean entityBaseTick(int tickDiff);

    Vector3 getDirectionVector();

    Vector2 getDirectionPlane();

    BlockFace getHorizontalFacing();

    boolean onUpdate(int currentTick);

    float getMountedYOffset();

    boolean isOnFire();

    void setOnFire(int seconds);

    float getAbsorption();

    void setAbsorption(float absorption);

    BlockFace getDirection();

    void extinguish();

    boolean canTriggerWalking();

    void resetFallDistance();

    AxisAlignedBB getBoundingBox();

    void fall(float fallDistance);

    void handleLavaMovement();

    void moveFlying(float strafe, float forward, float friction);

    void onCollideWithPlayer(EntityHuman entityPlayer);

    void applyEntityCollision(Entity entity);

    void onStruckByLightning(Entity entity);

    boolean onInteract(Player player, Item item);

    Position getPosition();

    Location getLocation();

    boolean isInsideOfWater();

    boolean isInsideOfSolid();

    boolean isInsideOfFire();

    boolean fastMove(double dx, double dy, double dz);

    boolean move(double dx, double dy, double dz);

    List<Block> getBlocksAround();

    List<Block> getCollisionBlocks();

    boolean setPositionAndRotation(Vector3 pos, double yaw, double pitch);

    void setRotation(double yaw, double pitch);

    boolean doesTriggerPressurePlate();

    boolean setPosition(Vector3 pos);

    Vector3 getMotion();

    boolean setMotion(Vector3 motion);

    boolean isOnGround();

    void kill();

    boolean teleport(Vector3 pos);

    boolean teleport(Vector3 pos, PlayerTeleportEvent.TeleportCause cause);

    boolean teleport(Position pos);

    boolean teleport(Position pos, PlayerTeleportEvent.TeleportCause cause);

    boolean teleport(Location location);

    boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause);

    long getId();

    void respawnToAll();

    void spawnToAll();

    void despawnFromAll();

    void close();

    boolean setDataProperty(EntityData data);

    boolean setDataProperty(EntityData data, boolean send);

    EntityMetadata getDataProperties();

    EntityData getDataProperty(int id);

    int getDataPropertyInt(int id);

    int getDataPropertyShort(int id);

    int getDataPropertyByte(int id);

    boolean getDataPropertyBoolean(int id);

    long getDataPropertyLong(int id);

    String getDataPropertyString(int id);

    float getDataPropertyFloat(int id);

    Item getDataPropertySlot(int id);

    Vector3 getDataPropertyPos(int id);

    int getDataPropertyType(int id);

    void setDataFlag(int propertyId, int id);

    void setDataFlag(int propertyId, int id, boolean value);

    boolean getDataFlag(int propertyId, int id);

    void setMetadata(String metadataKey, MetadataValue newMetadataValue);

    List<MetadataValue> getMetadata(String metadataKey);

    boolean hasMetadata(String metadataKey);

    void removeMetadata(String metadataKey, Plugin owningPlugin);

    Server getServer();

    boolean equals(Object obj);

    int hashCode();

}
