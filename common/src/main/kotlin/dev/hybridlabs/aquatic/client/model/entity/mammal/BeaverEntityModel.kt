package dev.hybridlabs.aquatic.client.model.entity.mammal

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

import dev.hybridlabs.aquatic.client.model.HADataTickets

import com.geckolib.renderer.base.BoneSnapshots

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.mammal.BeaverEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseMammalEntityModel
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import com.geckolib.animation.state.AnimationTest
import com.geckolib.constant.DataTickets

class BeaverEntityModel : BaseMammalEntityModel<BeaverEntity>("hybrid_aquatic", "beaver"), HABoneAdjustingModel {

    companion object {
        private val BEAVER_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/beaver/beaver.png")
        private val BABY_BEAVER_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/beaver/baby_beaver.png")

        private val BEAVER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "mammal/beaver/beaver")
        private val BABY_BEAVER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "mammal/beaver/baby_beaver")

        private val BEAVER_ANIMATION =
            Identifier.fromNamespaceAndPath(
                "hybrid_aquatic",
                "entity/mammal/beaver/beaver"
            )
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            BABY_BEAVER_TEXTURE
        } else {
            BEAVER_TEXTURE
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            BABY_BEAVER_MODEL
        } else {
            BEAVER_MODEL
        }
    }

    override fun getAnimationResource(animatable: BeaverEntity): Identifier {
        return BEAVER_ANIMATION
    }

    override fun addAdditionalStateData(
        animatable: BeaverEntity,
        relatedObject: Any?,
        renderState: GeoRenderState
    ) {
        renderState.addGeckolibData(HADataTickets.ON_GROUND, animatable.onGround())
        renderState.addGeckolibData(HADataTickets.FLOATING, animatable.getAction() == BeaverEntity.Companion.OtterAction.FLOATING)
    }

    override fun adjustModelBones(renderState: GeoRenderState, snapshots: BoneSnapshots) {
        val floating = renderState.getGeckolibData(HADataTickets.FLOATING) == true
        val isMoving = renderState.getOrDefaultGeckolibData(DataTickets.IS_MOVING, false) ?: false
        val inWater = (renderState as? LivingEntityRenderState)?.isInWater == true
        val onGround = renderState.getGeckolibData(HADataTickets.ON_GROUND) == true

        if (!isMoving && inWater && !onGround && floating) {
            snapshots.get(PartNames.BODY).ifPresent { bone ->
                bone.setRotX(Mth.lerp(0.1f, bone.getRotX(), 0f))
            }
        } else {
            val pitch = Mth.clamp(
                renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_PITCH, 0f) ?: 0f,
                -45f,
                45f
            )

            snapshots.get(PartNames.BODY).ifPresent { bone ->
                bone.setRotX(pitch * -Mth.DEG_TO_RAD)
            }
        }
    }

}
