package com.agilemods.materiamuto.common.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.common.Optional;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import com.agilemods.materiamuto.common.item.prefab.MMItem;

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
        return true;
    }

    @Optional.Method(modid = "Baubles")
    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }
}
