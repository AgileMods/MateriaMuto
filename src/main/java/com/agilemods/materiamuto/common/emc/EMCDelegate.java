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

import com.agilemods.materiamuto.api.IEMCRegistry;
import com.agilemods.materiamuto.common.core.lib.StackReference;
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
}
