package com.rater193.gearforge.upgrade;

import com.google.gson.*;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Tiny JSON loader for upgrades. Loads packaged sample JSON files from resources.
 */
public class UpgradeRegistry {
    private static final Map<String, Upgrade> UPGRADES = new LinkedHashMap<>();

    public static void init() {
        try {
            var cl = UpgradeRegistry.class.getClassLoader();
            var names = List.of(
                    "data/gearforge/upgrades/add_damage_2.json",
                    "data/gearforge/upgrades/add_armor_3.json",
                    "data/gearforge/upgrades/armor_pierce_5.json",
                    "data/gearforge/upgrades/yeet_to_moon.json",
                    "data/gearforge/upgrades/crit_boost_1.json"
            );
            Gson gson = new Gson();
            for (String name : names) {
                try (var is = cl.getResourceAsStream(name)) {
                    if (is == null) continue;
                    var reader = new InputStreamReader(is);
                    JsonObject obj = gson.fromJson(reader, JsonObject.class);
                    String id = obj.get("id").getAsString();
                    String nm = obj.get("name").getAsString();
                    String desc = obj.get("description").getAsString();
                    int tier = obj.get("tier").getAsInt();
                    int cost = obj.get("cost").getAsInt();
                    String effect = obj.get("effect").getAsString();
                    Upgrade u = new Upgrade(id, nm, desc, tier, cost, effect);
                    UPGRADES.put(id, u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Collection<Upgrade> all() {
        return UPGRADES.values();
    }

    public static Optional<Upgrade> get(String id) {
        return Optional.ofNullable(UPGRADES.get(id));
    }

    public static List<Upgrade> chooseForCost(int cost) {
        Random rng = new Random();
        List<Upgrade> pool = new ArrayList<>(UPGRADES.values());
        if (cost <= 1) {
            pool.removeIf(u -> u.tier > 2);
            Collections.shuffle(pool, rng);
            return pool.subList(0, Math.min(3, pool.size()));
        } else {
            pool.removeIf(u -> u.tier > 4);
            Collections.shuffle(pool, rng);
            return pool.subList(0, Math.min(4, pool.size()));
        }
    }
}