package cn.rainspace.easytools.tileentity;

import cn.rainspace.easytools.inventory.container.AutoFishContainer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

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
        return new AutoFishContainer(sycID, inventory, this.inventory);
    }

    @Override
    public void tick() {
        if(!level.isClientSide) {
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
        }
        if(isAvailable) {
           super.tick();
        }
    }
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getBlockPos(), 1, getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compoundNBT = super.getUpdateTag();
        compoundNBT.putInt("litTime", getLitTime());
        return compoundNBT;
    }
}
