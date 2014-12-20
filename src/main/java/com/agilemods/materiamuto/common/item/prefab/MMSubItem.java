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
package com.agilemods.materiamuto.common.item.prefab;

import com.agilemods.materiamuto.common.lib.LibMisc;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public abstract class MMSubItem extends MMItem {

    private IIcon iconOverride;
    protected IIcon[] icons;

    public MMSubItem() {
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int meta = itemStack.getItemDamage();

        if (meta < 0 || meta >= getNames().length) {
            meta = 0;
        }
        return super.getUnlocalizedName() + "." + getNames()[meta];
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (iconOverride != null) {
            return iconOverride;
        }

        if (damage < 0 || damage >= getNames().length) {
            damage = 0;
        }

        return icons[damage];
    }

    @Override
    public void registerIcons(IIconRegister register) {
        icons = new IIcon[getNames().length];
        if (getIconPrefix() != null && !getIconPrefix().isEmpty()) {
            for (int i = 0; i < getNames().length; i++) {
                icons[i] = register.registerIcon(LibMisc.RESOURCE_PREFIX + getIconPrefix() + "/" + getNames()[i]);
            }
        } else if (getOverrideIcon() != null && !getOverrideIcon().isEmpty()) {
            iconOverride = register.registerIcon(LibMisc.RESOURCE_PREFIX + getOverrideIcon());
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for (int meta = 0; meta < getNames().length; ++meta) {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    public String getOverrideIcon() {
        return null;
    }

    public abstract String[] getNames();

    public abstract String getIconPrefix();

}
