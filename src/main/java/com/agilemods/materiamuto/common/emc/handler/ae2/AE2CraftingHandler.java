package com.agilemods.materiamuto.common.emc.handler.ae2;

import com.agilemods.materiamuto.api.emc.EMCRegistryState;
import com.agilemods.materiamuto.api.emc.IEMCMiscHandler;
import com.agilemods.materiamuto.api.emc.IEMCRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class AE2CraftingHandler implements IEMCMiscHandler {

    private static Class<?> SHAPED;
    private static Class<?> SHAPELESS;

    static {
        try {
            SHAPED = Class.forName("appeng.recipes.game.ShapedRecipe");
            SHAPELESS = Class.forName("appeng.recipes.game.ShapelessRecipe");
        } catch (Exception ignore) {}
    }

    private int runs;

    public AE2CraftingHandler(int runs) {
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
            Object[] input = (Object[]) recipe.getClass().getDeclaredMethod("getInput").invoke(recipe);

            ItemStack result = recipe.getRecipeOutput();
            double emc = emcRegistry.getEMC(result);
            double calculated = 0;
            for (Object object : input) {
                if (object != null) {
                    if (object instanceof List) {
                        double listCalc = 0;
                        for (Object object1 : (List) object) {
                            listCalc += emcRegistry.getEMC(object1);
                        }
                        calculated += (listCalc / ((List)object).size());
                    } else if (object.toString().contains("oredictionary")) {
                        String str = object.toString();
                        String oreTag = str.substring(str.indexOf(":") + 1, str.lastIndexOf(":"));
                        List<ItemStack> ores = OreDictionary.getOres(oreTag);

                        double listCalc = 0;
                        for (ItemStack itemStack : ores) {
                            listCalc += emcRegistry.getEMC(itemStack);
                        }
                        calculated += (listCalc / ores.size());
                    } else {
                        calculated += emcRegistry.getEMC(object);
                    }
                }
            }
            if (calculated > emc) {
                emcRegistry.setEMC(result, calculated / result.stackSize);
            }
        } catch (Exception ignore) {}
    }

    private void handleShapelessRecipe(IEMCRegistry emcRegistry, IRecipe recipe) {
        try {
            ArrayList<Object> input = (ArrayList<Object>) recipe.getClass().getDeclaredMethod("getInput").invoke(recipe);

            ItemStack result = recipe.getRecipeOutput();
            double emc = emcRegistry.getEMC(result);
            double calculated = 0;
            for (Object object : input) {
                if (object != null) {
                    if (object instanceof List) {
                        double listCalc = 0;
                        for (Object object1 : (List) object) {
                            listCalc += emcRegistry.getEMC(object1);
                        }
                        calculated += (listCalc / ((List)object).size());
                    } else if (object.toString().contains("oredictionary")) {
                        String str = object.toString();
                        String oreTag = str.substring(str.indexOf(":") + 1, str.lastIndexOf(":"));
                        List<ItemStack> ores = OreDictionary.getOres(oreTag);

                        double listCalc = 0;
                        for (ItemStack itemStack : ores) {
                            listCalc += emcRegistry.getEMC(itemStack);
                        }
                        calculated += (listCalc / ores.size());
                    } else {
                        calculated += emcRegistry.getEMC(object);
                    }
                }
            }
            if (calculated > emc) {
                emcRegistry.setEMC(result, calculated / result.stackSize);
            }
        } catch (Exception ignore) {}
    }
}
