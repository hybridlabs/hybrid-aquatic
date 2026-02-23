@file:Suppress("unused", "SameParameterValue")

package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.fluid.HybridAquaticFluids
import dev.hybridlabs.aquatic.item.coral.CoralAxeItem
import dev.hybridlabs.aquatic.item.coral.CoralBladeItem
import dev.hybridlabs.aquatic.item.coral.CoralHoeItem
import dev.hybridlabs.aquatic.item.coral.CoralPickaxeItem
import dev.hybridlabs.aquatic.item.coral.CoralShovelItem
import dev.hybridlabs.aquatic.item.seashell.SeashellAxeItem
import dev.hybridlabs.aquatic.item.seashell.SeashellHoeItem
import dev.hybridlabs.aquatic.item.seashell.SeashellPickaxeItem
import dev.hybridlabs.aquatic.item.seashell.SeashellShovelItem
import dev.hybridlabs.aquatic.platform.Services.REINFORCED_DIVING_ARMOR_FACTORY
import dev.hybridlabs.aquatic.platform.Services.DIVING_ARMOR_FACTORY
import dev.hybridlabs.aquatic.platform.Services.EEL_ARMOR_FACTORY
import dev.hybridlabs.aquatic.platform.Services.MANGLERFISH_ARMOR_FACTORY
import dev.hybridlabs.aquatic.platform.Services.MOON_JELLYFISH_ARMOR_FACTORY
import dev.hybridlabs.aquatic.platform.Services.PLATFORM
import dev.hybridlabs.aquatic.platform.Services.SEASHELL_ARMOR_FACTORY
import dev.hybridlabs.aquatic.platform.Services.TURTLE_ARMOR_FACTORY
import net.minecraft.core.Direction
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.BucketItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.SpawnEggItem
import net.minecraft.world.item.StandingAndWallBlockItem
import net.minecraft.world.item.SwordItem
import net.minecraft.world.level.block.Block
import java.util.function.Supplier

object HybridAquaticItems {
    //#region Armor
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

    val TURTLE_CHESTPLATE = register(
        "turtle_chestplate"
    ) {
        TURTLE_ARMOR_FACTORY.create(
            ArmorItem.Type.CHESTPLATE, Item.Properties().stacksTo(1)
        )
    }

    val MANGLERFISH_LURE = register(
        "manglerfish_lure"
    ) {
        MANGLERFISH_ARMOR_FACTORY.create(
            ArmorItem.Type.HELMET, Item.Properties().stacksTo(1)
        )
    }

    val MANGLERFISH_FIN = register(
        "manglerfish_fin"
    ) {
        MANGLERFISH_ARMOR_FACTORY.create(
            ArmorItem.Type.CHESTPLATE, Item.Properties().stacksTo(1)
        )
    }

    val EEL_SCARF = register(
        "eel_scarf"
    ) {
        EEL_ARMOR_FACTORY.create(
            ArmorItem.Type.CHESTPLATE,
            Item.Properties().stacksTo(1)
        )
    }

    val MOON_JELLYFISH_HAT = register(
        "moon_jellyfish_hat"
    ) {
        MOON_JELLYFISH_ARMOR_FACTORY.create(
            ArmorItem.Type.HELMET, Item.Properties().stacksTo(1)
        )
    }
    //#endregion

    //#region Tools - Weapons - Hooks

    val SEA_MESSAGE_BOOK = register("sea_message_book") { SeaMessageBookItem(Item.Properties()) }
    val FISHING_NET = register("fishing_net") { FishingNetItem(Item.Properties().stacksTo(1)) }
    val KARKINOS_CLAW = register("karkinos_claw") { KarkinosClawItem(Item.Properties().stacksTo(1)) }
    val BRINE_BUCKET = register("brine_bucket") { BucketItem(HybridAquaticFluids.BRINE.get(), Item.Properties().stacksTo(1)) }

    val BARBED_HOOK = register("barbed_hook") { HookItem(Item.Properties().durability(16)) }
    val GLOWING_HOOK = register("glowing_hook") { HookItem(Item.Properties().durability(16)) }
    val MAGNETIC_HOOK = register("magnetic_hook") { HookItem(Item.Properties().durability(8)) }
    val CREEPERMAGNET_HOOK = register("creepermagnet_hook") { HookItem(Item.Properties().durability(1)) }
    val OMINOUS_HOOK = register("ominous_hook") { HookItem(Item.Properties().durability(1)) }

    val SEASHELL_SPEAR = register(
        "seashell_spear"
    ) {
        SwordItem(
            HybridAquaticToolMaterials.SEASHELL,
            2,
            -2.4f,
            Item.Properties()
        )
    }

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

    //#region Blocks

    //#region Nature Blocks

    val ANEMONE = register("anemone") {
        PLATFORM.createBlockItem(HybridAquaticBlocks.ANEMONE.get(), Item.Properties())
    }

    val STRAWBERRY_ANEMONE = register("strawberry_anemone") {
        PLATFORM.createBlockItem(HybridAquaticBlocks.STRAWBERRY_ANEMONE.get(), Item.Properties())
    }
    val GIANT_GREEN_ANEMONE = register("giant_green_anemone") {
        PLATFORM.createBlockItem(HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get(), Item.Properties())
    }

    val GIANT_CLAM = registerBlockItem("giant_clam") { HybridAquaticBlocks.GIANT_CLAM.get() }
    val OYSTER_BLOCK = registerBlockItem("oyster_block") { HybridAquaticBlocks.OYSTER.get() }
    val SARGASSUM = registerBlockItem("sargassum") { HybridAquaticBlocks.SARGASSUM.get() }
    val BULL_KELP = registerBlockItem("bull_kelp") { HybridAquaticBlocks.BULL_KELP.get() }
    val FLOATING_SARGASSUM =
        registerPlaceableInWaterBlockItem("floating_sargassum") { HybridAquaticBlocks.FLOATING_SARGASSUM.get() }
    val WATER_LETTUCE = registerPlaceableInWaterBlockItem("water_lettuce") { HybridAquaticBlocks.WATER_LETTUCE.get() }
    val WATER_HYACINTH = registerPlaceableInWaterBlockItem("water_hyacinth") { HybridAquaticBlocks.WATER_HYACINTH.get() }
    val JUNGLE_LILY_PAD =
        registerPlaceableInWaterBlockItem("jungle_lily_pad") { HybridAquaticBlocks.JUNGLE_LILY_PAD.get() }
    val SHORT_RED_ALGAE = registerBlockItem("short_red_algae") { HybridAquaticBlocks.SHORT_RED_ALGAE.get() }
    val RED_ALGAE = registerBlockItem("red_algae") { HybridAquaticBlocks.RED_ALGAE.get() }
    val SEA_LETTUCE = registerBlockItem("sea_lettuce") { HybridAquaticBlocks.SEA_LETTUCE.get() }
    val HYDROTHERMAL_VENT = registerBlockItem("hydrothermal_vent") { HybridAquaticBlocks.THERMAL_VENT.get() }
    val TUBE_WORM = registerBlockItem("tube_worm") { HybridAquaticBlocks.TUBE_WORM.get() }
    val TUBE_SPONGE = registerBlockItem("tube_sponge") { HybridAquaticBlocks.TUBE_SPONGE.get() }
    val GLASS_SPONGE = registerBlockItem("glass_sponge") { HybridAquaticBlocks.GLASS_SPONGE.get() }
    val HARP_SPONGE = registerBlockItem("harp_sponge") { HybridAquaticBlocks.HARP_SPONGE.get() }

