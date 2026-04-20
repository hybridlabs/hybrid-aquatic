@file:Suppress("unused", "SameParameterValue")

package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.item.coral.*
import dev.hybridlabs.aquatic.item.seashell.*
import dev.hybridlabs.aquatic.platform.Services.*
import dev.hybridlabs.aquatic.tag.HAInstrumentTags
import net.minecraft.core.Direction
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.*
import net.minecraft.world.level.block.Block
import java.util.function.Supplier

object HAItems {
    //#region Armor
    //#region Diving Armor
    val DIVING_HELMET = register(
        "diving_helmet"
    ) {
        DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.HELMET,
            Item.Properties().stacksTo(1)
        )
    }

    val DIVING_SUIT = register(
        "diving_suit"
    ) {
        DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.CHESTPLATE, Item.Properties().stacksTo(1)
        )
    }

    val DIVING_LEGGINGS = register(
        "diving_leggings"
    ) {
        DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.LEGGINGS, Item.Properties().stacksTo(1)
        )
    }

    val DIVING_BOOTS = register(
        "diving_boots"
    ) {
        DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.BOOTS, Item.Properties().stacksTo(1)
        )
    }
    //#endregion

    //#region Reinforced Diving Armor
    val REINFORCED_DIVING_HELMET = register(
        "reinforced_diving_helmet"
    ) {
        REINFORCED_DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.HELMET,
            Item.Properties().stacksTo(1)
        )
    }

    val REINFORCED_DIVING_SUIT = register(
        "reinforced_diving_suit"
    ) {
        REINFORCED_DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.CHESTPLATE, Item.Properties().stacksTo(1)
        )
    }

    val REINFORCED_DIVING_LEGGINGS = register(
        "reinforced_diving_leggings"
    ) {
        REINFORCED_DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.LEGGINGS, Item.Properties().stacksTo(1)
        )
    }

    val REINFORCED_DIVING_BOOTS = register(
        "reinforced_diving_boots"
    ) {
        REINFORCED_DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.BOOTS, Item.Properties().stacksTo(1)
        )
    }
    //#endregion

    //#region Glowing Diving Armor
    val GLOWING_DIVING_HELMET = register(
        "glowing_diving_helmet"
    ) {
        GLOWING_DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.HELMET,
            Item.Properties().stacksTo(1)
        )
    }

    val GLOWING_DIVING_SUIT = register(
        "glowing_diving_suit"
    ) {
        GLOWING_DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.CHESTPLATE, Item.Properties().stacksTo(1)
        )
    }

    val GLOWING_DIVING_LEGGINGS = register(
        "glowing_diving_leggings"
    ) {
        GLOWING_DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.LEGGINGS, Item.Properties().stacksTo(1)
        )
    }

    val GLOWING_DIVING_BOOTS = register(
        "glowing_diving_boots"
    ) {
        GLOWING_DIVING_ARMOR_FACTORY.create(
            ArmorItem.Type.BOOTS, Item.Properties().stacksTo(1)
        )
    }
    //#endregion

    //#region Nautilus Armor
    val NAUTILUS_HELMET = register(
        "nautilus_helmet"
    ) {
        SEASHELL_ARMOR_FACTORY.create(
            ArmorItem.Type.HELMET, Item.Properties().stacksTo(1)
        )
    }

    val NAUTILUS_PAULDRONS = register(
        "nautilus_pauldrons"
    ) {
        SEASHELL_ARMOR_FACTORY.create(
            ArmorItem.Type.CHESTPLATE, Item.Properties().stacksTo(1)
        )
    }
    //#endregion

    //#region Turtle Armor
    val TURTLE_CHESTPLATE = register(
        "turtle_chestplate"
    ) {
        TURTLE_ARMOR_FACTORY.create(
            ArmorItem.Type.CHESTPLATE, Item.Properties().stacksTo(1)
        )
    }
    //#endregion

    //#region Cosmetics
    val MANGLERFISH_LURE = register(
        "manglerfish_lure"
    ) {
        MANGLERFISH_COSMETIC_PROVIDER.create(
            Item.Properties().stacksTo(1)
        )
    }

    val MANGLERFISH_FIN = register(
        "manglerfish_fin"
    ) {
        MANGLERFISH_COSMETIC_PROVIDER.create(
            Item.Properties().stacksTo(1)
        )
    }

    val EEL_SCARF = register(
        "eel_scarf"
    ) {
        EEL_ARMOR_FACTORY.create(
            Item.Properties().stacksTo(1)
        )
    }

    val PINK_HATXOLOTL = register(
        "pink_hatxolotl"
    ) {
        PINK_HATXOLOTL_ARMOR_FACTORY.create(
            ArmorItem.Type.HELMET,
            Item.Properties().stacksTo(1)
        )
    }

    val BROWN_HATXOLOTL = register(
        "brown_hatxolotl"
    ) {
        BROWN_HATXOLOTL_ARMOR_FACTORY.create(
            ArmorItem.Type.HELMET,
            Item.Properties().stacksTo(1)
        )
    }

    val GOLD_HATXOLOTL = register(
        "gold_hatxolotl"
    ) {
        GOLD_HATXOLOTL_ARMOR_FACTORY.create(
            ArmorItem.Type.HELMET,
            Item.Properties().stacksTo(1)
        )
    }

    val BLUE_HATXOLOTL = register(
        "blue_hatxolotl"
    ) {
        BLUE_HATXOLOTL_ARMOR_FACTORY.create(
            ArmorItem.Type.HELMET,
            Item.Properties().stacksTo(1)
        )
    }

    val CYAN_HATXOLOTL = register(
        "cyan_hatxolotl"
    ) {
        CYAN_HATXOLOTL_ARMOR_FACTORY.create(
            ArmorItem.Type.HELMET,
            Item.Properties().stacksTo(1)
        )
    }

    val MOON_JELLYFISH_HAT = register(
        "moon_jellyfish_hat"
    ) {
        MOON_JELLYFISH_ARMOR_FACTORY.create(
            Item.Properties().stacksTo(1)
        )
    }
    //#endregion
    //#endregion

    //#region Tools - Weapons - Hooks
    val SEA_MESSAGE_BOOK = register("sea_message_book") { SeaMessageBookItem(Item.Properties()) }
    val FISHING_NET = register("fishing_net") { FishingNetItem(Item.Properties().stacksTo(1)) }
    val DIVING_WEIGHT = register("diving_weight") { DivingWeightItem(Item.Properties()) }
    val KARKINOS_CLAW = register("karkinos_claw") { KarkinosClawItem(Item.Properties().stacksTo(1)) }
    val OMINOUS_CONCH =
        register("ominous_conch") { OminousConchItem(Item.Properties().stacksTo(1), HAInstrumentTags.OMINOUS_CONCH) }
    val ARGONAUT = register("argonaut") { ArgonautItem(Item.Properties().stacksTo(1)) }

    val BARBED_HOOK = register("barbed_hook") { HookItem(Item.Properties().durability(16)) }
    val GLOWING_HOOK = register("glowing_hook") { HookItem(Item.Properties().durability(16)) }
    val MAGNETIC_HOOK = register("magnetic_hook") { HookItem(Item.Properties().durability(8)) }
    val CREEPERMAGNET_HOOK = register("creepermagnet_hook") { HookItem(Item.Properties().durability(1)) }
    val OMINOUS_HOOK = register("ominous_hook") { HookItem(Item.Properties().durability(1)) }

    //#region Seashell Set
    val SEASHELL_SPEAR = register(
        "seashell_spear"
    ) { SeashellSpearItem(Item.Properties()) }

    val SEASHELL_PICKAXE = register(
        "seashell_pickaxe"
    ) { SeashellPickaxeItem(Item.Properties()) }

    val SEASHELL_AXE = register(
        "seashell_axe"
    ) { SeashellAxeItem(Item.Properties()) }

    val SEASHELL_SHOVEL = register(
        "seashell_shovel"
    ) { SeashellShovelItem(Item.Properties()) }

    val SEASHELL_HOE = register(
        "seashell_hoe"
    ) {
        SeashellHoeItem(Item.Properties())
    }
    //#endregion

    //#region Coral Set
    val CORAL_BLADE = register(
        "coral_blade"
    ) { CoralBladeItem(Item.Properties()) }

    val CORAL_PICKAXE = register(
        "coral_pickaxe"
    ) { CoralPickaxeItem(Item.Properties()) }

    val CORAL_AXE = register(
        "coral_axe"
    ) { CoralAxeItem(Item.Properties()) }

    val CORAL_SHOVEL = register(
        "coral_shovel"
    ) { CoralShovelItem(Item.Properties()) }

    val CORAL_HOE = register(
        "coral_hoe"
    ) { CoralHoeItem(Item.Properties()) }
    //#endregion
    //#endregion

    //#region Blocks

    //#region Nature Blocks

    val ANEMONE = register("anemone") {
        PLATFORM.createBlockItem(HABlocks.ANEMONE.get(), Item.Properties())
    }

    val STRAWBERRY_ANEMONE = register("strawberry_anemone") {
        PLATFORM.createBlockItem(HABlocks.STRAWBERRY_ANEMONE.get(), Item.Properties())
    }
    val GIANT_GREEN_ANEMONE = register("giant_green_anemone") {
        PLATFORM.createBlockItem(HABlocks.GIANT_GREEN_ANEMONE.get(), Item.Properties())
    }

    val GIANT_CLAM = registerBlockItem("giant_clam") { HABlocks.GIANT_CLAM.get() }
    val OYSTER_BLOCK = registerBlockItem("oyster_block") { HABlocks.OYSTER.get() }
    val SARGASSUM = registerBlockItem("sargassum") { HABlocks.SARGASSUM.get() }
    val BULL_KELP = registerBlockItem("bull_kelp") { HABlocks.BULL_KELP.get() }
    val FLOATING_SARGASSUM =
        registerPlaceableInWaterBlockItem("floating_sargassum") { HABlocks.FLOATING_SARGASSUM.get() }
    val WATER_LETTUCE = registerPlaceableInWaterBlockItem("water_lettuce") { HABlocks.WATER_LETTUCE.get() }
    val WATER_HYACINTH =
        registerPlaceableInWaterBlockItem("water_hyacinth") { HABlocks.WATER_HYACINTH.get() }
    val JUNGLE_LILY_PAD =
        registerPlaceableInWaterBlockItem("jungle_lily_pad") { HABlocks.JUNGLE_LILY_PAD.get() }
    val SHORT_RED_ALGAE = registerBlockItem("short_red_algae") { HABlocks.SHORT_RED_ALGAE.get() }
    val RED_ALGAE = registerBlockItem("red_algae") { HABlocks.RED_ALGAE.get() }
    val SEA_LETTUCE = registerBlockItem("sea_lettuce") { HABlocks.SEA_LETTUCE.get() }
    val HYDROTHERMAL_VENT = registerBlockItem("hydrothermal_vent") { HABlocks.THERMAL_VENT.get() }
    val TUBE_WORM = registerBlockItem("tube_worm") { HABlocks.TUBE_WORM.get() }
    val BONE_WORMS = registerBlockItem("bone_worms") { HABlocks.BONE_WORMS.get() }
    val TUBE_SPONGE = registerBlockItem("tube_sponge") { HABlocks.TUBE_SPONGE.get() }
    val GLASS_SPONGE = registerBlockItem("glass_sponge") { HABlocks.GLASS_SPONGE.get() }
    val HARP_SPONGE = registerBlockItem("harp_sponge") { HABlocks.HARP_SPONGE.get() }

    //#region Coral Blocks
    val BUTTON_CORAL_BLOCK = registerBlockItem("button_coral_block") { HABlocks.BUTTON_CORAL_BLOCK.get() }
    val DEAD_BUTTON_CORAL_BLOCK =
        registerBlockItem("dead_button_coral_block") { HABlocks.DEAD_BUTTON_CORAL_BLOCK.get() }
    val BUTTON_CORAL = registerBlockItem("button_coral") { HABlocks.BUTTON_CORAL.get() }
    val DEAD_BUTTON_CORAL = registerBlockItem("dead_button_coral") { HABlocks.DEAD_BUTTON_CORAL.get() }
    val BUTTON_CORAL_FAN = registerVerticallyAttachable(
        "button_coral_fan",
        HABlocks.BUTTON_CORAL_FAN,
        HABlocks.BUTTON_CORAL_WALL_FAN
    )
    val DEAD_BUTTON_CORAL_FAN = registerVerticallyAttachable(
        "dead_button_coral_fan",
        HABlocks.DEAD_BUTTON_CORAL_FAN,
        HABlocks.DEAD_BUTTON_CORAL_WALL_FAN
    )

    val BLEACHED_BUTTON_CORAL_BLOCK =
        registerBlockItem("bleached_button_coral_block") { HABlocks.BLEACHED_BUTTON_CORAL_BLOCK.get() }
    val BLEACHED_BUTTON_CORAL =
        registerBlockItem("bleached_button_coral") { HABlocks.BLEACHED_BUTTON_CORAL.get() }
    val BLEACHED_BUTTON_CORAL_FAN = registerVerticallyAttachable(
        "bleached_button_coral_fan",
        HABlocks.BLEACHED_BUTTON_CORAL_FAN,
        HABlocks.BLEACHED_BUTTON_CORAL_WALL_FAN
    )

    val SUN_CORAL_BLOCK = registerBlockItem("sun_coral_block") { HABlocks.SUN_CORAL_BLOCK.get() }
    val DEAD_SUN_CORAL_BLOCK =
        registerBlockItem("dead_sun_coral_block") { HABlocks.DEAD_SUN_CORAL_BLOCK.get() }
    val SUN_CORAL = registerBlockItem("sun_coral") { HABlocks.SUN_CORAL.get() }
    val DEAD_SUN_CORAL = registerBlockItem("dead_sun_coral") { HABlocks.DEAD_SUN_CORAL.get() }
    val SUN_CORAL_FAN = registerVerticallyAttachable(
        "sun_coral_fan",
        HABlocks.SUN_CORAL_FAN,
        HABlocks.SUN_CORAL_WALL_FAN
    )
    val DEAD_SUN_CORAL_FAN = registerVerticallyAttachable(
        "dead_sun_coral_fan",
        HABlocks.DEAD_SUN_CORAL_FAN,
        HABlocks.DEAD_SUN_CORAL_WALL_FAN
    )

    val BLEACHED_SUN_CORAL_BLOCK =
        registerBlockItem("bleached_sun_coral_block") { HABlocks.BLEACHED_SUN_CORAL_BLOCK.get() }
    val BLEACHED_SUN_CORAL =
        registerBlockItem("bleached_sun_coral") { HABlocks.BLEACHED_SUN_CORAL.get() }
    val BLEACHED_SUN_CORAL_FAN = registerVerticallyAttachable(
        "bleached_sun_coral_fan",
        HABlocks.BLEACHED_SUN_CORAL_FAN,
        HABlocks.BLEACHED_SUN_CORAL_WALL_FAN
    )

    val LOPHELIA_CORAL_BLOCK =
        registerBlockItem("lophelia_coral_block") { HABlocks.LOPHELIA_CORAL_BLOCK.get() }
    val DEAD_LOPHELIA_CORAL_BLOCK =
        registerBlockItem("dead_lophelia_coral_block") { HABlocks.DEAD_LOPHELIA_CORAL_BLOCK.get() }
    val BLEACHED_LOPHELIA_CORAL_BLOCK =
        registerBlockItem("bleached_lophelia_coral_block") { HABlocks.BLEACHED_LOPHELIA_CORAL_BLOCK.get() }
    val LOPHELIA_CORAL = registerBlockItem("lophelia_coral") { HABlocks.LOPHELIA_CORAL.get() }
    val DEAD_LOPHELIA_CORAL = registerBlockItem("dead_lophelia_coral") { HABlocks.DEAD_LOPHELIA_CORAL.get() }
    val BLEACHED_LOPHELIA_CORAL =
        registerBlockItem("bleached_lophelia_coral") { HABlocks.BLEACHED_LOPHELIA_CORAL.get() }
    val LOPHELIA_CORAL_FAN = registerVerticallyAttachable(
        "lophelia_coral_fan",
        HABlocks.LOPHELIA_CORAL_FAN,
        HABlocks.LOPHELIA_CORAL_WALL_FAN
    )
    val DEAD_LOPHELIA_CORAL_FAN = registerVerticallyAttachable(
        "dead_lophelia_coral_fan",
        HABlocks.DEAD_LOPHELIA_CORAL_FAN,
        HABlocks.DEAD_LOPHELIA_CORAL_WALL_FAN
    )

    val BLEACHED_LOPHELIA_CORAL_FAN = registerVerticallyAttachable(
        "bleached_lophelia_coral_fan",
        HABlocks.BLEACHED_LOPHELIA_CORAL_FAN,
        HABlocks.BLEACHED_LOPHELIA_CORAL_WALL_FAN
    )

    val ROSE_CORAL_BLOCK = registerBlockItem("rose_coral_block") { HABlocks.ROSE_CORAL_BLOCK.get() }
    val DEAD_ROSE_CORAL_BLOCK =
        registerBlockItem("dead_rose_coral_block") { HABlocks.DEAD_ROSE_CORAL_BLOCK.get() }
    val BLEACHED_ROSE_CORAL_BLOCK =
        registerBlockItem("bleached_rose_coral_block") { HABlocks.BLEACHED_ROSE_CORAL_BLOCK.get() }
    val ROSE_CORAL = registerBlockItem("rose_coral") { HABlocks.ROSE_CORAL.get() }
    val DEAD_ROSE_CORAL = registerBlockItem("dead_rose_coral") { HABlocks.DEAD_ROSE_CORAL.get() }
    val BLEACHED_ROSE_CORAL = registerBlockItem("bleached_rose_coral") { HABlocks.BLEACHED_ROSE_CORAL.get() }
    val ROSE_CORAL_FAN = registerVerticallyAttachable(
        "rose_coral_fan",
        HABlocks.ROSE_CORAL_FAN,
        HABlocks.ROSE_CORAL_WALL_FAN
    )
    val DEAD_ROSE_CORAL_FAN = registerVerticallyAttachable(
        "dead_rose_coral_fan",
        HABlocks.DEAD_ROSE_CORAL_FAN,
        HABlocks.DEAD_ROSE_CORAL_WALL_FAN
    )
    val BLEACHED_ROSE_CORAL_FAN = registerVerticallyAttachable(
        "bleached_rose_coral_fan",
        HABlocks.BLEACHED_ROSE_CORAL_FAN,
        HABlocks.BLEACHED_ROSE_CORAL_WALL_FAN
    )

    val LEAF_CORAL_BLOCK =
        registerBlockItem("leaf_coral_block") { HABlocks.LEAF_CORAL_BLOCK.get() }
    val DEAD_LEAF_CORAL_BLOCK =
        registerBlockItem("dead_leaf_coral_block") { HABlocks.DEAD_LEAF_CORAL_BLOCK.get() }
    val LEAF_CORAL = registerBlockItem("leaf_coral") { HABlocks.LEAF_CORAL.get() }
    val DEAD_LEAF_CORAL = registerBlockItem("dead_leaf_coral") { HABlocks.DEAD_LEAF_CORAL.get() }
    val LEAF_CORAL_FAN = registerVerticallyAttachable(
        "leaf_coral_fan",
        HABlocks.LEAF_CORAL_FAN,
        HABlocks.LEAF_CORAL_WALL_FAN
    )
    val DEAD_LEAF_CORAL_FAN = registerVerticallyAttachable(
        "dead_leaf_coral_fan",
        HABlocks.DEAD_LEAF_CORAL_FAN,
        HABlocks.DEAD_LEAF_CORAL_WALL_FAN
    )

    val BLEACHED_LEAF_CORAL_BLOCK =
        registerBlockItem("bleached_leaf_coral_block") { HABlocks.BLEACHED_LEAF_CORAL_BLOCK.get() }
    val BLEACHED_LEAF_CORAL =
        registerBlockItem("bleached_leaf_coral") { HABlocks.BLEACHED_LEAF_CORAL.get() }
    val BLEACHED_LEAF_CORAL_FAN = registerVerticallyAttachable(
        "bleached_leaf_coral_fan",
        HABlocks.BLEACHED_LEAF_CORAL_FAN,
        HABlocks.BLEACHED_LEAF_CORAL_WALL_FAN
    )

    val THORN_CORAL_BLOCK = registerBlockItem("thorn_coral_block") { HABlocks.THORN_CORAL_BLOCK.get() }
    val DEAD_THORN_CORAL_BLOCK =
        registerBlockItem("dead_thorn_coral_block") { HABlocks.DEAD_THORN_CORAL_BLOCK.get() }
    val THORN_CORAL = registerBlockItem("thorn_coral") { HABlocks.THORN_CORAL.get() }
    val DEAD_THORN_CORAL = registerBlockItem("dead_thorn_coral") { HABlocks.DEAD_THORN_CORAL.get() }
    val THORN_CORAL_FAN = registerVerticallyAttachable(
        "thorn_coral_fan",
        HABlocks.THORN_CORAL_FAN,
        HABlocks.THORN_CORAL_WALL_FAN
    )
    val DEAD_THORN_CORAL_FAN = registerVerticallyAttachable(
        "dead_thorn_coral_fan",
        HABlocks.DEAD_THORN_CORAL_FAN,
        HABlocks.DEAD_THORN_CORAL_WALL_FAN
    )

    val BLEACHED_THORN_CORAL_BLOCK =
        registerBlockItem("bleached_thorn_coral_block") { HABlocks.BLEACHED_THORN_CORAL_BLOCK.get() }
    val BLEACHED_THORN_CORAL =
        registerBlockItem("bleached_thorn_coral") { HABlocks.BLEACHED_THORN_CORAL.get() }
    val BLEACHED_THORN_CORAL_FAN = registerVerticallyAttachable(
        "bleached_thorn_coral_fan",
        HABlocks.BLEACHED_THORN_CORAL_FAN,
        HABlocks.BLEACHED_THORN_CORAL_WALL_FAN
    )

    val BLEACHED_FIRE_CORAL_BLOCK =
        registerBlockItem("bleached_fire_coral_block") { HABlocks.BLEACHED_FIRE_CORAL_BLOCK.get() }
    val BLEACHED_FIRE_CORAL = registerBlockItem("bleached_fire_coral") { HABlocks.BLEACHED_FIRE_CORAL.get() }
    val BLEACHED_FIRE_CORAL_FAN = registerVerticallyAttachable(
        "bleached_fire_coral_fan",
        HABlocks.BLEACHED_FIRE_CORAL_FAN,
        HABlocks.BLEACHED_FIRE_CORAL_WALL_FAN
    )

    val BLEACHED_TUBE_CORAL_BLOCK =
        registerBlockItem("bleached_tube_coral_block") { HABlocks.BLEACHED_TUBE_CORAL_BLOCK.get() }
    val BLEACHED_TUBE_CORAL = registerBlockItem("bleached_tube_coral") { HABlocks.BLEACHED_TUBE_CORAL.get() }
    val BLEACHED_TUBE_CORAL_FAN = registerVerticallyAttachable(
        "bleached_tube_coral_fan",
        HABlocks.BLEACHED_TUBE_CORAL_FAN,
        HABlocks.BLEACHED_TUBE_CORAL_WALL_FAN
    )

    val BLEACHED_HORN_CORAL_BLOCK =
        registerBlockItem("bleached_horn_coral_block") { HABlocks.BLEACHED_HORN_CORAL_BLOCK.get() }
    val BLEACHED_HORN_CORAL = registerBlockItem("bleached_horn_coral") { HABlocks.BLEACHED_HORN_CORAL.get() }
    val BLEACHED_HORN_CORAL_FAN = registerVerticallyAttachable(
        "bleached_horn_coral_fan",
        HABlocks.BLEACHED_HORN_CORAL_FAN,
        HABlocks.BLEACHED_HORN_CORAL_WALL_FAN
    )

    val BLEACHED_BUBBLE_CORAL_BLOCK =
        registerBlockItem("bleached_bubble_coral_block") { HABlocks.BLEACHED_BUBBLE_CORAL_BLOCK.get() }
    val BLEACHED_BUBBLE_CORAL =
        registerBlockItem("bleached_bubble_coral") { HABlocks.BLEACHED_BUBBLE_CORAL.get() }
    val BLEACHED_BUBBLE_CORAL_FAN = registerVerticallyAttachable(
        "bleached_bubble_coral_fan",
        HABlocks.BLEACHED_BUBBLE_CORAL_FAN,
        HABlocks.BLEACHED_BUBBLE_CORAL_WALL_FAN
    )

    val BLEACHED_BRAIN_CORAL_BLOCK =
        registerBlockItem("bleached_brain_coral_block") { HABlocks.BLEACHED_BRAIN_CORAL_BLOCK.get() }
    val BLEACHED_BRAIN_CORAL =
        registerBlockItem("bleached_brain_coral") { HABlocks.BLEACHED_BRAIN_CORAL.get() }
    val BLEACHED_BRAIN_CORAL_FAN = registerVerticallyAttachable(
        "bleached_brain_coral_fan",
        HABlocks.BLEACHED_BRAIN_CORAL_FAN,
        HABlocks.BLEACHED_BRAIN_CORAL_WALL_FAN
    )
    //#endregion

    //#endregion

    //#region Artificial Blocks
    val GLOWSLIME_BLOCK = registerBlockItem("glowslime_block") { HABlocks.GLOWSLIME_BLOCK.get() }
    val PEARL_BLOCK = registerBlockItem("pearl_block") { HABlocks.PEARL_BLOCK.get() }
    val BLACK_PEARL_BLOCK = registerBlockItem("black_pearl_block") { HABlocks.BLACK_PEARL_BLOCK.get() }
    val CRYSTALLINE_SULFUR = registerBlockItem("crystalline_sulfur") { HABlocks.CRYSTALLINE_SULFUR.get() }
    val DEPTH_CHARGE = registerPlaceableInWaterOrLandBlockItem("depth_charge") { HABlocks.DEPTH_CHARGE.get() }
    val BUOY = registerPlaceableInWaterBlockItem("buoy") { HABlocks.BUOY.get() }
    val BELL_BUOY = registerPlaceableInWaterBlockItem("bell_buoy") { HABlocks.BELL_BUOY.get() }
    val RAFT = registerPlaceableInWaterBlockItem("raft") { HABlocks.RAFT.get() }
    val OAK_RAFT = registerPlaceableInWaterBlockItem("oak_raft") { HABlocks.OAK_RAFT.get() }
    val SPRUCE_RAFT = registerPlaceableInWaterBlockItem("spruce_raft") { HABlocks.SPRUCE_RAFT.get() }
    val DARK_OAK_RAFT = registerPlaceableInWaterBlockItem("dark_oak_raft") { HABlocks.DARK_OAK_RAFT.get() }
    val BIRCH_RAFT = registerPlaceableInWaterBlockItem("birch_raft") { HABlocks.BIRCH_RAFT.get() }
    val ACACIA_RAFT = registerPlaceableInWaterBlockItem("acacia_raft") { HABlocks.ACACIA_RAFT.get() }
    val JUNGLE_RAFT = registerPlaceableInWaterBlockItem("jungle_raft") { HABlocks.JUNGLE_RAFT.get() }
    val MANGROVE_RAFT = registerPlaceableInWaterBlockItem("mangrove_raft") { HABlocks.MANGROVE_RAFT.get() }
    val CHERRY_RAFT = registerPlaceableInWaterBlockItem("cherry_raft") { HABlocks.CHERRY_RAFT.get() }
    val DRIFTWOOD_RAFT = registerPlaceableInWaterBlockItem("driftwood_raft") { HABlocks.DRIFTWOOD_RAFT.get() }
    val GLOWSTICK = registerVerticallyAttachable("glowstick", HABlocks.GLOWSTICK, HABlocks.WALL_GLOWSTICK)
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle") { PLATFORM.createMessageInABottleItem(Item.Properties()) }
    //#endregion

    //#region Nature Blocks
    val GRASSY_SAND = registerBlockItem("grassy_sand") { HABlocks.GRASSY_SAND.get() }
    val AERATED_SAND = registerBlockItem("aerated_sand") { HABlocks.AERATED_SAND.get() }
    val BUBBLE_GEYSER = registerBlockItem("bubble_geyser") { HABlocks.BUBBLE_GEYSER.get() }

    val WHITE_SAND = registerBlockItem("white_sand") { HABlocks.WHITE_SAND.get() }
    val CHISELED_WHITE_SANDSTONE = registerBlockItem("chiseled_white_sandstone") { HABlocks.CHISELED_WHITE_SANDSTONE.get() }
    val WHITE_SANDSTONE = registerBlockItem("white_sandstone") { HABlocks.WHITE_SANDSTONE.get() }
    val WHITE_SANDSTONE_STAIRS = register("white_sandstone_stairs") { BlockItem(HABlocks.WHITE_SANDSTONE_STAIRS.get(), Item.Properties()) }
    val WHITE_SANDSTONE_SLAB = register("white_sandstone_slab") { BlockItem(HABlocks.WHITE_SANDSTONE_SLAB.get(), Item.Properties()) }
    val WHITE_SANDSTONE_WALL = register("white_sandstone_wall") { BlockItem(HABlocks.WHITE_SANDSTONE_WALL.get(), Item.Properties()) }
    val SMOOTH_WHITE_SANDSTONE = registerBlockItem("smooth_white_sandstone") { HABlocks.SMOOTH_WHITE_SANDSTONE.get() }
    val SMOOTH_WHITE_SANDSTONE_STAIRS = register("smooth_white_sandstone_stairs") { BlockItem(HABlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.get(), Item.Properties()) }
    val SMOOTH_WHITE_SANDSTONE_SLAB = register("smooth_white_sandstone_slab") { BlockItem(HABlocks.SMOOTH_WHITE_SANDSTONE_SLAB.get(), Item.Properties()) }
    val CUT_WHITE_SANDSTONE = registerBlockItem("cut_white_sandstone") { HABlocks.CUT_WHITE_SANDSTONE.get() }
    val CUT_WHITE_SANDSTONE_SLAB = register("cut_white_sandstone_slab") { BlockItem(HABlocks.CUT_WHITE_SANDSTONE_SLAB.get(), Item.Properties()) }

    val BONE_STAIRS = register("bone_stairs") { BlockItem(HABlocks.BONE_STAIRS.get(), Item.Properties()) }
    val BONE_SLAB = register("bone_slab") { BlockItem(HABlocks.BONE_SLAB.get(), Item.Properties()) }
    val BONE_WALL = register("bone_wall") { BlockItem(HABlocks.BONE_WALL.get(), Item.Properties()) }
    val BONE_FENCE = register("bone_fence") { BlockItem(HABlocks.BONE_FENCE.get(), Item.Properties()) }

    val SUSPICIOUS_RED_SAND = registerBlockItem("suspicious_red_sand") { HABlocks.SUSPICIOUS_RED_SAND.get() }
    val CORALSTONE = registerBlockItem("coralstone") { HABlocks.CORALSTONE.get() }
    val SHORESTONE = registerBlockItem("shorestone") { HABlocks.SHORESTONE.get() }
    val BARNACLE_SHORESTONE = registerBlockItem("barnacle_shorestone") { HABlocks.BARNACLE_SHORESTONE.get() }
    val MARINE_SNOW = registerBlockItem("marine_snow") { HABlocks.MARINE_SNOW.get() }
    //#endregion

    //#region Plushies
    val BASKING_SHARK_PLUSHIE =
        registerBlockItem("basking_shark_plushie") { HABlocks.BASKING_SHARK_PLUSHIE.get() }
    val BULL_SHARK_PLUSHIE = registerBlockItem("bull_shark_plushie") { HABlocks.BULL_SHARK_PLUSHIE.get() }
    val FRILLED_SHARK_PLUSHIE =
        registerBlockItem("frilled_shark_plushie") { HABlocks.FRILLED_SHARK_PLUSHIE.get() }
    val GREAT_WHITE_SHARK_PLUSHIE =
        registerBlockItem("great_white_shark_plushie") { HABlocks.GREAT_WHITE_SHARK_PLUSHIE.get() }
    val HAMMERHEAD_SHARK_PLUSHIE =
        registerBlockItem("hammerhead_shark_plushie") { HABlocks.HAMMERHEAD_SHARK_PLUSHIE.get() }
    val THRESHER_SHARK_PLUSHIE =
        registerBlockItem("thresher_shark_plushie") { HABlocks.THRESHER_SHARK_PLUSHIE.get() }
    val TIGER_SHARK_PLUSHIE = registerBlockItem("tiger_shark_plushie") { HABlocks.TIGER_SHARK_PLUSHIE.get() }
    val WHALE_SHARK_PLUSHIE = registerBlockItem("whale_shark_plushie") { HABlocks.WHALE_SHARK_PLUSHIE.get() }
    //#endregion

    //#region Crates
    val CRAB_POT = registerBlockItem("crab_pot") { HABlocks.CRAB_POT.get() }
    val HYBRID_CRATE = registerBlockItem("hybrid_crate") { HABlocks.HYBRID_CRATE.get() }
    val OAK_CRATE = registerBlockItem("oak_crate") { HABlocks.OAK_CRATE.get() }
    val SPRUCE_CRATE = registerBlockItem("spruce_crate") { HABlocks.SPRUCE_CRATE.get() }
    val BIRCH_CRATE = registerBlockItem("birch_crate") { HABlocks.BIRCH_CRATE.get() }
    val DARK_OAK_CRATE = registerBlockItem("dark_oak_crate") { HABlocks.DARK_OAK_CRATE.get() }
    val JUNGLE_CRATE = registerBlockItem("jungle_crate") { HABlocks.JUNGLE_CRATE.get() }
    val ACACIA_CRATE = registerBlockItem("acacia_crate") { HABlocks.ACACIA_CRATE.get() }
    val MANGROVE_CRATE = registerBlockItem("mangrove_crate") { HABlocks.MANGROVE_CRATE.get() }
    val CHERRY_CRATE = registerBlockItem("cherry_crate") { HABlocks.CHERRY_CRATE.get() }
    val BAMBOO_CRATE = registerBlockItem("bamboo_crate") { HABlocks.BAMBOO_CRATE.get() }
    //#endregion

    //#endregion

    //#region Crafting Ingredients
    val GLOWSLIME = register("glowslime") { Item(Item.Properties()) }
    val SEA_URCHIN_SPINE = register("sea_urchin_spine") { Item(Item.Properties()) }
    val SHARK_TOOTH = register("shark_tooth") { Item(Item.Properties()) }
    val SULFUR = register("sulfur") { Item(Item.Properties()) }
    val CORAL_CHUNK = register("coral_chunk") { Item(Item.Properties()) }
    val PRISMARINE_ROD = register("prismarine_rod") { Item(Item.Properties()) }
    val DIVING_ARMOR_UPGRADE_TEMPLATE = register("diving_armor_upgrade_template") { Item(Item.Properties()) }
    val PEARL = register("pearl") { Item(Item.Properties()) }
    val BLACK_PEARL = register("black_pearl") { Item(Item.Properties()) }
    val GIANT_NAUTILUS_SHELL = register("giant_nautilus_shell") { Item(Item.Properties()) }
    val CUTTLEBONE = register("cuttlebone") { Item(Item.Properties()) }
    val FISH_FOOD = register("fish_food") { Item(Item.Properties()) }

    //#region Claws
    val LOBSTER_CLAW = register("lobster_claw") { Item(Item.Properties()) }
    val DUNGENESS_CRAB_CLAW = register("dungeness_crab_claw") { Item(Item.Properties()) }
    val FIDDLER_CRAB_CLAW = register("fiddler_crab_claw") { Item(Item.Properties()) }
    val VAMPIRE_CRAB_CLAW = register("vampire_crab_claw") { Item(Item.Properties()) }
    val FLOWER_CRAB_CLAW = register("flower_crab_claw") { Item(Item.Properties()) }
    val GHOST_CRAB_CLAW = register("ghost_crab_claw") { Item(Item.Properties()) }
    val SPIDER_CRAB_CLAW = register("spider_crab_claw") { Item(Item.Properties()) }
    val COCONUT_CRAB_CLAW = register("coconut_crab_claw") { Item(Item.Properties()) }
    val YETI_CRAB_CLAW = register("yeti_crab_claw") { Item(Item.Properties()) }
    val LIGHTFOOT_CRAB_CLAW = register("lightfoot_crab_claw") { Item(Item.Properties()) }
    //#endregion

    //#endregion

    //#region Food
    val UNI = register("uni") {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val RAW_SHRIMP = register(
        "raw_shrimp"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val COOKED_SHRIMP = register(
        "cooked_shrimp"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationMod(0.5F)
                        .meat()
                        .build()
                )
        )
    }

    val COOKED_CLAM = register(
        "cooked_clam"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationMod(0.5F)
                        .meat()
                        .build()
                )
        )
    }

    val CLAM = register(
        "clam"
    ) {
        BlockItem(
            HABlocks.CLAMS.get(),
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationMod(0.5F)
                        .meat()
                        .build()
                )
        )
    }

    val RAW_CRAYFISH = register(
        "raw_crayfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val COOKED_CRAYFISH = register(
        "cooked_crayfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationMod(0.5F)
                        .meat()
                        .build()
                )
        )
    }

    val RAW_CRAB = register(
        "raw_crab"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val COOKED_CRAB = register(
        "cooked_crab"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationMod(0.5F)
                        .meat()
                        .build()
                )
        )
    }

    val RAW_LOBSTER = register(
        "raw_lobster"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val COOKED_LOBSTER = register(
        "cooked_lobster"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(5)
                        .saturationMod(0.6F)
                        .meat()
                        .build()
                )
        )
    }

    val RAW_LOBSTER_TAIL = register(
        "raw_lobster_tail"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val COOKED_LOBSTER_TAIL = register(
        "cooked_lobster_tail"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(5)
                        .saturationMod(0.6F)
                        .meat()
                        .build()
                )
        )
    }

    val COOKED_FISH_STEAK = register(
        "cooked_fish_steak"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(8)
                        .saturationMod(0.8F)
                        .meat()
                        .build()
                )
        )
    }

    val RAW_FISH_STEAK = register(
        "raw_fish_steak"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationMod(0.6F)
                        .meat()
                        .build()
                )
        )
    }

    val COOKED_FISH_MEAT = register(
        "cooked_fish_meat"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationMod(0.6F)
                        .meat()
                        .build()
                )
        )
    }

    val RAW_FISH_MEAT = register(
        "raw_fish_meat"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val RAW_TENTACLE = register(
        "raw_tentacle"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val COOKED_TENTACLE = register(
        "cooked_tentacle"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationMod(0.6F)
                        .meat()
                        .build()
                )
        )
    }

    val MACKEREL = register(
        "mackerel"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val HERRING = register(
        "herring"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val FLYING_FISH = register(
        "flying_fish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val PIRANHA = register(
        "piranha"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val ANGLERFISH = register(
        "anglerfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val BARRELEYE = register(
        "barreleye"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val DRAGONFISH = register(
        "dragonfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val SURGEONFISH = register(
        "surgeonfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val CLOWNFISH = register(
        "clownfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val JOHN_DORY = register(
        "john_dory"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val DAMSELFISH = register(
        "damselfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder().nutrition(1).saturationMod(0.2F).meat().build()
                )
        )
    }

    val FLASHLIGHT_FISH = register(
        "flashlight_fish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val SQUIRRELFISH = register(
        "squirrelfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val GOURAMI = register(
        "gourami"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val BETTA = register(
        "betta"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val PEARLFISH = register(
        "pearlfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val SNAILFISH = register(
        "snailfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val DISCUS = register(
        "discus"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val DANIO = register(
        "danio"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val NEON_TETRA = register(
        "neon_tetra"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val TIGER_BARB = register(
        "tiger_barb"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val OSCAR = register(
        "oscar"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val BOXFISH = register(
        "boxfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .effect(MobEffectInstance(MobEffects.POISON, 1200, 2), 1.0f)
                        .meat()
                        .build()
                )
        )
    }

    val CARP = register(
        "carp"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val SUNFISH = register(
        "sunfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val TROUT = register(
        "trout"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val PLECO = register(
        "pleco"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val SEAHORSE = register(
        "seahorse"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.2F)
                        .meat()
                        .build()
                )
        )
    }

    val BLOWFISH = register(
        "blowfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.4F)
                        .effect(MobEffectInstance(MobEffects.POISON, 1200, 1), 1.0f)
                        .meat()
                        .build()
                )
        )
    }

    val STONEFISH = register(
        "stonefish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.4F)
                        .effect(MobEffectInstance(MobEffects.POISON, 1200, 1), 1.0f)
                        .meat()
                        .build()
                )
        )
    }

    val LIONFISH = register(
        "lionfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .effect(MobEffectInstance(MobEffects.POISON, 1200, 0), 1.0f)
                        .meat()
                        .build()
                )
        )
    }

    val ROCKFISH = register(
        "rockfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val SEA_BASS = register(
        "sea_bass"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val BLUE_SPOTTED_STINGRAY = register(
        "blue_spotted_stingray"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .effect(MobEffectInstance(MobEffects.POISON, 1200, 0), 1.0f)
                        .meat()
                        .build()
                )
        )
    }

    val SPOTTED_EAGLE_RAY = register(
        "spotted_eagle_ray"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .effect(MobEffectInstance(MobEffects.POISON, 1200, 0), 1.0f)
                        .meat()
                        .build()
                )
        )
    }

    val MORAY_EEL = register(
        "moray_eel"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val NEEDLEFISH = register(
        "needlefish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val RATFISH = register(
        "ratfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val TRIGGERFISH = register(
        "triggerfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val TREVALLY = register(
        "trevally"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val PARROTFISH = register(
        "parrotfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val SHEEPSHEAD_WRASSE = register(
        "sheepshead_wrasse"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val COELACANTH = register(
        "coelacanth"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val GOLDEN_DORADO = register(
        "golden_dorado"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.4F)
                        .meat()
                        .build()
                )
        )
    }

    val MAHI = register(
        "mahi"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationMod(0.8F)
                        .meat()
                        .build()
                )
        )
    }

    val TUNA = register(
        "tuna"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationMod(0.8F)
                        .meat()
                        .build()
                )
        )
    }

    val OPAH = register(
        "opah"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationMod(0.8F)
                        .meat()
                        .build()
                )
        )
    }

    val OARFISH = register(
        "oarfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationMod(0.8F)
                        .meat()
                        .build()
                )
        )
    }

    val OCEAN_SUNFISH = register(
        "ocean_sunfish"
    ) {
        Item(
            Item.Properties()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationMod(0.8F)
                        .meat()
                        .build()
                )
        )
    }

    //#endregion

    //#region Spawn Eggs
    //#region Fish Spawn Eggs
    val AFRICAN_BUTTERFLYFISH_SPAWN_EGG =
        registerSpawnEgg(
            "african_butterflyfish_spawn_egg",
            HAEntityTypes.AFRICAN_BUTTERFLYFISH,
            0xb57955,
            0x1e3555
        )
    val BARRELEYE_SPAWN_EGG =
        registerSpawnEgg("barreleye_spawn_egg", HAEntityTypes.BARRELEYE, 0x4b4343, 0x6bc96c)
    val BETTA_SPAWN_EGG =
        registerSpawnEgg("betta_spawn_egg", HAEntityTypes.BETTA, 0xcc425e, 0x504198)
    val DAMSELFISH_SPAWN_EGG =
        registerSpawnEgg("damselfish_spawn_egg", HAEntityTypes.DAMSELFISH, 0x96aba5, 0xf9d100)
    val CARP_SPAWN_EGG =
        registerSpawnEgg("carp_spawn_egg", HAEntityTypes.CARP, 0x725234, 0xd3ad8c)
    val TROUT_SPAWN_EGG =
        registerSpawnEgg("trout_spawn_egg", HAEntityTypes.TROUT, 0xc6bd88, 0xe07d5a)
    val SUNFISH_SPAWN_EGG =
        registerSpawnEgg("sunfish_spawn_egg", HAEntityTypes.SUNFISH, 0x667749, 0xd38257)
    val CLOWNFISH_SPAWN_EGG =
        registerSpawnEgg("clownfish_spawn_egg", HAEntityTypes.CLOWNFISH, 0xff9166, 0xfdf7f9)
    val GARDEN_EEL_SPAWN_EGG =
        registerSpawnEgg("garden_eel_spawn_egg", HAEntityTypes.GARDEN_EEL, 0xffd14e, 0xececd4)
    val BOXFISH_SPAWN_EGG =
        registerSpawnEgg("boxfish_spawn_egg", HAEntityTypes.BOXFISH, 0xfffeac, 0xffc056)
    val DANIO_SPAWN_EGG =
        registerSpawnEgg("danio_spawn_egg", HAEntityTypes.DANIO, 0xdcdced, 0x2a3f52)
    val DISCUS_SPAWN_EGG =
        registerSpawnEgg("discus_spawn_egg", HAEntityTypes.DISCUS, 0xeeeecd, 0xf4a957)
    val CORYDORA_SPAWN_EGG =
        registerSpawnEgg("corydora_spawn_egg", HAEntityTypes.CORYDORA, 0x996059, 0x6e6b65)
    val FLASHLIGHT_FISH_SPAWN_EGG =
        registerSpawnEgg("flashlight_fish_spawn_egg", HAEntityTypes.FLASHLIGHT_FISH, 0x5c433e, 0xfffaa9)
    val SQUIRRELFISH_FISH_SPAWN_EGG =
        registerSpawnEgg("squirrelfish_spawn_egg", HAEntityTypes.SQUIRRELFISH, 0x9b3f3d, 0xcfa184)
    val PEARLFISH_FISH_SPAWN_EGG =
        registerSpawnEgg("pearlfish_spawn_egg", HAEntityTypes.PEARLFISH, 0x464c59, 0xc7cfd3)
    val FLYING_FISH_SPAWN_EGG =
        registerSpawnEgg("flying_fish_spawn_egg", HAEntityTypes.FLYING_FISH, 0x7c93e1, 0xfbf7e6)
    val GOURAMI_SPAWN_EGG =
        registerSpawnEgg("gourami_spawn_egg", HAEntityTypes.GOURAMI, 0x7bb6cf, 0x722a37)
    val PLECO_SPAWN_EGG =
        registerSpawnEgg("pleco_spawn_egg", HAEntityTypes.PLECO, 0x3f3d28, 0xa4a39a)
    val SHINER_SPAWN_EGG =
        registerSpawnEgg("shiner_spawn_egg", HAEntityTypes.SHINER, 0xc6c484, 0xd18952)
    val LIONFISH_SPAWN_EGG =
        registerSpawnEgg("lionfish_spawn_egg", HAEntityTypes.LIONFISH, 0xf9e6cf, 0xc64524)
    val MACKEREL_SPAWN_EGG =
        registerSpawnEgg("mackerel_spawn_egg", HAEntityTypes.MACKEREL, 0x395562, 0xfff09c)
    val HERRING_SPAWN_EGG =
        registerSpawnEgg("herring_spawn_egg", HAEntityTypes.HERRING, 0xcfd8d4, 0x5f6e75)
    val MAHI_SPAWN_EGG =
        registerSpawnEgg("mahi_spawn_egg", HAEntityTypes.MAHI, 0x528c4e, 0xfffd69)
    val MANTA_RAY_SPAWN_EGG =
        registerSpawnEgg("manta_ray_spawn_egg", HAEntityTypes.MANTA_RAY, 0x000000, 0xFFFFFF)
    val MORAY_EEL_SPAWN_EGG =
        registerSpawnEgg("moray_eel_spawn_egg", HAEntityTypes.MORAY_EEL, 0x8da163, 0x1d4435)
    val NEEDLEFISH_SPAWN_EGG =
        registerSpawnEgg("needlefish_spawn_egg", HAEntityTypes.NEEDLEFISH, 0xc0e4f7, 0x537da8)
    val BARRACUDA_SPAWN_EGG =
        registerSpawnEgg("barracuda_spawn_egg", HAEntityTypes.BARRACUDA, 0x64b2c6, 0x3d4d64)
    val OPAH_SPAWN_EGG =
        registerSpawnEgg("opah_spawn_egg", HAEntityTypes.OPAH, 0x6472a7, 0xea6262)
    val OSCAR_SPAWN_EGG =
        registerSpawnEgg("oscar_spawn_egg", HAEntityTypes.OSCAR, 0xd5c97e, 0x836136)
    val PARROTFISH_SPAWN_EGG =
        registerSpawnEgg("parrotfish_spawn_egg", HAEntityTypes.PARROTFISH, 0x728e6b, 0xe5c5c3)
    val PIRANHA_SPAWN_EGG =
        registerSpawnEgg("piranha_spawn_egg", HAEntityTypes.PIRANHA, 0x535f92, 0xaf3b3d)
    val ROCKFISH_SPAWN_EGG =
        registerSpawnEgg("rockfish_spawn_egg", HAEntityTypes.ROCKFISH, 0x711b2f, 0xeb5948)
    val SEA_BASS_SPAWN_EGG =
        registerSpawnEgg("sea_bass_spawn_egg", HAEntityTypes.SEA_BASS, 0x323337, 0xe7e8e8)
    val SEAHORSE_SPAWN_EGG =
        registerSpawnEgg("seahorse_spawn_egg", HAEntityTypes.SEAHORSE, 0xffc9ab, 0xe63f5e)
    val SEADRAGON_SPAWN_EGG =
        registerSpawnEgg("seadragon_spawn_egg", HAEntityTypes.SEADRAGON, 0xffc9ab, 0xe63f5e)
    val STINGRAY_SPAWN_EGG =
        registerSpawnEgg("stingray_spawn_egg", HAEntityTypes.STINGRAY, 0xffa214, 0x0069aa)
    val STONEFISH_SPAWN_EGG =
        registerSpawnEgg("stonefish_spawn_egg", HAEntityTypes.STONEFISH, 0xaf8b68, 0x574435)
    val OCEAN_SUNFISH_SPAWN_EGG =
        registerSpawnEgg("ocean_sunfish_spawn_egg", HAEntityTypes.OCEAN_SUNFISH, 0x687f96, 0x455764)
    val SURGEONFISH_SPAWN_EGG =
        registerSpawnEgg("surgeonfish_spawn_egg", HAEntityTypes.SURGEONFISH, 0x88a1d7, 0x211b2f)
    val TETRA_SPAWN_EGG =
        registerSpawnEgg("tetra_spawn_egg", HAEntityTypes.TETRA, 0x4eb1cc, 0xe64d43)
    val PUPFISH_SPAWN_EGG =
        registerSpawnEgg("pupfish_spawn_egg", HAEntityTypes.PUPFISH, 0x020c44, 0x2785f5)
    val TIGER_BARB_SPAWN_EGG =
        registerSpawnEgg("tiger_barb_spawn_egg", HAEntityTypes.TIGER_BARB, 0xfbbf2d, 0x611851)
    val BLOWFISH_SPAWN_EGG =
        registerSpawnEgg("blowfish_spawn_egg", HAEntityTypes.BLOWFISH, 0xfcf2ce, 0x885e6d)
    val TRIGGERFISH_SPAWN_EGG =
        registerSpawnEgg("triggerfish_spawn_egg", HAEntityTypes.TRIGGERFISH, 0x5b7c7e, 0xbdcdda)
    val TREVALLY_SPAWN_EGG =
        registerSpawnEgg("trevally_spawn_egg", HAEntityTypes.TREVALLY, 0xe3bb5f, 0x575644)
    val TUNA_SPAWN_EGG =
        registerSpawnEgg("tuna_spawn_egg", HAEntityTypes.TUNA, 0x36668d, 0xf5d58d)
    val GOLDEN_DORADO_SPAWN_EGG =
        registerSpawnEgg("golden_dorado_spawn_egg", HAEntityTypes.GOLDEN_DORADO, 0xd16020, 0xa4975f)
    val WRASSE_SPAWN_EGG =
        registerSpawnEgg("wrasse_spawn_egg", HAEntityTypes.WRASSE, 0x2c2628, 0xdc5f5d)
    val ANGLERFISH_SPAWN_EGG =
        registerSpawnEgg("anglerfish_spawn_egg", HAEntityTypes.ANGLERFISH, 0x4b4257, 0xa7f1eb)
    val VIPERFISH_SPAWN_EGG =
        registerSpawnEgg("viperfish_spawn_egg", HAEntityTypes.VIPERFISH, 0x65727e, 0x65727e)
    val HATCHETFISH_SPAWN_EGG =
        registerSpawnEgg("hatchetfish_spawn_egg", HAEntityTypes.HATCHETFISH, 0x5e718e, 0x4c7597)
    val TRIPOD_FISH_SPAWN_EGG =
        registerSpawnEgg("tripod_fish_spawn_egg", HAEntityTypes.TRIPOD_FISH, 0x4c7597, 0xafeeee)
    val FANGTOOTH_SPAWN_EGG =
        registerSpawnEgg("fangtooth_spawn_egg", HAEntityTypes.FANGTOOTH, 0xab691b, 0xf4d29c)
    val COELACANTH_SPAWN_EGG =
        registerSpawnEgg("coelacanth_spawn_egg", HAEntityTypes.COELACANTH, 0x2f517a, 0xbac4d3)
    val SLICKHEAD_SPAWN_EGG =
        registerSpawnEgg("slickhead_spawn_egg", HAEntityTypes.SLICKHEAD, 0x4e3a35, 0x5a9aa5)
    val DRAGONFISH_SPAWN_EGG =
        registerSpawnEgg("dragonfish_spawn_egg", HAEntityTypes.DRAGONFISH, 0x2e2e33, 0xfffaa9)
    val JOHN_DORY_SPAWN_EGG =
        registerSpawnEgg("john_dory_spawn_egg", HAEntityTypes.JOHN_DORY, 0xdcc6c6, 0x8a7f55)
    val SNAILFISH_SPAWN_EGG =
        registerSpawnEgg("snailfish_spawn_egg", HAEntityTypes.SNAILFISH, 0xe0c2ed, 0xf0dcef)
    val OARFISH_SPAWN_EGG =
        registerSpawnEgg("oarfish_spawn_egg", HAEntityTypes.OARFISH, 0x8892ab, 0xb04743)
    val RATFISH_SPAWN_EGG =
        registerSpawnEgg("ratfish_spawn_egg", HAEntityTypes.RATFISH, 0xa16470, 0x673146)
    //#endregion

    //#region Shark Spawn Eggs
    val BASKING_SHARK_SPAWN_EGG =
        registerSpawnEgg("basking_shark_spawn_egg", HAEntityTypes.BASKING_SHARK, 0x6a6558, 0xb5b3a6)
    val BULL_SHARK_SPAWN_EGG =
        registerSpawnEgg("bull_shark_spawn_egg", HAEntityTypes.BULL_SHARK, 0x5d6b7a, 0xb4c1c6)
    val FRILLED_SHARK_SPAWN_EGG =
        registerSpawnEgg("frilled_shark_spawn_egg", HAEntityTypes.FRILLED_SHARK, 0x5a4d50, 0x3a2f31)
    val SIXGILL_SHARK_SPAWN_EGG =
        registerSpawnEgg("sixgill_shark_spawn_egg", HAEntityTypes.SIXGILL_SHARK, 0x6e666a, 0xc5d4d0)
    val SLEEPER_SHARK_SPAWN_EGG =
        registerSpawnEgg("sleeper_shark_spawn_egg", HAEntityTypes.SLEEPER_SHARK, 0x222830, 0x709ae0)
    val LANTERN_SHARK_SPAWN_EGG =
        registerSpawnEgg("lantern_shark_spawn_egg", HAEntityTypes.LANTERN_SHARK, 0x543f46, 0x84d5fe)
    val GREAT_WHITE_SHARK_SPAWN_EGG =
        registerSpawnEgg("great_white_shark_spawn_egg", HAEntityTypes.GREAT_WHITE_SHARK, 0x5e6e7d, 0xf3f3f8)
    val HAMMERHEAD_SHARK_SPAWN_EGG =
        registerSpawnEgg("hammerhead_shark_spawn_egg", HAEntityTypes.HAMMERHEAD_SHARK, 0x78909a, 0xd7e1dd)
    val HOUND_SHARK_SHARK_SPAWN_EGG =
        registerSpawnEgg("hound_shark_spawn_egg", HAEntityTypes.HOUND_SHARK, 0xa18469, 0x5e453a)
    val THRESHER_SHARK_SPAWN_EGG =
        registerSpawnEgg("thresher_shark_spawn_egg", HAEntityTypes.THRESHER_SHARK, 0x5591af, 0xd7e1dd)
    val TIGER_SHARK_SPAWN_EGG =
        registerSpawnEgg("sand_tiger_shark_spawn_egg", HAEntityTypes.SAND_TIGER_SHARK, 0xb79167, 0xf0f3e6)
    val WHALE_SHARK_SPAWN_EGG =
        registerSpawnEgg("whale_shark_spawn_egg", HAEntityTypes.WHALE_SHARK, 0x4c6d98, 0xeff0f4)
    //#endregion

    //#region Cephalopod Spawn Eggs
    val CUTTLEFISH_SPAWN_EGG =
        registerSpawnEgg("cuttlefish_spawn_egg", HAEntityTypes.CUTTLEFISH, 0x8a4836, 0xf6deae)
    val ARROW_SQUID_SPAWN_EGG =
        registerSpawnEgg("arrow_squid_spawn_egg", HAEntityTypes.ARROW_SQUID, 0x761f31, 0xd56360)
    val COLOSSAL_SQUID_SPAWN_EGG =
        registerSpawnEgg("colossal_squid_spawn_egg", HAEntityTypes.COLOSSAL_SQUID, 0x9c0a31, 0x9c0a31)
    val GIANT_SQUID_SPAWN_EGG =
        registerSpawnEgg("giant_squid_spawn_egg", HAEntityTypes.GIANT_SQUID, 0xab0f32, 0xf6b2ab)
    val FIREFLY_SQUID_SPAWN_EGG =
        registerSpawnEgg("firefly_squid_spawn_egg", HAEntityTypes.FIREFLY_SQUID, 0xc93a61, 0x4ec0e8)
    val OCTOPUS_SPAWN_EGG =
        registerSpawnEgg("octopus_spawn_egg", HAEntityTypes.OCTOPUS, 0x73275c, 0xc34e69)
    val NAUTILUS_SPAWN_EGG =
        registerSpawnEgg("nautilus_spawn_egg", HAEntityTypes.NAUTILUS, 0xd4ccc3, 0xae4635)
    val UMBRELLA_OCTOPUS_SPAWN_EGG =
        registerSpawnEgg("umbrella_octopus_spawn_egg", HAEntityTypes.UMBRELLA_OCTOPUS, 0xffaf25, 0xfeff92)
    val VAMPIRE_SQUID_SPAWN_EGG =
        registerSpawnEgg("vampire_squid_spawn_egg", HAEntityTypes.VAMPIRE_SQUID, 0x73363c, 0xc3e9e2)
    //#endregion

    //#region Jellyfish Spawn Eggs
    val BARREL_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("barrel_jellyfish_spawn_egg", HAEntityTypes.BARREL_JELLYFISH, 0xd6f3ea, 0x413c83)
    val BLUE_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("blue_jellyfish_spawn_egg", HAEntityTypes.BLUE_JELLYFISH, 0x4dc0e8, 0xff6b97)
    val CEPHEIDAE_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg(
            "cepheidae_jellyfish_spawn_egg",
            HAEntityTypes.CEPHEIDAE_JELLYFISH,
            0x623062,
            0x89a1d8
        )
    val LIONS_MANE_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg(
            "lions_mane_jellyfish_spawn_egg",
            HAEntityTypes.LIONS_MANE_JELLYFISH,
            0xf6d5b1,
            0x541e48
        )
    val MOON_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("moon_jellyfish_spawn_egg", HAEntityTypes.MOON_JELLYFISH, 0xa293f3, 0xe0caf8)
    val NOMURA_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("nomura_jellyfish_spawn_egg", HAEntityTypes.NOMURA_JELLYFISH, 0xe5dccf, 0x64353b)
    val SEA_NETTLE_SPAWN_EGG =
        registerSpawnEgg("sea_nettle_spawn_egg", HAEntityTypes.SEA_NETTLE, 0xf7bc78, 0x76435f)
    val BOX_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("box_jellyfish_spawn_egg", HAEntityTypes.BOX_JELLYFISH, 0x9ba6de, 0xebeff8)
    val MAUVE_STINGER_SPAWN_EGG =
        registerSpawnEgg("mauve_stinger_spawn_egg", HAEntityTypes.MAUVE_STINGER, 0x633063, 0xbc787a)
    val CROWN_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("crown_jellyfish_spawn_egg", HAEntityTypes.CROWN_JELLYFISH, 0xa32858, 0x4dc0e8)
    val BIG_RED_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("big_red_jellyfish_spawn_egg", HAEntityTypes.BIG_RED_JELLYFISH, 0xf4e5e5, 0xe72e46)
    val COSMIC_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("cosmic_jellyfish_spawn_egg", HAEntityTypes.COSMIC_JELLYFISH, 0xe7debb, 0xffd375)
    val COMB_JELLY_SPAWN_EGG =
        registerSpawnEgg("comb_jelly_spawn_egg", HAEntityTypes.COMB_JELLY, 0x9de1df, 0xff6e76)
    val FIREWORK_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg(
            "firework_jellyfish_spawn_egg", HAEntityTypes.FIREWORK_JELLYFISH, 0x6975e8, 0xfc7fb7)
    //#endregion

    //#region Crustacean Spawn Eggs
    val COCONUT_CRAB_SPAWN_EGG =
        registerSpawnEgg("coconut_crab_spawn_egg", HAEntityTypes.COCONUT_CRAB, 0x3e2d25, 0x3c546d)
    val DUNGENESS_CRAB_SPAWN_EGG =
        registerSpawnEgg("dungeness_crab_spawn_egg", HAEntityTypes.DUNGENESS_CRAB, 0x81353f, 0xeecfce)
    val CRAYFISH_SPAWN_EGG =
        registerSpawnEgg("crayfish_spawn_egg", HAEntityTypes.CRAYFISH, 0x697152, 0x7c4452)
    val FIDDLER_CRAB_SPAWN_EGG =
        registerSpawnEgg("fiddler_crab_spawn_egg", HAEntityTypes.FIDDLER_CRAB, 0x80366b, 0xf39949)
    val FLOWER_CRAB_SPAWN_EGG =
        registerSpawnEgg("flower_crab_spawn_egg", HAEntityTypes.FLOWER_CRAB, 0x9b8a6e, 0x20a094)
    val DECORATOR_CRAB_SPAWN_EGG =
        registerSpawnEgg("decorator_crab_spawn_egg", HAEntityTypes.DECORATOR_CRAB, 0xffb570, 0x314fdd)
    val GHOST_CRAB_SPAWN_EGG =
        registerSpawnEgg("ghost_crab_spawn_egg", HAEntityTypes.GHOST_CRAB, 0xf2be69, 0xf5fcd9)
    val HERMIT_CRAB_SPAWN_EGG =
        registerSpawnEgg("hermit_crab_spawn_egg", HAEntityTypes.HERMIT_CRAB, 0xe97b13, 0xf2a65e)
    val HORSESHOE_CRAB_SPAWN_EGG =
        registerSpawnEgg("horseshoe_crab_spawn_egg", HAEntityTypes.HORSESHOE_CRAB, 0x6e6b55, 0x403b31)
    val LIGHTFOOT_CRAB_SPAWN_EGG =
        registerSpawnEgg("lightfoot_crab_spawn_egg", HAEntityTypes.LIGHTFOOT_CRAB, 0xb0305c, 0xff8c41)
    val LOBSTER_SPAWN_EGG =
        registerSpawnEgg("lobster_spawn_egg", HAEntityTypes.LOBSTER, 0x421b2f, 0x8a4836)
    val SHRIMP_SPAWN_EGG =
        registerSpawnEgg("shrimp_spawn_egg", HAEntityTypes.SHRIMP, 0xeb564b, 0xff9166)
    val VAMPIRE_CRAB_SPAWN_EGG =
        registerSpawnEgg("vampire_crab_spawn_egg", HAEntityTypes.VAMPIRE_CRAB, 0x322947, 0x752053)
    val GIANT_ISOPOD_SPAWN_EGG =
        registerSpawnEgg("giant_isopod_spawn_egg", HAEntityTypes.GIANT_ISOPOD, 0xe6d3d6, 0x3c2236)
    val SPIDER_CRAB_SPAWN_EGG =
        registerSpawnEgg("spider_crab_spawn_egg", HAEntityTypes.SPIDER_CRAB, 0x9d3e41, 0xc6836f)
    val YETI_CRAB_SPAWN_EGG =
        registerSpawnEgg("yeti_crab_spawn_egg", HAEntityTypes.YETI_CRAB, 0xfff4dd, 0xffd16b)
    //#endregion

    //#region Critter Spawn Eggs
    val SEA_SLUG_SPAWN_EGG =
        registerSpawnEgg("sea_slug_spawn_egg", HAEntityTypes.SEA_SLUG, 0xf7be47, 0xb853a3)
    val SCALYFOOT_SNAIL_SPAWN_EGG =
        registerSpawnEgg("scalyfoot_snail_spawn_egg", HAEntityTypes.SCALYFOOT_SNAIL, 0x39302d, 0xbe1433)
    val SEA_CUCUMBER_SPAWN_EGG =
        registerSpawnEgg("sea_cucumber_spawn_egg", HAEntityTypes.SEA_CUCUMBER, 0x225b6d, 0x0c2627)
    val SEA_URCHIN_SPAWN_EGG =
        registerSpawnEgg("sea_urchin_spawn_egg", HAEntityTypes.SEA_URCHIN, 0x994066, 0x41142c)
    val STARFISH_SPAWN_EGG =
        registerSpawnEgg("starfish_spawn_egg", HAEntityTypes.STARFISH, 0x994066, 0x592645)
    val SEA_ANGEL_SPAWN_EGG =
        registerSpawnEgg("sea_angel_spawn_egg", HAEntityTypes.SEA_ANGEL, 0xc6d5f9, 0xf38135)
    //#endregion

    //#region Mammal Spawn Eggs
    val OTTER_SPAWN_EGG =
        registerSpawnEgg("otter_spawn_egg", HAEntityTypes.OTTER, 0x60352f, 0xeebf80)

    val DUGONG_SPAWN_EGG =
        registerSpawnEgg("dugong_spawn_egg", HAEntityTypes.DUGONG, 0x807b75, 0xa9a18e)

    val MANATEE_SPAWN_EGG =
        registerSpawnEgg("manatee_spawn_egg", HAEntityTypes.MANATEE, 0x3e3935, 0x56534f)

    val ORCA_SPAWN_EGG =
        registerSpawnEgg("orca_spawn_egg", HAEntityTypes.ORCA, 0x282a32, 0xc5c6ca)
    //#endregion

    //#region Miniboss & Minion Spawn Eggs
    val KARKINOS_SPAWN_EGG =
        registerSpawnEgg("karkinos_spawn_egg", HAEntityTypes.KARKINOS, 0x852c2a, 0x3d1031)
    val KARCINOGEN_SPAWN_EGG =
        registerSpawnEgg("karcinogen_spawn_egg", HAEntityTypes.KARCINOGEN, 0x852c2a, 0x3d1031)
    val KARCINOMA_SPAWN_EGG =
        registerSpawnEgg("karcinoma_spawn_egg", HAEntityTypes.KARCINOMA, 0x852c2a, 0x3d1031)
    val SHELL_BEAST_SPAWN_EGG =
        registerSpawnEgg("shell_beast_spawn_egg", HAEntityTypes.SHELL_BEAST, 0xbbb490, 0x972f2e)
    val HYPNAUTILUS_SPAWN_EGG =
        registerSpawnEgg("hypnautilus_spawn_egg", HAEntityTypes.HYPNAUTILUS, 0xbbb490, 0x972f2e)
    //#endregion
    //#endregion

    fun register(id: String, item: Supplier<Item>): Supplier<Item> {
        return CommonClass.ITEMS.register(id, item)
    }

    private fun <T : Mob> registerSpawnEgg(
        id: String,
        type: Supplier<EntityType<T>>,
        primaryColor: Int,
        secondaryColor: Int,
    ): Supplier<SpawnEggItem> {
        return PLATFORM.registerSpawnEggItem(id, { type.get() }, primaryColor, secondaryColor)

    }

    fun registerBlockItem(id: String, block: Supplier<Block>): Supplier<Item> {
        return register(id) { BlockItem(block.get(), Item.Properties()) }
    }

    private fun registerPlaceableInWaterBlockItem(id: String, block: Supplier<Block>): Supplier<Item> {
        return register(id) { PlaceableInWaterItem(block.get(), Item.Properties()) }
    }

    private fun registerPlaceableInWaterOrLandBlockItem(id: String, block: Supplier<Block>): Supplier<Item> {
        return register(id) { PlaceableInWaterOrLandItem(block.get(), Item.Properties()) }
    }

    private fun registerVerticallyAttachable(
        id: String,
        standingBlock: Supplier<Block>,
        wallBlock: Supplier<Block>,
        direction: Direction = Direction.DOWN,
    ): Supplier<Item> {
        return register(id) {
            StandingAndWallBlockItem(
                standingBlock.get(),
                wallBlock.get(),
                Item.Properties(),
                direction
            )
        }
    }
}
