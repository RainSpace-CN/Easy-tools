package cn.rainspace.easytools.tileentity;

import cn.rainspace.easytools.inventory.container.AutoFishContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
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
        return new AutoFishContainer(sycID, inventory, this.inventory);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt){
        nbt.putBoolean("isAvailable",isAvailable);
        ItemStack itemStack_0 = this.inventory.getItem(0).copy();
        nbt.put("item_rod", itemStack_0.serializeNBT());
        ItemStack itemStack_1 = this.inventory.getItem(1).copy();
        nbt.put("item_mineral", itemStack_1.serializeNBT());
        return super.save(nbt);
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt){
        isAvailable = nbt.getBoolean("isAvailable");
        this.inventory.addItem(ItemStack.of(nbt.getCompound("item_rod")));
        this.inventory.addItem(ItemStack.of(nbt.getCompound("item_mineral")));
        super.load(state, nbt);
    }

    public void running() {
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
            super.running();
        }
    }
}
