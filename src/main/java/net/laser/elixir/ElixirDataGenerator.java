package net.laser.elixir;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.laser.elixir.datagen.ModItemTagProvider;
import net.laser.elixir.item.ModItems;
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
	}

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
}
