package com.agilemods.materiamuto.common.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.agilemods.materiamuto.common.item.prefab.MMItem;
import cpw.mods.fml.common.Optional;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import com.agilemods.materiamuto.api.IModeChanger;
import com.agilemods.materiamuto.common.lib.LibMisc;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "Baubles")
public class ItemSwiftWolfsRendingGale extends MMItem implements IBauble, IModeChanger {

    private IIcon iconInactive;
    private IIcon[] iconActiveStage;

    public ItemSwiftWolfsRendingGale() {
        super();
        setMaxStackSize(1);
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (damage == 0) {
            return iconInactive;
        } else {
            return iconActiveStage[net.minecraft.util.MathHelper.clamp_int(damage - 1, 0, 2)];
        }
    }

    @Override
    public void registerIcons(IIconRegister register) {
        iconInactive = register.registerIcon(LibMisc.RESOURCE_PREFIX + "rings/swrg_off");
        iconActiveStage = new IIcon[3];

        for (int i = 0; i < 3; i++) {
            iconActiveStage[i] = register.registerIcon(LibMisc.RESOURCE_PREFIX + "rings/swrg_on" + (i + 1));
        }
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
        } else if (itemStack.getItemDamage() == 1) {
            itemStack.setItemDamage(2);
        } else if (itemStack.getItemDamage() == 2) {
            itemStack.setItemDamage(3);
        } else {
            itemStack.setItemDamage(0);
        }
    }
}
