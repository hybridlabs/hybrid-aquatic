package dev.hybridlabs.aquatic.config

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.hapi.config.ConfigHandler
import dev.hybridlabs.hapi.config.ConfigHelper

/**
 * Single shared config handler for the whole mod.
 *
 * Loaders must touch [handler] during mod construction so the config file exists before any worldgen or
 * trade registry code reads it.
 */
object HAConfigHolder {
    val handler: ConfigHandler<HAConfig> by lazy {
        ConfigHelper.initializeConfig(
            modId = Constants.MOD_ID,
            codec = HAConfig.CODEC,
            defaultConfig = HAConfig(),
            dataVersion = HAConfig::dataVersion,
            modName = Constants.MOD_NAME,
        )
    }

    val config: HAConfig
        get() = handler.config
}
