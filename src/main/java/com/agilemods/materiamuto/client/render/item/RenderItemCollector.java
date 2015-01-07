package com.agilemods.materiamuto.client.render.item;

import com.agilemods.materiamuto.client.lib.Model;
import com.agilemods.materiamuto.client.lib.Texture;
import com.agilemods.materiamuto.client.render.item.prefab.MMItemRenderer;
import net.minecraft.item.ItemStack;

public class RenderItemCollector extends MMItemRenderer {

    @Override
    public Texture getTexture(ItemStack stack) {
        switch (stack.getItemDamage()) {
            case 2:
                return Texture.COLLECTOR_MK3;
            case 1:
                return Texture.COLLECTOR_Mk2;
            default:
                return Texture.COLLECTOR_MK1;
        }
    }

    @Override
    public Model getModel(ItemStack stack) {
        return Model.COLLECTOR;
    }
}