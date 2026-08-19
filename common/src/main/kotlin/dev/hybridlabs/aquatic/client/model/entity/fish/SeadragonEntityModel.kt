package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SeadragonEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.core.animation.AnimationState

class SeadragonEntityModel : BaseFishEntityModel<SeadragonEntity>("hybrid_aquatic", "seadragon") {
    override fun getRenderType(animatable: SeadragonEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    override fun getTextureResource(animatable: SeadragonEntity): ResourceLocation {
        return when (animatable.variant) {
            SeadragonEntity.Companion.Type.LEAFY -> LEAFY_TEXTURE
            SeadragonEntity.Companion.Type.WEEDY -> WEEDY_TEXTURE
            SeadragonEntity.Companion.Type.RUBY -> RUBY_TEXTURE
        }
    }

    override fun getModelResource(animatable: SeadragonEntity): ResourceLocation {
        return when (animatable.variant) {
            SeadragonEntity.Companion.Type.LEAFY -> LEAFY_MODEL
            SeadragonEntity.Companion.Type.WEEDY -> WEEDY_MODEL
            SeadragonEntity.Companion.Type.RUBY -> RUBY_MODEL
        }
    }

    override fun getAnimationResource(animatable: SeadragonEntity): ResourceLocation {
        return when (animatable.variant) {
            SeadragonEntity.Companion.Type.LEAFY -> LEAFY_ANIMATION
            SeadragonEntity.Companion.Type.WEEDY -> WEEDY_ANIMATION
            SeadragonEntity.Companion.Type.RUBY -> RUBY_ANIMATION
        }
    }

    override fun setCustomAnimations(
        animatable: SeadragonEntity,
        instanceId: Long,
        animationState: AnimationState<SeadragonEntity>,
    ) {
        val head = animationProcessor.getBone("head")

        if (head != null) {
            val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

            head.rotX = entityData.headPitch() * Mth.DEG_TO_RAD
            head.rotY = entityData.netHeadYaw() * Mth.DEG_TO_RAD
        }
    }

    companion object {
        private val LEAFY_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/fish/seadragon/leafy_seadragon.png")
        private val WEEDY_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/fish/seadragon/weedy_seadragon.png")
        private val RUBY_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/fish/seadragon/ruby_seadragon.png")

        private val LEAFY_MODEL =
            ResourceLocation("hybrid_aquatic", "geo/fish/seadragon/leafy_seadragon.geo.json")
        private val WEEDY_MODEL =
            ResourceLocation("hybrid_aquatic", "geo/fish/seadragon/weedy_seadragon.geo.json")
        private val RUBY_MODEL =
            ResourceLocation("hybrid_aquatic", "geo/fish/seadragon/ruby_seadragon.geo.json")

        private val LEAFY_ANIMATION =
            ResourceLocation("hybrid_aquatic", "animations/entity/fish/seadragon/seadragon_leafy.animation.json")
        private val WEEDY_ANIMATION =
            ResourceLocation("hybrid_aquatic", "animations/entity/fish/seadragon/seadragon_weedy.animation.json")
        private val RUBY_ANIMATION =
            ResourceLocation("hybrid_aquatic", "animations/entity/fish/seadragon/seadragon_ruby.animation.json")
    }
}
