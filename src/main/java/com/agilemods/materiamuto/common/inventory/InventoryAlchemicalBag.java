package com.agilemods.materiamuto.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import com.agilemods.materiamuto.common.core.helpers.InventoryHelper;
import com.agilemods.materiamuto.common.item.MMItems;

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
