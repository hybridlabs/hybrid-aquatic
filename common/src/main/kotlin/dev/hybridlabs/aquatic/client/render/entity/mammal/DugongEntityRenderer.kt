package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.mammal.DugongEntityModel
import dev.hybridlabs.aquatic.entity.mammal.DugongEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseSirenianEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DugongEntityRenderer(context: Context) :
    BaseSirenianEntityRenderer<DugongEntity>(context, DugongEntityModel(), true)
