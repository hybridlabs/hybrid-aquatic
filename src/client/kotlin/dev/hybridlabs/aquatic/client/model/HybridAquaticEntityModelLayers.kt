package dev.hybridlabs.aquatic.client.model

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.BaskingSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.BullSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.FrilledSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.GreatWhiteSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.HammerheadSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.ThresherSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.TigerSharkBlahajPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.WhaleSharkBlahajPlushieModel
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.TexturedModelDataProvider
import net.minecraft.client.render.entity.model.EntityModelLayer
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
}
