package dev.hybridlabs.aquatic.client.renderer.block.entity

import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.GiantGreenAnemoneBlockEntityModel
import net.minecraft.client.renderer.block.entity.Bloc.EntityRendererProvider.Context
import software.bernie.geckolib.renderer.GeoBlockRenderer

class GiantGreenAnemoneBlockEntityRenderer(context: Context) : GeoBlockRenderer<GiantGreenAnemoneBlockEntity>(GiantGreenAnemoneBlockEntityModel())