package com.agilemods.materiamuto.common.emc.handler;

import com.agilemods.materiamuto.api.emc.EMCRegistryState;
import com.agilemods.materiamuto.api.emc.IEMCMiscHandler;
import com.agilemods.materiamuto.api.emc.IEMCRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.List;

public class VanillaCraftingHandler implements IEMCMiscHandler {

    private int runs;

    public VanillaCraftingHandler(int runs) {
        this.runs = runs;
    }

    @Override
    public EMCRegistryState getInsertionState() {
        return EMCRegistryState.RECIPE;
    }

    @Override
    public void handle(IEMCRegistry emcRegistry) {
        List<IRecipe> recipeList = (List<IRecipe>) CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < runs; i++) {
            for (IRecipe recipe : recipeList) {
                if (recipe instanceof ShapedRecipes) {
                    ItemStack result = recipe.getRecipeOutput();
                    double emc = emcRegistry.getEMC(result);
                    double calculated = 0;
                    for (ItemStack itemStack : ((ShapedRecipes) recipe).recipeItems) {
                        calculated += emcRegistry.getEMC(itemStack);
                    }
                    if (calculated > emc) {
                        emcRegistry.setEMC(result, calculated / result.stackSize);
                    }
                } else if (recipe instanceof ShapelessRecipes) {
                    ItemStack result = recipe.getRecipeOutput();
                    double emc = emcRegistry.getEMC(result);
                    double calculated = 0;
                    for (Object object : ((ShapelessRecipes) recipe).recipeItems) {
                        calculated += emcRegistry.getEMC(object);
                    }
                    if (calculated > emc) {
                        emcRegistry.setEMC(result, calculated / result.stackSize);
                    }
                } else if (recipe instanceof ShapedOreRecipe) {
                    ItemStack result = recipe.getRecipeOutput();
                    double emc = emcRegistry.getEMC(result);
                    double calculated = 0;
                    for (Object object : ((ShapedOreRecipe) recipe).getInput()) {
                        if (object != null) {
                            if (object instanceof List) {
                                double listCalc = 0;
                                for (Object object1 : (List) object) {
                                    listCalc += emcRegistry.getEMC(object1);
                                }
                                calculated += (listCalc / ((List) object).size());
                            } else {
                                calculated += emcRegistry.getEMC(object);
                            }
                        }
                    }
                    if (calculated > emc) {
                        emcRegistry.setEMC(result, calculated / result.stackSize);
                    }
                } else if (recipe instanceof ShapelessOreRecipe) {
                    ItemStack result = recipe.getRecipeOutput();
                    double emc = emcRegistry.getEMC(result);
                    double calculated = 0;
                    for (Object object : ((ShapelessOreRecipe) recipe).getInput()) {
                        if (object != null) {
                            if (object instanceof List) {
                                double listCalc = 0;
                                for (Object object1 : (List) object) {
                                    listCalc += emcRegistry.getEMC(object1);
                                }
                                calculated += (listCalc / ((List) object).size());
                            } else {
                                calculated += emcRegistry.getEMC(object);
                            }
                        }
                    }
                    if (calculated > emc) {
                        emcRegistry.setEMC(result, calculated / result.stackSize);
                    }
                }
            }
        }
    }
}
