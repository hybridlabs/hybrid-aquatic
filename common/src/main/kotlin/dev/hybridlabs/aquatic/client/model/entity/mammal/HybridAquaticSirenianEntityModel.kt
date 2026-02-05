package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticSirenianEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
abstract class HybridAquaticSirenianEntityModel<T : HybridAquaticSirenianEntity>(private val id: String) :
    GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return CommonClass.locate("geo/mammal/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return CommonClass.locate("textures/entity/mammal/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/$id.animation.json")
    }

    fun getLayerTextureResource(layer: String): ResourceLocation {
        return CommonClass.locate("textures/entity/mammal/$id/layers/${id}_$layer.png")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>,
    ) {
        val head = animationProcessor.getBone("head")

        if (head != null) {
            val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

            head.rotX = entityData.headPitch() * Mth.DEG_TO_RAD
            head.rotY = entityData.netHeadYaw() * Mth.DEG_TO_RAD
        }
    }
}