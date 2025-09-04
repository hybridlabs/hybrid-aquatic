package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.OscarEntityModel
import dev.hybridlabs.aquatic.entity.fish.OscarEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class OscarEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<OscarEntity>(context, OscarEntityModel(), true, false)