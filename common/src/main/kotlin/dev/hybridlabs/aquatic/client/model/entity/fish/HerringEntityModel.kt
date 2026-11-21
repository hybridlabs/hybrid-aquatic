package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.HerringEntity
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import com.geckolib.animation.state.AnimationTest
import kotlin.math.abs

class HerringEntityModel : BaseFishEntityModel<HerringEntity>("hybrid_aquatic", "herring") {

    companion object {
        private val ONE_FISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/herring/herring")
        private val TWO_FISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/herring/herring_two")
        private val THREE_FISH_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "fish/herring/herring_three")

        private val ONE_FISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/herring/herring")
        private val TWO_FISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/herring/herring_two")
        private val THREE_FISH_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/fish/herring/herring_three")
    }

    override fun addAdditionalStateData(
        animatable: HerringEntity,
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

    override fun getAnimationResource(animatable: HerringEntity): Identifier {
        val fish = animatable.getFishCount()

        return when (fish) {
            1 -> ONE_FISH_ANIMATION
            2 -> TWO_FISH_ANIMATION
            3 -> THREE_FISH_ANIMATION
            else -> ONE_FISH_ANIMATION
        }
    }

}
