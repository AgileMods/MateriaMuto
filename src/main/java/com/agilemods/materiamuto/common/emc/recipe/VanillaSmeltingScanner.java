package com.agilemods.materiamuto.common.emc.recipe;

import com.agilemods.materiamuto.api.IEMCRegistry;
import com.agilemods.materiamuto.api.IRecipeScanner;
import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VanillaSmeltingScanner implements IRecipeScanner {

    private Map<VanillaStackWrapper, Set<VanillaStackWrapper>> smeltingMap = Maps.newHashMap();

    private void addRecipe(VanillaStackWrapper input, VanillaStackWrapper output) {
        Set<VanillaStackWrapper> set = smeltingMap.get(output);
        if (set == null) {
            set = new HashSet<VanillaStackWrapper>();
        }

        set.add(input);

        smeltingMap.put(output, set);
    }

    @Override
    public void scan() {
        for (Map.Entry<ItemStack, ItemStack> entry : (Set<Map.Entry>)FurnaceRecipes.smelting().getSmeltingList().entrySet()) {
            addRecipe(new VanillaStackWrapper(entry.getKey()), new VanillaStackWrapper(entry.getValue()));
        }
    }

    public double getEMC(IEMCRegistry emcRegistry, VanillaStackWrapper vanillaStackWrapper) {
        double emc = Double.MAX_VALUE;

        Set<VanillaStackWrapper> set = smeltingMap.get(vanillaStackWrapper);
        if (set != null) {
            for (VanillaStackWrapper input : set) {
                double subEmc = input.getEMC();
                if (subEmc > 0 && subEmc < emc) {
                    emc = subEmc;
                }
            }
        } else {
            return 0;
        }

        return emc;
    }
}
