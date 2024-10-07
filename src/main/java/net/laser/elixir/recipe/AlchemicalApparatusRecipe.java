package net.laser.elixir.recipe;

import net.minecraft.inventory.Inventory;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.io.Serial;
import java.util.List;

public class AlchemicalApparatusRecipe implements Recipe<SimpleInventory> {

    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public AlchemicalApparatusRecipe(List<Ingredient> ingredients, ItemStack itemStack ){
        this.output = itemStack;
        this.recipeItems = ingredients;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(world.isClient()){
            return false;
        }
        // this checks the input slots
       return recipeItems.get(0).test(inventory.getStack(0));

    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer(){
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<AlchemicalApparatusRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "alchemical_apparatus";

    }

    public static class Serializer implements RecipeSerializer<AlchemicalApparatusRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "alchemical_apparatus";
        public static final Codec<AlchemicalApparatusRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 9).fieldOf("ingredients").forGetter(AlchemicalApparatusRecipe::getIngredients),
               ItemStack.RECIPE_RESULT_CODEC.fieldOf("output").forGetter(r -> r.output)
        ).apply(in, AlchemicalApparatusRecipe::new));

        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return Codecs.validate(Codecs.validate(
                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "Recipe has too many ingredients!") : DataResult.success(list)
            ), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
        }
        @Override
        public Codec<AlchemicalApparatusRecipe> codec() {
            return CODEC;
        }

        @Override
        public AlchemicalApparatusRecipe read(PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++){
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new AlchemicalApparatusRecipe(inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, AlchemicalApparatusRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for(Ingredient ingredient : recipe.getIngredients()){
                ingredient.write(buf);
            }
            buf.writeItemStack(recipe.getResult(null));
        }
    }
}

