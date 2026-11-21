package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.StrawberryAnemoneBlockEntityModel
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider

@Suppress("UNUSED_PARAMETER")
class StrawberryAnemoneBlockEntityRenderer(context: BlockEntityRendererProvider.Context) :
    HADirectionalBlockEntityRenderer<StrawberryAnemoneBlockEntity>(StrawberryAnemoneBlockEntityModel())
