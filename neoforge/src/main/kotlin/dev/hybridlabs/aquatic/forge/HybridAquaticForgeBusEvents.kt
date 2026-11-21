package dev.hybridlabs.aquatic.forge

import dev.hybridlabs.aquatic.potions.HAPotions
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent
import thedarkcolour.kotlinforforge.neoforge.forge.FORGE_BUS

object HybridAquaticForgeBusEvents {
    init {
        FORGE_BUS.addListener(::registerBrewingRecipes)
    }

    fun registerBrewingRecipes(event: RegisterBrewingRecipesEvent) {
        for (recipe in HAPotions.recipes.get()) {
            event.builder.addMix(
                recipe.inputPotion,
                recipe.addition,
                recipe.outputPotion
            )
        }
    }
}