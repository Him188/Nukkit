package cn.nukkit.block;

import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import org.intellij.lang.annotations.MagicConstant;

/**
 * Created by PetteriM1
 */
public class BlockWallBanner extends BlockBanner {

    public BlockWallBanner() {
        this(0);
    }

    public BlockWallBanner(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WALL_BANNER;
    }

    @Override
    public String getName() {
        return "Wall Banner";
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
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            if (this.getDamage() >= BlockFace.NORTH.getIndex() && this.getDamage() <= BlockFace.EAST.getIndex()) {
                if (this.getSide(BlockFace.fromIndex(this.getDamage()).getOpposite()).getId() == AIR) {
                    this.getLevel().useBreakOn(this);
                }
                return Level.BLOCK_UPDATE_NORMAL;
            }
        }
        return 0;
    }
}
