package cn.rainspace.easytools.inventory.container;

import cn.rainspace.easytools.inventory.AutoFishMineralSlot;
import cn.rainspace.easytools.inventory.AutoFishRodSlot;
import cn.rainspace.easytools.tileentity.LitTimeNumber;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IIntArray;

public class AutoFishContainer extends Container {
    private IInventory container;
    private LitTimeNumber intArray;
    public AutoFishContainer(int id, PlayerInventory playerInventory, IInventory inventory, LitTimeNumber intArray) {
        super(ModContainerType.AUTO_FISH_CONTAINER.get(),id);
        this.addSlot(new AutoFishMineralSlot(inventory, 0, 80,54 ,this));
        this.addSlot(new AutoFishRodSlot(inventory, 1, 80, 18,this));
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
        this.container = inventory;
        this.intArray = intArray;

        addDataSlots(this.intArray);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        //May be wrong,should pay attention
        //TODO
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0 || index==1) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else  {
                if ((!this.isMineral(itemstack1)&&!this.isFishingRod(itemstack1))||!this.moveItemStackTo(itemstack1, 0, 2, false)) {
                    if (index < 29) {
                        if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index >= 29 && index < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }
            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }

    public boolean isMineral(ItemStack itemStack){
        return itemStack.getItem()== Items.IRON_INGOT||itemStack.getItem()== Items.GOLD_INGOT||itemStack.getItem()== Items.DIAMOND||itemStack.getItem()== Items.EMERALD;
    }
    public boolean isFishingRod(ItemStack itemStack){
        return itemStack.getItem()== Items.FISHING_ROD;
    }

    public IIntArray getIntArray() {
        return intArray;
    }

}
