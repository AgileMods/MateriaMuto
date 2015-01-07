package com.agilemods.materiamuto.client.render.item;

import com.agilemods.materiamuto.client.lib.Model;
import com.agilemods.materiamuto.client.lib.Texture;
import com.agilemods.materiamuto.client.render.item.prefab.MMItemRenderer;
import net.minecraft.item.ItemStack;

public class RenderItemAlchemicalChest extends MMItemRenderer {

    @Override
    public Texture getTexture(ItemStack stack) {
        return Texture.ALCHEMICAL_CHEST;
    }

    @Override
    public Model getModel(ItemStack stack) {
        return Model.ALCHEMICAL_CHESET;
    }
}