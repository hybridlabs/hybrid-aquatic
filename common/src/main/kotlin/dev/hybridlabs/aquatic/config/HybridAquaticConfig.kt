package dev.hybridlabs.aquatic.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class HybridAquaticConfig(
    /**
     * The version of the data stored.
     * Increase when the config needs to be reset, i.e. when new entity spawn configs are added.
     */
    val dataVersion: Int = 7,



    val entitySpawnConfig: List<EntitySpawnConfig> = EntitySpawnConfigGenerator.generate(),
    val enableWanderingTraderTrades: Boolean = true,
    val enableVillagerTrades: Boolean = true,
) {
    companion object {
        val CODEC: Codec<HybridAquaticConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("data_version").forGetter(HybridAquaticConfig::dataVersion),
                Codec.BOOL.fieldOf("enable_wandering_trader_trades").forGetter(HybridAquaticConfig::enableWanderingTraderTrades),
                Codec.BOOL.fieldOf("enable_villager_trades").forGetter(HybridAquaticConfig::enableVillagerTrades),
                EntitySpawnConfig.CODEC.listOf().fieldOf("spawn_configuration").forGetter(HybridAquaticConfig::entitySpawnConfig),
            ).apply(instance, ::HybridAquaticConfig)
        }
    }
}
