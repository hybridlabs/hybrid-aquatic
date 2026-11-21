package dev.hybridlabs.aquatic.client.render.entity.crustacean.layer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCrustaceanEntity
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.item.ItemStackRenderState
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import com.geckolib.cache.model.GeoBone
import com.geckolib.renderer.layer.builtin.BlockAndItemGeoLayer
import com.geckolib.util.RenderUtil

class HybridAquaticShellLayer<T : BaseCrustaceanEntity>(
    context: EntityRendererProvider.Context,
    renderer: BaseCrustaceanEntityRenderer<T, LivingEntityRenderState>,
    private val defaultItem: Item
) : BlockAndItemGeoLayer<T, Void, LivingEntityRenderState>(context, renderer) {

    override fun addRenderData(
        animatable: T,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ) {
        renderState.addGeckolibData(HADataTickets.SHELL_ITEM, animatable.shellItem)

        val contents = getRelevantBones(animatable, relatedObject, renderState, partialTick)
        if (contents.isNotEmpty()) {
            renderState.addGeckolibData(BlockAndItemGeoLayer.CONTENTS, contents)
        }
    }

    override fun getRelevantBones(
        animatable: T,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ): List<BlockAndItemGeoLayer.RenderData> {
        val stack = renderState.getGeckolibData(HADataTickets.SHELL_ITEM) ?: return emptyList()
        if (stack.isEmpty || stack.`is`(defaultItem) || stack.`is`(HAItems.OMINOUS_CONCH.get())) return emptyList()

        return listOf(
            BlockAndItemGeoLayer.RenderData.item(
                "shell",
                ItemDisplayContext.FIXED,
                RenderUtil.createRenderStateForItem(stack, itemModelResolver, ItemDisplayContext.FIXED, animatable)
            )
        )
    }

    override fun submitItemStackRender(
        poseStack: PoseStack,
        bone: GeoBone,
        stack: ItemStackRenderState,
        displayContext: ItemDisplayContext,
        renderState: LivingEntityRenderState,
        renderTasks: SubmitNodeCollector,
        packedLight: Int
    ) {
        poseStack.pushPose()
        poseStack.rotateAround(Axis.XP.rotationDegrees(32.5f), 0.0F, 0.0F, 0.0F)
        poseStack.translate(0.0, 0.25, -0.1)

        super.submitItemStackRender(
            poseStack,
            bone,
            stack,
            displayContext,
            renderState,
            renderTasks,
            packedLight
        )

        poseStack.popPose()
    }
}
