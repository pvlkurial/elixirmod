package net.laser.elixir.recipe;

import net.laser.elixir.Elixir;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes(){
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Elixir.MOD_ID, AlchemicalApparatusRecipe.Serializer.ID),
                AlchemicalApparatusRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Elixir.MOD_ID, AlchemicalApparatusRecipe.Type.ID),
                AlchemicalApparatusRecipe.Type.INSTANCE);
    }
}
