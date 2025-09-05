@file:Suppress("unused", "SameParameterValue")

package dev.hybridlabs.aquatic.potions

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.PotionBrewing
import net.minecraft.world.item.alchemy.Potions

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
        HybridAquaticItems.BARRELEYE.get()
    )

    val THALASSOPHOBIA_POTION = registerPotionWithRecipe(
        "thalassophobia",
        Potion(MobEffectInstance(HybridAquaticMobEffects.THALASSOPHOBIA.get(), 1200, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.ANGLERFISH.get()
    )

    val MINOR_LUCK_POTION = registerPotionWithRecipe(
        "minor_luck",
        Potion(MobEffectInstance(MobEffects.LUCK, 1200, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.PEARL.get()
    )

    val MAJOR_LUCK_POTION = registerPotionWithRecipe(
        "major_luck",
        Potion(MobEffectInstance(MobEffects.LUCK, 600, 1)),
        Potions.AWKWARD,
        HybridAquaticItems.BLACK_PEARL.get()
    )

    val BLEEDING_POTION = registerPotionWithRecipe(
        "bleeding",
        Potion(MobEffectInstance(HybridAquaticMobEffects.BLEEDING.get(), 200, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.SHARK_TOOTH.get()
    )

    val SWIMMING_POTION = registerPotionWithRecipe(
        "swimming",
        Potion(
            MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1200, 0),
            MobEffectInstance(MobEffects.HUNGER, 600, 0)
        ),
        Potions.AWKWARD,
        HybridAquaticItems.MAHI.get()
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
        HybridAquaticItems.SEA_URCHIN_SPINE.get()
    )

    val CORROSION_POTION = registerPotionWithRecipe(
        "corrosion",
        Potion(MobEffectInstance(HybridAquaticMobEffects.CORROSION.get(), 300, 0)),
        Potions.AWKWARD,
        HybridAquaticItems.SULFUR.get()
    )

    val BLINDNESS_POTION = registerPotionWithRecipe(
        "blindness",
        Potion(MobEffectInstance(MobEffects.BLINDNESS, 300, 0)),
        Potions.AWKWARD,
        Items.INK_SAC
    )

    private fun registerPotionWithRecipe(id: String, potion: Potion, inputPotion: Potion, ingredient: Item): Potion {
        PotionBrewing.addMix()
        BrewingRecipeRegistry.registerPotionRecipe(inputPotion, ingredient, potion)
        return register(id, potion)
    }

    private fun register(id: String, potion: Potion): Potion {
        return Registry.register(Registries.POTION, ResourceLocation(Constants.MOD_ID, id), potion)
    }
}