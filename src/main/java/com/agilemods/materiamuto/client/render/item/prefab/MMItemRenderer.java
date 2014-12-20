package com.agilemods.materiamuto.client.render.item.prefab;

import com.agilemods.materiamuto.client.lib.Texture;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import com.agilemods.materiamuto.client.lib.Model;
import org.lwjgl.opengl.GL11;

public abstract class MMItemRenderer implements IItemRenderer {

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();

        if (type == ItemRenderType.INVENTORY) {
            GL11.glRotated(-90, 0, 1, 0);
        }

        getTexture(item).bindTexture();
        getModel(item).renderAll();

        GL11.glPopMatrix();
    }

    public abstract Texture getTexture(ItemStack stack);

    public abstract Model getModel(ItemStack stack);
}