package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.BettaEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class BettaEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, BettaEntityModel())
