package dev.hybridlabs.aquatic.potions

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.potion.Potion
import net.minecraft.potion.Potions
import net.minecraft.recipe.BrewingRecipeRegistry
import net.minecraft.registry.Registries.POTION
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticPotions {
    val GLOWING_POTION = Potion(StatusEffectInstance(StatusEffects.GLOWING, 3600, 0))

    fun registerPotions() {
        Registry.register(POTION, Identifier("glowing", "glowing_potion"), GLOWING_POTION)
    }

    fun registerPotionsRecipes() {
        BrewingRecipeRegistry.registerPotionRecipe(
            Potions.AWKWARD,
            HybridAquaticItems.GLOW_SLIME,
            GLOWING_POTION
        )
    }
}