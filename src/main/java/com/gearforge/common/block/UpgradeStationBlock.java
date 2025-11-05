package com.gearforge.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.Block;

import com.gearforge.common.block.entity.UpgradeStationBlockEntity;

public class UpgradeStationBlock extends Block {
    public UpgradeStationBlock(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide) return InteractionResult.SUCCESS;
        var be = world.getBlockEntity(pos);
        if (be instanceof UpgradeStationBlockEntity) {
            player.openMenu((UpgradeStationBlockEntity) be);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }
}
