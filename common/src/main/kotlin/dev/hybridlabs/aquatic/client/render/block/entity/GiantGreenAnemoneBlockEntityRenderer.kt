package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.GiantGreenAnemoneBlockEntityModel
import net.minecraft.client.renderer.block.model.BlockModelDefinition
import software.bernie.geckolib.renderer.GeoBlockRenderer

class GiantGreenAnemoneBlockEntityRenderer(context: BlockModelDefinition.Context) : GeoBlockRenderer<GiantGreenAnemoneBlockEntity>(GiantGreenAnemoneBlockEntityModel())