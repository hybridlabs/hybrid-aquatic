package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MorayEelEntityModel
import dev.hybridlabs.aquatic.entity.fish.MorayEelEntity
import dev.hybridlabs.hapi.client.render.entity.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class MorayEelEntityRenderer(context: Context) :
    BaseFishEntityRenderer<MorayEelEntity>(context, MorayEelEntityModel(), true, false)