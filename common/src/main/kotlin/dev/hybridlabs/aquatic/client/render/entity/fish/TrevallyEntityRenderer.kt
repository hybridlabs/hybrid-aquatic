package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TrevallyEntityModel
import dev.hybridlabs.aquatic.entity.fish.TrevallyEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class TrevallyEntityRenderer(context: Context) :
    BaseFishEntityRenderer<TrevallyEntity, LivingEntityRenderState>(context, TrevallyEntityModel(), true, false)