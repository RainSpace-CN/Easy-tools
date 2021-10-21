package cn.rainspace.easytools.gui.screen;

import cn.rainspace.easytools.utils.Const;
import cn.rainspace.easytools.inventory.container.WorldAnchorContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class WorldAnchorScreen extends ContainerScreen<WorldAnchorContainer> {
    private final ResourceLocation WORLD_ANCHOR_CONTAINER_RESOURCE = new ResourceLocation(Const.MOD_ID, "textures/gui/container/container.png");

    public WorldAnchorScreen(WorldAnchorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void init(){
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }


    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.minecraft.getTextureManager().bind(WORLD_ANCHOR_CONTAINER_RESOURCE);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        blit(matrixStack, i, j, 0, 0, imageWidth, imageHeight, this.imageWidth, imageHeight);
    }
}
