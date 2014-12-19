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
package net.pixelight.materiamuto.common.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.pixelight.materiamuto.common.item.prefab.MMSubItem;

public class ItemKleinStar extends MMSubItem {

    public static final String[] STARS = new String[]{"ein", "zwei", "drei", "vier", "sphere", "omega"};
    public static final int[] MAX_EMC = new int[]{50000, 200000, 800000, 3200000, 12800000, 51200000};

    public ItemKleinStar() {
        super();
        setMaxStackSize(1);
    }

    @Override
    public boolean showDurabilityBar(ItemStack itemStack) {
        return itemStack.getItemDamage() != 6;
    }

    @Override
    public String[] getNames() {
        return STARS;
    }

    @Override
    public String getIconPrefix() {
        return "stars";
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return damage == 6 ? super.getIconFromDamage(5) : super.getIconFromDamage(damage);
    }
}
