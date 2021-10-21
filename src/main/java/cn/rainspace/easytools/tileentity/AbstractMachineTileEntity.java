package cn.rainspace.easytools.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class AbstractMachineTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    private AbstractMachineTileEntity self = this;
    protected Inventory inventory;
    private int litTime = 0;
    private int VALUE = 100;

    public AbstractMachineTileEntity(TileEntityType<?> type,int inventorySize) {
        super(type);
        inventory = new Inventory(inventorySize){
            AbstractMachineTileEntity owner = self;
            @Override
            public boolean stillValid(PlayerEntity player){
                return owner.stillValid(player);
            }
        };
    }

    public void turnOn (){
        litTime=VALUE;
    }

    public void turnOff (){
        litTime=0;
    }

    public void running (){
        litTime--;
    }

    @Override
    public void tick(){
        if(!level.isClientSide) {
            if(litTime ==0) {
                ItemStack itemStack = inventory.getItem(0);
                if(!itemStack.isEmpty()){
                    itemStack.shrink(1);
                    this.turnOn();
                }
            } else if(litTime == 1) {
                running();
                ItemStack itemStack = inventory.getItem(0);
                if(itemStack.isEmpty()) {
                    this.turnOff();
                }else{
                    itemStack.shrink(1);
                    litTime+=VALUE;
                }
            }else if(litTime>1){
                running();
            } else{
                litTime=0;
            }
        }
    }

    public boolean stillValid(PlayerEntity player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void setRemoved(){
        super.setRemoved();
        if(!level.isClientSide()) {
            turnOff();
        }
    }

    public boolean isLit(){
        return this.litTime>0;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt){
        if(!this.level.isClientSide()) {
            ListNBT items = (ListNBT) nbt.get("items");
            for (int i = 0; i < this.inventory.getContainerSize(); i++) {
                this.inventory.addItem(ItemStack.of((CompoundNBT) items.get(i)));
            }
        }
        super.load(state,nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt){
        ListNBT items = new ListNBT();
        for(int i=0;i<this.inventory.getContainerSize();i++){
            items.add(this.inventory.getItem(0).serializeNBT());
        }
        nbt.put("items", items);
        return super.save(nbt);
    }
}
