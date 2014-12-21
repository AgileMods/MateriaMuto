package com.agilemods.materiamuto;

import com.agilemods.materiamuto.client.core.handlers.ItemTooltipEventHandler;
import com.agilemods.materiamuto.client.core.handlers.KeyInputEventHandler;
import com.agilemods.materiamuto.client.core.settings.Keybindings;
import com.agilemods.materiamuto.client.model.importer.TechneModelLoader;
import com.agilemods.materiamuto.client.render.item.RenderItemCondenser;
import com.agilemods.materiamuto.client.render.tile.RenderTileCondenser;
import com.agilemods.materiamuto.common.core.MMBlocks;
import com.agilemods.materiamuto.common.tile.TileCondenser;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        MinecraftForge.EVENT_BUS.register(new ItemTooltipEventHandler());

        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
        ClientRegistry.registerKeyBinding(Keybindings.charge);
        ClientRegistry.registerKeyBinding(Keybindings.mode);
        ClientRegistry.registerKeyBinding(Keybindings.release);
        ClientRegistry.registerKeyBinding(Keybindings.toggle);

        AdvancedModelLoader.registerModelHandler(new TechneModelLoader());

        ClientRegistry.bindTileEntitySpecialRenderer(TileCondenser.class, new RenderTileCondenser());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MMBlocks.condenser), new RenderItemCondenser());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
