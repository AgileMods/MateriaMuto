package com.agilemods.materiamuto.common.item.prefab;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import com.agilemods.materiamuto.common.core.MMTab;
import com.agilemods.materiamuto.common.lib.LibMisc;

public class MMItem extends Item {

    private IIcon icon;

    public MMItem() {
        super();
        if (registerInCreative()) {
            setCreativeTab(MMTab.INSTANCE);
        }
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return icon;
    }

    @Override
    public void registerIcons(IIconRegister register) {
        if (!getIcon().isEmpty()) {
            icon = register.registerIcon(LibMisc.RESOURCE_PREFIX + getIcon());
        }
    }

    public String getIcon() {
        return "";
    }

    boolean registerInCreative() {
        return true;
    }


}
