package com.agilemods.materiamuto.common.emc.handler;

import com.agilemods.materiamuto.api.emc.EMCRegistryState;
import com.agilemods.materiamuto.api.emc.IEMCItemHandler;
import com.agilemods.materiamuto.api.emc.IEMCRegistry;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;

public class DenseOreHandler implements IEMCItemHandler {

    @Override
    public EMCRegistryState getInsertionState() {
        return EMCRegistryState.PRE;
    }

    @Override
    public void handleItem(IEMCRegistry emcRegistry, ItemStack itemStack) {
        if (Loader.isModLoaded("denseores") && itemStack.getUnlocalizedName().contains("denseore")) {
            emcRegistry.blacklist(itemStack);
        }
    }
}
