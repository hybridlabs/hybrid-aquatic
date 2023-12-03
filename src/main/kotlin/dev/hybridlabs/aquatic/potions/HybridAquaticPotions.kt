package dev.hybridlabs.aquatic.potions

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Item
import net.minecraft.potion.Potion
import net.minecraft.potion.Potions
import net.minecraft.recipe.BrewingRecipeRegistry
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticPotions {
    val GLOWING_POTION = registerPotionWithRecipe(
        "glowing",
        Potion(StatusEffectInstance(StatusEffects.GLOWING, 3600, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.GLOW_SLIME
    )

    private fun registerPotionWithRecipe(id: String, potion: Potion, inputPotion: Potion, ingredient: Item): Potion {
        BrewingRecipeRegistry.registerPotionRecipe(inputPotion, ingredient, potion)
        return register(id, potion)
    }

    private fun register(id: String, potion: Potion): Potion {
        return Registry.register(Registries.POTION, Identifier(HybridAquatic.MOD_ID, id), potion)
    }
}