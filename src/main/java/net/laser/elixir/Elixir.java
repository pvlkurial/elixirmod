package net.laser.elixir;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.laser.elixir.item.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Elixir implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static String MOD_ID = "elixir";
    public static final Logger LOGGER = LoggerFactory.getLogger("elixir");


	public static final Block ALCHEMY_MACHINE = new Block(FabricBlockSettings.create().strength(4.0f));
	@Override
	public void onInitialize() {

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registries.BLOCK, new Identifier("elixir", "alchemy_machine"), ALCHEMY_MACHINE);
		Registry.register(Registries.ITEM, new Identifier("elixir", "alchemy_machine"), new BlockItem(ALCHEMY_MACHINE, new FabricItemSettings()));
		LOGGER.info("Hello Fabric world!");
	}
}