package dev.hybridlabs.aquatic.effects

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticStatusEffects {
    val BLEEDING: StatusEffect = Bleeding()

    private fun registerEffects() {
        Registry.register(Registries.STATUS_EFFECT, Identifier("hybrid_aquatic", "bleeding"), BLEEDING)
    }
}
