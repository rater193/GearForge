package com.gearforge.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import com.gearforge.common.block.entity.GearInfuserBlockEntity;

public class GearInfuserBlock extends Block {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    public GearInfuserBlock(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide) return InteractionResult.SUCCESS;
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof GearInfuserBlockEntity infuser) {
            ItemStack held = player.getItemInHand(hand);
            if (!held.isEmpty()) {
                infuser.setItem(0, held.split(1));
                infuser.unlockLastInserted();
                infuser.setChanged();
                world.playSound(null, pos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
                return InteractionResult.CONSUME;
            } else {
                ItemStack result = infuser.removeItemNoUpdate(0);
                if (!result.isEmpty()) {
                    if (!player.addItem(result)) {
                        player.drop(result, false);
                    }
                }
                infuser.setChanged();
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GearInfuserBlockEntity(pos, state);
    }
}
