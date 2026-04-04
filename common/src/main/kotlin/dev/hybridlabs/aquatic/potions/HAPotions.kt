@file:Suppress("unused", "SameParameterValue")

package dev.hybridlabs.aquatic.potions

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.effect.HAMobEffects
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.PotionBrewing
import net.minecraft.world.item.alchemy.Potions
import java.util.function.Supplier

object HAPotions {

    val GLOWING_POTION = register(
        "glowing"
    ) {
        Potion(MobEffectInstance(MobEffects.GLOWING, 3600, 0))
    }

    val CLARITY_POTION = register(
        "clarity"
    ) {
        Potion(MobEffectInstance(HAMobEffects.CLARITY.get(), 1200, 0))
    }

    val THALASSOPHOBIA_POTION = register(
        "thalassophobia"
    ) {
        Potion(MobEffectInstance(HAMobEffects.THALASSOPHOBIA.get(), 1200, 0))
    }

    val MINOR_LUCK_POTION = register(
        "minor_luck"
    ) {
        Potion(MobEffectInstance(MobEffects.LUCK, 1200, 0))
    }

    val MAJOR_LUCK_POTION = register(
        "major_luck"
    ) {
        Potion(MobEffectInstance(MobEffects.LUCK, 600, 1))
    }

    val BLEEDING_POTION = register(
        "bleeding"
    ) {
        Potion(MobEffectInstance(HAMobEffects.BLEEDING.get(), 200, 0))
    }

    val SWIMMING_POTION = register(
        "swimming"
    ) {
        Potion(
            MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1200, 0),
            MobEffectInstance(MobEffects.HUNGER, 600, 0)
        )
    }

    val BUOYANCY_POTION = register(
        "buoyancy"
    ) {
        Potion(MobEffectInstance(HAMobEffects.BUOYANCY.get(), 200, 0))
    }

    val THORNS_POTION = register(
        "thorns"
    ) {
        Potion(MobEffectInstance(HAMobEffects.THORNS.get(), 300, 0))
    }

    val CORROSION_POTION = register(
        "corrosion"
    ) {
        Potion(MobEffectInstance(HAMobEffects.CORROSION.get(), 300, 0))
    }

    val BLINDNESS_POTION = register(
        "blindness"
    ) {
        Potion(MobEffectInstance(MobEffects.BLINDNESS, 300, 0))
    }

    private fun register(id: String, potion: Supplier<Potion>): RegistryObject<Potion> {
        return CommonClass.POTIONS.register(id, potion)
    }

    fun registerPotionRecipes() {
        PotionBrewing.addMix(
            Potions.AWKWARD, Items.INK_SAC, BLINDNESS_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HAItems.SULFUR.get(), CORROSION_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HAItems.SEA_URCHIN_SPINE.get(), THORNS_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, Items.KELP, BUOYANCY_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HAItems.MAHI.get(), SWIMMING_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HAItems.SHARK_TOOTH.get(), BLEEDING_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HAItems.BLACK_PEARL.get(), MAJOR_LUCK_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HAItems.PEARL.get(), MINOR_LUCK_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HAItems.ANGLERFISH.get(), THALASSOPHOBIA_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HAItems.BARRELEYE.get(), CLARITY_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, Items.GLOW_INK_SAC, GLOWING_POTION.get()
        )
    }
}