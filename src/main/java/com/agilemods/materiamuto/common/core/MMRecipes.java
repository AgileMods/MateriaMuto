package com.agilemods.materiamuto.common.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class MMRecipes {

    public static void initialize() {
        ItemStack dustLow = new ItemStack(MMItems.covalenceDust, 40, 0);
        ItemStack dustMedium = new ItemStack(MMItems.covalenceDust, 40, 1);
        ItemStack dustHigh = new ItemStack(MMItems.covalenceDust, 40, 2);

        GameRegistry.addShapelessRecipe(
                dustLow,
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                Blocks.cobblestone,
                new ItemStack(Items.coal, 1, 1)
        );

        GameRegistry.addShapelessRecipe(
                dustMedium,
                Items.iron_ingot,
                Items.redstone
        );

        GameRegistry.addShapelessRecipe(
                dustHigh,
                Items.diamond,
                new ItemStack(Items.coal, 1, 0)
        );

        GameRegistry.addRecipe(new ShapedOreRecipe(
                MMBlocks.alchemicalChest,
                "LMH",
                "SDS",
                "ICI",
                'L', dustLow,
                'M', dustMedium,
                'H', dustHigh,
                'S', "stone",
                'D', Items.diamond,
                'I', Items.iron_ingot,
                'C', "chestWood"
        ));

        GameRegistry.addShapedRecipe(
                new ItemStack(MMBlocks.condenser),
                "ODO",
                "DCD",
                "ODO",
                'O', Blocks.obsidian,
                'D', Items.diamond,
                'C', MMBlocks.alchemicalChest
        );

        for (int i=0; i<16; i++) {
            GameRegistry.addShapedRecipe(
                    new ItemStack(MMItems.alchemicalBag, 1, i),
                    "DDD",
                    "WCW",
                    "WWW",
                    'D', dustHigh,
                    'W', new ItemStack(Blocks.wool, 1, i),
                    'C', MMBlocks.alchemicalChest
            );
        }
    }
}
