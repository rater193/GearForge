package com.gearforge.common.handler;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.gearforge.common.util.GearNBT;

public class GearXpHandler {

    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            float dmg = event.getAmount();
            ItemStack weapon = player.getMainHandItem();
            if (!weapon.isEmpty() && GearNBT.isUnlocked(weapon)) {
                long xp = Math.max(1, (long) (dmg * 10));
                GearNBT.addXP(weapon, xp);
            }
        }
    }

    public static void onPlayerHurt(LivingHurtEvent event) {
        LivingEntity target = event.getEntity();
        if (target instanceof Player player) {
            float dmg = event.getAmount();
            for (ItemStack armor : player.getArmorSlots()) {
                if (!armor.isEmpty() && GearNBT.isUnlocked(armor)) {
                    long xp = Math.max(1, (long) (dmg * 8));
                    GearNBT.addXP(armor, xp);
                }
            }
        }
    }
}
