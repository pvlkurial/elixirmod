package net.laser.elixir.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.laser.elixir.Elixir;
import net.laser.elixir.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup ELIXIR_GROUP = FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.ESSENCE_PRIMA_MATERIA))
                    .displayName(Text.translatable("itemGroup.elixir.elixir_group"))
                    .entries((displayContext, entries) -> {

                        entries.add(ModItems.ESSENCE_PRIMA_MATERIA);
                        entries.add(ModItems.VOID_SHARD);
                        entries.add(ModItems.ARCANE_SHARD);
                        entries.add(ModItems.VOID_SHARD);
                        entries.add(ModBlocks.ALCHEMICAL_APPARATUS);

            }).build();

    public static void registerItemGroups(){
        Registry.register(Registries.ITEM_GROUP, new Identifier(Elixir.MOD_ID, "elixir_group"), ELIXIR_GROUP);

    }

}
