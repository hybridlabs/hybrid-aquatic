package dev.hybridlabs.aquatic.datagen.server

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.config.ConfigHelper.initializeConfig
import dev.hybridlabs.aquatic.world.gen.feature.BiomeFeatureAddition
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraftforge.common.world.BiomeModifier
import net.minecraftforge.common.world.ForgeBiomeModifiers
import net.minecraftforge.registries.ForgeRegistries.Keys.BIOME_MODIFIERS

class BiomeModifierProvider(context: BootstapContext<BiomeModifier>) {
    init {
        registerBiomeSpawns(context)
        registerFeatures(context)
    }

    /**
     * Create Forge biome modifiers to add mob spawns based on the config.
     */
    private fun registerBiomeSpawns(
        context: BootstapContext<BiomeModifier>,
    ) {
        val configHandler = initializeConfig(CommonClass.CONFIG_FILE)
        val biomeRegistry = context.lookup(Registries.BIOME)
        for (spawnConfig in configHandler.defaultConfig.entitySpawnConfig) {

            val location = "${spawnConfig.type.toShortString()}_${spawnConfig.biomes.location.path}"
            val key = ResourceKey.create(
                BIOME_MODIFIERS, CommonClass.locate(location)
            )

            context.register(
                key, ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                    biomeRegistry.get(spawnConfig.biomes).get(),
                    listOf(
                        MobSpawnSettings.SpawnerData(
                            spawnConfig.type,
                            spawnConfig.weight,
                            spawnConfig.minGroupSize,
                            spawnConfig.maxGroupSize
                        )
                    )
                )
            )

        }
    }

    /**
     * Create Forge biome modifiers to add placed features.
     */
    private fun registerFeatures(
        context: BootstapContext<BiomeModifier>,
    ) {
        val biomeRegistry = context.lookup(Registries.BIOME)
        val featureRegistry = context.lookup(Registries.PLACED_FEATURE)
        for (addition in BiomeFeatureAddition.builtIn) {

            val location = "${addition.placedFeature.location().path}_${addition.biomeTag.location.path}"
            val key = ResourceKey.create(
                BIOME_MODIFIERS,
                CommonClass.locate(location)
            )
            context.register(
                key, ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    biomeRegistry.getOrThrow(addition.biomeTag),
                    HolderSet.direct(featureRegistry.getOrThrow(addition.placedFeature)),
                    addition.step
                )
            )
        }
    }
}