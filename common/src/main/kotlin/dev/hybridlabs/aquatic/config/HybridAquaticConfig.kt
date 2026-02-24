package dev.hybridlabs.aquatic.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class HybridAquaticConfig(
    /**
     * The version of the data stored.
     * Increase when the config needs to be reset, i.e. when new entity spawn configs are added.
     */
    val dataVersion: Int = 8,
    val enableWanderingTraderTrades: Boolean = true,
    val enableVillagerTrades: Boolean = true,
    val generateRedMeadow: Boolean = true,
    val generateSeagrassBed: Boolean = true,
    val generateTropicalRiver: Boolean = true,
    val generatePlacerRiver: Boolean = true,
    val generateSeasonalRiver: Boolean = true,
    val generateColdRiver: Boolean = true,
    val generateFloatingSargassum: Boolean = true,
    val generateBottledMessages: Boolean = true,

    val entitySpawnConfig: List<EntitySpawnConfig> = EntitySpawnConfigGenerator.generate(),
) {
    companion object {
        val CODEC: Codec<HybridAquaticConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("data_version").forGetter(HybridAquaticConfig::dataVersion),
                Codec.BOOL.fieldOf("enable_wandering_trader_trades").forGetter(HybridAquaticConfig::enableWanderingTraderTrades),
                Codec.BOOL.fieldOf("enable_villager_trades").forGetter(HybridAquaticConfig::enableVillagerTrades),
                Codec.BOOL.fieldOf("generate_red_meadow").forGetter(HybridAquaticConfig::generateRedMeadow),
                Codec.BOOL.fieldOf("generate_seagrass_bed").forGetter(HybridAquaticConfig::generateSeagrassBed),
                Codec.BOOL.fieldOf("generate_tropical_river").forGetter(HybridAquaticConfig::generateTropicalRiver),
                Codec.BOOL.fieldOf("generate_placer_river").forGetter(HybridAquaticConfig::generatePlacerRiver),
                Codec.BOOL.fieldOf("generate_seasonal_river").forGetter(HybridAquaticConfig::generateSeasonalRiver),
                Codec.BOOL.fieldOf("generate_cold_river").forGetter(HybridAquaticConfig::generateColdRiver),
                Codec.BOOL.fieldOf("generate_floating_sargassum").forGetter(HybridAquaticConfig::generateFloatingSargassum),
                Codec.BOOL.fieldOf("generate_bottled_messages").forGetter(HybridAquaticConfig::generateBottledMessages),
                EntitySpawnConfig.CODEC.listOf().fieldOf("spawn_configuration").forGetter(HybridAquaticConfig::entitySpawnConfig),
            ).apply(instance, ::HybridAquaticConfig)
        }
    }
}