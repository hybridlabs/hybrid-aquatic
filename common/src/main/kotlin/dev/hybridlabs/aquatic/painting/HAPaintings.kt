package dev.hybridlabs.aquatic.painting

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants.MOD_ID
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.decoration.PaintingVariant
import java.util.function.Supplier

object HAPaintings {
    fun register(id: String, painting: Supplier<PaintingVariant>): Supplier<PaintingVariant> {
        return CommonClass.PAINTINGS.register(id, painting)
    }

    fun registerPainting(id: String, widthBlocks: Int, heightBlocks: Int): ResourceLocation {
        register(id) { PaintingVariant(widthBlocks * 16, heightBlocks * 16) }
        return ResourceLocation(MOD_ID, id)
    }
}