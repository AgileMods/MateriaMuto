package com.agilemods.materiamuto.common.item;

import com.agilemods.materiamuto.common.item.prefab.MMSubItem;

public class ItemCovalenceDust extends MMSubItem {

    public static final String[] DUSTS = new String[]{"low", "medium", "high"};

    public ItemCovalenceDust() {
        super();
        setHasSubtypes(true);
    }

    @Override
    public String[] getNames() {
        return DUSTS;
    }

    @Override
    public String getIconPrefix() {
        return "covalence_dust";
    }
}
