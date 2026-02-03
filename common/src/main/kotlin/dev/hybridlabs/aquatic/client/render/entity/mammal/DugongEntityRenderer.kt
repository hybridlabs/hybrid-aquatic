package dev.hybridlabs.aquatic.client.render.entity.mammal

import dev.hybridlabs.aquatic.client.model.entity.fish.DugongEntityModel
import dev.hybridlabs.aquatic.client.model.entity.fish.ViperfishEntityModel
import dev.hybridlabs.aquatic.client.render.entity.fish.HybridAquaticFishEntityRenderer
import dev.hybridlabs.aquatic.entity.fish.ViperfishEntity
import dev.hybridlabs.aquatic.entity.mammal.DugongEntity
import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticMammalEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DugongEntityRenderer(context: Context) :
    HybridAquaticMammalEntityRenderer<DugongEntity>(context, DugongEntityModel(), true)
