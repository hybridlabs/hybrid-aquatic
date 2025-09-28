package dev.hybridlabs.aquatic.world.gen.structure

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.aquatic.world.gen.structure.SpawnModifier
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.structure.BuiltinStructures
import java.util.function.Supplier

val structureModifiers = mapOf(
    BuiltinStructures.SHIPWRECK to
            Supplier {
                SpawnModifier(
                    Services.PLATFORM.getMobCategoryByName("SHARK"),
                    listOf(
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.HOUND_SHARK.get(), 1, 1, 4)
                    )
                )
            },

    BuiltinStructures.RUINED_PORTAL_OCEAN to
            Supplier {
                SpawnModifier(
                    Services.PLATFORM.getMobCategoryByName("SHARK"),
                    listOf(
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.GREAT_WHITE_SHARK.get(), 1, 1, 4)
                    )
                )
            },

    BuiltinStructures.OCEAN_RUIN_WARM to
            Supplier {
                SpawnModifier(
                    Services.PLATFORM.getMobCategoryByName("SHARK"),
                    listOf(
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.TIGER_SHARK.get(), 1, 1, 4)
                    )
                )
            }
)