package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.HermitCrabEntityModel
import dev.hybridlabs.aquatic.entity.critter.HermitCrabEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class HermitCrabEntityRenderer(context: Context) : GeoEntityRenderer<HermitCrabEntity>(context, HermitCrabEntityModel())
