package com.agilemods.materiamuto.common.tile;

import com.agilemods.materiamuto.common.tile.prefab.TileMM;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

public class TileCollector extends TileMM {

    @SideOnly(Side.CLIENT)
    public int rotationTicker;

    public TileCollector() {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            rotationTicker = new Random().nextInt(5000);
        }
    }
}
