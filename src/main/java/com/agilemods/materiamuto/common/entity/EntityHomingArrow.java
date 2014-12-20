package com.agilemods.materiamuto.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntityHomingArrow extends EntityArrow {

    public EntityHomingArrow(World world) {
        super(world);
    }

    public EntityHomingArrow(World world, EntityLivingBase entityLivingBase, float par3) {
        super(world, entityLivingBase, par3);
    }
}
