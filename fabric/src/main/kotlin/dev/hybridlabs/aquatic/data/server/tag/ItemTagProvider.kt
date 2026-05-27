package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HAItems
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
            HAItems.BARBED_HOOK.get(),
            HAItems.GLOWING_HOOK.get(),
            HAItems.MAGNETIC_HOOK.get(),
            HAItems.CREEPERMAGNET_HOOK.get(),
            HAItems.OMINOUS_HOOK.get()
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
                HAItems.DIVING_HELMET.get(),
                HAItems.REINFORCED_DIVING_HELMET.get(),
                HAItems.GLOWING_DIVING_HELMET.get(),
            )
            .addOptional(ResourceLocation("create", "copper_diving_helmet"))
            .addOptional(ResourceLocation("create", "netherite_diving_helmet"))
            .addOptional(ResourceLocation("aquamirae", "three_bolt_helmet"))
            .addOptional(ResourceLocation("alexscaves", "diving_helmet"))

        getOrCreateTagBuilder(HAItemTags.DIVING_SUIT)
            .add(
                HAItems.DIVING_SUIT.get(),
                HAItems.REINFORCED_DIVING_SUIT.get(),
                HAItems.GLOWING_DIVING_SUIT.get(),
            )
            .addOptional(ResourceLocation("create", "copper_backtank"))
            .addOptional(ResourceLocation("create", "netherite_backtank"))
            .addOptional(ResourceLocation("aquamirae", "three_bolt_chestplate"))
            .addOptional(ResourceLocation("alexscaves", "diving_chestplate"))

        getOrCreateTagBuilder(HAItemTags.DIVING_LEGGINGS)
            .add(
                HAItems.DIVING_LEGGINGS.get(),
                HAItems.REINFORCED_DIVING_LEGGINGS.get(),
                HAItems.GLOWING_DIVING_LEGGINGS.get(),
            )
            .addOptional(ResourceLocation("aquamirae", "three_bolt_leggings"))
            .addOptional(ResourceLocation("alexscaves", "diving_leggings"))

        getOrCreateTagBuilder(HAItemTags.DIVING_BOOTS)
            .add(
                HAItems.DIVING_BOOTS.get(),
                HAItems.REINFORCED_DIVING_BOOTS.get(),
                HAItems.GLOWING_DIVING_BOOTS.get(),
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

                HAItems.REINFORCED_DIVING_HELMET.get(),
                HAItems.REINFORCED_DIVING_SUIT.get(),
                HAItems.REINFORCED_DIVING_LEGGINGS.get(),
                HAItems.REINFORCED_DIVING_BOOTS.get(),
            )
            .addOptional(ResourceLocation("create", "netherite_diving_helmet"))
            .addOptional(ResourceLocation("create", "netherite_backtank"))
            .addOptional(ResourceLocation("create", "netherite_diving_boots"))
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
            .add(HAItems.SHORESTONE.get())
            .add(HAItems.BARNACLE_SHORESTONE.get())
            .add(HAItems.CORALSTONE.get())

        getOrCreateTagBuilder(HAItemTags.SANDS)
            .add(HAItems.WHITE_SAND.get())
            .add(HAItems.GRASSY_SAND.get())

        //#region Tool Tags
        getOrCreateTagBuilder(HAItemTags.CORAL_SET)
            .add(HAItems.CORAL_BLADE.get())
            .add(HAItems.CORAL_PICKAXE.get())
            .add(HAItems.CORAL_AXE.get())
            .add(HAItems.CORAL_SHOVEL.get())
            .add(HAItems.CORAL_HOE.get())

        getOrCreateTagBuilder(HAItemTags.SEASHELL_SET)
            .add(HAItems.SEASHELL_SPEAR.get())
            .add(HAItems.SEASHELL_PICKAXE.get())
            .add(HAItems.SEASHELL_AXE.get())
            .add(HAItems.SEASHELL_SHOVEL.get())
            .add(HAItems.SEASHELL_HOE.get())

        getOrCreateTagBuilder(HAItemTags.TURTLE_SET)
            .add(HAItems.TURTLE_CHESTPLATE.get())
            .add(Items.TURTLE_HELMET)

        getOrCreateTagBuilder(ItemTags.SWORDS)
            .add(HAItems.SEASHELL_SPEAR.get())
            .add(HAItems.CORAL_BLADE.get())

        getOrCreateTagBuilder(ItemTags.SHOVELS)
            .add(HAItems.SEASHELL_SHOVEL.get())
            .add(HAItems.CORAL_SHOVEL.get())

        getOrCreateTagBuilder(ItemTags.AXES)
            .add(HAItems.SEASHELL_AXE.get())
            .add(HAItems.CORAL_AXE.get())

        getOrCreateTagBuilder(ItemTags.PICKAXES)
            .add(HAItems.SEASHELL_PICKAXE.get())
            .add(HAItems.CORAL_PICKAXE.get())

        getOrCreateTagBuilder(ItemTags.HOES)
            .add(HAItems.SEASHELL_HOE.get())
            .add(HAItems.CORAL_HOE.get())
        //#endregion

        listOf(
            HAItems.RAW_LOBSTER.get(),
            HAItems.COOKED_LOBSTER.get(),
            HAItems.RAW_LOBSTER_TAIL.get(),
            HAItems.COOKED_LOBSTER_TAIL.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.LOBSTER_MEAT).add(item)
        }

        listOf(
            HAItems.RAW_LOBSTER.get(),
            HAItems.COOKED_LOBSTER.get(),
            HAItems.RAW_LOBSTER_TAIL.get(),
            HAItems.COOKED_LOBSTER_TAIL.get(),
            HAItems.RAW_CRAB.get(),
            HAItems.COOKED_CRAB.get(),
            HAItems.RAW_SHRIMP.get(),
            HAItems.COOKED_SHRIMP.get(),
            HAItems.RAW_CRAYFISH.get(),
            HAItems.COOKED_CRAYFISH.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.CRUSTACEAN_MEAT).add(item)
        }

        listOf(
            HAItems.CARP.get(),
            HAItems.TROUT.get(),
            HAItems.SUNFISH.get(),
            HAItems.PLECO.get(),
            HAItems.DRAGONFISH.get(),
            HAItems.HAGFISH.get(),
            HAItems.PIRANHA.get(),
            HAItems.ANGLERFISH.get(),
            HAItems.BARRELEYE.get(),
            HAItems.SURGEONFISH.get(),
            HAItems.CLOWNFISH.get(),
            HAItems.DAMSELFISH.get(),
            HAItems.BOXFISH.get(),
            HAItems.TIGER_BARB.get(),
            HAItems.FLYING_FISH.get(),
            HAItems.SNAILFISH.get(),
            HAItems.PEARLFISH.get(),
            HAItems.SEAHORSE.get(),
            HAItems.OSCAR.get(),
            HAItems.FLASHLIGHT_FISH.get(),
            HAItems.SQUIRRELFISH.get(),
            HAItems.BLOWFISH.get(),
            HAItems.STONEFISH.get(),
            HAItems.DISCUS.get(),
            HAItems.GOURAMI.get(),
            HAItems.BETTA.get(),
            HAItems.DANIO.get(),
            HAItems.NEON_TETRA.get(),
            HAItems.MACKEREL.get(),
            HAItems.HERRING.get(),
            HAItems.JOHN_DORY.get(),
            HAItems.GOLDFISH.get(),
            Items.COD,
            Items.SALMON,
            Items.TROPICAL_FISH,
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.SMALL_FISH).add(item)
        }

        listOf(
            HAItems.MORAY_EEL.get(),
            HAItems.TRIGGERFISH.get(),
            HAItems.TREVALLY.get(),
            HAItems.PARROTFISH.get(),
            HAItems.SHEEPSHEAD_WRASSE.get(),
            HAItems.RATFISH.get(),
            HAItems.BLUE_SPOTTED_STINGRAY.get(),
            HAItems.SPOTTED_EAGLE_RAY.get(),
            HAItems.LIONFISH.get(),
            HAItems.ROCKFISH.get(),
            HAItems.SEA_BASS.get(),
            HAItems.NEEDLEFISH.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.MEDIUM_FISH).add(item)
        }

        listOf(
            HAItems.OCEAN_SUNFISH.get(),
            HAItems.OARFISH.get(),
            HAItems.TUNA.get(),
            HAItems.MAHI.get(),
            HAItems.OPAH.get(),
            HAItems.GOLDEN_DORADO.get(),
            HAItems.COELACANTH.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.LARGE_FISH).add(item)
        }

        listOf(
            HAItems.COCONUT_CRAB_CLAW.get(),
            HAItems.DUNGENESS_CRAB_CLAW.get(),
            HAItems.FIDDLER_CRAB_CLAW.get(),
            HAItems.FLOWER_CRAB_CLAW.get(),
            HAItems.GHOST_CRAB_CLAW.get(),
            HAItems.LIGHTFOOT_CRAB_CLAW.get(),
            HAItems.SPIDER_CRAB_CLAW.get(),
            HAItems.VAMPIRE_CRAB_CLAW.get(),
            HAItems.YETI_CRAB_CLAW.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.CRAB_CLAW).add(item)
        }

        listOf(
            HAItems.ANGLERFISH.get(),
            HAItems.CARP.get(),
            HAItems.TROUT.get(),
            HAItems.SUNFISH.get(),
            HAItems.MACKEREL.get(),
            HAItems.HERRING.get(),
            HAItems.DAMSELFISH.get(),
            HAItems.SEA_BASS.get(),
            HAItems.PARROTFISH.get(),
            HAItems.SHEEPSHEAD_WRASSE.get(),
            HAItems.SEAHORSE.get(),
            HAItems.BARRELEYE.get(),
            HAItems.BETTA.get(),
            HAItems.BLUE_SPOTTED_STINGRAY.get(),
            HAItems.SPOTTED_EAGLE_RAY.get(),
            HAItems.TUNA.get(),
            HAItems.SURGEONFISH.get(),
            HAItems.PEARLFISH.get(),
            HAItems.SNAILFISH.get(),
            HAItems.CLOWNFISH.get(),
            HAItems.BOXFISH.get(),
            HAItems.STONEFISH.get(),
            HAItems.BLOWFISH.get(),
            HAItems.OARFISH.get(),
            HAItems.OCEAN_SUNFISH.get(),
            HAItems.DANIO.get(),
            HAItems.DISCUS.get(),
            HAItems.DRAGONFISH.get(),
            HAItems.FLASHLIGHT_FISH.get(),
            HAItems.FLYING_FISH.get(),
            HAItems.GOLDEN_DORADO.get(),
            HAItems.COELACANTH.get(),
            HAItems.SQUIRRELFISH.get(),
            HAItems.GOURAMI.get(),
            HAItems.LIONFISH.get(),
            HAItems.MAHI.get(),
            HAItems.MORAY_EEL.get(),
            HAItems.NEEDLEFISH.get(),
            HAItems.OPAH.get(),
            HAItems.OSCAR.get(),
            HAItems.PIRANHA.get(),
            HAItems.RATFISH.get(),
            HAItems.ROCKFISH.get(),
            HAItems.NEON_TETRA.get(),
            HAItems.TIGER_BARB.get(),
            HAItems.TRIGGERFISH.get(),
            HAItems.TREVALLY.get(),
            HAItems.JOHN_DORY.get(),
            HAItems.GOLDFISH.get(),
            HAItems.RAW_FISH_MEAT.get(),
            HAItems.RAW_FISH_STEAK.get(),
            HAItems.RAW_TENTACLE.get(),
            HAItems.RAW_CRAB.get(),
            HAItems.RAW_SHRIMP.get(),
            HAItems.RAW_LOBSTER.get(),
            HAItems.RAW_CRAYFISH.get(),
            HAItems.RAW_LOBSTER_TAIL.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.RAW_FISH).add(item)
            getOrCreateTagBuilder(HAItemTags.RAW_FISHES).add(item)
        }

        listOf(
            HAItems.COOKED_FISH_MEAT.get(),
            HAItems.COOKED_FISH_STEAK.get(),
            HAItems.COOKED_TENTACLE.get(),
            HAItems.COOKED_CRAB.get(),
            HAItems.COOKED_SHRIMP.get(),
            HAItems.COOKED_LOBSTER.get(),
            HAItems.COOKED_CRAYFISH.get(),
            HAItems.COOKED_LOBSTER_TAIL.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.COOKED_FISH).add(item)
            getOrCreateTagBuilder(HAItemTags.COOKED_FISHES).add(item)
        }

        listOf(
            HAItems.SARGASSUM.get(),
            HAItems.BULL_KELP.get(),
            HAItems.DELESSERIA.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.KELPS).add(item)
        }

        listOf(
            HAItems.LIONFISH.get(),
            HAItems.STONEFISH.get(),
            HAItems.BLOWFISH.get(),
            HAItems.BOXFISH.get(),
            HAItems.HAGFISH.get(),
            HAItems.BLUE_SPOTTED_STINGRAY.get(),
            HAItems.SPOTTED_EAGLE_RAY.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.FOOD_POISONING).add(item)
        }

        listOf(
            HAItems.ANGLERFISH.get(),
            HAItems.BARRELEYE.get(),
            HAItems.CLOWNFISH.get(),
            HAItems.DRAGONFISH.get(),
            HAItems.HAGFISH.get(),
            HAItems.FLASHLIGHT_FISH.get(),
            HAItems.ROCKFISH.get(),
            HAItems.SEA_BASS.get(),
            HAItems.SEAHORSE.get(),
            HAItems.MACKEREL.get(),
            HAItems.HERRING.get(),
            HAItems.BOXFISH.get(),
            HAItems.BLOWFISH.get(),
            HAItems.STONEFISH.get(),
            HAItems.PARROTFISH.get(),
            HAItems.SHEEPSHEAD_WRASSE.get(),
            HAItems.OCEAN_SUNFISH.get(),
            HAItems.CARP.get(),
            HAItems.TROUT.get(),
            HAItems.SUNFISH.get(),
            HAItems.OARFISH.get(),
            HAItems.TUNA.get(),
            HAItems.SURGEONFISH.get(),
            HAItems.TRIGGERFISH.get(),
            HAItems.TREVALLY.get(),
            HAItems.OSCAR.get(),
            HAItems.OPAH.get(),
            HAItems.MAHI.get(),
            HAItems.NEEDLEFISH.get(),
            HAItems.BLUE_SPOTTED_STINGRAY.get(),
            HAItems.SPOTTED_EAGLE_RAY.get(),
            HAItems.LIONFISH.get(),
            HAItems.MORAY_EEL.get(),
            HAItems.PIRANHA.get(),
            HAItems.NEON_TETRA.get(),
            HAItems.DANIO.get(),
            HAItems.GOURAMI.get(),
            HAItems.BETTA.get(),
            HAItems.DISCUS.get(),
            HAItems.RATFISH.get(),
            HAItems.FLYING_FISH.get(),
            HAItems.TIGER_BARB.get(),
            HAItems.JOHN_DORY.get(),
            HAItems.GOLDFISH.get(),
            HAItems.COELACANTH.get(),
            HAItems.SQUIRRELFISH.get(),
            HAItems.GOLDEN_DORADO.get(),
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
            HAItems.MOON_JELLYFISH_HAT.get(),
            HAItems.MANGLERFISH_LURE.get(),
            HAItems.PINK_HATXOLOTL.get(),
            HAItems.CYAN_HATXOLOTL.get(),
            HAItems.BLUE_HATXOLOTL.get(),
            HAItems.BROWN_HATXOLOTL.get(),
            HAItems.GOLD_HATXOLOTL.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.HAT).add(item)
        }

        setOf(
            HAItems.EEL_SCARF.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.SCARF).add(item)
        }

        setOf(
            HAItems.MANGLERFISH_FIN.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.BACK_FIN).add(item)
        }

        setOf(
            HAItems.MANGLERFISH_LURE.get(),
            HAItems.MANGLERFISH_FIN.get(),
            HAItems.EEL_SCARF.get(),
            HAItems.PINK_HATXOLOTL.get(),
            HAItems.MOON_JELLYFISH_HAT.get(),
            HAItems.NAUTILUS_HELMET.get(),
            HAItems.NAUTILUS_PAULDRONS.get(),
            HAItems.TURTLE_CHESTPLATE.get(),
            HAItems.DIVING_HELMET.get(),
            HAItems.DIVING_SUIT.get(),
            HAItems.DIVING_LEGGINGS.get(),
            HAItems.DIVING_BOOTS.get(),
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.ARMORS).add(item)
        }

        getOrCreateTagBuilder(HAItemTags.CRAB_WEARABLES)
            .addOptionalTag(ItemTags.WOOL.location())
            .addOptionalTag(ResourceLocation("c", "skulls"))
            .addOptionalTag(ResourceLocation("c", "bookshelves"))
            .addOptionalTag(ResourceLocation("c", "barrels"))
            .addOptionalTag(ResourceLocation("c", "chests"))
            .addOptionalTag(ResourceLocation("c", "pumpkins"))
            .addOptionalTag(ResourceLocation("c", "shulker_boxes"))
            .add(
                Items.NAUTILUS_SHELL,
                Items.SKELETON_SKULL,
                Items.WITHER_SKELETON_SKULL,
                Items.CREEPER_HEAD,
                Items.ZOMBIE_HEAD,
                Items.PIGLIN_HEAD,
                Items.DRAGON_HEAD,
                Items.PLAYER_HEAD,
                Items.BEEHIVE,
                Items.BEE_NEST,
                Items.BOOKSHELF,
                Items.DECORATED_POT,
                Items.JUKEBOX,
                Items.NOTE_BLOCK,
                Items.REDSTONE_LAMP,
                Items.TNT,
                Items.TARGET,
                Items.LODESTONE,
                Items.BARREL,
                Items.CHEST,
                Items.TRAPPED_CHEST,
                Items.END_PORTAL_FRAME,
                Items.ENDER_CHEST,
                Items.OCHRE_FROGLIGHT,
                Items.VERDANT_FROGLIGHT,
                Items.PEARLESCENT_FROGLIGHT,
                Items.SEA_LANTERN,
                Items.SHROOMLIGHT,
                Items.GLOWSTONE,
                Items.PUMPKIN,
                Items.CARVED_PUMPKIN,
                Items.JACK_O_LANTERN,
                Items.SHULKER_BOX,
                Items.RED_SHULKER_BOX,
                Items.BLUE_SHULKER_BOX,
                Items.BLACK_SHULKER_BOX,
                Items.BROWN_SHULKER_BOX,
                Items.CYAN_SHULKER_BOX,
                Items.GRAY_SHULKER_BOX,
                Items.GREEN_SHULKER_BOX,
                Items.LIGHT_BLUE_SHULKER_BOX,
                Items.LIGHT_GRAY_SHULKER_BOX,
                Items.LIME_SHULKER_BOX,
                Items.MAGENTA_SHULKER_BOX,
                Items.ORANGE_SHULKER_BOX,
                Items.PINK_SHULKER_BOX,
                Items.PURPLE_SHULKER_BOX,
                Items.WHITE_SHULKER_BOX,
                Items.YELLOW_SHULKER_BOX,
            )

        setOf(
            Items.NOTE_BLOCK,
            Items.REDSTONE_LAMP,
            Items.TNT
        ).forEach { item ->
            getOrCreateTagBuilder(HAItemTags.REDSTONE_COMPONENTS).add(item)
        }
    }
}