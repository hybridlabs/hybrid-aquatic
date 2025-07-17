package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.WreckfishEntityModel
import dev.hybridlabs.aquatic.entity.fish.WreckfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class WreckfishEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<WreckfishEntity>(context, WreckfishEntityModel(), true, false)