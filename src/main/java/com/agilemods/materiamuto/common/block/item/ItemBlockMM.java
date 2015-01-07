package com.agilemods.materiamuto.common.block.item;

import com.agilemods.materiamuto.common.block.prefab.MMSubBlock;
import com.agilemods.materiamuto.common.block.prefab.MMTileBlock;
import com.agilemods.materiamuto.common.tile.prefab.TileMM;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemBlockMM extends ItemBlock {

    public ItemBlockMM(Block block) {
        super(block);
        if (block instanceof MMSubBlock) {
            setHasSubtypes(true);
        }
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String name = super.getUnlocalizedName(stack);
        if (field_150939_a instanceof MMSubBlock) {
            name = name + "." + ((MMSubBlock) field_150939_a).getNameForType(stack.getItemDamage());
        }
        return name;
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);

        if (field_150939_a instanceof MMTileBlock) {
            if (result) {
                TileMM tile = (TileMM) world.getTileEntity(x, y, z);
                if (tile != null) {
                    tile.orientation = get2DRotation(player);
                    world.markBlockForUpdate(x, y, z);
                }
            }
        }

        return result;
    }

    private static ForgeDirection get2DRotation(EntityLivingBase entityLivingBase) {
        int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            return ForgeDirection.NORTH;
        }

        if (l == 1) {
            return ForgeDirection.EAST;
        }

        if (l == 2) {
            return ForgeDirection.SOUTH;
        }

        if (l == 3) {
            return ForgeDirection.WEST;
        }

        return ForgeDirection.UNKNOWN;
    }
}
