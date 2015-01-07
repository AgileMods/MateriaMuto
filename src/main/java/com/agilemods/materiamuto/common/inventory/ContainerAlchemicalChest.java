package com.agilemods.materiamuto.common.inventory;

import com.agilemods.materiamuto.common.tile.TileAlchemicalChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerAlchemicalChest extends Container {

    private EntityPlayer entityPlayer;

    private TileAlchemicalChest tileAlchemicalChest;

    public ContainerAlchemicalChest(EntityPlayer entityPlayer, TileAlchemicalChest tileAlchemicalChest) {
        this.entityPlayer = entityPlayer;
        this.tileAlchemicalChest = tileAlchemicalChest;

        tileAlchemicalChest.openInventory();

        int j;
        int k;
        for (j = 0; j < 8; ++j) {
            for (k = 0; k < 13; ++k) {
                this.addSlotToContainer(new Slot(tileAlchemicalChest, k + j * 13, 12 + k * 18, 5 + j * 18));
            }
        }

        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(entityPlayer.inventory, k + j * 9 + 9, 48 + k * 18, 152 + j * 18));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(entityPlayer.inventory, j, 48 + j * 18, 210));
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer entityPlayer) {
        super.onContainerClosed(entityPlayer);
        tileAlchemicalChest.closeInventory();
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
