package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.AnglerfishEntityModel
import dev.hybridlabs.aquatic.client.model.entity.YellowfinTunaEntityModel
import dev.hybridlabs.aquatic.entity.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class YelowfinTunaEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, YellowfinTunaEntityModel())
