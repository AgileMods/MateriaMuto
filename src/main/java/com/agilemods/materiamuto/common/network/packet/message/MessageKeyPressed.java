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
