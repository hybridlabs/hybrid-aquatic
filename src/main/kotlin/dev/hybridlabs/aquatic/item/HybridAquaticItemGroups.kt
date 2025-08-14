@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object  HybridAquaticItemGroups {
    val BLOCKS = register("blocks", FabricItemGroup.builder()
        .displayName(Text.translatable("itemGroup.${HybridAquatic.MOD_ID}.blocks"))
        .icon { ItemStack(HybridAquaticItems.ANEMONE) }
        .entries { _, entries ->

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

            entries.add(HybridAquaticBlocks.CRAB_POT)
            entries.add(HybridAquaticBlocks.HYBRID_CRATE)
            entries.add(HybridAquaticBlocks.OAK_CRATE)
            entries.add(HybridAquaticBlocks.SPRUCE_CRATE)
            entries.add(HybridAquaticBlocks.BIRCH_CRATE)
            entries.add(HybridAquaticBlocks.DARK_OAK_CRATE)
            entries.add(HybridAquaticBlocks.JUNGLE_CRATE)
            entries.add(HybridAquaticBlocks.ACACIA_CRATE)
            entries.add(HybridAquaticBlocks.MANGROVE_CRATE)
            entries.add(HybridAquaticBlocks.CHERRY_CRATE)

            entries.add(HybridAquaticItems.SEA_LETTUCE)
            entries.add(HybridAquaticItems.RED_ALGAE)
            entries.add(HybridAquaticItems.BULL_KELP)
            entries.add(HybridAquaticItems.SARGASSUM)
            entries.add(HybridAquaticItems.FLOATING_SARGASSUM)
            entries.add(HybridAquaticItems.WATER_LETTUCE)
            entries.add(HybridAquaticItems.JUNGLE_LILY_PAD)

            entries.add(HybridAquaticItems.BUTTON_CORAL_BLOCK)
            entries.add(HybridAquaticItems.SUN_CORAL_BLOCK)
            entries.add(HybridAquaticItems.LOPHELIA_CORAL_BLOCK)
            entries.add(HybridAquaticItems.THORN_CORAL_BLOCK)

            entries.add(HybridAquaticItems.BUTTON_CORAL)
            entries.add(HybridAquaticItems.SUN_CORAL)
            entries.add(HybridAquaticItems.LOPHELIA_CORAL)
            entries.add(HybridAquaticItems.THORN_CORAL)

            entries.add(HybridAquaticItems.BUTTON_CORAL_FAN)
            entries.add(HybridAquaticItems.SUN_CORAL_FAN)
            entries.add(HybridAquaticItems.LOPHELIA_CORAL_FAN)
            entries.add(HybridAquaticItems.THORN_CORAL_FAN)

            entries.add(HybridAquaticItems.DEAD_BUTTON_CORAL_BLOCK)
            entries.add(HybridAquaticItems.DEAD_SUN_CORAL_BLOCK)
            entries.add(HybridAquaticItems.DEAD_LOPHELIA_CORAL_BLOCK)
            entries.add(HybridAquaticItems.DEAD_THORN_CORAL_BLOCK)

            entries.add(HybridAquaticItems.DEAD_BUTTON_CORAL)
            entries.add(HybridAquaticItems.DEAD_SUN_CORAL)
            entries.add(HybridAquaticItems.DEAD_LOPHELIA_CORAL)
            entries.add(HybridAquaticItems.DEAD_THORN_CORAL)

            entries.add(HybridAquaticItems.DEAD_BUTTON_CORAL_FAN)
            entries.add(HybridAquaticItems.DEAD_SUN_CORAL_FAN)
            entries.add(HybridAquaticItems.DEAD_LOPHELIA_CORAL_FAN)
            entries.add(HybridAquaticItems.DEAD_THORN_CORAL_FAN)

            entries.add(HybridAquaticItems.BUOY)
            entries.add(HybridAquaticItems.RAFT)
            entries.add(HybridAquaticBlocks.ANEMONE)
            entries.add(HybridAquaticBlocks.GIANT_GREEN_ANEMONE)
            entries.add(HybridAquaticBlocks.STRAWBERRY_ANEMONE)
            entries.add(HybridAquaticItems.GIANT_CLAM)
            entries.add(HybridAquaticItems.TUBE_SPONGE)
            entries.add(HybridAquaticItems.TUBE_WORM)
            entries.add(HybridAquaticItems.HYDROTHERMAL_VENT)
            entries.add(HybridAquaticBlocks.BASKING_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.BULL_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.FRILLED_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.GREAT_WHITE_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.HAMMERHEAD_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.THRESHER_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.TIGER_SHARK_PLUSHIE)
            entries.add(HybridAquaticBlocks.WHALE_SHARK_PLUSHIE)

            Registries.ITEM.forEach { item ->
                val id = Registries.ITEM.getId(item)
                if (id.namespace != HybridAquatic.MOD_ID) {
                    return@forEach
                }
            }
        }
        .build()
    )

    val ITEMS = register("items", FabricItemGroup.builder()
        .displayName(Text.translatable("itemGroup.${HybridAquatic.MOD_ID}.items"))
        .icon { ItemStack(HybridAquaticItems.BARBED_HOOK) }
        .entries { _, entries ->

            // food items
            entries.add(HybridAquaticItems.RAW_FISH_MEAT)
            entries.add(HybridAquaticItems.COOKED_FISH_MEAT)
            entries.add(HybridAquaticItems.RAW_FISH_STEAK)
            entries.add(HybridAquaticItems.COOKED_FISH_STEAK)
            entries.add(HybridAquaticItems.RAW_TENTACLE)
            entries.add(HybridAquaticItems.COOKED_TENTACLE)
            entries.add(HybridAquaticItems.RAW_SHRIMP)
            entries.add(HybridAquaticItems.COOKED_SHRIMP)
            entries.add(HybridAquaticItems.RAW_CRAYFISH)
            entries.add(HybridAquaticItems.COOKED_CRAYFISH)
            entries.add(HybridAquaticItems.RAW_CRAB)
            entries.add(HybridAquaticItems.COOKED_CRAB)
            entries.add(HybridAquaticItems.RAW_LOBSTER)
            entries.add(HybridAquaticItems.COOKED_LOBSTER)
            entries.add(HybridAquaticItems.RAW_LOBSTER_TAIL)
            entries.add(HybridAquaticItems.COOKED_LOBSTER_TAIL)
            entries.add(HybridAquaticItems.UNI)

            entries.add(HybridAquaticItems.BETTA)
            entries.add(HybridAquaticItems.DANIO)
            entries.add(HybridAquaticItems.NEON_TETRA)
            entries.add(HybridAquaticItems.DISCUS)
            entries.add(HybridAquaticItems.TIGER_BARB)
            entries.add(HybridAquaticItems.OSCAR)
            entries.add(HybridAquaticItems.GOURAMI)
            entries.add(HybridAquaticItems.PIRANHA)
            entries.add(HybridAquaticItems.GOLDFISH)
            entries.add(HybridAquaticItems.KOI)
            entries.add(HybridAquaticItems.CARP)
            entries.add(HybridAquaticItems.ANGLERFISH)
            entries.add(HybridAquaticItems.BARRELEYE)
            entries.add(HybridAquaticItems.DRAGONFISH)
            entries.add(HybridAquaticItems.FLASHLIGHT_FISH)
            entries.add(HybridAquaticItems.RATFISH)
            entries.add(HybridAquaticItems.SQUIRRELFISH)
            entries.add(HybridAquaticItems.CLOWNFISH)
            entries.add(HybridAquaticItems.BLUE_TANG)
            entries.add(HybridAquaticItems.YELLOW_TANG)
            entries.add(HybridAquaticItems.POWDER_BLUE_TANG)
            entries.add(HybridAquaticItems.UNICORNFISH)
            entries.add(HybridAquaticItems.SURGEONFISH_SOHAL)
            entries.add(HybridAquaticItems.SURGEONFISH_ORANGESHOULDER)
            entries.add(HybridAquaticItems.SURGEONFISH_LINED)
            entries.add(HybridAquaticItems.SERGEANT_MAJOR)
            entries.add(HybridAquaticItems.SEAHORSE)
            entries.add(HybridAquaticItems.MACKEREL)
            entries.add(HybridAquaticItems.FLYING_FISH)
            entries.add(HybridAquaticItems.JOHN_DORY)
            entries.add(HybridAquaticItems.PEARLFISH)
            entries.add(HybridAquaticItems.SNAILFISH)
            entries.add(HybridAquaticItems.BOXFISH)
            entries.add(HybridAquaticItems.BLOWFISH)
            entries.add(HybridAquaticItems.STONEFISH)
            entries.add(HybridAquaticItems.GOLDEN_DORADO)
            entries.add(HybridAquaticItems.LIONFISH)
            entries.add(HybridAquaticItems.BLUE_SPOTTED_STINGRAY)
            entries.add(HybridAquaticItems.SPOTTED_EAGLE_RAY)
            entries.add(HybridAquaticItems.TRIGGERFISH)
            entries.add(HybridAquaticItems.ROCKFISH)
            entries.add(HybridAquaticItems.SEA_BASS)
            entries.add(HybridAquaticItems.COELACANTH)
            entries.add(HybridAquaticItems.NEEDLEFISH)
            entries.add(HybridAquaticItems.PARROTFISH)
            entries.add(HybridAquaticItems.MORAY_EEL)
            entries.add(HybridAquaticItems.YELLOWFIN_TUNA)
            entries.add(HybridAquaticItems.BLUEFIN_TUNA)
            entries.add(HybridAquaticItems.MAHI)
            entries.add(HybridAquaticItems.OPAH)
            entries.add(HybridAquaticItems.OARFISH)
            entries.add(HybridAquaticItems.SUNFISH)

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
            entries.add(HybridAquaticItems.GLOW_SLIME)
            entries.add(HybridAquaticItems.SHARK_TOOTH)
            entries.add(HybridAquaticItems.PEARL)
            entries.add(HybridAquaticItems.BLACK_PEARL)
            entries.add(HybridAquaticItems.SULFUR)
            entries.add(HybridAquaticItems.TUBE_SPONGE)
            entries.add(HybridAquaticItems.BUOY)
            entries.add(HybridAquaticItems.CUTTLEBONE)
            entries.add(HybridAquaticItems.CORAL_CHUNK)
            entries.add(HybridAquaticItems.SEA_URCHIN_SPINE)

            // lures
            entries.add(HybridAquaticItems.BARBED_HOOK)
            entries.add(HybridAquaticItems.GLOWING_HOOK)
            entries.add(HybridAquaticItems.MAGNETIC_HOOK)
            entries.add(HybridAquaticItems.CREEPERMAGNET_HOOK)
            entries.add(HybridAquaticItems.OMINOUS_HOOK)

            // tools
            entries.add(HybridAquaticItems.SEASHELL_SPEAR)
            entries.add(HybridAquaticItems.SEASHELL_PICKAXE)
            entries.add(HybridAquaticItems.SEASHELL_AXE)
            entries.add(HybridAquaticItems.SEASHELL_SHOVEL)
            entries.add(HybridAquaticItems.SEASHELL_HOE)
            entries.add(HybridAquaticItems.CORAL_BLADE)
            entries.add(HybridAquaticItems.CORAL_PICKAXE)
            entries.add(HybridAquaticItems.CORAL_AXE)
            entries.add(HybridAquaticItems.CORAL_SHOVEL)
            entries.add(HybridAquaticItems.CORAL_HOE)
            entries.add(HybridAquaticItems.FISHING_NET)

            entries.add(HybridAquaticItems.DIVING_HELMET)
            entries.add(HybridAquaticItems.DIVING_SUIT)
            entries.add(HybridAquaticItems.DIVING_LEGGINGS)
            entries.add(HybridAquaticItems.DIVING_BOOTS)
            entries.add(HybridAquaticItems.NAUTILUS_HELMET)
            entries.add(HybridAquaticItems.NAUTILUS_PAULDRONS)
            entries.add(Items.TURTLE_HELMET)
            entries.add(HybridAquaticItems.TURTLE_CHESTPLATE)
            entries.add(HybridAquaticItems.MANGLERFISH_LURE)
            entries.add(HybridAquaticItems.MANGLERFISH_FIN)
            entries.add(HybridAquaticItems.EEL_SCARF)
            entries.add(HybridAquaticItems.MOON_JELLYFISH_HAT)


            // spawn eggs
            Registries.ITEM.forEach { item ->
                val id = Registries.ITEM.getId(item)
                if (id.namespace != HybridAquatic.MOD_ID) {
                    return@forEach
                }
            }
        }
        .build()
    )

    val SPAWN_EGGS = register("spawn_eggs", FabricItemGroup.builder()
        .displayName(Text.translatable("itemGroup.${HybridAquatic.MOD_ID}.spawn_eggs"))
        .icon { ItemStack(HybridAquaticItems.YELLOWFIN_TUNA) }
        .entries { _, entries ->
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
        return Registry.register(Registries.ITEM_GROUP, Identifier.of(HybridAquatic.MOD_ID, id), itemGroup)
    }
}
