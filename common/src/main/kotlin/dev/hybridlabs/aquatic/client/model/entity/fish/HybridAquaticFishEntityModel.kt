package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticFishEntityModel<T : HybridAquaticFishEntity>(private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T): ResourceLocation {
        return CommonClass.locate("geo/fish/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return CommonClass.locate("textures/entity/fish/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/$id.animation.json")
    }

    fun getLayerTextureResource(layer: String): ResourceLocation {
        return CommonClass.locate("textures/entity/fish/$id/layers/${id}_$layer.png")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val timer= Minecraft.getInstance().timer

        val body = animationProcessor.getBone(PartNames.BODY)

        val xRot = Mth.clamp(Mth.lerp(timer.gameTimeDeltaTicks, animatable.xRot, animatable.xRotO), -45f, 45f)
        body.rotX = xRot * -Mth.DEG_TO_RAD
    }
}