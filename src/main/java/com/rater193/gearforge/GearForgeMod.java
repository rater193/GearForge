package com.rater193.gearforge;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GearForgeMod.MODID)
public class GearForgeMod {
    public static final String MODID = "gearforge";

    public GearForgeMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.register(bus);
        ModBlockEntities.register(bus);
        ModMenuTypes.register(bus);
        com.rater193.gearforge.upgrade.UpgradeRegistry.init();
        EventHandlers.register();
    }
}