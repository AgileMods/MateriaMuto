package com.agilemods.materiamuto.common.tile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

import java.util.List;

public class TileInterdictionTorch extends TileEntity {

    public static final int PUSH_RADIUS = 5;

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            List<Entity> entityList = worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(PUSH_RADIUS, PUSH_RADIUS, PUSH_RADIUS));

            if (entityList != null && !entityList.isEmpty()) {
                Vec3 tileCoord = Vec3.createVectorHelper(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5);
                for (Entity entity : entityList) {
                    if (entity instanceof EntityPlayer || entity instanceof EntityItem) {
                        continue;
                    }

                    Vec3 mobCoord = Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ);
                    Vec3 direction = tileCoord.subtract(mobCoord).normalize();

                    entity.motionX = direction.xCoord;
                    entity.motionY = direction.zCoord;
                    entity.motionZ = direction.zCoord;
                }
            }
        }
    }
}
