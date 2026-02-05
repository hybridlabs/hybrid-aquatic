package dev.hybridlabs.aquatic.world.gen.structure

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.structure.BuiltinStructures
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride.BoundingBoxType

val BuiltinSpawnModifiers: Set<SpawnModifier> = setOf(
    SpawnModifier(
        "shipwreck", BuiltinStructures.SHIPWRECK, BoundingBoxType.STRUCTURE, mapOf(
            "SHARK" to listOf(
                MobSpawnSettings.SpawnerData(
                    HybridAquaticEntityTypes.HOUND_SHARK.get(), 1, 1, 4
                )
            )
        )
    ), SpawnModifier(
        "ruined_portal_ocean", BuiltinStructures.RUINED_PORTAL_OCEAN, BoundingBoxType.STRUCTURE, mapOf(
            "SHARK" to listOf(
                MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.GREAT_WHITE_SHARK.get(), 1, 1, 4)
            )
        )
    ), SpawnModifier(
        "ocean_ruin_warm", BuiltinStructures.OCEAN_RUIN_WARM, BoundingBoxType.STRUCTURE, mapOf(
            "SHARK" to listOf(
                MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.SAND_TIGER_SHARK.get(), 1, 1, 4)
            )
        )
    )
)