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
package com.agilemods.materiamuto.common.core.lib;

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class StackReference {

    private String item;

    private int damage;

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

    public ItemStack toItemStack() {
        return new ItemStack(GameData.getItemRegistry().getObject(item), 1, damage);
    }

    public boolean valid() {
        return item != null && !item.isEmpty();
    }

    @Override
    public int hashCode() {
        return valid() ? item.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StackReference)) {
            return false;
        }

        StackReference stackReference = (StackReference) obj;

        if (item.equals(stackReference.item)) {
            if (damage == OreDictionary.WILDCARD_VALUE || stackReference.damage == OreDictionary.WILDCARD_VALUE) {
                return true;
            } else {
                return damage == stackReference.damage;
            }
        } else {
            return false;
        }
    }
}
