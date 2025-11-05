package com.rater193.gearforge.network;

import com.rater193.gearforge.GearForgeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class ModNetwork {
    private static final String PROTOCOL_VERSION = "1";
    private static SimpleChannel CHANNEL;
    private static int id = 0;

    public static void init() {
        CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(GearForgeMod.MODID, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals);

        // register messages
        CHANNEL.registerMessage(id++, com.rater193.gearforge.network.SelectUpgradePacket.class,
                com.rater193.gearforge.network.SelectUpgradePacket::encode,
                com.rater193.gearforge.network.SelectUpgradePacket::decode,
                com.rater193.gearforge.network.SelectUpgradePacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

    public static void sendToServer(Object msg) {
        if (CHANNEL == null) return;
        CHANNEL.sendToServer(msg);
    }
}