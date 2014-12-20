package com.agilemods.materiamuto.common.network.packet;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import com.agilemods.materiamuto.common.lib.LibMisc;
import com.agilemods.materiamuto.common.network.packet.message.MessageGuiData;
import com.agilemods.materiamuto.common.network.packet.message.MessageKeyPressed;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(LibMisc.MODID.toLowerCase());

    public static void init() {
        INSTANCE.registerMessage(MessageKeyPressed.class, MessageKeyPressed.class, 0, Side.SERVER);
        INSTANCE.registerMessage(MessageGuiData.class, MessageGuiData.class, 1, Side.CLIENT);
    }
}
