package com.agilemods.materiamuto.common.emc.recipe.compat.thaumcraft;

import com.agilemods.materiamuto.api.IEMCRegistry;
import com.agilemods.materiamuto.api.IRecipeScanner;
import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.CrucibleRecipe;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThaumcraftCrucibleScanner implements IRecipeScanner {

    private Map<VanillaStackWrapper, Set<VanillaStackWrapper>> components = Maps.newHashMap();

    private void addCatalyst(VanillaStackWrapper result, ItemStack ... catalyst) {
        Set<VanillaStackWrapper> set = components.get(result);
        if (set == null) {
            set = Sets.newHashSet();
        }

        for (ItemStack c : catalyst) {
            set.add(new VanillaStackWrapper(c));
        }

        components.put(result, set);
    }

    @Override
    public void scan() {
        for (Object r : ThaumcraftApi.getCraftingRecipes()) {
            if (r instanceof CrucibleRecipe) {
                CrucibleRecipe crucibleRecipe = (CrucibleRecipe) r;
                ItemStack result = crucibleRecipe.getRecipeOutput();
                Object input = crucibleRecipe.catalyst;

                if (input instanceof ItemStack) {
                    addCatalyst(new VanillaStackWrapper(result), (ItemStack)input);
                } else if (input instanceof List<?>) {
                    addCatalyst(new VanillaStackWrapper(result), (ItemStack[]) ((List) input).toArray(new ItemStack[((List) input).size()]));
                }
            }
        }
    }

    @Override
    public double getEMC(IEMCRegistry emcRegistry, VanillaStackWrapper vanillaStackWrapper) {
        double emc = Double.MAX_VALUE;

        Set<VanillaStackWrapper> set = components.get(vanillaStackWrapper);

        if (set != null) {
            for (VanillaStackWrapper stackWrapper : components.get(vanillaStackWrapper)) {
                double subEmc = stackWrapper.getEMC();
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
