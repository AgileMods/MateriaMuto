package com.agilemods.materiamuto.common.block.prefab;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public abstract class MMSubBlock extends MMBlock {

    public MMSubBlock(Material material) {
        super(material);
    }

    public MMSubBlock() {
        super(Material.iron);
    }

    public abstract int[] getSubtypes();

    public abstract String getNameForType(int type);

    @Override
    public int damageDropped(int damage) {
        return damage;
    }

    @Override
    public void getSubBlocks(Item block, CreativeTabs tab, List list) {
        if (getSubtypes() == null || getSubtypes().length == 0) {
            list.add(new ItemStack(this, 1, 0));
        } else {
            for (int type : getSubtypes()) {
                list.add(new ItemStack(this, 1, type));
            }
        }
    }
}
