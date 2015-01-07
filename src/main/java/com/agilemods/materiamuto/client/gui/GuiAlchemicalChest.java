package com.agilemods.materiamuto.client.gui;

import com.agilemods.materiamuto.common.inventory.ContainerAlchemicalChest;
import com.agilemods.materiamuto.common.tile.TileAlchemicalChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiAlchemicalChest extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation("materiamuto:textures/gui/alchchest.png");

    private final EntityPlayer player;

    private final TileAlchemicalChest tile;

    public GuiAlchemicalChest(EntityPlayer player, TileAlchemicalChest tile) {
        super(new ContainerAlchemicalChest(player, tile));

        this.player = player;
        this.tile = tile;
        this.xSize = 256;
        this.ySize = 231;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float parital, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}