package com.agilemods.materiamuto.common.command;

import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.agilemods.materiamuto.common.emc.recipe.VanillaCraftingRecipeScanner;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CommandEMC extends CommandBase {

    @Override
    public String getCommandName() {
        return "emc";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 1 && args[0].equals("reload")) {
            VanillaCraftingRecipeScanner recipeScanner = new VanillaCraftingRecipeScanner();
            recipeScanner.scan();

            ItemStack held = ((EntityPlayer)sender).getHeldItem();

            if (held != null) {
                System.out.println(recipeScanner.getRecipesForItem(new VanillaStackWrapper(held)));
            }
        }
    }
}
