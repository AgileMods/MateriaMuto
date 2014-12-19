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
package net.pixelight.materiamuto.common.network.packet.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.pixelight.materiamuto.common.lib.LibKey;
import net.pixelight.materiamuto.api.IKeyBound;

public class MessageKeyPressed implements IMessage, IMessageHandler<MessageKeyPressed, IMessage> {

    private byte keyPressed;

    public MessageKeyPressed() {

    }

    public MessageKeyPressed(LibKey key) {
        if (key == LibKey.CHARGE) {
            this.keyPressed = (byte) LibKey.CHARGE.ordinal();
        } else if (key == LibKey.MODE) {
            this.keyPressed = (byte) LibKey.MODE.ordinal();
        } else if (key == LibKey.RELEASE) {
            this.keyPressed = (byte) LibKey.RELEASE.ordinal();
        } else {
            this.keyPressed = (byte) LibKey.UNKNOWN.ordinal();
        }
    }


    @Override
    public void fromBytes(ByteBuf byteBuf) {
        this.keyPressed = byteBuf.readByte();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeByte(keyPressed);
    }

    @Override
    public IMessage onMessage(MessageKeyPressed messageKeyPressed, MessageContext messageContext) {
        EntityPlayer entityPlayer = messageContext.getServerHandler().playerEntity;

        if (entityPlayer != null && entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem()
                .getItem() instanceof IKeyBound) {
            if (messageKeyPressed.keyPressed == LibKey.CHARGE.ordinal()) {
                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem())
                        .doKeyAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), LibKey.CHARGE);
            } else if (messageKeyPressed.keyPressed == LibKey.MODE.ordinal()) {
                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem())
                        .doKeyAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), LibKey.MODE);
            } else if (messageKeyPressed.keyPressed == LibKey.RELEASE.ordinal()) {
                ((IKeyBound) entityPlayer.getCurrentEquippedItem().getItem())
                        .doKeyAction(entityPlayer, entityPlayer.getCurrentEquippedItem(), LibKey.RELEASE);
            }
        }
        return null;
    }
}
