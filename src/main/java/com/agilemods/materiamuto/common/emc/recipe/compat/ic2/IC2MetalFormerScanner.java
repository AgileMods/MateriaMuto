package com.agilemods.materiamuto.common.emc.recipe.compat.ic2;

import com.agilemods.materiamuto.api.IEMCRegistry;
import com.agilemods.materiamuto.api.IRecipeScanner;
import com.agilemods.materiamuto.api.wrapper.IStackWrapper;
import com.agilemods.materiamuto.api.wrapper.OreStackWrapper;
import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.google.common.collect.Maps;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IC2MetalFormerScanner implements IRecipeScanner {

    private Map<VanillaStackWrapper, Set<VanillaStackWrapper>> resultMap = Maps.newHashMap();

    private void addRecipe(VanillaStackWrapper input, VanillaStackWrapper output) {
        Set<VanillaStackWrapper> set = resultMap.get(output);
        if (set == null) {
            set = new HashSet<VanillaStackWrapper>();
        }

        set.add(output);

        resultMap.put(input, set);
    }

    @Override
    public void scan() {
        Map<IRecipeInput, RecipeOutput> recipeMap = Maps.newHashMap();
        recipeMap.putAll(Recipes.metalformerCutting.getRecipes());
        recipeMap.putAll(Recipes.metalformerExtruding.getRecipes());
        recipeMap.putAll(Recipes.metalformerRolling.getRecipes());

        for (Map.Entry<IRecipeInput, RecipeOutput> entry : recipeMap.entrySet()) {
            IStackWrapper input = IC2ClassHelper.convert(entry.getKey());
            for (ItemStack itemStack : entry.getValue().items) {
                if (input instanceof VanillaStackWrapper) {
                    addRecipe(new VanillaStackWrapper(itemStack), (VanillaStackWrapper) input);
                } else if (input instanceof OreStackWrapper) {
                    for (ItemStack oreStack : OreDictionary.getOres(((OreStackWrapper) input).getOreTag())) {
                        addRecipe(new VanillaStackWrapper(itemStack), new VanillaStackWrapper(oreStack));
                    }
                }
            }
        }
    }

    public double getEMC(IEMCRegistry emcRegistry, VanillaStackWrapper vanillaStackWrapper) {
        double emc = Double.MAX_VALUE;

        Set<VanillaStackWrapper> set = resultMap.get(vanillaStackWrapper);
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
