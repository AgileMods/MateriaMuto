package com.agilemods.materiamuto.client.render.tile;

import com.agilemods.materiamuto.client.lib.Model;
import com.agilemods.materiamuto.client.lib.Texture;
import com.agilemods.materiamuto.client.render.lib.RenderUtil;
import com.agilemods.materiamuto.common.tile.TileAlchemicalChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class RenderTileAlchemicalChest extends TileEntitySpecialRenderer {

    public void renderChestAt(TileAlchemicalChest tile, double x, double y, double z, float partial) {
        ForgeDirection direction;
        if (tile.getWorldObj() != null) {
            direction = tile.orientation;
        } else {
            direction = ForgeDirection.EAST;
        }

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);

        short angle = 0;
        if (direction != null) {
            if (direction == ForgeDirection.NORTH) {
                angle = 0;
            } else if (direction == ForgeDirection.SOUTH) {
                angle = 180;
            } else if (direction == ForgeDirection.WEST) {
                angle = 90;
            } else if (direction == ForgeDirection.EAST) {
                angle = -90;
            }
        }

        GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);

        float adjustedLidAngle = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partial;
        adjustedLidAngle = 1.0F - adjustedLidAngle;
        adjustedLidAngle = 1.0F - adjustedLidAngle * adjustedLidAngle * adjustedLidAngle;

        Texture.ALCHEMICAL_CHEST.bindTexture();

        GL11.glTranslated(0, 0.0625, 0.4375);
        GL11.glRotated((adjustedLidAngle * 90), 1, 0, 0);
        GL11.glTranslated(0, -0.0625, -0.4375);
        Model.ALCHEMICAL_CHESET.renderOnly("top", "latch");
        GL11.glTranslated(0, 0.0625, 0.4375);
        GL11.glRotated(-(adjustedLidAngle * 90), 1, 0, 0);
        GL11.glTranslated(0, -0.0625, -0.4375);

        Model.ALCHEMICAL_CHESET.renderAllExcept("top", "latch");

        if (tile.getWorldObj() != null) {
            RenderUtil.enableBrightRendering();

            GL11.glScaled(1.001, 1.001, 1.001);

            float pulse = (float) ((MathHelper.sin(((float) tile.getWorldObj().getTotalWorldTime()) / 20.0F) + 1.25F) * 0.5);

            GL11.glColor4f(pulse + 0.15F, pulse + 0.15F, pulse + 0.15F, pulse + 0.15F);

            Texture.ALCHEMICAL_CHEST_OVERLAY.bindTexture();

            GL11.glTranslated(0, 0.0625, 0.4375);
            GL11.glRotated((adjustedLidAngle * 90), 1, 0, 0);
            GL11.glTranslated(0, -0.0625, -0.4375);
            Model.ALCHEMICAL_CHESET.renderOnly("top", "latch");
            GL11.glTranslated(0, 0.0625, 0.4375);
            GL11.glRotated(-(adjustedLidAngle * 90), 1, 0, 0);
            GL11.glTranslated(0, -0.0625, -0.4375);

            Model.ALCHEMICAL_CHESET.renderAllExcept("top", "latch");

            RenderUtil.disableBrightRendering();
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
        renderChestAt((TileAlchemicalChest) tile, x, y, z, partial);
    }
}