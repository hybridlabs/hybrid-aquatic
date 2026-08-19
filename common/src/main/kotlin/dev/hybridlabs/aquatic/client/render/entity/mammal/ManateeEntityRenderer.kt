package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.mammal.ManateeEntityModel
import dev.hybridlabs.aquatic.entity.mammal.ManateeEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSirenianEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ManateeEntityRenderer(context: Context) :
    BaseSirenianEntityRenderer<ManateeEntity>(context, ManateeEntityModel(), true)
