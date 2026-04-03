package dev.hybridlabs.aquatic.world.gen.structure

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.structure.BuiltinStructures
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride.BoundingBoxType

val BuiltinSpawnModifiers: Set<SpawnModifier> = setOf(
    SpawnModifier(
        "shipwreck", BuiltinStructures.SHIPWRECK, BoundingBoxType.STRUCTURE, mapOf(
            "HYBRID_AQUATIC_SHARK" to listOf(
                MobSpawnSettings.SpawnerData(
                    HAEntityTypes.GREAT_WHITE_SHARK.get(), 1, 1, 3
                )
            )
        )
    ),
    SpawnModifier(
        "shipwreck_beached", BuiltinStructures.SHIPWRECK_BEACHED, BoundingBoxType.STRUCTURE, mapOf(
            "HYBRID_AQUATIC_CRUSTACEAN" to listOf(
                MobSpawnSettings.SpawnerData(HAEntityTypes.COCONUT_CRAB.get(), 1, 1, 4
                )
            )
        )
    )
)