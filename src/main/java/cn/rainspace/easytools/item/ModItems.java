package cn.rainspace.easytools.item;

import cn.rainspace.easytools.utils.Const;
import cn.rainspace.easytools.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Const.MOD_ID);
    public static final RegistryObject<Item> WORLD_ANCHOR = ITEMS.register("world_anchor", () -> new BlockItem(ModBlocks.WORLD_ANCHOR.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
}