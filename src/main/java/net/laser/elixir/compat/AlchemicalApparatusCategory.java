package net.laser.elixir.compat;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.laser.elixir.Elixir;
import net.laser.elixir.block.ModBlocks;
import net.laser.elixir.recipe.AlchemicalApparatusRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class AlchemicalApparatusCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE =
            new Identifier(Elixir.MOD_ID, "textures/gui/alchemical_apparatus_gui.png");

    public static final CategoryIdentifier<AlchemicalApparatusDisplay> ALCHEMICAL_APPARATUS =
            CategoryIdentifier.of(Elixir.MOD_ID, "alchemical_apparatus");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return ALCHEMICAL_APPARATUS;
    }

    @Override
    public Text getTitle() {
        return Text.literal("Alchemical Apparatus");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.ALCHEMICAL_APPARATUS.asItem().getDefaultStack());
    }
    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE,
                new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 11))
                .entries(display.getInputEntries().get(0)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 59))
                .markOutput().entries(display.getOutputEntries().get(0)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 50, startPoint.y + 59))
                .markOutput().entries(display.getOutputEntries().get(0)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 10, startPoint.y + 59))
                .markOutput().entries(display.getOutputEntries().get(0)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 10, startPoint.y + 50))
                .markOutput().entries(display.getOutputEntries().get(1)));
        return widgets;
    }
}
