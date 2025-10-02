package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.world.gen.biome.HybridAquaticBiomes
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.worldgen.BiomeDefaultFeatures
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

    fun create(entries: Entries): Biome {
        return Biome.BiomeBuilder()
            .generationSettings(makeGenerationSettings(entries))
            .mobSpawnSettings(makeSpawnSettings())
            .hasPrecipitation(true)
            .temperature(0.8f)
            .downfall(0.4f)
            .specialEffects(createStandardBiomeEffects().build())
        .build()
    }

    fun makeGenerationSettings(entries: Entries): BiomeGenerationSettings {
        val builder = BiomeGenerationSettings.Builder(entries.placedFeatures(), entries.configuredCarvers())
        addStandardFeatures(builder)
        BiomeDefaultFeatures.addDefaultOres(builder)
        BiomeDefaultFeatures.addDefaultSoftDisks(builder)
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,entries.ref(HybridAquaticPlacedFeatures.BOULDERS))
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,entries.ref(HybridAquaticPlacedFeatures.TIDE_POOLS))
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
        entries.add(HybridAquaticBiomes.TIDE_POOLS, create(entries))
    }

    override fun getName(): String {
        return "Biomes"
    }


}