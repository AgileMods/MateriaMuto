package com.agilemods.materiamuto.common.item;

import com.agilemods.materiamuto.common.item.prefab.MMItem;

public class ItemElderKnowledge extends MMItem {

    public ItemElderKnowledge() {
        super();
        setMaxStackSize(1);
    }

    @Override
    public String getIcon() {
        return "elder_knowledge";
    }

}
