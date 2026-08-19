package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.NautilusEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.NautilusEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class NautilusEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<NautilusEntity>(context, NautilusEntityModel(), true, false)