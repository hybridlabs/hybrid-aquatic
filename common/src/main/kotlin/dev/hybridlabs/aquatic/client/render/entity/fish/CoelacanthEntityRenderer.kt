package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.CoelacanthEntityModel
import dev.hybridlabs.aquatic.entity.fish.CoelacanthEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CoelacanthEntityRenderer(context: Context) :
    HAFishEntityRenderer<CoelacanthEntity>(context, CoelacanthEntityModel(), true, false)