package dev.hybridlabs.aquatic.datagen.server

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.config.HAConfigHolder
import dev.hybridlabs.aquatic.world.gen.feature.BiomeFeatureAddition
import dev.hybridlabs.aquatic.world.gen.modifier.ConfigGatedFeatureBiomeModifier
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.util.random.Weighted
import net.minecraft.world.level.biome.MobSpawnSettings
import net.neoforged.neoforge.common.world.BiomeModifier
import net.neoforged.neoforge.common.world.BiomeModifiers
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys.BIOME_MODIFIERS

class BiomeModifierProvider(context: BootstrapContext<BiomeModifier>) {
    init {
        registerBiomeSpawns(context)
        registerFeatures(context)
    }

    /**
     * Create Forge biome modifiers to add mob spawns based on the config.
     */
    private fun registerBiomeSpawns(
        context: BootstrapContext<BiomeModifier>,
    ) {
        val configHandler = HAConfigHolder.handler
        val biomeRegistry = context.lookup(Registries.BIOME)
        for (spawnConfig in configHandler.defaultConfig.entitySpawnConfig) {

            val location = "${spawnConfig.type.toShortString()}_${spawnConfig.biomes.location.path}"
            val key = ResourceKey.create(
                BIOME_MODIFIERS, CommonClass.locate(location)
            )

            context.register(
                key, BiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(
                    biomeRegistry.get(spawnConfig.biomes).get(),
                    Weighted(
                        MobSpawnSettings.SpawnerData(
                            spawnConfig.type,
                            spawnConfig.minGroupSize,
                            spawnConfig.maxGroupSize
                        ),
                        spawnConfig.weight
                    )
                )
            )

        }
    }

    /**
     * Create Forge biome modifiers to add placed features.
     */
    private fun registerFeatures(
        context: BootstrapContext<BiomeModifier>,
    ) {
        val biomeRegistry = context.lookup(Registries.BIOME)
        val featureRegistry = context.lookup(Registries.PLACED_FEATURE)
        for (addition in BiomeFeatureAddition.builtIn) {

            val location = "${addition.placedFeature.identifier().path}_${addition.biomeTag.location.path}"
            val key = ResourceKey.create(
                BIOME_MODIFIERS,
                CommonClass.locate(location)
            )
            context.register(
                key, ConfigGatedFeatureBiomeModifier(
                    biomeRegistry.getOrThrow(addition.biomeTag),
                    HolderSet.direct(featureRegistry.getOrThrow(addition.placedFeature)),
                    addition.step,
                    addition.toggle
                )
            )
        }
    }
}