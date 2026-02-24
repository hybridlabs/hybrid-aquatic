package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.SpawnEggItem

object HybridAquaticItemGroups {
    val BLOCKS = register(
        "blocks",
        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.${Constants.MOD_ID}.blocks"))
            .icon { ItemStack(HybridAquaticItems.ANEMONE.get()) }
            .displayItems { _, entries ->
                // message in a bottle variants
                MessageInABottleBlock.Variant.entries.forEach { variant ->
                    val blockEntity = MessageInABottleBlockEntity(
                        BlockPos.ZERO,
                        HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get().defaultBlockState()
                    )
                        .also { blockEntity -> blockEntity.variant = variant }
                    val stack = MessageInABottleBlock.createItemStack(blockEntity)
                    entries.accept(stack)
                }

                // blocks
                entries.accept(HybridAquaticBlocks.SUSPICIOUS_RED_SAND.get())
                entries.accept(HybridAquaticBlocks.AERATED_SAND.get())
                entries.accept(HybridAquaticBlocks.BUBBLE_GEYSER.get())
                entries.accept(HybridAquaticBlocks.WHITE_SAND.get())
                entries.accept(HybridAquaticBlocks.WHITE_SANDSTONE.get())
                entries.accept(HybridAquaticBlocks.GRASSY_SAND.get())
                entries.accept(HybridAquaticBlocks.CORALSTONE.get())
                entries.accept(HybridAquaticBlocks.SHORESTONE.get())
                entries.accept(HybridAquaticBlocks.BARNACLE_SHORESTONE.get())

                entries.accept(HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get())
                entries.accept(HybridAquaticPlatformBlocks.DRIFTWOOD_WOOD.get())
                entries.accept(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get())
                entries.accept(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get())
                entries.accept(HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get())
                entries.accept(HybridAquaticPlatformBlocks.DRIFTWOOD_STAIRS.get())
                entries.accept(HybridAquaticPlatformBlocks.DRIFTWOOD_SLAB.get())
                entries.accept(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE.get())
                entries.accept(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE_GATE.get())
                entries.accept(HybridAquaticPlatformBlocks.DRIFTWOOD_DOOR.get())
                entries.accept(HybridAquaticPlatformBlocks.DRIFTWOOD_TRAPDOOR.get())
                entries.accept(HybridAquaticPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get())
                entries.accept(HybridAquaticPlatformBlocks.DRIFTWOOD_BUTTON.get())

                entries.accept(HybridAquaticBlocks.CRAB_POT.get())
                entries.accept(HybridAquaticBlocks.HYBRID_CRATE.get())
                entries.accept(HybridAquaticBlocks.OAK_CRATE.get())
                entries.accept(HybridAquaticBlocks.SPRUCE_CRATE.get())
                entries.accept(HybridAquaticBlocks.BIRCH_CRATE.get())
                entries.accept(HybridAquaticBlocks.DARK_OAK_CRATE.get())
                entries.accept(HybridAquaticBlocks.JUNGLE_CRATE.get())
                entries.accept(HybridAquaticBlocks.BAMBOO_CRATE.get())
                entries.accept(HybridAquaticBlocks.ACACIA_CRATE.get())
                entries.accept(HybridAquaticBlocks.MANGROVE_CRATE.get())
                entries.accept(HybridAquaticBlocks.CHERRY_CRATE.get())
                entries.accept(HybridAquaticBlocks.PEARL_BLOCK.get())
                entries.accept(HybridAquaticBlocks.BLACK_PEARL_BLOCK.get())
                entries.accept(HybridAquaticBlocks.GLOWSLIME_BLOCK.get())
                entries.accept(HybridAquaticBlocks.GLOWSTICK.get())
                entries.accept(HybridAquaticItems.BUOY.get())
                entries.accept(HybridAquaticItems.RAFT.get())
                entries.accept(HybridAquaticItems.OAK_RAFT.get())
                entries.accept(HybridAquaticItems.SPRUCE_RAFT.get())
                entries.accept(HybridAquaticItems.BIRCH_RAFT.get())
                entries.accept(HybridAquaticItems.DARK_OAK_RAFT.get())
                entries.accept(HybridAquaticItems.JUNGLE_RAFT.get())
                entries.accept(HybridAquaticItems.ACACIA_RAFT.get())
                entries.accept(HybridAquaticItems.MANGROVE_RAFT.get())
                entries.accept(HybridAquaticItems.CHERRY_RAFT.get())
                entries.accept(HybridAquaticItems.DRIFTWOOD_RAFT.get())

                entries.accept(HybridAquaticPlatformItems.DUNEGRASS.get())
                entries.accept(HybridAquaticPlatformItems.TALL_DUNEGRASS.get())
                entries.accept(HybridAquaticItems.SEA_LETTUCE.get())
                entries.accept(HybridAquaticItems.SHORT_RED_ALGAE.get())
                entries.accept(HybridAquaticItems.RED_ALGAE.get())
                entries.accept(HybridAquaticItems.BULL_KELP.get())
                entries.accept(HybridAquaticItems.SARGASSUM.get())
                entries.accept(HybridAquaticItems.FLOATING_SARGASSUM.get())
                entries.accept(HybridAquaticItems.WATER_LETTUCE.get())
                entries.accept(HybridAquaticItems.WATER_HYACINTH.get())
                entries.accept(HybridAquaticItems.JUNGLE_LILY_PAD.get())

                entries.accept(HybridAquaticItems.SUN_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.LEAF_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.BUTTON_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.ROSE_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.LOPHELIA_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.THORN_CORAL_BLOCK.get())

                entries.accept(HybridAquaticItems.DEAD_SUN_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.DEAD_LEAF_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.DEAD_BUTTON_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.DEAD_ROSE_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.DEAD_LOPHELIA_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.DEAD_THORN_CORAL_BLOCK.get())

                entries.accept(HybridAquaticItems.BLEACHED_SUN_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.BLEACHED_LEAF_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.BLEACHED_BUTTON_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.BLEACHED_ROSE_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.BLEACHED_LOPHELIA_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.BLEACHED_THORN_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.BLEACHED_FIRE_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.BLEACHED_TUBE_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.BLEACHED_HORN_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.BLEACHED_BRAIN_CORAL_BLOCK.get())
                entries.accept(HybridAquaticItems.BLEACHED_BUBBLE_CORAL_BLOCK.get())

                entries.accept(HybridAquaticItems.SUN_CORAL.get())
                entries.accept(HybridAquaticItems.LEAF_CORAL.get())
                entries.accept(HybridAquaticItems.BUTTON_CORAL.get())
                entries.accept(HybridAquaticItems.ROSE_CORAL.get())
                entries.accept(HybridAquaticItems.LOPHELIA_CORAL.get())
                entries.accept(HybridAquaticItems.THORN_CORAL.get())

                entries.accept(HybridAquaticItems.DEAD_SUN_CORAL.get())
                entries.accept(HybridAquaticItems.DEAD_LEAF_CORAL.get())
                entries.accept(HybridAquaticItems.DEAD_BUTTON_CORAL.get())
                entries.accept(HybridAquaticItems.DEAD_ROSE_CORAL.get())
                entries.accept(HybridAquaticItems.DEAD_LOPHELIA_CORAL.get())
                entries.accept(HybridAquaticItems.DEAD_THORN_CORAL.get())

                entries.accept(HybridAquaticItems.BLEACHED_SUN_CORAL.get())
                entries.accept(HybridAquaticItems.BLEACHED_LEAF_CORAL.get())
                entries.accept(HybridAquaticItems.BLEACHED_BUTTON_CORAL.get())
                entries.accept(HybridAquaticItems.BLEACHED_ROSE_CORAL.get())
                entries.accept(HybridAquaticItems.BLEACHED_LOPHELIA_CORAL.get())
                entries.accept(HybridAquaticItems.BLEACHED_THORN_CORAL.get())
                entries.accept(HybridAquaticItems.BLEACHED_FIRE_CORAL.get())
                entries.accept(HybridAquaticItems.BLEACHED_TUBE_CORAL.get())
                entries.accept(HybridAquaticItems.BLEACHED_HORN_CORAL.get())
                entries.accept(HybridAquaticItems.BLEACHED_BRAIN_CORAL.get())
                entries.accept(HybridAquaticItems.BLEACHED_BUBBLE_CORAL.get())

                entries.accept(HybridAquaticItems.SUN_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.LEAF_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.BUTTON_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.ROSE_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.LOPHELIA_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.THORN_CORAL_FAN.get())

                entries.accept(HybridAquaticItems.DEAD_SUN_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.DEAD_LEAF_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.DEAD_BUTTON_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.DEAD_ROSE_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.DEAD_LOPHELIA_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.DEAD_THORN_CORAL_FAN.get())

                entries.accept(HybridAquaticItems.BLEACHED_SUN_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.BLEACHED_LEAF_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.BLEACHED_BUTTON_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.BLEACHED_ROSE_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.BLEACHED_LOPHELIA_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.BLEACHED_THORN_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.BLEACHED_FIRE_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.BLEACHED_TUBE_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.BLEACHED_HORN_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.BLEACHED_BRAIN_CORAL_FAN.get())
                entries.accept(HybridAquaticItems.BLEACHED_BUBBLE_CORAL_FAN.get())

                entries.accept(HybridAquaticBlocks.ANEMONE.get())
                entries.accept(HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get())
                entries.accept(HybridAquaticBlocks.STRAWBERRY_ANEMONE.get())
                entries.accept(HybridAquaticItems.GIANT_CLAM.get())
                entries.accept(HybridAquaticItems.OYSTER_BLOCK.get())
                entries.accept(HybridAquaticItems.TUBE_SPONGE.get())
                entries.accept(HybridAquaticItems.GLASS_SPONGE.get())
                entries.accept(HybridAquaticItems.HARP_SPONGE.get())
                entries.accept(HybridAquaticItems.TUBE_WORM.get())
                entries.accept(HybridAquaticItems.HYDROTHERMAL_VENT.get())
                entries.accept(HybridAquaticItems.CRYSTALLINE_SULFUR.get())
                entries.accept(HybridAquaticItems.DEPTH_CHARGE.get())
                entries.accept(HybridAquaticBlocks.BASKING_SHARK_PLUSHIE.get())
                entries.accept(HybridAquaticBlocks.BULL_SHARK_PLUSHIE.get())
                entries.accept(HybridAquaticBlocks.FRILLED_SHARK_PLUSHIE.get())
                entries.accept(HybridAquaticBlocks.GREAT_WHITE_SHARK_PLUSHIE.get())
                entries.accept(HybridAquaticBlocks.HAMMERHEAD_SHARK_PLUSHIE.get())
                entries.accept(HybridAquaticBlocks.THRESHER_SHARK_PLUSHIE.get())
                entries.accept(HybridAquaticBlocks.TIGER_SHARK_PLUSHIE.get())
                entries.accept(HybridAquaticBlocks.WHALE_SHARK_PLUSHIE.get())

                BuiltInRegistries.ITEM.forEach { item ->
                    val id = BuiltInRegistries.ITEM.getKey(item)
                    if (id.namespace != Constants.MOD_ID) {
                        return@forEach
                    }
                }
            }
            .build()
    )

