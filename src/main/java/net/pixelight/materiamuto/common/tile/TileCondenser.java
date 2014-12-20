package net.pixelight.materiamuto.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.pixelight.materiamuto.common.core.helpers.InventoryHelper;
import net.pixelight.materiamuto.common.core.helpers.MathFX;
import net.pixelight.materiamuto.common.emc.EMCRegistry;
import net.pixelight.materiamuto.common.tile.prefab.TileMM;

/**
 * @author dmillerw
 */
public class TileCondenser extends TileMM implements IInventory {

    public static final double MAX_EMC = 500000000;

    public static final int TARGET_SLOT = 91;
    public static final int INVENTORY_SIZE = 92;

    public static final int ANIMATION_TICK_MIN = 0;
    public static final int ANIMATION_TICK_MAX = 20;

    private int animationCooldown;
    public int animationTick;

    public boolean open;

    private ItemStack[] inventory = new ItemStack[INVENTORY_SIZE];

    public double targetEmc;
    private double lastEmc;
    public double emc;

    @Override
    public void updateEntity() {
        if (!open) {
            if (animationTick < ANIMATION_TICK_MAX) {
                animationTick++;
            }
        } else {
            if (animationTick > ANIMATION_TICK_MIN) {
                animationTick--;
            }
        }

        if (!worldObj.isRemote) {
            if (animationCooldown > 0) {
                animationCooldown--;
            }

            ItemStack targetStack = getStackInSlot(TARGET_SLOT);
            boolean acted = false;

            if (targetStack != null) {
                targetEmc = EMCRegistry.getEMC(targetStack);

                for (int i=0; i<TARGET_SLOT; i++) {
                    ItemStack itemStack = getStackInSlot(i);
                    double emc = EMCRegistry.getEMC(itemStack);

                    if (itemStack != null && emc > 0 && (this.emc + emc) <= MAX_EMC) {
                        if (itemStack.isItemEqual(targetStack))
                            continue;

                        acted = true;

                        itemStack.stackSize--;
                        if (itemStack.stackSize <= 0) setInventorySlotContents(i, null);
                        this.emc += emc;
                        break;
                    }
                }

                if (emc >= targetEmc) {
                    ItemStack itemStack = targetStack.copy();
                    itemStack.stackSize = 1;
                    if (insertStack(itemStack) == null) {
                        acted = true;
                        emc -= targetEmc;
                    }
                }

                if (emc != lastEmc) {
                    acted = true;
                    lastEmc = emc;
                }

                setOpen(acted);
            } else {
                setOpen(false);
            }
        }
    }

    private void setOpen(boolean open) {
        if (animationCooldown > 0) {
            return;
        }
        if (this.open != open) {
            this.open = open;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            animationCooldown = 20 * 15;
        }
    }

    public float getAnimationInterpolation() {
        return MathFX.sinerp(0F, 1F, ((float) animationTick / (float) ANIMATION_TICK_MAX));
    }

    private ItemStack insertStack(ItemStack itemStack) {
        for (int i=0; i<TARGET_SLOT; i++) {
            if (itemStack == null || itemStack.stackSize <= 0)
                return null;

            ItemStack slotStack = getStackInSlot(i);

            if (slotStack == null) {
                setInventorySlotContents(i, itemStack.copy());
                return null;
            }

            if (!(itemStack.isItemEqual(slotStack)))
                continue;

            int remainingAmount = slotStack.getMaxStackSize() - slotStack.stackSize;

            if (remainingAmount <= 0)
                continue;

            if (itemStack.stackSize <= remainingAmount) {
                slotStack.stackSize += itemStack.stackSize;
                return null;
            } else {
                itemStack.stackSize -= remainingAmount;
            }
        }
        return itemStack;
    }

    @Override
    public void onBlockBroken() {
        InventoryHelper.dropContents(this, worldObj, xCoord, yCoord, zCoord);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbtTagCompound) {
        InventoryHelper.writeToNBT(this, nbtTagCompound);
        nbtTagCompound.setDouble("emc", emc);
        nbtTagCompound.setBoolean("open", open);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbtTagCompound) {
        InventoryHelper.readFromNBT(this, nbtTagCompound);
        emc = nbtTagCompound.getDouble("emc");
        open = nbtTagCompound.getBoolean("open");
    }

    @Override
    public int getSizeInventory() {
        return INVENTORY_SIZE;
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
            ItemStack returnedStack;
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
        return "container.condenser";
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
        return true;
    }
}
