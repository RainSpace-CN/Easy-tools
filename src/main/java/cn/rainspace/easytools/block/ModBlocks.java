package cn.rainspace.easytools.block;

import cn.rainspace.easytools.utils.Const;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Const.MOD_ID);
    public static final RegistryObject<Block> WORLD_ANCHOR = BLOCKS.register("world_anchor", () -> new WorldAnchorBlock(AbstractBlock.Properties.of(Material.STONE).strength(3.5F).requiresCorrectToolForDrops().lightLevel(litBlockEmission(13))));
    public static final RegistryObject<Block> AUTO_FISH=BLOCKS.register("auto_fish",()->new AutoFishBlock(AbstractBlock.Properties.of(Material.STONE).strength(3.5F).requiresCorrectToolForDrops().lightLevel(litBlockEmission(0))));
    private static ToIntFunction<BlockState> litBlockEmission(int value) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? value : 0;
    }
}