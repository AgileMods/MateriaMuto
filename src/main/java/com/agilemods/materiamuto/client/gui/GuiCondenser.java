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
package com.agilemods.materiamuto.client.gui;

import com.agilemods.materiamuto.common.emc.EMCHelper;
import com.agilemods.materiamuto.common.inventory.ContainerCondenser;
import com.agilemods.materiamuto.common.tile.TileCondenser;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiCondenser extends GuiContainer {

    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("materiamuto:textures/gui/condenser.png");

    private static final int PROGRESS_X = 39;
    private static final int PROGRESS_Y = 13;
    private static final int PROGRESS_U = 39;
    private static final int PROGRESS_V = 237;

    private EntityPlayer entityPlayer;

    private TileCondenser tileCondenser;

    public GuiCondenser(EntityPlayer entityPlayer, TileCondenser tileCondenser) {
        super(new ContainerCondenser(entityPlayer, tileCondenser));
        this.entityPlayer = entityPlayer;
        this.tileCondenser = tileCondenser;
        this.xSize = 256;
        this.ySize = 237;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partial, int mx, int my) {
        GL11.glColor4f(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(GUI_TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        double percentage = Math.min(1.0D, tileCondenser.emc / tileCondenser.targetEmc);
        int width = (int) Math.min(106, ((float) 106 * percentage));
        this.drawTexturedModalRect(k + PROGRESS_X, l + PROGRESS_Y, PROGRESS_U, PROGRESS_V, width, 10);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        fontRendererObj.drawString(EMCHelper.emcToString(tileCondenser.emc), 157, 15, 0x4A4A4A);
    }
}
