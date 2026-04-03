package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CorydoraEntityModel
import dev.hybridlabs.aquatic.entity.fish.CorydoraEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CorydoraEntityRenderer(context: Context) :
    HAFishEntityRenderer<CorydoraEntity>(context, CorydoraEntityModel(), true, false)