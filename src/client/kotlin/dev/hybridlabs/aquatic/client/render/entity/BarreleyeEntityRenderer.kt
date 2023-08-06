package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.AnglerfishEntityModel
import dev.hybridlabs.aquatic.client.model.entity.BarreleyeEntityModel
import dev.hybridlabs.aquatic.client.model.entity.ClownfishEntityModel
import dev.hybridlabs.aquatic.entity.ClownfishEntity
import dev.hybridlabs.aquatic.entity.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.entity.mob.WaterCreatureEntity
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class BarreleyeEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, BarreleyeEntityModel())
