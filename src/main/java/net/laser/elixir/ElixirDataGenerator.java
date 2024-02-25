package net.laser.elixir;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.laser.elixir.block.ModBlocks;
import net.laser.elixir.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ElixirDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(MyBlockLootTables::new);
		pack.addProvider(MyTagGenerator::new);
		pack.addProvider(MyModelGenerator::new);
	}
// TAG GENERATOR
	private static class MyTagGenerator extends FabricTagProvider.ItemTagProvider {
		public MyTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}
		private static final TagKey<Item> ELIXIR_SHARDS = TagKey.of(RegistryKeys.ITEM, new Identifier("elixir:elixir_shards"));

		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(ELIXIR_SHARDS)
					.add(ModItems.ARCANE_SHARD)
					.add(ModItems.VOID_SHARD)
					.add(ModItems.REMNANT_SHARD)
					.add(Items.BLUE_ORCHID)
					.addOptionalTag(ItemTags.ANVIL);

		}
	}
// LOOT TABLE GENERATOR
	private static class MyBlockLootTables extends FabricBlockLootTableProvider {
		public MyBlockLootTables(FabricDataOutput dataOutput) {
			super(dataOutput);
		}

		@Override
		public void generate() {
			addDrop(ModBlocks.PRIMA_MATERIA);
			addPottedPlantDrops(ModBlocks.POTTED_PRIMA_MATERIA);
			addDrop(ModBlocks.ALCHEMICAL_APPARATUS);
			addDrop(Elixir.ALCHEMY_MACHINE);
		}
	}

	// MODEL GENERATOR
	private static class MyModelGenerator extends FabricModelProvider {
		private MyModelGenerator(FabricDataOutput generator) {
			super(generator);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.PRIMA_MATERIA, ModBlocks.POTTED_PRIMA_MATERIA, BlockStateModelGenerator.TintType.NOT_TINTED);
			blockStateModelGenerator.registerSimpleCubeAll(Elixir.ALCHEMY_MACHINE);
			blockStateModelGenerator.registerSimpleState(ModBlocks.ALCHEMICAL_APPARATUS);
		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			itemModelGenerator.register(ModItems.ARCANE_SHARD, Models.GENERATED);
			itemModelGenerator.register(ModItems.VOID_SHARD, Models.GENERATED);
			itemModelGenerator.register(ModItems.REMNANT_SHARD, Models.GENERATED);
			itemModelGenerator.register(ModItems.EMPTY_VESSEL, Models.GENERATED);
			itemModelGenerator.register(ModItems.ESSENCE_PRIMA_MATERIA, Models.GENERATED);
		}
	}





}
