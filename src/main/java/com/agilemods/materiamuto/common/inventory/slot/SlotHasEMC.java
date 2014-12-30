package com.agilemods.materiamuto.common.inventory.slot;

import com.agilemods.materiamuto.common.emc.EMCRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotHasEMC extends Slot {

    public SlotHasEMC(IInventory inventory, int id, int x, int y) {
        super(inventory, id, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return EMCRegistry.getEMC(itemStack) > 0;
    }
}
