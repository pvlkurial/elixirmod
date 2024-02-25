package net.laser.elixir.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.laser.elixir.Elixir;
import net.laser.elixir.block.custom.AlchemicalApparatusBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;

import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.minecraft.util.Identifier;


public class ModBlocks {
    public static final Block ALCHEMICAL_APPARATUS = registerBlock("alchemical_apparatus",
            new AlchemicalApparatusBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block PRIMA_MATERIA = registerBlock("prima_materia",
            new FlowerBlock(StatusEffects.DARKNESS, 5, FabricBlockSettings.copyOf(Blocks.ALLIUM).nonOpaque().noCollision()));
    public static final Block POTTED_PRIMA_MATERIA = Registry.register(Registries.BLOCK, new Identifier(Elixir.MOD_ID, "potted_prima_materia"),
            new FlowerPotBlock(PRIMA_MATERIA, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque().noCollision()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Elixir.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Elixir.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
}


