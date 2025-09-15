@file:Suppress("unused", "SameParameterValue")

package dev.hybridlabs.aquatic.potions

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.PotionBrewing
import net.minecraft.world.item.alchemy.Potions
import java.util.function.Supplier

object HybridAquaticPotions {

    val GLOWING_POTION = register(
        "glowing"
    ) {
        Potion(MobEffectInstance(MobEffects.GLOWING, 3600, 0))
    }

    val CLARITY_POTION = register(
        "clarity"
    ) {
        Potion(MobEffectInstance(HybridAquaticMobEffects.CLARITY.get(), 1200, 0))
    }

    val THALASSOPHOBIA_POTION = register(
        "thalassophobia"
    ) {
        Potion(MobEffectInstance(HybridAquaticMobEffects.THALASSOPHOBIA.get(), 1200, 0))
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
        Potion(MobEffectInstance(HybridAquaticMobEffects.BLEEDING.get(), 200, 0))
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
        Potion(MobEffectInstance(HybridAquaticMobEffects.BUOYANCY.get(), 200, 0))
    }

    val SPININESS_POTION = register(
        "spininess"
    ) {
        Potion(MobEffectInstance(HybridAquaticMobEffects.SPININESS.get(), 300, 0))
    }

    val CORROSION_POTION = register(
        "corrosion"
    ) {
        Potion(MobEffectInstance(HybridAquaticMobEffects.CORROSION.get(), 300, 0))
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
            Potions.AWKWARD, Items.INK_SAC, HybridAquaticPotions.BLINDNESS_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HybridAquaticItems.SULFUR.get(), HybridAquaticPotions.CORROSION_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HybridAquaticItems.SEA_URCHIN_SPINE.get(), HybridAquaticPotions.SPININESS_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, Items.KELP, HybridAquaticPotions.BUOYANCY_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HybridAquaticItems.MAHI.get(), HybridAquaticPotions.SWIMMING_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HybridAquaticItems.SHARK_TOOTH.get(), HybridAquaticPotions.BLEEDING_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HybridAquaticItems.BLACK_PEARL.get(), HybridAquaticPotions.MAJOR_LUCK_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HybridAquaticItems.PEARL.get(), HybridAquaticPotions.MINOR_LUCK_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HybridAquaticItems.ANGLERFISH.get(), HybridAquaticPotions.THALASSOPHOBIA_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, HybridAquaticItems.BARRELEYE.get(), HybridAquaticPotions.CLARITY_POTION.get()
        )
        PotionBrewing.addMix(
            Potions.AWKWARD, Items.GLOW_INK_SAC, HybridAquaticPotions.GLOWING_POTION.get()
        )
    }
}
