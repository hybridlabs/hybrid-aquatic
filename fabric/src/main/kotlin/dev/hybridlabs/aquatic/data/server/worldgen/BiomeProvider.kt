package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.aquatic.world.gen.biome.HybridAquaticBiomes
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
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
            HybridAquaticBiomes.COLD_RIVER,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x3D57D6,
                waterFogColor = 0x050533,
                listOf(
                    Pair(
                        MobCategory.WATER_AMBIENT,
                        MobSpawnSettings.SpawnerData(EntityType.SALMON, 1, 3, 5)
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
            HybridAquaticBiomes.TROPICAL_RIVER,
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
            HybridAquaticBiomes.SEASONAL_RIVER,
            create(
                entries,
                temperature = 2.0f,
                downfall = 0.0f,
                waterColor = 0x3F76E4,
                waterFogColor = 0x3F76E4
            )
        )

        entries.add(
            HybridAquaticBiomes.PLACER_RIVER,
            create(
                entries,
                temperature = 0.95f,
                downfall = 0.9f,
                waterColor = 0x3F76E4,
                waterFogColor = 0x3F76E4
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
            HybridAquaticBiomes.CORAL_REEF,
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
                    entries.ref(HybridAquaticPlacedFeatures.CORAL_MOUND)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HybridAquaticPlacedFeatures.CORAL_LAYER)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HybridAquaticPlacedFeatures.CORAL_REEF_VEGETATION)
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
            HybridAquaticBiomes.SEAGRASS_BED,
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
                    entries.ref(HybridAquaticPlacedFeatures.MOUND)
                )
                addFeature(
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    entries.ref(HybridAquaticPlacedFeatures.SAND_CIRCLE)
                )
                addFeature(
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    entries.ref(HybridAquaticPlacedFeatures.AERATED_SAND_CIRCLE)
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
            HybridAquaticBiomes.RED_MEADOW,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x43D5EE,
                waterFogColor = 0x041F33
            ) {
                addFeature(
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    entries.ref(HybridAquaticPlacedFeatures.WHITE_MOUND)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HybridAquaticPlacedFeatures.BLEACHED_REEF_VEGETATION)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HybridAquaticPlacedFeatures.RED_MEADOW_VEGETATION)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(AquaticPlacements.SEA_PICKLE)
                )
            }
        )

        entries.add(
            HybridAquaticBiomes.DEEP_WARM_OCEAN,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x48B3C7,
                waterFogColor = 0x418794
            ) {
                addFeature(
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    entries.ref(HybridAquaticPlacedFeatures.MOUND)
                )
            }
        )

        entries.add(
            HybridAquaticBiomes.TIDE_POOLS,
            create(
                entries,
                temperature = 1.1f,
                downfall = 0.6f,
                waterColor = 0x3FA7D6,
                waterFogColor = 0x2E5D73
            ) {
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HybridAquaticPlacedFeatures.TIDE_POOLS)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(AquaticPlacements.SEA_PICKLE)
                )
            }
        )

        entries.add(
            HybridAquaticBiomes.BASALT_BEACH,
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
            HybridAquaticBiomes.SULFURIC_CAVES,
            create(
                entries,
                temperature = 1.0f,
                downfall = 0.0f,
                waterColor = 0xc9b147,
                waterFogColor = 0xadb148
            )
        )

        entries.add(
            HybridAquaticBiomes.TRENCH,
            create(
                entries,
                temperature = 0.2f,
                downfall = 0.0f,
                waterColor = 0x1b2447,
                waterFogColor = 0x1b2447,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.VAMPIRE_SQUID.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.NAUTILUS.get(), 10, 1, 2)
                    ),

                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.RATFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.SNAILFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.JOHN_DORY.get(), 8, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.VIPERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.DRAGONFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.BARRELEYE.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.SEA_ANGEL.get(), 3, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.COELACANTH.get(), 1, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.SLICKHEAD.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.OARFISH.get(), 1, 1, 2)
                    ),

                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.SIXGILL_SHARK.get(), 3, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.LANTERN_SHARK.get(), 5, 1, 2)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HybridAquaticEntityTypes.GIANT_SQUID.get(), 0.9, 1.0),
                    Triple(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 0.15, 0.3),
                    Triple(HybridAquaticEntityTypes.NAUTILUS.get(), 0.15, 0.6),
                    Triple(HybridAquaticEntityTypes.VAMPIRE_SQUID.get(), 0.1, 0.2),

                    Triple(HybridAquaticEntityTypes.RATFISH.get(), 0.1, 0.2),
                    Triple(HybridAquaticEntityTypes.SNAILFISH.get(), 0.1, 0.7),
                    Triple(HybridAquaticEntityTypes.ANGLERFISH.get(), 0.1, 0.3),
                    Triple(HybridAquaticEntityTypes.FANGTOOTH.get(), 0.15, 0.6),
                    Triple(HybridAquaticEntityTypes.VIPERFISH.get(), 0.15, 0.6),
                    Triple(HybridAquaticEntityTypes.DRAGONFISH.get(), 0.15, 0.6),
                    Triple(HybridAquaticEntityTypes.JOHN_DORY.get(), 0.1, 0.3),
                    Triple(HybridAquaticEntityTypes.HATCHETFISH.get(), 0.1, 0.6),
                    Triple(HybridAquaticEntityTypes.SEA_ANGEL.get(), 0.1, 0.25),
                    Triple(HybridAquaticEntityTypes.BARRELEYE.get(), 0.1, 0.2),
                    Triple(HybridAquaticEntityTypes.COELACANTH.get(), 0.1, 0.2),
                    Triple(HybridAquaticEntityTypes.SLICKHEAD.get(), 0.1, 0.2),
                    Triple(HybridAquaticEntityTypes.OARFISH.get(), 0.5, 0.8),

                    Triple(HybridAquaticEntityTypes.SIXGILL_SHARK.get(), 0.4, 0.8),
                    Triple(HybridAquaticEntityTypes.LANTERN_SHARK.get(), 0.1, 0.4),
                )
            )
        )

        entries.add(
            HybridAquaticBiomes.WARM_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x37808C,
                waterFogColor = 0x1b2447,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.VAMPIRE_SQUID.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.NAUTILUS.get(), 10, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.FIREFLY_SQUID.get(), 10, 1, 2)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HybridAquaticEntityTypes.GIANT_SQUID.get(), 1.0, 0.1),
                    Triple(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 0.7, 0.15),
                    Triple(HybridAquaticEntityTypes.NAUTILUS.get(), 0.7, 0.2),
                    Triple(HybridAquaticEntityTypes.FIREFLY_SQUID.get(), 0.7, 0.2),
                    Triple(HybridAquaticEntityTypes.VAMPIRE_SQUID.get(), 0.8, 0.15),
                )
            )
        )

        entries.add(
            HybridAquaticBiomes.LUKEWARM_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x4787BF,
                waterFogColor = 0x1b2447,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.VAMPIRE_SQUID.get(), 7, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.NAUTILUS.get(), 10, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.FIREFLY_SQUID.get(), 10, 1, 2)
                    ),

                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.RATFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.SNAILFISH.get(), 10, 1, 3)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.JOHN_DORY.get(), 8, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.VIPERFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.DRAGONFISH.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.BARRELEYE.get(), 5, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.COELACANTH.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.OARFISH.get(), 1, 1, 2)
                    ),


                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.SIXGILL_SHARK.get(), 3, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.FRILLED_SHARK.get(), 3, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.LANTERN_SHARK.get(), 5, 1, 2)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HybridAquaticEntityTypes.GIANT_SQUID.get(), 0.9, 1.0),
                    Triple(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 0.15, 0.3),
                    Triple(HybridAquaticEntityTypes.NAUTILUS.get(), 0.15, 0.6),
                    Triple(HybridAquaticEntityTypes.VAMPIRE_SQUID.get(), 0.1, 0.2),
                    Triple(HybridAquaticEntityTypes.FIREFLY_SQUID.get(), 0.7, 0.2),

                    Triple(HybridAquaticEntityTypes.RATFISH.get(), 0.1, 0.2),
                    Triple(HybridAquaticEntityTypes.SNAILFISH.get(), 0.1, 0.7),
                    Triple(HybridAquaticEntityTypes.ANGLERFISH.get(), 0.1, 0.3),
                    Triple(HybridAquaticEntityTypes.FANGTOOTH.get(), 0.15, 0.6),
                    Triple(HybridAquaticEntityTypes.VIPERFISH.get(), 0.15, 0.6),
                    Triple(HybridAquaticEntityTypes.DRAGONFISH.get(), 0.15, 0.6),
                    Triple(HybridAquaticEntityTypes.JOHN_DORY.get(), 0.1, 0.3),
                    Triple(HybridAquaticEntityTypes.HATCHETFISH.get(), 0.1, 0.6),
                    Triple(HybridAquaticEntityTypes.BARRELEYE.get(), 0.1, 0.2),
                    Triple(HybridAquaticEntityTypes.COELACANTH.get(), 0.1, 0.2),
                    Triple(HybridAquaticEntityTypes.OARFISH.get(), 0.5, 0.8),

                    Triple(HybridAquaticEntityTypes.SIXGILL_SHARK.get(), 0.4, 0.8),
                    Triple(HybridAquaticEntityTypes.FRILLED_SHARK.get(), 0.4, 0.8),
                    Triple(HybridAquaticEntityTypes.LANTERN_SHARK.get(), 0.1, 0.4),
                )
            )
        )

        entries.add(
            HybridAquaticBiomes.COLD_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x1b2447,
                waterFogColor = 0x1b2447,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.COLOSSAL_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 2)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HybridAquaticEntityTypes.COLOSSAL_SQUID.get(), 1.0, 0.1),
                    Triple(HybridAquaticEntityTypes.GIANT_SQUID.get(), 0.9, 0.1),
                    Triple(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 0.8, 0.15),
                )
            )
        )

        entries.add(
            HybridAquaticBiomes.FROZEN_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x1b2447,
                waterFogColor = 0x1b2447,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.COLOSSAL_SQUID.get(), 1, 1, 1)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 2)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HybridAquaticEntityTypes.COLOSSAL_SQUID.get(), 1.0, 0.1),
                    Triple(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 0.8, 0.15),
                )
            )
        )

        entries.add(
            HybridAquaticBiomes.DEEP_CORAL_REEF,
            create(
                entries,
                temperature = 0.2f,
                downfall = 0.0f,
                waterColor = 0x3F76E4,
                waterFogColor = 0x050533,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.NAUTILUS.get(), 10, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HybridAquaticEntityTypes.NAUTILUS.get(), 0.7, 0.2),
                    Triple(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 0.8, 0.15),
                )
            ) {
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HybridAquaticPlacedFeatures.DEEP_OCEAN_VEGETATION)
                )
            }
        )

        entries.add(
            HybridAquaticBiomes.TROPICAL_DEEP_CORAL_REEF,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x45ADF2,
                waterFogColor = 0x041633,
                listOf(
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.NAUTILUS.get(), 10, 1, 2)
                    ),
                    Pair(
                        Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
                        MobSpawnSettings.SpawnerData(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                    ),
                ),
                extraSpawnCosts = listOf(
                    Triple(HybridAquaticEntityTypes.NAUTILUS.get(), 0.7, 0.2),
                    Triple(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), 0.8, 0.15),
                )
            ) {
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HybridAquaticPlacedFeatures.DEEP_OCEAN_VEGETATION)
                )
            }
        )
    }

    override fun getName(): String {
        return "Biomes"
    }
}