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
package com.agilemods.materiamuto.common.block;

import com.agilemods.materiamuto.common.block.prefab.MMTileBlock;
import com.agilemods.materiamuto.common.network.GuiHandler;
import com.agilemods.materiamuto.common.tile.TileCondenser;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class BlockCondenser extends MMTileBlock {

    public static final float OFFSET = 0.3125F;

    public BlockCondenser() {
        super(Material.iron, 2F, 2F);
        setBlockBounds(0F, 00F, 0F, 1F, 1F, 1F);
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end) {
        TileCondenser tile = (TileCondenser) world.getTileEntity(x, y, z);
        if (tile != null) {
            double temp = this.maxY;
            this.maxY -= (tile.getAnimationInterpolation() * OFFSET);
            MovingObjectPosition mob = super.collisionRayTrace(world, x, y, z, start, end);
            this.maxY = temp;
            return mob;
        } else {
            return super.collisionRayTrace(world, x, y, z, start, end);
        }
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        TileCondenser tile = (TileCondenser) world.getTileEntity(x, y, z);
        if (tile != null) {
            return AxisAlignedBB.getBoundingBox((double) x + this.minX, (double) y + this.minY, (double) z + this.minZ, (double) x + this.maxX,
                                                (double) y + this.maxY - (tile.getAnimationInterpolation() * OFFSET), (double) z + this.maxZ);
        } else {
            return super.getCollisionBoundingBoxFromPool(world, x, y, z);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        TileCondenser tile = (TileCondenser) world.getTileEntity(x, y, z);
        if (tile != null) {
            return AxisAlignedBB.getBoundingBox((double) x + this.minX, (double) y + this.minY, (double) z + this.minZ, (double) x + this.maxX,
                                                (double) y + this.maxY - (tile.getAnimationInterpolation() * OFFSET), (double) z + this.maxZ);
        } else {
            return super.getCollisionBoundingBoxFromPool(world, x, y, z);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float fx, float fy, float fz) {
        if (!world.isRemote) {
            GuiHandler.Type.GUI_CONDENSER.openGui(entityPlayer, x, y, z);
        }
        return true;
    }

    @Override
    public boolean useCustomRender() {
        return true;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileCondenser();
    }
}
