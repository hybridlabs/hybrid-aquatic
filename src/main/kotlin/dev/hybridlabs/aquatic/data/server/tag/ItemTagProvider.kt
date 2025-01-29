package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
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

        //region wood

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

        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
            .add(HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE.asItem())

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

        getOrCreateTagBuilder(ItemTags.SWORDS)
            .add(HybridAquaticItems.SEASHELL_SPEAR)

        getOrCreateTagBuilder(ItemTags.SHOVELS)
            .add(HybridAquaticItems.SEASHELL_SHOVEL)

        getOrCreateTagBuilder(ItemTags.AXES)
            .add(HybridAquaticItems.SEASHELL_AXE)

        getOrCreateTagBuilder(ItemTags.PICKAXES)
            .add(HybridAquaticItems.SEASHELL_PICKAXE)

        getOrCreateTagBuilder(ItemTags.HOES)
            .add(HybridAquaticItems.SEASHELL_HOE)

        //endregion

        listOf(
            HybridAquaticItems.KOI,
            HybridAquaticItems.GOLDFISH,
            HybridAquaticItems.DRAGONFISH,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.UNICORNFISH,
            HybridAquaticItems.COWFISH,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.OSCAR,
            HybridAquaticItems.FLASHLIGHT_FISH,
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
            HybridAquaticItems.NEEDLEFISH,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.MEDIUM_FISH).add(item)
        }

        listOf(
            HybridAquaticItems.SUNFISH,
            HybridAquaticItems.OARFISH,
            HybridAquaticItems.TUNA,
            HybridAquaticItems.MAHI,
            HybridAquaticItems.OPAH,
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
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.DRAGONFISH,
            HybridAquaticItems.FLASHLIGHT_FISH,
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
            HybridAquaticItems.TUNA,
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
            getOrCreateTagBuilder(HybridAquaticItemTags.COOKED_FISHES).add(item)
        }

        listOf(
            HybridAquaticItems.RAW_CRAB,
            HybridAquaticItems.RAW_SHRIMP,
            HybridAquaticItems.RAW_LOBSTER,
            HybridAquaticItems.RAW_CRAYFISH,
            HybridAquaticItems.RAW_TENTACLE,
            HybridAquaticItems.RAW_FISH_MEAT,
            HybridAquaticItems.RAW_FISH_STEAK,
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.DRAGONFISH,
            HybridAquaticItems.FLASHLIGHT_FISH,
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
            HybridAquaticItems.TUNA,
            HybridAquaticItems.UNICORNFISH,
            Items.PORKCHOP,
            Items.BEEF,
            Items.MUTTON,
            Items.CHICKEN,
            Items.RABBIT,
            Items.COD,
            Items.SALMON,
            Items.TROPICAL_FISH,
            Items.ROTTEN_FLESH,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.CRUSTACEAN_TEMPT_ITEMS).add(item)
        }

        listOf(
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.TUNA,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.UNICORNFISH,
            HybridAquaticItems.TRIGGERFISH,
            HybridAquaticItems.OSCAR,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.MAHI,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.NEON_TETRA,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.GOURAMI,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.RATFISH,
            HybridAquaticItems.TIGER_BARB,
        ).forEach { item ->
            getOrCreateTagBuilder(ItemTags.FISHES).add(item)
        }

        setOf(
            HybridAquaticItems.RAW_SHRIMP,
            HybridAquaticItems.COOKED_SHRIMP,
            HybridAquaticItems.RAW_CRAYFISH,
            HybridAquaticItems.COOKED_CRAYFISH,
            HybridAquaticItems.RAW_CRAB,
            HybridAquaticItems.COOKED_CRAB,
            HybridAquaticItems.RAW_LOBSTER,
            HybridAquaticItems.COOKED_LOBSTER,
            HybridAquaticItems.RAW_LOBSTER_TAIL,
            HybridAquaticItems.COOKED_LOBSTER_TAIL,
            HybridAquaticItems.COOKED_FISH_STEAK,
            HybridAquaticItems.RAW_FISH_STEAK,
            HybridAquaticItems.COOKED_FISH_MEAT,
            HybridAquaticItems.RAW_FISH_MEAT,
            HybridAquaticItems.RAW_TENTACLE,
            HybridAquaticItems.COOKED_TENTACLE,
            // HybridAquaticItems.MACKEREL, TODO implement new items when they merge
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.DRAGONFISH,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.SURGEONFISH_SOHAL,
            HybridAquaticItems.SURGEONFISH_LINED,
            HybridAquaticItems.SURGEONFISH_ORANGESHOULDER,
            HybridAquaticItems.YELLOW_TANG,
            HybridAquaticItems.POWDER_BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            // HybridAquaticItems.UNICORN_FISH,
            HybridAquaticItems.FLASHLIGHT_FISH,
            HybridAquaticItems.GOURAMI,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.NEON_TETRA,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.OSCAR,
            HybridAquaticItems.COWFISH,
            HybridAquaticItems.KOI,
            // HybridAquaticItems.CARP,
            HybridAquaticItems.GOLDFISH,
            HybridAquaticItems.SEAHORSE,
            HybridAquaticItems.TOADFISH,
            HybridAquaticItems.STONEFISH,
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.ROCKFISH,
            // HybridAquaticItems.SEA_BASS,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.SPOTTED_EAGLE_RAY,
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.RATFISH,
            HybridAquaticItems.TRIGGERFISH,
            HybridAquaticItems.PARROTFISH,
            HybridAquaticItems.MAHI,
            HybridAquaticItems.TUNA,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.OARFISH,
            HybridAquaticItems.SUNFISH,
        ).forEach { item ->
            getOrCreateTagBuilder(ItemTags.MEAT).add(item)
        }

        getOrCreateTagBuilder(HybridAquaticItemTags.REPAIRS_DIVING_HELMET).add(Items.COPPER_INGOT)
        getOrCreateTagBuilder(HybridAquaticItemTags.REPAIRS_NAUTILUS_ARMOR).add(Items.NAUTILUS_SHELL)
        getOrCreateTagBuilder(HybridAquaticItemTags.REPAIRS_MANGLERFISH_ARMOR).add(HybridAquaticItems.GLOW_SLIME)
        getOrCreateTagBuilder(HybridAquaticItemTags.REPAIRS_EEL_SCARF).add(HybridAquaticItems.MORAY_EEL)
        getOrCreateTagBuilder(HybridAquaticItemTags.REPAIRS_MOON_JELLYFISH_HAT).add(Items.SLIME_BALL)

        getOrCreateTagBuilder(HybridAquaticItemTags.SEASHELL_TOOL_MATERIALS).add(Items.NAUTILUS_SHELL)
        getOrCreateTagBuilder(HybridAquaticItemTags.CORAL_TOOL_MATERIALS).add(HybridAquaticItems.CORAL_CHUNK)

        // plushies
        Registries.ITEM
            .filter(filterHybridAquatic(Registries.ITEM))
            .filter { item ->
                val id = Registries.ITEM.getId(item)
                id.path.endsWith("plushie")
            }
            .forEach { item ->
                getOrCreateTagBuilder(HybridAquaticItemTags.PLUSHIES).add(item)
            }

        setOf(
            Items.IRON_AXE,
            Items.IRON_PICKAXE,
            Items.IRON_SHOVEL,
            Items.IRON_HOE,
            Items.IRON_SWORD,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.IRON_TOOLS).add(item)
        }
    }
}
