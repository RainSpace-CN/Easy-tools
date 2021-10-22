package cn.rainspace.easytools.inventory;

import cn.rainspace.easytools.inventory.container.AutoFishContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class AutoFishRodSlot extends Slot{
    private final AutoFishContainer menu;

    public AutoFishRodSlot(IInventory container, int slot, int x, int y, AutoFishContainer menu) {
        super(container, slot, x, y);
        this.menu=menu;
    }
    @Override
    public boolean mayPlace(ItemStack itemStack){
        return this.menu.isFishingRod(itemStack);
    }
}
