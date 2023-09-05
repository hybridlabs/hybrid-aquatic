package dev.hybridlabs.aquatic.client.render

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.util.math.MatrixStack

class RenderUtils {
    companion object {
        fun renderSizedEntity(entity: HybridAquaticFishEntity?, poseStack: MatrixStack?) {
            val size = entity!!.size
            poseStack?.scale(size, size, size)
        }

    }
}