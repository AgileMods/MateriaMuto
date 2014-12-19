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
package net.pixelight.materiamuto.common.item.armor.darkmatter;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;

public class ItemArmorDarkMatter extends ItemArmor implements ISpecialArmor {

    public ItemArmorDarkMatter(ArmorMaterial armorMaterial, int renderIndex, int armorType) {
        super(armorMaterial, renderIndex, armorType);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack itemStack, DamageSource damageSource, double damage, int slot) {
        return null;
    }

    @Override
    public int getArmorDisplay(EntityPlayer entityPlayer, ItemStack itemStack, int slot) {
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entityLivingBase, ItemStack itemStack, DamageSource damageSource, int damage, int slot) {

    }

    @Override
    public boolean getIsRepairable(ItemStack input, ItemStack repair) {
        return true;
    }
}
