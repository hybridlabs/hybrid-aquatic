package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.StrawberryAnemoneBlockEntityModel
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoBlockRenderer

class StrawberryAnemoneBlockEntityRenderer(context: Context) : GeoBlockRenderer<StrawberryAnemoneBlockEntity>(
    StrawberryAnemoneBlockEntityModel()
)