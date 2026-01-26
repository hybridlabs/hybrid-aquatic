package dev.hybridlabs.aquatic.data.server.worldgen

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
        extraFeatures: (BiomeGenerationSettings.Builder.() -> Unit)? = null
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
            .generationSettings(builder.build())
            .mobSpawnSettings(makeSpawnSettings())
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

    fun makeSpawnSettings(): MobSpawnSettings{
        val builder = makeDefaultSpawnSettings()
        return builder.build()
    }

    fun makeDefaultSpawnSettings(): MobSpawnSettings.Builder{
        val spawnSettings =  MobSpawnSettings.Builder()
        addDefaultAmbientSpawns(spawnSettings)
        addDefaultMonsterSpawns(spawnSettings)
        return spawnSettings
    }

    fun addStandardFeatures(builder: BiomeGenerationSettings.Builder){
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder)
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder)
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder)
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder)
        BiomeDefaultFeatures.addDefaultSprings(builder)
        BiomeDefaultFeatures.addSurfaceFreezing(builder)
    }

    fun addDefaultAmbientSpawns(builder: MobSpawnSettings.Builder){
        builder.addSpawn(MobCategory.AMBIENT, MobSpawnSettings.SpawnerData(EntityType.BAT,10,8,8))
        builder.addSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, MobSpawnSettings.SpawnerData(EntityType.GLOW_SQUID,5,2,5))
    }

    fun addDefaultMonsterSpawns(builder: MobSpawnSettings.Builder){
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.SPIDER,100,4,4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.ZOMBIE,95,4,4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER,5,1,1))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.SKELETON,100,4,4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.CREEPER,100,4,4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.SLIME,100,4,4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.ENDERMAN,10,1,4))
        builder.addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.WITCH,5,1,1))
    }

    fun createStandardBiomeEffects(): BiomeSpecialEffects.Builder{
        return BiomeSpecialEffects.Builder()
            .waterColor(0x3f76e4)
            .waterFogColor(0x50533)
            .fogColor(0xC0D8FF)
            .skyColor(0x78A7FF)
    }

    override fun configure(
        registries: HolderLookup.Provider,
        entries: Entries
    ) {
        entries.add(
            HybridAquaticBiomes.COLD_RIVER,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x3D57D6,
                waterFogColor = 0x050533
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
                waterFogColor = 0x4D7A60
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
                waterFogColor = 0x041F33
            ) {
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(HybridAquaticPlacedFeatures.CORAL_REEF)
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
                waterFogColor = 0x041F33
            ) {
                addFeature(
                    GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                    entries.ref(MiscOverworldPlacements.FOREST_ROCK)
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
                    GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                    entries.ref(MiscOverworldPlacements.FOREST_ROCK)
                )
                addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    entries.ref(AquaticPlacements.SEA_PICKLE)
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
            HybridAquaticBiomes.VOLCANIC_TRENCH,
            create(
                entries,
                temperature = 0.2f,
                downfall = 0.0f,
                waterColor = 0x1b2447,
                waterFogColor = 0x1b2447
            )
        )

        entries.add(
            HybridAquaticBiomes.BRINE_LAGOON,
            create(
                entries,
                temperature = 0.2f,
                downfall = 0.0f,
                waterColor = 0x1b2447,
                waterFogColor = 0x1b2447
            )
        )

        entries.add(
            HybridAquaticBiomes.TRENCH,
            create(
                entries,
                temperature = 0.2f,
                downfall = 0.0f,
                waterColor = 0x1b2447,
                waterFogColor = 0x1b2447
            )
        )

        entries.add(
            HybridAquaticBiomes.WARM_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x1b2447,
                waterFogColor = 0x1b2447
            )
        )

        entries.add(
            HybridAquaticBiomes.LUKEWARM_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x1b2447,
                waterFogColor = 0x1b2447
            )
        )

        entries.add(
            HybridAquaticBiomes.COLD_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x1b2447,
                waterFogColor = 0x1b2447
            )
        )

        entries.add(
            HybridAquaticBiomes.FROZEN_TRENCH,
            create(
                entries,
                temperature = 0.5f,
                downfall = 0.5f,
                waterColor = 0x1b2447,
                waterFogColor = 0x1b2447
            )
        )
    }

    override fun getName(): String {
        return "Biomes"
    }
}