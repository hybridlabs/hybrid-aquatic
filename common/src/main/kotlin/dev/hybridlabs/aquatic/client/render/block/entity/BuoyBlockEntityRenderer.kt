package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.BuoyBlockEntityModel
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import software.bernie.geckolib.renderer.GeoBlockRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class BuoyBlockEntityRenderer(context: BlockEntityRendererProvider.Context) :
    GeoBlockRenderer<BuoyBlockEntity>(BuoyBlockEntityModel()) {
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}