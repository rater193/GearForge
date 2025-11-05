package com.gearforge.common.block.entity;

import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;

import com.gearforge.common.util.GearNBT;

public class GearInfuserBlockEntity extends BlockEntity {
    private final SimpleContainer inventory = new SimpleContainer(1);

    public GearInfuserBlockEntity(BlockPos pos, BlockState state) {
        super(com.gearforge.common.init.ModBlockEntities.GEAR_INFUSER_BE.get(), pos, state);
    }

    public ItemStack getItem(int slot) {
        return inventory.getItem(slot);
    }

    public void setItem(int slot, ItemStack stack) {
        inventory.setItem(slot, stack);
    }

    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack s = inventory.removeItemNoUpdate(slot);
        return s;
    }

    public void unlockLastInserted() {
        ItemStack s = inventory.getItem(0);
        if (!s.isEmpty()) {
            GearNBT.setUnlocked(s, true);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ContainerHelper.loadAllItems(tag, inventory);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ContainerHelper.saveAllItems(tag, inventory);
        return super.save(tag);
    }
}
