package com.agilemods.materiamuto.common.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.agilemods.materiamuto.common.item.prefab.MMItem;
import cpw.mods.fml.common.Optional;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "Baubles")
public class ItemEvertideAmulet extends MMItem implements IBauble {

    public ItemEvertideAmulet() {
        super();
        setMaxStackSize(1);
    }

    @Override
    public String getIcon() {
        return "rings/evertide_amulet";
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
