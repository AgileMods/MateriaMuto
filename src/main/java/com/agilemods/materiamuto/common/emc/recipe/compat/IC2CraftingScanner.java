package com.agilemods.materiamuto.common.emc.recipe.compat;

import com.agilemods.materiamuto.api.CachedRecipe;
import com.agilemods.materiamuto.api.IEMCRegistry;
import com.agilemods.materiamuto.api.IRecipeScanner;
import com.agilemods.materiamuto.api.wrapper.IStackWrapper;
import com.agilemods.materiamuto.api.wrapper.OreStackWrapper;
import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.lang.reflect.Field;
import java.util.*;

public class IC2CraftingScanner implements IRecipeScanner {

    private Map<VanillaStackWrapper, Set<CachedRecipe>> outputMaps = Maps.newHashMap();

    public IC2CraftingScanner() {
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
        for (IRecipe recipe : (List<IRecipe>) CraftingManager.getInstance().getRecipeList()) {
            VanillaStackWrapper stackWrapper = new VanillaStackWrapper(recipe.getRecipeOutput());
            if (recipe.getClass().getSimpleName().equals("AdvRecipe") || recipe.getClass().getSimpleName().equals("AdvShapelessRecipe")) {
                addRecipe(stackWrapper, getCachedRecipe(recipe));
            }
        }
    }

    private CachedRecipe getCachedRecipe(IRecipe recipe) {
        CachedRecipe cachedRecipe = new CachedRecipe().setResult(new VanillaStackWrapper(recipe.getRecipeOutput()));
        Object[] input = (Object[]) getInput(recipe);

        for (Object object : input) {
            if (object instanceof List<?>) {
                List<?> list = (List<?>) object;

                for (Object entry : list) {
                    if (entry.getClass().getSimpleName().equals("RecipeInputItemStack")) {
                        cachedRecipe.addStackWrapper(new VanillaStackWrapper((ItemStack) getInput(entry)));
                    } else if (entry.getClass().getSimpleName().equals("RecipeInputOreDict")) {
                        cachedRecipe.addStackWrapper(new OreStackWrapper((String) getInput(entry)));
                    }
                }
            } else {
                if (object.getClass().getSimpleName().equals("RecipeInputItemStack")) {
                    cachedRecipe.addStackWrapper(new VanillaStackWrapper((ItemStack) getInput(object)));
                } else if (object.getClass().getSimpleName().equals("RecipeInputOreDict")) {
                    cachedRecipe.addStackWrapper(new OreStackWrapper((String) getInput(object)));
                }
            }
        }

        return cachedRecipe;
    }

    private Object getInput(Object object) {
        Class<?> clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField("input");
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception ex) {
            return new Object[0];
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
                emc += cachedRecipe.getEMC() / cachedRecipe.result.stackSize;
            }
        } else {
            return 0;
        }

        return emc / (double)count;
    }
}
