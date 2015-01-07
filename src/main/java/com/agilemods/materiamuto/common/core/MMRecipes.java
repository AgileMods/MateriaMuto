package com.agilemods.materiamuto.common.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

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
    }
}
