package com.agilemods.materiamuto.common.core.handlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class MaterialHandler {

    // Armor Materials
    public static ItemArmor.ArmorMaterial darkMatterArmorMaterial = EnumHelper.addArmorMaterial("DARK_MATTER", 16, new int[]{2, 6, 5, 2}, 18);
    public static ItemArmor.ArmorMaterial redMatterArmorMaterial = EnumHelper.addArmorMaterial("RED_MATTER", 16, new int[]{2, 6, 5, 2}, 18);
    public static ItemArmor.ArmorMaterial gemArmorMaterial = EnumHelper.addArmorMaterial("GEM", 16, new int[]{2, 6, 5, 2}, 18);

    // Tool Materials
    public static Item.ToolMaterial darkMatterToolMaterial = EnumHelper.addToolMaterial("DARK_MATTER", 3, 300, 6.2F, 2F, 0);
    public static Item.ToolMaterial redMatterToolMaterial = EnumHelper.addToolMaterial("RED_MATTER", 3, 300, 6.2F, 2F, 0);
}
