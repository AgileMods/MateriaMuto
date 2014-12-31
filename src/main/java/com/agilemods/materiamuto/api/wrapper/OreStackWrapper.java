package com.agilemods.materiamuto.api.wrapper;

import com.agilemods.materiamuto.common.emc.EMCRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreStackWrapper implements IStackWrapper {

    private String oreTag;

    public OreStackWrapper(Block block) {
        this(new ItemStack(block));
    }

    public OreStackWrapper(Item item) {
        this(new ItemStack(item));
    }

    public OreStackWrapper(ItemStack itemStack) {
        int[] oreIds = OreDictionary.getOreIDs(itemStack);
        if (oreIds != null && oreIds.length > 0) {
            this.oreTag = OreDictionary.getOreName(oreIds[0]);
        }
    }

    public OreStackWrapper(String oreTag) {
        this.oreTag = oreTag;
    }

    public String getOreTag() {
        return oreTag;
    }

    @Override
    public boolean valid() {
        return oreTag != null && !oreTag.isEmpty();
    }

    @Override
    public double getEMC() {
        double emc = 0;
        int count = 0;

        for (ItemStack itemStack : OreDictionary.getOres(oreTag)) {
            emc += EMCRegistry.getEMC(itemStack);
            count++;
        }

        emc = emc / (double)count;

        return emc;
    }

    @Override
    public String toString() {
        return "{oreTag: " + oreTag + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OreStackWrapper that = (OreStackWrapper) o;

        if (oreTag != null ? !oreTag.equals(that.oreTag) : that.oreTag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return oreTag != null ? oreTag.hashCode() : 0;
    }
}
