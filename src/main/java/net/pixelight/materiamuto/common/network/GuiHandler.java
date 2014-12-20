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
package net.pixelight.materiamuto.common.network;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.pixelight.materiamuto.MateriaMuto;
import net.pixelight.materiamuto.client.gui.GuiAlchemicalBag;
import net.pixelight.materiamuto.client.gui.GuiCondenser;
import net.pixelight.materiamuto.common.inventory.ContainerAlchemicalBag;
import net.pixelight.materiamuto.common.inventory.ContainerCondenser;
import net.pixelight.materiamuto.common.tile.TileCondenser;

public class GuiHandler implements IGuiHandler {

    public static enum Type {
        GUI_ALCHEMICAL_CHEST,
        GUI_CONDENSER;

        public void openGui(EntityPlayer entityPlayer) {
            openGui(entityPlayer, 0, 0, 0);
        }

        public void openGui(EntityPlayer entityPlayer, int x, int y, int z) {
            entityPlayer.openGui(MateriaMuto.instance, this.ordinal(), entityPlayer.worldObj, x, y, z);
        }

        private static Type[] values;

        public static Type[] all() {
            if (values == null) {
                values = values();
            }
            return values;
        }

        public static Type get(int ordinal) {
            return all()[ordinal % all().length];
        }
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        Type type = Type.get(id);
        switch (type) {
            case GUI_ALCHEMICAL_CHEST:
                return new ContainerAlchemicalBag(entityPlayer, entityPlayer.getHeldItem());
            case GUI_CONDENSER:
                return new ContainerCondenser(entityPlayer, (TileCondenser) world.getTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        Type type = Type.get(id);
        switch (type) {
            case GUI_ALCHEMICAL_CHEST:
                return new GuiAlchemicalBag(entityPlayer, entityPlayer.getHeldItem());
            case GUI_CONDENSER:
                return new GuiCondenser(entityPlayer, (TileCondenser) world.getTileEntity(x, y, z));
        }
        return null;
    }
}
