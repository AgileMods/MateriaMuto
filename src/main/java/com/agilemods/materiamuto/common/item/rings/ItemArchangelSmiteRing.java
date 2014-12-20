package com.agilemods.materiamuto.common.item.rings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.agilemods.materiamuto.common.entity.EntityHomingArrow;
import com.agilemods.materiamuto.common.item.prefab.MMItem;

public class ItemArchangelSmiteRing extends MMItem {

    public ItemArchangelSmiteRing() {
        super();
        setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        EntityHomingArrow arrow = new EntityHomingArrow(world, entityPlayer, 2.0F);
        if (!world.isRemote) {
            world.spawnEntityInWorld(arrow);
        }
        return itemStack;
    }

    @Override
    public String getIcon() {
        return "rings/archangel_smite";
    }
}
