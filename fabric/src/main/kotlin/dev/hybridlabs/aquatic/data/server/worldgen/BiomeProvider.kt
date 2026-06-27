package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.aquatic.world.gen.biome.HABiomes
import dev.hybridlabs.aquatic.world.gen.feature.HAPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.AquaticPlacements
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.BiomeSpecialEffects
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import java.util.concurrent.CompletableFuture

class BiomeProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
) : FabricDynamicRegistryProvider(output, registriesFuture) {
    companion object {
        fun bootstrapBiomes(bootstrap: BootstrapContext<Biome>) {
            val carversGetter = bootstrap.lookup(Registries.CONFIGURED_CARVER)
            val featuresGetter = bootstrap.lookup(Registries.PLACED_FEATURE)

            bootstrap.register(
                HABiomes.TROPICAL_RIVER,
                create(
                    featuresGetter,
                    carversGetter,
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
                        featuresGetter.get(MiscOverworldPlacements.FOREST_ROCK).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(AquaticPlacements.SEAGRASS_RIVER).get()
                    )
                }
            )
            bootstrap.register(
                HABiomes.CORAL_REEF,
                create(
                    featuresGetter,
                    carversGetter,
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
                        featuresGetter.get(HAPlacedFeatures.CORAL_MOUND).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                        featuresGetter.get(HAPlacedFeatures.AERATED_SAND_CIRCLE).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(HAPlacedFeatures.CORAL_LAYER).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(HAPlacedFeatures.CORAL_REEF_VEGETATION).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(AquaticPlacements.SEAGRASS_WARM).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(AquaticPlacements.SEA_PICKLE).get()
                    )
                }
            )

            bootstrap.register(
                HABiomes.SEAGRASS_BED,
                create(
                    featuresGetter,
                    carversGetter,
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
                )
                {
                    addFeature(
                        GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                        featuresGetter.get(HAPlacedFeatures.SAND_CIRCLE).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                        featuresGetter.get(HAPlacedFeatures.AERATED_SAND_CIRCLE).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(AquaticPlacements.SEAGRASS_WARM).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(AquaticPlacements.SEA_PICKLE).get()
                    )
                }
            )

            bootstrap.register(
                HABiomes.RED_MEADOW,
                create(
                    featuresGetter, carversGetter,
                    temperature = 0.5f,
                    downfall = 0.5f,
                    waterColor = 0x43D5EE,
                    waterFogColor = 0x041F33
                )
                {
                    addFeature(
                        GenerationStep.Decoration.UNDERGROUND_ORES,
                        featuresGetter.get(HAPlacedFeatures.WHITE_MOUND).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(HAPlacedFeatures.BLEACHED_REEF_VEGETATION).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(HAPlacedFeatures.RED_MEADOW_VEGETATION).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(AquaticPlacements.SEA_PICKLE).get()
                    )
                }
            )

            bootstrap.register(
                HABiomes.DEEP_WARM_OCEAN,
                create(
                    featuresGetter, carversGetter,
                    temperature = 0.5f,
                    downfall = 0.5f,
                    waterColor = 0x48B3C7,
                    waterFogColor = 0x418794
                )
            )

            bootstrap.register(
                HABiomes.TIDE_POOLS,
                create(
                    featuresGetter, carversGetter,
                    temperature = 1.1f,
                    downfall = 0.6f,
                    waterColor = 0x3FA7D6,
                    waterFogColor = 0x2E5D73
                )
                {
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(HAPlacedFeatures.TIDE_POOLS).get()
                    )
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(AquaticPlacements.SEA_PICKLE).get()
                    )
                }
            )

            bootstrap.register(
                HABiomes.SULFURIC_CAVES,
                create(
                    featuresGetter, carversGetter,
                    temperature = 1.0f,
                    downfall = 0.0f,
                    waterColor = 0xc9b147,
                    waterFogColor = 0xadb148
                )
            )

            bootstrap.register(
                HABiomes.TRENCH,
                create(
                    featuresGetter, carversGetter,
                    temperature = 0.2f,
                    downfall = 0.0f,
                    waterColor = 0x1A4EB7,
                    waterFogColor = 0x020217,
                    listOf(
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.VAMPIRE_SQUID.get(), 7, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.NAUTILUS.get(), 10, 1, 2)
                        ),

                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.RATFISH.get(), 10, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SNAILFISH.get(), 10, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.JOHN_DORY.get(), 8, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.VIPERFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.DRAGONFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.TRIPOD_FISH.get(), 5, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.BARRELEYE.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SEA_ANGEL.get(), 3, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.COELACANTH.get(), 1, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SLICKHEAD.get(), 1, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.OARFISH.get(), 1, 1, 2)
                        ),

                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SIXGILL_SHARK.get(), 3, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.GOBLIN_SHARK.get(), 3, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
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
                        Triple(HAEntityTypes.GOBLIN_SHARK.get(), 0.4, 0.8),
                        Triple(HAEntityTypes.LANTERN_SHARK.get(), 0.1, 0.4),
                    )
                )
            )

            bootstrap.register(
                HABiomes.WARM_TRENCH,
                create(
                    featuresGetter, carversGetter,
                    temperature = 0.5f,
                    downfall = 0.5f,
                    waterColor = 0x37808C,
                    waterFogColor = 0x1b2447,
                    listOf(
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.VAMPIRE_SQUID.get(), 7, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.NAUTILUS.get(), 10, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.FIREFLY_SQUID.get(), 10, 1, 2)
                        ),

                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.RATFISH.get(), 10, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SNAILFISH.get(), 10, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.JOHN_DORY.get(), 8, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.VIPERFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.DRAGONFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.TRIPOD_FISH.get(), 5, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.BARRELEYE.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.COELACANTH.get(), 1, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.OARFISH.get(), 1, 1, 2)
                        ),


                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SIXGILL_SHARK.get(), 3, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.GOBLIN_SHARK.get(), 3, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.FRILLED_SHARK.get(), 3, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
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
                        Triple(HAEntityTypes.GOBLIN_SHARK.get(), 0.5, 0.8),
                        Triple(HAEntityTypes.FRILLED_SHARK.get(), 0.5, 0.8),
                        Triple(HAEntityTypes.LANTERN_SHARK.get(), 0.1, 0.4),
                    )
                )
            )

            bootstrap.register(
                HABiomes.LUKEWARM_TRENCH,
                create(
                    featuresGetter, carversGetter,
                    temperature = 0.5f,
                    downfall = 0.5f,
                    waterColor = 0x4787BF,
                    waterFogColor = 0x1b2447,
                    listOf(
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.VAMPIRE_SQUID.get(), 7, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.NAUTILUS.get(), 10, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.FIREFLY_SQUID.get(), 10, 1, 2)
                        ),

                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.RATFISH.get(), 10, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SNAILFISH.get(), 10, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.JOHN_DORY.get(), 8, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.VIPERFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.DRAGONFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.TRIPOD_FISH.get(), 5, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.BARRELEYE.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.COELACANTH.get(), 1, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.OARFISH.get(), 1, 1, 2)
                        ),


                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SIXGILL_SHARK.get(), 3, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.GOBLIN_SHARK.get(), 3, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.FRILLED_SHARK.get(), 3, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
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
                        Triple(HAEntityTypes.GOBLIN_SHARK.get(), 0.4, 0.8),
                        Triple(HAEntityTypes.FRILLED_SHARK.get(), 0.4, 0.8),
                        Triple(HAEntityTypes.LANTERN_SHARK.get(), 0.1, 0.4),
                    )
                )
            )

            bootstrap.register(
                HABiomes.COLD_TRENCH,
                create(
                    featuresGetter, carversGetter,
                    temperature = 0.5f,
                    downfall = 0.5f,
                    waterColor = 0x1D2E87,
                    waterFogColor = 0x020217,
                    listOf(
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.COLOSSAL_SQUID.get(), 1, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.GIANT_SQUID.get(), 1, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 2)
                        ),

                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.RATFISH.get(), 10, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SNAILFISH.get(), 10, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.VIPERFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.TRIPOD_FISH.get(), 5, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SEA_ANGEL.get(), 3, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SLICKHEAD.get(), 1, 1, 2)
                        ),

                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SLEEPER_SHARK.get(), 3, 1, 1)
                        ),

                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.GOBLIN_SHARK.get(), 3, 1, 1)
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
                        Triple(HAEntityTypes.GOBLIN_SHARK.get(), 0.5, 0.8),
                    )
                )
            )

            bootstrap.register(
                HABiomes.FROZEN_TRENCH,
                create(
                    featuresGetter, carversGetter,
                    temperature = 0.5f,
                    downfall = 0.5f,
                    waterColor = 0x232380,
                    waterFogColor = 0x020217,
                    listOf(
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.COLOSSAL_SQUID.get(), 1, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 2)
                        ),

                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.RATFISH.get(), 10, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SNAILFISH.get(), 10, 1, 3)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.ANGLERFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.FANGTOOTH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.VIPERFISH.get(), 5, 1, 1)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.HATCHETFISH.get(), 5, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.TRIPOD_FISH.get(), 5, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("fish"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.SEA_ANGEL.get(), 3, 1, 3)
                        ),

                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("shark"),
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

            bootstrap.register(
                HABiomes.DEEP_CORAL_REEF,
                create(
                    featuresGetter, carversGetter,
                    temperature = 0.2f,
                    downfall = 0.0f,
                    waterColor = 0x3F76E4,
                    waterFogColor = 0x050533,
                    listOf(
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.NAUTILUS.get(), 10, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                        ),
                    ),
                    extraSpawnCosts = listOf(
                        Triple(HAEntityTypes.NAUTILUS.get(), 0.7, 0.2),
                        Triple(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 0.8, 0.15),
                    )
                )
                {
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(HAPlacedFeatures.DEEP_OCEAN_VEGETATION).get()
                    )
                }
            )

            bootstrap.register(
                HABiomes.TROPICAL_DEEP_CORAL_REEF,
                create(
                    featuresGetter, carversGetter,
                    temperature = 0.5f,
                    downfall = 0.5f,
                    waterColor = 0x45ADF2,
                    waterFogColor = 0x041633,
                    listOf(
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.NAUTILUS.get(), 10, 1, 2)
                        ),
                        Pair(
                            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
                            MobSpawnSettings.SpawnerData(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 7, 1, 1)
                        ),
                    ),
                    extraSpawnCosts = listOf(
                        Triple(HAEntityTypes.NAUTILUS.get(), 0.7, 0.2),
                        Triple(HAEntityTypes.UMBRELLA_OCTOPUS.get(), 0.8, 0.15),
                    )
                )
                {
                    addFeature(
                        GenerationStep.Decoration.VEGETAL_DECORATION,
                        featuresGetter.get(HAPlacedFeatures.DEEP_OCEAN_VEGETATION).get()
                    )
                }
            )

        }


        fun create(
            features: HolderGetter<PlacedFeature>,
            carvers: HolderGetter<ConfiguredWorldCarver<*>>,
            temperature: Float,
            downfall: Float,
            waterColor: Int,
            waterFogColor: Int,
            extraSpawns: List<Pair<MobCategory, MobSpawnSettings.SpawnerData>> = ArrayList(),
            extraSpawnCosts: List<Triple<EntityType<*>, Double, Double>> = emptyList(),
            extraFeatures: (BiomeGenerationSettings.Builder.() -> Unit)? = null,
        ): Biome {
            val builder = BiomeGenerationSettings.Builder(features, carvers)

            addStandardFeatures(builder)
            BiomeDefaultFeatures.addDefaultOres(builder)
            BiomeDefaultFeatures.addDefaultSoftDisks(builder)

            extraFeatures?.invoke(builder)

            return Biome.BiomeBuilder()
                .generationSettings(makeGenerationSettings(features, carvers))
                .generationSettings(builder.build()).mobSpawnSettings(
                    makeSpawnSettings(extraSpawns, extraSpawnCosts)
                )

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

        fun makeGenerationSettings(
            features: HolderGetter<PlacedFeature>,
            carvers: HolderGetter<ConfiguredWorldCarver<*>>
        ): BiomeGenerationSettings {
            val builder = BiomeGenerationSettings.Builder(features, carvers)
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


    }

    override fun configure(
        registries: HolderLookup.Provider,
        entries: Entries
    ) {
        val reg = registries.lookup(Registries.BIOME).get()
        entries.add(reg.getOrThrow(HABiomes.CORAL_REEF))
        entries.add(reg.getOrThrow(HABiomes.TROPICAL_RIVER))
        entries.add(reg.getOrThrow(HABiomes.TRENCH))
        entries.add(reg.getOrThrow(HABiomes.TIDE_POOLS))
        entries.add(reg.getOrThrow(HABiomes.COLD_TRENCH))
        entries.add(reg.getOrThrow(HABiomes.DEEP_CORAL_REEF))
        entries.add(reg.getOrThrow(HABiomes.DEEP_WARM_OCEAN))
        entries.add(reg.getOrThrow(HABiomes.FROZEN_TRENCH))
        entries.add(reg.getOrThrow(HABiomes.LUKEWARM_TRENCH))
        entries.add(reg.getOrThrow(HABiomes.SEAGRASS_BED))
        entries.add(reg.getOrThrow(HABiomes.SULFURIC_CAVES))
        entries.add(reg.getOrThrow(HABiomes.TROPICAL_DEEP_CORAL_REEF))
        entries.add(reg.getOrThrow(HABiomes.WARM_TRENCH))
    }

    override fun getName(): String {
        return "Biomes"
    }
}