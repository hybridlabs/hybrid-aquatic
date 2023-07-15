package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object HybridAquatic : ModInitializer {
    const val MOD_ID: String = "hybrid-aquatic"
    private val LOGGER = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        LOGGER.info("Initializing $MOD_ID")

        HybridAquaticBlocks
        HybridAquaticBlockEntityTypes

        HybridAquaticEntityTypes

        HybridAquaticItems
        HybridAquaticItemGroups
    }
}
