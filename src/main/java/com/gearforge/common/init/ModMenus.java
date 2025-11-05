package com.gearforge.common.init;

import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.gearforge.GearForgeMod;
import com.gearforge.common.menu.GearInfuserMenu;
import com.gearforge.common.menu.UpgradeStationMenu;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, GearForgeMod.MODID);

    public static final RegistryObject<MenuType<GearInfuserMenu>> GEAR_INFUSER_MENU =
        MENUS.register("gear_infuser_menu", () -> new MenuType<>(GearInfuserMenu::new));
    public static final RegistryObject<MenuType<UpgradeStationMenu>> UPGRADE_STATION_MENU =
        MENUS.register("upgrade_station_menu", () -> new MenuType<>(UpgradeStationMenu::new));

    public static void register(IEventBus bus) {
        MENUS.register(bus);
    }
}
