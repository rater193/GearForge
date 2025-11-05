package com.rater193.gearforge.network;

import com.rater193.gearforge.core.GearData;
import com.rater193.gearforge.upgrade.Upgrade;
import com.rater193.gearforge.upgrade.UpgradeRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SelectUpgradePacket {
    private final String upgradeId;

    public SelectUpgradePacket(String upgradeId) {
        this.upgradeId = upgradeId;
    }

    public static void encode(SelectUpgradePacket pkt, FriendlyByteBuf buf) {
        buf.writeUtf(pkt.upgradeId);
    }

    public static SelectUpgradePacket decode(FriendlyByteBuf buf) {
        return new SelectUpgradePacket(buf.readUtf(32767));
    }

    public static void handle(SelectUpgradePacket pkt, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;

            var stack = player.getMainHandItem();
            if (stack.isEmpty()) {
                player.sendSystemMessage(Component.literal("No item in hand to upgrade."));
                return;
            }

            if (!GearData.isUnlocked(stack)) {
                player.sendSystemMessage(Component.literal("Item is not unlocked for gear XP."));
                return;
            }

            UpgradeRegistry.get(pkt.upgradeId).ifPresent(up -> {
                if (!GearData.spendGearPoints(stack, up.cost)) {
                    player.sendSystemMessage(Component.literal("Not enough gear points."));
                    return;
                }
                up.applyTo(stack);
                player.sendSystemMessage(Component.literal("Applied upgrade: " + up.name));
            });
        });
        ctx.setPacketHandled(true);
    }
}