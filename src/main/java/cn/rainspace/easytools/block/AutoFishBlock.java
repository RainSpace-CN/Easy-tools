package cn.rainspace.easytools.block;

import cn.rainspace.easytools.tileentity.AutoFishTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class AutoFishBlock extends AbstractMachineBlock{

    public AutoFishBlock(Properties properties) {
        super(properties);
    }

    public TileEntity newBlockEntity(IBlockReader world) {
        return new AutoFishTileEntity();
    }

    protected void openContainer(World p_220089_1_, BlockPos p_220089_2_, PlayerEntity p_220089_3_) {
        TileEntity tileentity = p_220089_1_.getBlockEntity(p_220089_2_);
        if (tileentity instanceof AutoFishTileEntity) {
            p_220089_3_.openMenu((INamedContainerProvider) tileentity);
        }
    }
}
