package cn.rainspace.easytools.inventory.container;

import cn.rainspace.easytools.utils.Const;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerType {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Const.MOD_ID);
    public static final RegistryObject<ContainerType<WorldAnchorContainer>> WORLD_ANCHOR_CONTAINER = CONTAINERS.register("world_anchor_container", ()->
            IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data)->
                    new WorldAnchorContainer(windowId,inv, new Inventory(1))));

}
