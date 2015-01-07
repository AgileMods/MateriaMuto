package com.agilemods.materiamuto.common.core;

import com.agilemods.materiamuto.common.lib.LibMisc;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MMTab extends CreativeTabs {

    public static MMTab INSTANCE = new MMTab();

    public MMTab() {
        super(LibMisc.MODID);
        setNoTitle();
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(MMItems.kleinStar);
    }

    @Override
    public Item getTabIconItem() {
        return getIconItemStack().getItem();
    }
}
