package com.agilemods.materiamuto.common.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import com.agilemods.materiamuto.common.item.prefab.MMSubItem;

public class ItemKleinStar extends MMSubItem {

    public static final String[] STARS = new String[]{"ein", "zwei", "drei", "vier", "sphere", "omega"};
    public static final int[] MAX_EMC = new int[]{50000, 200000, 800000, 3200000, 12800000, 51200000};

    public ItemKleinStar() {
        super();
        setMaxStackSize(1);
    }

    @Override
    public boolean showDurabilityBar(ItemStack itemStack) {
        return itemStack.getItemDamage() != 6;
    }

    @Override
    public String[] getNames() {
        return STARS;
    }

    @Override
    public String getIconPrefix() {
        return "stars";
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return damage == 6 ? super.getIconFromDamage(5) : super.getIconFromDamage(damage);
    }
}
