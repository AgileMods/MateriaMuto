package com.agilemods.materiamuto.api.emc;

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class StackReference {

    private String item;

    private int damage;

    private NBTTagCompound nbtTagCompound;

    public StackReference(Block block) {
        this(Item.getItemFromBlock(block));
    }

    public StackReference(Item item) {
        this(new ItemStack(item));
    }

    public StackReference(ItemStack itemStack) {
        if (itemStack == null || itemStack.getItem() == null) {
            this.item = "";
            this.damage = 0;
        } else {
            this.item = GameData.getItemRegistry().getNameForObject(itemStack.getItem());
            this.damage = itemStack.getItemDamage();
        }
    }

    public StackReference(String item, int damage) {
        this.item = item;
        this.damage = damage;
    }

    public StackReference setNBT(NBTTagCompound nbtTagCompound) {
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

    public boolean valid() {
        return item != null && !item.isEmpty();
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
        if (!(obj instanceof StackReference)) {
            return false;
        }

        if (!valid()) {
            return false;
        }

        StackReference stackReference = (StackReference) obj;

        if (!stackReference.valid()) {
            return false;
        }

        if (item.equals(stackReference.item)) {
            if (damage == OreDictionary.WILDCARD_VALUE || stackReference.damage == OreDictionary.WILDCARD_VALUE) {
                if (nbtTagCompound != null && stackReference.nbtTagCompound != null) {
                    return ItemStack.areItemStackTagsEqual(toItemStack(), stackReference.toItemStack());
                } else {
                    return true;
                }
            } else {
                if (nbtTagCompound != null && stackReference.nbtTagCompound != null) {
                    return damage == stackReference.damage && ItemStack.areItemStackTagsEqual(toItemStack(), stackReference.toItemStack());
                } else {
                    return damage == stackReference.damage;
                }
            }
        } else {
            return false;
        }
    }
}
