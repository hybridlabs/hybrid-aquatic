@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos

object HybridAquaticItemGroups {
    val ALL = register("all", FabricItemGroup.builder()
        .displayName(Text.translatable("itemGroup.${HybridAquatic.MOD_ID}"))
        .icon { ItemStack(HybridAquaticItems.ANEMONE) }
        .entries { _, entries ->
            // anemone
            entries.add(HybridAquaticBlocks.ANEMONE)

            // message in a bottle variants
            MessageInABottleBlock.Variant.entries.forEach { variant ->
                val blockEntity = MessageInABottleBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.defaultState)
                    .also { blockEntity -> blockEntity.variant = variant }
                val stack = MessageInABottleBlock.createItemStack(blockEntity)
                entries.add(stack)
            }

            // plushies
            entries.add(HybridAquaticBlocks.BASKING_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.BULL_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.FRILLED_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.GREAT_WHITE_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.HAMMERHEAD_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.THRESHER_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.TIGER_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.WHALE_SHARK_PLUSHIE)

            // crates
            entries.add(HybridAquaticBlocks.CRATE)

            // food items
            entries.add(HybridAquaticItems.FISH_MEAT)
            entries.add(HybridAquaticItems.COOKED_FISH_MEAT)
            entries.add(HybridAquaticItems.SMALL_FISH_MEAT)
            entries.add(HybridAquaticItems.COOKED_SMALL_FISH_MEAT)
            entries.add(HybridAquaticItems.TENTACLE)
            entries.add(HybridAquaticItems.COOKED_TENTACLE)
            entries.add(HybridAquaticItems.CRAB_MEAT)
            entries.add(HybridAquaticItems.COOKED_CRAB_MEAT)
            entries.add(HybridAquaticItems.LIONFISH)
            entries.add(HybridAquaticItems.MAHI_MAHI)
            entries.add(HybridAquaticItems.YELLOWFIN_TUNA)
            entries.add(HybridAquaticItems.OPAH)
            entries.add(HybridAquaticItems.ROCKFISH)
            entries.add(HybridAquaticItems.BLUE_SPOTTED_STINGRAY)
            entries.add(HybridAquaticItems.MORAY_EEL)
            entries.add(HybridAquaticItems.NEEDLEFISH)
            entries.add(HybridAquaticItems.PIRANHA)
            entries.add(HybridAquaticItems.ANGLERFISH)
            entries.add(HybridAquaticItems.BARRELEYE)
            entries.add(HybridAquaticItems.BLUE_TANG)
            entries.add(HybridAquaticItems.CLOWNFISH)
            entries.add(HybridAquaticItems.UNICORN_FISH)
            entries.add(HybridAquaticItems.COWFISH)
            entries.add(HybridAquaticItems.TRIGGERFISH)
            entries.add(HybridAquaticItems.TIGER_BARB)
            entries.add(HybridAquaticItems.OSCAR)

            // miscellaneous items
            entries.add(HybridAquaticItems.CRAB_CLAW)
            entries.add(HybridAquaticItems.GLOW_SLIME)
            entries.add(HybridAquaticItems.SHARK_TOOTH)
            entries.add(HybridAquaticItems.PEARL)
            entries.add(HybridAquaticItems.BLACK_PEARL)
            entries.add(HybridAquaticItems.SPONGE_CHUNK)

            // lures
            entries.add(HybridAquaticItems.BARBED_HOOK)
            entries.add(HybridAquaticItems.GLOWING_HOOK)
            entries.add(HybridAquaticItems.MAGNETIC_HOOK)

            // fishing net
            entries.add(HybridAquaticItems.FISHING_NET)

            entries.add(HybridAquaticItems.BUOY)
            entries.add(HybridAquaticItems.CRAB_POT)
            entries.add(HybridAquaticItems.HYDROTHERMAL_VENT)
            entries.add(HybridAquaticItems.TUBE_SPONGE)

            // spawn eggs
            Registries.ITEM.forEach { item ->
                val id = Registries.ITEM.getId(item)
                if (id.namespace != HybridAquatic.MOD_ID) {
                    return@forEach
                }

                if (item is SpawnEggItem) {
                    entries.add(item)
                }
            }
        }
        .build()
    )

    private fun register(id: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, Identifier(HybridAquatic.MOD_ID, id), itemGroup)
    }
}
