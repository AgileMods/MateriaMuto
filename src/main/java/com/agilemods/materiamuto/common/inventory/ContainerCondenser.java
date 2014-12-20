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
package com.agilemods.materiamuto.common.inventory;

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

        this.addSlotToContainer(new Slot(tileCondenser, TileCondenser.TARGET_SLOT, 12, 6));

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
