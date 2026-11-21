package dev.hybridlabs.aquatic.client.model.entity.mammal

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

import dev.hybridlabs.aquatic.client.model.HADataTickets

import software.bernie.geckolib.renderer.base.BoneSnapshots

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import dev.hybridlabs.aquatic.entity.mammal.OtterEntity.Companion.OtterAction
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseMammalEntityModel
import net.minecraft.client.model.geom.PartNames
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.state.AnimationTest
import software.bernie.geckolib.constant.DataTickets

class OtterEntityModel : BaseMammalEntityModel<OtterEntity>("hybrid_aquatic", "otter"), HABoneAdjustingModel {

    companion object {
        private val RIVER_OTTER_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/otter/river_otter.png")
        private val BABY_RIVER_OTTER_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/otter/baby_river_otter.png")
        private val SEA_OTTER_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/otter/sea_otter.png")
        private val BABY_SEA_OTTER_TEXTURE =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/otter/baby_sea_otter.png")

        private val RIVER_OTTER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "mammal/otter/river_otter")
        private val BABY_RIVER_OTTER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "mammal/otter/baby_river_otter")
        private val SEA_OTTER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "mammal/otter/sea_otter")
        private val BABY_SEA_OTTER_MODEL =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "mammal/otter/baby_sea_otter")

        private val RIVER_OTTER_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/mammal/otter/river_otter")
        private val SEA_OTTER_ANIMATION =
            Identifier.fromNamespaceAndPath("hybrid_aquatic", "entity/mammal/otter/sea_otter")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            when (renderState.getVariant(OtterEntity.Companion.Type.entries)!!) {
                OtterEntity.Companion.Type.RIVER -> BABY_RIVER_OTTER_TEXTURE
                OtterEntity.Companion.Type.SEA -> BABY_SEA_OTTER_TEXTURE
            }
        } else {
            when (renderState.getVariant(OtterEntity.Companion.Type.entries)!!) {
                OtterEntity.Companion.Type.RIVER -> RIVER_OTTER_TEXTURE
                OtterEntity.Companion.Type.SEA -> SEA_OTTER_TEXTURE
            }
        }
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return if ((renderState.getGeckolibData(HAPIDataTickets.IS_BABY) == true)) {
            when (renderState.getVariant(OtterEntity.Companion.Type.entries)!!) {
                OtterEntity.Companion.Type.RIVER -> BABY_RIVER_OTTER_MODEL
                OtterEntity.Companion.Type.SEA -> BABY_SEA_OTTER_MODEL
            }
        } else {
            when (renderState.getVariant(OtterEntity.Companion.Type.entries)!!) {
                OtterEntity.Companion.Type.RIVER -> RIVER_OTTER_MODEL
                OtterEntity.Companion.Type.SEA -> SEA_OTTER_MODEL
            }
        }
    }

    override fun getAnimationResource(animatable: OtterEntity): Identifier {
        return when (animatable.variant) {
            OtterEntity.Companion.Type.RIVER -> RIVER_OTTER_ANIMATION
            OtterEntity.Companion.Type.SEA -> SEA_OTTER_ANIMATION
        }
    }

    override fun addAdditionalStateData(
        animatable: OtterEntity,
        relatedObject: Any?,
        renderState: GeoRenderState
    ) {
        renderState.addGeckolibData(HADataTickets.ON_GROUND, animatable.onGround())
        renderState.addGeckolibData(HADataTickets.FLOATING, animatable.getAction() == OtterAction.FLOATING)
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
