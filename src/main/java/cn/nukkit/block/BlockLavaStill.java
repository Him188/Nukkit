package cn.nukkit.block;

import cn.nukkit.level.Level;
import org.intellij.lang.annotations.MagicConstant;

/**
 * author: Angelic47
 * Nukkit Project
 */
public class BlockLavaStill extends BlockLava {

    public BlockLavaStill() {
        super(0);
    }

    public BlockLavaStill(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return STILL_LAVA;
    }

    @Override
    public String getName() {
        return "Still Lava";
    }

    @Override
    public BlockLiquid getBlock(int meta) {
        return new BlockLavaStill(meta);
    }

    @Override
    public int onUpdate(@MagicConstant(intValues = {
            Level.BLOCK_UPDATE_NORMAL,
            Level.BLOCK_UPDATE_RANDOM,
            Level.BLOCK_UPDATE_SCHEDULED,
            Level.BLOCK_UPDATE_WEAK,
            Level.BLOCK_UPDATE_TOUCH,
            Level.BLOCK_UPDATE_REDSTONE,
            Level.BLOCK_UPDATE_TICK}) int type) {
        if (type != Level.BLOCK_UPDATE_SCHEDULED) {
            return super.onUpdate(type);
        }
        return 0;
    }
}
