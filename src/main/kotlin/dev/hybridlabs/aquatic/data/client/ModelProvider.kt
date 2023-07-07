package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.TextureMap
import net.minecraft.util.Identifier

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) { generator.run {
        registerBuiltinWithParticle(HybridAquaticBlocks.ANEMONE, TextureMap.getId(HybridAquaticBlocks.ANEMONE))
        excludeFromSimpleItemModelGeneration(HybridAquaticBlocks.ANEMONE)
        registerParentedItemModel(HybridAquaticItems.ANEMONE, Identifier("builtin/entity"))
    } }

    override fun generateItemModels(generator: ItemModelGenerator) {
    }
}
