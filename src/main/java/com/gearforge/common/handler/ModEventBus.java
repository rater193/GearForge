package com.gearforge.common.handler;

import net.minecraftforge.common.MinecraftForge;

public class ModEventBus {
    public static void register() {
        MinecraftForge.EVENT_BUS.addListener(GearXpHandler::onLivingHurt);
        MinecraftForge.EVENT_BUS.addListener(GearXpHandler::onPlayerHurt);
    }
}
