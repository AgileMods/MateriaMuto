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
package net.pixelight.materiamuto.client.core.handlers;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.pixelight.materiamuto.client.core.settings.Keybindings;
import net.pixelight.materiamuto.common.lib.LibKey;
import net.pixelight.materiamuto.common.network.packet.PacketHandler;
import net.pixelight.materiamuto.common.network.packet.message.MessageKeyPressed;
import net.pixelight.materiamuto.api.IKeyBound;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

    private static LibKey getPressedKey() {
        if (Keybindings.charge.isPressed()) {
            return LibKey.CHARGE;
        } else if (Keybindings.mode.isPressed()) {
            return LibKey.MODE;
        } else if (Keybindings.release.isPressed()) {
            return LibKey.RELEASE;
        }
        return LibKey.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
        if (getPressedKey() == LibKey.UNKNOWN) {
            return;
        }

        if (FMLClientHandler.instance().getClient().inGameHasFocus) {
            if (FMLClientHandler.instance().getClientPlayerEntity() != null) {
                EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();
                if (entityPlayer.getCurrentEquippedItem() != null) {
                    ItemStack currentEquppedItem = entityPlayer.getCurrentEquippedItem();
                    if (currentEquppedItem.getItem() instanceof IKeyBound) {
                        if (entityPlayer.worldObj.isRemote) {
                            PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(getPressedKey()));
                        } else {
                            ((IKeyBound) currentEquppedItem.getItem()).doKeyAction(entityPlayer, currentEquppedItem, getPressedKey());
                        }
                    }
                }
            }
        }
    }

}
