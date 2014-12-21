package com.agilemods.materiamuto.common.core.handlers;

import com.agilemods.materiamuto.common.inventory.InventoryAlchemicalBag;
import com.google.common.collect.Maps;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import com.agilemods.materiamuto.common.item.ItemAlchemicalBag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class AlchemicalBagHandler {

    private static final String FILE_SUFFIX = "alch";

    public static final AlchemicalBagHandler INSTANCE = new AlchemicalBagHandler();

    public static void register() {
        MinecraftForge.EVENT_BUS.register(AlchemicalBagHandler.INSTANCE);
    }

    public Map<String, InventoryAlchemicalBag[]> inventoryMap = Maps.newHashMap();

    private InventoryAlchemicalBag[] getOrCreateArray(String uuid) {
        InventoryAlchemicalBag[] array = inventoryMap.get(uuid);
        if (array == null) {
            array = new InventoryAlchemicalBag[16];
            for (int i = 0; i < array.length; i++) {
                array[i] = new InventoryAlchemicalBag(i);
            }
            inventoryMap.put(uuid, array);
        }
        return array;
    }

    public InventoryAlchemicalBag getInventory(String target, int color) {
        return getOrCreateArray(target)[color];
    }

    public void setInventory(String target, int color, InventoryAlchemicalBag inventory) {
        InventoryAlchemicalBag[] array = getOrCreateArray(target);
        array[color] = inventory;
        inventoryMap.put(target, array);
    }

    public InventoryAlchemicalBag getInventory(ItemStack itemStack) {
        if (itemStack.hasTagCompound()) {
            String target = itemStack.getTagCompound().getString("target");
            return getInventory(target, itemStack.getItemDamage());
        } else {
            return null;
        }
    }

    public void setInventory(ItemStack itemStack, InventoryAlchemicalBag inventory) {
        if (itemStack.hasTagCompound()) {
            String target = itemStack.getTagCompound().getString("target");
            setInventory(target, itemStack.getItemDamage(), inventory);
        }
    }

    @SubscribeEvent
    public void onPlayerLoad(PlayerEvent.LoadFromFile event) {
        File file = new File(event.playerDirectory, event.entityPlayer.getCommandSenderName() + "." + FILE_SUFFIX);
        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        InventoryAlchemicalBag[] array = new InventoryAlchemicalBag[16];

        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                nbtTagCompound = CompressedStreamTools.readCompressed(fileInputStream);
            } catch (IOException ignored) {
            }
        }

        for (int i = 0; i < 16; i++) {
            NBTTagCompound dataTag = nbtTagCompound.getCompoundTag(ItemAlchemicalBag.COLORS[i]);
            InventoryAlchemicalBag inventoryAlchemicalBag = new InventoryAlchemicalBag(i);
            inventoryAlchemicalBag.readFromNBT(dataTag);
            array[i] = inventoryAlchemicalBag;
        }

        inventoryMap.put(event.entity.getCommandSenderName(), array);
    }

    @SubscribeEvent
    public void onPlayerSave(PlayerEvent.SaveToFile event) {
        File file = new File(event.playerDirectory, event.entityPlayer.getCommandSenderName() + "." + FILE_SUFFIX);

        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    return;
                }
            } catch (IOException ex) {
                return;
            }
        }

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        InventoryAlchemicalBag[] array = getOrCreateArray(event.entityPlayer.getCommandSenderName());

        for (int i = 0; i < array.length; i++) {
            NBTTagCompound dataTag = new NBTTagCompound();
            array[i].writeToNBT(dataTag);
            nbtTagCompound.setTag(ItemAlchemicalBag.COLORS[i], dataTag);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            CompressedStreamTools.writeCompressed(nbtTagCompound, fileOutputStream);
        } catch (IOException ex) {
            return; // LOG, and stuff
        }
    }
}