    val ITEMS = register(
        "items",
        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.${Constants.MOD_ID}.items"))
            .icon { ItemStack(HybridAquaticItems.BARBED_HOOK.get()) }
            .displayItems { _, entries ->

                // food items
                entries.accept(HybridAquaticItems.RAW_FISH_MEAT.get())
                entries.accept(HybridAquaticItems.COOKED_FISH_MEAT.get())
                entries.accept(HybridAquaticItems.RAW_FISH_STEAK.get())
                entries.accept(HybridAquaticItems.COOKED_FISH_STEAK.get())
                entries.accept(HybridAquaticItems.RAW_TENTACLE.get())
                entries.accept(HybridAquaticItems.COOKED_TENTACLE.get())
                entries.accept(HybridAquaticItems.RAW_SHRIMP.get())
                entries.accept(HybridAquaticItems.COOKED_SHRIMP.get())
                entries.accept(HybridAquaticItems.RAW_CRAYFISH.get())
                entries.accept(HybridAquaticItems.COOKED_CRAYFISH.get())
                entries.accept(HybridAquaticItems.RAW_CRAB.get())
                entries.accept(HybridAquaticItems.COOKED_CRAB.get())
                entries.accept(HybridAquaticItems.RAW_LOBSTER.get())
                entries.accept(HybridAquaticItems.COOKED_LOBSTER.get())
                entries.accept(HybridAquaticItems.RAW_LOBSTER_TAIL.get())
                entries.accept(HybridAquaticItems.COOKED_LOBSTER_TAIL.get())
                entries.accept(HybridAquaticItems.UNI.get())
                entries.accept(HybridAquaticItems.OYSTER.get())

                entries.accept(HybridAquaticItems.BETTA.get())
                entries.accept(HybridAquaticItems.DANIO.get())
                entries.accept(HybridAquaticItems.NEON_TETRA.get())
                entries.accept(HybridAquaticItems.DISCUS.get())
                entries.accept(HybridAquaticItems.TIGER_BARB.get())
                entries.accept(HybridAquaticItems.OSCAR.get())
                entries.accept(HybridAquaticItems.GOURAMI.get())
                entries.accept(HybridAquaticItems.PIRANHA.get())
                entries.accept(HybridAquaticItems.GOLDFISH.get())
                entries.accept(HybridAquaticItems.CARP.get())
                entries.accept(HybridAquaticItems.PLECO.get())
                entries.accept(HybridAquaticItems.ANGLERFISH.get())
                entries.accept(HybridAquaticItems.BARRELEYE.get())
                entries.accept(HybridAquaticItems.DRAGONFISH.get())
                entries.accept(HybridAquaticItems.FLASHLIGHT_FISH.get())
                entries.accept(HybridAquaticItems.RATFISH.get())
                entries.accept(HybridAquaticItems.SQUIRRELFISH.get())
                entries.accept(HybridAquaticItems.CLOWNFISH.get())
                entries.accept(HybridAquaticItems.SURGEONFISH.get())
                entries.accept(HybridAquaticItems.DAMSELFISH.get())
                entries.accept(HybridAquaticItems.SEAHORSE.get())
                entries.accept(HybridAquaticItems.MACKEREL.get())
                entries.accept(HybridAquaticItems.HERRING.get())
                entries.accept(HybridAquaticItems.FLYING_FISH.get())
                entries.accept(HybridAquaticItems.JOHN_DORY.get())
                entries.accept(HybridAquaticItems.PEARLFISH.get())
                entries.accept(HybridAquaticItems.SNAILFISH.get())
                entries.accept(HybridAquaticItems.BOXFISH.get())
                entries.accept(HybridAquaticItems.BLOWFISH.get())
                entries.accept(HybridAquaticItems.STONEFISH.get())
                entries.accept(HybridAquaticItems.GOLDEN_DORADO.get())
                entries.accept(HybridAquaticItems.LIONFISH.get())
                entries.accept(HybridAquaticItems.BLUE_SPOTTED_STINGRAY.get())
                entries.accept(HybridAquaticItems.SPOTTED_EAGLE_RAY.get())
                entries.accept(HybridAquaticItems.TRIGGERFISH.get())
                entries.accept(HybridAquaticItems.ROCKFISH.get())
                entries.accept(HybridAquaticItems.SEA_BASS.get())
                entries.accept(HybridAquaticItems.COELACANTH.get())
                entries.accept(HybridAquaticItems.NEEDLEFISH.get())
                entries.accept(HybridAquaticItems.PARROTFISH.get())
                entries.accept(HybridAquaticItems.SHEEPSHEAD_WRASSE.get())
                entries.accept(HybridAquaticItems.MORAY_EEL.get())
                entries.accept(HybridAquaticItems.TUNA.get())
                entries.accept(HybridAquaticItems.MAHI.get())
                entries.accept(HybridAquaticItems.OPAH.get())
                entries.accept(HybridAquaticItems.OARFISH.get())
                entries.accept(HybridAquaticItems.OCEAN_SUNFISH.get())

                // miscellaneous items
                entries.accept(HybridAquaticItems.LOBSTER_CLAW.get())
                entries.accept(HybridAquaticItems.COCONUT_CRAB_CLAW.get())
                entries.accept(HybridAquaticItems.DUNGENESS_CRAB_CLAW.get())
                entries.accept(HybridAquaticItems.FIDDLER_CRAB_CLAW.get())
                entries.accept(HybridAquaticItems.VAMPIRE_CRAB_CLAW.get())
                entries.accept(HybridAquaticItems.FLOWER_CRAB_CLAW.get())
                entries.accept(HybridAquaticItems.GHOST_CRAB_CLAW.get())
                entries.accept(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW.get())
                entries.accept(HybridAquaticItems.YETI_CRAB_CLAW.get())
                entries.accept(HybridAquaticItems.SPIDER_CRAB_CLAW.get())
                entries.accept(HybridAquaticItems.KARKINOS_CLAW.get())
                entries.accept(HybridAquaticItems.GLOWSLIME.get())
                entries.accept(HybridAquaticItems.SHARK_TOOTH.get())
                entries.accept(HybridAquaticItems.PEARL.get())
                entries.accept(HybridAquaticItems.BLACK_PEARL.get())
                entries.accept(HybridAquaticItems.SULFUR.get())
                entries.accept(HybridAquaticItems.TUBE_SPONGE.get())
                entries.accept(HybridAquaticItems.BUOY.get())
                entries.accept(HybridAquaticItems.CUTTLEBONE.get())
                entries.accept(HybridAquaticItems.CORAL_CHUNK.get())
                entries.accept(HybridAquaticItems.PRISMARINE_ROD.get())
                entries.accept(HybridAquaticItems.SEA_URCHIN_SPINE.get())

                // lures
                entries.accept(HybridAquaticItems.BARBED_HOOK.get())
                entries.accept(HybridAquaticItems.GLOWING_HOOK.get())
                entries.accept(HybridAquaticItems.MAGNETIC_HOOK.get())
                entries.accept(HybridAquaticItems.CREEPERMAGNET_HOOK.get())
                entries.accept(HybridAquaticItems.OMINOUS_HOOK.get())

                // tools
                entries.accept(HybridAquaticItems.SEASHELL_SPEAR.get())
                entries.accept(HybridAquaticItems.SEASHELL_PICKAXE.get())
                entries.accept(HybridAquaticItems.SEASHELL_AXE.get())
                entries.accept(HybridAquaticItems.SEASHELL_SHOVEL.get())
                entries.accept(HybridAquaticItems.SEASHELL_HOE.get())
                entries.accept(HybridAquaticItems.CORAL_BLADE.get())
                entries.accept(HybridAquaticItems.CORAL_PICKAXE.get())
                entries.accept(HybridAquaticItems.CORAL_AXE.get())
                entries.accept(HybridAquaticItems.CORAL_SHOVEL.get())
                entries.accept(HybridAquaticItems.CORAL_HOE.get())
                entries.accept(HybridAquaticItems.FISHING_NET.get())
                entries.accept(HybridAquaticItems.BRINE_BUCKET.get())

                entries.accept(HybridAquaticItems.DIVING_HELMET.get())
                entries.accept(HybridAquaticItems.DIVING_SUIT.get())
                entries.accept(HybridAquaticItems.DIVING_LEGGINGS.get())
                entries.accept(HybridAquaticItems.DIVING_BOOTS.get())
                entries.accept(HybridAquaticItems.REINFORCED_DIVING_HELMET.get())
                entries.accept(HybridAquaticItems.REINFORCED_DIVING_SUIT.get())
                entries.accept(HybridAquaticItems.REINFORCED_DIVING_LEGGINGS.get())
                entries.accept(HybridAquaticItems.REINFORCED_DIVING_BOOTS.get())
                entries.accept(HybridAquaticItems.NAUTILUS_HELMET.get())
                entries.accept(HybridAquaticItems.NAUTILUS_PAULDRONS.get())
                entries.accept(Items.TURTLE_HELMET)
                entries.accept(HybridAquaticItems.TURTLE_CHESTPLATE.get())
                entries.accept(HybridAquaticItems.MANGLERFISH_LURE.get())
                entries.accept(HybridAquaticItems.MANGLERFISH_FIN.get())
                entries.accept(HybridAquaticItems.EEL_SCARF.get())
                entries.accept(HybridAquaticItems.MOON_JELLYFISH_HAT.get())


                // spawn eggs
                BuiltInRegistries.ITEM.forEach { item ->
                    val id = BuiltInRegistries.ITEM.getKey(item)
                    if (id.namespace != Constants.MOD_ID) {
                        return@forEach
                    }
                }
            }
            .build()
    )

    val SPAWN_EGGS = register(
        "spawn_eggs",

        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.${Constants.MOD_ID}.spawn_eggs"))
            .icon { ItemStack(HybridAquaticItems.TUNA.get()) }
            .displayItems { _, entries ->
                BuiltInRegistries.ITEM.forEach { item ->
                    val id = BuiltInRegistries.ITEM.getKey(item)
                    if (id.namespace != Constants.MOD_ID) {
                        return@forEach
                    }
                    if (item is SpawnEggItem) {

                        entries.accept(item)
                    }
                }
            }
            .build()
    )


    private fun register(id: String, itemGroup: CreativeModeTab): RegistryObject<CreativeModeTab> {
        return CommonClass.CREATIVE_MODE_TABS.register(id) { itemGroup }
    }
}