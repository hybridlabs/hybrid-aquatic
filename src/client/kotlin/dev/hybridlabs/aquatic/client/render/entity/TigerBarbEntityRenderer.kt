package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.TigerBarbEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class TigerBarbEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, TigerBarbEntityModel())
