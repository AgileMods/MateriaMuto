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
package com.agilemods.materiamuto.common.core.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTHelper {

    public static NBTTagCompound getTagCompound(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        return stack.stackTagCompound;
    }

    public static void setTag(ItemStack stack, String name, NBTBase tag) {
        getTagCompound(stack).setTag(name, tag);
    }

    public static void setByte(ItemStack stack, String name, byte value) {
        getTagCompound(stack).setByte(name, value);
    }

    public static void setInt(ItemStack stack, String name, int value) {
        getTagCompound(stack).setInteger(name, value);
    }

    public static void setDouble(ItemStack stack, String name, double value) {
        getTagCompound(stack).setDouble(name, value);
    }

    public static byte getByte(ItemStack stack, String name) {
        return getTagCompound(stack).getByte(name);
    }

    public static int getInt(ItemStack stack, String name) {
        return getTagCompound(stack).getInteger(name);
    }

    public static double getDouble(ItemStack stack, String name) {
        return getTagCompound(stack).getDouble(name);
    }

    public static NBTTagList getList(ItemStack stack, String name, int type) {
        return getTagCompound(stack).getTagList(name, type);
    }

}
