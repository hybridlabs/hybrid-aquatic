package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.decoration.PaintingVariant

object HAPaintingTags {
    val UNIQUE_PAINTING = create("unique_paintings")

    fun create(id: String): TagKey<PaintingVariant> {
        return TagKey.create(Registries.PAINTING_VARIANT, CommonClass.locate(id))
    }
}