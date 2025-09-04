package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.NomuraJellyfishEntityModel
import dev.hybridlabs.aquatic.client.renderer.entity.jellyfish.HybridAquaticJellyfishEntityRenderer
import dev.hybridlabs.aquatic.entity.jellyfish.NomuraJellyfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class NomuraJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<NomuraJellyfishEntity>(context, NomuraJellyfishEntityModel(), true, false)