package net.laser.elixir.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.laser.elixir.Elixir;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item REMNANT_SHARD = registerItem("remnant_shard", new Item(new FabricItemSettings()));
    public static final Item ARCANE_SHARD = registerItem("arcane_shard", new Item(new FabricItemSettings()));
    public static final Item VOID_SHARD = registerItem("void_shard", new Item(new FabricItemSettings()));
    public static final Item ESSENCE_PRIMA_MATERIA = registerItem("essence_prima_materia", new Item(new FabricItemSettings()));
    public static final Item EMPTY_VESSEL = registerItem("empty_vessel", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ARCANE_SHARD);

    }
    private static Item registerItem( String name, Item item){

            return Registry.register(Registries.ITEM, new Identifier(Elixir.MOD_ID, name), item);

    }
    public static void registerModItems() {
        Elixir.LOGGER.info("Registering mod items for " + Elixir.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup);

    }

}
