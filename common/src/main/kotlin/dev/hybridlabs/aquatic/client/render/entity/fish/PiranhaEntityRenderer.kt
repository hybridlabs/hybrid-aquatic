package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.PiranhaEntityModel
import dev.hybridlabs.aquatic.entity.fish.PiranhaEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class PiranhaEntityRenderer(context: Context) :
    BaseFishEntityRenderer<PiranhaEntity>(context, PiranhaEntityModel(), true, false)