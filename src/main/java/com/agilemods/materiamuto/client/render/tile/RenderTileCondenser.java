package com.agilemods.materiamuto.client.render.tile;

import com.agilemods.materiamuto.client.lib.Model;
import com.agilemods.materiamuto.client.lib.Texture;
import com.agilemods.materiamuto.client.render.lib.RenderUtil;
import com.agilemods.materiamuto.common.block.BlockCondenser;
import com.agilemods.materiamuto.common.tile.TileCondenser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class RenderTileCondenser extends TileEntitySpecialRenderer {

    public void renderCondenserAt(TileCondenser tile, double x, double y, double z, float partial) {
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

        Texture.CONDENSER.bindTexture();

        float animation = tile.getAnimationInterpolation();

        Model.CONDENSER.renderAllExcept("top");
        GL11.glTranslated(0, -(BlockCondenser.OFFSET * animation), 0);
        Model.CONDENSER.renderOnly("top");
        GL11.glTranslated(0, BlockCondenser.OFFSET * animation, 0);

        if (tile.getWorldObj() != null) {
            RenderUtil.enableBrightRendering();

            GL11.glScaled(1.001, 1.001, 1.001);

            float pulse = (float) ((MathHelper.sin(((float) tile.getWorldObj().getTotalWorldTime()) / 20.0F) + 1.25F) * 0.5);

            GL11.glColor4f(pulse + 0.15F, pulse + 0.15F, pulse + 0.15F, pulse + 0.15F);

            Texture.CONDENSER_OVERLAY.bindTexture();

            Model.CONDENSER.renderAllExcept("top");
            GL11.glTranslated(0, -(BlockCondenser.OFFSET * animation), 0);
            Model.CONDENSER.renderOnly("top");
            GL11.glTranslated(0, BlockCondenser.OFFSET * animation, 0);

            RenderUtil.disableBrightRendering();
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
        renderCondenserAt((TileCondenser) tile, x, y, z, partial);
    }
}