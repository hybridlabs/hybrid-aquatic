package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SunfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.SunfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SunfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<SunfishEntity>(context, SunfishEntityModel(), true, false)