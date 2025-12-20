package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.HybridAquaticPlatformItems
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        // Items that will be consumed by a fishing rod
        setOf(
            HybridAquaticItems.BARBED_HOOK.get(),
            HybridAquaticItems.GLOWING_HOOK.get(),
            HybridAquaticItems.MAGNETIC_HOOK.get(),
            HybridAquaticItems.CREEPERMAGNET_HOOK.get(),
            HybridAquaticItems.OMINOUS_HOOK.get()
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.LURE_ITEMS).add(item)
        }

        setOf(
            Items.BOW,
            Items.ENCHANTED_BOOK,
            Items.NAME_TAG,
            Items.FISHING_ROD,
            Items.NAUTILUS_SHELL,
            Items.SADDLE
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.FISHING_TREASURE).add(item)
        }

        //#region wood
        getOrCreateTagBuilder(ItemTags.PLANKS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get().asItem())

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get().asItem())
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_WOOD.get().asItem())
            .add(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get().asItem())
            .add(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get().asItem())

        getOrCreateTagBuilder(HybridAquaticItemTags.DRIFTWOOD_LOG_WOOD)
            .add(HybridAquaticPlatformItems.DRIFTWOOD_LOG.get())
            .add(HybridAquaticPlatformItems.STRIPPED_DRIFTWOOD_LOG.get())
            .add(HybridAquaticPlatformItems.DRIFTWOOD_WOOD.get())
            .add(HybridAquaticPlatformItems.STRIPPED_DRIFTWOOD_WOOD.get())

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE.get().asItem())

        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE_GATE.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE_GATE.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_BUTTON.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_SLAB.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_STAIRS.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
            .add(HybridAquaticPlatformBlocks.DRIFTWOOD_TRAPDOOR.get().asItem())

        getOrCreateTagBuilder(ItemTags.SWORDS)
            .add(HybridAquaticItems.SEASHELL_SPEAR.get())
            .add(HybridAquaticItems.CORAL_BLADE.get())

        getOrCreateTagBuilder(ItemTags.SHOVELS)
            .add(HybridAquaticItems.SEASHELL_SHOVEL.get())
            .add(HybridAquaticItems.CORAL_SHOVEL.get())

        getOrCreateTagBuilder(ItemTags.AXES)
            .add(HybridAquaticItems.SEASHELL_AXE.get())
            .add(HybridAquaticItems.CORAL_AXE.get())

        getOrCreateTagBuilder(ItemTags.PICKAXES)
            .add(HybridAquaticItems.SEASHELL_PICKAXE.get())
            .add(HybridAquaticItems.CORAL_PICKAXE.get())

        getOrCreateTagBuilder(ItemTags.HOES)
            .add(HybridAquaticItems.SEASHELL_HOE.get())
            .add(HybridAquaticItems.CORAL_HOE.get())

        getOrCreateTagBuilder(ItemTags.MEAT)
            .forceAddTag(HybridAquaticItemTags.RAW_FISH)
            .forceAddTag(HybridAquaticItemTags.COOKED_FISH)

        //#endregion

        listOf(
            HybridAquaticItems.CARP.get(),
            HybridAquaticItems.PLECO.get(),
            HybridAquaticItems.GOLDFISH.get(),
            HybridAquaticItems.DRAGONFISH.get(),
            HybridAquaticItems.PIRANHA.get(),
            HybridAquaticItems.ANGLERFISH.get(),
            HybridAquaticItems.BARRELEYE.get(),
            HybridAquaticItems.SURGEONFISH.get(),
            HybridAquaticItems.CLOWNFISH.get(),
            HybridAquaticItems.DAMSELFISH.get(),
            HybridAquaticItems.BOXFISH.get(),
            HybridAquaticItems.TIGER_BARB.get(),
            HybridAquaticItems.FLYING_FISH.get(),
            HybridAquaticItems.SNAILFISH.get(),
            HybridAquaticItems.PEARLFISH.get(),
            HybridAquaticItems.OSCAR.get(),
            HybridAquaticItems.FLASHLIGHT_FISH.get(),
            HybridAquaticItems.SQUIRRELFISH.get(),
            HybridAquaticItems.BLOWFISH.get(),
            HybridAquaticItems.STONEFISH.get(),
            HybridAquaticItems.DISCUS.get(),
            HybridAquaticItems.GOURAMI.get(),
            HybridAquaticItems.BETTA.get(),
            HybridAquaticItems.DANIO.get(),
            HybridAquaticItems.NEON_TETRA.get(),
            HybridAquaticItems.MACKEREL.get(),
            HybridAquaticItems.HERRING.get(),
            HybridAquaticItems.JOHN_DORY.get()
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.SMALL_FISH).add(item)
        }

        listOf(
            HybridAquaticItems.MORAY_EEL.get(),
            HybridAquaticItems.TRIGGERFISH.get(),
            HybridAquaticItems.PARROTFISH.get(),
            HybridAquaticItems.SHEEPSHEAD_WRASSE.get(),
            HybridAquaticItems.RATFISH.get(),
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY.get(),
            HybridAquaticItems.SPOTTED_EAGLE_RAY.get(),
            HybridAquaticItems.LIONFISH.get(),
            HybridAquaticItems.ROCKFISH.get(),
            HybridAquaticItems.SEA_BASS.get(),
            HybridAquaticItems.NEEDLEFISH.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.MEDIUM_FISH).add(item)
        }

        listOf(
            HybridAquaticItems.OCEAN_SUNFISH.get(),
            HybridAquaticItems.OARFISH.get(),
            HybridAquaticItems.TUNA.get(),
            HybridAquaticItems.MAHI.get(),
            HybridAquaticItems.OPAH.get(),
            HybridAquaticItems.GOLDEN_DORADO.get(),
            HybridAquaticItems.COELACANTH.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.LARGE_FISH).add(item)
        }

        listOf(
            HybridAquaticItems.COCONUT_CRAB_CLAW.get(),
            HybridAquaticItems.DUNGENESS_CRAB_CLAW.get(),
            HybridAquaticItems.FIDDLER_CRAB_CLAW.get(),
            HybridAquaticItems.FLOWER_CRAB_CLAW.get(),
            HybridAquaticItems.GHOST_CRAB_CLAW.get(),
            HybridAquaticItems.LIGHTFOOT_CRAB_CLAW.get(),
            HybridAquaticItems.SPIDER_CRAB_CLAW.get(),
            HybridAquaticItems.VAMPIRE_CRAB_CLAW.get(),
            HybridAquaticItems.YETI_CRAB_CLAW.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.CRAB_CLAW).add(item)
        }

        listOf(
            HybridAquaticItems.ANGLERFISH.get(),
            HybridAquaticItems.GOLDFISH.get(),
            HybridAquaticItems.CARP.get(),
            HybridAquaticItems.MACKEREL.get(),
            HybridAquaticItems.HERRING.get(),
            HybridAquaticItems.DAMSELFISH.get(),
            HybridAquaticItems.SEA_BASS.get(),
            HybridAquaticItems.PARROTFISH.get(),
            HybridAquaticItems.SHEEPSHEAD_WRASSE.get(),
            HybridAquaticItems.SEAHORSE.get(),
            HybridAquaticItems.BARRELEYE.get(),
            HybridAquaticItems.BETTA.get(),
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY.get(),
            HybridAquaticItems.SPOTTED_EAGLE_RAY.get(),
            HybridAquaticItems.TUNA.get(),
            HybridAquaticItems.SURGEONFISH.get(),
            HybridAquaticItems.PEARLFISH.get(),
            HybridAquaticItems.SNAILFISH.get(),
            HybridAquaticItems.CLOWNFISH.get(),
            HybridAquaticItems.BOXFISH.get(),
            HybridAquaticItems.STONEFISH.get(),
            HybridAquaticItems.BLOWFISH.get(),
            HybridAquaticItems.OARFISH.get(),
            HybridAquaticItems.OCEAN_SUNFISH.get(),
            HybridAquaticItems.DANIO.get(),
            HybridAquaticItems.DISCUS.get(),
            HybridAquaticItems.DRAGONFISH.get(),
            HybridAquaticItems.FLASHLIGHT_FISH.get(),
            HybridAquaticItems.FLYING_FISH.get(),
            HybridAquaticItems.GOLDEN_DORADO.get(),
            HybridAquaticItems.COELACANTH.get(),
            HybridAquaticItems.SQUIRRELFISH.get(),
            HybridAquaticItems.GOURAMI.get(),
            HybridAquaticItems.LIONFISH.get(),
            HybridAquaticItems.MAHI.get(),
            HybridAquaticItems.MORAY_EEL.get(),
            HybridAquaticItems.NEEDLEFISH.get(),
            HybridAquaticItems.OPAH.get(),
            HybridAquaticItems.OSCAR.get(),
            HybridAquaticItems.PIRANHA.get(),
            HybridAquaticItems.RATFISH.get(),
            HybridAquaticItems.ROCKFISH.get(),
            HybridAquaticItems.NEON_TETRA.get(),
            HybridAquaticItems.TIGER_BARB.get(),
            HybridAquaticItems.TRIGGERFISH.get(),
            HybridAquaticItems.JOHN_DORY.get(),
            HybridAquaticItems.RAW_FISH_MEAT.get(),
            HybridAquaticItems.RAW_FISH_STEAK.get(),
            HybridAquaticItems.RAW_TENTACLE.get(),
            HybridAquaticItems.RAW_CRAB.get(),
            HybridAquaticItems.RAW_SHRIMP.get(),
            HybridAquaticItems.RAW_LOBSTER.get(),
            HybridAquaticItems.RAW_CRAYFISH.get(),
            HybridAquaticItems.RAW_LOBSTER_TAIL.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.RAW_FISH).add(item)
            getOrCreateTagBuilder(HybridAquaticItemTags.RAW_FISHES).add(item)
        }

        listOf(
            HybridAquaticItems.COOKED_FISH_MEAT.get(),
            HybridAquaticItems.COOKED_FISH_STEAK.get(),
            HybridAquaticItems.COOKED_TENTACLE.get(),
            HybridAquaticItems.COOKED_CRAB.get(),
            HybridAquaticItems.COOKED_SHRIMP.get(),
            HybridAquaticItems.COOKED_LOBSTER.get(),
            HybridAquaticItems.COOKED_CRAYFISH.get(),
            HybridAquaticItems.COOKED_LOBSTER_TAIL.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.COOKED_FISH).add(item)
            getOrCreateTagBuilder(HybridAquaticItemTags.COOKED_FISHES).add(item)
        }

        listOf(
            HybridAquaticItems.SARGASSUM.get(),
            HybridAquaticItems.BULL_KELP.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.KELPS).add(item)
        }

        listOf(
            HybridAquaticItems.LIONFISH.get(),
            HybridAquaticItems.STONEFISH.get(),
            HybridAquaticItems.BLOWFISH.get(),
            HybridAquaticItems.BOXFISH.get(),
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY.get(),
            HybridAquaticItems.SPOTTED_EAGLE_RAY.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.FOOD_POISONING).add(item)
        }

        listOf(
            HybridAquaticItems.ANGLERFISH.get(),
            HybridAquaticItems.BARRELEYE.get(),
            HybridAquaticItems.CLOWNFISH.get(),
            HybridAquaticItems.DRAGONFISH.get(),
            HybridAquaticItems.FLASHLIGHT_FISH.get(),
            HybridAquaticItems.ROCKFISH.get(),
            HybridAquaticItems.SEA_BASS.get(),
            HybridAquaticItems.SEAHORSE.get(),
            HybridAquaticItems.MACKEREL.get(),
            HybridAquaticItems.HERRING.get(),
            HybridAquaticItems.BOXFISH.get(),
            HybridAquaticItems.BLOWFISH.get(),
            HybridAquaticItems.STONEFISH.get(),
            HybridAquaticItems.PARROTFISH.get(),
            HybridAquaticItems.SHEEPSHEAD_WRASSE.get(),
            HybridAquaticItems.OCEAN_SUNFISH.get(),
            HybridAquaticItems.CARP.get(),
            HybridAquaticItems.GOLDFISH.get(),
            HybridAquaticItems.OARFISH.get(),
            HybridAquaticItems.TUNA.get(),
            HybridAquaticItems.SURGEONFISH.get(),
            HybridAquaticItems.TRIGGERFISH.get(),
            HybridAquaticItems.OSCAR.get(),
            HybridAquaticItems.OPAH.get(),
            HybridAquaticItems.MAHI.get(),
            HybridAquaticItems.NEEDLEFISH.get(),
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY.get(),
            HybridAquaticItems.SPOTTED_EAGLE_RAY.get(),
            HybridAquaticItems.LIONFISH.get(),
            HybridAquaticItems.MORAY_EEL.get(),
            HybridAquaticItems.PIRANHA.get(),
            HybridAquaticItems.NEON_TETRA.get(),
            HybridAquaticItems.DANIO.get(),
            HybridAquaticItems.GOURAMI.get(),
            HybridAquaticItems.BETTA.get(),
            HybridAquaticItems.DISCUS.get(),
            HybridAquaticItems.RATFISH.get(),
            HybridAquaticItems.FLYING_FISH.get(),
            HybridAquaticItems.TIGER_BARB.get(),
            HybridAquaticItems.JOHN_DORY.get(),
            HybridAquaticItems.COELACANTH.get(),
            HybridAquaticItems.SQUIRRELFISH.get(),
            HybridAquaticItems.GOLDEN_DORADO.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(ItemTags.FISHES).add(item)
        }

        // plushies
        BuiltInRegistries.ITEM
            .filter(filterHybridAquatic(BuiltInRegistries.ITEM))
            .filter { item ->
                val id = BuiltInRegistries.ITEM.getKey(item)
                id.path.endsWith("plushie")
            }
            .forEach { item ->
                getOrCreateTagBuilder(HybridAquaticItemTags.PLUSHIES).add(item)
            }

        setOf(
            HybridAquaticItems.MOON_JELLYFISH_HAT.get(),
            HybridAquaticItems.MANGLERFISH_LURE.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.HAT).add(item)
        }

        setOf(
            HybridAquaticItems.EEL_SCARF.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.SCARF).add(item)
        }

        setOf(
            HybridAquaticItems.MANGLERFISH_FIN.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.BACK_FIN).add(item)
        }

        setOf(
            HybridAquaticItems.MANGLERFISH_LURE.get(),
            HybridAquaticItems.MANGLERFISH_FIN.get(),
            HybridAquaticItems.EEL_SCARF.get(),
            HybridAquaticItems.MOON_JELLYFISH_HAT.get(),
            HybridAquaticItems.NAUTILUS_HELMET.get(),
            HybridAquaticItems.NAUTILUS_PAULDRONS.get(),
            HybridAquaticItems.DIVING_HELMET.get(),
            HybridAquaticItems.DIVING_SUIT.get(),
            HybridAquaticItems.DIVING_LEGGINGS.get(),
            HybridAquaticItems.DIVING_BOOTS.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.ARMORS).add(item)
        }
    }
}
