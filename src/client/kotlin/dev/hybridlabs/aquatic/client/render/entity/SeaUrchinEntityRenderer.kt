package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.SeaUrchinEntityModel
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class SeaUrchinEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticCritterEntity>(context, SeaUrchinEntityModel())
