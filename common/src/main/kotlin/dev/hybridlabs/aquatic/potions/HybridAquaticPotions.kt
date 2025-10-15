@file:Suppress("unused", "SameParameterValue")

package dev.hybridlabs.aquatic.potions

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.core.Holder
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potion
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
        Potion(MobEffectInstance(HybridAquaticMobEffects.CLARITY.asHolder(), 1200, 0))
    }

    val THALASSOPHOBIA_POTION = register(
        "thalassophobia"
    ) {
        Potion(MobEffectInstance(HybridAquaticMobEffects.THALASSOPHOBIA.asHolder(), 1200, 0))
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
        Potion(MobEffectInstance(HybridAquaticMobEffects.BLEEDING.asHolder(), 200, 0))
    }

    val SWIMMING_POTION = register(
        "swimming"
    ) {
        Potion(
            MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1200, 0), MobEffectInstance(MobEffects.HUNGER, 600, 0)
        )
    }

    val BUOYANCY_POTION = register(
        "buoyancy"
    ) {
        Potion(MobEffectInstance(HybridAquaticMobEffects.BUOYANCY.asHolder(), 200, 0))
    }

    val THORNS_POTION = register(
        "thorns"
    ) {
        Potion(MobEffectInstance(HybridAquaticMobEffects.THORNS.asHolder(), 300, 0))
    }

    val CORROSION_POTION = register(
        "corrosion"
    ) {
        Potion(MobEffectInstance(HybridAquaticMobEffects.CORROSION.asHolder(), 300, 0))
    }

    val BLINDNESS_POTION = register(
        "blindness"
    ) {
        Potion(MobEffectInstance(MobEffects.BLINDNESS, 300, 0))
    }

    private fun register(id: String, potion: Supplier<Potion>): RegistryObject<Potion> {
        return CommonClass.POTIONS.register(id, potion)
    }

    data class PotionRecipe(val inputPotion: Holder<Potion>, val addition: Item, val outputPotion: Holder<Potion>)

    val recipes = Supplier {
        listOf(
            PotionRecipe(Potions.AWKWARD, Items.INK_SAC, BLINDNESS_POTION.asHolder()),
            PotionRecipe(Potions.AWKWARD, HybridAquaticItems.SULFUR.get(), CORROSION_POTION.asHolder()),
            PotionRecipe(Potions.AWKWARD, HybridAquaticItems.SEA_URCHIN_SPINE.get(), THORNS_POTION.asHolder()),
            PotionRecipe(Potions.AWKWARD, Items.KELP, BUOYANCY_POTION.asHolder()),
            PotionRecipe(Potions.AWKWARD, HybridAquaticItems.MAHI.get(), SWIMMING_POTION.asHolder()),
            PotionRecipe(Potions.AWKWARD, HybridAquaticItems.SHARK_TOOTH.get(), BLEEDING_POTION.asHolder()),
            PotionRecipe(Potions.AWKWARD, HybridAquaticItems.BLACK_PEARL.get(), MAJOR_LUCK_POTION.asHolder()),
            PotionRecipe(Potions.AWKWARD, HybridAquaticItems.PEARL.get(), MINOR_LUCK_POTION.asHolder()),
            PotionRecipe(Potions.AWKWARD, HybridAquaticItems.ANGLERFISH.get(), THALASSOPHOBIA_POTION.asHolder()),
            PotionRecipe(Potions.AWKWARD, HybridAquaticItems.BARRELEYE.get(), CLARITY_POTION.asHolder()),
            PotionRecipe(Potions.AWKWARD, Items.GLOW_INK_SAC, GLOWING_POTION.asHolder())
        )
    }
}
