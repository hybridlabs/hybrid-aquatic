package dev.hybridlabs.aquatic.effect

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticStatusEffects {
    val BLEEDING = register("bleeding", Bleeding())

    private fun register(id: String, effect: StatusEffect): StatusEffect {
        return Registry.register(Registries.STATUS_EFFECT, Identifier(HybridAquatic.MOD_ID, id), effect)
    }
}
