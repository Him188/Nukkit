package cn.nukkit.entity;

import cn.nukkit.level.format.anvil.Chunk;

/**
 * Entity 将不会在 {@link Chunk#toBinary()} 时保存.
 */
public interface EntityUnsavable {
}
