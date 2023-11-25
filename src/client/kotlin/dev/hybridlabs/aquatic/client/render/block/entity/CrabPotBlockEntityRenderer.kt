package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.CrabPotBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.CrabPotBlockEntityModel
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoBlockRenderer

class CrabPotBlockEntityRenderer(context: Context) : GeoBlockRenderer<CrabPotBlockEntity>(CrabPotBlockEntityModel())