package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.NeedlefishEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class NeedlefishEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, NeedlefishEntityModel())