    //#region Coral Blocks
    val BUTTON_CORAL_BLOCK = registerBlockItem("button_coral_block") { HybridAquaticBlocks.BUTTON_CORAL_BLOCK.get() }
    val DEAD_BUTTON_CORAL_BLOCK =
        registerBlockItem("dead_button_coral_block") { HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK.get() }
    val BUTTON_CORAL = registerBlockItem("button_coral") { HybridAquaticBlocks.BUTTON_CORAL.get() }
    val DEAD_BUTTON_CORAL = registerBlockItem("dead_button_coral") { HybridAquaticBlocks.DEAD_BUTTON_CORAL.get() }
    val BUTTON_CORAL_FAN = registerVerticallyAttachable(
        "button_coral_fan",
        HybridAquaticBlocks.BUTTON_CORAL_FAN,
        HybridAquaticBlocks.BUTTON_CORAL_WALL_FAN
    )
    val DEAD_BUTTON_CORAL_FAN = registerVerticallyAttachable(
        "dead_button_coral_fan",
        HybridAquaticBlocks.DEAD_BUTTON_CORAL_FAN,
        HybridAquaticBlocks.DEAD_BUTTON_CORAL_WALL_FAN
    )

    val BLEACHED_BUTTON_CORAL_BLOCK =
        registerBlockItem("bleached_button_coral_block") { HybridAquaticBlocks.BLEACHED_BUTTON_CORAL_BLOCK.get() }
    val BLEACHED_BUTTON_CORAL =
        registerBlockItem("bleached_button_coral") { HybridAquaticBlocks.BLEACHED_BUTTON_CORAL.get() }
    val BLEACHED_BUTTON_CORAL_FAN = registerVerticallyAttachable(
        "bleached_button_coral_fan",
        HybridAquaticBlocks.BLEACHED_BUTTON_CORAL_FAN,
        HybridAquaticBlocks.BLEACHED_BUTTON_CORAL_WALL_FAN
    )

    val SUN_CORAL_BLOCK = registerBlockItem("sun_coral_block") { HybridAquaticBlocks.SUN_CORAL_BLOCK.get() }
    val DEAD_SUN_CORAL_BLOCK =
        registerBlockItem("dead_sun_coral_block") { HybridAquaticBlocks.DEAD_SUN_CORAL_BLOCK.get() }
    val SUN_CORAL = registerBlockItem("sun_coral") { HybridAquaticBlocks.SUN_CORAL.get() }
    val DEAD_SUN_CORAL = registerBlockItem("dead_sun_coral") { HybridAquaticBlocks.DEAD_SUN_CORAL.get() }
    val SUN_CORAL_FAN = registerVerticallyAttachable(
        "sun_coral_fan",
        HybridAquaticBlocks.SUN_CORAL_FAN,
        HybridAquaticBlocks.SUN_CORAL_WALL_FAN
    )
    val DEAD_SUN_CORAL_FAN = registerVerticallyAttachable(
        "dead_sun_coral_fan",
        HybridAquaticBlocks.DEAD_SUN_CORAL_FAN,
        HybridAquaticBlocks.DEAD_SUN_CORAL_WALL_FAN
    )

    val BLEACHED_SUN_CORAL_BLOCK =
        registerBlockItem("bleached_sun_coral_block") { HybridAquaticBlocks.BLEACHED_SUN_CORAL_BLOCK.get() }
    val BLEACHED_SUN_CORAL =
        registerBlockItem("bleached_sun_coral") { HybridAquaticBlocks.BLEACHED_SUN_CORAL.get() }
    val BLEACHED_SUN_CORAL_FAN = registerVerticallyAttachable(
        "bleached_sun_coral_fan",
        HybridAquaticBlocks.BLEACHED_SUN_CORAL_FAN,
        HybridAquaticBlocks.BLEACHED_SUN_CORAL_WALL_FAN
    )

    val LOPHELIA_CORAL_BLOCK = registerBlockItem("lophelia_coral_block") { HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK.get() }
    val DEAD_LOPHELIA_CORAL_BLOCK = registerBlockItem("dead_lophelia_coral_block") { HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK.get() }
    val BLEACHED_LOPHELIA_CORAL_BLOCK = registerBlockItem("bleached_lophelia_coral_block") { HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL_BLOCK.get() }
    val LOPHELIA_CORAL = registerBlockItem("lophelia_coral") { HybridAquaticBlocks.LOPHELIA_CORAL.get() }
    val DEAD_LOPHELIA_CORAL = registerBlockItem("dead_lophelia_coral") { HybridAquaticBlocks.DEAD_LOPHELIA_CORAL.get() }
    val BLEACHED_LOPHELIA_CORAL = registerBlockItem("bleached_lophelia_coral") { HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL.get() }
    val LOPHELIA_CORAL_FAN = registerVerticallyAttachable(
        "lophelia_coral_fan",
        HybridAquaticBlocks.LOPHELIA_CORAL_FAN,
        HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN
    )
    val DEAD_LOPHELIA_CORAL_FAN = registerVerticallyAttachable(
        "dead_lophelia_coral_fan",
        HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN,
        HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_WALL_FAN
    )

    val BLEACHED_LOPHELIA_CORAL_FAN = registerVerticallyAttachable(
        "bleached_lophelia_coral_fan",
        HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL_FAN,
        HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL_WALL_FAN
    )

    val ROSE_CORAL_BLOCK = registerBlockItem("rose_coral_block") { HybridAquaticBlocks.ROSE_CORAL_BLOCK.get() }
    val DEAD_ROSE_CORAL_BLOCK = registerBlockItem("dead_rose_coral_block") { HybridAquaticBlocks.DEAD_ROSE_CORAL_BLOCK.get() }
    val BLEACHED_ROSE_CORAL_BLOCK = registerBlockItem("bleached_rose_coral_block") { HybridAquaticBlocks.BLEACHED_ROSE_CORAL_BLOCK.get() }
    val ROSE_CORAL = registerBlockItem("rose_coral") { HybridAquaticBlocks.ROSE_CORAL.get() }
    val DEAD_ROSE_CORAL = registerBlockItem("dead_rose_coral") { HybridAquaticBlocks.DEAD_ROSE_CORAL.get() }
    val BLEACHED_ROSE_CORAL = registerBlockItem("bleached_rose_coral") { HybridAquaticBlocks.BLEACHED_ROSE_CORAL.get() }
    val ROSE_CORAL_FAN = registerVerticallyAttachable(
        "rose_coral_fan",
        HybridAquaticBlocks.ROSE_CORAL_FAN,
        HybridAquaticBlocks.ROSE_CORAL_WALL_FAN
    )
    val DEAD_ROSE_CORAL_FAN = registerVerticallyAttachable(
        "dead_rose_coral_fan",
        HybridAquaticBlocks.DEAD_ROSE_CORAL_FAN,
        HybridAquaticBlocks.DEAD_ROSE_CORAL_WALL_FAN
    )
    val BLEACHED_ROSE_CORAL_FAN = registerVerticallyAttachable(
        "bleached_rose_coral_fan",
        HybridAquaticBlocks.BLEACHED_ROSE_CORAL_FAN,
        HybridAquaticBlocks.BLEACHED_ROSE_CORAL_WALL_FAN
    )

