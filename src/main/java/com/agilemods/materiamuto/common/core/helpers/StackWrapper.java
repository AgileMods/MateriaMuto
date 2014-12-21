package com.agilemods.materiamuto.common.core.helpers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class StackWrapper {

    public int id;
    public int damage;

    private boolean fullEquality = false;

    public StackWrapper(int id, int damage) {
        this.id = id;
        this.damage = damage;
    }

    public StackWrapper(ItemStack itemStack) {
        if (itemStack == null) {
            id = -1;
        } else {
            id = Item.itemRegistry.getIDForObject(itemStack.getItem());
            damage = itemStack.getItemDamage();
        }
    }

    public StackWrapper fullEquality() {
        this.fullEquality = true;
        return this;
    }

    private StackWrapper setFullEquality(boolean fullEquality) {
        this.fullEquality = fullEquality;
        return this;
    }

    public boolean isValid() {
        return id != -1;
    }

    public ItemStack toItemStack() {
        return toItemStack(1);
    }

    public ItemStack toItemStack(int quantity) {
        if (isValid()) {
            Item item = Item.getItemById(id);
            if (item != null) {
                return new ItemStack(Item.getItemById(id), quantity, damage);
            }
        }
        return null;
    }

    public StackWrapper copy() {
        return new StackWrapper(id, damage).setFullEquality(fullEquality);
    }

    @Override
    public int hashCode() {
        if (fullEquality) {
            int code = 1;
            code = 31 * code + id;
            code = 31 * code + damage;
            return code;
        } else {
            return id;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof StackWrapper) {
            StackWrapper other = (StackWrapper) object;
            if (this.damage == OreDictionary.WILDCARD_VALUE || other.damage == OreDictionary.WILDCARD_VALUE) {
                return this.id == other.id;
            }
            return this.id == other.id && this.damage == other.damage;
        }
        return false;
    }

    @Override
    public String toString() {
        Object object = Item.itemRegistry.getObjectById(id);

        if (object != null) {
            return Item.itemRegistry.getNameForObject(object) + " " + damage;
        }
        return "id:" + id + " damage:" + damage;
    }
}
