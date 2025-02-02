package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.impl.tag.convention.TagRegistration
import net.minecraft.fluid.Fluid
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object HybridAquaticFluidTags {
    val BRINE = createConventional("brine")

    private fun create(id: String): TagKey<Fluid> {
        return TagKey.of(RegistryKeys.FLUID, Identifier(HybridAquatic.MOD_ID, id))
    }

    private fun createConventional(id: String): TagKey<Fluid> {
        @Suppress("UnstableApiUsage")
        return TagRegistration.FLUID_TAG_REGISTRATION.registerC(id)
    }
}
