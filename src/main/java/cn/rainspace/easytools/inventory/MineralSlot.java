package cn.rainspace.easytools.inventory;

import cn.rainspace.easytools.inventory.container.WorldAnchorContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class MineralSlot extends Slot {
    private final WorldAnchorContainer menu;

    public MineralSlot(IInventory container, int slot, int x, int y, WorldAnchorContainer menu) {
        super(container, slot, x, y);
        this.menu = menu;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return this.menu.isMineral(itemStack);
    }
}
