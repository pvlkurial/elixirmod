package net.laser.elixir.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.laser.elixir.recipe.AlchemicalApparatusRecipe;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.RecipeEntry;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AlchemicalApparatusDisplay extends BasicDisplay {

    private RecipeEntry<AlchemicalApparatusRecipe> recipe;

    public AlchemicalApparatusDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
        super(inputs, outputs);

    }

    public AlchemicalApparatusDisplay(RecipeEntry<AlchemicalApparatusRecipe> recipe) {
        super(getInputList(recipe.value()), List.of(EntryIngredient.of(EntryStacks.of(recipe.value().getResult(null)))));
    }

    private static List<EntryIngredient> getInputList(AlchemicalApparatusRecipe recipe) {
        if(recipe == null) return Collections.emptyList();
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.getIngredients().get(0)));
        list.add(EntryIngredients.ofIngredient(recipe.getIngredients().get(1)));
        list.add(EntryIngredients.ofIngredient(recipe.getIngredients().get(2)));
        list.add(EntryIngredients.ofIngredient(recipe.getIngredients().get(3)));
        list.add(EntryIngredients.ofIngredient(recipe.getIngredients().get(4)));
        return list;
    }

    public int getCraftTime() {
        return 70;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return AlchemicalApparatusCategory.ALCHEMICAL_APPARATUS;
    }
}
