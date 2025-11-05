package com.gearforge.common.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.gearforge.GearForgeMod;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GearForgeMod.MODID);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
