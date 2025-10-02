package dev.hybridlabs.aquatic.painting

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.world.entity.decoration.PaintingVariant
import java.util.function.Supplier

object HybridAquaticPaintings {
    val TEST_PAINTING1 = registerPainting("test_painting1", 32, 32)

    fun register(id: String, painting: Supplier<PaintingVariant>) {
        CommonClass.PAINTINGS.register(id, painting)
    }

    fun registerPainting(id: String, width: Int, height: Int) {
        register(id) { PaintingVariant(width, height) }
    }
}