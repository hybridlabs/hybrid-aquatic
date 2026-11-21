package dev.hybridlabs.aquatic.client.render.entity.miniboss

import com.geckolib.renderer.base.BoneSnapshots

import com.geckolib.renderer.base.RenderPassInfo

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.entity.base.HAMinionEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import com.geckolib.model.GeoModel
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import com.geckolib.renderer.GeoEntityRenderer
import com.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer

@Suppress("LeakingThis")
open class HAMinionEntityRenderer<T : HAMinionEntity>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>,
    canGlow: Boolean = false
) : GeoEntityRenderer<T, LivingEntityRenderState>(context, model) {

    init {
        if(canGlow) withRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun adjustModelBonesForRender(
        renderPassInfo: RenderPassInfo<LivingEntityRenderState>,
        snapshots: BoneSnapshots
    ) {
        (getGeoModel() as? HABoneAdjustingModel)?.adjustModelBones(renderPassInfo.renderState(), snapshots)
    }

}