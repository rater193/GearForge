package com.gearforge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.gearforge.common.init.ModBlocks;
import com.gearforge.common.init.ModItems;
import com.gearforge.common.init.ModMenus;
import com.gearforge.common.handler.GearXpHandler;
import com.gearforge.common.upgrade.UpgradeRegistry;

/**
 * GearForge main mod class.
 */
@Mod(GearForgeMod.MODID)
public class GearForgeMod {
    public static final String MODID = "gearforge";

    public GearForgeMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register(bus);
        ModItems.register(bus);
        ModMenus.register(bus);

        bus.addListener(this::commonSetup);

        com.gearforge.common.handler.ModEventBus.register();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        UpgradeRegistry.registerDefaults();
    }
}
