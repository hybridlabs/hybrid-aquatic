package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.config.HybridAquaticConfigHandler
import java.nio.file.Path
import kotlin.io.path.notExists

fun initializeConfig(configFile: Path = CommonClass.CONFIG_FILE, configHandler: HybridAquaticConfigHandler) {
    val logger = Constants.LOG
    if (configFile.notExists()) {
        logger.info("${Constants.MOD_NAME} config file did not exist, creating one")
        configHandler.save()
    } else {
        logger.info("Loading ${Constants.MOD_NAME} config file")
        configHandler.load()

        // check config data version, if updated then reset
        val defaultConfig = configHandler.defaultConfig
        val config = configHandler.config
        if (config.dataVersion < defaultConfig.dataVersion) {
            logger.info("Old ${Constants.MOD_NAME} config file found, upgrading")

            configHandler.backup()

            configHandler.config = defaultConfig
            configHandler.save()

            logger.info("${Constants.MOD_NAME} config reset, the old config has been backed up to \"${configHandler.backupFile}\"")
        }
    }
}