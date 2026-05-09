package dev.hybridlabs.aquatic.client.render.block

import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.BASKING_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.BULL_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.FRILLED_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.GREAT_WHITE_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.HAMMERHEAD_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.THRESHER_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.TIGER_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.WHALE_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.*
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer

object HAModelLayerRegistry {
    init {
        registerModelLayer(BASKING_SHARK_PLUSHIE, BaskingSharkPlushieModel.Companion::createModelData)
        registerModelLayer(BULL_SHARK_PLUSHIE, BullSharkPlushieModel.Companion::createModelData)
        registerModelLayer(FRILLED_SHARK_PLUSHIE, FrilledSharkPlushieModel.Companion::createModelData)
        registerModelLayer(GREAT_WHITE_SHARK_PLUSHIE, GreatWhiteSharkPlushieModel.Companion::createModelData)
        registerModelLayer(HAMMERHEAD_SHARK_PLUSHIE, HammerheadSharkPlushieModel.Companion::createModelData)
        registerModelLayer(THRESHER_SHARK_PLUSHIE, ThresherSharkPlushieModel.Companion::createModelData)
        registerModelLayer(TIGER_SHARK_PLUSHIE, TigerSharkPlushieModel.Companion::createModelData)
        registerModelLayer(WHALE_SHARK_PLUSHIE, WhaleSharkPlushieModel.Companion::createModelData)
    }
}