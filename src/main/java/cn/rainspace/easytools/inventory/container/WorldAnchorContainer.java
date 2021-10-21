package cn.rainspace.easytools.inventory.container;

import cn.rainspace.easytools.inventory.MineralSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class WorldAnchorContainer extends Container {
    private IInventory container;

    public WorldAnchorContainer(int id, PlayerInventory playerInventory, IInventory inventory) {
        super(ModContainerType.WORLD_ANCHOR_CONTAINER.get(), id);
        this.addSlot(new MineralSlot(inventory, 0, 80, 32, this));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
        this.container = inventory;

    }

    public boolean isMineral(ItemStack itemStack) {
        return itemStack.getItem() == Items.IRON_INGOT || itemStack.getItem() == Items.GOLD_INGOT || itemStack.getItem() == Items.DIAMOND || itemStack.getItem() == Items.EMERALD;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 0) {
                if (!this.moveItemStackTo(itemstack1, 1, 37, false)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else {
                if (!this.isMineral(itemstack1) || !this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    if (index < 28) {
                        if (!this.moveItemStackTo(itemstack1, 28, 37, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index >= 28 && index < 37 && !this.moveItemStackTo(itemstack1, 1, 28, false)) {
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

    public boolean stillValid(PlayerEntity player) {
        return this.container.stillValid(player);
    }
}
