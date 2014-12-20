package com.agilemods.materiamuto.common.emc;

import com.agilemods.materiamuto.api.emc.IEMCRegistry;
import com.agilemods.materiamuto.api.emc.StackReference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class EMCDelegate implements IEMCRegistry {

    @Override
    public void blacklist(Block block) {
        EMCRegistry.blacklist(new StackReference(block));
    }

    @Override
    public void blacklist(Item item) {
        EMCRegistry.blacklist(new StackReference(item));
    }

    @Override
    public void blacklist(ItemStack itemStack) {
        EMCRegistry.blacklist(new StackReference(itemStack));
    }

    @Override
    public void blacklist(StackReference stackReference) {
        EMCRegistry.blacklist(stackReference);
    }

    @Override
    public double getEMC(Object object) {
        return EMCRegistry.getEMC(object);
    }

    @Override
    public double getEMC(Block block) {
        return EMCRegistry.getEMC(block);
    }

    @Override
    public double getEMC(Item item) {
        return EMCRegistry.getEMC(item);
    }

    @Override
    public double getEMC(Fluid fluid) {
        return EMCRegistry.getEMC(fluid);
    }

    @Override
    public double getEMC(ItemStack itemStack) {
        return EMCRegistry.getEMC(itemStack);
    }

    @Override
    public double getEMC(StackReference stackReference) {
        return EMCRegistry.getEMC(stackReference);
    }

    @Override
    public void setEMC(Block block, double value) {
        EMCRegistry.setEMC(block, value);
    }

    @Override
    public void setEMC(Item item, double value) {
        EMCRegistry.setEMC(item, value);
    }

    @Override
    public void setEMC(Fluid fluid, double value) {
        EMCRegistry.setEMC(fluid, value);
    }

    @Override
    public void setEMC(ItemStack itemStack, double value) {
        EMCRegistry.setEMC(itemStack, value);
    }

    @Override
    public void setEMC(StackReference stackReference, double value) {
        EMCRegistry.setEMC(stackReference, value, false);
    }
}
