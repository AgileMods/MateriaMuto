package com.agilemods.materiamuto.common.emc.recipe.compat.ic2;

import com.agilemods.materiamuto.api.wrapper.IStackWrapper;
import com.agilemods.materiamuto.api.wrapper.OreStackWrapper;
import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;

public class IC2ClassHelper {

    public static IStackWrapper convert(IRecipeInput input) {
        if (input instanceof RecipeInputItemStack) {
            return new VanillaStackWrapper(((RecipeInputItemStack) input).input);
        } else if (input instanceof RecipeInputOreDict) {
            return new OreStackWrapper(((RecipeInputOreDict) input).input);
        } else {
            return null;
        }
    }
}
