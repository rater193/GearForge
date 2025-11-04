package com.rater193.gearforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(GearForge.MOD_ID)
public class GearForge {
    public static final String MOD_ID = "gearforge";
    private static final Logger LOGGER = LoggerFactory.getLogger(GearForge.class);

    public GearForge(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing GearForge mod");
        
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        
        // Register the client setup method for client-side modloading
        modEventBus.addListener(this::clientSetup);
        
        // Register our mod's ModConfigSpec so that the config is loaded
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("GearForge common setup");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("GearForge client setup");
    }
}
