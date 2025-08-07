package dev.hybridlabs.aquatic.client.render.entity.jellyfish

import dev.hybridlabs.aquatic.client.model.entity.jellyfish.CepheidaeJellyfishEntityModel
import dev.hybridlabs.aquatic.entity.jellyfish.CepheidaeJellyfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class CepheidaeJellyfishEntityRenderer(context: Context) :
    HybridAquaticJellyfishEntityRenderer<CepheidaeJellyfishEntity>(context, CepheidaeJellyfishEntityModel(), true, true)