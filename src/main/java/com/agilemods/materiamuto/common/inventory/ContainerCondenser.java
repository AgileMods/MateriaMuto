package com.agilemods.materiamuto.common.inventory;

import com.agilemods.materiamuto.common.inventory.slot.SlotHasEMC;
import com.agilemods.materiamuto.common.network.packet.PacketHandler;
import com.agilemods.materiamuto.common.network.packet.message.MessageGuiData;
import com.agilemods.materiamuto.common.tile.TileCondenser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import java.util.List;

public class ContainerCondenser extends Container implements MessageGuiData.IGuiData {

    public EntityPlayer entityPlayer;

    public TileCondenser tileCondenser;

    private double lastTargetEmc;
    private double lastEmc;

    public ContainerCondenser(EntityPlayer entityPlayer, TileCondenser tileCondenser) {
        this.entityPlayer = entityPlayer;
        this.tileCondenser = tileCondenser;

        int j;
        int k;
        for (j = 0; j < 7; ++j) {
            for (k = 0; k < 13; ++k) {
                this.addSlotToContainer(new Slot(tileCondenser, k + j * 13, 12 + k * 18, 27 + j * 18));
            }
        }

        this.addSlotToContainer(new SlotHasEMC(tileCondenser, TileCondenser.TARGET_SLOT, 12, 6));

        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(entityPlayer.inventory, k + j * 9 + 9, 47 + k * 18, 157 + j * 18));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(entityPlayer.inventory, j, 48 + j * 18, 215));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        if (tileCondenser.targetEmc != lastTargetEmc) {
            sendGuiData(0, tileCondenser.targetEmc);
            lastTargetEmc = tileCondenser.targetEmc;
        }

        if (tileCondenser.emc != lastEmc) {
            sendGuiData(1, tileCondenser.emc);
            lastEmc = tileCondenser.emc;
        }
    }

    private void sendGuiData(int id, double data) {
        for (ICrafting crafting : (List<ICrafting>) crafters) {
            PacketHandler.INSTANCE.sendTo(new MessageGuiData(id, data), (EntityPlayerMP) crafting);
        }
    }

    @Override
    public void handleData(int id, double data) {
        switch (id) {
            case 0:
                tileCondenser.targetEmc = data;
                break;
            case 1:
                tileCondenser.emc = data;
                break;
            default:
                break;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
