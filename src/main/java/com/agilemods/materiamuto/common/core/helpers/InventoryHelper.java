package com.agilemods.materiamuto.common.core.helpers;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.Random;

public class InventoryHelper {

    private static Random random = new Random();

    public static void readFromNBT(IInventory inventory, NBTTagCompound nbtTagCompound) {
        NBTTagList nbtTagList = nbtTagCompound.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < nbtTagList.tagCount(); i++) {
            NBTTagCompound dataTag = nbtTagList.getCompoundTagAt(i);
            byte slot = dataTag.getByte("slot");
            ItemStack itemStack = ItemStack.loadItemStackFromNBT(dataTag);
            inventory.setInventorySlotContents(slot, itemStack);
        }
    }

    public static void writeToNBT(IInventory inventory, NBTTagCompound nbtTagCompound) {
        NBTTagList nbtTagList = new NBTTagList();
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack != null) {
                NBTTagCompound dataTag = new NBTTagCompound();
                itemStack.writeToNBT(dataTag);
                dataTag.setByte("slot", (byte) i);
                nbtTagList.appendTag(dataTag);
            }
        }
        nbtTagCompound.setTag("Inventory", nbtTagList);
    }

    public static void dropContents(IInventory inventory, World world, int x, int y, int z) {
        for (int i = 0; i < inventory.getSizeInventory(); ++i) {
            ItemStack stack = inventory.getStackInSlot(i);

            if (stack != null) {
                float f = random.nextFloat() * 0.8F + 0.1F;
                float f1 = random.nextFloat() * 0.8F + 0.1F;
                float f2 = random.nextFloat() * 0.8F + 0.1F;

                while (stack.stackSize > 0) {
                    int j1 = random.nextInt(21) + 10;

                    if (j1 > stack.stackSize) {
                        j1 = stack.stackSize;
                    }

                    stack.stackSize -= j1;
                    EntityItem
                            entityitem =
                            new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2),
                                    new ItemStack(stack.getItem(), j1, stack.getItemDamage()));

                    if (stack.hasTagCompound()) {
                        entityitem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
                    }

                    float f3 = 0.05F;
                    entityitem.motionX = (double) ((float) random.nextGaussian() * f3);
                    entityitem.motionY = (double) ((float) random.nextGaussian() * f3 + 0.2F);
                    entityitem.motionZ = (double) ((float) random.nextGaussian() * f3);

                    world.spawnEntityInWorld(entityitem);
                }
            }
        }
    }
}
