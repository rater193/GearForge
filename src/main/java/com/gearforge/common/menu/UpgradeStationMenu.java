package com.gearforge.common.menu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.entity.player.Inventory;
import net.minecraft.entity.player.Player;

public class UpgradeStationMenu extends AbstractContainerMenu {
    public UpgradeStationMenu(int id, Inventory inv) {
        super(com.gearforge.common.init.ModMenus.UPGRADE_STATION_MENU.get(), id);
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }
}
