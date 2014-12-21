package com.agilemods.materiamuto.client.gui;

import com.agilemods.materiamuto.common.inventory.InventoryAlchemicalBag;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GuiAlchemicalBag extends GuiChest {

    public GuiAlchemicalBag(EntityPlayer entityPlayer, ItemStack itemStack) {
        super(entityPlayer.inventory, new InventoryAlchemicalBag(itemStack.getItemDamage()));
    }
}