    val LEAF_CORAL_BLOCK =
        registerBlockItem("leaf_coral_block") { HybridAquaticBlocks.LEAF_CORAL_BLOCK.get() }
    val DEAD_LEAF_CORAL_BLOCK =
        registerBlockItem("dead_leaf_coral_block") { HybridAquaticBlocks.DEAD_LEAF_CORAL_BLOCK.get() }
    val LEAF_CORAL = registerBlockItem("leaf_coral") { HybridAquaticBlocks.LEAF_CORAL.get() }
    val DEAD_LEAF_CORAL = registerBlockItem("dead_leaf_coral") { HybridAquaticBlocks.DEAD_LEAF_CORAL.get() }
    val LEAF_CORAL_FAN = registerVerticallyAttachable(
        "leaf_coral_fan",
        HybridAquaticBlocks.LEAF_CORAL_FAN,
        HybridAquaticBlocks.LEAF_CORAL_WALL_FAN
    )
    val DEAD_LEAF_CORAL_FAN = registerVerticallyAttachable(
        "dead_leaf_coral_fan",
        HybridAquaticBlocks.DEAD_LEAF_CORAL_FAN,
        HybridAquaticBlocks.DEAD_LEAF_CORAL_WALL_FAN
    )

    val BLEACHED_LEAF_CORAL_BLOCK =
        registerBlockItem("bleached_leaf_coral_block") { HybridAquaticBlocks.BLEACHED_LEAF_CORAL_BLOCK.get() }
    val BLEACHED_LEAF_CORAL =
        registerBlockItem("bleached_leaf_coral") { HybridAquaticBlocks.BLEACHED_LEAF_CORAL.get() }
    val BLEACHED_LEAF_CORAL_FAN = registerVerticallyAttachable(
        "bleached_leaf_coral_fan",
        HybridAquaticBlocks.BLEACHED_LEAF_CORAL_FAN,
        HybridAquaticBlocks.BLEACHED_LEAF_CORAL_WALL_FAN
    )

    val THORN_CORAL_BLOCK = registerBlockItem("thorn_coral_block") { HybridAquaticBlocks.THORN_CORAL_BLOCK.get() }
    val DEAD_THORN_CORAL_BLOCK =
        registerBlockItem("dead_thorn_coral_block") { HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK.get() }
    val THORN_CORAL = registerBlockItem("thorn_coral") { HybridAquaticBlocks.THORN_CORAL.get() }
    val DEAD_THORN_CORAL = registerBlockItem("dead_thorn_coral") { HybridAquaticBlocks.DEAD_THORN_CORAL.get() }
    val THORN_CORAL_FAN = registerVerticallyAttachable(
        "thorn_coral_fan",
        HybridAquaticBlocks.THORN_CORAL_FAN,
        HybridAquaticBlocks.THORN_CORAL_WALL_FAN
    )
    val DEAD_THORN_CORAL_FAN = registerVerticallyAttachable(
        "dead_thorn_coral_fan",
        HybridAquaticBlocks.DEAD_THORN_CORAL_FAN,
        HybridAquaticBlocks.DEAD_THORN_CORAL_WALL_FAN
    )

    val BLEACHED_THORN_CORAL_BLOCK =
        registerBlockItem("bleached_thorn_coral_block") { HybridAquaticBlocks.BLEACHED_THORN_CORAL_BLOCK.get() }
    val BLEACHED_THORN_CORAL =
        registerBlockItem("bleached_thorn_coral") { HybridAquaticBlocks.BLEACHED_THORN_CORAL.get() }
    val BLEACHED_THORN_CORAL_FAN = registerVerticallyAttachable(
        "bleached_thorn_coral_fan",
        HybridAquaticBlocks.BLEACHED_THORN_CORAL_FAN,
        HybridAquaticBlocks.BLEACHED_THORN_CORAL_WALL_FAN
    )

    val BLEACHED_FIRE_CORAL_BLOCK = registerBlockItem("bleached_fire_coral_block") { HybridAquaticBlocks.BLEACHED_FIRE_CORAL_BLOCK.get() }
    val BLEACHED_FIRE_CORAL = registerBlockItem("bleached_fire_coral") { HybridAquaticBlocks.BLEACHED_FIRE_CORAL.get() }
    val BLEACHED_FIRE_CORAL_FAN = registerVerticallyAttachable(
        "bleached_fire_coral_fan",
        HybridAquaticBlocks.BLEACHED_FIRE_CORAL_FAN,
        HybridAquaticBlocks.BLEACHED_FIRE_CORAL_WALL_FAN
    )

    val BLEACHED_TUBE_CORAL_BLOCK = registerBlockItem("bleached_tube_coral_block") { HybridAquaticBlocks.BLEACHED_TUBE_CORAL_BLOCK.get() }
    val BLEACHED_TUBE_CORAL = registerBlockItem("bleached_tube_coral") { HybridAquaticBlocks.BLEACHED_TUBE_CORAL.get() }
    val BLEACHED_TUBE_CORAL_FAN = registerVerticallyAttachable(
        "bleached_tube_coral_fan",
        HybridAquaticBlocks.BLEACHED_TUBE_CORAL_FAN,
        HybridAquaticBlocks.BLEACHED_TUBE_CORAL_WALL_FAN
    )

    val BLEACHED_HORN_CORAL_BLOCK = registerBlockItem("bleached_horn_coral_block") { HybridAquaticBlocks.BLEACHED_HORN_CORAL_BLOCK.get() }
    val BLEACHED_HORN_CORAL = registerBlockItem("bleached_horn_coral") { HybridAquaticBlocks.BLEACHED_HORN_CORAL.get() }
    val BLEACHED_HORN_CORAL_FAN = registerVerticallyAttachable(
        "bleached_horn_coral_fan",
        HybridAquaticBlocks.BLEACHED_HORN_CORAL_FAN,
        HybridAquaticBlocks.BLEACHED_HORN_CORAL_WALL_FAN
    )

    val BLEACHED_BUBBLE_CORAL_BLOCK = registerBlockItem("bleached_bubble_coral_block") { HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL_BLOCK.get() }
    val BLEACHED_BUBBLE_CORAL = registerBlockItem("bleached_bubble_coral") { HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL.get() }
    val BLEACHED_BUBBLE_CORAL_FAN = registerVerticallyAttachable(
        "bleached_bubble_coral_fan",
        HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL_FAN,
        HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL_WALL_FAN
    )

