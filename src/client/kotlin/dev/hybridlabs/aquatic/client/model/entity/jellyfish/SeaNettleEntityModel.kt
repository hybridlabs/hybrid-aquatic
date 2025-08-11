package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.SeaNettleEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class SeaNettleEntityModel : HybridAquaticJellyfishEntityModel<SeaNettleEntity>("sea_nettle") {
    override fun getRenderType(animatable: SeaNettleEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }

    private val PACIFIC_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/jellyfish/sea_nettle/pacific_sea_nettle.png")
    private val COMPASS_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/jellyfish/sea_nettle/compass_jellyfish.png")

    private val PACIFIC_MODEL = Identifier.of("hybrid-aquatic", "geo/jellyfish/sea_nettle/sea_nettle.geo.json")
    private val COMPASS_MODEL = Identifier.of("hybrid-aquatic", "geo/jellyfish/sea_nettle/compass_jellyfish.geo.json")

    private val PACIFIC_ANIMATION = Identifier.of("hybrid-aquatic", "animations/sea_nettle.animation.json")
    private val COMPASS_ANIMATION = Identifier.of("hybrid-aquatic", "animations/compass_jellyfish.animation.json")

    override fun getTextureResource(animatable: SeaNettleEntity): Identifier {
        return when (animatable.variant) {
            SeaNettleEntity.Companion.Type.PACIFIC -> PACIFIC_TEXTURE
            SeaNettleEntity.Companion.Type.COMPASS -> COMPASS_TEXTURE
        }
    }

    override fun getModelResource(animatable: SeaNettleEntity): Identifier {
        return when (animatable.variant) {
            SeaNettleEntity.Companion.Type.PACIFIC -> PACIFIC_MODEL
            SeaNettleEntity.Companion.Type.COMPASS -> COMPASS_MODEL
        }
    }

    override fun getAnimationResource(animatable: SeaNettleEntity): Identifier {
        return when (animatable.variant) {
            SeaNettleEntity.Companion.Type.PACIFIC -> PACIFIC_ANIMATION
            SeaNettleEntity.Companion.Type.COMPASS -> COMPASS_ANIMATION
        }
    }
}