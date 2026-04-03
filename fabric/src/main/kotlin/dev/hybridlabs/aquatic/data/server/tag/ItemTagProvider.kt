package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.wood.HAPlatformBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HAAquaticItems
import dev.hybridlabs.aquatic.item.HAPlatformItems
import dev.hybridlabs.aquatic.tag.HAItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        // Items that will be consumed by a fishing rod
        setOf(
            HAAquaticItems.BARBED_HOOK.get(),
            HAAquaticItems.GLOWING_HOOK.get(),
            HAAquaticItems.MAGNETIC_HOOK.get(),
            HAAquaticItems.CREEPERMAGNET_HOOK.get(),
            HAAquaticItems.OMINOUS_HOOK.get()
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.LURE_ITEMS).add(item)
        }

        setOf(
            Items.BOW,
            Items.ENCHANTED_BOOK,
            Items.NAME_TAG,
            Items.FISHING_ROD,
            Items.NAUTILUS_SHELL,
            Items.SADDLE
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.FISHING_TREASURE).add(item)
        }

        //#region Diving Armor
        getOrCreateTagBuilder(HAItemTags.DIVING_ARMOR)
            .addTag(HAItemTags.DIVING_HELMET)
            .addTag(HAItemTags.DIVING_SUIT)
            .addTag(HAItemTags.DIVING_LEGGINGS)
            .addTag(HAItemTags.DIVING_BOOTS)

        getOrCreateTagBuilder(HAItemTags.DIVING_HELMET)
            .add(
                HAAquaticItems.DIVING_HELMET.get(),
                HAAquaticItems.REINFORCED_DIVING_HELMET.get(),
                HAAquaticItems.GLOWING_DIVING_HELMET.get(),
            )
            .addOptional(ResourceLocation("create", "copper_diving_helmet"))
            .addOptional(ResourceLocation("create", "netherite_diving_helmet"))
            .addOptional(ResourceLocation("aquamirae", "three_bolt_helmet"))
            .addOptional(ResourceLocation("alexscaves", "diving_helmet"))

        getOrCreateTagBuilder(HAItemTags.DIVING_SUIT)
            .add(
                HAAquaticItems.DIVING_SUIT.get(),
                HAAquaticItems.REINFORCED_DIVING_SUIT.get(),
                HAAquaticItems.GLOWING_DIVING_SUIT.get(),
            )
            .addOptional(ResourceLocation("aquamirae", "three_bolt_chestplate"))
            .addOptional(ResourceLocation("alexscaves", "diving_chestplate"))

        getOrCreateTagBuilder(HAItemTags.DIVING_LEGGINGS)
            .add(
                HAAquaticItems.DIVING_LEGGINGS.get(),
                HAAquaticItems.REINFORCED_DIVING_LEGGINGS.get(),
                HAAquaticItems.GLOWING_DIVING_LEGGINGS.get(),
            )
            .addOptional(ResourceLocation("aquamirae", "three_bolt_leggings"))
            .addOptional(ResourceLocation("alexscaves", "diving_leggings"))

        getOrCreateTagBuilder(HAItemTags.DIVING_BOOTS)
            .add(
                HAAquaticItems.DIVING_BOOTS.get(),
                HAAquaticItems.REINFORCED_DIVING_BOOTS.get(),
                HAAquaticItems.GLOWING_DIVING_BOOTS.get(),
            )
            .addOptional(ResourceLocation("create", "copper_diving_boots"))
            .addOptional(ResourceLocation("create", "netherite_diving_boots"))
            .addOptional(ResourceLocation("aquamirae", "three_bolt_boots"))
            .addOptional(ResourceLocation("alexscaves", "diving_boots"))
        //#endregion

        //#region Effect Tags
        getOrCreateTagBuilder(HAItemTags.RESISTS_CORROSION)
            .add(
                Items.DIAMOND_AXE,
                Items.DIAMOND_PICKAXE,
                Items.DIAMOND_SWORD,
                Items.DIAMOND_SHOVEL,
                Items.DIAMOND_HOE,
                Items.DIAMOND_HELMET,
                Items.DIAMOND_CHESTPLATE,
                Items.DIAMOND_LEGGINGS,
                Items.DIAMOND_BOOTS,

                Items.GOLDEN_AXE,
                Items.GOLDEN_PICKAXE,
                Items.GOLDEN_SWORD,
                Items.GOLDEN_SHOVEL,
                Items.GOLDEN_HOE,
                Items.GOLDEN_HELMET,
                Items.GOLDEN_CHESTPLATE,
                Items.GOLDEN_LEGGINGS,
                Items.GOLDEN_BOOTS,

                HAAquaticItems.REINFORCED_DIVING_HELMET.get(),
                HAAquaticItems.REINFORCED_DIVING_SUIT.get(),
                HAAquaticItems.REINFORCED_DIVING_LEGGINGS.get(),
                HAAquaticItems.REINFORCED_DIVING_BOOTS.get(),
            )
        //#endregion

        //#region Wood Tags
        getOrCreateTagBuilder(ItemTags.PLANKS)
            .add(HAPlatformBlocks.DRIFTWOOD_PLANKS.get().asItem())

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
            .add(HAPlatformBlocks.DRIFTWOOD_LOG.get().asItem())
            .add(HAPlatformBlocks.DRIFTWOOD_WOOD.get().asItem())
            .add(HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get().asItem())
            .add(HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get().asItem())

        getOrCreateTagBuilder(HAItemTags.DRIFTWOOD_LOG_WOOD)
            .add(HAPlatformItems.DRIFTWOOD_LOG.get())
            .add(HAPlatformItems.STRIPPED_DRIFTWOOD_LOG.get())
            .add(HAPlatformItems.DRIFTWOOD_WOOD.get())
            .add(HAPlatformItems.STRIPPED_DRIFTWOOD_WOOD.get())

        getOrCreateTagBuilder(HAItemTags.STRIPPED_LOGS)
            .add(HAPlatformItems.STRIPPED_DRIFTWOOD_LOG.get())

        getOrCreateTagBuilder(HAItemTags.STRIPPED_WOODS)
            .add(HAPlatformItems.STRIPPED_DRIFTWOOD_WOOD.get())

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
            .add(HAPlatformBlocks.DRIFTWOOD_FENCE.get().asItem())

        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
            .add(HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
            .add(HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
            .add(HAPlatformBlocks.DRIFTWOOD_BUTTON.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
            .add(HAPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
            .add(HAPlatformBlocks.DRIFTWOOD_SLAB.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
            .add(HAPlatformBlocks.DRIFTWOOD_STAIRS.get().asItem())

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
            .add(HAPlatformBlocks.DRIFTWOOD_TRAPDOOR.get().asItem())
        //#endregion

        getOrCreateTagBuilder(HAItemTags.STONES)
            .add(HAAquaticItems.SHORESTONE.get())
            .add(HAAquaticItems.BARNACLE_SHORESTONE.get())
            .add(HAAquaticItems.CORALSTONE.get())

        getOrCreateTagBuilder(HAItemTags.SANDS)
            .add(HAAquaticItems.WHITE_SAND.get())
            .add(HAAquaticItems.GRASSY_SAND.get())

        //#region Tool Tags
        getOrCreateTagBuilder(HAItemTags.CORAL_SET)
            .add(HAAquaticItems.CORAL_BLADE.get())
            .add(HAAquaticItems.CORAL_PICKAXE.get())
            .add(HAAquaticItems.CORAL_AXE.get())
            .add(HAAquaticItems.CORAL_SHOVEL.get())
            .add(HAAquaticItems.CORAL_HOE.get())

        getOrCreateTagBuilder(HAItemTags.SEASHELL_SET)
            .add(HAAquaticItems.SEASHELL_SPEAR.get())
            .add(HAAquaticItems.SEASHELL_PICKAXE.get())
            .add(HAAquaticItems.SEASHELL_AXE.get())
            .add(HAAquaticItems.SEASHELL_SHOVEL.get())
            .add(HAAquaticItems.SEASHELL_HOE.get())

        getOrCreateTagBuilder(HAItemTags.TURTLE_SET)
            .add(HAAquaticItems.TURTLE_CHESTPLATE.get())
            .add(Items.TURTLE_HELMET)

        getOrCreateTagBuilder(ItemTags.SWORDS)
            .add(HAAquaticItems.SEASHELL_SPEAR.get())
            .add(HAAquaticItems.CORAL_BLADE.get())

        getOrCreateTagBuilder(ItemTags.SHOVELS)
            .add(HAAquaticItems.SEASHELL_SHOVEL.get())
            .add(HAAquaticItems.CORAL_SHOVEL.get())

        getOrCreateTagBuilder(ItemTags.AXES)
            .add(HAAquaticItems.SEASHELL_AXE.get())
            .add(HAAquaticItems.CORAL_AXE.get())

        getOrCreateTagBuilder(ItemTags.PICKAXES)
            .add(HAAquaticItems.SEASHELL_PICKAXE.get())
            .add(HAAquaticItems.CORAL_PICKAXE.get())

        getOrCreateTagBuilder(ItemTags.HOES)
            .add(HAAquaticItems.SEASHELL_HOE.get())
            .add(HAAquaticItems.CORAL_HOE.get())
        //#endregion

        listOf(
            HAAquaticItems.RAW_LOBSTER.get(),
            HAAquaticItems.COOKED_LOBSTER.get(),
            HAAquaticItems.RAW_LOBSTER_TAIL.get(),
            HAAquaticItems.COOKED_LOBSTER_TAIL.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.LOBSTER_MEAT).add(item)
        }

        listOf(
            HAAquaticItems.RAW_LOBSTER.get(),
            HAAquaticItems.COOKED_LOBSTER.get(),
            HAAquaticItems.RAW_LOBSTER_TAIL.get(),
            HAAquaticItems.COOKED_LOBSTER_TAIL.get(),
            HAAquaticItems.RAW_CRAB.get(),
            HAAquaticItems.COOKED_CRAB.get(),
            HAAquaticItems.RAW_SHRIMP.get(),
            HAAquaticItems.COOKED_SHRIMP.get(),
            HAAquaticItems.RAW_CRAYFISH.get(),
            HAAquaticItems.COOKED_CRAYFISH.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.CRUSTACEAN_MEAT).add(item)
        }

        listOf(
            HAAquaticItems.CARP.get(),
            HAAquaticItems.TROUT.get(),
            HAAquaticItems.SUNFISH.get(),
            HAAquaticItems.PLECO.get(),
            HAAquaticItems.GOLDFISH.get(),
            HAAquaticItems.DRAGONFISH.get(),
            HAAquaticItems.PIRANHA.get(),
            HAAquaticItems.ANGLERFISH.get(),
            HAAquaticItems.BARRELEYE.get(),
            HAAquaticItems.SURGEONFISH.get(),
            HAAquaticItems.CLOWNFISH.get(),
            HAAquaticItems.DAMSELFISH.get(),
            HAAquaticItems.BOXFISH.get(),
            HAAquaticItems.TIGER_BARB.get(),
            HAAquaticItems.FLYING_FISH.get(),
            HAAquaticItems.SNAILFISH.get(),
            HAAquaticItems.PEARLFISH.get(),
            HAAquaticItems.OSCAR.get(),
            HAAquaticItems.FLASHLIGHT_FISH.get(),
            HAAquaticItems.SQUIRRELFISH.get(),
            HAAquaticItems.BLOWFISH.get(),
            HAAquaticItems.STONEFISH.get(),
            HAAquaticItems.DISCUS.get(),
            HAAquaticItems.GOURAMI.get(),
            HAAquaticItems.BETTA.get(),
            HAAquaticItems.DANIO.get(),
            HAAquaticItems.NEON_TETRA.get(),
            HAAquaticItems.MACKEREL.get(),
            HAAquaticItems.HERRING.get(),
            HAAquaticItems.JOHN_DORY.get(),
            Items.COD,
            Items.SALMON,
            Items.TROPICAL_FISH,
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.SMALL_FISH).add(item)
        }

        listOf(
            HAAquaticItems.MORAY_EEL.get(),
            HAAquaticItems.TRIGGERFISH.get(),
            HAAquaticItems.TREVALLY.get(),
            HAAquaticItems.PARROTFISH.get(),
            HAAquaticItems.SHEEPSHEAD_WRASSE.get(),
            HAAquaticItems.RATFISH.get(),
            HAAquaticItems.BLUE_SPOTTED_STINGRAY.get(),
            HAAquaticItems.SPOTTED_EAGLE_RAY.get(),
            HAAquaticItems.LIONFISH.get(),
            HAAquaticItems.ROCKFISH.get(),
            HAAquaticItems.SEA_BASS.get(),
            HAAquaticItems.NEEDLEFISH.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.MEDIUM_FISH).add(item)
        }

        listOf(
            HAAquaticItems.OCEAN_SUNFISH.get(),
            HAAquaticItems.OARFISH.get(),
            HAAquaticItems.TUNA.get(),
            HAAquaticItems.MAHI.get(),
            HAAquaticItems.OPAH.get(),
            HAAquaticItems.GOLDEN_DORADO.get(),
            HAAquaticItems.COELACANTH.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.LARGE_FISH).add(item)
        }

        listOf(
            HAAquaticItems.COCONUT_CRAB_CLAW.get(),
            HAAquaticItems.DUNGENESS_CRAB_CLAW.get(),
            HAAquaticItems.FIDDLER_CRAB_CLAW.get(),
            HAAquaticItems.FLOWER_CRAB_CLAW.get(),
            HAAquaticItems.GHOST_CRAB_CLAW.get(),
            HAAquaticItems.LIGHTFOOT_CRAB_CLAW.get(),
            HAAquaticItems.SPIDER_CRAB_CLAW.get(),
            HAAquaticItems.VAMPIRE_CRAB_CLAW.get(),
            HAAquaticItems.YETI_CRAB_CLAW.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.CRAB_CLAW).add(item)
        }

        listOf(
            HAAquaticItems.ANGLERFISH.get(),
            HAAquaticItems.GOLDFISH.get(),
            HAAquaticItems.CARP.get(),
            HAAquaticItems.TROUT.get(),
            HAAquaticItems.SUNFISH.get(),
            HAAquaticItems.MACKEREL.get(),
            HAAquaticItems.HERRING.get(),
            HAAquaticItems.DAMSELFISH.get(),
            HAAquaticItems.SEA_BASS.get(),
            HAAquaticItems.PARROTFISH.get(),
            HAAquaticItems.SHEEPSHEAD_WRASSE.get(),
            HAAquaticItems.SEAHORSE.get(),
            HAAquaticItems.BARRELEYE.get(),
            HAAquaticItems.BETTA.get(),
            HAAquaticItems.BLUE_SPOTTED_STINGRAY.get(),
            HAAquaticItems.SPOTTED_EAGLE_RAY.get(),
            HAAquaticItems.TUNA.get(),
            HAAquaticItems.SURGEONFISH.get(),
            HAAquaticItems.PEARLFISH.get(),
            HAAquaticItems.SNAILFISH.get(),
            HAAquaticItems.CLOWNFISH.get(),
            HAAquaticItems.BOXFISH.get(),
            HAAquaticItems.STONEFISH.get(),
            HAAquaticItems.BLOWFISH.get(),
            HAAquaticItems.OARFISH.get(),
            HAAquaticItems.OCEAN_SUNFISH.get(),
            HAAquaticItems.DANIO.get(),
            HAAquaticItems.DISCUS.get(),
            HAAquaticItems.DRAGONFISH.get(),
            HAAquaticItems.FLASHLIGHT_FISH.get(),
            HAAquaticItems.FLYING_FISH.get(),
            HAAquaticItems.GOLDEN_DORADO.get(),
            HAAquaticItems.COELACANTH.get(),
            HAAquaticItems.SQUIRRELFISH.get(),
            HAAquaticItems.GOURAMI.get(),
            HAAquaticItems.LIONFISH.get(),
            HAAquaticItems.MAHI.get(),
            HAAquaticItems.MORAY_EEL.get(),
            HAAquaticItems.NEEDLEFISH.get(),
            HAAquaticItems.OPAH.get(),
            HAAquaticItems.OSCAR.get(),
            HAAquaticItems.PIRANHA.get(),
            HAAquaticItems.RATFISH.get(),
            HAAquaticItems.ROCKFISH.get(),
            HAAquaticItems.NEON_TETRA.get(),
            HAAquaticItems.TIGER_BARB.get(),
            HAAquaticItems.TRIGGERFISH.get(),
            HAAquaticItems.TREVALLY.get(),
            HAAquaticItems.JOHN_DORY.get(),
            HAAquaticItems.RAW_FISH_MEAT.get(),
            HAAquaticItems.RAW_FISH_STEAK.get(),
            HAAquaticItems.RAW_TENTACLE.get(),
            HAAquaticItems.RAW_CRAB.get(),
            HAAquaticItems.RAW_SHRIMP.get(),
            HAAquaticItems.RAW_LOBSTER.get(),
            HAAquaticItems.RAW_CRAYFISH.get(),
            HAAquaticItems.RAW_LOBSTER_TAIL.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.RAW_FISH).add(item)
            getOrCreateTagBuilder(HAItemTags.RAW_FISHES).add(item)
        }

        listOf(
            HAAquaticItems.COOKED_FISH_MEAT.get(),
            HAAquaticItems.COOKED_FISH_STEAK.get(),
            HAAquaticItems.COOKED_TENTACLE.get(),
            HAAquaticItems.COOKED_CRAB.get(),
            HAAquaticItems.COOKED_SHRIMP.get(),
            HAAquaticItems.COOKED_LOBSTER.get(),
            HAAquaticItems.COOKED_CRAYFISH.get(),
            HAAquaticItems.COOKED_LOBSTER_TAIL.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.COOKED_FISH).add(item)
            getOrCreateTagBuilder(HAItemTags.COOKED_FISHES).add(item)
        }

        listOf(
            HAAquaticItems.SARGASSUM.get(),
            HAAquaticItems.BULL_KELP.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.KELPS).add(item)
        }

        listOf(
            HAAquaticItems.LIONFISH.get(),
            HAAquaticItems.STONEFISH.get(),
            HAAquaticItems.BLOWFISH.get(),
            HAAquaticItems.BOXFISH.get(),
            HAAquaticItems.BLUE_SPOTTED_STINGRAY.get(),
            HAAquaticItems.SPOTTED_EAGLE_RAY.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.FOOD_POISONING).add(item)
        }

        listOf(
            HAAquaticItems.ANGLERFISH.get(),
            HAAquaticItems.BARRELEYE.get(),
            HAAquaticItems.CLOWNFISH.get(),
            HAAquaticItems.DRAGONFISH.get(),
            HAAquaticItems.FLASHLIGHT_FISH.get(),
            HAAquaticItems.ROCKFISH.get(),
            HAAquaticItems.SEA_BASS.get(),
            HAAquaticItems.SEAHORSE.get(),
            HAAquaticItems.MACKEREL.get(),
            HAAquaticItems.HERRING.get(),
            HAAquaticItems.BOXFISH.get(),
            HAAquaticItems.BLOWFISH.get(),
            HAAquaticItems.STONEFISH.get(),
            HAAquaticItems.PARROTFISH.get(),
            HAAquaticItems.SHEEPSHEAD_WRASSE.get(),
            HAAquaticItems.OCEAN_SUNFISH.get(),
            HAAquaticItems.CARP.get(),
            HAAquaticItems.TROUT.get(),
            HAAquaticItems.SUNFISH.get(),
            HAAquaticItems.GOLDFISH.get(),
            HAAquaticItems.OARFISH.get(),
            HAAquaticItems.TUNA.get(),
            HAAquaticItems.SURGEONFISH.get(),
            HAAquaticItems.TRIGGERFISH.get(),
            HAAquaticItems.TREVALLY.get(),
            HAAquaticItems.OSCAR.get(),
            HAAquaticItems.OPAH.get(),
            HAAquaticItems.MAHI.get(),
            HAAquaticItems.NEEDLEFISH.get(),
            HAAquaticItems.BLUE_SPOTTED_STINGRAY.get(),
            HAAquaticItems.SPOTTED_EAGLE_RAY.get(),
            HAAquaticItems.LIONFISH.get(),
            HAAquaticItems.MORAY_EEL.get(),
            HAAquaticItems.PIRANHA.get(),
            HAAquaticItems.NEON_TETRA.get(),
            HAAquaticItems.DANIO.get(),
            HAAquaticItems.GOURAMI.get(),
            HAAquaticItems.BETTA.get(),
            HAAquaticItems.DISCUS.get(),
            HAAquaticItems.RATFISH.get(),
            HAAquaticItems.FLYING_FISH.get(),
            HAAquaticItems.TIGER_BARB.get(),
            HAAquaticItems.JOHN_DORY.get(),
            HAAquaticItems.COELACANTH.get(),
            HAAquaticItems.SQUIRRELFISH.get(),
            HAAquaticItems.GOLDEN_DORADO.get(),
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
                getOrCreateTagBuilder(HAItemTags.PLUSHIES).add(item)
            }

        setOf(
            HAAquaticItems.MOON_JELLYFISH_HAT.get(),
            HAAquaticItems.MANGLERFISH_LURE.get(),
            HAAquaticItems.PINK_HATXOLOTL.get(),
            HAAquaticItems.CYAN_HATXOLOTL.get(),
            HAAquaticItems.BLUE_HATXOLOTL.get(),
            HAAquaticItems.BROWN_HATXOLOTL.get(),
            HybridAquaticItems.GOLD_HATXOLOTL.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.HAT).add(item)
        }

        setOf(
            HybridAquaticItems.EEL_SCARF.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.SCARF).add(item)
        }

        setOf(
            HybridAquaticItems.MANGLERFISH_FIN.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.BACK_FIN).add(item)
        }

        setOf(
            HybridAquaticItems.MANGLERFISH_LURE.get(),
            HybridAquaticItems.MANGLERFISH_FIN.get(),
            HybridAquaticItems.EEL_SCARF.get(),
            HybridAquaticItems.PINK_HATXOLOTL.get(),
            HybridAquaticItems.MOON_JELLYFISH_HAT.get(),
            HybridAquaticItems.NAUTILUS_HELMET.get(),
            HybridAquaticItems.NAUTILUS_PAULDRONS.get(),
            HybridAquaticItems.TURTLE_CHESTPLATE.get(),
            HybridAquaticItems.DIVING_HELMET.get(),
            HybridAquaticItems.DIVING_SUIT.get(),
            HybridAquaticItems.DIVING_LEGGINGS.get(),
            HybridAquaticItems.DIVING_BOOTS.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.ARMORS).add(item)
        }

        setOf(
            Items.NAUTILUS_SHELL,
            Items.SKELETON_SKULL,
            Items.WITHER_SKELETON_SKULL,
            Items.CREEPER_HEAD,
            Items.ZOMBIE_HEAD,
            Items.PIGLIN_HEAD,
            Items.DRAGON_HEAD,
            Items.PLAYER_HEAD,
            Items.BEEHIVE,
            Items.DECORATED_POT,
            Items.JUKEBOX,
            Items.NOTE_BLOCK,
            Items.REDSTONE_LAMP,
            Items.TNT,
            Items.TARGET,
            Items.LODESTONE,
            Items.BARREL,
            Items.CHEST,
            Items.ENDER_CHEST,
            Items.PUMPKIN,
            Items.CARVED_PUMPKIN,
            Items.JACK_O_LANTERN,
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.CRAB_WEARABLES).add(item)
        }

        setOf(
            Items.NOTE_BLOCK,
            Items.REDSTONE_LAMP,
            Items.TNT
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.REDSTONE_COMPONENTS).add(item)
        }
    }
}