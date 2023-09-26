package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Blocks
import net.minecraft.data.client.*
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) { generator.run {
        // plushies
        Registries.BLOCK
            .filterIsInstance<PlushieBlock>()
            .forEach { block ->
                excludeFromSimpleItemModelGeneration(block)

                registerBuiltinWithParticle(block, TextureMap.getId(block.particleBlock))
                registerParentedItemModel(block, TEMPLATE_PLUSHIE)
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
        listOf(
            HybridAquaticItems.CRAB_CLAW,
            HybridAquaticItems.CRAB_MEAT,
            HybridAquaticItems.COOKED_CRAB_MEAT,
            HybridAquaticItems.FISH_MEAT,
            HybridAquaticItems.COOKED_FISH_MEAT,
            HybridAquaticItems.SMALL_FISH_MEAT,
            HybridAquaticItems.COOKED_SMALL_FISH_MEAT,
            HybridAquaticItems.TENTACLE,
            HybridAquaticItems.COOKED_TENTACLE,
            HybridAquaticItems.GLOW_SLIME,
            HybridAquaticItems.SHARK_TOOTH,
            HybridAquaticItems.PEARL,
            HybridAquaticItems.BLACK_PEARL,
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.MAHI_MAHI,
            HybridAquaticItems.YELLOWFIN_TUNA,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.UNICORN_FISH,
            HybridAquaticItems.COWFISH,
            HybridAquaticItems.TRIGGERFISH,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.OSCAR,

            HybridAquaticItems.BARBED_HOOK,
            HybridAquaticItems.GLOWING_HOOK,
            HybridAquaticItems.MAGNETIC_HOOK,
        ).forEach { item ->
            generator.register(item, Models.GENERATED)
        }
    }

    companion object {
        private val TEMPLATE_ANEMONE = Identifier(HybridAquatic.MOD_ID, "item/template_anemone")
        private val TEMPLATE_MESSAGE_IN_A_BOTTLE = Identifier(HybridAquatic.MOD_ID, "item/template_message_in_a_bottle")
        private val TEMPLATE_PLUSHIE = Identifier(HybridAquatic.MOD_ID, "item/template_plushie")
    }
}
