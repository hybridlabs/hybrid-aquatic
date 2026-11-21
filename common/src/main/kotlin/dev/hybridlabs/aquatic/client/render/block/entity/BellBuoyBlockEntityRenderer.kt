package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.BellBuoyBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.BellBuoyBlockEntityModel
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import com.geckolib.renderer.GeoBlockRenderer

class BellBuoyBlockEntityRenderer(context: BlockEntityRendererProvider.Context) :
    GeoBlockRenderer<BellBuoyBlockEntity, HABlockEntityRenderState>(context, BellBuoyBlockEntityModel()) {
    override fun createRenderState(): HABlockEntityRenderState = HABlockEntityRenderState()
}
