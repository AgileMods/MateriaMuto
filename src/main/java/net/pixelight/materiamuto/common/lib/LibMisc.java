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
package net.pixelight.materiamuto.common.lib;

public class LibMisc {

    // Mod Constants
    public static final String MODID = "MateriaMuto";
    public static final String MODNAME = "MateriaMuto";
    public static final String BUILD = "GRADLE:BUILD";
    public static final String VERSION = "GRADLE:VERSION-" + BUILD;
    public static final String DEPENDENCIES = "required-after:Forge@[10.12.2.1230,);";

    // Proxy Constants
    public static final String PROXY_COMMON = "net.pixelight.materiamuto.CommonProxy";
    public static final String PROXY_CLIENT = "net.pixelight.materiamuto.ClientProxy";
    public static final String GUI_FACTORY = "net.pixelight.materiamuto.client.core.gui.GuiFactory";

    // Keybindings
    public static final String CATEGORY = "key.categories.mm";
    public static final String CHARGE = "key.charge";
    public static final String MODE = "key.extra";
    public static final String RELEASE = "key.release";
    public static final String TOGGLE = "key.toggle";

    // Texture Constants
    public static final String RESOURCE_PREFIX = MODID.toLowerCase() + ":";
}

