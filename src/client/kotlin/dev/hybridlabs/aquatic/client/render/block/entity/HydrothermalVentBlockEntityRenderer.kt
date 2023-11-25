package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.HydrothermalVentBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.HydrothermalVentBlockEntityModel
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoBlockRenderer

class HydrothermalVentBlockEntityRenderer(context: Context) : GeoBlockRenderer<HydrothermalVentBlockEntity>(HydrothermalVentBlockEntityModel())