package com.agilemods.materiamuto.common.item;

import com.agilemods.materiamuto.common.item.prefab.MMItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;

public class ItemEnchantmentScroll extends MMItem {

    public ItemEnchantmentScroll() {
        super();
        setMaxStackSize(1);
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

}
