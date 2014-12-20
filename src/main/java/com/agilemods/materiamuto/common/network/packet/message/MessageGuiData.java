package com.agilemods.materiamuto.common.network.packet.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class MessageGuiData implements IMessage, IMessageHandler<MessageGuiData, IMessage> {

    public int id;

    public double data;

    public MessageGuiData() {

    }

    public MessageGuiData(int id, double data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
        buf.writeDouble(data);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        id = buf.readInt();
        data = buf.readDouble();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IMessage onMessage(MessageGuiData message, MessageContext ctx) {
        EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();
        if (entityPlayer != null) {
            Container openContainer = entityPlayer.openContainer;
            if (openContainer instanceof IGuiData) {
                ((IGuiData) openContainer).handleData(message.id, message.data);
            }
        }
        return null;
    }

    public static interface IGuiData {
        public void handleData(int id, double data);
    }
}
