package dev.hybridlabs.aquatic.client.renderer.item

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.HybridAquaticClient
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import dev.hybridlabs.aquatic.client.renderer.block.entity.BuoyBlockEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

class BuoyBlockItemRenderer : DynamicItemRenderer {
    private val buoyBlockEntity = BuoyBlockEntity(BlockPos.ZERO, HybridAquaticBlocks.BUOY.get().defaultBlockState())
    private val renderer = BuoyBlockEntityRenderer(HybridAquaticClient.createBlocEntityRendererProviderContext())

    override fun render(
        stack: ItemStack,
        mode: ItemDisplayContext,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
        overlay: Int
    ) {
        renderer.render(buoyBlockEntity, 1.0f, matrices, vertexConsumers, light, overlay)
    }
}