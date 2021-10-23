package cn.rainspace.easytools.tileentity;

import cn.rainspace.easytools.inventory.container.AutoFishContainer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;


public class AutoFishTileEntity extends AbstractMachineTileEntity{

    private boolean isAvailable=false;

    public AutoFishTileEntity() {
        super(ModTileEntityType.AUTO_FISH.get(), 2);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.auto_fish");
    }

    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        return new AutoFishContainer(sycID, inventory, this.inventory,litTimeNumber);
    }

    @Override
    public void running() {
        BlockPos blockPos=this.getBlockPos();
        double X=blockPos.getX();
        double Y=blockPos.getY();
        double Z=blockPos.getZ();
        BlockPos checkedPos=new BlockPos(X,Y-1,Z);
        Block checkedBlock = level.getBlockState(checkedPos).getBlock();
        if(checkedBlock.equals(Blocks.WATER)) {
            isAvailable=true;
        } else {
            isAvailable=false;
        }
        if(isAvailable) {
            setLitTime(getLitTime()-1);
        }
    }


}
