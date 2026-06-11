package dev.hybridlabs.aquatic.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class HAConfig(
    /**
     * The version of the data stored.
     * Increase when the config needs to be reset, i.e. when new entity spawn configs are added.
     */
    val dataVersion: Int = 8,
    val enableWanderingTraderTrades: Boolean = true,
    val enableVillagerTrades: Boolean = true,
    val addFishingLoot: Boolean = true,
    val biomeConfig: BiomeConfig = BiomeConfig(),
    val featureConfig: FeatureConfig = FeatureConfig(),
    val entitySpawnConfig: List<EntitySpawnConfig> = EntitySpawnConfigGenerator.generate(),
) {
    companion object {
        val CODEC: Codec<HAConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("data_version").forGetter(HAConfig::dataVersion),
                Codec.BOOL.fieldOf("enable_wandering_trader_trades").forGetter(HAConfig::enableWanderingTraderTrades),
                Codec.BOOL.fieldOf("enable_villager_trades").forGetter(HAConfig::enableVillagerTrades),
                Codec.BOOL.fieldOf("add_fishing_loot").forGetter(HAConfig::addFishingLoot),
                BiomeConfig.CODEC.fieldOf("biomes").forGetter(HAConfig::biomeConfig),
                FeatureConfig.CODEC.fieldOf("worldgen_features").forGetter(HAConfig::featureConfig),
                EntitySpawnConfig.CODEC.listOf().fieldOf("spawn_configuration").forGetter(HAConfig::entitySpawnConfig),
            ).apply(instance, ::HAConfig)
        }
    }
}