/**
 * This file is part of MateriaMuto, licensed under the MIT License (MIT).
 *
 * Copyright (c) AgileMods <http://www.agilemods.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.agilemods.materiamuto.client.render.tile;

import com.agilemods.materiamuto.client.lib.Texture;
import com.agilemods.materiamuto.common.tile.TileCondenser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import com.agilemods.materiamuto.client.lib.Model;
import com.agilemods.materiamuto.common.block.BlockCondenser;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class RenderTileCondenser extends TileEntitySpecialRenderer {

    public void renderCondenserAt(TileCondenser tile, double x, double y, double z, float partial) {
        ForgeDirection direction;
//        if (tile.getWorldObj() != null) {
//            direction = tile.orientation;
//        } else {
        direction = ForgeDirection.WEST;
//        }

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
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(false);
            GL11.glScaled(1.001, 1.001, 1.001);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_BLEND);

            float pulse = (float) ((MathHelper.sin(((float) tile.getWorldObj().getTotalWorldTime()) / 20.0F) + 1.25F) * 0.5);

            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j / 1.0F, (float) k / 1.0F);
            GL11.glColor4f(pulse + 0.15F, pulse + 0.15F, pulse + 0.15F, pulse + 0.15F);

            Texture.CONDENSER_OVERLAY.bindTexture();

            Model.CONDENSER.renderAllExcept("top");
            GL11.glTranslated(0, -(BlockCondenser.OFFSET * animation), 0);
            Model.CONDENSER.renderOnly("top");
            GL11.glTranslated(0, BlockCondenser.OFFSET * animation, 0);

            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_LIGHTING);
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
        renderCondenserAt((TileCondenser) tile, x, y, z, partial);
    }
}