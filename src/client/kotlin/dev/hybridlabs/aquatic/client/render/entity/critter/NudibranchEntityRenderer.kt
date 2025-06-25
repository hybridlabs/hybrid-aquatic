package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.critter.NudibranchEntityModel
import dev.hybridlabs.aquatic.entity.critter.NudibranchEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class NudibranchEntityRenderer(context: Context) :
    HybridAquaticCritterEntityRenderer<NudibranchEntity>(context, NudibranchEntityModel())