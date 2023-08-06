package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.BlahajPlushieBlock
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.TextureMap
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) { generator.run {
        Registries.BLOCK
            .filter { block ->
                val id = Registries.BLOCK.getId(block)
                id.namespace == HybridAquatic.MOD_ID
            }
            .forEach { block ->
                // blahaj plushies
                if (block is BlahajPlushieBlock) {
                    registerBuiltinWithParticle(block, TextureMap.getId(block.particleBlock))
                    excludeFromSimpleItemModelGeneration(block)
                    registerParentedItemModel(block, TEMPLATE_BLAHAJ_PLUSHIE)
                }
            }

        // anemone
        registerBuiltinWithParticle(HybridAquaticBlocks.ANEMONE, TextureMap.getId(HybridAquaticBlocks.ANEMONE))
        excludeFromSimpleItemModelGeneration(HybridAquaticBlocks.ANEMONE)
        registerParentedItemModel(HybridAquaticItems.ANEMONE, BUILTIN_ENTITY)
    } }

    override fun generateItemModels(generator: ItemModelGenerator) {
    }

    companion object {
        private val BUILTIN_ENTITY = Identifier("builtin/entity")
        private val TEMPLATE_BLAHAJ_PLUSHIE = Identifier(HybridAquatic.MOD_ID, "item/template_blahaj_plushie")
    }
}
