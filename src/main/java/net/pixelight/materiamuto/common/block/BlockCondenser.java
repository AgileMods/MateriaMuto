package net.pixelight.materiamuto.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pixelight.materiamuto.common.block.prefab.MMTileBlock;
import net.pixelight.materiamuto.common.network.GuiHandler;
import net.pixelight.materiamuto.common.tile.TileCondenser;

/**
 * @author dmillerw
 */
public class BlockCondenser extends MMTileBlock {

    public BlockCondenser() {
        super(Material.iron, 2F, 2F);
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
