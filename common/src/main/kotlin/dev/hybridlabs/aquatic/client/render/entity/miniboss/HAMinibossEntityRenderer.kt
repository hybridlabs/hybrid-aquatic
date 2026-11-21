package dev.hybridlabs.aquatic.client.render.entity.miniboss

import software.bernie.geckolib.renderer.base.BoneSnapshots

import software.bernie.geckolib.renderer.base.RenderPassInfo

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.entity.base.HAMinibossEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import software.bernie.geckolib.model.GeoModel
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer

@Suppress("LeakingThis")
open class HAMinibossEntityRenderer<T : HAMinibossEntity>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false,
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