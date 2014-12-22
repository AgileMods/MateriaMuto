package com.agilemods.materiamuto.api;

import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;

import java.util.Set;

public interface IRecipeScanner {

    public void scan();

    public Set<CachedRecipe> getRecipesForItem(VanillaStackWrapper vanillaStackWrapper);
}
