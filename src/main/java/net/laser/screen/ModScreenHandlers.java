package net.laser.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.laser.elixir.Elixir;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<AlchemicalApparatusScreenHandler> ALCHEMICAL_APPARATUS_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Elixir.MOD_ID, "alchemical_apparatus_screen"),
            new ExtendedScreenHandlerType<>(AlchemicalApparatusScreenHandler::new));

    public static void registerScreenHandlers(){
        Elixir.LOGGER.info("Registering screen handlers for " + Elixir.MOD_ID);
    }
}
