package dev.hybridlabs.aquatic.effect

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticStatusEffects {
    val BLEEDING = register("bleeding", BleedingStatusEffect())
    val CLARITY = register("clarity", ClarityStatusEffect())
    val THALASSOPHOBIA = register("thalassophobia", ThalassophobiaStatusEffect())
    val BUOYANCY = register("buoyancy", BuoyancyStatusEffect())
    val SPININESS = register("spininess", SpininessStatusEffect())
    val CORROSION = register("corrosion", CorrosionStatusEffect())
    val TOXIC_SHOCK = register("toxic_shock", ToxicShockStatusEffect())

    private fun register(id: String, effect: StatusEffect): StatusEffect {
        return Registry.register(Registries.STATUS_EFFECT, Identifier(HybridAquatic.MOD_ID, id), effect)
    }
}
