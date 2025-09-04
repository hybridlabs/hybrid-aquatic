package dev.hybridlabs.aquatic.client.renderer.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.NautilusEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.NautilusEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class NautilusEntityRenderer(context: Context) :
    HybridAquaticCephalopodEntityRenderer<NautilusEntity>(context, NautilusEntityModel(), true, false)