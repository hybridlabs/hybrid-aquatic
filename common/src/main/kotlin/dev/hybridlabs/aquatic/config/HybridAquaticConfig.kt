package dev.hybridlabs.aquatic.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class HybridAquaticConfig(
    /**
     * The version of the data stored.
     * Increase when the config needs to be reset, i.e. when new entity spawn configs are added.
     */
    val dataVersion: Int = 6,

    val entitySpawnConfig: List<EntitySpawnConfig> = EntitySpawnConfigGenerator.generate(),
    val disableDeeperOceans: Boolean = false
) {
    companion object {
        val CODEC: Codec<HybridAquaticConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("data_version").forGetter(HybridAquaticConfig::dataVersion),
                EntitySpawnConfig.CODEC.listOf().fieldOf("spawn_configuration").forGetter(HybridAquaticConfig::entitySpawnConfig),
                Codec.BOOL.fieldOf("disable_ha_deeper_oceans").forGetter(HybridAquaticConfig::disableDeeperOceans)
            ).apply(instance, ::HybridAquaticConfig)
        }
    }
}