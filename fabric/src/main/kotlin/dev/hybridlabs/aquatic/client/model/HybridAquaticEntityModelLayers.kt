package dev.hybridlabs.aquatic.client.model

import com.google.common.collect.ImmutableMap
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.*
import dev.hybridlabs.aquatic.mixin.client.SkullBlockEntityRendererMixin
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.TexturedModelDataProvider
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.model.geom.EntityModelSet
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.SkullBlock

/**
 * The registry of all entity model layers in Hybrid Aquatic.
 */
object HybridAquaticEntityModelLayers {
    private val BASKING_SHARK_PLUSHIE = register("basking_shark_plushie", BaskingSharkPlushieModel::createModelData)
    private val BULL_SHARK_PLUSHIE = register("bull_shark_plushie", BullSharkPlushieModel::createModelData)
    private val FRILLED_SHARK_PLUSHIE = register("frilled_shark_plushie", FrilledSharkPlushieModel::createModelData)
    private val GREAT_WHITE_SHARK_PLUSHIE =
        register("great_white_shark_plushie", GreatWhiteSharkPlushieModel::createModelData)
    private val HAMMERHEAD_SHARK_PLUSHIE =
        register("hammerhead_shark_plushie", HammerheadSharkPlushieModel::createModelData)
    private val THRESHER_SHARK_PLUSHIE = register("thresher_shark_plushie", ThresherSharkPlushieModel::createModelData)
    private val TIGER_SHARK_PLUSHIE = register("tiger_shark_plushie", TigerSharkPlushieModel::createModelData)
    private val WHALE_SHARK_PLUSHIE = register("whale_shark_plushie", WhaleSharkPlushieModel::createModelData)

    private fun register(id: String, modelProvider: TexturedModelDataProvider): ModelLayerLocation {
        val layer = ModelLayerLocation(ResourceLocation(Constants.MOD_ID, id), "main")
        registerModelLayer(layer, modelProvider)
        return layer
    }

    /**
     * Injects Plushie models into the given [builder].
     * @see SkullBlockEntityRendererMixin
     */
    fun injectModels(
        modelLoader: EntityModelSet,
        builder: ImmutableMap.Builder<SkullBlock.Type, SkullModelBase>
    ) {
        builder.put(
            PlushieBlock.Variant.BASKING_SHARK,
            BaskingSharkPlushieModel(modelLoader.bakeLayer(BASKING_SHARK_PLUSHIE))
        )
        builder.put(
            PlushieBlock.Variant.BULL_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(BULL_SHARK_PLUSHIE))
        )
        builder.put(
            PlushieBlock.Variant.FRILLED_SHARK,
            FrilledSharkPlushieModel(modelLoader.bakeLayer(FRILLED_SHARK_PLUSHIE))
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
            PlushieBlock.Variant.TIGER_SHARK,
            TigerSharkPlushieModel(modelLoader.bakeLayer(TIGER_SHARK_PLUSHIE))
        )
        builder.put(
            PlushieBlock.Variant.WHALE_SHARK,
            WhaleSharkPlushieModel(modelLoader.bakeLayer(WHALE_SHARK_PLUSHIE))
        )
    }
}
