package com.agilemods.materiamuto.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import com.agilemods.materiamuto.common.core.handlers.AlchemicalBagHandler;

public class ContainerAlchemicalBag extends ContainerChest {

    private ItemStack itemStack;

    public ContainerAlchemicalBag(EntityPlayer entityPlayer, ItemStack itemStack) {
        super(entityPlayer.inventory, AlchemicalBagHandler.INSTANCE.getInventory(itemStack));

        this.itemStack = itemStack;
    }

    @Override
    public void onContainerClosed(EntityPlayer entityPlayer) {
        super.onContainerClosed(entityPlayer);

        if (entityPlayer.worldObj.isRemote) {
            InventoryAlchemicalBag inventoryAlchemicalBag = (InventoryAlchemicalBag) getLowerChestInventory();
            AlchemicalBagHandler.INSTANCE.setInventory(itemStack, inventoryAlchemicalBag);
            ((EntityPlayerMP) entityPlayer).updateHeldItem();
        }
    }
}
