package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.GiantGreenAnemoneBlockEntityModel
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider

class GiantGreenAnemoneBlockEntityRenderer(context: BlockEntityRendererProvider.Context) :
    HADirectionalBlockEntityRenderer<GiantGreenAnemoneBlockEntity>(context, GiantGreenAnemoneBlockEntityModel())
