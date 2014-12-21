package com.agilemods.materiamuto.common.emc.handler;

import com.agilemods.materiamuto.api.emc.EMCRegistryState;
import com.agilemods.materiamuto.api.emc.IEMCMiscHandler;
import com.agilemods.materiamuto.api.emc.IEMCRegistry;
import com.agilemods.materiamuto.api.emc.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.util.Map;

public class FurnaceHandler implements IEMCMiscHandler {

    private int runs;

    public FurnaceHandler(int runs) {
        this.runs = runs;
    }

    @Override
    public EMCRegistryState getInsertionState() {
        return EMCRegistryState.RECIPE;
    }

    @Override
    public void handle(IEMCRegistry emcRegistry) {
        Map<ItemStack, ItemStack> recipeMap = (Map<ItemStack, ItemStack>) FurnaceRecipes.smelting().getSmeltingList();
        for (int i = 0; i < runs; i++) {
            for (Map.Entry<ItemStack, ItemStack> entry : recipeMap.entrySet()) {
                StackReference key = new StackReference(entry.getKey());
                StackReference value = new StackReference(entry.getValue());

                if (!key.valid() || !value.valid()) {
                    continue;
                }

                double keyEMC = emcRegistry.getEMC(key);
                double valueEMC = emcRegistry.getEMC(value);

                if (keyEMC > 0 && valueEMC == 0) {
                    emcRegistry.setEMC(value, keyEMC / value.toItemStack().stackSize);
                } else if (keyEMC == 0 && valueEMC > 0) {
                    emcRegistry.setEMC(key, valueEMC * key.toItemStack().stackSize);
                }
            }
        }
    }
}
