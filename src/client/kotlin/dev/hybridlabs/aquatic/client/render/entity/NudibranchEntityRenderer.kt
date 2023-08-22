package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.NudibranchEntityModel
import dev.hybridlabs.aquatic.entity.critter.NudibranchEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class NudibranchEntityRenderer(context: Context) : GeoEntityRenderer<NudibranchEntity>(context, NudibranchEntityModel())
