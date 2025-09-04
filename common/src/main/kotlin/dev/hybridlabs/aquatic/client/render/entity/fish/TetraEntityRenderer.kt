package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.TetraEntityModel
import dev.hybridlabs.aquatic.entity.fish.TetraEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class TetraEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<TetraEntity>(context, TetraEntityModel(), false, false)
