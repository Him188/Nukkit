package cn.nukkit.level;

import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public interface ChunkLoader {

    int getLoaderId();

    boolean isLoaderActive();

    Position getPosition();

    double getX();

    double getZ();

    /**
     * 保持加载除当前对象所在的 chunk 外, 还要额外保持加载的 chunk 范围半径.
     *
     * @return 保持加载除当前对象所在的 chunk 外, 还要额外保持加载的 chunk 范围半径.
     */
    default int getKeepChunkLoadRange() {
        return 0;
    }

    Level getLevel();

    default void onChunkChanged(FullChunk chunk) {

    }

    default void onChunkLoaded(FullChunk chunk) {

    }

    default void onChunkUnloaded(FullChunk chunk) {

    }

    default void onChunkPopulated(FullChunk chunk) {

    }

    default void onBlockChanged(Vector3 block) {

    }
}
