package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.GiantGreenAnemoneBlockEntityModel
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoBlockRenderer

class GiantGreenAnemoneBlockEntityRenderer(context: Context) : GeoBlockRenderer<GiantGreenAnemoneBlockEntity>(GiantGreenAnemoneBlockEntityModel())