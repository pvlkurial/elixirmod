package net.laser.elixir.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.laser.elixir.block.ModBlocks;
import net.laser.elixir.block.entity.ModBlockEntities;
import net.laser.elixir.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(ModBlocks.ALCHEMICAL_APPARATUS);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ARCANE_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.REMNANT_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPTY_VESSEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.VOID_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.ESSENCE_PRIMA_MATERIA, Models.GENERATED);
    }
}
