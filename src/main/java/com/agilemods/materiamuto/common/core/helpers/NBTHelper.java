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
