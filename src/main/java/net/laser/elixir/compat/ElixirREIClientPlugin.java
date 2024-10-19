package net.laser.elixir.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.laser.elixir.block.ModBlocks;
import net.laser.elixir.recipe.AlchemicalApparatusRecipe;
import net.laser.screen.AlchemicalApparatusScreen;

public class ElixirREIClientPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.addWorkstations(AlchemicalApparatusCategory.ALCHEMICAL_APPARATUS,
                                EntryStacks.of(ModBlocks.ALCHEMICAL_APPARATUS));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(AlchemicalApparatusRecipe.class, AlchemicalApparatusRecipe.Type.INSTANCE,
                AlchemicalApparatusDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 30, 20, 25),
                AlchemicalApparatusScreen.class,
                AlchemicalApparatusCategory.ALCHEMICAL_APPARATUS);
    }
}
