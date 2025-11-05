package com.rater193.gearforge.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rater193.gearforge.gui.UpgradeStationMenu;
import com.rater193.gearforge.upgrade.Upgrade;
import com.rater193.gearforge.upgrade.UpgradeRegistry;
import com.rater193.gearforge.network.ModNetwork;
import com.rater193.gearforge.network.SelectUpgradePacket;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import java.util.List;

/**
 * Simple Upgrade Station screen which draws three card-style slots and a Select button.
 * Sends a SelectUpgradePacket to the server when a card is chosen.
 */
public class UpgradeStationScreen extends AbstractContainerScreen<UpgradeStationMenu> {
    private static final ResourceLocation VANILLA_BG = new ResourceLocation("textures/gui/container/generic_54.png");

    public UpgradeStationScreen(UpgradeStationMenu menu, net.minecraft.world.entity.player.Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 256;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        // layout: display 3 cards horizontally
        int cards = 3;
        int cardW = 72;
        int cardH = 120;
        int spacing = 10;
        int startX = (this.width - (cards * cardW + (cards - 1) * spacing)) / 2;
        int top = (this.height - cardH) / 2 - 10;

        List<Upgrade> choices = UpgradeRegistry.chooseForCost(1); // placeholder: show 3 choices for cost 1

        for (int i = 0; i < cards; i++) {
            int x = startX + i * (cardW + spacing);
            int y = top;
            final int idx = i;
            final String upgradeId = (idx < choices.size() ? choices.get(idx).id : "");

            // Add a big select button at bottom of the card
            this.addRenderableWidget(new Button(x + 8, y + cardH - 28, cardW - 16, 20,
                    Component.literal("Select"), (btn) -> {
                        if (upgradeId == null || upgradeId.isEmpty()) {
                            Minecraft.getInstance().player.displayClientMessage(Component.literal("Nothing to select."), false);
                            return;
                        }
                        // send selection packet to server
                        ModNetwork.sendToServer(new SelectUpgradePacket(upgradeId));
                        // client feedback: close the screen to allow server-side message to appear in chat
                        this.minecraft.player.closeContainer();
                    }));
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        // draw some overlay text
        graphics.drawString(this.font, Component.literal("Choose an Upgrade"), this.leftPos + 8, this.topPos + 6, 0xFFFFFF);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        // draw vanilla container background (stretched to our imageWidth/imageHeight)
        Minecraft.getInstance().getTextureManager().bind(VANILLA_BG);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(VANILLA_BG, x, y, 0, 0, this.imageWidth, this.imageHeight);
        // draw three card rectangles on top
        int cards = 3;
        int cardW = 72;
        int cardH = 120;
        int spacing = 10;
        int startX = (this.width - (cards * cardW + (cards - 1) * spacing)) / 2;
        int top = (this.height - cardH) / 2 - 10;

        // get sample choices
        List<Upgrade> choices = UpgradeRegistry.chooseForCost(1);

        for (int i = 0; i < cards; i++) {
            int cx = startX + i * (cardW + spacing);
            int cy = top;
            // card background
            graphics.fill(cx, cy, cx + cardW, cy + cardH, 0xFFEEE4C8); // parchment-like
            // card frame
            graphics.drawRect(cx, cy, cx + cardW, cy + cardH, 0xFF8B5A9E);
            // draw title and description for the card
            String title = (i < choices.size() ? choices.get(i).name : "???");
            String desc = (i < choices.size() ? choices.get(i).description : "A mysterious upgrade.");
            graphics.drawString(this.font, Component.literal(title), cx + 6, cy + 6, 0x1F0830);
            // wrap brief desc to two lines
            graphics.drawString(this.font, Component.literal(desc), cx + 6, cy + 26, 0x2A1A3A);
            // perks line (simple example)
            graphics.drawString(this.font, Component.literal("Perks: grants stat bonus"), cx + 6, cy + 44, 0x3B2748);
            // cost label
            graphics.drawString(this.font, Component.literal("Cost: 1 GP"), cx + 6, cy + 58, 0x551111);
        }
    }
}