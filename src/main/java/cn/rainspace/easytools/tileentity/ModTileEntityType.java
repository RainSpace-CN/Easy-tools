package cn.rainspace.easytools.tileentity;

import cn.rainspace.easytools.block.ModBlocks;
import cn.rainspace.easytools.utils.Const;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityType {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Const.MOD_ID);
    public static final RegistryObject<TileEntityType<WorldAnchorTileEntity>> WORLD_ANCHOR = TILE_ENTITIES.register("world_anchor", () -> TileEntityType.Builder.of(WorldAnchorTileEntity::new, ModBlocks.WORLD_ANCHOR.get()).build(null));
    public static final RegistryObject<TileEntityType<AutoFishTileEntity>> AUTO_FISH = TILE_ENTITIES.register("auto_fish", () -> TileEntityType.Builder.of(AutoFishTileEntity::new, ModBlocks.AUTO_FISH.get()).build(null));

}
