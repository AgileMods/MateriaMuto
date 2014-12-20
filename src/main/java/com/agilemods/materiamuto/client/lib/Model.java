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
package com.agilemods.materiamuto.client.lib;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public enum Model {

    ALCHEMICAL_CHESET("chest"),
    COLLECTOR("collector"),
    CONDENSER("condenser"),
    RELAY("relay"),
    MATTER_FURNACE("furnace"),
    TRANSMUTATION_TABLET("tablet");

    private IModelCustom model;

    private Model(String file) {
        this.model = AdvancedModelLoader.loadModel(new ResourceLocation("materiamuto:models/" + file + ".tcn"));
    }

    public void renderAll() {
        model.renderAll();
    }

    public void renderOnly(String... groupNames) {
        model.renderOnly(groupNames);
    }

    public void renderPart(String partName) {
        model.renderPart(partName);
    }

    public void renderAllExcept(String... excludedGroupNames) {
        model.renderAllExcept(excludedGroupNames);
    }
}
