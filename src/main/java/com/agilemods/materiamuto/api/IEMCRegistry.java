package com.agilemods.materiamuto.api;

import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public interface IEMCRegistry {

    public void blacklist(Block block);

    public void blacklist(Item item);

    public void blacklist(ItemStack itemStack);

    public void blacklist(VanillaStackWrapper vanillaStackWrapper);

    public double getEMC(Object object);

    public double getEMC(Block block);

    public double getEMC(Item item);

    public double getEMC(Fluid fluid);

    public double getEMC(ItemStack itemStack);

    public double getEMC(VanillaStackWrapper vanillaStackWrapper);

    public void setEMC(Block block, double value);

    public void setEMC(Item item, double value);

    public void setEMC(Fluid fluid, double value);

    public void setEMC(ItemStack itemStack, double value);

    public void setEMC(VanillaStackWrapper vanillaStackWrapper, double value);
}
