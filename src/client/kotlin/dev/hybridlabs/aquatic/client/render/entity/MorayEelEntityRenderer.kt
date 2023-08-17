package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.MorayEelEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class MorayEelEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, MorayEelEntityModel())
