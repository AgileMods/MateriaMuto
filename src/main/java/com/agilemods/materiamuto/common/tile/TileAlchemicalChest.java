package com.agilemods.materiamuto.common.tile;

import com.agilemods.materiamuto.common.core.helpers.InventoryHelper;
import com.agilemods.materiamuto.common.tile.prefab.TileMM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileAlchemicalChest extends TileMM implements IInventory {

    public ItemStack[] inventory = new ItemStack[104];

    public float lidAngle;
    public float prevLidAngle;

    public int numPlayersUsing;

    private int ticksSinceSync;

    @Override
    public void writeCustomNBT(NBTTagCompound nbtTagCompound) {
        InventoryHelper.writeToNBT(this, nbtTagCompound);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbtTagCompound) {
        InventoryHelper.readFromNBT(this, nbtTagCompound);
    }

    @Override
    public int getSizeInventory() {
        return 104;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int size) {
        if (inventory[slot] != null) {
            ItemStack itemstack;

            if (inventory[slot].stackSize <= size) {
                itemstack = inventory[slot];
                inventory[slot] = null;
                return itemstack;
            } else {
                itemstack = inventory[slot].splitStack(size);

                if (inventory[slot].stackSize == 0) {
                    inventory[slot] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (inventory[slot] != null) {
            ItemStack itemstack = inventory[slot];
            inventory[slot] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "";
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
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        /* BEGIN ANIMATION HANDLING */

        float f;

        this.prevLidAngle = this.lidAngle;
        f = 0.1F;
        double d0;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
            double d1 = (double) this.xCoord + 0.5D;
            d0 = (double) this.zCoord + 0.5D;
            this.worldObj.playSoundEffect(d1, (double) this.yCoord + 0.5D, d0, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
            float f1 = this.lidAngle;

            if (this.numPlayersUsing > 0) {
                this.lidAngle += f;
            } else {
                this.lidAngle -= f;
            }

            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            float f2 = 0.5F;

            if (this.lidAngle < f2 && f1 >= f2) {
                d0 = (double) this.xCoord + 0.5D;
                double d2 = (double) this.zCoord + 0.5D;
                this.worldObj.playSoundEffect(d0, (double) this.yCoord + 0.5D, d2, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }

        /* END ANIMATION HANDLING */
    }

    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        if (par1 == 1) {
            this.numPlayersUsing = par2;
            return true;
        } else {
            return super.receiveClientEvent(par1, par2);
        }
    }

    public void openInventory() {
        if (this.numPlayersUsing < 0) {
            this.numPlayersUsing = 0;
        }

        ++this.numPlayersUsing;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
    }

    public void closeInventory() {
        if (this.numPlayersUsing < 0) {
            this.numPlayersUsing = 0;
        }

        --this.numPlayersUsing;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return true;
    }
}