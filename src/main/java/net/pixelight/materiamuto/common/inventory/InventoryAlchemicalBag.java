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
package net.pixelight.materiamuto.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.pixelight.materiamuto.common.core.helpers.InventoryHelper;
import net.pixelight.materiamuto.common.item.MMItems;

public class InventoryAlchemicalBag implements IInventory {

    private static final int MAX_BAG_SIZE = 27;

    private ItemStack[] inventory;

    public final int color;

    public InventoryAlchemicalBag(int color) {
        this.inventory = new ItemStack[MAX_BAG_SIZE];
        this.color = color;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        InventoryHelper.readFromNBT(this, nbtTagCompound);
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        InventoryHelper.writeToNBT(this, nbtTagCompound);
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return slot >= 0 && slot < getSizeInventory() ? inventory[slot] : null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        if (slot >= 0 && slot < getSizeInventory()) {
            inventory[slot] = itemStack;
        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack itemStack = getStackInSlot(slot);

        if (itemStack != null) {
            ItemStack returnedStack = null;
            if (itemStack.stackSize <= amount) {
                returnedStack = itemStack.copy();
                setInventorySlotContents(slot, null);
                this.markDirty();
                return returnedStack;
            } else {
                returnedStack = itemStack.splitStack(amount);

                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(0, null);
                }

                this.markDirty();
                return returnedStack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack itemStack = getStackInSlot(slot);

        if (itemStack != null) {
            ItemStack returnedStack = itemStack.copy();
            setInventorySlotContents(slot, null);
            return returnedStack;
        } else {
            return null;
        }
    }

    @Override
    public String getInventoryName() {
        return "container.alchemicalBag";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.getItem() == null) {
            return false;
        }
        if (itemStack.getItem() == MMItems.alchemicalBag) {
            return false;
        }
        return true;
    }
}
