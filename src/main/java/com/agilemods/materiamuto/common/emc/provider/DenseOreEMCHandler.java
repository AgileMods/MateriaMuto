package com.agilemods.materiamuto.common.emc.provider;

import com.agilemods.materiamuto.api.IEMCHandler;
import com.agilemods.materiamuto.api.IEMCRegistry;
import net.minecraft.item.ItemStack;

public class DenseOreEMCHandler implements IEMCHandler {

    @Override
    public void handleItem(IEMCRegistry emcRegistry, ItemStack itemStack, boolean finishedRecipeCalc) {
        if (itemStack.getUnlocalizedName().contains("denseore")) {
            emcRegistry.blacklist(itemStack);
        }
    }
}
