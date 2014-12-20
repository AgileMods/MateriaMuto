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
package com.agilemods.materiamuto.common.item;

import com.agilemods.materiamuto.common.item.prefab.MMSubItem;
import com.agilemods.materiamuto.common.network.GuiHandler;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.awt.*;
import java.util.List;

public class ItemAlchemicalBag extends MMSubItem {

    public static final String[]
            COLORS =
            new String[]{"white", "orange", "magenta", "lightBlue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown",
                         "green", "red", "black"};

    private static final int[] colorCache = new int[COLORS.length];

    public ItemAlchemicalBag() {
        super();
        setHasSubtypes(true);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean debug) {
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("target")) {
            list.add("Bound to: " + itemStack.getTagCompound().getString("target"));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        if (!itemStack.getTagCompound().hasKey("target")) {
            itemStack.getTagCompound().setString("target", entityPlayer.getCommandSenderName());
        }
        GuiHandler.Type.GUI_ALCHEMICAL_CHEST.openGui(entityPlayer);
        return itemStack;
    }

    @Override
    public int getColorFromItemStack(ItemStack itemStack, int pass) {
        int cachedColor = colorCache[itemStack.getItemDamage()];
        if (cachedColor == 0) {
            float[] colorArray = EntitySheep.fleeceColorTable[itemStack.getItemDamage()];
            Color color = new Color(colorArray[0], colorArray[1], colorArray[2]).brighter();
            colorCache[itemStack.getItemDamage()] = color.getRGB();
        }
        return cachedColor;
    }

    @Override
    public String[] getNames() {
        return COLORS;
    }

    @Override
    public String getIconPrefix() {
        return null;
    }

    @Override
    public String getOverrideIcon() {
        return "bag_base";
    }
}
