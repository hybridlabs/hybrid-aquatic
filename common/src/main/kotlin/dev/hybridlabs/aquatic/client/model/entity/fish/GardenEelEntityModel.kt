package dev.hybridlabs.aquatic.client.model.entity.fish

import software.bernie.geckolib.renderer.base.GeoRenderState

import software.bernie.geckolib.renderer.base.BoneSnapshots

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.GardenEelEntity
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.state.AnimationTest
import software.bernie.geckolib.constant.DataTickets

class GardenEelEntityModel : BaseFishEntityModel<GardenEelEntity>("hybrid_aquatic", "garden_eel"), HABoneAdjustingModel {

    override fun adjustModelBones(renderState: GeoRenderState, snapshots: BoneSnapshots) {
        val headPitch = renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_PITCH, 0f) ?: 0f
        val netHeadYaw = renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_YAW, 0f) ?: 0f

        snapshots.get("head").ifPresent { head ->
            head.setRotX(headPitch * Mth.DEG_TO_RAD)
            head.setRotY(netHeadYaw * Mth.DEG_TO_RAD)
        }
    }

}
