package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MahiEntityModel
import dev.hybridlabs.aquatic.entity.fish.MahiEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class MahiEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<MahiEntity>(context, MahiEntityModel(), true, false)