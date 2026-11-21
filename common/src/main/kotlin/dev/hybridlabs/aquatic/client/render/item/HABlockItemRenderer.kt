package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.client.model.item.BlockItemGeoModel
import dev.hybridlabs.aquatic.client.render.block.entity.applyFacingTransform
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.level.block.entity.BlockEntity
import com.geckolib.animatable.GeoAnimatable
import com.geckolib.model.GeoModel
import com.geckolib.renderer.GeoItemRenderer
import com.geckolib.renderer.base.GeoRenderState
import com.geckolib.renderer.base.RenderPassInfo

/**
 * Renders the item form of a block using that block's GeckoLib block entity model, applying the
 * same orientation the block entity renderer uses for the block's default facing.
 */
abstract class HABlockItemRenderer<T, B>(
    delegate: GeoModel<B>,
    protected val blockEntity: B,
) : GeoItemRenderer<T>(BlockItemGeoModel(delegate, blockEntity))
    where T : Item, T : GeoAnimatable, B : BlockEntity, B : GeoAnimatable {

    override fun preRenderPass(renderPassInfo: RenderPassInfo<GeoRenderState>, renderTasks: SubmitNodeCollector) {
        super.preRenderPass(renderPassInfo, renderTasks)
        renderPassInfo.poseStack().translate(0.5, 0.0, 0.5)
    }

    override fun adjustRenderPose(renderPassInfo: RenderPassInfo<GeoRenderState>) {
        val state = blockEntity.blockState

        if (!state.hasProperty(DirectionalBlock.FACING)) return

        applyFacingTransform(renderPassInfo.poseStack(), state.getValue(DirectionalBlock.FACING))
    }
}
