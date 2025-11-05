package com.gearforge.common.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.minecraftforge.eventbus.api.IEventBus;

import com.gearforge.GearForgeMod;
import com.gearforge.common.block.GearInfuserBlock;
import com.gearforge.common.block.UpgradeStationBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GearForgeMod.MODID);

    public static final RegistryObject<Block> GEAR_INFUSER = BLOCKS.register("gear_infuser", () ->
        new GearInfuserBlock(BlockBehaviour.Properties.of(Material.METAL).strength(3.5f).requiresCorrectToolForDrops())
    );

    public static final RegistryObject<Block> UPGRADE_STATION = BLOCKS.register("upgrade_station", () ->
        new UpgradeStationBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0f))
    );

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
