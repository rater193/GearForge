package com.gearforge.common.init;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.gearforge.GearForgeMod;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import com.gearforge.common.block.entity.GearInfuserBlockEntity;
import com.gearforge.common.block.entity.UpgradeStationBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GearForgeMod.MODID);

    public static final RegistryObject<BlockEntityType<GearInfuserBlockEntity>> GEAR_INFUSER_BE =
        BLOCK_ENTITIES.register("gear_infuser_be", () -> BlockEntityType.Builder.of(GearInfuserBlockEntity::new, ModBlocks.GEAR_INFUSER.get()).build(null));

    public static final RegistryObject<BlockEntityType<UpgradeStationBlockEntity>> UPGRADE_STATION_BE =
        BLOCK_ENTITIES.register("upgrade_station_be", () -> BlockEntityType.Builder.of(UpgradeStationBlockEntity::new, ModBlocks.UPGRADE_STATION.get()).build(null));

    public static void register(net.minecraftforge.eventbus.api.IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
