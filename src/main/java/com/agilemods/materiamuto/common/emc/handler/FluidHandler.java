package com.agilemods.materiamuto.common.emc.handler;

import com.agilemods.materiamuto.api.emc.EMCRegistryState;
import com.agilemods.materiamuto.api.emc.IEMCItemHandler;
import com.agilemods.materiamuto.api.emc.IEMCRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FluidHandler implements IEMCItemHandler {

    @Override
    public EMCRegistryState getInsertionState() {
        return EMCRegistryState.MISC;
    }

    @Override
    public void handleItem(IEMCRegistry emcRegistry, ItemStack itemStack) {
        if (FluidContainerRegistry.isContainer(itemStack)) {
            FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(itemStack);

            if (fluidStack != null) {
                Fluid fluid = fluidStack.getFluid();
                ItemStack empty = FluidContainerRegistry.drainFluidContainer(itemStack);

                if (empty != null && fluid != null) {
                    emcRegistry.setEMC(itemStack, emcRegistry.getEMC(empty) + emcRegistry.getEMC(fluid));
                }
            }
        }
    }
}
