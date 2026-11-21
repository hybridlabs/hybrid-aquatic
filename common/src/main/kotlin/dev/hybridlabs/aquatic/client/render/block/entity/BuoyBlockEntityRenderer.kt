package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.BuoyBlockEntityModel
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import com.geckolib.renderer.GeoBlockRenderer
import com.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer

class BuoyBlockEntityRenderer(context: BlockEntityRendererProvider.Context) :
    GeoBlockRenderer<BuoyBlockEntity, HABlockEntityRenderState>(context, BuoyBlockEntityModel()) {

    override fun createRenderState(): HABlockEntityRenderState = HABlockEntityRenderState()

    init {
        withRenderLayer(AutoGlowingGeoLayer(this))
    }
}