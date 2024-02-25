package net.laser.elixir.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.laser.elixir.Elixir;
import net.laser.elixir.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<AlchemicalApparatusBlockEntity> ALCHEMICAL_APPARATUS_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Elixir.MOD_ID, "alchemical_apparatus"),
                    FabricBlockEntityTypeBuilder.create(AlchemicalApparatusBlockEntity::new,
                            ModBlocks.ALCHEMICAL_APPARATUS).build());

    public static void registerBlockEntities(){
            Elixir.LOGGER.info("Register entities for " + Elixir.MOD_ID);

    }
}
