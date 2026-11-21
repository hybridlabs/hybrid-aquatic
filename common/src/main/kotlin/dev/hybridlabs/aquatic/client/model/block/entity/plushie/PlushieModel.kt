package dev.hybridlabs.aquatic.client.model.block.entity.plushie

import net.minecraft.client.model.`object`.skull.SkullModelBase
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.util.Mth
import kotlin.math.sin

/**
 * Represents the model of a Blahaj Plushie.
 */
abstract class PlushieModel(root: ModelPart) : SkullModelBase(root) {
    /**
     * The jaw model part.
     */
    open val jaw: ModelPart? = null

    override fun setupAnim(state: SkullModelBase.State) {
        root.yRot = state.yRot * Mth.DEG_TO_RAD
        root.xRot = state.xRot * Mth.DEG_TO_RAD

        jaw?.xRot = (sin((state.animationPos * 3.1415927f * 0.2f).toDouble()) + 1.0).toFloat() * 0.2f
    }
}
