package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TrevallyEntityModel
import dev.hybridlabs.aquatic.entity.fish.TrevallyEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class TrevallyEntityRenderer(context: Context) :
    BaseFishEntityRenderer<TrevallyEntity>(context, TrevallyEntityModel(), true, false)