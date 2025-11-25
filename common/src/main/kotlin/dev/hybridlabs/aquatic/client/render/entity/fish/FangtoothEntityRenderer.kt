package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.FangtoothEntityModel
import dev.hybridlabs.aquatic.entity.fish.FangtoothEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FangtoothEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<FangtoothEntity>(context, FangtoothEntityModel(), true, false)
