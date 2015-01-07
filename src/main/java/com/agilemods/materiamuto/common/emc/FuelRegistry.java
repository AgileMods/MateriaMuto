package com.agilemods.materiamuto.common.emc;

import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.google.common.collect.Lists;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Comparator;
import java.util.LinkedList;

public class FuelRegistry {

    public static final Comparator<VanillaStackWrapper> EMC_COMPARATOR = new Comparator<VanillaStackWrapper>() {
        @Override
        public int compare(VanillaStackWrapper s1, VanillaStackWrapper s2) {
            double emc1 = EMCRegistry.getEMC(s1);
            double emc2 = EMCRegistry.getEMC(s2);

            if (emc1 < emc2) {
                return -1;
            }

            if (emc1 > emc2) {
                return 1;
            }

            return 0;
        }
    };
    
    private static LinkedList<VanillaStackWrapper> fuels = Lists.newLinkedList();

    public static void initialize() {
        fuels.clear();

        registerFuel(new ItemStack(Items.coal, 1, 1));
        registerFuel(new ItemStack(Items.redstone));
        registerFuel(new ItemStack(Blocks.redstone_block));
        registerFuel(new ItemStack(Items.coal));
        registerFuel(new ItemStack(Blocks.coal_block));
        registerFuel(new ItemStack(Items.gunpowder));
        registerFuel(new ItemStack(Items.glowstone_dust));
        registerFuel(new ItemStack(Items.blaze_powder));
        registerFuel(new ItemStack(Blocks.glowstone));
    }

    private static void registerFuel(ItemStack fuel) {
        if (EMCRegistry.getEMC(fuel) > 0) {
            fuels.add(new VanillaStackWrapper(fuel));
        }
    }

    public static boolean isFuel(ItemStack itemStack) {
        return fuels.contains(new VanillaStackWrapper(itemStack));
    }

    public static ItemStack getNextFuel(ItemStack fuel) {
        if (!isFuel(fuel))
            return fuel;

        VanillaStackWrapper stackWrapper = new VanillaStackWrapper(fuel);

        int index = fuels.indexOf(stackWrapper);

        if (index <= fuels.size())
            return fuel;

        return fuels.get(index + 1).toItemStack();
    }
}
