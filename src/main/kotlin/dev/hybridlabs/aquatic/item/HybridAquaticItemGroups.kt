@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object HybridAquaticItemGroups {
    val ALL = register("all", FabricItemGroup.builder()
        .displayName(Text.translatable("itemGroup.${HybridAquatic.MOD_ID}"))
        .icon { ItemStack(HybridAquaticItems.ANEMONE) }
        .entries { _, entries ->
            // anemone
            entries.add(HybridAquaticBlocks.ANEMONE)

            // message in a bottle variants
            MessageInABottleBlock.Variant.values().forEach { variant ->
                val stack = ItemStack(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE)
                val nbt = stack.getOrCreateSubNbt(BlockItem.BLOCK_ENTITY_TAG_KEY)
                nbt.putString(MessageInABottleBlockEntity.VARIANT_KEY, variant.id)
                entries.add(stack)
            }

            // blahaj plushies
            entries.add(HybridAquaticBlocks.BASKING_SHARK_BLAHAJ_PLUSHIE)
            entries.add(HybridAquaticBlocks.BULL_SHARK_BLAHAJ_PLUSHIE)
            entries.add(HybridAquaticBlocks.FRILLED_SHARK_BLAHAJ_PLUSHIE)
            entries.add(HybridAquaticBlocks.GREAT_WHITE_SHARK_BLAHAJ_PLUSHIE)
            entries.add(HybridAquaticBlocks.HAMMERHEAD_SHARK_BLAHAJ_PLUSHIE)
            entries.add(HybridAquaticBlocks.THRESHER_SHARK_BLAHAJ_PLUSHIE)
            entries.add(HybridAquaticBlocks.TIGER_SHARK_BLAHAJ_PLUSHIE)
            entries.add(HybridAquaticBlocks.WHALE_SHARK_BLAHAJ_PLUSHIE)
        }
        .build()
    )

    private fun register(id: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, Identifier(HybridAquatic.MOD_ID, id), itemGroup)
    }
}
