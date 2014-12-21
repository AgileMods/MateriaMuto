package com.agilemods.materiamuto.api.emc;

import net.minecraft.item.ItemStack;

public interface IEMCItemHandler extends IEMCHandler {

    public void handleItem(IEMCRegistry emcRegistry, ItemStack itemStack);
}
