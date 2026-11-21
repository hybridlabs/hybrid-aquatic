package dev.hybridlabs.aquatic.client.model.entity.miniboss

import software.bernie.geckolib.constant.DataTickets

import software.bernie.geckolib.renderer.base.GeoRenderState

import software.bernie.geckolib.renderer.base.BoneSnapshots

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import dev.hybridlabs.aquatic.entity.miniboss.ShellBeastEntity
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.state.AnimationTest

class ShellBeastEntityModel : HAMinibossEntityModel<ShellBeastEntity>("shell_beast"), HABoneAdjustingModel {

    override fun adjustModelBones(renderState: GeoRenderState, snapshots: BoneSnapshots) {
        val pitch = Mth.clamp(renderState.getOrDefaultGeckolibData(DataTickets.ENTITY_PITCH, 0f) ?: 0f, -45f, 45f)

        snapshots.get("shell_beast").ifPresent { bone ->
            bone.setRotX(pitch * -Mth.DEG_TO_RAD)
        }
    }

}