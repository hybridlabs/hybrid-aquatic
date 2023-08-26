package dev.hybridlabs.aquatic.client.model

import com.google.common.collect.ImmutableMap
import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.BlahajPlushieBlock
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.BaskingSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.BullSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.FrilledSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.GreatWhiteSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.HammerheadSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.ThresherSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.TigerSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.WhaleSharkBlahajPlushieModel
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
    val BASKING_SHARK_BLAHAJ_PLUSHIE = register("basking_shark_blahaj_plushie", BaskingSharkBlahajPlushieModel::createModelData)
    val BULL_SHARK_BLAHAJ_PLUSHIE = register("bull_shark_blahaj_plushie", BullSharkBlahajPlushieModel::createModelData)
    val FRILLED_SHARK_BLAHAJ_PLUSHIE = register("frilled_shark_blahaj_plushie", FrilledSharkBlahajPlushieModel::createModelData)
    val GREAT_WHITE_SHARK_BLAHAJ_PLUSHIE = register("great_white_shark_blahaj_plushie", GreatWhiteSharkBlahajPlushieModel::createModelData)
    val HAMMERHEAD_SHARK_BLAHAJ_PLUSHIE = register("hammerhead_shark_blahaj_plushie", HammerheadSharkBlahajPlushieModel::createModelData)
    val THRESHER_SHARK_BLAHAJ_PLUSHIE = register("thresher_shark_blahaj_plushie", ThresherSharkBlahajPlushieModel::createModelData)
    val TIGER_SHARK_BLAHAJ_PLUSHIE = register("tiger_shark_blahaj_plushie", TigerSharkBlahajPlushieModel::createModelData)
    val WHALE_SHARK_BLAHAJ_PLUSHIE = register("whale_shark_blahaj_plushie", WhaleSharkBlahajPlushieModel::createModelData)

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
        builder.put(BlahajPlushieBlock.Variant.BASKING_SHARK, BaskingSharkBlahajPlushieModel(modelLoader.getModelPart(BASKING_SHARK_BLAHAJ_PLUSHIE)))
        builder.put(BlahajPlushieBlock.Variant.BULL_SHARK, BullSharkBlahajPlushieModel(modelLoader.getModelPart(BULL_SHARK_BLAHAJ_PLUSHIE)))
        builder.put(BlahajPlushieBlock.Variant.FRILLED_SHARK, FrilledSharkBlahajPlushieModel(modelLoader.getModelPart(FRILLED_SHARK_BLAHAJ_PLUSHIE)))
        builder.put(BlahajPlushieBlock.Variant.GREAT_WHITE_SHARK, GreatWhiteSharkBlahajPlushieModel(modelLoader.getModelPart(GREAT_WHITE_SHARK_BLAHAJ_PLUSHIE)))
        builder.put(BlahajPlushieBlock.Variant.HAMMERHEAD_SHARK, HammerheadSharkBlahajPlushieModel(modelLoader.getModelPart(HAMMERHEAD_SHARK_BLAHAJ_PLUSHIE)))
        builder.put(BlahajPlushieBlock.Variant.THRESHER_SHARK, ThresherSharkBlahajPlushieModel(modelLoader.getModelPart(THRESHER_SHARK_BLAHAJ_PLUSHIE)))
        builder.put(BlahajPlushieBlock.Variant.TIGER_SHARK, TigerSharkBlahajPlushieModel(modelLoader.getModelPart(TIGER_SHARK_BLAHAJ_PLUSHIE)))
        builder.put(BlahajPlushieBlock.Variant.WHALE_SHARK, WhaleSharkBlahajPlushieModel(modelLoader.getModelPart(WHALE_SHARK_BLAHAJ_PLUSHIE)))
    }
}
