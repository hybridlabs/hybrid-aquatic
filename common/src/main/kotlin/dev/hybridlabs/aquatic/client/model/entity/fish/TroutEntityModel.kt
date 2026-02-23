package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.TroutEntity
import net.minecraft.resources.ResourceLocation

class TroutEntityModel : HybridAquaticFishEntityModel<TroutEntity>("trout") {

    private val BULL_TROUT_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/trout/bull_trout.png")
    private val REDBAND_TROUT_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/trout/redband_trout.png")

    private val BULL_TROUT_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/trout/bull_trout.geo.json")
    private val REDBAND_TROUT_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/trout/redband_trout.geo.json")

    private val BULL_TROUT_ANIMATION = ResourceLocation("hybrid-aquatic", "animations/entity/fish/trout/bull_trout.animation.json")
    private val REDBAND_TROUT_ANIMATION = ResourceLocation("hybrid-aquatic", "animations/entity/fish/trout/redband_trout.animation.json")

    override fun getTextureResource(animatable: TroutEntity): ResourceLocation {
        return when (animatable.variant) {
            TroutEntity.Companion.Type.BULL_TROUT -> BULL_TROUT_TEXTURE
            TroutEntity.Companion.Type.REDBAND_TROUT -> REDBAND_TROUT_TEXTURE
        }
    }

    override fun getModelResource(animatable: TroutEntity): ResourceLocation {
        return when (animatable.variant) {
            TroutEntity.Companion.Type.BULL_TROUT -> BULL_TROUT_MODEL
            TroutEntity.Companion.Type.REDBAND_TROUT -> REDBAND_TROUT_MODEL
        }
    }

    override fun getAnimationResource(animatable: TroutEntity): ResourceLocation {
        return when (animatable.variant) {
            TroutEntity.Companion.Type.BULL_TROUT -> BULL_TROUT_ANIMATION
            TroutEntity.Companion.Type.REDBAND_TROUT -> REDBAND_TROUT_ANIMATION
        }
    }
}
