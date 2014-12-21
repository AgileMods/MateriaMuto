package com.agilemods.materiamuto.common.core;

import com.agilemods.materiamuto.common.item.MMItems;
import com.agilemods.materiamuto.common.lib.LibMisc;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MMTabScroll extends CreativeTabs {

    public static MMTabScroll INSTANCE = new MMTabScroll();

    public MMTabScroll() {
        super("Scrolls");
        setNoTitle();
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(MMItems.enchantScroll);
    }

    @Override
    public Item getTabIconItem() {
        return getIconItemStack().getItem();
    }
}
