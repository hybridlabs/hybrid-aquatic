package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.NautilusEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.NautilusEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class NautilusEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<NautilusEntity>(context, NautilusEntityModel(), true, false)