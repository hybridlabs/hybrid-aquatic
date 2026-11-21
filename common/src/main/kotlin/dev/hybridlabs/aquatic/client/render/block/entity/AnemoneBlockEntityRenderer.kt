package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.AnemoneBlockEntityModel
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider

@Suppress("UNUSED_PARAMETER")
class AnemoneBlockEntityRenderer(context: BlockEntityRendererProvider.Context) :
    HADirectionalBlockEntityRenderer<AnemoneBlockEntity>(AnemoneBlockEntityModel())
