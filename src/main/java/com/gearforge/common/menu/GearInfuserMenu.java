package com.gearforge.common.menu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class GearInfuserMenu extends AbstractContainerMenu {
    protected final Inventory playerInventory;
    protected final ContainerLevelAccess access;

    public GearInfuserMenu(int id, Inventory playerInventory) {
        super(com.gearforge.common.init.ModMenus.GEAR_INFUSER_MENU.get(), id);
        this.playerInventory = playerInventory;
        this.access = ContainerLevelAccess.NULL;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
