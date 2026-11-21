package dev.hybridlabs.aquatic.client.model.entity.miniboss

import software.bernie.geckolib.constant.DataTickets

import software.bernie.geckolib.renderer.base.GeoRenderState

import software.bernie.geckolib.renderer.base.BoneSnapshots

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import dev.hybridlabs.aquatic.entity.miniboss.KarcinomaEntity
import net.minecraft.client.model.geom.PartNames
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.state.AnimationTest

class KarcinomaEntityModel : HAMinionEntityModel<KarcinomaEntity>("karcinoma"), HABoneAdjustingModel {

    override fun adjustModelBones(renderState: GeoRenderState, snapshots: BoneSnapshots) {
        val pitch = Mth.clamp(renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_PITCH, 0f) ?: 0f, -45f, 45f)

        snapshots.get(PartNames.BODY).ifPresent { bone ->
            bone.setRotX(pitch * -Mth.DEG_TO_RAD)
        }
    }

}