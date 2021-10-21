package cn.rainspace.easytools.tileentity;

import cn.rainspace.easytools.block.WorldAnchorBlock;
import cn.rainspace.easytools.inventory.container.WorldAnchorContainer;
import cn.rainspace.easytools.utils.Const;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.world.ForgeChunkManager;

public class WorldAnchorTileEntity extends AbstractMachineTileEntity {
	public WorldAnchorTileEntity() {
		super(ModTileEntityType.WORLD_ANCHOR.get(),1);
	}

	public void turnOn(){
		super.turnOn();
		ChunkPos chunkpos = new ChunkPos(getBlockPos());
		ForgeChunkManager.forceChunk((ServerWorld) level, Const.MOD_ID, getBlockPos(), chunkpos.x, chunkpos.z, true, true);
		this.level.setBlockAndUpdate(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(WorldAnchorBlock.LIT, Boolean.valueOf(this.isLit())));
	}

	public void turnOff(){
		super.turnOff();
		ChunkPos chunkpos = new ChunkPos(getBlockPos());
		ForgeChunkManager.forceChunk((ServerWorld) level, Const.MOD_ID, getBlockPos(), chunkpos.x, chunkpos.z, false, true);
		if(!this.isRemoved()){
			this.level.setBlockAndUpdate(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(WorldAnchorBlock.LIT, Boolean.valueOf(this.isLit())));
		}
	}

	public void running(){
		super.running();
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.world_anchor");
	}

	@Override
	public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
		return new WorldAnchorContainer(sycID, inventory, this.inventory);
	}


}
