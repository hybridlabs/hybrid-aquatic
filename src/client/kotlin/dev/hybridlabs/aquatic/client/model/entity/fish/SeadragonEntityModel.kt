package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SeadragonEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class SeadragonEntityModel : HybridAquaticFishEntityModel<SeadragonEntity>("seadragon") {
    override fun getRenderType(animatable: SeadragonEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }

    private val LEAFY_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/seadragon/leafy_seadragon.png")
    private val WEEDY_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/seadragon/weedy_seadragon.png")
    private val RUBY_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/seadragon/ruby_seadragon.png")

    private val LEAFY_MODEL = Identifier("hybrid-aquatic", "geo/fish/seadragon/leafy_seadragon.geo.json")
    private val WEEDY_MODEL = Identifier("hybrid-aquatic", "geo/fish/seadragon/weedy_seadragon.geo.json")
    private val RUBY_MODEL = Identifier("hybrid-aquatic", "geo/fish/seadragon/ruby_seadragon.geo.json")

    private val LEAFY_ANIMATION = Identifier("hybrid-aquatic", "animations/seadragon_leafy.animation.json")
    private val WEEDY_ANIMATION = Identifier("hybrid-aquatic", "animations/seadragon_weedy.animation.json")
    private val RUBY_ANIMATION = Identifier("hybrid-aquatic", "animations/seadragon_ruby.animation.json")

    override fun getTextureResource(animatable: SeadragonEntity): Identifier {
        return when (animatable.variant) {
            SeadragonEntity.Type.LEAFY -> LEAFY_TEXTURE
            SeadragonEntity.Type.WEEDY -> WEEDY_TEXTURE
            SeadragonEntity.Type.RUBY -> RUBY_TEXTURE
        }
    }

    override fun getModelResource(animatable: SeadragonEntity): Identifier {
        return when (animatable.variant) {
            SeadragonEntity.Type.LEAFY -> LEAFY_MODEL
            SeadragonEntity.Type.WEEDY -> WEEDY_MODEL
            SeadragonEntity.Type.RUBY -> RUBY_MODEL
        }
    }

    override fun getAnimationResource(animatable: SeadragonEntity): Identifier {
        return when (animatable.variant) {
            SeadragonEntity.Type.LEAFY -> LEAFY_ANIMATION
            SeadragonEntity.Type.WEEDY -> WEEDY_ANIMATION
            SeadragonEntity.Type.RUBY -> RUBY_ANIMATION
        }
    }
}