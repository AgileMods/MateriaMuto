package com.agilemods.materiamuto.common.item.armor.darkmatter;

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
