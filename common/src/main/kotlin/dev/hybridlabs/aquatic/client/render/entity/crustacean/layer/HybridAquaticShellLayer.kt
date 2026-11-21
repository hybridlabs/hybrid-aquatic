package dev.hybridlabs.aquatic.client.render.entity.crustacean.layer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.datafixers.util.Either
import com.mojang.math.Axis
import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCrustaceanEntity
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.state.CameraRenderState
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.cache.model.BakedGeoModel
import software.bernie.geckolib.cache.model.GeoBone
import software.bernie.geckolib.renderer.layer.builtin.BlockAndItemGeoLayer

class HybridAquaticShellLayer<T : BaseCrustaceanEntity>(
    renderer: BaseCrustaceanEntityRenderer<T, LivingEntityRenderState>,
    private val defaultItem: Item
) : BlockAndItemGeoLayer<T, Void, LivingEntityRenderState>(renderer) {

    override fun addRenderData(
        animatable: T,
        relatedObject: Void?,
        renderState: LivingEntityRenderState,
        partialTick: Float
    ) {
        renderState.addGeckolibData(HADataTickets.SHELL_ITEM, animatable.shellItem)
    }

    override fun getRelevantBones(
        renderState: LivingEntityRenderState,
        model: BakedGeoModel
    ): List<RenderData<LivingEntityRenderState>> {
        return listOf(
            RenderData("shell", ItemDisplayContext.FIXED) { _, state ->
                Either.left(state.getGeckolibData(HADataTickets.SHELL_ITEM) ?: ItemStack.EMPTY)
            }
        )
    }

    override fun submitItemStackRender(
        poseStack: PoseStack,
        bone: GeoBone,
        stack: ItemStack,
        displayContext: ItemDisplayContext,
        renderState: LivingEntityRenderState,
        renderTasks: SubmitNodeCollector,
        cameraState: CameraRenderState,
        packedLight: Int,
        packedOverlay: Int,
        renderColor: Int
    ) {
        if (stack.isEmpty) return
        if (stack.`is`(defaultItem) || stack.`is`(HAItems.OMINOUS_CONCH.get())) return

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
            cameraState,
            packedLight,
            packedOverlay,
            renderColor
        )

        poseStack.popPose()
    }
}
