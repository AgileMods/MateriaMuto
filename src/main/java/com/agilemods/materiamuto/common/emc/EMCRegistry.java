/**
 * This file is part of MateriaMuto, licensed under the MIT License (MIT).
 *
 * Copyright (c) AgileMods <http://www.agilemods.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.agilemods.materiamuto.common.emc;

import com.agilemods.materiamuto.common.core.lib.StackReference;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.List;
import java.util.Map;

public class EMCRegistry {

    private static Map<StackReference, Double> emcMapping = Maps.newHashMap();

    public static void setEMC(Block block, double value) {
        setEMC(new StackReference(block), value);
    }

    public static void setEMC(Item item, double value) {
        setEMC(new StackReference(item), value);
    }

    public static void setEMC_wild(Block block, double value) {
        setEMC(new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE), value);
    }

    public static void setEMC_wild(Item item, double value) {
        setEMC(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE), value);
    }

    public static void setEMC(ItemStack itemStack, double value) {
        setEMC(new StackReference(itemStack), value);
    }

    public static void setEMC(StackReference stackReference, double value) {
        emcMapping.put(stackReference, value);
    }

    public static double getEMC(Object object) {
        if (object instanceof Block) {
            return getEMC((Block) object);
        }
        if (object instanceof Item) {
            return getEMC((Item) object);
        }
        if (object instanceof ItemStack) {
            return getEMC((ItemStack) object);
        }
        if (object instanceof StackReference) {
            return getEMC((StackReference) object);
        }
        return 0;
    }

    public static double getEMC(Block block) {
        return getEMC(new StackReference(block));
    }

    public static double getEMC(Item item) {
        return getEMC(new StackReference(item));
    }

    public static double getEMC(ItemStack itemStack) {
        return getEMC(new StackReference(itemStack));
    }

    public static double getEMC(StackReference stackReference) {
        Double value = emcMapping.get(stackReference);
        return value == null ? 0 : value;
    }

    public static void initialize() {
        initializeLazyValues();
        scanCraftingRecipes(2);
        scanFurnaceRecipes(2);
        scanCraftingRecipes(2);
        scanFurnaceRecipes(2);
        addFinalValues();
    }

    private static void initializeLazyValues() {
        setEMC_wild(Blocks.cobblestone, 1);
        setEMC(Blocks.netherrack, 1);
        setEMC_wild(Blocks.dirt, 1);
        setEMC(Blocks.grass, 1);
        setEMC(Blocks.mycelium, 1);
        setEMC_wild(Blocks.leaves, 1);
        setEMC_wild(Blocks.leaves2, 1);
        setEMC_wild(Blocks.sand, 1);
        setEMC(Blocks.end_stone, 4);
        setEMC(Blocks.snow, 1);
        setEMC(Blocks.ice, 1);
        setEMC(Blocks.gravel, 4);
        setEMC_wild(Blocks.log, 32);
        setEMC_wild(Blocks.log2, 32);
        setEMC(Blocks.cactus, 8);
        setEMC(Blocks.vine, 8);
        setEMC(Blocks.torch, 9);
        setEMC(Blocks.web, 12);
        setEMC(Items.wheat_seeds, 16);
        setEMC(Items.melon, 16);
        setEMC(Items.clay_ball, 16);
        setEMC(Blocks.waterlily, 16);
        setEMC_wild(Blocks.yellow_flower, 16);
        setEMC_wild(Blocks.red_flower, 16);
        setEMC_wild(Blocks.double_plant, 32);
        setEMC(Items.wheat, 24);
        setEMC(Items.nether_wart, 24);
        setEMC(Items.stick, 4);
        setEMC(Blocks.red_mushroom, 32);
        setEMC(Blocks.brown_mushroom, 32);
        setEMC(Items.reeds, 32);
        setEMC(Blocks.soul_sand, 49);
        setEMC(Blocks.obsidian, 64);
        setEMC(Items.apple, 128);
        setEMC(Blocks.pumpkin, 144);
        setEMC(Items.bone, 144);
        setEMC(Blocks.mossy_cobblestone, 145);
        setEMC(Items.saddle, 192);
        setEMC(Items.record_11, 2048);
        setEMC(Items.record_13, 2048);
        setEMC(Items.record_blocks, 2048);
        setEMC(Items.record_cat, 2048);
        setEMC(Items.record_chirp, 2048);
        setEMC(Items.record_far, 2048);
        setEMC(Items.record_mall, 2048);
        setEMC(Items.record_mellohi, 2048);
        setEMC(Items.record_stal, 2048);
        setEMC(Items.record_strad, 2048);
        setEMC(Items.record_wait, 2048);
        setEMC(Items.record_ward, 2048);
        setEMC(Items.string, 12);
        setEMC(Items.rotten_flesh, 32);
        setEMC(Items.slime_ball, 32);
        setEMC(Items.egg, 32);
        setEMC(Items.feather, 48);
        setEMC(Items.leather, 64);
        setEMC(Items.spider_eye, 128);
        setEMC(Items.gunpowder, 192);
        setEMC(Items.ender_pearl, 1024);
        setEMC(Items.blaze_rod, 1536);
        setEMC(Items.ghast_tear, 4096);
        setEMC(Blocks.dragon_egg, 262144);
        setEMC(Items.porkchop, 64);
        setEMC(Items.beef, 64);
        setEMC(Items.chicken, 64);
        setEMC_wild(Items.fish, 64);
        setEMC(Items.carrot, 64);
        setEMC(Items.potato, 64);
        setEMC(Items.iron_ingot, 256);
        setEMC(Items.gold_ingot, 2048);
        setEMC(Items.flint, 4);
        setEMC_wild(Items.coal, 128);
        setEMC(Items.redstone, 64);
        setEMC(Items.glowstone_dust, 384);
        setEMC(Items.quartz, 256);
        setEMC(Items.enchanted_book, 2048);
        setEMC(Items.emerald, 16384);
        setEMC(Items.nether_star, 139264);
        setEMC(Items.iron_horse_armor, 1280);
        setEMC(Items.golden_horse_armor, 1024);
        setEMC(Items.diamond_horse_armor, 40960);
        setEMC(Blocks.packed_ice, 4);
        setEMC(Items.snowball, 1);
        setEMC(Items.filled_map, 1472);
        setEMC(Items.blaze_powder, 768);
        setEMC_wild(Blocks.sapling, 32);
        setEMC_wild(Blocks.tallgrass, 1);
        setEMC_wild(Blocks.deadbush, 1);
        setEMC(Items.diamond, 8192);

        // Dye Handling
        for (int i = 0; i < 16; i++) {
            double emc = 16;

            if (i == 3) {
                emc = 128;
            } else if (i == 4) {
                emc = 864;
            } else if (i == 15) {
                emc = 48;
            }

            setEMC(new ItemStack(Items.dye, 1, i), emc);
        }

        // Also add ore dictionary tags
        for (StackReference stackReference : emcMapping.keySet()) {
            double emc = getEMC(stackReference);
            ItemStack itemStack = stackReference.toItemStack();

            for (int id : OreDictionary.getOreIDs(itemStack)) {
                String name = OreDictionary.getOreName(id);
                for (ItemStack oreStack : OreDictionary.getOres(name)) {
                    setEMC(oreStack, emc);
                }
            }
        }
    }

    private static void scanCraftingRecipes(int runs) {
        List<IRecipe> recipeList = (List<IRecipe>) CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < runs; i++) {
            for (IRecipe recipe : recipeList) {
                if (recipe instanceof ShapedRecipes) {
                    ItemStack result = recipe.getRecipeOutput();
                    double emc = getEMC(result);
                    double calculated = 0;
                    for (ItemStack itemStack : ((ShapedRecipes) recipe).recipeItems) {
                        calculated += getEMC(itemStack);
                    }
                    if (calculated > emc) {
                        setEMC(result, calculated / result.stackSize);
                    }
                } else if (recipe instanceof ShapelessRecipes) {
                    ItemStack result = recipe.getRecipeOutput();
                    double emc = getEMC(result);
                    double calculated = 0;
                    for (Object object : ((ShapelessRecipes) recipe).recipeItems) {
                        calculated += getEMC(object);
                    }
                    if (calculated > emc) {
                        setEMC(result, calculated / result.stackSize);
                    }
                } else if (recipe instanceof ShapedOreRecipe) {
                    ItemStack result = recipe.getRecipeOutput();
                    double emc = getEMC(result);
                    double calculated = 0;
                    for (Object object : ((ShapedOreRecipe) recipe).getInput()) {
                        if (object != null) {
                            if (object instanceof List) {
                                for (Object object1 : (List) object) {
                                    calculated += getEMC(object1);
                                }
                            } else {
                                calculated += getEMC(object);
                            }
                        }
                        if (calculated > emc) {
                            setEMC(result, calculated / result.stackSize);
                        }
                    }
                } else if (recipe instanceof ShapelessOreRecipe) {
                    ItemStack result = recipe.getRecipeOutput();
                    double emc = getEMC(result);
                    double calculated = 0;
                    for (Object object : ((ShapelessOreRecipe) recipe).getInput()) {
                        if (object != null) {
                            if (object instanceof List) {
                                for (Object object1 : (List) object) {
                                    calculated += getEMC(object1);
                                }
                            } else {
                                calculated += getEMC(object);
                            }
                        }
                        if (calculated > emc) {
                            setEMC(result, calculated / result.stackSize);
                        }
                    }
                }
            }
        }
    }

    private static void scanFurnaceRecipes(int runs) {
        Map<ItemStack, ItemStack> recipeMap = (Map<ItemStack, ItemStack>) FurnaceRecipes.smelting().getSmeltingList();
        for (int i = 0; i < runs; i++) {
            for (Map.Entry<ItemStack, ItemStack> entry : recipeMap.entrySet()) {
                StackReference key = new StackReference(entry.getKey());
                StackReference value = new StackReference(entry.getValue());

                if (!key.valid() || !value.valid()) {
                    continue;
                }

                double keyEMC = getEMC(key);
                double valueEMC = getEMC(value);

                if (keyEMC > 0 && valueEMC == 0) {
                    setEMC(value, keyEMC / value.toItemStack().stackSize);
                } else if (keyEMC == 0 && valueEMC > 0) {
                    setEMC(key, valueEMC * key.toItemStack().stackSize);
                }
            }
        }
    }

    private static void addFinalValues() {
        // Stone brick handling
        double stoneBrickEmc = getEMC(Blocks.stonebrick);
        for (int i = 1; i < BlockStoneBrick.field_150141_b.length; i++) {
            setEMC(new ItemStack(Blocks.stonebrick, 1, i), stoneBrickEmc);
        }

        // Anvil handling
        double anvilEmc = getEMC(new ItemStack(Blocks.anvil, 1, 0));
        setEMC(new ItemStack(Blocks.anvil, 1, 1), anvilEmc * 0.66D);
        setEMC(new ItemStack(Blocks.anvil, 1, 2), anvilEmc * 0.33D);

        // Name tag
        double string = getEMC(Items.string);
        double paper = getEMC(Items.paper);
        setEMC(Items.name_tag, string + paper);
    }
}
