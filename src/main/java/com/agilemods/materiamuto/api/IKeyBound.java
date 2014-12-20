package com.agilemods.materiamuto.api;

import com.agilemods.materiamuto.common.lib.LibKey;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * This class should be implemented on and item that has a key bind action.
 */
public interface IKeyBound {

    /**
     * Perform an action when a keybind is pressed
     *
     * @param entityPlayer The player who pressed the key
     * @param itemStack    The ItemStack on which to perform the action
     * @param key          The key pressed
     */
    public abstract void doKeyAction(EntityPlayer entityPlayer, ItemStack itemStack, LibKey key);

}
