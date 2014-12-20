package com.agilemods.materiamuto.common.item;

import com.agilemods.materiamuto.common.item.prefab.MMSubItem;

public class ItemMatter extends MMSubItem {

    public static final String[] MATTER = new String[]{"dark", "red"};

    public ItemMatter() {
        super();
        setHasSubtypes(true);
    }

    @Override
    public String[] getNames() {
        return MATTER;
    }

    @Override
    public String getIconPrefix() {
        return "matter/";
    }
}
