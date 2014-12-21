package com.agilemods.materiamuto.common.core;

import com.agilemods.materiamuto.common.tile.TileCondenser;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import com.agilemods.materiamuto.common.block.BlockCondenser;
import com.agilemods.materiamuto.common.block.BlockInterdictionTorch;
import com.agilemods.materiamuto.common.lib.LibBlockNames;
import com.agilemods.materiamuto.common.tile.TileInterdictionTorch;

public class MMBlocks {

    public static Block interdictionTorch;
    public static Block condenser;

    public static void init() {
        interdictionTorch = new BlockInterdictionTorch().setBlockName(LibBlockNames.INTERDICTION_TORCH);
        condenser = new BlockCondenser().setBlockName(LibBlockNames.CONDENSER);

        register(interdictionTorch, TileInterdictionTorch.class);
        register(condenser, TileCondenser.class);
    }

    private static void register(Block block) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
    }

    private static void register(Block block, Class<?> clazz) {
        if (ItemBlock.class.isAssignableFrom(clazz)) {
            GameRegistry.registerBlock(block, (Class<? extends ItemBlock>) clazz, block.getUnlocalizedName().replace("tile.", ""));
        } else {
            GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
            GameRegistry.registerTileEntity((Class<? extends TileEntity>) clazz, "materiamuto:" + block.getUnlocalizedName().replace("tile.", ""));
        }
    }

    private static void register(Block block, Class<? extends ItemBlock> itemBlock, Class<? extends TileEntity> tileEntity) {
        GameRegistry.registerBlock(block, itemBlock, block.getUnlocalizedName().replace("tile.", ""));
        GameRegistry.registerTileEntity(tileEntity, "materiamuto:" + block.getUnlocalizedName().replace("tile.", ""));
    }
}
