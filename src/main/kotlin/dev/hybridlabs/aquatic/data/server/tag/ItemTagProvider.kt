package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.tag.ItemTags
import net.minecraft.util.registry.Registry
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // Items that will be consumed by a fishing rod
        setOf(
            HybridAquaticItems.BARBED_HOOK,
            HybridAquaticItems.GLOWING_HOOK,
            HybridAquaticItems.MAGNETIC_HOOK,
            HybridAquaticItems.CREEPERMAGNET_HOOK,
            HybridAquaticItems.OMINOUS_HOOK
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.LURE_ITEMS).add(item)
        }

        //#region wood
        getOrCreateTagBuilder(ItemTags.PLANKS)
            .add(HybridAquaticBlocks.DRIFTWOOD_PLANKS.asItem())

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
            .add(HybridAquaticBlocks.DRIFTWOOD_LOG.asItem())
            .add(HybridAquaticBlocks.DRIFTWOOD_WOOD.asItem())
            .add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG.asItem())
            .add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD.asItem())

        getOrCreateTagBuilder(HybridAquaticItemTags.DRIFTWOOD_LOG_WOOD)
            .add(HybridAquaticItems.DRIFTWOOD_LOG)
            .add(HybridAquaticItems.STRIPPED_DRIFTWOOD_LOG)
            .add(HybridAquaticItems.DRIFTWOOD_WOOD)
            .add(HybridAquaticItems.STRIPPED_DRIFTWOOD_WOOD)

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
            .add(HybridAquaticBlocks.DRIFTWOOD_FENCE.asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
            .add(HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE.asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
            .add(HybridAquaticBlocks.DRIFTWOOD_BUTTON.asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
            .add(HybridAquaticBlocks.DRIFTWOOD_PRESSURE_PLATE.asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
            .add(HybridAquaticBlocks.DRIFTWOOD_SLAB.asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
            .add(HybridAquaticBlocks.DRIFTWOOD_STAIRS.asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
            .add(HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR.asItem())

        //#endregion

        listOf(
            HybridAquaticItems.KOI,
            HybridAquaticItems.CARP,
            HybridAquaticItems.GOLDFISH,
            HybridAquaticItems.DRAGONFISH,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.SERGEANT_MAJOR,
            HybridAquaticItems.UNICORNFISH,
            HybridAquaticItems.BOXFISH,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.FLYING_FISH,
            HybridAquaticItems.SNAILFISH,
            HybridAquaticItems.PEARLFISH,
            HybridAquaticItems.OSCAR,
            HybridAquaticItems.FLASHLIGHT_FISH,
            HybridAquaticItems.SQUIRRELFISH,
            HybridAquaticItems.SURGEONFISH_ORANGESHOULDER,
            HybridAquaticItems.SURGEONFISH_SOHAL,
            HybridAquaticItems.SURGEONFISH_LINED,
            HybridAquaticItems.POWDER_BLUE_TANG,
            HybridAquaticItems.YELLOW_TANG,
            HybridAquaticItems.TOADFISH,
            HybridAquaticItems.STONEFISH,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.GOURAMI,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.NEON_TETRA,
            HybridAquaticItems.MACKEREL,
            HybridAquaticItems.JOHN_DORY,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.SMALL_FISH).add(item)
        }

        listOf(
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.TRIGGERFISH,
            HybridAquaticItems.PARROTFISH,
            HybridAquaticItems.RATFISH,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.SPOTTED_EAGLE_RAY,
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.SEA_BASS,
            HybridAquaticItems.NEEDLEFISH,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.MEDIUM_FISH).add(item)
        }

        listOf(
            HybridAquaticItems.SUNFISH,
            HybridAquaticItems.OARFISH,
            HybridAquaticItems.YELLOWFIN_TUNA,
            HybridAquaticItems.BLUEFIN_TUNA,
            HybridAquaticItems.MAHI,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.GOLDEN_DORADO,
            HybridAquaticItems.COELACANTH,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.LARGE_FISH).add(item)
        }

        listOf(
            HybridAquaticItems.COCONUT_CRAB_CLAW,
            HybridAquaticItems.DUNGENESS_CRAB_CLAW,
            HybridAquaticItems.FIDDLER_CRAB_CLAW,
            HybridAquaticItems.FLOWER_CRAB_CLAW,
            HybridAquaticItems.GHOST_CRAB_CLAW,
            HybridAquaticItems.LIGHTFOOT_CRAB_CLAW,
            HybridAquaticItems.SPIDER_CRAB_CLAW,
            HybridAquaticItems.VAMPIRE_CRAB_CLAW,
            HybridAquaticItems.YETI_CRAB_CLAW,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.CRAB_CLAW).add(item)
        }

        listOf(
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.KOI,
            HybridAquaticItems.GOLDFISH,
            HybridAquaticItems.CARP,
            HybridAquaticItems.MACKEREL,
            HybridAquaticItems.SERGEANT_MAJOR,
            HybridAquaticItems.SEA_BASS,
            HybridAquaticItems.PARROTFISH,
            HybridAquaticItems.SEAHORSE,
            HybridAquaticItems.SURGEONFISH_SOHAL,
            HybridAquaticItems.SURGEONFISH_LINED,
            HybridAquaticItems.SURGEONFISH_ORANGESHOULDER,
            HybridAquaticItems.YELLOW_TANG,
            HybridAquaticItems.POWDER_BLUE_TANG,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.SPOTTED_EAGLE_RAY,
            HybridAquaticItems.YELLOWFIN_TUNA,
            HybridAquaticItems.BLUEFIN_TUNA,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.PEARLFISH,
            HybridAquaticItems.SNAILFISH,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.BOXFISH,
            HybridAquaticItems.STONEFISH,
            HybridAquaticItems.TOADFISH,
            HybridAquaticItems.OARFISH,
            HybridAquaticItems.SUNFISH,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.DRAGONFISH,
            HybridAquaticItems.FLASHLIGHT_FISH,
            HybridAquaticItems.FLYING_FISH,
            HybridAquaticItems.GOLDEN_DORADO,
            HybridAquaticItems.COELACANTH,
            HybridAquaticItems.SQUIRRELFISH,
            HybridAquaticItems.GOURAMI,
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.MAHI,
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.OSCAR,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.RATFISH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.NEON_TETRA,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.TRIGGERFISH,
            HybridAquaticItems.JOHN_DORY,
            HybridAquaticItems.UNICORNFISH,
            HybridAquaticItems.RAW_FISH_MEAT,
            HybridAquaticItems.RAW_FISH_STEAK,
            HybridAquaticItems.RAW_TENTACLE,
            HybridAquaticItems.RAW_CRAB,
            HybridAquaticItems.RAW_SHRIMP,
            HybridAquaticItems.RAW_LOBSTER,
            HybridAquaticItems.RAW_CRAYFISH,
            HybridAquaticItems.RAW_LOBSTER_TAIL,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.RAW_FISH).add(item)
            getOrCreateTagBuilder(HybridAquaticItemTags.RAW_FISHES).add(item)
        }

        listOf(
            HybridAquaticItems.COOKED_FISH_MEAT,
            HybridAquaticItems.COOKED_FISH_STEAK,
            HybridAquaticItems.COOKED_TENTACLE,
            HybridAquaticItems.COOKED_CRAB,
            HybridAquaticItems.COOKED_SHRIMP,
            HybridAquaticItems.COOKED_LOBSTER,
            HybridAquaticItems.COOKED_CRAYFISH,
            HybridAquaticItems.COOKED_LOBSTER_TAIL,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.COOKED_FISH).add(item)
            getOrCreateTagBuilder(HybridAquaticItemTags.COOKED_FISHES).add(item)
        }

        listOf(
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.STONEFISH,
            HybridAquaticItems.TOADFISH,
            HybridAquaticItems.BOXFISH,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.SPOTTED_EAGLE_RAY,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.FOOD_POISONING).add(item)
        }

        listOf(
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.SERGEANT_MAJOR,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.DRAGONFISH,
            HybridAquaticItems.FLASHLIGHT_FISH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.SEA_BASS,
            HybridAquaticItems.SEAHORSE,
            HybridAquaticItems.YELLOW_TANG,
            HybridAquaticItems.POWDER_BLUE_TANG,
            HybridAquaticItems.SURGEONFISH_SOHAL,
            HybridAquaticItems.SURGEONFISH_LINED,
            HybridAquaticItems.SURGEONFISH_ORANGESHOULDER,
            HybridAquaticItems.MACKEREL,
            HybridAquaticItems.BOXFISH,
            HybridAquaticItems.TOADFISH,
            HybridAquaticItems.STONEFISH,
            HybridAquaticItems.PARROTFISH,
            HybridAquaticItems.SUNFISH,
            HybridAquaticItems.CARP,
            HybridAquaticItems.KOI,
            HybridAquaticItems.GOLDFISH,
            HybridAquaticItems.OARFISH,
            HybridAquaticItems.YELLOWFIN_TUNA,
            HybridAquaticItems.BLUEFIN_TUNA,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.UNICORNFISH,
            HybridAquaticItems.TRIGGERFISH,
            HybridAquaticItems.OSCAR,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.MAHI,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.SPOTTED_EAGLE_RAY,
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.NEON_TETRA,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.GOURAMI,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.RATFISH,
            HybridAquaticItems.FLYING_FISH,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.JOHN_DORY,
            HybridAquaticItems.COELACANTH,
            HybridAquaticItems.SQUIRRELFISH,
            HybridAquaticItems.GOLDEN_DORADO,
        ).forEach { item ->
            getOrCreateTagBuilder(ItemTags.FISHES).add(item)
            getOrCreateTagBuilder(HybridAquaticItemTags.COOLING).add(item)
        }

        // plushies
        Registry.ITEM
            .filter(filterHybridAquatic(Registry.ITEM))
            .filter { item ->
                val id = Registry.ITEM.getId(item)
                id.path.endsWith("plushie")
            }
            .forEach { item ->
                getOrCreateTagBuilder(HybridAquaticItemTags.PLUSHIES).add(item)
            }

        setOf(
            HybridAquaticItems.MOON_JELLYFISH_HAT,
            HybridAquaticItems.MANGLERFISH_LURE,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.HAT).add(item)
        }

        setOf(
            HybridAquaticItems.EEL_SCARF,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.SCARF).add(item)
        }

        setOf(
            HybridAquaticItems.MANGLERFISH_FIN,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.BACK_FIN).add(item)
        }

        setOf(
            HybridAquaticItems.MANGLERFISH_LURE,
            HybridAquaticItems.MANGLERFISH_FIN,
            HybridAquaticItems.EEL_SCARF,
            HybridAquaticItems.MOON_JELLYFISH_HAT,
            HybridAquaticItems.NAUTILUS_HELMET,
            HybridAquaticItems.NAUTILUS_PAULDRONS,
            HybridAquaticItems.DIVING_HELMET,
            HybridAquaticItems.DIVING_SUIT,
            HybridAquaticItems.DIVING_LEGGINGS,
            HybridAquaticItems.DIVING_BOOTS,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.ARMORS).add(item)
        }
    }
}
