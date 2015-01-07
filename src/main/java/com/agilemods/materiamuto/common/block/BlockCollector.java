package com.agilemods.materiamuto.common.block;

import com.agilemods.materiamuto.common.block.prefab.MMTileBlock;
import com.agilemods.materiamuto.common.tile.TileCollector;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class BlockCollector extends MMTileBlock {

    public BlockCollector() {
        super(Material.rock);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        for (int i=0; i<3; i++) {
            list.add(new ItemStack(this, 1, i));
        }
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
        return new TileCollector();
    }
}
