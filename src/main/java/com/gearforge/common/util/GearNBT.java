package com.gearforge.common.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.ListTag;

public class GearNBT {
    public static final String TAG = "gearforge";

    public static CompoundTag getOrCreateTag(ItemStack stack) {
        CompoundTag root = stack.getTag();
        if (root == null) {
            root = new CompoundTag();
            stack.setTag(root);
        }
        if (!root.contains(TAG)) {
            root.put(TAG, new CompoundTag());
        }
        return root.getCompound(TAG);
    }

    public static boolean isUnlocked(ItemStack stack) {
        CompoundTag t = stack.getTag();
        if (t == null || !t.contains(TAG)) return false;
        CompoundTag gf = t.getCompound(TAG);
        return gf.getBoolean("unlocked");
    }

    public static void setUnlocked(ItemStack stack, boolean v) {
        CompoundTag gf = getOrCreateTag(stack);
        gf.putBoolean("unlocked", v);
    }

    public static long getXP(ItemStack stack) {
        CompoundTag gf = getOrCreateTag(stack);
        return gf.getLong("xp");
    }

    public static void addXP(ItemStack stack, long add) {
        CompoundTag gf = getOrCreateTag(stack);
        long xp = gf.getLong("xp");
        xp += add;
        gf.putLong("xp", xp);

        int level = gf.getInt("level");
        while (xp >= xpForLevel(level + 1)) {
            level++;
            gf.putInt("level", level);
            int gp = gf.getInt("gear_points");
            gf.putInt("gear_points", gp + 1);
        }
    }

    public static int getLevel(ItemStack stack) {
        CompoundTag gf = getOrCreateTag(stack);
        return gf.getInt("level");
    }

    public static int getGearPoints(ItemStack stack) {
        CompoundTag gf = getOrCreateTag(stack);
        return gf.getInt("gear_points");
    }

    public static void spendGearPoints(ItemStack stack, int amount) {
        CompoundTag gf = getOrCreateTag(stack);
        int gp = gf.getInt("gear_points");
        gp -= amount;
        if (gp < 0) gp = 0;
        gf.putInt("gear_points", gp);
    }

    public static ListTag getUpgrades(ItemStack stack) {
        CompoundTag gf = getOrCreateTag(stack);
        if (!gf.contains("upgrades")) {
            gf.put("upgrades", new ListTag());
        }
        return gf.getList("upgrades", 10);
    }

    public static void addUpgrade(ItemStack stack, CompoundTag upgradeTag) {
        CompoundTag gf = getOrCreateTag(stack);
        ListTag list = getUpgrades(stack);
        list.add(upgradeTag);
        gf.put("upgrades", list);
    }

    public static long xpForLevel(int level) {
        if (level <= 0) return 0;
        return 100 + (long) level * level * 20L;
    }
}
