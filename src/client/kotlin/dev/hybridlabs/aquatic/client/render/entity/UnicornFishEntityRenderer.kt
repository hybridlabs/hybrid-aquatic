package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.UnicornFishEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class UnicornFishEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, UnicornFishEntityModel())
