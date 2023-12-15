package dev.hybridlabs.aquatic.client.model

import com.google.common.collect.ImmutableMap
import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.*
import dev.hybridlabs.aquatic.client.model.item.FishingNetItemModel
import dev.hybridlabs.aquatic.mixin.client.SkullBlockEntityRendererMixin
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.TexturedModelDataProvider
import net.minecraft.block.SkullBlock
import net.minecraft.client.render.block.entity.SkullBlockEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelLoader
import net.minecraft.util.Identifier

/**
 * The registry of all entity model layers in Hybrid Aquatic.
 */
object HybridAquaticEntityModelLayers {
    val BASKING_SHARK_PLUSHIE = register("basking_shark_plushie", BaskingSharkPlushieModel::createModelData)
    val BULL_SHARK_PLUSHIE = register("bull_shark_plushie", BullSharkPlushieModel::createModelData)
    val FRILLED_SHARK_PLUSHIE = register("frilled_shark_plushie", FrilledSharkPlushieModel::createModelData)
    val GREAT_WHITE_SHARK_PLUSHIE = register("great_white_shark_plushie", GreatWhiteSharkPlushieModel::createModelData)
    val HAMMERHEAD_SHARK_PLUSHIE = register("hammerhead_shark_plushie", HammerheadSharkPlushieModel::createModelData)
    val THRESHER_SHARK_PLUSHIE = register("thresher_shark_plushie", ThresherSharkPlushieModel::createModelData)
    val TIGER_SHARK_PLUSHIE = register("tiger_shark_plushie", TigerSharkPlushieModel::createModelData)
    val WHALE_SHARK_PLUSHIE = register("whale_shark_plushie", WhaleSharkPlushieModel::createModelData)

    val FISHING_NET = register("fishing_net", FishingNetItemModel::createModelData)

    private fun register(id: String, modelProvider: TexturedModelDataProvider): EntityModelLayer {
        val layer = EntityModelLayer(Identifier(HybridAquatic.MOD_ID, id), "main")
        EntityModelLayerRegistry.registerModelLayer(layer, modelProvider)
        return layer
    }

    /**
     * Injects Blahaj Plushie models into the given [builder].
     * @see SkullBlockEntityRendererMixin
     */
    fun injectModels(
        modelLoader: EntityModelLoader,
        builder: ImmutableMap.Builder<SkullBlock.SkullType, SkullBlockEntityModel>
    ) {
        builder.put(PlushieBlock.Variant.BASKING_SHARK, BaskingSharkPlushieModel(modelLoader.getModelPart(BASKING_SHARK_PLUSHIE)))
        builder.put(PlushieBlock.Variant.BULL_SHARK, BullSharkPlushieModel(modelLoader.getModelPart(BULL_SHARK_PLUSHIE)))
        builder.put(PlushieBlock.Variant.FRILLED_SHARK, FrilledSharkPlushieModel(modelLoader.getModelPart(FRILLED_SHARK_PLUSHIE)))
        builder.put(PlushieBlock.Variant.GREAT_WHITE_SHARK, GreatWhiteSharkPlushieModel(modelLoader.getModelPart(GREAT_WHITE_SHARK_PLUSHIE)))
        builder.put(PlushieBlock.Variant.HAMMERHEAD_SHARK, HammerheadSharkPlushieModel(modelLoader.getModelPart(HAMMERHEAD_SHARK_PLUSHIE)))
        builder.put(PlushieBlock.Variant.THRESHER_SHARK, ThresherSharkPlushieModel(modelLoader.getModelPart(THRESHER_SHARK_PLUSHIE)))
        builder.put(PlushieBlock.Variant.TIGER_SHARK, TigerSharkPlushieModel(modelLoader.getModelPart(TIGER_SHARK_PLUSHIE)))
        builder.put(PlushieBlock.Variant.WHALE_SHARK, WhaleSharkPlushieModel(modelLoader.getModelPart(WHALE_SHARK_PLUSHIE)))
    }
}
