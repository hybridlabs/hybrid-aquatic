package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.SeaNettleEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.util.Identifier
import software.bernie.geckolib.renderer.GeoEntityRenderer

class SeaNettleEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticJellyfishEntity>(context, SeaNettleEntityModel())