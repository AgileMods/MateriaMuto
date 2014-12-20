package com.agilemods.materiamuto;

import com.agilemods.materiamuto.common.core.MMBlocks;
import com.agilemods.materiamuto.common.core.MMEntities;
import com.agilemods.materiamuto.common.item.MMItems;
import com.agilemods.materiamuto.common.network.GuiHandler;
import com.agilemods.materiamuto.common.network.packet.PacketHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import com.agilemods.materiamuto.common.core.handlers.AlchemicalBagHandler;
import com.agilemods.materiamuto.common.emc.EMCRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        MMBlocks.init();
        MMItems.init();
        MMEntities.init();

        PacketHandler.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(MateriaMuto.instance, new GuiHandler());

        AlchemicalBagHandler.register();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {
        EMCRegistry.initialize();
    }
}
