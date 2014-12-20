package com.agilemods.materiamuto.common.network.packet.message;

import com.agilemods.materiamuto.api.IKeyBound;
import com.agilemods.materiamuto.common.lib.LibKey;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MessageKeyPressed implements IMessage, IMessageHandler<MessageKeyPressed, IMessage> {

    private LibKey keyPressed;

    public MessageKeyPressed() {

    }

    public MessageKeyPressed(LibKey key) {
        this.keyPressed = key;
    }


    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeByte(keyPressed.ordinal());
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        this.keyPressed = LibKey.values()[byteBuf.readByte()];
    }

    @Override
    public IMessage onMessage(MessageKeyPressed messageKeyPressed, MessageContext messageContext) {
        EntityPlayer entityPlayer = messageContext.getServerHandler().playerEntity;

        if (entityPlayer != null) {
            ItemStack itemStack = entityPlayer.getCurrentEquippedItem();
            if (itemStack != null && itemStack.getItem() instanceof IKeyBound && messageKeyPressed.keyPressed != LibKey.UNKNOWN) {
                ((IKeyBound) itemStack.getItem()).doKeyAction(entityPlayer, itemStack, messageKeyPressed.keyPressed);
            }
        }
        return null;
    }
}
