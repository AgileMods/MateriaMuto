package com.agilemods.materiamuto.common.emc.recipe;

import com.agilemods.materiamuto.api.CachedRecipe;
import com.agilemods.materiamuto.api.IEMCRegistry;
import com.agilemods.materiamuto.api.IRecipeScanner;
import com.agilemods.materiamuto.api.wrapper.IStackWrapper;
import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VanillaCraftingScanner implements IRecipeScanner {

    private Map<VanillaStackWrapper, Set<CachedRecipe>> outputMaps = Maps.newHashMap();

    public VanillaCraftingScanner() {
        scan();
    }

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

    private void scan() {
        for (IRecipe recipe : (List<IRecipe>)CraftingManager.getInstance().getRecipeList()) {
            VanillaStackWrapper stackWrapper = new VanillaStackWrapper(recipe.getRecipeOutput());
            if (recipe instanceof ShapedRecipes) {
                addRecipe(stackWrapper, new CachedRecipe(((ShapedRecipes) recipe).recipeItems));
            } else if (recipe instanceof ShapelessRecipes) {
                addRecipe(stackWrapper, new CachedRecipe(((ShapelessRecipes) recipe).recipeItems));
            } else if (recipe instanceof ShapedOreRecipe) {
                addRecipe(stackWrapper, new CachedRecipe(((ShapedOreRecipe) recipe).getInput()));
            } else if (recipe instanceof ShapelessOreRecipe) {
                addRecipe(stackWrapper, new CachedRecipe(((ShapelessOreRecipe) recipe).getInput()));
            }
        }
    }

    @Override
    public double getEMC(IEMCRegistry emcRegistry, VanillaStackWrapper vanillaStackWrapper) {
        int count = 0;
        double emc = 0;

        Set<CachedRecipe> recipeSet = outputMaps.get(vanillaStackWrapper);
        if (recipeSet != null) {
            for (CachedRecipe cachedRecipe : recipeSet) {
                count++;
                emc += cachedRecipe.getEMC() / vanillaStackWrapper.stackSize;
            }
        } else {
            return 0;
        }

        return emc / (double)count;
    }
}
