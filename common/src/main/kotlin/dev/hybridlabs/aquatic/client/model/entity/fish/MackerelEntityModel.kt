package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.fish.MackerelEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.state.AnimationTest
import kotlin.math.abs

class MackerelEntityModel : BaseFishEntityModel<MackerelEntity>("hybrid_aquatic", "mackerel") {

    companion object {
        private val ONE_FISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/mackerel/mackerel")
        private val TWO_FISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/mackerel/mackerel_two")
        private val THREE_FISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/mackerel/mackerel_three")

        private val ONE_FISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/mackerel/mackerel")
        private val TWO_FISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/mackerel/mackerel_two")
        private val THREE_FISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/mackerel/mackerel_three")
    }

    override fun addAdditionalStateData(
        animatable: MackerelEntity,
        relatedObject: Any?,
        renderState: GeoRenderState
    ) {
        renderState.addGeckolibData(HADataTickets.FISH_COUNT, animatable.getFishCount())
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        val fish = renderState.getGeckolibData(HADataTickets.FISH_COUNT) ?: 1

        return when (fish) {
            1 -> ONE_FISH_MODEL
            2 -> TWO_FISH_MODEL
            3 -> THREE_FISH_MODEL
            else -> ONE_FISH_MODEL
        }
    }

    override fun getAnimationResource(animatable: MackerelEntity): Identifier {
        val fish = animatable.getFishCount()

        return when (fish) {
            1 -> ONE_FISH_ANIMATION
            2 -> TWO_FISH_ANIMATION
            3 -> THREE_FISH_ANIMATION
            else -> ONE_FISH_ANIMATION
        }
    }

}
