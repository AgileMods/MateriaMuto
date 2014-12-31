package com.agilemods.materiamuto.common.emc;

import com.agilemods.materiamuto.MateriaMuto;
import com.agilemods.materiamuto.api.IRecipeScanner;
import com.agilemods.materiamuto.api.wrapper.IStackWrapper;
import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.agilemods.materiamuto.common.emc.recipe.VanillaCraftingScanner;
import com.agilemods.materiamuto.common.emc.recipe.VanillaSmeltingScanner;
import com.agilemods.materiamuto.common.emc.recipe.compat.ic2.IC2CraftingScanner;
import com.agilemods.materiamuto.common.emc.recipe.compat.ic2.IC2MetalFormerScanner;
import com.agilemods.materiamuto.common.emc.recipe.compat.thaumcraft.ThaumcraftCraftingScanner;
import com.agilemods.materiamuto.common.emc.recipe.compat.thaumcraft.ThaumcraftCrucibleScanner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EMCRegistry {

    private static Map<VanillaStackWrapper, Double> emcMapping = Maps.newHashMap();
    private static Map<String, Double> genericEmcMapping = Maps.newHashMap();

    private static Set<VanillaStackWrapper> blacklist = Sets.newHashSet();

    private static List<IRecipeScanner> recipeScanners = Lists.newArrayList();

    private static Set<IStackWrapper> tempBlacklist = Sets.newHashSet();

    private static final EMCDelegate emcDelegate = new EMCDelegate();

    public static void blacklist(VanillaStackWrapper VanillaStackWrapper) {
        blacklist.add(VanillaStackWrapper);
    }

    private static boolean canDisable = false;

    public static void addRecipeScanner(IRecipeScanner recipeScanner) {
        boolean canScan = !canDisable;
        if (canDisable) {
            MateriaMuto.configuration.get("recipeScanner", recipeScanner.getClass().getSimpleName(), true).getBoolean(true);
            MateriaMuto.configuration.save();
        }

        if (canScan) {
            recipeScanners.add(recipeScanner);
            recipeScanner.scan();
        }
    }

    public static void reset() {
        emcMapping.clear();
        genericEmcMapping.clear();
        blacklist.clear();
        recipeScanners.clear();
        tempBlacklist.clear();
        canDisable = false;
    }

    private static void determineEMC(VanillaStackWrapper stackWrapper) {
        if (getEMC(stackWrapper, false) <= 0) {
            EMCRegistry.tempBlacklist.add(stackWrapper);

            double emc = 0;

            for (IRecipeScanner recipeScanner : recipeScanners) {
                double subEmc = recipeScanner.getEMC(emcDelegate, stackWrapper);
                if (emc == 0 || subEmc < emc) {
                    emc = subEmc;
                }
            }

            EMCRegistry.setEMC(stackWrapper, emc, false);

            EMCRegistry.tempBlacklist.remove(stackWrapper);
        }
    }

    public static void setEMC(Fluid fluid, double value) {
        setGenericEMC(fluid.getName(), value);
    }

    public static double getEMC(Fluid fluid) {
        return getGenericEMC(fluid.getName());
    }

    public static void setGenericEMC(String ident, double value) {
        genericEmcMapping.put(ident, value);
    }

    public static double getGenericEMC(String ident) {
        Double value = genericEmcMapping.get(ident);
        return value == null ? 0 : value;
    }

    public static void setEMC(Block block, double value) {
        setEMC(new VanillaStackWrapper(block), value, false);
    }

    public static void setEMC(Item item, double value) {
        setEMC(new VanillaStackWrapper(item), value, false);
    }

    public static void setEMC_wild(Block block, double value) {
        setEMC(new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE), value);
    }

    public static void setEMC_wild(Item item, double value) {
        setEMC(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE), value);
    }

    public static void setEMC(ItemStack itemStack, double value) {
        setEMC(new VanillaStackWrapper(itemStack), value, false);
    }

    public static void setEMC(VanillaStackWrapper stackWrapper, double value, boolean force) {
        if (!blacklist.contains(stackWrapper)) {
            if (force) {
                emcMapping.remove(stackWrapper);
            }
            emcMapping.put(stackWrapper, value);
        }
    }

    public static double getEMC(Object object) {
        if (object instanceof Block) {
            return getEMC((Block) object);
        }
        if (object instanceof Item) {
            return getEMC((Item) object);
        }
        if (object instanceof ItemStack) {
            return getEMC((ItemStack) object);
        }
        if (object instanceof VanillaStackWrapper) {
            return getEMC((VanillaStackWrapper) object, true);
        }
        return 0;
    }

    public static double getEMC(Block block) {
        return getEMC(new VanillaStackWrapper(block), true);
    }

    public static double getEMC(Item item) {
        return getEMC(new VanillaStackWrapper(item), true);
    }

    public static double getEMC(ItemStack itemStack) {
        return getEMC(new VanillaStackWrapper(itemStack), true);
    }

    public static double getEMC(VanillaStackWrapper stackWrapper, boolean scan) {
        if (!tempBlacklist.contains(stackWrapper) && !blacklist.contains(stackWrapper)) {

            tempBlacklist.add(stackWrapper);
            Double value = emcMapping.get(stackWrapper);
            if (scan) {
                if (value == null || Double.isNaN(value) || value <= 0) {
                    determineEMC(stackWrapper);
                    value = getEMC(stackWrapper, false);
                }
            }
            tempBlacklist.remove(stackWrapper);

            if (value == null || Double.isNaN(value)) {
                return 0;
            } else {
                return value;
            }
        } else {
            return 0;
        }
    }

    public static void initialize() {
        initializeBlacklist();

        initializeLazyValues();
        initializeLazyFluidValues();

        addRecipeScanner(new VanillaCraftingScanner());
        addRecipeScanner(new VanillaSmeltingScanner());

        // Global flag to determine if recipe scanner should be checked against config
        canDisable = true;

        if (Loader.isModLoaded("IC2")) {
            addRecipeScanner(new IC2CraftingScanner());
            addRecipeScanner(new IC2MetalFormerScanner());
        }

        if (Loader.isModLoaded("Thaumcraft")) {
            addRecipeScanner(new ThaumcraftCraftingScanner());
            addRecipeScanner(new ThaumcraftCrucibleScanner());
        }

        addFinalValues();
    }

    private static void initializeBlacklist() {
        if (Loader.isModLoaded("IC2")) {
            blacklist.add(new VanillaStackWrapper(GameRegistry.findItem("IC2", "itemToolForgeHammer")));
            blacklist.add(new VanillaStackWrapper(GameRegistry.findItem("IC2", "itemToolCutter")));
        }
    }

    private static void initializeLazyValues() {
        setEMC(Blocks.brown_mushroom, 32);
        setEMC(Blocks.cactus, 8);
        setEMC(Blocks.dragon_egg, 262144);
        setEMC(Blocks.end_stone, 4);
        setEMC(Blocks.grass, 1);
        setEMC(Blocks.gravel, 4);
        setEMC(Blocks.ice, 1);
        setEMC(Blocks.mossy_cobblestone, 145);
        setEMC(Blocks.mycelium, 1);
        setEMC(Blocks.netherrack, 1);
        setEMC(Blocks.obsidian, 64);
        setEMC(Blocks.packed_ice, 4);
        setEMC(Blocks.pumpkin, 144);
        setEMC(Blocks.red_mushroom, 32);
        setEMC(Blocks.snow, 1);
        setEMC(Blocks.soul_sand, 49);
        setEMC(Blocks.torch, 9);
        setEMC(Blocks.vine, 8);
        setEMC(Blocks.waterlily, 16);
        setEMC(Blocks.web, 12);
        setEMC(Items.apple, 128);
        setEMC(Items.beef, 64);
        setEMC(Items.blaze_powder, 768);
        setEMC(Items.blaze_rod, 1536);
        setEMC(Items.bone, 144);
        setEMC(Items.carrot, 64);
        setEMC(Items.chicken, 64);
        setEMC(Items.clay_ball, 16);
        setEMC(Items.diamond, 8192);
        setEMC(Items.diamond_horse_armor, 40960);
        setEMC(Items.egg, 32);
        setEMC(Items.emerald, 16384);
        setEMC(Items.enchanted_book, 2048);
        setEMC(Items.ender_pearl, 1024);
        setEMC(Items.feather, 48);
        setEMC(Items.filled_map, 1472);
        setEMC(Items.flint, 4);
        setEMC(Items.ghast_tear, 4096);
        setEMC(Items.glowstone_dust, 384);
        setEMC(Items.gold_ingot, 2048);
        setEMC(Items.golden_horse_armor, 1024);
        setEMC(Items.gunpowder, 192);
        setEMC(Items.iron_horse_armor, 1280);
        setEMC(Items.iron_ingot, 256);
        setEMC(Items.leather, 64);
        setEMC(Items.milk_bucket, 833);
        setEMC(Items.melon, 16);
        setEMC(Items.nether_star, 139264);
        setEMC(Items.nether_wart, 24);
        setEMC(Items.porkchop, 64);
        setEMC(Items.quartz, 256);
        setEMC(Items.record_11, 2048);
        setEMC(Items.record_13, 2048);
        setEMC(Items.record_blocks, 2048);
        setEMC(Items.record_cat, 2048);
        setEMC(Items.record_chirp, 2048);
        setEMC(Items.record_far, 2048);
        setEMC(Items.record_mall, 2048);
        setEMC(Items.record_mellohi, 2048);
        setEMC(Items.record_stal, 2048);
        setEMC(Items.record_strad, 2048);
        setEMC(Items.record_wait, 2048);
        setEMC(Items.record_ward, 2048);
        setEMC(Items.redstone, 64);
        setEMC(Items.reeds, 32);
        setEMC(Items.rotten_flesh, 32);
        setEMC(Items.saddle, 192);
        setEMC(Items.slime_ball, 32);
        setEMC(Items.snowball, 1);
        setEMC(Items.spider_eye, 128);
        setEMC(Items.stick, 4);
        setEMC(Items.string, 12);
        setEMC(Items.wheat, 24);
        setEMC(Items.wheat_seeds, 16);
        setEMC_wild(Blocks.cobblestone, 1);
        setEMC_wild(Blocks.deadbush, 1);
        setEMC_wild(Blocks.dirt, 1);
        setEMC_wild(Blocks.double_plant, 32);
        setEMC_wild(Blocks.leaves2, 1);
        setEMC_wild(Blocks.leaves, 1);
        setEMC_wild(Blocks.log2, 32);
        setEMC_wild(Blocks.log, 32);
        setEMC_wild(Blocks.red_flower, 16);
        setEMC_wild(Blocks.sand, 1);
        setEMC_wild(Blocks.sapling, 32);
        setEMC_wild(Blocks.tallgrass, 1);
        setEMC_wild(Blocks.yellow_flower, 16);
        setEMC_wild(Items.coal, 128);
        setEMC_wild(Items.fish, 64);
        setEMC_wild(Items.potato, 64);

        // Dye Handling
        for (int i = 0; i < 16; i++) {
            double emc = 16;

            if (i == 3) {
                emc = 128;
            } else if (i == 4) {
                emc = 864;
            } else if (i == 15) {
                emc = 48;
            }

            setEMC(new ItemStack(Items.dye, 1, i), emc);
        }

        addOreDictionaryValue("ingotCopper", 85);
        addOreDictionaryValue("ingotTin", 256);
        addOreDictionaryValue("ingotLead", 256);
        addOreDictionaryValue("ingotSilver", 512);

        // Also add ore dictionary tags
        ImmutableSet<VanillaStackWrapper> immutableSet = ImmutableSet.copyOf(emcMapping.keySet());
        for (VanillaStackWrapper VanillaStackWrapper : immutableSet) {
            double emc = getEMC(VanillaStackWrapper);
            ItemStack itemStack = VanillaStackWrapper.toItemStack();

            for (int id : OreDictionary.getOreIDs(itemStack)) {
                String name = OreDictionary.getOreName(id);
                for (ItemStack oreStack : OreDictionary.getOres(name)) {
                    setEMC(oreStack, emc);
                }
            }
        }
    }

    private static void addOreDictionaryValue(String oreTag, double value) {
        for (ItemStack itemStack : OreDictionary.getOres(oreTag)) {
            setEMC(itemStack, value);
        }
    }

    private static void initializeLazyFluidValues() {
        setEMC(FluidRegistry.WATER, 1);
        setEMC(FluidRegistry.LAVA, 64);
    }

    private static void addFinalValues() {
        // Fluid handling
        for (Item item : GameData.getItemRegistry().typeSafeIterable()) {
            LinkedList<ItemStack> subItems = Lists.newLinkedList();

            try {
                item.getSubItems(item, item.getCreativeTab(), subItems);
            } catch (Exception ignore) {}

            for (ItemStack itemStack : subItems) {
                if (FluidContainerRegistry.isContainer(itemStack)) {
                    FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(itemStack);
                    ItemStack empty = FluidContainerRegistry.drainFluidContainer(itemStack);

                    if (fluidStack != null && empty != null) {
                        setEMC(itemStack, getEMC(empty) + getEMC(fluidStack.getFluid()));
                    }
                }
            }
        }

        // Stone brick handling
        double stoneBrickEmc = getEMC(Blocks.stonebrick);
        for (int i = 1; i < BlockStoneBrick.field_150141_b.length; i++) {
            setEMC(new ItemStack(Blocks.stonebrick, 1, i), stoneBrickEmc);
        }

        // Anvil handling
        double anvilEmc = getEMC(new ItemStack(Blocks.anvil, 1, 0));
        setEMC(new ItemStack(Blocks.anvil, 1, 1), anvilEmc * 0.66D);
        setEMC(new ItemStack(Blocks.anvil, 1, 2), anvilEmc * 0.33D);

        // Name tag
        double string = getEMC(Items.string);
        double paper = getEMC(Items.paper);
        setEMC(Items.name_tag, string + paper);

        if (Loader.isModLoaded("Thaumcraft")) {
            setEMC(GameRegistry.findItem("Thaumcraft", "ItemThaumonomicon"), getEMC(Blocks.bookshelf));
        }
    }
}
