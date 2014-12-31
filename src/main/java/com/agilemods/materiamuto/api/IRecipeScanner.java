package com.agilemods.materiamuto.api;

import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;

public interface IRecipeScanner {

    public void scan();

    public double getEMC(IEMCRegistry emcRegistry, VanillaStackWrapper vanillaStackWrapper);
}