    val BLEACHED_BRAIN_CORAL_BLOCK = registerBlockItem("bleached_brain_coral_block") { HybridAquaticBlocks.BLEACHED_BRAIN_CORAL_BLOCK.get() }
    val BLEACHED_BRAIN_CORAL = registerBlockItem("bleached_brain_coral") { HybridAquaticBlocks.BLEACHED_BRAIN_CORAL.get() }
    val BLEACHED_BRAIN_CORAL_FAN = registerVerticallyAttachable(
        "bleached_brain_coral_fan",
        HybridAquaticBlocks.BLEACHED_BRAIN_CORAL_FAN,
        HybridAquaticBlocks.BLEACHED_BRAIN_CORAL_WALL_FAN
    )
    //#endregion

    //#endregion

    //#region Artificial Blocks

    val GLOWSLIME_BLOCK = registerBlockItem("glowslime_block") { HybridAquaticBlocks.GLOWSLIME_BLOCK.get() }
    val PEARL_BLOCK = registerBlockItem("pearl_block") { HybridAquaticBlocks.PEARL_BLOCK.get() }
    val BLACK_PEARL_BLOCK = registerBlockItem("black_pearl_block") { HybridAquaticBlocks.BLACK_PEARL_BLOCK.get() }

    val CRYSTALLINE_SULFUR = registerBlockItem("crystalline_sulfur") { HybridAquaticBlocks.CRYSTALLINE_SULFUR.get() }
    val GRASSY_SAND = registerBlockItem("grassy_sand") { HybridAquaticBlocks.GRASSY_SAND.get() }
    val AERATED_SAND = registerBlockItem("aerated_sand") { HybridAquaticBlocks.AERATED_SAND.get() }
    val BUBBLE_GEYSER = registerBlockItem("bubble_geyser") { HybridAquaticBlocks.BUBBLE_GEYSER.get() }
    val WHITE_SAND = registerBlockItem("white_sand") { HybridAquaticBlocks.WHITE_SAND.get() }
    val WHITE_SANDSTONE = registerBlockItem("white_sandstone") { HybridAquaticBlocks.WHITE_SANDSTONE.get() }
    val SUSPICIOUS_RED_SAND = registerBlockItem("suspicious_red_sand") { HybridAquaticBlocks.SUSPICIOUS_RED_SAND.get() }
    val CORALSTONE = registerBlockItem("coralstone") { HybridAquaticBlocks.CORALSTONE.get() }
    val SHORESTONE = registerBlockItem("shorestone") { HybridAquaticBlocks.SHORESTONE.get() }
    val BARNACLE_SHORESTONE = registerBlockItem("barnacle_shorestone") { HybridAquaticBlocks.BARNACLE_SHORESTONE.get() }

    val MARINE_SNOW = registerBlockItem("marine_snow") { HybridAquaticBlocks.MARINE_SNOW.get() }
    val BUOY = registerPlaceableInWaterBlockItem("buoy") { HybridAquaticBlocks.BUOY.get() }
    val RAFT = registerPlaceableInWaterBlockItem("raft") { HybridAquaticBlocks.RAFT.get() }
    val OAK_RAFT = registerPlaceableInWaterBlockItem("oak_raft") { HybridAquaticBlocks.OAK_RAFT.get() }
    val SPRUCE_RAFT = registerPlaceableInWaterBlockItem("spruce_raft") { HybridAquaticBlocks.SPRUCE_RAFT.get() }
    val DARK_OAK_RAFT = registerPlaceableInWaterBlockItem("dark_oak_raft") { HybridAquaticBlocks.DARK_OAK_RAFT.get() }
    val BIRCH_RAFT = registerPlaceableInWaterBlockItem("birch_raft") { HybridAquaticBlocks.BIRCH_RAFT.get() }
    val ACACIA_RAFT = registerPlaceableInWaterBlockItem("acacia_raft") { HybridAquaticBlocks.ACACIA_RAFT.get() }
    val JUNGLE_RAFT = registerPlaceableInWaterBlockItem("jungle_raft") { HybridAquaticBlocks.JUNGLE_RAFT.get() }
    val MANGROVE_RAFT = registerPlaceableInWaterBlockItem("mangrove_raft") { HybridAquaticBlocks.MANGROVE_RAFT.get() }
    val CHERRY_RAFT = registerPlaceableInWaterBlockItem("cherry_raft") { HybridAquaticBlocks.CHERRY_RAFT.get() }
    val DRIFTWOOD_RAFT = registerPlaceableInWaterBlockItem("driftwood_raft") { HybridAquaticBlocks.DRIFTWOOD_RAFT.get() }
    val GLOWSTICK = registerVerticallyAttachable("glowstick", HybridAquaticBlocks.GLOWSTICK, HybridAquaticBlocks.WALL_GLOWSTICK)
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle") { PLATFORM.createMessageInABottleItem(Item.Properties()) }

    //#region Plushies

    val BASKING_SHARK_PLUSHIE = registerBlockItem("basking_shark_plushie") { HybridAquaticBlocks.BASKING_SHARK_PLUSHIE.get() }
    val BULL_SHARK_PLUSHIE = registerBlockItem("bull_shark_plushie") { HybridAquaticBlocks.BULL_SHARK_PLUSHIE.get() }
    val FRILLED_SHARK_PLUSHIE = registerBlockItem("frilled_shark_plushie") { HybridAquaticBlocks.FRILLED_SHARK_PLUSHIE.get() }
    val GREAT_WHITE_SHARK_PLUSHIE = registerBlockItem("great_white_shark_plushie") { HybridAquaticBlocks.GREAT_WHITE_SHARK_PLUSHIE.get() }
    val HAMMERHEAD_SHARK_PLUSHIE = registerBlockItem("hammerhead_shark_plushie") { HybridAquaticBlocks.HAMMERHEAD_SHARK_PLUSHIE.get() }
    val THRESHER_SHARK_PLUSHIE = registerBlockItem("thresher_shark_plushie") { HybridAquaticBlocks.THRESHER_SHARK_PLUSHIE.get() }
    val TIGER_SHARK_PLUSHIE = registerBlockItem("tiger_shark_plushie") { HybridAquaticBlocks.TIGER_SHARK_PLUSHIE.get() }
    val WHALE_SHARK_PLUSHIE = registerBlockItem("whale_shark_plushie") { HybridAquaticBlocks.WHALE_SHARK_PLUSHIE.get() }

    //#endregion

    //#region Crates

