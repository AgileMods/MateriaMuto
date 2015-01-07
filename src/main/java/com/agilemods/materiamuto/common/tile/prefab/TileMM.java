
package com.agilemods.materiamuto.common.tile.prefab;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileMM extends TileEntity {

    public ForgeDirection orientation = ForgeDirection.UNKNOWN;

    public void writeCustomNBT(NBTTagCompound nbtTagCompound) {

    }

    public void readCustomNBT(NBTTagCompound nbtTagCompound) {

    }

    public void onBlockBroken() {
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setByte("orientation", (byte) orientation.ordinal());
        writeCustomNBT(nbtTagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte("orientation"));
        readCustomNBT(nbtTagCompound);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
        worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
    }
}
