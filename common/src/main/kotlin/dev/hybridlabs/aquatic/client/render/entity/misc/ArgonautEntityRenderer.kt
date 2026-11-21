package dev.hybridlabs.aquatic.client.render.entity.misc

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.client.model.entity.misc.ArgonautEntityModel
import dev.hybridlabs.aquatic.client.render.entity.misc.layer.ArgonautSailEntityLayer
import dev.hybridlabs.aquatic.client.render.entity.misc.layer.ArgonautShellEntityLayer
import dev.hybridlabs.aquatic.client.render.entity.misc.layer.ArgonautVisorEntityLayer
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.base.BoneSnapshots
import software.bernie.geckolib.renderer.base.RenderPassInfo
import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer

class ArgonautEntityRenderer<T : ArgonautEntity>(
    context: EntityRendererProvider.Context
) : GeoEntityRenderer<T, LivingEntityRenderState>(context, ArgonautEntityModel()) {

    init {
        withRenderLayer(ArgonautVisorEntityLayer(this))
        withRenderLayer(ArgonautSailEntityLayer(this))
        withRenderLayer(ArgonautShellEntityLayer(this))
        withRenderLayer(AutoGlowingGeoLayer(this))
        this.shadowRadius = 0.3f
    }

    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }

    override fun getRenderType(renderState: LivingEntityRenderState, texture: Identifier): RenderType {
        return RenderTypes.entityCutoutNoCull(texture)
    }

    override fun addRenderData(
        animatable: T,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)

        val controller = if (animatable.hasControllingPassenger()) animatable.controllingPassenger!! else animatable
        renderState.addGeckolibData(HADataTickets.BODY_YAW, Mth.rotLerp(partialTick, controller.yRotO, controller.yRot))
        renderState.addGeckolibData(HADataTickets.BODY_TILT, Mth.rotLerp(partialTick, controller.xRotO, controller.xRot))
    }

    override fun adjustModelBonesForRender(
        renderPassInfo: RenderPassInfo<LivingEntityRenderState>,
        snapshots: BoneSnapshots
    ) {
        val yaw = renderPassInfo.getGeckolibData(HADataTickets.BODY_YAW) ?: return
        val tilt = renderPassInfo.getGeckolibData(HADataTickets.BODY_TILT) ?: return

        snapshots.get(PartNames.BODY).ifPresent { bone ->
            bone.setRotY(-yaw * Mth.DEG_TO_RAD)
            bone.setRotX(tilt * -Mth.DEG_TO_RAD)
        }
    }
}
