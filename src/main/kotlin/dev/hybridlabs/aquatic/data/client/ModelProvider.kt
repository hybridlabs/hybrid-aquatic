package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.BlahajPlushieBlock
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Blocks
import net.minecraft.data.client.Models
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.ModelIds
import net.minecraft.data.client.TextureMap
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) { generator.run {
        // blahaj plushies
        Registries.BLOCK
            .filterIsInstance<BlahajPlushieBlock>()
            .forEach { block ->
                excludeFromSimpleItemModelGeneration(block)

                registerBuiltinWithParticle(block, TextureMap.getId(block.particleBlock))
                registerParentedItemModel(block, TEMPLATE_BLAHAJ_PLUSHIE)
            }

        // spawn eggs
        Registries.ITEM.forEach { item ->
            val id = Registries.ITEM.getId(item)
            if (id.namespace != HybridAquatic.MOD_ID) {
                return@forEach
            }

            if (item is SpawnEggItem) {
                registerParentedItemModel(item, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"))
            }
        }

        // builtin
        mapOf(
            HybridAquaticBlocks.ANEMONE to (HybridAquaticBlocks.ANEMONE to TEMPLATE_ANEMONE),
            HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE to (Blocks.GLASS to TEMPLATE_MESSAGE_IN_A_BOTTLE),
        ).forEach { (block, info) ->
            val (particleBlock, template) = info

            excludeFromSimpleItemModelGeneration(block)

            registerBuiltinWithParticle(block, TextureMap.getId(particleBlock))
            registerParentedItemModel(block, template)
        }
    } }

    override fun generateItemModels(generator: ItemModelGenerator) {
        generator.register(HybridAquaticItems.RAW_FISH_MEAT, Models.GENERATED)
        generator.register(HybridAquaticItems.COOKED_FISH_MEAT, Models.GENERATED)
        generator.register(HybridAquaticItems.RAW_TENTACLE, Models.GENERATED)
        generator.register(HybridAquaticItems.COOKED_TENTACLE, Models.GENERATED)
        generator.register(HybridAquaticItems.GLOW_SLIME, Models.GENERATED)
        generator.register(HybridAquaticItems.SHARK_TOOTH, Models.GENERATED)
        generator.register(HybridAquaticItems.PEARL, Models.GENERATED)
        generator.register(HybridAquaticItems.BLACK_PEARL, Models.GENERATED)
        generator.register(HybridAquaticItems.RED_STARFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.YELLOW_STARFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.PURPLE_STARFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.BLUE_STARFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.GREEN_STARFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.WHITE_STARFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.BLACK_STARFISH, Models.GENERATED)
    }

    companion object {
        private val TEMPLATE_ANEMONE = Identifier(HybridAquatic.MOD_ID, "item/template_anemone")
        private val TEMPLATE_MESSAGE_IN_A_BOTTLE = Identifier(HybridAquatic.MOD_ID, "item/template_message_in_a_bottle")
        private val TEMPLATE_BLAHAJ_PLUSHIE = Identifier(HybridAquatic.MOD_ID, "item/template_blahaj_plushie")
    }
}
