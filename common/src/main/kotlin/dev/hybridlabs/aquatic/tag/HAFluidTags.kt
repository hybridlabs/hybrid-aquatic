package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.material.Fluid

object HAFluidTags {
    val BRINE = create("brine")

    private fun create(id: String): TagKey<Fluid> {
        return TagKey.create(Registries.FLUID, CommonClass.locate(id))
    }
}
