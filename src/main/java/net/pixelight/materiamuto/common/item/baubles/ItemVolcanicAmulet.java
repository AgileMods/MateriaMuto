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
package net.pixelight.materiamuto.common.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.common.Optional;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.pixelight.materiamuto.common.item.prefab.MMItem;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "Baubles")
public class ItemVolcanicAmulet extends MMItem implements IBauble {

    public ItemVolcanicAmulet() {
        super();
        setMaxStackSize(1);
    }

    @Override
    public String getIcon() {
        return "rings/volcanic_amulet";
    }

    @Optional.Method(modid = "Baubles")
    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    @Optional.Method(modid = "Baubles")
    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {

    }

    @Optional.Method(modid = "Baubles")
    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {

    }

    @Optional.Method(modid = "Baubles")
    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {

    }

    @Optional.Method(modid = "Baubles")
    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return false;
    }

    @Optional.Method(modid = "Baubles")
    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return false;
    }
}
