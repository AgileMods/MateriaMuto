package com.agilemods.materiamuto.client.render.item;

import net.minecraft.item.ItemStack;
import com.agilemods.materiamuto.client.lib.Model;
import com.agilemods.materiamuto.client.lib.Texture;
import com.agilemods.materiamuto.client.render.item.prefab.MMItemRenderer;

public class RenderItemCondenser extends MMItemRenderer {

    @Override
    public Texture getTexture(ItemStack stack) {
        return Texture.CONDENSER;
    }

    @Override
    public Model getModel(ItemStack stack) {
        return Model.CONDENSER;
    }
}