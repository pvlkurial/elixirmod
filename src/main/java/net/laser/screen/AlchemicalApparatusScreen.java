package net.laser.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laser.elixir.Elixir;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AlchemicalApparatusScreen extends HandledScreen<AlchemicalApparatusScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Elixir.MOD_ID, "textures/gui/alchemical_apparatus_gui.png");
    public AlchemicalApparatusScreen(AlchemicalApparatusScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f,1f,1f,1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) /2;
        int y = (height - backgroundHeight) /2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        
        renderProgressArrow(context, x ,y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()){
            context.drawTexture(TEXTURE, x+100, y+40, 176, 0, 8, handler.getScaledProgress());
            // #30, 26:48       TEXTURE gets drawn at x,y ; u is offset; idk; width is pixel width; scaledprogress

        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
