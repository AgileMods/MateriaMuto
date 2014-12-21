package com.agilemods.materiamuto.common.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.agilemods.materiamuto.api.IModeChanger;
import com.agilemods.materiamuto.common.item.prefab.MMItem;
import com.agilemods.materiamuto.common.lib.LibMisc;
import cpw.mods.fml.common.Optional;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "Baubles")
public class ItemZeroRing extends MMItem implements IBauble, IModeChanger {

    private IIcon iconInactive;
    private IIcon iconActive;

    public ItemZeroRing() {
        super();
        setMaxStackSize(1);
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return damage == 1 ? iconActive : iconInactive;
    }

    @Override
    public void registerIcons(IIconRegister register) {
        iconInactive = register.registerIcon(LibMisc.RESOURCE_PREFIX + "rings/zero_off");
        iconActive = register.registerIcon(LibMisc.RESOURCE_PREFIX + "rings/zero_on");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (!world.isRemote) {
            changeMode(entityPlayer, itemStack);
        }
        return itemStack;
    }

    @Optional.Method(modid = "Baubles")
    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
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

    @Override
    public byte getMode(ItemStack itemStack) {
        return (byte) itemStack.getItemDamage();
    }

    @Override
    public void changeMode(EntityPlayer entityPlayer, ItemStack itemStack) {
        if (itemStack.getItemDamage() == 0) {
            itemStack.setItemDamage(1);
        } else {
            itemStack.setItemDamage(0);
        }
    }
}
