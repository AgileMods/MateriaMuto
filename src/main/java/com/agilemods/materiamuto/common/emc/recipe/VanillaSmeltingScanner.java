package com.agilemods.materiamuto.common.emc.recipe;

import com.agilemods.materiamuto.api.IEMCRegistry;
import com.agilemods.materiamuto.api.IRecipeScanner;
import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.util.Map;
import java.util.Set;

public class VanillaSmeltingScanner implements IRecipeScanner {

    private Map<VanillaStackWrapper, VanillaStackWrapper> inToOutMap = Maps.newHashMap();
    private Map<VanillaStackWrapper, VanillaStackWrapper> outToInMap = Maps.newHashMap();

    public VanillaSmeltingScanner() {
        scan();
    }

    private void addRecipe(VanillaStackWrapper input, VanillaStackWrapper output) {
        inToOutMap.put(input, output);
        outToInMap.put(output, input);
    }

    private void scan() {
        for (Map.Entry<ItemStack, ItemStack> entry : (Set<Map.Entry>)FurnaceRecipes.smelting().getSmeltingList().entrySet()) {
            addRecipe(new VanillaStackWrapper(entry.getKey()), new VanillaStackWrapper(entry.getValue()));
        }
    }

    public double getEMC(IEMCRegistry emcRegistry, VanillaStackWrapper vanillaStackWrapper) {
        VanillaStackWrapper result = inToOutMap.get(vanillaStackWrapper);
        VanillaStackWrapper input = outToInMap.get(vanillaStackWrapper);

        if (result != null) {
            return result.getEMC() / result.stackSize;
        } else if (input != null) {
            return input.getEMC() * input.getEMC();
        } else {
            return 0;
        }
    }
}
