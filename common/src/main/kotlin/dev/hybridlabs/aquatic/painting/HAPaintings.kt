package dev.hybridlabs.aquatic.painting

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.decoration.PaintingVariant

object HAPaintings {
    val TEST_PAINTING1 = key("test_painting1")
    val TEST_PAINTING2 = key("test_painting2")

    fun key(id: String): ResourceKey<PaintingVariant> {
        return ResourceKey.create(Registries.PAINTING_VARIANT, CommonClass.locate(id))
    }
}