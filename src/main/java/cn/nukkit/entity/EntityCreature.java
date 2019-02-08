package cn.nukkit.entity;

import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public abstract class EntityCreature extends EntityLiving implements EntityInteractable {
    public EntityCreature(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public boolean canDoInteraction() {
        return false;
    }

    @Override
    public String getInteractButtonText() {
        return "";
    }
}
