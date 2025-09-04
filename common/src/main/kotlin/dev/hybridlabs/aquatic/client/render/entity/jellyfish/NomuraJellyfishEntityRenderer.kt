package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.NomuraJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.NomuraJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class NomuraJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<NomuraJellyfishEntity>(context, NomuraJellyfishEntityModel(), true, false)