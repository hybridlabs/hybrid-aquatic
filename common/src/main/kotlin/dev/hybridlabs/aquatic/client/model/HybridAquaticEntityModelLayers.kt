package dev.hybridlabs.aquatic.client.model

import com.google.common.collect.ImmutableMap
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.*
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.model.geom.EntityModelSet
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.world.level.block.SkullBlock

/**
 * The registry of all entity model layers in Hybrid Aquatic.
 */
object HybridAquaticEntityModelLayers {
    val BASKING_SHARK_PLUSHIE = getModelLayerLocation("basking_shark_plushie")
    val BULL_SHARK_PLUSHIE = getModelLayerLocation("bull_shark_plushie")
    val FRILLED_SHARK_PLUSHIE = getModelLayerLocation("frilled_shark_plushie")
    val GREAT_WHITE_SHARK_PLUSHIE = getModelLayerLocation("great_white_shark_plushie")
    val HAMMERHEAD_SHARK_PLUSHIE = getModelLayerLocation("hammerhead_shark_plushie")
    val THRESHER_SHARK_PLUSHIE = getModelLayerLocation("thresher_shark_plushie")
    val TIGER_SHARK_PLUSHIE = getModelLayerLocation("tiger_shark_plushie")
    val WHALE_SHARK_PLUSHIE = getModelLayerLocation("whale_shark_plushie")

    private fun getModelLayerLocation(id: String): ModelLayerLocation {
        return ModelLayerLocation(CommonClass.locate(id), "main")
    }

    /**
     * Injects Plushie models into the given [builder].
     * @see dev.hybridlabs.aquatic.mixin.client.SkullBlockEntityModelMixin
     */
    fun injectModels(
        modelLoader: EntityModelSet, builder: ImmutableMap.Builder<SkullBlock.Type, SkullModelBase>
    ) {
        builder.put(
            PlushieBlock.Variant.BASKING_SHARK, BaskingSharkPlushieModel(modelLoader.bakeLayer(BASKING_SHARK_PLUSHIE))
        )
        builder.put(
            PlushieBlock.Variant.BULL_SHARK, BullSharkPlushieModel(modelLoader.bakeLayer(BULL_SHARK_PLUSHIE))
        )
        builder.put(
            PlushieBlock.Variant.FRILLED_SHARK, FrilledSharkPlushieModel(modelLoader.bakeLayer(FRILLED_SHARK_PLUSHIE))
        )
        builder.put(
            PlushieBlock.Variant.GREAT_WHITE_SHARK,
            GreatWhiteSharkPlushieModel(modelLoader.bakeLayer(GREAT_WHITE_SHARK_PLUSHIE))
        )
        builder.put(
            PlushieBlock.Variant.HAMMERHEAD_SHARK,
            HammerheadSharkPlushieModel(modelLoader.bakeLayer(HAMMERHEAD_SHARK_PLUSHIE))
        )
        builder.put(
            PlushieBlock.Variant.THRESHER_SHARK,
            ThresherSharkPlushieModel(modelLoader.bakeLayer(THRESHER_SHARK_PLUSHIE))
        )
        builder.put(
            PlushieBlock.Variant.TIGER_SHARK, TigerSharkPlushieModel(modelLoader.bakeLayer(TIGER_SHARK_PLUSHIE))
        )
        builder.put(
            PlushieBlock.Variant.WHALE_SHARK, WhaleSharkPlushieModel(modelLoader.bakeLayer(WHALE_SHARK_PLUSHIE))
        )
    }
}