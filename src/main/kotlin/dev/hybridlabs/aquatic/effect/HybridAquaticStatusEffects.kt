package dev.hybridlabs.aquatic.effect

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object HybridAquaticStatusEffects {
    val BLEEDING = register("bleeding", BleedingStatusEffect())
    val CLARITY = register("clarity", ClarityStatusEffect())
    val THALASSOPHOBIA = register("thalassophobia", ThalassophobiaStatusEffect())
    val BUOYANCY = register("buoyancy", BuoyancyStatusEffect())
    val SPININESS = register("spininess", SpininessStatusEffect())
    val CORROSION = register("corrosion", CorrosionStatusEffect())

    private fun register(id: String, effect: StatusEffect): StatusEffect {
        return Registry.register(Registry.STATUS_EFFECT, Identifier(HybridAquatic.MOD_ID, id), effect)
    }
}