    val CRAB_POT = registerBlockItem("crab_pot") { HybridAquaticBlocks.CRAB_POT.get() }
    val HYBRID_CRATE = registerBlockItem("hybrid_crate") { HybridAquaticBlocks.HYBRID_CRATE.get() }
    val OAK_CRATE = registerBlockItem("oak_crate") { HybridAquaticBlocks.OAK_CRATE.get() }
    val SPRUCE_CRATE = registerBlockItem("spruce_crate") { HybridAquaticBlocks.SPRUCE_CRATE.get() }
    val BIRCH_CRATE = registerBlockItem("birch_crate") { HybridAquaticBlocks.BIRCH_CRATE.get() }
    val DARK_OAK_CRATE = registerBlockItem("dark_oak_crate") { HybridAquaticBlocks.DARK_OAK_CRATE.get() }
    val JUNGLE_CRATE = registerBlockItem("jungle_crate") { HybridAquaticBlocks.JUNGLE_CRATE.get() }
    val ACACIA_CRATE = registerBlockItem("acacia_crate") { HybridAquaticBlocks.ACACIA_CRATE.get() }
    val MANGROVE_CRATE = registerBlockItem("mangrove_crate") { HybridAquaticBlocks.MANGROVE_CRATE.get() }
    val CHERRY_CRATE = registerBlockItem("cherry_crate") { HybridAquaticBlocks.CHERRY_CRATE.get() }
    val BAMBOO_CRATE = registerBlockItem("bamboo_crate") { HybridAquaticBlocks.BAMBOO_CRATE.get() }

    //#endregion


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
    val CUTTLEBONE = register("cuttlebone") { Item(Item.Properties()) }
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

    //# region Food

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

