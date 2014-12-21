package com.agilemods.materiamuto.common.item.rings;

import com.agilemods.materiamuto.api.IModeChanger;
import com.agilemods.materiamuto.common.item.prefab.MMItem;
import com.agilemods.materiamuto.common.lib.LibMisc;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemHarvestGodessRing extends MMItem implements IModeChanger {

    private IIcon iconInactive;
    private IIcon iconActive;

    public ItemHarvestGodessRing() {
        super();
        setMaxStackSize(1);
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return damage == 1 ? iconActive : iconInactive;
    }

    @Override
    public void registerIcons(IIconRegister register) {
        iconInactive = register.registerIcon(LibMisc.RESOURCE_PREFIX + "rings/harvest_god_off");
        iconActive = register.registerIcon(LibMisc.RESOURCE_PREFIX + "rings/harvest_god_on");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (!world.isRemote) {
            changeMode(entityPlayer, itemStack);
        }
        return itemStack;
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
