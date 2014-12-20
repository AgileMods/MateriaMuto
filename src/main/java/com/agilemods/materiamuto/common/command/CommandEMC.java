package com.agilemods.materiamuto.common.command;

import com.agilemods.materiamuto.common.emc.EMCRegistry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

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
            EMCRegistry.wipeout();
            EMCRegistry.initialize();
        }
    }
}
