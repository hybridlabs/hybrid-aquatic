package dev.hybridlabs.aquatic.forge

import dev.hybridlabs.aquatic.utils.HACustomTrades
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

object HybridAquaticForgeBusEvents {
    init {
        FORGE_BUS.addListener(HACustomTrades::registerWandererTrades)
        FORGE_BUS.addListener(HACustomTrades::registerCustomTrades)
    }
}