package com.agilemods.materiamuto.common.item;

import com.agilemods.materiamuto.common.item.baubles.ItemBlackHoleBand;
import com.agilemods.materiamuto.common.item.baubles.ItemBodyStone;
import com.agilemods.materiamuto.common.item.baubles.ItemEvertideAmulet;
import com.agilemods.materiamuto.common.item.baubles.ItemIgnitionBand;
import com.agilemods.materiamuto.common.item.baubles.ItemLifeStone;
import com.agilemods.materiamuto.common.item.baubles.ItemMindStone;
import com.agilemods.materiamuto.common.item.baubles.ItemRepairTalisman;
import com.agilemods.materiamuto.common.item.baubles.ItemSoulStone;
import com.agilemods.materiamuto.common.item.baubles.ItemSwiftWolfsRendingGale;
import com.agilemods.materiamuto.common.item.baubles.ItemVolcanicAmulet;
import com.agilemods.materiamuto.common.item.baubles.ItemZeroRing;
import com.agilemods.materiamuto.common.item.rings.ItemArchangelSmiteRing;
import com.agilemods.materiamuto.common.item.rings.ItemIronBand;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import com.agilemods.materiamuto.common.item.rings.ItemHarvestGodessRing;
import com.agilemods.materiamuto.common.lib.LibItemNames;

public class MMItems {

    public static Item dust;
    public static Item alchemicalFuel;
    public static Item kleinStar;
    public static Item matter;
    public static Item elderKnowledge;
    public static Item enchantScroll;
    public static Item alchemicalBag;

    public static Item blackHoleRing;
    public static Item archangelSmiteRing;
    public static Item repairTalismanAmulet;
    public static Item bodyStone;
    public static Item soulStone;
    public static Item lifeStone;
    public static Item mindStone;
    public static Item volcanicAmulet;
    public static Item evertideAmulet;
    public static Item ignitionRing;
    public static Item swiftwolfsRendingGale;
    public static Item zeroRing;
    public static Item harvestGodessRing;
    public static Item ironBand;

    public static void init() {
        dust = new ItemCovalenceDust().setUnlocalizedName(LibItemNames.COVALENCE_DUST);
        alchemicalFuel = new ItemAlchemicalFuel().setUnlocalizedName(LibItemNames.ALCHEMICAL_FUEL);
        kleinStar = new ItemKleinStar().setUnlocalizedName(LibItemNames.KLEIN_STAR);
        matter = new ItemMatter().setUnlocalizedName(LibItemNames.MATTER);
        elderKnowledge = new ItemElderKnowledge().setUnlocalizedName(LibItemNames.ELDER_KNOWLEDGE);
        enchantScroll = new ItemEnchantmentScroll().setUnlocalizedName(LibItemNames.ENCHANT_SCROLL);
        alchemicalBag = new ItemAlchemicalBag().setUnlocalizedName(LibItemNames.ALCHEMICAL_BAG);

        // Rings & Amulets
        blackHoleRing = new ItemBlackHoleBand().setUnlocalizedName(LibItemNames.BLACK_HOLE_RING);
        archangelSmiteRing = new ItemArchangelSmiteRing().setUnlocalizedName(LibItemNames.ARCHANGEL_SMITE_RING);
        repairTalismanAmulet = new ItemRepairTalisman().setUnlocalizedName(LibItemNames.REPAIR_TALISMAN);
        bodyStone = new ItemBodyStone().setUnlocalizedName(LibItemNames.BODY_STONE);
        soulStone = new ItemSoulStone().setUnlocalizedName(LibItemNames.SOUL_STONE);
        lifeStone = new ItemLifeStone().setUnlocalizedName(LibItemNames.LIFE_STONE);
        mindStone = new ItemMindStone().setUnlocalizedName(LibItemNames.MIND_STONE);
        volcanicAmulet = new ItemVolcanicAmulet().setUnlocalizedName(LibItemNames.VOLCANIC_AMULET);
        evertideAmulet = new ItemEvertideAmulet().setUnlocalizedName(LibItemNames.EVERTIDE_AMULET);
        ignitionRing = new ItemIgnitionBand().setUnlocalizedName(LibItemNames.IGNITION_RING);
        swiftwolfsRendingGale = new ItemSwiftWolfsRendingGale().setUnlocalizedName(LibItemNames.SWRG);
        zeroRing = new ItemZeroRing().setUnlocalizedName(LibItemNames.ZERO_RING);
        harvestGodessRing = new ItemHarvestGodessRing().setUnlocalizedName(LibItemNames.HARVEST_RING);
        ironBand = new ItemIronBand().setUnlocalizedName(LibItemNames.IRON_BAND);

        registerItem(dust);
        registerItem(alchemicalFuel);
        registerItem(kleinStar);
        registerItem(matter);
        registerItem(elderKnowledge);
        registerItem(enchantScroll);
        registerItem(alchemicalBag);

        // Rings & Amulets
        registerItem(blackHoleRing);
        registerItem(archangelSmiteRing);
        registerItem(repairTalismanAmulet);
        registerItem(bodyStone);
        registerItem(soulStone);
        registerItem(lifeStone);
        registerItem(mindStone);
        registerItem(volcanicAmulet);
        registerItem(evertideAmulet);
        registerItem(ignitionRing);
        registerItem(swiftwolfsRendingGale);
        registerItem(zeroRing);
        registerItem(harvestGodessRing);
        registerItem(ironBand);

    }

    public static void registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
    }
}
