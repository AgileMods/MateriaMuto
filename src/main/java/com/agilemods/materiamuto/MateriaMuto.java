package com.agilemods.materiamuto;

import com.agilemods.materiamuto.common.command.CommandEMC;
import com.agilemods.materiamuto.common.lib.LibMisc;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = LibMisc.MODID, name = LibMisc.MODNAME, version = LibMisc.VERSION, dependencies = LibMisc.DEPENDENCIES)
public class MateriaMuto {

    public static boolean neiLoaded = false;
    public static boolean thaumcraftLoaded = false;

    @Mod.Instance(LibMisc.MODID)
    public static MateriaMuto instance;

    @SidedProxy(serverSide = LibMisc.PROXY_COMMON, clientSide = LibMisc.PROXY_CLIENT)
    public static CommonProxy proxy;

    public static Configuration configuration;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        neiLoaded = Loader.isModLoaded("NotEnoughItems");
        thaumcraftLoaded = Loader.isModLoaded("Thaumcraft");

        configuration = new Configuration(event.getSuggestedConfigurationFile());
        configuration.load();

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandEMC());
    }
}
