package com.agilemods.materiamuto.common.item;

import com.agilemods.materiamuto.common.item.prefab.MMSubItem;

public class ItemAlchemicalFuel extends MMSubItem {

    public static final String[] FUELS = new String[]{"alchemical", "mobius", "aeternalis"};

    public ItemAlchemicalFuel() {
        super();
        setHasSubtypes(true);
    }

    @Override
    public String[] getNames() {
        return FUELS;
    }

    @Override
    public String getIconPrefix() {
        return "fuels";
    }
}
