package com.agilemods.materiamuto.client.core.handlers;

import com.agilemods.materiamuto.api.IKeyBound;
import com.agilemods.materiamuto.client.core.settings.Keybindings;
import com.agilemods.materiamuto.common.lib.LibKey;
import com.agilemods.materiamuto.common.network.packet.PacketHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import com.agilemods.materiamuto.common.network.packet.message.MessageKeyPressed;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

    private static LibKey getPressedKey() {
        if (Keybindings.charge.getIsKeyPressed()) {
            return LibKey.CHARGE;
        } else if (Keybindings.mode.getIsKeyPressed()) {
            return LibKey.MODE;
        } else if (Keybindings.release.getIsKeyPressed()) {
            return LibKey.RELEASE;
        } else if (Keybindings.toggle.getIsKeyPressed()) {
            return LibKey.TOGGLE;
        }
        return LibKey.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
        LibKey keyPress = getPressedKey();

        if (keyPress == LibKey.UNKNOWN) {
            return;
        }

        if (FMLClientHandler.instance().getClient().inGameHasFocus) {
            if (FMLClientHandler.instance().getClientPlayerEntity() != null) {
                EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();
                if (entityPlayer.getCurrentEquippedItem() != null) {
                    ItemStack currentEquppedItem = entityPlayer.getCurrentEquippedItem();
                    if (currentEquppedItem.getItem() instanceof IKeyBound) {
                        if (entityPlayer.worldObj.isRemote) {
                            PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(keyPress));
                        }
                    }
                }
            }
        }
    }
}
