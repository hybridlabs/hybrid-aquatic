package dev.hybridlabs.aquatic.forge

import dev.hybridlabs.aquatic.utils.HybridAquaticCustomTrades
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

object HybridAquaticForgeBusEvents {
    init {
        FORGE_BUS.addListener(HybridAquaticCustomTrades::registerWandererTrades)
        FORGE_BUS.addListener(HybridAquaticCustomTrades::registerCustomTrades)
    }
}