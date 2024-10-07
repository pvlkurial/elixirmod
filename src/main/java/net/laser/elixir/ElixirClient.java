package net.laser.elixir;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.laser.elixir.block.ModBlocks;
import net.laser.elixir.block.entity.ModBlockEntities;
import net.laser.elixir.block.entity.renderer.AlchemicalApparatusRenderer;
import net.laser.elixir.item.ModItems;
import net.laser.screen.AlchemicalApparatusScreen;
import net.laser.screen.ModScreenHandlers;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;


public class ElixirClient implements ClientModInitializer   {


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PRIMA_MATERIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PRIMA_MATERIA, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.ALCHEMICAL_APPARATUS_SCREEN_HANDLER, AlchemicalApparatusScreen::new);
        ModItems.registerModItems();

        BlockEntityRendererFactories.register(ModBlockEntities.ALCHEMICAL_APPARATUS_BLOCK_ENTITY, AlchemicalApparatusRenderer::new);

    }
}
