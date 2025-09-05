@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SpawnEggItem

object  HybridAquaticItemGroups {
    val BLOCKS = register("blocks",
        CreativeModeTab.builder(CreativeModeTab.Row.TOP,0)

        .title(Component.translatable("itemGroup.${Constants.MOD_ID}.blocks"))
        .icon { ItemStack(HybridAquaticItems.ANEMONE) }
        .displayItems { _, entries ->
            // message in a bottle variants
            MessageInABottleBlock.Variant.entries.forEach { variant ->
                val blockEntity = MessageInABottleBlockEntity(BlockPos.ZERO, HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.defaultBlockState())
                    .also { blockEntity -> blockEntity.variant = variant }
                val stack = MessageInABottleBlock.createItemStack(blockEntity)
                entries.accept(stack)
            }

            // blocks
            entries.accept(HybridAquaticBlocks.GLOWSTICK.get())
            entries.accept(HybridAquaticBlocks.DRIFTWOOD_LOG.get())
            entries.accept(HybridAquaticBlocks.DRIFTWOOD_WOOD.get())
            entries.accept(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG.get())
            entries.accept(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD.get())
            entries.accept(HybridAquaticBlocks.DRIFTWOOD_PLANKS.get())
            entries.accept(HybridAquaticBlocks.DRIFTWOOD_STAIRS.get())
            entries.accept(HybridAquaticBlocks.DRIFTWOOD_SLAB.get())
            entries.accept(HybridAquaticBlocks.DRIFTWOOD_FENCE.get())
            entries.accept(HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE.get())
            entries.accept(HybridAquaticBlocks.DRIFTWOOD_DOOR.get())
            entries.accept(HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR.get())
            entries.accept(HybridAquaticBlocks.DRIFTWOOD_PRESSURE_PLATE.get())
            entries.accept(HybridAquaticBlocks.DRIFTWOOD_BUTTON.get())

            entries.accept(HybridAquaticBlocks.CRAB_POT.get())
            entries.accept(HybridAquaticBlocks.HYBRID_CRATE.get())
            entries.accept(HybridAquaticBlocks.OAK_CRATE.get())
            entries.accept(HybridAquaticBlocks.SPRUCE_CRATE.get())
            entries.accept(HybridAquaticBlocks.BIRCH_CRATE.get())
            entries.accept(HybridAquaticBlocks.DARK_OAK_CRATE.get())
            entries.accept(HybridAquaticBlocks.JUNGLE_CRATE.get())
            entries.accept(HybridAquaticBlocks.ACACIA_CRATE.get())
            entries.accept(HybridAquaticBlocks.MANGROVE_CRATE.get())
            entries.accept(HybridAquaticBlocks.CHERRY_CRATE.get())

            entries.accept(HybridAquaticItems.SEA_LETTUCE.get())
            entries.accept(HybridAquaticItems.RED_ALGAE.get())
            entries.accept(HybridAquaticItems.BULL_KELP.get())
            entries.accept(HybridAquaticItems.SARGASSUM.get())
            entries.accept(HybridAquaticItems.FLOATING_SARGASSUM.get())
            entries.accept(HybridAquaticItems.WATER_LETTUCE.get())
            entries.accept(HybridAquaticItems.JUNGLE_LILY_PAD.get())

            entries.accept(HybridAquaticItems.BUTTON_CORAL_BLOCK.get())
            entries.accept(HybridAquaticItems.SUN_CORAL_BLOCK.get())
            entries.accept(HybridAquaticItems.LOPHELIA_CORAL_BLOCK.get())
            entries.accept(HybridAquaticItems.THORN_CORAL_BLOCK.get())

            entries.accept(HybridAquaticItems.BUTTON_CORAL.get())
            entries.accept(HybridAquaticItems.SUN_CORAL.get())
            entries.accept(HybridAquaticItems.LOPHELIA_CORAL.get())
            entries.accept(HybridAquaticItems.THORN_CORAL.get())

            entries.accept(HybridAquaticItems.BUTTON_CORAL_FAN.get())
            entries.accept(HybridAquaticItems.SUN_CORAL_FAN.get())
            entries.accept(HybridAquaticItems.LOPHELIA_CORAL_FAN.get())
            entries.accept(HybridAquaticItems.THORN_CORAL_FAN.get())

            entries.accept(HybridAquaticItems.DEAD_BUTTON_CORAL_BLOCK.get())
            entries.accept(HybridAquaticItems.DEAD_SUN_CORAL_BLOCK.get())
            entries.accept(HybridAquaticItems.DEAD_LOPHELIA_CORAL_BLOCK.get())
            entries.accept(HybridAquaticItems.DEAD_THORN_CORAL_BLOCK.get())

            entries.accept(HybridAquaticItems.DEAD_BUTTON_CORAL.get())
            entries.accept(HybridAquaticItems.DEAD_SUN_CORAL.get())
            entries.accept(HybridAquaticItems.DEAD_LOPHELIA_CORAL.get())
            entries.accept(HybridAquaticItems.DEAD_THORN_CORAL.get())

            entries.accept(HybridAquaticItems.DEAD_BUTTON_CORAL_FAN.get())
            entries.accept(HybridAquaticItems.DEAD_SUN_CORAL_FAN.get())
            entries.accept(HybridAquaticItems.DEAD_LOPHELIA_CORAL_FAN.get())
            entries.accept(HybridAquaticItems.DEAD_THORN_CORAL_FAN.get())

            entries.accept(HybridAquaticItems.BUOY.get())
            entries.accept(HybridAquaticItems.RAFT.get())
            entries.accept(HybridAquaticBlocks.ANEMONE.get())
            entries.accept(HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get())
            entries.accept(HybridAquaticBlocks.STRAWBERRY_ANEMONE.get())
            entries.accept(HybridAquaticItems.GIANT_CLAM.get())
            entries.accept(HybridAquaticItems.TUBE_SPONGE.get())
            entries.accept(HybridAquaticItems.TUBE_WORM.get())
            entries.accept(HybridAquaticItems.HYDROTHERMAL_VENT.get())
            entries.accept(HybridAquaticBlocks.BASKING_SHARK_PLUSHIE.get())
            entries.accept(HybridAquaticBlocks.BULL_SHARK_PLUSHIE.get())
            entries.accept(HybridAquaticBlocks.FRILLED_SHARK_PLUSHIE.get())
            entries.accept(HybridAquaticBlocks.GREAT_WHITE_SHARK_PLUSHIE.get())
            entries.accept(HybridAquaticBlocks.HAMMERHEAD_SHARK_PLUSHIE.get())
            entries.accept(HybridAquaticBlocks.THRESHER_SHARK_PLUSHIE.get())
            entries.accept(HybridAquaticBlocks.TIGER_SHARK_PLUSHIE.get())
            entries.accept(HybridAquaticBlocks.WHALE_SHARK_PLUSHIE.get())

            Registries.ITEM.forEach { item ->
                val id = Registries.ITEM.getId(item)
                if (id.namespace != Constants.MOD_ID) {
                    return@forEach
                }
            }
        }
        .build()
    )

    val ITEMS = register("items", FabricItemGroup.builder()
        .displayName(Component.translatable("itemGroup.${Constants.MOD_ID}.items"))
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
            entries.add(HybridAquaticItems.KARKINOS_CLAW)
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
                if (id.namespace != Constants.MOD_ID) {
                    return@forEach
                }
            }
        }
        .build()
    )

    val SPAWN_EGGS = register("spawn_eggs", FabricItemGroup.builder()
        .displayName(Component.translatable("itemGroup.${Constants.MOD_ID}.spawn_eggs"))
        .icon { ItemStack(HybridAquaticItems.YELLOWFIN_TUNA) }
        .entries { _, entries ->
            Registries.ITEM.forEach { item ->
                val id = Registries.ITEM.getId(item)
                if (id.namespace != Constants.MOD_ID) {
                    return@forEach
                }
                if (item is SpawnEggItem) {
                    entries.add(item)
                }
            }
        }
        .build()
    )


    private fun register(id: String, itemGroup: CreativeModeTab): RegistryObject<CreativeModeTab> {
        return CommonClass.CREATIVE_MODE_TABS.register(id) { itemGroup }
    }
}
