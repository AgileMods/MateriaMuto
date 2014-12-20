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

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public enum Texture {

    ALCHEMICAL_CHEST("alchemicalChest"),
    ALCHEMICAL_CHEST_OVERLAY("alchemicalChest_overlay"),

    COLLECTOR_MK1("collector_mk1"),
    COLLECTOR_Mk2("collector_mk2"),
    COLLECTOR_MK3("collector_mk3"),
    COLLECTOR_OVERLAY("collector_overlay"),

    RELAY_MK1("relay_mk1"),
    RELAY_Mk2("relay_mk2"),
    RELAY_MK3("relay_mk3"),
    RELAY_OVERLAY("relay_overlay"),

    CONDENSER("condenser"),
    CONDENSER_OVERLAY("condenser_overlay"),

    MATTER_FURNACE_DARK_OFF("furnaceDark_off"),
    MATTER_FURNACE_DARK_ON("furnaceDark_on"),
    MATTER_FURNACE_RED_OFF("furnaceRed_off"),
    MATTER_FURNACE_RED_ON("furnaceRed_on"),
    MATTER_FURNACE_OVERLAY("furnace_overlay"),

    TRANSMUTATION_TABLET("tablet"),
    TRANSMUTATION_TABLET_OVERLAY("tablet_overlay");

    private final ResourceLocation texture;

    private Texture(String file) {
        this.texture = new ResourceLocation("materiamuto:textures/models/" + file + ".png");
    }

    public void bindTexture() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
    }
}
