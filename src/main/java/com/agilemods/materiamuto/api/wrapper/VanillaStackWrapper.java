package com.agilemods.materiamuto.api.wrapper;

import com.agilemods.materiamuto.common.emc.EMCRegistry;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class VanillaStackWrapper implements IStackWrapper {

    private String item;

    private int damage;
    public  int stackSize;

    private NBTTagCompound nbtTagCompound;

    public VanillaStackWrapper(Block block) {
        this(Item.getItemFromBlock(block));
    }

    public VanillaStackWrapper(Item item) {
        this(new ItemStack(item));
    }

    public VanillaStackWrapper(ItemStack itemStack) {
        if (itemStack == null || itemStack.getItem() == null) {
            this.item = "";
            this.damage = 0;
            this.stackSize = 1;
        } else {
            this.item = GameData.getItemRegistry().getNameForObject(itemStack.getItem());
            this.damage = itemStack.getItemDamage();
            this.stackSize = itemStack.stackSize;
        }
    }

    public VanillaStackWrapper(String item, int damage) {
        this.item = item;
        this.damage = damage;
    }

    public VanillaStackWrapper setNBT(NBTTagCompound nbtTagCompound) {
        this.nbtTagCompound = nbtTagCompound;
        return this;
    }

    public ItemStack toItemStack() {
        ItemStack itemStack = new ItemStack(GameData.getItemRegistry().getObject(item), 1, damage);
        if (nbtTagCompound != null) {
            itemStack.setTagCompound(nbtTagCompound);
        }
        return itemStack;
    }

    @Override
    public boolean valid() {
        return item != null && !item.isEmpty();
    }

    @Override
    public double getEMC() {
        return EMCRegistry.getEMC(toItemStack());
    }

    @Override
    public String toString() {
        return "{item: " + item + " damage: " + damage + (nbtTagCompound != null ? " nbt: " + nbtTagCompound + "}" : "}");
    }

    @Override
    public int hashCode() {
        if (valid()) {
            int result = item.hashCode();
            result = 31 * result + (nbtTagCompound != null ? nbtTagCompound.hashCode() : 0);
            return result;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VanillaStackWrapper)) {
            return false;
        }

        if (!valid()) {
            return false;
        }

        VanillaStackWrapper vanillaStackWrapper = (VanillaStackWrapper) obj;

        if (!vanillaStackWrapper.valid()) {
            return false;
        }

        if (item.equals(vanillaStackWrapper.item)) {
            if (damage == OreDictionary.WILDCARD_VALUE || vanillaStackWrapper.damage == OreDictionary.WILDCARD_VALUE) {
                if (nbtTagCompound != null && vanillaStackWrapper.nbtTagCompound != null) {
                    return ItemStack.areItemStackTagsEqual(toItemStack(), vanillaStackWrapper.toItemStack());
                } else {
                    return true;
                }
            } else {
                if (nbtTagCompound != null && vanillaStackWrapper.nbtTagCompound != null) {
                    return damage == vanillaStackWrapper.damage && ItemStack.areItemStackTagsEqual(toItemStack(), vanillaStackWrapper.toItemStack());
                } else {
                    return damage == vanillaStackWrapper.damage;
                }
            }
        } else {
            return false;
        }
    }
}
