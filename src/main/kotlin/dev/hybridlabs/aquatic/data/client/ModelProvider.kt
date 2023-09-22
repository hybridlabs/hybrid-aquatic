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
        generator.register(HybridAquaticItems.CRAB_CLAW, Models.GENERATED)
        generator.register(HybridAquaticItems.RAW_CRAB_MEAT, Models.GENERATED)
        generator.register(HybridAquaticItems.COOKED_CRAB_MEAT, Models.GENERATED)
        generator.register(HybridAquaticItems.RAW_FISH_MEAT, Models.GENERATED)
        generator.register(HybridAquaticItems.COOKED_FISH_MEAT, Models.GENERATED)
        generator.register(HybridAquaticItems.RAW_TENTACLE, Models.GENERATED)
        generator.register(HybridAquaticItems.COOKED_TENTACLE, Models.GENERATED)
        generator.register(HybridAquaticItems.GLOW_SLIME, Models.GENERATED)
        generator.register(HybridAquaticItems.SHARK_TOOTH, Models.GENERATED)
        generator.register(HybridAquaticItems.PEARL, Models.GENERATED)
        generator.register(HybridAquaticItems.BLACK_PEARL, Models.GENERATED)
        generator.register(HybridAquaticItems.LIONFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.MAHI_MAHI, Models.GENERATED)
        generator.register(HybridAquaticItems.YELLOWFIN_TUNA, Models.GENERATED)
        generator.register(HybridAquaticItems.OPAH, Models.GENERATED)
        generator.register(HybridAquaticItems.ROCKFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.BLUE_SPOTTED_STINGRAY, Models.GENERATED)
        generator.register(HybridAquaticItems.MORAY_EEL, Models.GENERATED)
        generator.register(HybridAquaticItems.NEEDLEFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.PIRANHA, Models.GENERATED)
        generator.register(HybridAquaticItems.ANGLERFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.BARRELEYE, Models.GENERATED)
        generator.register(HybridAquaticItems.BLUE_TANG, Models.GENERATED)
        generator.register(HybridAquaticItems.CLOWNFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.UNICORN_FISH, Models.GENERATED)
        generator.register(HybridAquaticItems.COWFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.TRIGGERFISH, Models.GENERATED)
        generator.register(HybridAquaticItems.TIGER_BARB, Models.GENERATED)
        generator.register(HybridAquaticItems.OSCAR, Models.GENERATED)

        generator.register(HybridAquaticItems.BARBED_HOOK, Models.GENERATED)
        generator.register(HybridAquaticItems.GLOWING_HOOK, Models.GENERATED)
        generator.register(HybridAquaticItems.MAGNETIC_HOOK, Models.GENERATED)
    }

    companion object {
        private val TEMPLATE_ANEMONE = Identifier(HybridAquatic.MOD_ID, "item/template_anemone")
        private val TEMPLATE_MESSAGE_IN_A_BOTTLE = Identifier(HybridAquatic.MOD_ID, "item/template_message_in_a_bottle")
        private val TEMPLATE_PLUSHIE = Identifier(HybridAquatic.MOD_ID, "item/template_plushie")
    }
}
