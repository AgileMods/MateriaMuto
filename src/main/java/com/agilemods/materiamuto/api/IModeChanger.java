package com.agilemods.materiamuto.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * This class should be implemented on any items that can change modes.
 */
public interface IModeChanger {

    /**
     * Get the current mode of the ItemStack
     *
     * @param itemStack The ItemStack to check mode
     * @return The current mode
     */
    public byte getMode(ItemStack itemStack);

    /**
     * Called when the item needs to change modes.
     *
     * @param entityPlayer The player sending the request
     * @param itemStack    The ItemStack to be changed.
     */
    public void changeMode(EntityPlayer entityPlayer, ItemStack itemStack);
}
