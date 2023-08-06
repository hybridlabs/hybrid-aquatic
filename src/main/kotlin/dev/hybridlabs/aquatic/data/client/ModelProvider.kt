package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.BlahajPlushieBlock
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Blocks
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
                    excludeFromSimpleItemModelGeneration(block)

                    registerBuiltinWithParticle(block, TextureMap.getId(block.particleBlock))
                    registerParentedItemModel(block, TEMPLATE_BLAHAJ_PLUSHIE)
                }
            }

        // anemone

        // builtin
        mapOf(
            HybridAquaticBlocks.ANEMONE to HybridAquaticBlocks.ANEMONE,
            HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE to Blocks.GLASS,
        ).forEach { (block, particleBlock) ->
            excludeFromSimpleItemModelGeneration(block)

            registerBuiltinWithParticle(block, TextureMap.getId(particleBlock))
            registerParentedItemModel(block, BUILTIN_ENTITY)
        }
    } }

    override fun generateItemModels(generator: ItemModelGenerator) {
    }

    companion object {
        private val BUILTIN_ENTITY = Identifier("builtin/entity")
        private val TEMPLATE_BLAHAJ_PLUSHIE = Identifier(HybridAquatic.MOD_ID, "item/template_blahaj_plushie")
    }
}
