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
package net.pixelight.materiamuto.common.core.helpers;

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
