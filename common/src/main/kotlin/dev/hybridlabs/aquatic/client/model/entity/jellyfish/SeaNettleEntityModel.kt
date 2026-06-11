package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.SeaNettleEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class SeaNettleEntityModel : HAJellyfishEntityModel<SeaNettleEntity>("sea_nettle") {
    override fun getRenderType(animatable: SeaNettleEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    companion object {
        private val PACIFIC_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/jellyfish/sea_nettle/pacific_sea_nettle.png")
        private val COMPASS_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/jellyfish/sea_nettle/compass_jellyfish.png")

        private val PACIFIC_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/jellyfish/sea_nettle/sea_nettle.geo.json")
        private val COMPASS_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/jellyfish/sea_nettle/compass_jellyfish.geo.json")

        private val PACIFIC_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/jellyfish/sea_nettle/sea_nettle.animation.json")
        private val COMPASS_ANIMATION =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "animations/entity/jellyfish/sea_nettle/compass_jellyfish.animation.json")
    }
    override fun getTextureResource(animatable: SeaNettleEntity): ResourceLocation {
        return when (animatable.variant) {
            SeaNettleEntity.Companion.Type.PACIFIC -> PACIFIC_TEXTURE
            SeaNettleEntity.Companion.Type.COMPASS -> COMPASS_TEXTURE
        }
    }

    override fun getModelResource(animatable: SeaNettleEntity): ResourceLocation {
        return when (animatable.variant) {
            SeaNettleEntity.Companion.Type.PACIFIC -> PACIFIC_MODEL
            SeaNettleEntity.Companion.Type.COMPASS -> COMPASS_MODEL
        }
    }

    override fun getAnimationResource(animatable: SeaNettleEntity): ResourceLocation {
        return when (animatable.variant) {
            SeaNettleEntity.Companion.Type.PACIFIC -> PACIFIC_ANIMATION
            SeaNettleEntity.Companion.Type.COMPASS -> COMPASS_ANIMATION
        }
    }
}
