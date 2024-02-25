package net.laser.elixir;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.laser.elixir.item.ModItems;
import net.laser.screen.AlchemicalApparatusScreen;
import net.laser.screen.ModScreenHandlers;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ElixirClient implements ClientModInitializer   {


    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.ALCHEMICAL_APPARATUS_SCREEN_HANDLER, AlchemicalApparatusScreen::new);
        ModItems.registerModItems();
    }
}
