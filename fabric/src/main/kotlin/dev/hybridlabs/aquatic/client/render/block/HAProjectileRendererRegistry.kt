package dev.hybridlabs.aquatic.client.render.block

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.platform.ClientServices
import net.minecraft.client.renderer.entity.ThrownItemRenderer

object HAProjectileRendererRegistry {
    init {
        ClientServices.PLATFORM.registerEntityRenderer(
            HAEntityTypes.STARFISH_PROJECTILE
        ) { context ->
            ThrownItemRenderer(context)
        }
    }
}