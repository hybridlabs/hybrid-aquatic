package dev.hybridlabs.aquatic.effect

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.effect.MobEffect

object HybridAquaticMobEffects {
    val BLEEDING = register("bleeding", ::BleedingMobEffect)
    val CLARITY = register("clarity", ::ClarityMobEffect)
    val THALASSOPHOBIA = register("thalassophobia", ::ThalassophobiaMobEffect)
    val BUOYANCY = register("buoyancy", ::BuoyancyMobEffect)
    val THORNS = register("thorns", ::ThornsMobEffect)
    val CORROSION = register("corrosion", ::CorrosionMobEffect)

    private fun register(id: String, effect: () -> MobEffect): RegistryObject<MobEffect> {
        return CommonClass.MOB_EFFECTS.register(id, effect)
    }
}
