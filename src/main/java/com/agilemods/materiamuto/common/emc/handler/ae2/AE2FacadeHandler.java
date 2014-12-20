package com.agilemods.materiamuto.common.emc.handler.ae2;

import com.agilemods.materiamuto.api.emc.EMCRegistryState;
import com.agilemods.materiamuto.api.emc.IEMCMiscHandler;
import com.agilemods.materiamuto.api.emc.IEMCRegistry;
import com.agilemods.materiamuto.api.emc.StackReference;
import com.google.common.collect.Lists;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class AE2FacadeHandler implements IEMCMiscHandler {

    private static Class<?> ITEM_FACADE;

    static {
        try {
            ITEM_FACADE = Class.forName("appeng.items.parts.ItemFacade");
        } catch (Exception ignore) {}
    }

    @Override
    public EMCRegistryState getInsertionState() {
        return EMCRegistryState.POST;
    }

    @Override
    public void handle(IEMCRegistry emcRegistry) {
        for (Item item : (Iterable<Item>) GameData.getItemRegistry()) {
            if (ITEM_FACADE.isAssignableFrom(item.getClass())) {
                List<ItemStack> facades = Lists.newArrayList();
                try {
                    facades = (List<ItemStack>) ITEM_FACADE.getDeclaredMethod("getFacades").invoke(item);
                } catch (Exception ignore) {}

                for (ItemStack itemStack : facades) {
                    Block block = null;
                    int meta = 0;
                    try {
                        block = (Block) ITEM_FACADE.getDeclaredMethod("getBlock", ItemStack.class).invoke(itemStack.getItem(), itemStack);
                        meta = (Integer) ITEM_FACADE.getDeclaredMethod("getMeta", ItemStack.class).invoke(itemStack.getItem(), itemStack);
                    } catch (Exception ignore) {}

                    if (block != null) {
                        emcRegistry.setEMC(new StackReference(itemStack).setNBT(itemStack.getTagCompound()), emcRegistry.getEMC(new ItemStack(block, 1, meta)));
                    }
                }

                return;
            }
        }
    }
}
