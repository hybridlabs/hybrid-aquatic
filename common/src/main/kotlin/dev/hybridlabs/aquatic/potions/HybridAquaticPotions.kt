@file:Suppress("unused", "SameParameterValue")

package dev.hybridlabs.aquatic.potions

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.entity.effect.MobEffectInstance
import net.minecraft.entity.effect.MobEffects
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.potion.Potion
import net.minecraft.potion.Potions
import net.minecraft.recipe.BrewingRecipeRegistry
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.Potions
import net.minecraftforge.common.brewing.BrewingRecipeRegistry

object HybridAquaticPotions {
    val GLOWING_POTION = registerPotionWithRecipe(
        "glowing",
        Potion(MobEffectInstance(MobEffects.GLOWING, 3600, 0)),
        Potions.AWKWARD,
        Items.GLOW_INK_SAC
    )

    val CLARITY_POTION = registerPotionWithRecipe(
        "clarity",
        Potion(MobEffectInstance(HybridAquaticMobEffects.CLARITY.get(), 1200, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.BARRELEYE
    )

    val THALASSOPHOBIA_POTION = registerPotionWithRecipe(
        "thalassophobia",
        Potion(MobEffectInstance(HybridAquaticMobEffects.THALASSOPHOBIA.get(), 1200, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.ANGLERFISH
    )

    val MINOR_LUCK_POTION = registerPotionWithRecipe(
        "minor_luck",
        Potion(MobEffectInstance(MobEffects.LUCK, 1200, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.PEARL
    )

    val MAJOR_LUCK_POTION = registerPotionWithRecipe(
        "major_luck",
        Potion(MobEffectInstance(MobEffects.LUCK, 600, 1)),
        Potions.AWKWARD,
        HybridAquaticItems.BLACK_PEARL
    )

    val BLEEDING_POTION = registerPotionWithRecipe(
        "bleeding",
        Potion(MobEffectInstance(HybridAquaticMobEffects.BLEEDING.get(), 200, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.SHARK_TOOTH
    )

    val SWIMMING_POTION = registerPotionWithRecipe(
        "swimming",
        Potion(MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1200, 0),
            MobEffectInstance(MobEffects.HUNGER, 600, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.MAHI
    )

    val BUOYANCY_POTION = registerPotionWithRecipe(
        "buoyancy",
        Potion(MobEffectInstance(HybridAquaticMobEffects.BUOYANCY.get(), 200, 0)),
        Potions.AWKWARD,
        Items.KELP
    )

    val SPININESS_POTION = registerPotionWithRecipe(
        "spininess",
        Potion(MobEffectInstance(HybridAquaticMobEffects.SPININESS.get(), 300, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.SEA_URCHIN_SPINE
    )

    val CORROSION_POTION = registerPotionWithRecipe(
        "corrosion",
        Potion(MobEffectInstance(HybridAquaticMobEffects.CORROSION.get(), 300, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.SULFUR
    )

    val BLINDNESS_POTION = registerPotionWithRecipe(
        "blindness",
        Potion(MobEffectInstance(MobEffects.BLINDNESS, 300, 0)),
        Potions.AWKWARD,
        Items.INK_SAC
    )

    private fun registerPotionWithRecipe(id: String, potion: Potion, inputPotion: Potion, ingredient: Item): Potion {
        BrewingRecipeRegistry.registerPotionRecipe(inputPotion, ingredient, potion)
        return register(id, potion)
    }

    private fun register(id: String, potion: Potion): Potion {
        return Registry.register(Registries.POTION, ResourceLocation(Constants.MOD_ID, id), potion)
    }
}