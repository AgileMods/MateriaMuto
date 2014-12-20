package com.agilemods.materiamuto.common.item;

import com.agilemods.materiamuto.common.item.prefab.MMSubItem;
import com.agilemods.materiamuto.common.network.GuiHandler;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.awt.*;
import java.util.List;

public class ItemAlchemicalBag extends MMSubItem {

    public static final String[]
            COLORS =
            new String[]{"white", "orange", "magenta", "lightBlue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown",
                         "green", "red", "black"};

    private static final int[] colorCache = new int[COLORS.length];

    public ItemAlchemicalBag() {
        super();
        setHasSubtypes(true);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean debug) {
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("target")) {
            list.add("Bound to: " + itemStack.getTagCompound().getString("target"));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        if (!itemStack.getTagCompound().hasKey("target")) {
            itemStack.getTagCompound().setString("target", entityPlayer.getCommandSenderName());
        }
        GuiHandler.Type.GUI_ALCHEMICAL_CHEST.openGui(entityPlayer);
        return itemStack;
    }

    @Override
    public int getColorFromItemStack(ItemStack itemStack, int pass) {
        int cachedColor = colorCache[itemStack.getItemDamage()];
        if (cachedColor == 0) {
            float[] colorArray = EntitySheep.fleeceColorTable[itemStack.getItemDamage()];
            Color color = new Color(colorArray[0], colorArray[1], colorArray[2]).brighter();
            colorCache[itemStack.getItemDamage()] = color.getRGB();
        }
        return cachedColor;
    }

    @Override
    public String[] getNames() {
        return COLORS;
    }

    @Override
    public String getIconPrefix() {
        return null;
    }

    @Override
    public String getOverrideIcon() {
        return "bag_base";
    }
}
