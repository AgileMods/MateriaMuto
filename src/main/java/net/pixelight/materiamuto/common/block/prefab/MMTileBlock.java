package net.pixelight.materiamuto.common.block.prefab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.pixelight.materiamuto.common.core.MMTab;
import net.pixelight.materiamuto.common.tile.prefab.TileMM;

/**
 * @author dmillerw
 */
public abstract class MMTileBlock extends BlockContainer {

    public MMTileBlock(Material material) {
        super(material);
        if (registerInCreative()) {
            setCreativeTab(MMTab.INSTANCE);
        }
    }

    public MMTileBlock(Material material, float hardness, float resistance) {
        this(material);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity != null && tileEntity instanceof TileMM) ((TileMM) tileEntity).onBlockBroken();
        super.breakBlock(world, x, y, z, block, meta);
    }

    @SideOnly(Side.CLIENT)
    public abstract boolean useCustomRender();

    @SideOnly(Side.CLIENT)
    public abstract void registerBlockIcons(IIconRegister iconRegister);

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return Blocks.stone.getIcon(0, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isOpaqueCube() {
        return !this.useCustomRender();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean renderAsNormalBlock() {
        return !this.useCustomRender();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType() {
        return this.useCustomRender() ? -1 : 0;
    }

    boolean registerInCreative() {
        return true;
    }
}
