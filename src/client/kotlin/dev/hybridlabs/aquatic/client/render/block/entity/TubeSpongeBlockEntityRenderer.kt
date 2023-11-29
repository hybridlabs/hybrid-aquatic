package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.TubeSpongeBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.TubeSpongeBlockEntityModel
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoBlockRenderer

class TubeSpongeBlockEntityRenderer(context: Context) : GeoBlockRenderer<TubeSpongeBlockEntity>(TubeSpongeBlockEntityModel()) {

}
