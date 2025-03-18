package dev.hybridlabs.aquatic.potions

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Item
import net.minecraft.item.Items
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
        Items.GLOW_INK_SAC
    )

    val CLARITY_POTION = registerPotionWithRecipe(
        "clarity",
        Potion(StatusEffectInstance(HybridAquaticStatusEffects.CLARITY, 1200, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.BARRELEYE
    )

    val THALASSOPHOBIA_POTION = registerPotionWithRecipe(
        "thalassophobia",
        Potion(StatusEffectInstance(HybridAquaticStatusEffects.THALASSOPHOBIA, 1200, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.ANGLERFISH
    )

    val MINOR_LUCK_POTION = registerPotionWithRecipe(
        "minor_luck",
        Potion(StatusEffectInstance(StatusEffects.LUCK, 1200, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.PEARL
    )

    val MAJOR_LUCK_POTION = registerPotionWithRecipe(
        "major_luck",
        Potion(StatusEffectInstance(StatusEffects.LUCK, 600, 1)),
        Potions.AWKWARD,
        HybridAquaticItems.BLACK_PEARL
    )

    val BLEEDING_POTION = registerPotionWithRecipe(
        "bleeding",
        Potion(StatusEffectInstance(HybridAquaticStatusEffects.BLEEDING, 200, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.SHARK_TOOTH
    )

    val SWIMMING_POTION = registerPotionWithRecipe(
        "swimming",
        Potion(StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 1200, 0),
            StatusEffectInstance(StatusEffects.HUNGER, 600, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.MAHI
    )

    val BUOYANCY_POTION = registerPotionWithRecipe(
        "buoyancy",
        Potion(StatusEffectInstance(HybridAquaticStatusEffects.BUOYANCY, 200, 0)),
        Potions.AWKWARD,
        Items.KELP
    )

    val SPININESS_POTION = registerPotionWithRecipe(
        "spininess",
        Potion(StatusEffectInstance(HybridAquaticStatusEffects.SPININESS, 300, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.SEA_URCHIN_SPINE
    )

    val CORROSION_POTION = registerPotionWithRecipe(
        "corrosion",
        Potion(StatusEffectInstance(HybridAquaticStatusEffects.CORROSION, 300, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.SULFUR
    )

    val BLINDNESS_POTION = registerPotionWithRecipe(
        "blindness",
        Potion(StatusEffectInstance(StatusEffects.BLINDNESS, 300, 0)),
        Potions.AWKWARD,
        Items.INK_SAC
    )

    private fun registerPotionWithRecipe(id: String, potion: Potion, inputPotion: Potion, ingredient: Item): Potion {
        BrewingRecipeRegistry.registerPotionRecipe(inputPotion, ingredient, potion)
        return register(id, potion)
    }

    private fun register(id: String, potion: Potion): Potion {
        return Registry.register(Registries.POTION, Identifier(HybridAquatic.MOD_ID, id), potion)
    }
}