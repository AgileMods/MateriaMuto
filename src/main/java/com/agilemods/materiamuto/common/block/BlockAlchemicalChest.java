package com.agilemods.materiamuto.common.block;

import com.agilemods.materiamuto.common.block.prefab.MMTileBlock;
import com.agilemods.materiamuto.common.network.GuiHandler;
import com.agilemods.materiamuto.common.tile.TileAlchemicalChest;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAlchemicalChest extends MMTileBlock {

    public BlockAlchemicalChest() {
        super(Material.iron);

        setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz) {
        if (!world.isRemote) {
            TileAlchemicalChest tile = (TileAlchemicalChest) world.getTileEntity(x, y, z);
            if (tile != null) {
                GuiHandler.Type.GUI_ALCHEMICAL_CHEST.openGui(player, x, y, z);
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileAlchemicalChest();
    }

    @Override
    public boolean useCustomRender() {
        return true;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {

    }
}