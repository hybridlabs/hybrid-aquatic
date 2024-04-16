@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
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
            // message in a bottle variants
            MessageInABottleBlock.Variant.entries.forEach { variant ->
                val blockEntity = MessageInABottleBlockEntity(BlockPos.ORIGIN, HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.defaultState)
                    .also { blockEntity -> blockEntity.variant = variant }
                val stack = MessageInABottleBlock.createItemStack(blockEntity)
                entries.add(stack)
            }

            // blocks
            entries.add(HybridAquaticBlocks.GLOWSTICK)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_LOG)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_WOOD)
            entries.add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG)
            entries.add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_PLANKS)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_STAIRS)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_SLAB)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_FENCE)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_DOOR)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_PRESSURE_PLATE)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_BUTTON)
            entries.add(HybridAquaticBlocks.HYBRID_CRATE)
            entries.add(HybridAquaticBlocks.DRIFTWOOD_CRATE)
            entries.add(HybridAquaticBlocks.OAK_CRATE)
            entries.add(HybridAquaticBlocks.SPRUCE_CRATE)
            entries.add(HybridAquaticBlocks.BIRCH_CRATE)
            entries.add(HybridAquaticBlocks.DARK_OAK_CRATE)
            entries.add(HybridAquaticBlocks.JUNGLE_CRATE)
            entries.add(HybridAquaticBlocks.ACACIA_CRATE)
            entries.add(HybridAquaticBlocks.MANGROVE_CRATE)
            entries.add(HybridAquaticBlocks.CHERRY_CRATE)
            entries.add(HybridAquaticItems.BUOY)
            entries.add(HybridAquaticItems.LOPHELIA_CORAL_BLOCK)
            entries.add(HybridAquaticItems.LOPHELIA_CORAL)
            entries.add(HybridAquaticItems.LOPHELIA_CORAL_FAN)
            entries.add(HybridAquaticItems.DEAD_LOPHELIA_CORAL_BLOCK)
            entries.add(HybridAquaticItems.DEAD_LOPHELIA_CORAL)
            entries.add(HybridAquaticItems.DEAD_LOPHELIA_CORAL_FAN)
            entries.add(HybridAquaticItems.THORN_CORAL_BLOCK)
            entries.add(HybridAquaticItems.THORN_CORAL)
            entries.add(HybridAquaticItems.THORN_CORAL_FAN)
            entries.add(HybridAquaticItems.DEAD_THORN_CORAL_BLOCK)
            entries.add(HybridAquaticItems.DEAD_THORN_CORAL)
            entries.add(HybridAquaticItems.DEAD_THORN_CORAL_FAN)
            entries.add(HybridAquaticItems.HYDROTHERMAL_VENT)
            entries.add(HybridAquaticItems.TUBE_SPONGE)
            entries.add(HybridAquaticItems.GIANT_CLAM)
            entries.add(HybridAquaticBlocks.ANEMONE)
            entries.add(HybridAquaticBlocks.BASKING_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.BULL_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.FRILLED_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.GREAT_WHITE_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.HAMMERHEAD_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.THRESHER_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.TIGER_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.WHALE_SHARK_PLUSHIE)

            // food items
            entries.add(HybridAquaticItems.RAW_FISH_MEAT)
            entries.add(HybridAquaticItems.COOKED_FISH_MEAT)
            entries.add(HybridAquaticItems.RAW_FISH_STEAK)
            entries.add(HybridAquaticItems.COOKED_FISH_STEAK)
            entries.add(HybridAquaticItems.RAW_TENTACLE)
            entries.add(HybridAquaticItems.COOKED_TENTACLE)
            entries.add(HybridAquaticItems.RAW_CRAB)
            entries.add(HybridAquaticItems.COOKED_CRAB)
            entries.add(HybridAquaticItems.RAW_LOBSTER)
            entries.add(HybridAquaticItems.COOKED_LOBSTER)
            entries.add(HybridAquaticItems.RAW_SHRIMP)
            entries.add(HybridAquaticItems.COOKED_SHRIMP)
            entries.add(HybridAquaticItems.RAW_CRAYFISH)
            entries.add(HybridAquaticItems.COOKED_CRAYFISH)
            entries.add(HybridAquaticItems.TIGER_BARB)
            entries.add(HybridAquaticItems.OSCAR)
            entries.add(HybridAquaticItems.COWFISH)
            entries.add(HybridAquaticItems.UNICORN_FISH)
            entries.add(HybridAquaticItems.LIONFISH)
            entries.add(HybridAquaticItems.BLUE_SPOTTED_STINGRAY)
            entries.add(HybridAquaticItems.PIRANHA)
            entries.add(HybridAquaticItems.ANGLERFISH)
            entries.add(HybridAquaticItems.BARRELEYE)
            entries.add(HybridAquaticItems.BLUE_TANG)
            entries.add(HybridAquaticItems.CLOWNFISH)
            entries.add(HybridAquaticItems.NEEDLEFISH)
            entries.add(HybridAquaticItems.MORAY_EEL)
            entries.add(HybridAquaticItems.TRIGGERFISH)
            entries.add(HybridAquaticItems.OPAH)
            entries.add(HybridAquaticItems.ROCKFISH)
            entries.add(HybridAquaticItems.MAHI_MAHI)
            entries.add(HybridAquaticItems.YELLOWFIN_TUNA)

            // miscellaneous items
            entries.add(HybridAquaticItems.LOBSTER_CLAW)
            entries.add(HybridAquaticItems.COCONUT_CRAB_CLAW)
            entries.add(HybridAquaticItems.DUNGENESS_CRAB_CLAW)
            entries.add(HybridAquaticItems.FIDDLER_CRAB_CLAW)
            entries.add(HybridAquaticItems.VAMPIRE_CRAB_CLAW)
            entries.add(HybridAquaticItems.FLOWER_CRAB_CLAW)
            entries.add(HybridAquaticItems.GHOST_CRAB_CLAW)
            entries.add(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW)
            entries.add(HybridAquaticItems.YETI_CRAB_CLAW)
            entries.add(HybridAquaticItems.SPIDER_CRAB_CLAW)
            entries.add(HybridAquaticItems.KARKINOS_CLAW)
            entries.add(HybridAquaticItems.GLOW_SLIME)
            entries.add(HybridAquaticItems.SHARK_TOOTH)
            entries.add(HybridAquaticItems.PEARL)
            entries.add(HybridAquaticItems.BLACK_PEARL)
            entries.add(HybridAquaticItems.SPONGE_CHUNK)

            // lures
            entries.add(HybridAquaticItems.BARBED_HOOK)
            entries.add(HybridAquaticItems.GLOWING_HOOK)
            entries.add(HybridAquaticItems.MAGNETIC_HOOK)
            entries.add(HybridAquaticItems.OMINOUS_HOOK)

            // fishing net
            entries.add(HybridAquaticItems.FISHING_NET)

            entries.add(HybridAquaticItems.DIVING_HELMET)
            entries.add(HybridAquaticItems.DIVING_SUIT)
            entries.add(HybridAquaticItems.DIVING_LEGGINGS)
            entries.add(HybridAquaticItems.DIVING_BOOTS)
            entries.add(HybridAquaticItems.MANGLERFISH_LURE)
            entries.add(HybridAquaticItems.MANGLERFISH_FIN)
            entries.add(HybridAquaticItems.EEL_SCARF)
            entries.add(HybridAquaticItems.MOON_JELLYFISH_HAT)
            entries.add(Items.TURTLE_HELMET)
            entries.add(HybridAquaticItems.TURTLE_CHESTPLATE)


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