    val OYSTER = register(
        "oyster"
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

    val GOLDFISH = register(
        "goldfish"
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

    //#region Fish

    val AFRICAN_BUTTERFLYFISH_SPAWN_EGG =
        registerSpawnEgg("african_butterflyfish_spawn_egg", HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH, 0xb57955, 0x1e3555)
    val BARRELEYE_SPAWN_EGG =
        registerSpawnEgg("barreleye_spawn_egg", HybridAquaticEntityTypes.BARRELEYE, 0x4b4343, 0x6bc96c)
    val BETTA_SPAWN_EGG =
        registerSpawnEgg("betta_spawn_egg", HybridAquaticEntityTypes.BETTA, 0xcc425e, 0x504198)
    val DAMSELFISH_SPAWN_EGG =
        registerSpawnEgg("damselfish_spawn_egg", HybridAquaticEntityTypes.DAMSELFISH, 0x96aba5, 0xf9d100)
    val CARP_SPAWN_EGG =
        registerSpawnEgg("carp_spawn_egg", HybridAquaticEntityTypes.CARP, 0x725234, 0xd3ad8c)
    val TROUT_SPAWN_EGG =
        registerSpawnEgg("trout_spawn_egg", HybridAquaticEntityTypes.TROUT, 0x725234, 0xd3ad8c)
    val SUNFISH_SPAWN_EGG =
        registerSpawnEgg("sunfish_spawn_egg", HybridAquaticEntityTypes.SUNFISH, 0x725234, 0xd3ad8c)
    val CLOWNFISH_SPAWN_EGG =
        registerSpawnEgg("clownfish_spawn_egg", HybridAquaticEntityTypes.CLOWNFISH, 0xff9166, 0xfdf7f9)
    val BOXFISH_SPAWN_EGG =
        registerSpawnEgg("boxfish_spawn_egg", HybridAquaticEntityTypes.BOXFISH, 0xfffeac, 0xffc056)
    val DANIO_SPAWN_EGG =
        registerSpawnEgg("danio_spawn_egg", HybridAquaticEntityTypes.DANIO, 0xdcdced, 0x2a3f52)
    val DISCUS_SPAWN_EGG =
        registerSpawnEgg("discus_spawn_egg", HybridAquaticEntityTypes.DISCUS, 0xeeeecd, 0xf4a957)
    val CORYDORA_SPAWN_EGG =
        registerSpawnEgg("corydora_spawn_egg", HybridAquaticEntityTypes.CORYDORA, 0xeeeecd, 0xf4a957)
    val FLASHLIGHT_FISH_SPAWN_EGG =
        registerSpawnEgg("flashlight_fish_spawn_egg", HybridAquaticEntityTypes.FLASHLIGHT_FISH, 0x5c433e, 0xfffaa9)
    val SQUIRRELFISH_FISH_SPAWN_EGG =
        registerSpawnEgg("squirrelfish_spawn_egg", HybridAquaticEntityTypes.SQUIRRELFISH, 0x9b3f3d, 0xcfa184)
    val PEARLFISH_FISH_SPAWN_EGG =
        registerSpawnEgg("pearlfish_spawn_egg", HybridAquaticEntityTypes.PEARLFISH, 0x464c59, 0xc7cfd3)
    val FLYING_FISH_SPAWN_EGG =
        registerSpawnEgg("flying_fish_spawn_egg", HybridAquaticEntityTypes.FLYING_FISH, 0x7c93e1, 0xfbf7e6)
    val GOLDFISH_SPAWN_EGG =
        registerSpawnEgg("goldfish_spawn_egg", HybridAquaticEntityTypes.GOLDFISH, 0xefedf6, 0xff9166)
    val GOURAMI_SPAWN_EGG =
        registerSpawnEgg("gourami_spawn_egg", HybridAquaticEntityTypes.GOURAMI, 0x7bb6cf, 0x722a37)
    val PLECO_SPAWN_EGG =
        registerSpawnEgg("pleco_spawn_egg", HybridAquaticEntityTypes.PLECO, 0x7bb6cf, 0x722a37)
    val SHINER_SPAWN_EGG =
        registerSpawnEgg("shiner_spawn_egg", HybridAquaticEntityTypes.SHINER, 0x7bb6cf, 0x722a37)
    val LIONFISH_SPAWN_EGG =
        registerSpawnEgg("lionfish_spawn_egg", HybridAquaticEntityTypes.LIONFISH, 0xf9e6cf, 0xc64524)
    val MACKEREL_SPAWN_EGG =
        registerSpawnEgg("mackerel_spawn_egg", HybridAquaticEntityTypes.MACKEREL, 0x395562, 0xfff09c)
    val HERRING_SPAWN_EGG =
        registerSpawnEgg("herring_spawn_egg", HybridAquaticEntityTypes.HERRING, 0xcfd8d4, 0x5f6e75)
    val MAHI_SPAWN_EGG =
        registerSpawnEgg("mahi_spawn_egg", HybridAquaticEntityTypes.MAHI, 0x528c4e, 0xfffd69)
    val MANTA_RAY_SPAWN_EGG =
        registerSpawnEgg("manta_ray_spawn_egg", HybridAquaticEntityTypes.MANTA_RAY, 0x000000, 0xFFFFFF)
    val MORAY_EEL_SPAWN_EGG =
        registerSpawnEgg("moray_eel_spawn_egg", HybridAquaticEntityTypes.MORAY_EEL, 0x8da163, 0x1d4435)
    val NEEDLEFISH_SPAWN_EGG =
        registerSpawnEgg("needlefish_spawn_egg", HybridAquaticEntityTypes.NEEDLEFISH, 0xc0e4f7, 0x537da8)
    val BARRACUDA_SPAWN_EGG =
        registerSpawnEgg("barracuda_spawn_egg", HybridAquaticEntityTypes.BARRACUDA, 0x64b2c6, 0x3d4d64)
    val OPAH_SPAWN_EGG =
        registerSpawnEgg("opah_spawn_egg", HybridAquaticEntityTypes.OPAH, 0x6472a7, 0xea6262)
    val OSCAR_SPAWN_EGG =
        registerSpawnEgg("oscar_spawn_egg", HybridAquaticEntityTypes.OSCAR, 0xd5c97e, 0x836136)
    val PARROTFISH_SPAWN_EGG =
        registerSpawnEgg("parrotfish_spawn_egg", HybridAquaticEntityTypes.PARROTFISH, 0x728e6b, 0xe5c5c3)
    val PIRANHA_SPAWN_EGG =
        registerSpawnEgg("piranha_spawn_egg", HybridAquaticEntityTypes.PIRANHA, 0x535f92, 0xaf3b3d)
    val ROCKFISH_SPAWN_EGG =
        registerSpawnEgg("rockfish_spawn_egg", HybridAquaticEntityTypes.ROCKFISH, 0x711b2f, 0xeb5948)
    val SEA_BASS_SPAWN_EGG =
        registerSpawnEgg("sea_bass_spawn_egg", HybridAquaticEntityTypes.SEA_BASS, 0x323337, 0xe7e8e8)
    val SEAHORSE_SPAWN_EGG =
        registerSpawnEgg("seahorse_spawn_egg", HybridAquaticEntityTypes.SEAHORSE, 0xffc9ab, 0xe63f5e)
    val SEADRAGON_SPAWN_EGG =
        registerSpawnEgg("seadragon_spawn_egg", HybridAquaticEntityTypes.SEADRAGON, 0xffc9ab, 0xe63f5e)
    val STINGRAY_SPAWN_EGG =
        registerSpawnEgg("stingray_spawn_egg", HybridAquaticEntityTypes.STINGRAY, 0xffa214, 0x0069aa)
    val STONEFISH_SPAWN_EGG =
        registerSpawnEgg("stonefish_spawn_egg", HybridAquaticEntityTypes.STONEFISH, 0xaf8b68, 0x574435)
    val OCEAN_SUNFISH_SPAWN_EGG =
        registerSpawnEgg("ocean_sunfish_spawn_egg", HybridAquaticEntityTypes.OCEAN_SUNFISH, 0x687f96, 0x455764)
    val SURGEONFISH_SPAWN_EGG =
        registerSpawnEgg("surgeonfish_spawn_egg", HybridAquaticEntityTypes.SURGEONFISH, 0x88a1d7, 0x211b2f)
    val TETRA_SPAWN_EGG =
        registerSpawnEgg("tetra_spawn_egg", HybridAquaticEntityTypes.TETRA, 0x4eb1cc, 0xe64d43)
    val PUPFISH_SPAWN_EGG =
        registerSpawnEgg("pupfish_spawn_egg", HybridAquaticEntityTypes.PUPFISH, 0x020c44, 0x2785f5)
    val TIGER_BARB_SPAWN_EGG =
        registerSpawnEgg("tiger_barb_spawn_egg", HybridAquaticEntityTypes.TIGER_BARB, 0xfbbf2d, 0x611851)
    val BLOWFISH_SPAWN_EGG =
        registerSpawnEgg("blowfish_spawn_egg", HybridAquaticEntityTypes.BLOWFISH, 0xfcf2ce, 0x885e6d)
    val TRIGGERFISH_SPAWN_EGG =
        registerSpawnEgg("triggerfish_spawn_egg", HybridAquaticEntityTypes.TRIGGERFISH, 0x5b7c7e, 0xbdcdda)
    val TUNA_SPAWN_EGG =
        registerSpawnEgg("tuna_spawn_egg", HybridAquaticEntityTypes.TUNA, 0x36668d, 0xf5d58d)
    val GOLDEN_DORADO_SPAWN_EGG =
        registerSpawnEgg("golden_dorado_spawn_egg", HybridAquaticEntityTypes.GOLDEN_DORADO, 0xd16020, 0xa4975f)
    val WRASSE_SPAWN_EGG =
        registerSpawnEgg("wrasse_spawn_egg", HybridAquaticEntityTypes.WRASSE, 0x2c2628, 0xdc5f5d)

    //#endregion

    //#region Deep Sea Fish

    val ANGLERFISH_SPAWN_EGG =
        registerSpawnEgg("anglerfish_spawn_egg", HybridAquaticEntityTypes.ANGLERFISH, 0x4b4257, 0xa7f1eb)
     val VIPERFISH_SPAWN_EGG =
        registerSpawnEgg("viperfish_spawn_egg", HybridAquaticEntityTypes.VIPERFISH, 0x4b4257, 0xa7f1eb)
     val HATCHETFISH_SPAWN_EGG =
        registerSpawnEgg("hatchetfish_spawn_egg", HybridAquaticEntityTypes.HATCHETFISH, 0x4b4257, 0xa7f1eb)
     val FANGTOOTH_SPAWN_EGG =
        registerSpawnEgg("fangtooth_spawn_egg", HybridAquaticEntityTypes.FANGTOOTH, 0x4b4257, 0xa7f1eb)
    val COELACANTH_SPAWN_EGG =
        registerSpawnEgg("coelacanth_spawn_egg", HybridAquaticEntityTypes.COELACANTH, 0x2f517a, 0xbac4d3)
    val DRAGONFISH_SPAWN_EGG =
        registerSpawnEgg("dragonfish_spawn_egg", HybridAquaticEntityTypes.DRAGONFISH, 0x2e2e33, 0xfffaa9)
    val JOHN_DORY_SPAWN_EGG =
        registerSpawnEgg("john_dory_spawn_egg", HybridAquaticEntityTypes.JOHN_DORY, 0xdcc6c6, 0x8a7f55)
    val SNAILFISH_SPAWN_EGG =
        registerSpawnEgg("snailfish_spawn_egg", HybridAquaticEntityTypes.SNAILFISH, 0xe0c2ed, 0xf0dcef)
    val OARFISH_SPAWN_EGG =
        registerSpawnEgg("oarfish_spawn_egg", HybridAquaticEntityTypes.OARFISH, 0x8892ab, 0xb04743)
    val RATFISH_SPAWN_EGG =
        registerSpawnEgg("ratfish_spawn_egg", HybridAquaticEntityTypes.RATFISH, 0xa16470, 0x673146)

    //#endregion

    //#region Cephalopod

    val CUTTLEFISH_SPAWN_EGG =
        registerSpawnEgg("cuttlefish_spawn_egg", HybridAquaticEntityTypes.CUTTLEFISH, 0x8a4836, 0xf6deae)
    val ARROW_SQUID_SPAWN_EGG =
        registerSpawnEgg("arrow_squid_spawn_egg", HybridAquaticEntityTypes.ARROW_SQUID, 0x761f31, 0xd56360)
    val COLOSSAL_SQUID_SPAWN_EGG =
        registerSpawnEgg("colossal_squid_spawn_egg", HybridAquaticEntityTypes.COLOSSAL_SQUID, 0x761f31, 0xd56360)
    val GIANT_SQUID_SPAWN_EGG =
        registerSpawnEgg("giant_squid_spawn_egg", HybridAquaticEntityTypes.GIANT_SQUID, 0x761f31, 0xd56360)
    val FIREFLY_SQUID_SPAWN_EGG =
        registerSpawnEgg("firefly_squid_spawn_egg", HybridAquaticEntityTypes.FIREFLY_SQUID, 0xc93a61, 0x4ec0e8)

    //#endregion

    //#region Deep Sea Cephalopod

    val OCTOPUS_SPAWN_EGG =
        registerSpawnEgg("octopus_spawn_egg", HybridAquaticEntityTypes.OCTOPUS, 0x73275c, 0xc34e69)
    val NAUTILUS_SPAWN_EGG =
        registerSpawnEgg("nautilus_spawn_egg", HybridAquaticEntityTypes.NAUTILUS, 0xd4ccc3, 0xae4635)
    val UMBRELLA_OCTOPUS_SPAWN_EGG =
        registerSpawnEgg("umbrella_octopus_spawn_egg", HybridAquaticEntityTypes.UMBRELLA_OCTOPUS, 0xffaf25, 0xfeff92)
    val VAMPIRE_SQUID_SPAWN_EGG =
        registerSpawnEgg("vampire_squid_spawn_egg", HybridAquaticEntityTypes.VAMPIRE_SQUID, 0x73363c, 0xc3e9e2)

    //#endregion

    //#region Jellyfish

    val BARREL_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("barrel_jellyfish_spawn_egg", HybridAquaticEntityTypes.BARREL_JELLYFISH, 0xd6f3ea, 0x413c83)
    val BLUE_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("blue_jellyfish_spawn_egg", HybridAquaticEntityTypes.BLUE_JELLYFISH, 0x4dc0e8, 0xff6b97)
    val CEPHEIDAE_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("cepheidae_jellyfish_spawn_egg", HybridAquaticEntityTypes.CEPHEIDAE_JELLYFISH, 0x623062, 0x89a1d8)
    val LIONS_MANE_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("lions_mane_jellyfish_spawn_egg", HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH, 0xf6d5b1, 0x541e48)
    val MOON_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("moon_jellyfish_spawn_egg", HybridAquaticEntityTypes.MOON_JELLYFISH, 0xa293f3, 0xe0caf8)
    val NOMURA_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("nomura_jellyfish_spawn_egg", HybridAquaticEntityTypes.NOMURA_JELLYFISH, 0xe5dccf, 0x64353b)
    val SEA_NETTLE_SPAWN_EGG =
        registerSpawnEgg("sea_nettle_spawn_egg", HybridAquaticEntityTypes.SEA_NETTLE, 0xf7bc78, 0x76435f)
    val BOX_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("box_jellyfish_spawn_egg", HybridAquaticEntityTypes.BOX_JELLYFISH, 0x9ba6de, 0xebeff8)

    //#endregion

    //#region Deep Sea Jellyfish

    val MAUVE_STINGER_SPAWN_EGG =
        registerSpawnEgg("mauve_stinger_spawn_egg", HybridAquaticEntityTypes.MAUVE_STINGER, 0x633063, 0xbc787a)
    val CROWN_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("crown_jellyfish_spawn_egg", HybridAquaticEntityTypes.CROWN_JELLYFISH, 0xa32858, 0x4dc0e8)
    val BIG_RED_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("big_red_jellyfish_spawn_egg", HybridAquaticEntityTypes.BIG_RED_JELLYFISH, 0xf4e5e5, 0xe72e46)
    val COSMIC_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("cosmic_jellyfish_spawn_egg", HybridAquaticEntityTypes.COSMIC_JELLYFISH, 0xe7debb, 0xffd375)
    val FIREWORK_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("firework_jellyfish_spawn_egg", HybridAquaticEntityTypes.FIREWORK_JELLYFISH, 0x6975e8, 0xfc7fb7
    )

    //#endregion

    //#region Crustaceans

    val COCONUT_CRAB_SPAWN_EGG =
        registerSpawnEgg("coconut_crab_spawn_egg", HybridAquaticEntityTypes.COCONUT_CRAB, 0x3e2d25, 0x3c546d)
    val DUNGENESS_CRAB_SPAWN_EGG =
        registerSpawnEgg("dungeness_crab_spawn_egg", HybridAquaticEntityTypes.DUNGENESS_CRAB, 0x81353f, 0xeecfce)
    val CRAYFISH_SPAWN_EGG =
        registerSpawnEgg("crayfish_spawn_egg", HybridAquaticEntityTypes.CRAYFISH, 0x697152, 0x7c4452)
    val FIDDLER_CRAB_SPAWN_EGG =
        registerSpawnEgg("fiddler_crab_spawn_egg", HybridAquaticEntityTypes.FIDDLER_CRAB, 0x80366b, 0xf39949)
    val FLOWER_CRAB_SPAWN_EGG =
        registerSpawnEgg("flower_crab_spawn_egg", HybridAquaticEntityTypes.FLOWER_CRAB, 0x9b8a6e, 0x20a094)
    val DECORATOR_CRAB_SPAWN_EGG =
        registerSpawnEgg("decorator_crab_spawn_egg", HybridAquaticEntityTypes.DECORATOR_CRAB, 0xffb570, 0x314fdd)
    val GHOST_CRAB_SPAWN_EGG =
        registerSpawnEgg("ghost_crab_spawn_egg", HybridAquaticEntityTypes.GHOST_CRAB, 0xf2be69, 0xf5fcd9)
    val HERMIT_CRAB_SPAWN_EGG =
        registerSpawnEgg("hermit_crab_spawn_egg", HybridAquaticEntityTypes.HERMIT_CRAB, 0xe97b13, 0xf2a65e)
    val HORSESHOE_CRAB_SPAWN_EGG =
        registerSpawnEgg("horseshoe_crab_spawn_egg", HybridAquaticEntityTypes.HORSESHOE_CRAB, 0x6e6b55, 0x403b31)
    val LIGHTFOOT_CRAB_SPAWN_EGG =
        registerSpawnEgg("lightfoot_crab_spawn_egg", HybridAquaticEntityTypes.LIGHTFOOT_CRAB, 0xb0305c, 0xff8c41)
    val LOBSTER_SPAWN_EGG =
        registerSpawnEgg("lobster_spawn_egg", HybridAquaticEntityTypes.LOBSTER, 0x421b2f, 0x8a4836)
    val SHRIMP_SPAWN_EGG =
        registerSpawnEgg("shrimp_spawn_egg", HybridAquaticEntityTypes.SHRIMP, 0xeb564b, 0xff9166)
    val VAMPIRE_CRAB_SPAWN_EGG =
        registerSpawnEgg("vampire_crab_spawn_egg", HybridAquaticEntityTypes.VAMPIRE_CRAB, 0x322947, 0x752053)

    //#endregion

    //#region Deep Sea Crustaceans

    val GIANT_ISOPOD_SPAWN_EGG =
        registerSpawnEgg("giant_isopod_spawn_egg", HybridAquaticEntityTypes.GIANT_ISOPOD, 0xe6d3d6, 0x3c2236)
    val SPIDER_CRAB_SPAWN_EGG =
        registerSpawnEgg("spider_crab_spawn_egg", HybridAquaticEntityTypes.SPIDER_CRAB, 0x9d3e41, 0xc6836f)
    val YETI_CRAB_SPAWN_EGG =
        registerSpawnEgg("yeti_crab_spawn_egg", HybridAquaticEntityTypes.YETI_CRAB, 0xfff4dd, 0xffd16b)

    //#endregion

    //#region Miniboss

    val KARKINOS_SPAWN_EGG =
        registerSpawnEgg("karkinos_spawn_egg", HybridAquaticEntityTypes.KARKINOS, 0x852c2a, 0x3d1031)
    val KARCINOGEN_SPAWN_EGG =
        registerSpawnEgg("karcinogen_spawn_egg", HybridAquaticEntityTypes.KARCINOGEN, 0x852c2a, 0x3d1031)
    val KARCINOMA_SPAWN_EGG =
        registerSpawnEgg("karcinoma_spawn_egg", HybridAquaticEntityTypes.KARCINOMA, 0x852c2a, 0x3d1031)

    //#endregion

    //#region Critters

    val SEA_SLUG_SPAWN_EGG =
        registerSpawnEgg("sea_slug_spawn_egg", HybridAquaticEntityTypes.SEA_SLUG, 0xf7be47, 0xb853a3)
    val SCALYFOOT_SNAIL_SPAWN_EGG =
        registerSpawnEgg("scalyfoot_snail_spawn_egg", HybridAquaticEntityTypes.SCALYFOOT_SNAIL, 0xf7be47, 0xb853a3)
    val SEA_CUCUMBER_SPAWN_EGG =
        registerSpawnEgg("sea_cucumber_spawn_egg", HybridAquaticEntityTypes.SEA_CUCUMBER, 0x225b6d, 0x0c2627)
    val SEA_URCHIN_SPAWN_EGG =
        registerSpawnEgg("sea_urchin_spawn_egg", HybridAquaticEntityTypes.SEA_URCHIN, 0x994066, 0x41142c)
    val STARFISH_SPAWN_EGG =
        registerSpawnEgg("starfish_spawn_egg", HybridAquaticEntityTypes.STARFISH, 0x994066, 0x592645)
    val SEA_ANGEL_SPAWN_EGG =
        registerSpawnEgg("sea_angel_spawn_egg", HybridAquaticEntityTypes.SEA_ANGEL, 0xc6d5f9, 0xf38135)

    //#endregion

    //#region Sharks

    val BASKING_SHARK_SPAWN_EGG =
        registerSpawnEgg("basking_shark_spawn_egg", HybridAquaticEntityTypes.BASKING_SHARK, 0x6a6558, 0xb5b3a6)
    val BULL_SHARK_SPAWN_EGG =
        registerSpawnEgg("bull_shark_spawn_egg", HybridAquaticEntityTypes.BULL_SHARK, 0x5d6b7a, 0xb4c1c6)
    val FRILLED_SHARK_SPAWN_EGG =
        registerSpawnEgg("frilled_shark_spawn_egg", HybridAquaticEntityTypes.FRILLED_SHARK, 0x5a4d50, 0x3a2f31)
    val LANTERN_SHARK_SPAWN_EGG =
        registerSpawnEgg("lantern_shark_spawn_egg", HybridAquaticEntityTypes.LANTERN_SHARK, 0x543f46, 0x84d5fe)
    val GREAT_WHITE_SHARK_SPAWN_EGG =
        registerSpawnEgg("great_white_shark_spawn_egg", HybridAquaticEntityTypes.GREAT_WHITE_SHARK, 0x5e6e7d, 0xf3f3f8)
    val HAMMERHEAD_SHARK_SPAWN_EGG =
        registerSpawnEgg("hammerhead_shark_spawn_egg", HybridAquaticEntityTypes.HAMMERHEAD_SHARK, 0x78909a, 0xd7e1dd)
    val HOUND_SHARK_SHARK_SPAWN_EGG =
        registerSpawnEgg("hound_shark_spawn_egg", HybridAquaticEntityTypes.HOUND_SHARK, 0xa18469, 0x5e453a)
    val THRESHER_SHARK_SPAWN_EGG =
        registerSpawnEgg("thresher_shark_spawn_egg", HybridAquaticEntityTypes.THRESHER_SHARK, 0x5591af, 0xd7e1dd)
    val TIGER_SHARK_SPAWN_EGG =
        registerSpawnEgg("sand_tiger_shark_spawn_egg", HybridAquaticEntityTypes.SAND_TIGER_SHARK, 0xb79167, 0xf0f3e6)
    val WHALE_SHARK_SPAWN_EGG =
        registerSpawnEgg("whale_shark_spawn_egg", HybridAquaticEntityTypes.WHALE_SHARK, 0x4c6d98, 0xeff0f4)

    //#endregion

    //#region Mammals
    val OTTER_SPAWN_EGG =
        registerSpawnEgg("otter_spawn_egg", HybridAquaticEntityTypes.OTTER, 0x60352f, 0xeebf80)

    val DUGONG_SPAWN_EGG =
        registerSpawnEgg("dugong_spawn_egg", HybridAquaticEntityTypes.DUGONG, 0x60352f, 0xeebf80)

    val ORCA_SPAWN_EGG =
        registerSpawnEgg("orca_spawn_egg", HybridAquaticEntityTypes.ORCA, 0x60352f, 0xeebf80)

    //#endregion

    //#endregion

    fun register(id: String, item: Supplier<Item>): Supplier<Item> {
        return CommonClass.ITEMS.register(id, item)
    }

    private fun <T : Mob> registerSpawnEgg(
        id: String,
        type: Supplier<EntityType<T>>,
        primaryColor: Int,
        secondaryColor: Int
    ): Supplier<SpawnEggItem> {
        return PLATFORM.registerSpawnEggItem(id, { type.get() }, primaryColor, secondaryColor)

    }

    fun registerBlockItem(id: String, block: Supplier<Block>): Supplier<Item> {
        return register(id) { BlockItem(block.get(), Item.Properties()) }
    }

    private fun registerPlaceableInWaterBlockItem(id: String, block: Supplier<Block>): Supplier<Item> {
        return register(id) { PlaceableInWaterItem(block.get(), Item.Properties()) }
    }

    private fun registerVerticallyAttachable(
        id: String,
        standingBlock: Supplier<Block>,
        wallBlock: Supplier<Block>,
        direction: Direction = Direction.DOWN
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
