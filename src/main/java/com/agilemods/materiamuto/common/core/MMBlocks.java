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
