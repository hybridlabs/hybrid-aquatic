package dev.hybridlabs.aquatic.datagen.server

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.config.ConfigHelper.initializeConfig
import dev.hybridlabs.aquatic.world.gen.feature.BiomeFeatureAddition
import java.util.concurrent.CompletableFuture
import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import net.minecraft.util.random.Weighted
import net.minecraft.util.random.WeightedList
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.GenerationStep

/**
 * Generates the NeoForge biome modifiers.
 *
 * The entries are written directly instead of going through a registry set builder, because the
 * placed features and biome tags they reference are produced by the Fabric datagen and are not
 * present in the NeoForge datagen registry snapshot.
 */
class HABiomeModifierProvider(output: PackOutput) : DataProvider {
    private val pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "neoforge/biome_modifier")

    override fun run(cache: CachedOutput): CompletableFuture<*> {
        val futures = mutableListOf<CompletableFuture<*>>()

        for (addition in BiomeFeatureAddition.builtIn) {
            val location = "${addition.placedFeature.identifier().path}_${addition.biomeTag.location.path}"
            val json = JsonObject().apply {
                addProperty("type", "neoforge:add_features")
                addProperty("biomes", "#${addition.biomeTag.location}")
                addProperty("features", addition.placedFeature.identifier().toString())
                addProperty("step", addition.step.getSerializedName())
            }
            futures += DataProvider.saveStable(
                cache,
                json,
                pathProvider.json(CommonClass.locate(location))
            )
        }

        val config = initializeConfig(CommonClass.CONFIG_FILE).defaultConfig
        for (spawnConfig in config.entitySpawnConfig) {
            val location = "${spawnConfig.type.toShortString()}_${spawnConfig.biomes.location.path}"
            val spawner = Weighted(
                MobSpawnSettings.SpawnerData(
                    spawnConfig.type,
                    UniformInt.of(spawnConfig.minGroupSize, spawnConfig.maxGroupSize)
                ),
                spawnConfig.weight
            )
            val spawners = WeightedList.codec(MobSpawnSettings.SpawnerData.CODEC)
                .encodeStart(com.mojang.serialization.JsonOps.INSTANCE, WeightedList.of(spawner))
                .getOrThrow()

            val json = JsonObject().apply {
                addProperty("type", "neoforge:add_spawns")
                addProperty("biomes", "#${spawnConfig.biomes.location}")
                add("spawners", spawners)
            }
            futures += DataProvider.saveStable(
                cache,
                json,
                pathProvider.json(CommonClass.locate(location))
            )
        }

        return CompletableFuture.allOf(*futures.toTypedArray())
    }

    override fun getName(): String = "Biome Modifiers"
}
