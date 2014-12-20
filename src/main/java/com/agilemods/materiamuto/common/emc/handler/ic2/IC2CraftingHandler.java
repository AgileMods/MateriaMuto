package com.agilemods.materiamuto.common.emc.handler.ic2;

import com.agilemods.materiamuto.api.emc.EMCRegistryState;
import com.agilemods.materiamuto.api.emc.IEMCMiscHandler;
import com.agilemods.materiamuto.api.emc.IEMCRegistry;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.ArrayList;
import java.util.List;

public class IC2CraftingHandler implements IEMCMiscHandler {

    private static Class<?> SHAPED;
    private static Class<?> SHAPELESS;
    private static Class<?> INPUT;

    static {
        try {
            SHAPED = Class.forName("ic2.core.AdvRecipe");
            SHAPELESS = Class.forName("ic2.core.AdvShapelessRecipe");
            INPUT = Class.forName("ic2.api.recipe.IRecipeInput");
        } catch (Exception ignore) {
        }
    }

    private int runs;

    public IC2CraftingHandler(int runs) {
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
                if (SHAPED.isAssignableFrom(recipe.getClass())) {
                    handleShapedRecipe(emcRegistry, recipe);
                } else if (SHAPELESS.isAssignableFrom(recipe.getClass())) {
                    handleShapelessRecipe(emcRegistry, recipe);
                }
            }
        }
    }

    private void handleShapedRecipe(IEMCRegistry emcRegistry, IRecipe recipe) {
        try {
            Object[] input = (Object[]) recipe.getClass().getDeclaredField("input").get(recipe);

            ItemStack result = recipe.getRecipeOutput();

            double emc = emcRegistry.getEMC(result);
            double calculated = 0;
            for (Object object : input) {
                if (object != null) {
                    if (object.getClass().getSimpleName().contains("RecipeInput")) {
                        List<ItemStack> list = Lists.newArrayList();
                        try {
                            list = (List<ItemStack>) object.getClass().getDeclaredMethod("getInputs").invoke(object);
                        } catch (Exception ignore) {
                            ignore.printStackTrace();
                        }

                        for (ItemStack itemStack : list) {
                            calculated += emcRegistry.getEMC(itemStack);
                        }
                    } else if (object instanceof ArrayList<?>) {
                        for (Object obj : (ArrayList<?>) object) {
                            calculated += emcRegistry.getEMC(obj);
                        }
                    }
                }
            }
            if (emc <= 0) {
                emcRegistry.setEMC(result, calculated);
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    private void handleShapelessRecipe(IEMCRegistry emcRegistry, IRecipe recipe) {
        try {
            Object[] input = (Object[]) recipe.getClass().getDeclaredField("input").get(recipe);

            ItemStack result = recipe.getRecipeOutput();

            double emc = emcRegistry.getEMC(result);
            double calculated = 0;
            for (Object object : input) {
                if (object != null) {
                    if (object.getClass().getSimpleName().contains("RecipeInput")) {
                        List<ItemStack> list = Lists.newArrayList();
                        try {
                            list = (List<ItemStack>) object.getClass().getDeclaredMethod("getInputs").invoke(object);
                        } catch (Exception ignore) {
                            ignore.printStackTrace();
                        }

                        for (ItemStack itemStack : list) {
                            calculated += emcRegistry.getEMC(itemStack);
                        }
                    } else if (object instanceof ArrayList<?>) {
                        for (Object obj : (ArrayList<?>) object) {
                            calculated += emcRegistry.getEMC(obj);
                        }
                    }
                }
            }
            if (emc <= 0) {
                emcRegistry.setEMC(result, calculated);
            }
        } catch (Exception ignore) {
        }
    }
}