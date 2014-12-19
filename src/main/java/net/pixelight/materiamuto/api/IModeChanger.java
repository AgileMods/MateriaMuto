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
package net.pixelight.materiamuto.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * This class should be implemented on any items that can change modes.
 */
public interface IModeChanger {

    /**
     * Get the current mode of the ItemStack
     *
     * @param itemStack The ItemStack to check mode
     * @return The current mode
     */
    public byte getMode(ItemStack itemStack);

    /**
     * Called when the item needs to change modes.
     *
     * @param entityPlayer The player sending the request
     * @param itemStack    The ItemStack to be changed.
     */
    public void changeMode(EntityPlayer entityPlayer, ItemStack itemStack);
}
