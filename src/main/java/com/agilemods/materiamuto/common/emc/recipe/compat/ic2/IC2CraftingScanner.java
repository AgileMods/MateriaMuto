package com.agilemods.materiamuto.common.emc.recipe.compat.ic2;

import com.agilemods.materiamuto.api.CachedRecipe;
import com.agilemods.materiamuto.api.IEMCRegistry;
import com.agilemods.materiamuto.api.IRecipeScanner;
import com.agilemods.materiamuto.api.wrapper.IStackWrapper;
import com.agilemods.materiamuto.api.wrapper.OreStackWrapper;
import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.core.AdvRecipe;
import ic2.core.AdvShapelessRecipe;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IC2CraftingScanner implements IRecipeScanner {

    private Map<VanillaStackWrapper, Set<CachedRecipe>> outputMaps = Maps.newHashMap();

    private void addRecipe(VanillaStackWrapper stackWrapper, CachedRecipe recipe) {
        Set<CachedRecipe> set = outputMaps.get(stackWrapper);
        if (set == null) {
            set = Sets.newHashSet();
        }

        // Remove any null/bad entries
        Iterator<Map.Entry<IStackWrapper, Integer>> iterator = recipe.components.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<IStackWrapper, Integer> entry = iterator.next();
            if (entry == null || entry.getKey() == null || entry.getValue() == null || !entry.getKey().valid()) {
                iterator.remove();
            }
        }

        set.add(recipe);
        outputMaps.put(stackWrapper, set);
    }

    @Override
    public void scan() {
        for (IRecipe recipe : (List<IRecipe>) CraftingManager.getInstance().getRecipeList()) {
            VanillaStackWrapper stackWrapper = new VanillaStackWrapper(recipe.getRecipeOutput());
            if (recipe instanceof AdvRecipe) {
                addRecipe(stackWrapper, getCachedRecipe(recipe));
            } else if (recipe instanceof AdvShapelessRecipe) {
                addRecipe(stackWrapper, getCachedRecipe(recipe));
            }
        }
    }

    private CachedRecipe getCachedRecipe(IRecipe recipe) {
        CachedRecipe cachedRecipe = new CachedRecipe().setResult(new VanillaStackWrapper(recipe.getRecipeOutput()));
        Object[] input = getInput(recipe);

        for (Object object : input) {
            if (object instanceof List<?>) {
                List<?> list = (List<?>) object;

                for (Object entry : list) {
                    cachedRecipe.addStackWrapper(ic2InputToWrapper(entry));
                }
            } else {
                cachedRecipe.addStackWrapper(ic2InputToWrapper(object));
            }
        }

        return cachedRecipe;
    }

    private Object[] getInput(IRecipe recipe) {
        if (recipe instanceof AdvRecipe) {
            return ((AdvRecipe) recipe).input;
        } else if (recipe instanceof AdvShapelessRecipe) {
            return ((AdvShapelessRecipe) recipe).input;
        } else {
            return new Object[0];
        }
    }

    private IStackWrapper ic2InputToWrapper(Object object) {
        if (object instanceof RecipeInputItemStack) {
            return new VanillaStackWrapper(((RecipeInputItemStack) object).input);
        } else if (object instanceof RecipeInputOreDict) {
            return new OreStackWrapper(((RecipeInputOreDict) object).input);
        } else {
            return null;
        }
    }

    @Override
    public double getEMC(IEMCRegistry emcRegistry, VanillaStackWrapper vanillaStackWrapper) {
        double emc = Double.MAX_VALUE;

        Set<CachedRecipe> recipeSet = outputMaps.get(vanillaStackWrapper);
        if (recipeSet != null) {
            for (CachedRecipe cachedRecipe : recipeSet) {
                double subEmc = cachedRecipe.getEMC() / cachedRecipe.result.stackSize;
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
