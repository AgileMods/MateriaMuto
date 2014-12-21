package com.agilemods.materiamuto.common.item;

import com.agilemods.materiamuto.common.core.MMTabScroll;
import com.agilemods.materiamuto.common.item.prefab.MMItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class ItemEnchantmentScroll extends MMItem {

    public ItemEnchantmentScroll() {
        super();
        setMaxStackSize(1);
        setCreativeTab(MMTabScroll.INSTANCE);
    }

    @Override
    public String getIcon() {
        return "enchant_scroll";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int renderPass) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List tooltip, boolean advanced) {
        if (itemStack.hasTagCompound()) {
            NBTTagCompound tag = itemStack.getTagCompound();

            if (tag.hasKey("id") && tag.hasKey("lvl")) {
                int id = tag.getInteger("id");
                int level = tag.getInteger("lvl");

                if (Enchantment.enchantmentsList[id] != null) {
                    tooltip.add(Enchantment.enchantmentsList[id].getTranslatedName(level));
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List subItems) {
        for (Enchantment enchantment : Enchantment.enchantmentsBookList) {
            for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); ++i) {
                subItems.add(this.getEnchantedItemStack(new EnchantmentData(enchantment, i)));
            }
        }
    }

    public ItemStack getEnchantedItemStack(EnchantmentData enchantment) {
        ItemStack itemstack = new ItemStack(this);
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound tag = itemstack.getTagCompound();

        tag.setInteger("id", enchantment.enchantmentobj.effectId);
        tag.setInteger("lvl", enchantment.enchantmentLevel);

        return itemstack;
    }
}
