package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.client.render.item.AnemoneBlockItemRenderer
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Block
import net.minecraftforge.client.extensions.common.IClientItemExtensions
import java.util.function.Consumer

class AnemoneBlockItem(block: Block, properties: Properties) : BlockItem(block, properties) {

    override fun initializeClient(consumer: Consumer<IClientItemExtensions?>) {
        consumer.accept(object : IClientItemExtensions {
            override fun getCustomRenderer(): BlockEntityWithoutLevelRenderer {
                return AnemoneBlockItemRenderer()
            }
        })
    }
}