package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.SheepsheadWrasseEntityModel
import dev.hybridlabs.aquatic.entity.fish.SheepsheadWrasseEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SheepsheadWrasseEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<SheepsheadWrasseEntity>(context, SheepsheadWrasseEntityModel(), true, false)