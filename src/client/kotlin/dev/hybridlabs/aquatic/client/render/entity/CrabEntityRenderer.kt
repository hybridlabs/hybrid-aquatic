package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.CrabEntityModel
import dev.hybridlabs.aquatic.entity.critter.CrabEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class CrabEntityRenderer(context: Context) : GeoEntityRenderer<CrabEntity>(context, CrabEntityModel())
