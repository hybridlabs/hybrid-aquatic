package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.aquatic.world.gen.biome.HABiomes
import dev.hybridlabs.aquatic.world.gen.feature.HAPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.placement.AquaticPlacements
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements
import net.minecraft.data.worldgen.placement.NetherPlacements
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.BiomeSpecialEffects
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.GenerationStep
import java.util.concurrent.CompletableFuture

class BiomeProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
) : FabricDynamicRegistryProvider(output, registriesFuture) {

    fun create(
        entries: Entries,
        temperature: Float,
        downfall: Float,
        waterColor: Int,
        waterFogColor: Int,
        extraSpawns: List<Pair<MobCategory, MobSpawnSettings.SpawnerData>> = ArrayList(),
        extraSpawnCosts: List<Triple<EntityType<*>, Double, Double>> = emptyList(),
        extraFeatures: (BiomeGenerationSettings.Builder.() -> Unit)? = null,
    ): Biome {
        val builder = BiomeGenerationSettings.Builder(
            entries.placedFeatures(),
            entries.configuredCarvers()
        )

        addStandardFeatures(builder)
        BiomeDefaultFeatures.addDefaultOres(builder)
        BiomeDefaultFeatures.addDefaultSoftDisks(builder)

        extraFeatures?.invoke(builder)

        return Biome.BiomeBuilder()
            .generationSettings(makeGenerationSettings(entries))
            .generationSettings(builder.build()).mobSpawnSettings(
                makeSpawnSettings(extraSpawns, extraSpawnCosts))

            .hasPrecipitation(true)
            .temperature(temperature)
            .downfall(downfall)
            .specialEffects(
                createStandardBiomeEffects()
                    .waterColor(waterColor)
                    .waterFogColor(waterFogColor)
                    .build()
            )
            .build()
    }

    fun makeGenerationSettings(entries: Entries): BiomeGenerationSettings {
        val builder = BiomeGenerationSettings.Builder(entries.placedFeatures(), entries.configuredCarvers())
        addStandardFeatures(builder)
        BiomeDefaultFeatures.addDefaultOres(builder)
        BiomeDefaultFeatures.addDefaultSoftDisks(builder)
        return builder.build()
    }

    fun makeSpawnSettings(
        extraSpawns: List<Pair<MobCategory, MobSpawnSettings.SpawnerData>>,
        extraSpawnCosts: List<Triple<EntityType<*>, Double, Double>>
    ): MobSpawnSettings {
        val builder = makeDefaultSpawnSettings()

        for ((category, spawner) in extraSpawns) {
            builder.addSpawn(category, spawner)
        }

        for ((entity, charge, budget) in extraSpawnCosts) {
            builder.addMobCharge(entity, charge, budget)
        }

        return builder.build()
    }

    fun makeDefaultSpawnSettings(): MobSpawnSettings.Builder {
        val spawnSettings = MobSpawnSettings.Builder()
        addDefaultAmbientSpawns(spawnSettings)
        addDefaultMonsterSpawns(spawnSettings)
        return spawnSettings
    }

    fun addStandardFeatures(builder: BiomeGenerationSettings.Builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder)
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder)
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder)
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder)
        BiomeDefaultFeatures.addDefaultSprings(builder)
        BiomeDefaultFeatures.addSurfaceFreezing(builder)
    }

    fun addDefaultAmbientSpawns(builder: MobSpawnSettings.Builder) {
        builder.addSpawn(MobCategory.AMBIENT, MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 8, 8))
        builder.addSpawn(
            MobCategory.UNDERGROUND_WATER_CREATURE,
            MobSpawnSettings.SpawnerData(EntityType.GLOW_SQUID, 5, 2, 5)
        )
    }

    fun addDefaultMonsterSpawns(builder: MobSpawnSettings.Builder) {
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 95, 4, 4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.SKELETON, 100, 4, 4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.CREEPER, 100, 4, 4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1))
    }

    fun createStandardBiomeEffects(): BiomeSpecialEffects.Builder {
        return BiomeSpecialEffects.Builder()
            .waterColor(0x3f76e4)
            .waterFogColor(0x50533)
            .fogColor(0xC0D8FF)
            .skyColor(0x78A7FF)
    }

    override fun configure(
        registries: HolderLookup.Provider,
        entries: Entries,
    ) {
        entries.add(
            HABiomes.TROPICAL_RIVER,
            create(
                entries,
                temperature = 0.95f,
                downfall = 0.9f,
                waterColor = 0x3A7A6A,
                waterFogColor = 0x4D7A60,
                listOf(
                    Pair(
                        MobCategory.CREATURE,
                        MobSpawnSettings.SpawnerData(EntityType.FROG, 1, 1, 2)
                    )
                )
            ) {
                addFeature(
                    GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                    entries.ref(MiscOverworldPlacements.FOREST_ROCK)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(AquaticPlacements.SEAGRASS_RIVER)
                )
            }
        )

        entries.add(
            HABiomes.CORAL_REEF,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x43D5EE,
                waterFogColor = 0x041F33,
                listOf(
                    Pair(
                        MobCategory.WATER_AMBIENT,
                        MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 3, 3, 6)
                    )
                )
            ) {
                addFeature(
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    entries.ref(HAPlacedFeatures.CORAL_MOUND)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HAPlacedFeatures.CORAL_LAYER)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HAPlacedFeatures.CORAL_REEF_VEGETATION)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(AquaticPlacements.SEAGRASS_WARM)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(AquaticPlacements.SEA_PICKLE)
                )
            }
        )

        entries.add(
            HABiomes.SEAGRASS_BED,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x43D5EE,
                waterFogColor = 0x3DB872,
                listOf(
                    Pair(
                        MobCategory.WATER_CREATURE,
                        MobSpawnSettings.SpawnerData(EntityType.DOLPHIN, 1, 1, 2)
                    ),
                    Pair(
                        MobCategory.WATER_AMBIENT,
                        MobSpawnSettings.SpawnerData(EntityType.PUFFERFISH, 1, 1, 3)
                    ),
                    Pair(
                        MobCategory.WATER_AMBIENT,
                        MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 8, 2, 4)
                    )
                )
            ) {
                addFeature(
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    entries.ref(HAPlacedFeatures.MOUND)
                )
                addFeature(
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    entries.ref(HAPlacedFeatures.SAND_CIRCLE)
                )
                addFeature(
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    entries.ref(HAPlacedFeatures.AERATED_SAND_CIRCLE)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(AquaticPlacements.SEAGRASS_WARM)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(AquaticPlacements.SEA_PICKLE)
                )
            }
        )

        entries.add(
            HABiomes.RED_MEADOW,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x43D5EE,
                waterFogColor = 0x041F33
            ) {
                addFeature(
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    entries.ref(HAPlacedFeatures.WHITE_MOUND)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HAPlacedFeatures.BLEACHED_REEF_VEGETATION)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HAPlacedFeatures.RED_MEADOW_VEGETATION)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(AquaticPlacements.SEA_PICKLE)
                )
            }
        )

        entries.add(
            HABiomes.DEEP_WARM_OCEAN,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x48B3C7,
                waterFogColor = 0x418794
            )
        )

        entries.add(
            HABiomes.TIDE_POOLS,
            create(
                entries,
                temperature = 1.1f,
                downfall = 0.6f,
                waterColor = 0x3FA7D6,
                waterFogColor = 0x2E5D73
            ) {
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HAPlacedFeatures.TIDE_POOLS)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(AquaticPlacements.SEA_PICKLE)
                )
            }
        )

        entries.add(
            HABiomes.BASALT_BEACH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x3D57D6,
                waterFogColor = 0x050533
            ) {
                addFeature(
                    GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                    entries.ref(NetherPlacements.SMALL_BASALT_COLUMNS)
                )
            }
        )

        entries.add(
            HABiomes.SULFURIC_CAVES,
            create(
                entries,
                temperature = 1.0f,
                downfall = 0.0f,
                waterColor = 0xc9b147,
                waterFogColor = 0xadb148
            )
        )

        entries.add(
            HABiomes.TRENCH,
            create(
                entries,
                temperature = 0.2f,
                downfall = 0.0f,
                waterColor = 0x1A4EB7,
                waterFogColor = 0x020217,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.VAMPIRE_SQUID.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.NAUTILUS.get(), 10, 1, 2)
                    ),

                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.RATFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SNAILFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.JOHN_DORY.get(), 8, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.VIPERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.DRAGONFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.TRIPOD_FISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.BARRELEYE.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SEA_ANGEL.get(), 3, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.COELACANTH.get(), 1, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SLICKHEAD.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.OARFISH.get(), 1, 1, 2)
                    ),

                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SIXGILL_SHARK.get(), 3, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.LANTERN_SHARK.get(), 5, 1, 2)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HAEntityTypes.GIANT_SQUID.get(), 0.9, 1.0),
                    Triple(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 0.15, 0.3),
                    Triple(HAEntityTypes.NAUTILUS.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.VAMPIRE_SQUID.get(), 0.1, 0.2),

                    Triple(HAEntityTypes.RATFISH.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.SNAILFISH.get(), 0.1, 0.7),
                    Triple(HAEntityTypes.ANGLERFISH.get(), 0.1, 0.3),
                    Triple(HAEntityTypes.FANGTOOTH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.VIPERFISH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.DRAGONFISH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.JOHN_DORY.get(), 0.1, 0.3),
                    Triple(HAEntityTypes.HATCHETFISH.get(), 0.1, 0.6),
                    Triple(HAEntityTypes.TRIPOD_FISH.get(), 0.1, 0.6),
                    Triple(HAEntityTypes.SEA_ANGEL.get(), 0.1, 0.25),
                    Triple(HAEntityTypes.BARRELEYE.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.COELACANTH.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.SLICKHEAD.get(), 0.1, 0.3),
                    Triple(HAEntityTypes.OARFISH.get(), 0.5, 0.8),

                    Triple(HAEntityTypes.SIXGILL_SHARK.get(), 0.4, 0.8),
                    Triple(HAEntityTypes.LANTERN_SHARK.get(), 0.1, 0.4),
                )
            )
        )

        entries.add(
            HABiomes.WARM_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x37808C,
                waterFogColor = 0x1b2447,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.VAMPIRE_SQUID.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.NAUTILUS.get(), 10, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.FIREFLY_SQUID.get(), 10, 1, 2)
                    ),

                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.RATFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SNAILFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.JOHN_DORY.get(), 8, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.VIPERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.DRAGONFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.TRIPOD_FISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.BARRELEYE.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.COELACANTH.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.OARFISH.get(), 1, 1, 2)
                    ),


                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SIXGILL_SHARK.get(), 3, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.FRILLED_SHARK.get(), 3, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.LANTERN_SHARK.get(), 5, 1, 2)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HAEntityTypes.GIANT_SQUID.get(), 0.9, 1.0),
                    Triple(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 0.15, 0.3),
                    Triple(HAEntityTypes.NAUTILUS.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.VAMPIRE_SQUID.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.FIREFLY_SQUID.get(), 0.7, 0.2),

                    Triple(HAEntityTypes.RATFISH.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.SNAILFISH.get(), 0.1, 0.5),
                    Triple(HAEntityTypes.ANGLERFISH.get(), 0.1, 0.3),
                    Triple(HAEntityTypes.FANGTOOTH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.VIPERFISH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.DRAGONFISH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.JOHN_DORY.get(), 0.1, 0.3),
                    Triple(HAEntityTypes.HATCHETFISH.get(), 0.1, 0.6),
                    Triple(HAEntityTypes.TRIPOD_FISH.get(), 0.1, 0.6),
                    Triple(HAEntityTypes.BARRELEYE.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.COELACANTH.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.OARFISH.get(), 0.5, 0.8),

                    Triple(HAEntityTypes.SIXGILL_SHARK.get(), 0.5, 0.8),
                    Triple(HAEntityTypes.FRILLED_SHARK.get(), 0.5, 0.8),
                    Triple(HAEntityTypes.LANTERN_SHARK.get(), 0.1, 0.4),
                )
            )
        )

        entries.add(
            HABiomes.LUKEWARM_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x4787BF,
                waterFogColor = 0x1b2447,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.VAMPIRE_SQUID.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.NAUTILUS.get(), 10, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.FIREFLY_SQUID.get(), 10, 1, 2)
                    ),

                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.RATFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SNAILFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.JOHN_DORY.get(), 8, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.VIPERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.DRAGONFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.TRIPOD_FISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.BARRELEYE.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.COELACANTH.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.OARFISH.get(), 1, 1, 2)
                    ),


                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SIXGILL_SHARK.get(), 3, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.FRILLED_SHARK.get(), 3, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.LANTERN_SHARK.get(), 5, 1, 2)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HAEntityTypes.GIANT_SQUID.get(), 0.9, 1.0),
                    Triple(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 0.15, 0.3),
                    Triple(HAEntityTypes.NAUTILUS.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.VAMPIRE_SQUID.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.FIREFLY_SQUID.get(), 0.7, 0.2),

                    Triple(HAEntityTypes.RATFISH.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.SNAILFISH.get(), 0.1, 0.5),
                    Triple(HAEntityTypes.ANGLERFISH.get(), 0.1, 0.3),
                    Triple(HAEntityTypes.FANGTOOTH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.VIPERFISH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.DRAGONFISH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.JOHN_DORY.get(), 0.1, 0.3),
                    Triple(HAEntityTypes.HATCHETFISH.get(), 0.1, 0.6),
                    Triple(HAEntityTypes.TRIPOD_FISH.get(), 0.1, 0.6),
                    Triple(HAEntityTypes.BARRELEYE.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.COELACANTH.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.OARFISH.get(), 0.5, 0.8),

                    Triple(HAEntityTypes.SIXGILL_SHARK.get(), 0.4, 0.8),
                    Triple(HAEntityTypes.FRILLED_SHARK.get(), 0.4, 0.8),
                    Triple(HAEntityTypes.LANTERN_SHARK.get(), 0.1, 0.4),
                )
            )
        )

        entries.add(
            HABiomes.COLD_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x1D2E87,
                waterFogColor = 0x020217,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.COLOSSAL_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 2)
                    ),

                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.RATFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SNAILFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.VIPERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.TRIPOD_FISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SEA_ANGEL.get(), 3, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SLICKHEAD.get(), 1, 1, 2)
                    ),

                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SLEEPER_SHARK.get(), 3, 1, 1)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HAEntityTypes.COLOSSAL_SQUID.get(), 1.0, 0.1),
                    Triple(HAEntityTypes.GIANT_SQUID.get(), 0.9, 0.1),
                    Triple(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 0.8, 0.15),

                    Triple(HAEntityTypes.RATFISH.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.SNAILFISH.get(), 0.1, 0.5),
                    Triple(HAEntityTypes.ANGLERFISH.get(), 0.1, 0.3),
                    Triple(HAEntityTypes.FANGTOOTH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.VIPERFISH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.HATCHETFISH.get(), 0.1, 0.6),
                    Triple(HAEntityTypes.TRIPOD_FISH.get(), 0.1, 0.6),
                    Triple(HAEntityTypes.SLICKHEAD.get(), 0.1, 0.3),

                    Triple(HAEntityTypes.SLEEPER_SHARK.get(), 0.5, 0.8),
                )
            )
        )

        entries.add(
            HABiomes.FROZEN_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x232380,
                waterFogColor = 0x020217,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.COLOSSAL_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 2)
                    ),

                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.RATFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SNAILFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.VIPERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.TRIPOD_FISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SEA_ANGEL.get(), 3, 1, 3)
                    ),

                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.SLEEPER_SHARK.get(), 3, 1, 1)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HAEntityTypes.COLOSSAL_SQUID.get(), 1.0, 0.1),
                    Triple(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 0.8, 0.15),

                    Triple(HAEntityTypes.RATFISH.get(), 0.1, 0.2),
                    Triple(HAEntityTypes.SNAILFISH.get(), 0.1, 0.7),
                    Triple(HAEntityTypes.ANGLERFISH.get(), 0.1, 0.3),
                    Triple(HAEntityTypes.FANGTOOTH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.VIPERFISH.get(), 0.15, 0.6),
                    Triple(HAEntityTypes.HATCHETFISH.get(), 0.1, 0.6),
                    Triple(HAEntityTypes.TRIPOD_FISH.get(), 0.1, 0.6),

                    Triple(HAEntityTypes.SLEEPER_SHARK.get(), 0.5, 0.8),
                )
            )
        )

        entries.add(
            HABiomes.DEEP_CORAL_REEF,
            create(
                entries,
                temperature = 0.2f,
                downfall = 0.0f,
                waterColor = 0x3F76E4,
                waterFogColor = 0x050533,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.NAUTILUS.get(), 10, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HAEntityTypes.NAUTILUS.get(), 0.7, 0.2),
                    Triple(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 0.8, 0.15),
                )
            ) {
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HAPlacedFeatures.DEEP_OCEAN_VEGETATION)
                )
            }
        )

        entries.add(
            HABiomes.TROPICAL_DEEP_CORAL_REEF,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x45ADF2,
                waterFogColor = 0x041633,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.NAUTILUS.get(), 10, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HAEntityTypes.NAUTILUS.get(), 0.7, 0.2),
                    Triple(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 0.8, 0.15),
                )
            ) {
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HAPlacedFeatures.DEEP_OCEAN_VEGETATION)
                )
            }
        )
    }

    override fun getName(): String {
        return "Biomes"
    }
}