package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CorydoraEntityModel
import dev.hybridlabs.aquatic.entity.fish.CorydoraEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CorydoraEntityRenderer(context: Context) :
    BaseFishEntityRenderer<CorydoraEntity>(context, CorydoraEntityModel(), true, false)