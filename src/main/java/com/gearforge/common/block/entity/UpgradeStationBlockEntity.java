package com.gearforge.common.block.entity;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import com.gearforge.common.init.ModBlockEntities;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import com.gearforge.common.menu.UpgradeStationMenu;

public class UpgradeStationBlockEntity extends BlockEntity implements MenuProvider {
    public UpgradeStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.UPGRADE_STATION_BE.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Upgrade Station");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new UpgradeStationMenu(id, inv);
    }
}
