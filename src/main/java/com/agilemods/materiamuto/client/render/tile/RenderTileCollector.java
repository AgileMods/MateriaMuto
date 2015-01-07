package com.agilemods.materiamuto.client.render.tile;

import com.agilemods.materiamuto.client.lib.Model;
import com.agilemods.materiamuto.client.lib.Texture;
import com.agilemods.materiamuto.client.render.lib.RenderUtil;
import com.agilemods.materiamuto.common.tile.TileCollector;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderTileCollector extends TileEntitySpecialRenderer {

    public void renderCollectorAt(TileCollector tile, double x, double y, double z, float partial) {
        ForgeDirection direction;
        if (tile.getWorldObj() != null) {
            direction = tile.orientation;
        } else {
            direction = ForgeDirection.WEST;
        }

        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);

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

        switch (tile.getBlockMetadata()) {
            case 2:
                Texture.COLLECTOR_MK3.bindTexture();
                break;
            case 1:
                Texture.COLLECTOR_Mk2.bindTexture();
                break;
            default:
                Texture.COLLECTOR_MK1.bindTexture();
                break;
        }

        Model.COLLECTOR.renderAllExcept("diamond");

        if (tile.getWorldObj() != null) {
            RenderUtil.enableBrightRendering();

            GL11.glScaled(1.001, 0.999, 1.001);

            Texture.COLLECTOR_OVERLAY.bindTexture();

            Model.COLLECTOR.renderAllExcept("diamond");

            float pulse = ((((float) tile.rotationTicker)) / 20.0F) * (180F / (float) Math.PI);
            final float adjust = 0.0625F;
            GL11.glTranslated(0, 0, adjust);
            GL11.glRotated(pulse, 0, 1, 0);
            GL11.glTranslated(0, 0, -adjust);
            Model.COLLECTOR.renderOnly("diamond");

            RenderUtil.disableBrightRendering();
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
        renderCollectorAt((TileCollector) tile, x, y, z, partial);
    }
}