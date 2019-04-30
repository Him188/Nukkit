package cn.nukkit.entity;

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
    boolean mountEntity(Entity rider);

    boolean dismountEntity(Entity entity);
}
