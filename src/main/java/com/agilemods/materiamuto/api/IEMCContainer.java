package com.agilemods.materiamuto.api;

import net.minecraft.item.ItemStack;

public interface IEMCContainer {

    public double getMaxEMC(ItemStack itemStack);

    public double getEMC(ItemStack itemStack);

    public void setEMC(ItemStack itemStack, double value);
}
