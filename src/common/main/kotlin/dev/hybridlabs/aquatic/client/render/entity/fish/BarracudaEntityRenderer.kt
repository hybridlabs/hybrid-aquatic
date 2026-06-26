package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.BarracudaEntityModel
import dev.hybridlabs.aquatic.entity.fish.BarracudaEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class BarracudaEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<BarracudaEntity>(context, BarracudaEntityModel(), true, false)