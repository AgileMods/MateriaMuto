/**
 * This file is part of MateriaMuto, licensed under the MIT License (MIT).
 *
 * Copyright (c) AgileMods <http://www.agilemods.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
            List<Entity> entityList = worldObj.getEntitiesWithinAABB(
                    Entity.class,
                    AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1)
                            .expand(PUSH_RADIUS, PUSH_RADIUS, PUSH_RADIUS)
            );

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
