package com.gearforge.common.upgrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UpgradeRegistry {
    private static final List<Upgrade> UPGRADES = new ArrayList<>();
    private static final Random RAND = new Random();

    public static void registerDefaults() {
        UPGRADES.add(new Upgrade("dmg_plus_1", "Keen Edge", Upgrade.Rarity.COMMON, 1, "+1 flat damage"));
        UPGRADES.add(new Upgrade("dmg_plus_2", "Sharpness Attune", Upgrade.Rarity.UNCOMMON, 2, "+2 flat damage"));
        UPGRADES.add(new Upgrade("dmg_plus_5", "Rending Fury", Upgrade.Rarity.RARE, 3, "+5 flat damage"));

        UPGRADES.add(new Upgrade("armor_plus_1", "Reinforced Plating", Upgrade.Rarity.COMMON, 1, "+1 armor"));
        UPGRADES.add(new Upgrade("tough_plus_2", "Tempered Weave", Upgrade.Rarity.UNCOMMON, 2, "+2 toughness"));

        UPGRADES.add(new Upgrade("armor_pierce", "Armor Piercing Edge", Upgrade.Rarity.UNCOMMON, 2, "Ignores 20% of target armor"));

        UPGRADES.add(new Upgrade("lifesteal_small", "Leeching", Upgrade.Rarity.RARE, 3, "Small life steal on hit"));
        UPGRADES.add(new Upgrade("yeet_lunar", "Lunar Yeet", Upgrade.Rarity.WACKY, 4, "Yeet attacker to the moon (very silly!)"));

        for (int i = 0; i < 10; i++) {
            UPGRADES.add(new Upgrade("minor_stat_"+i, "Minor Blessing "+(i+1), Upgrade.Rarity.COMMON, 1, "+1 minor stat"));
        }
    }

    public static Upgrade randomUpgrade() {
        return UPGRADES.get(RAND.nextInt(UPGRADES.size()));
    }

    public static List<Upgrade> createRandomPack(int choices, int costBudget) {
        List<Upgrade> picks = new ArrayList<>();
        for (int i = 0; i < choices; i++) {
            picks.add(randomUpgrade());
        }
        return picks;
    }
}
