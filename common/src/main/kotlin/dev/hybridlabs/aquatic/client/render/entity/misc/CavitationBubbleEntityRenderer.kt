package dev.hybridlabs.aquatic.client.render.entity.misc

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.resources.Identifier
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.rendertype.RenderType
import dev.hybridlabs.aquatic.client.model.entity.misc.CavitationBubbleEntityModel
import dev.hybridlabs.aquatic.entity.projectile.CavitationBubbleEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.base.BoneSnapshots
import software.bernie.geckolib.renderer.base.RenderPassInfo
import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer
import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

class CavitationBubbleEntityRenderer(
    context: EntityRendererProvider.Context
) : GeoEntityRenderer<CavitationBubbleEntity, LivingEntityRenderState>(context, CavitationBubbleEntityModel()) {

    init {
        withRenderLayer(AutoGlowingGeoLayer(this))
        this.shadowRadius = 0.5f
    }

    override fun getMotionAnimThreshold(animatable: CavitationBubbleEntity): Float {
        return 0.0025f
    }

    override fun getRenderType(
        renderState: LivingEntityRenderState,
        texture: Identifier
    ): RenderType {
        return RenderTypes.entityTranslucent(texture)
    }

    override fun adjustModelBonesForRender(
        renderPassInfo: RenderPassInfo<LivingEntityRenderState>,
        snapshots: BoneSnapshots
    ) {
        (getGeoModel() as? HABoneAdjustingModel)?.adjustModelBones(renderPassInfo.renderState(), snapshots)
    }
}