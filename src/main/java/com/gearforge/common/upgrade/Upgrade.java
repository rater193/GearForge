package com.gearforge.common.upgrade;

import net.minecraft.nbt.CompoundTag;

public class Upgrade {
    public enum Rarity { COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, WACKY }

    public final String id;
    public final String displayName;
    public final Rarity rarity;
    public final int cost;
    public final String description;

    public Upgrade(String id, String displayName, Rarity rarity, int cost, String description) {
        this.id = id;
        this.displayName = displayName;
        this.rarity = rarity;
        this.cost = cost;
        this.description = description;
    }

    public CompoundTag toTag() {
        CompoundTag t = new CompoundTag();
        t.putString("id", id);
        t.putString("name", displayName);
        t.putString("rarity", rarity.name());
        t.putInt("cost", cost);
        return t;
    }
}
