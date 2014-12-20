package com.agilemods.materiamuto.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * This class should be implemented for any item that can have a 'charge'
 */
public interface IItemCharge {

    /**
     * @param itemStack The current ItemStack
     * @return The current charge level of the ItemStack
     */
    public byte getCharge(ItemStack itemStack);

    /**
     * Called when the item needs to change the charge level.
     *
     * @param entityPlayer The player who sends the request
     * @param itemStack    The ItemStack to be charged
     */
    public void changeCharge(EntityPlayer entityPlayer, ItemStack itemStack);
}
