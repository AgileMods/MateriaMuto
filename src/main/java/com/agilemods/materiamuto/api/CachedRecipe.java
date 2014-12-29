package com.agilemods.materiamuto.api;

import com.agilemods.materiamuto.api.wrapper.IStackWrapper;
import com.agilemods.materiamuto.api.wrapper.OreStackWrapper;
import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CachedRecipe {

    public Map<IStackWrapper, Integer> components = Maps.newHashMap();

    public CachedRecipe(List list) {
        this(list.toArray(new Object[list.size()]));
    }

    public CachedRecipe(Object[] array) {
        Map<IStackWrapper, Integer> map = Maps.newHashMap();

        for (Object object : array) {
            IStackWrapper stackWrapper = null;
            int count;

            if (object instanceof Block) {
                stackWrapper = new VanillaStackWrapper((Block) object);
            } else if (object instanceof Item) {
                stackWrapper = new VanillaStackWrapper((Item) object);
            } else if (object instanceof ItemStack) {
                stackWrapper = new VanillaStackWrapper((ItemStack) object);
            } else if (object instanceof ArrayList<?>) {
                if (((ArrayList)object).size() > 0) {
                    stackWrapper = new OreStackWrapper((ItemStack) ((ArrayList)object).get(0));
                }
            }

            if (!map.containsKey(stackWrapper)) {
                count = 1;
            } else {
                count = map.get(stackWrapper) + 1;
            }

            map.put(stackWrapper, count);
        }

        components.putAll(map);
    }

    public CachedRecipe(ItemStack[] array) {
        Map<IStackWrapper, Integer> map = Maps.newHashMap();

        for (ItemStack itemStack : array) {
            VanillaStackWrapper stackWrapper = new VanillaStackWrapper(itemStack);
            if (components.containsKey(stackWrapper)) {
                components.put(stackWrapper, components.get(stackWrapper) + 1);
            } else {
                components.put(stackWrapper, 1);
            }
        }

        components.putAll(map);
    }

    public double getEMC() {
        double emc = 0;
        for (Map.Entry<IStackWrapper, Integer> entry : components.entrySet()) {
            double subEmc = entry.getKey() == null ? 0 : entry.getKey().getEMC();
            emc += subEmc * entry.getValue();
        }
        return emc;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (Map.Entry<IStackWrapper, Integer> entry : components.entrySet()) {
            if (entry.getKey() == null) continue;
            stringBuilder.append("{stack: " + entry.getKey().toString() + " count: " + entry.getValue() + "}\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
