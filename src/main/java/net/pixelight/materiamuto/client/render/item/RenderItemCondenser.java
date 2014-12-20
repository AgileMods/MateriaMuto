package net.pixelight.materiamuto.client.render.item;

import net.minecraft.item.ItemStack;
import net.pixelight.materiamuto.client.lib.Model;
import net.pixelight.materiamuto.client.lib.Texture;
import net.pixelight.materiamuto.client.render.item.prefab.MMItemRenderer;

/**
 * @author dmillerw
 */
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