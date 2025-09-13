package dev.hybridlabs.aquatic.block

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider

class BlockEntityHelper {
    companion object {
        fun createBlockEntityRendererProviderContext(): BlockEntityRendererProvider.Context {
            val client =
                Minecraft.getInstance()
            return BlockEntityRendererProvider.Context(
                client.blockEntityRenderDispatcher,
                client.blockRenderer,
                client.itemRenderer,
                client.entityRenderDispatcher,
                client.entityModels,
                client.font
            )
        }
    }
}