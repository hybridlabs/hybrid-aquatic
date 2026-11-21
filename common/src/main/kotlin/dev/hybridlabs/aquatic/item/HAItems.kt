@file:Suppress("unused", "SameParameterValue")

package dev.hybridlabs.aquatic.item

import net.minecraft.core.component.DataComponents

import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect

import net.minecraft.world.item.component.Consumable

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.item.armor.*
import dev.hybridlabs.aquatic.item.coral.*
import dev.hybridlabs.aquatic.item.cosmetic.*
import dev.hybridlabs.aquatic.item.seashell.*
import dev.hybridlabs.aquatic.platform.Services.PLATFORM
import dev.hybridlabs.aquatic.tag.HAInstrumentTags
import dev.hybridlabs.hapi.item.HAPIToolMaterials
import net.minecraft.core.Direction
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.*
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.world.level.block.Block
import java.util.function.Supplier

object HAItems {
    //#region Armor
    //#region Diving Armor
    val DIVING_HELMET = register(
        "diving_helmet"
    ) {
        DivingArmorItem(
            ArmorType.HELMET,
            armorProperties(ArmorType.HELMET, 15)
        )
    }

    val DIVING_SUIT = register(
        "diving_suit"
    ) {
        DivingArmorItem(
            ArmorType.CHESTPLATE,
            armorProperties(ArmorType.CHESTPLATE, 15)
        )
    }

    val DIVING_LEGGINGS = register(
        "diving_leggings"
    ) {
        DivingArmorItem(
            ArmorType.LEGGINGS,
            armorProperties(ArmorType.LEGGINGS, 15)
        )
    }

    val DIVING_BOOTS = register(
        "diving_boots"
    ) {
        DivingArmorItem(
            ArmorType.BOOTS,
            armorProperties(ArmorType.BOOTS, 15)
        )
    }
    //#endregion

    //#region Reinforced Diving Armor
    val REINFORCED_DIVING_HELMET = register(
        "reinforced_diving_helmet"
    ) {
        ReinforcedDivingArmorItem(
            ArmorType.HELMET,
            armorProperties(ArmorType.HELMET, 25)
        )
    }

    val REINFORCED_DIVING_SUIT = register(
        "reinforced_diving_suit"
    ) {
        ReinforcedDivingArmorItem(
            ArmorType.CHESTPLATE,
            armorProperties(ArmorType.CHESTPLATE, 25)
        )
    }

    val REINFORCED_DIVING_LEGGINGS = register(
        "reinforced_diving_leggings"
    ) {
        ReinforcedDivingArmorItem(
            ArmorType.LEGGINGS,
            armorProperties(ArmorType.LEGGINGS, 25)
        )
    }

    val REINFORCED_DIVING_BOOTS = register(
        "reinforced_diving_boots"
    ) {
        ReinforcedDivingArmorItem(
            ArmorType.BOOTS,
            armorProperties(ArmorType.BOOTS, 25)
        )
    }
    //#endregion

    //#region Glowing Diving Armor
    val GLOWING_DIVING_HELMET = register(
        "glowing_diving_helmet"
    ) {
        GlowingDivingArmorItem(
            ArmorType.HELMET,
            armorProperties(ArmorType.HELMET, 15)
        )
    }

    val GLOWING_DIVING_SUIT = register(
        "glowing_diving_suit"
    ) {
        GlowingDivingArmorItem(
            ArmorType.CHESTPLATE,
            armorProperties(ArmorType.CHESTPLATE, 15)
        )
    }

    val GLOWING_DIVING_LEGGINGS = register(
        "glowing_diving_leggings"
    ) {
        GlowingDivingArmorItem(
            ArmorType.LEGGINGS,
            armorProperties(ArmorType.LEGGINGS, 15)
        )
    }

    val GLOWING_DIVING_BOOTS = register(
        "glowing_diving_boots"
    ) {
        GlowingDivingArmorItem(
            ArmorType.BOOTS,
            armorProperties(ArmorType.BOOTS, 15)
        )
    }
    //#endregion

    //#region Nautilus Armor
    val NAUTILUS_HELMET = register(
        "nautilus_helmet"
    ) {
        SeashellArmorItem(
            ArmorType.HELMET,
            armorProperties(ArmorType.HELMET, 15)
        )
    }

    val NAUTILUS_PAULDRONS = register(
        "nautilus_pauldrons"
    ) {
        SeashellArmorItem(
            ArmorType.CHESTPLATE,
            armorProperties(ArmorType.CHESTPLATE, 15)
        )
    }
    //#endregion

    //#region Turtle Armor
    val TURTLE_CHESTPLATE = register(
        "turtle_chestplate"
    ) {
        TurtleArmorItem(
            ArmorType.CHESTPLATE,
            armorProperties(ArmorType.CHESTPLATE, 25)
        )
    }
    //#endregion

    //#region Cosmetics
    val MANGLERFISH_LURE = register(
        "manglerfish_lure"
    ) {
        ManglerfishArmorItem(
            ArmorType.HELMET,
            armorProperties(ArmorType.HELMET, 15)
        )
    }

    val MANGLERFISH_FIN = register(
        "manglerfish_fin"
    ) {
        ManglerfishArmorItem(
            ArmorType.CHESTPLATE,
            armorProperties(ArmorType.CHESTPLATE, 15)
        )
    }

    val EEL_SCARF = register(
        "eel_scarf"
    ) {
        EelArmorItem(
            ArmorType.CHESTPLATE,
            armorProperties(ArmorType.CHESTPLATE, 15)
        )
    }

    val STRIPED_EEL_SCARF = register(
        "striped_eel_scarf"
    ) {
        StripedEelScarfItem(
            ArmorType.CHESTPLATE,
            armorProperties(ArmorType.CHESTPLATE, 15)
        )
    }

    val PINK_HATXOLOTL = register(
        "pink_hatxolotl"
    ) {
        PinkHatxolotlArmorItem(
            ArmorType.HELMET,
            armorProperties(ArmorType.HELMET, 15)
        )
    }

    val BROWN_HATXOLOTL = register(
        "brown_hatxolotl"
    ) {
        BrownHatxolotlArmorItem(
            ArmorType.HELMET,
            armorProperties(ArmorType.HELMET, 15)
        )
    }

    val GOLD_HATXOLOTL = register(
        "gold_hatxolotl"
    ) {
        GoldHatxolotlArmorItem(
            ArmorType.HELMET,
            armorProperties(ArmorType.HELMET, 15)
        )
    }

    val BLUE_HATXOLOTL = register(
        "blue_hatxolotl"
    ) {
        BlueHatxolotlArmorItem(
            ArmorType.HELMET,
            armorProperties(ArmorType.HELMET, 15)
        )
    }

    val CYAN_HATXOLOTL = register(
        "cyan_hatxolotl"
    ) {
        CyanHatxolotlArmorItem(
            ArmorType.HELMET,
            armorProperties(ArmorType.HELMET, 15)
        )
    }

    val MOON_JELLYFISH_HAT = register(
        "moon_jellyfish_hat"
    ) {
        MoonJellyfishArmorItem(
            ArmorType.HELMET,
            armorProperties(ArmorType.HELMET, 15)
        )
    }
    //#endregion
    //#endregion

    //#region Tools - Weapons - Hooks
    val DIVING_WEIGHT = register("diving_weight") { DivingWeightItem(HAItemProperties.props()) }
    val KARKINOS_CLAW = register("karkinos_claw") {
        KarkinosClawItem(HAItemProperties.props().stacksTo(1).attributes(KarkinosClawItem.ATTRIBUTES))
    }
    val OMINOUS_CONCH = register("ominous_conch") { OminousConchItem(HAItemProperties.props().stacksTo(1)) }
    val ARGONAUT = register("argonaut") { ArgonautItem(HAItemProperties.props().stacksTo(1)) }

    val BARBED_HOOK = register("barbed_hook") { HookItem(HAItemProperties.props().durability(16)) }
    val GLOWING_HOOK = register("glowing_hook") { HookItem(HAItemProperties.props().durability(16)) }
    val MAGNETIC_HOOK = register("magnetic_hook") { HookItem(HAItemProperties.props().durability(8)) }
    val CREEPERMAGNET_HOOK = register("creepermagnet_hook") { HookItem(HAItemProperties.props().durability(1)) }
    val OMINOUS_HOOK = register("ominous_hook") { HookItem(HAItemProperties.props().durability(1)) }

    //#region Seashell Set
    val SEASHELL_SPEAR = register(
        "seashell_spear"
    ) {
        SeashellSpearItem(
            HAItemProperties.props().sword(HAPIToolMaterials.SEASHELL, 2f, -2.4f)
        )
    }

    val SEASHELL_PICKAXE = register(
        "seashell_pickaxe"
    ) {
        SeashellPickaxeItem(
            HAItemProperties.props().pickaxe(HAPIToolMaterials.SEASHELL, 1.0f, -2.8f)
        )
    }

    val SEASHELL_AXE = register(
        "seashell_axe"
    ) {
        SeashellAxeItem(
            HAItemProperties.props().axe(HAPIToolMaterials.SEASHELL, 7.0f, -3.2f)
        )
    }

    val SEASHELL_SHOVEL = register(
        "seashell_shovel"
    ) {
        SeashellShovelItem(
            HAItemProperties.props().shovel(HAPIToolMaterials.SEASHELL, 1.5f, -3.0f)
        )
    }

    val SEASHELL_HOE = register(
        "seashell_hoe"
    ) {
        SeashellHoeItem(
            HAItemProperties.props().hoe(HAPIToolMaterials.SEASHELL, 1.0f, -2.0f)
        )
    }
    //#endregion

    //#region Coral Set
    val CORAL_BLADE = register(
        "coral_blade"
    ) {
        CoralBladeItem(
            HAItemProperties.props().sword(HAPIToolMaterials.CORAL, 3f, -2.4f)
        )
    }

    val CORAL_PICKAXE = register(
        "coral_pickaxe"
    ) {
        CoralPickaxeItem(
            HAItemProperties.props().pickaxe(HAPIToolMaterials.CORAL, 1.0f, -2.8f)
        )
    }

    val CORAL_AXE = register(
        "coral_axe"
    ) {
        CoralAxeItem(
            HAItemProperties.props().axe(HAPIToolMaterials.CORAL, 1.0f, -3.2f)
        )
    }

    val CORAL_SHOVEL = register(
        "coral_shovel"
    ) {
        CoralShovelItem(
            HAItemProperties.props().shovel(HAPIToolMaterials.CORAL, 1.5f, -3.0f)
        )
    }

    val CORAL_HOE = register(
        "coral_hoe"
    ) {
        CoralHoeItem(
            HAItemProperties.props().hoe(HAPIToolMaterials.CORAL, 1.0f, -2.0f)
        )
    }
    //#endregion
    //#endregion

    //#region Blocks

    //#region Nature Blocks

    val ANEMONE = register("anemone") {
        PLATFORM.createBlockItem(HABlocks.ANEMONE.get(), HAItemProperties.blockProps())
    }

    val STRAWBERRY_ANEMONE = register("strawberry_anemone") {
        PLATFORM.createBlockItem(HABlocks.STRAWBERRY_ANEMONE.get(), HAItemProperties.blockProps())
    }
    val GIANT_GREEN_ANEMONE = register("giant_green_anemone") {
        PLATFORM.createBlockItem(HABlocks.GIANT_GREEN_ANEMONE.get(), HAItemProperties.blockProps())
    }

    val GIANT_CLAM = registerBlockItem("giant_clam") { HABlocks.GIANT_CLAM.get() }
    val OYSTER_BLOCK = registerBlockItem("oyster_block") { HABlocks.OYSTER.get() }
    val SARGASSUM = registerBlockItem("sargassum") { HABlocks.SARGASSUM.get() }
    val BULL_KELP = registerBlockItem("bull_kelp") { HABlocks.BULL_KELP.get() }
    val DELESSERIA = registerBlockItem("delesseria") { HABlocks.DELESSERIA.get() }
    val FLOATING_SARGASSUM = registerPlaceableInWaterBlockItem("floating_sargassum") { HABlocks.FLOATING_SARGASSUM.get() }
    val FLOATING_PETALS = registerPlaceableInWaterBlockItem("floating_petals") { HABlocks.FLOATING_PETALS.get() }
    val FLOATING_LEAVES = registerPlaceableInWaterBlockItem("floating_leaves") { HABlocks.FLOATING_LEAVES.get() }
    val FLOATING_LEAF_LITTER = registerPlaceableInWaterBlockItem("floating_leaf_litter") { HABlocks.FLOATING_LEAF_LITTER.get() }
    val DUCKWEED = registerPlaceableInWaterBlockItem("duckweed") { HABlocks.DUCKWEED.get() }
    val WATER_LETTUCE = registerPlaceableInWaterBlockItem("water_lettuce") { HABlocks.WATER_LETTUCE.get() }
    val WATER_HYACINTH = registerPlaceableInWaterBlockItem("water_hyacinth") { HABlocks.WATER_HYACINTH.get() }
    val JUNGLE_LILY_PAD = registerPlaceableInWaterBlockItem("jungle_lily_pad") { HABlocks.JUNGLE_LILY_PAD.get() }
    val SHORT_RED_ALGAE = registerBlockItem("short_red_algae") { HABlocks.SHORT_RED_ALGAE.get() }
    val RED_ALGAE = registerBlockItem("red_algae") { HABlocks.RED_ALGAE.get() }
    val SEA_LETTUCE = registerBlockItem("sea_lettuce") { HABlocks.SEA_LETTUCE.get() }
    val HYDROTHERMAL_VENT = registerBlockItem("hydrothermal_vent") { HABlocks.THERMAL_VENT.get() }
    val GIANT_THERMAL_VENT = registerBlockItem("giant_thermal_vent") { HABlocks.GIANT_THERMAL_VENT.get() }
    val TUBE_WORM = registerBlockItem("tube_worm") { HABlocks.TUBE_WORM.get() }
    val BONE_WORMS = registerBlockItem("bone_worms") { HABlocks.BONE_WORMS.get() }
    val TUBE_SPONGE = registerBlockItem("tube_sponge") { HABlocks.TUBE_SPONGE.get() }
    val GLASS_SPONGE = registerBlockItem("glass_sponge") { HABlocks.GLASS_SPONGE.get() }
    val HARP_SPONGE = registerBlockItem("harp_sponge") { HABlocks.HARP_SPONGE.get() }
    val PING_PONG_SPONGE = registerBlockItem("ping_pong_sponge") { HABlocks.PING_PONG_SPONGE.get() }

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

    val ZIGZAG_CORAL_BLOCK = registerBlockItem("zigzag_coral_block") { HABlocks.ZIGZAG_CORAL_BLOCK.get() }
    val DEAD_ZIGZAG_CORAL_BLOCK =
        registerBlockItem("dead_zigzag_coral_block") { HABlocks.DEAD_ZIGZAG_CORAL_BLOCK.get() }
    val ZIGZAG_CORAL = registerBlockItem("zigzag_coral") { HABlocks.ZIGZAG_CORAL.get() }
    val DEAD_ZIGZAG_CORAL = registerBlockItem("dead_zigzag_coral") { HABlocks.DEAD_ZIGZAG_CORAL.get() }
    val ZIGZAG_CORAL_FAN = registerVerticallyAttachable(
        "zigzag_coral_fan",
        HABlocks.ZIGZAG_CORAL_FAN,
        HABlocks.ZIGZAG_CORAL_WALL_FAN
    )
    val DEAD_ZIGZAG_CORAL_FAN = registerVerticallyAttachable(
        "dead_zigzag_coral_fan",
        HABlocks.DEAD_ZIGZAG_CORAL_FAN,
        HABlocks.DEAD_ZIGZAG_CORAL_WALL_FAN
    )

    val BLEACHED_ZIGZAG_CORAL_BLOCK =
        registerBlockItem("bleached_zigzag_coral_block") { HABlocks.BLEACHED_ZIGZAG_CORAL_BLOCK.get() }
    val BLEACHED_ZIGZAG_CORAL =
        registerBlockItem("bleached_zigzag_coral") { HABlocks.BLEACHED_ZIGZAG_CORAL.get() }
    val BLEACHED_ZIGZAG_CORAL_FAN = registerVerticallyAttachable(
        "bleached_zigzag_coral_fan",
        HABlocks.BLEACHED_ZIGZAG_CORAL_FAN,
        HABlocks.BLEACHED_ZIGZAG_CORAL_WALL_FAN
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

    val BAMBOO_CORAL_BLOCK =
        registerBlockItem("bamboo_coral_block") { HABlocks.BAMBOO_CORAL_BLOCK.get() }
    val DEAD_BAMBOO_CORAL_BLOCK =
        registerBlockItem("dead_bamboo_coral_block") { HABlocks.DEAD_BAMBOO_CORAL_BLOCK.get() }
    val BLEACHED_BAMBOO_CORAL_BLOCK =
        registerBlockItem("bleached_bamboo_coral_block") { HABlocks.BLEACHED_BAMBOO_CORAL_BLOCK.get() }
    val BAMBOO_CORAL = registerBlockItem("bamboo_coral") { HABlocks.BAMBOO_CORAL.get() }
    val DEAD_BAMBOO_CORAL = registerBlockItem("dead_bamboo_coral") { HABlocks.DEAD_BAMBOO_CORAL.get() }
    val BLEACHED_BAMBOO_CORAL =
        registerBlockItem("bleached_bamboo_coral") { HABlocks.BLEACHED_BAMBOO_CORAL.get() }
    val BAMBOO_CORAL_FAN = registerVerticallyAttachable(
        "bamboo_coral_fan",
        HABlocks.BAMBOO_CORAL_FAN,
        HABlocks.BAMBOO_CORAL_WALL_FAN
    )
    val DEAD_BAMBOO_CORAL_FAN = registerVerticallyAttachable(
        "dead_bamboo_coral_fan",
        HABlocks.DEAD_BAMBOO_CORAL_FAN,
        HABlocks.DEAD_BAMBOO_CORAL_WALL_FAN
    )

    val BLEACHED_BAMBOO_CORAL_FAN = registerVerticallyAttachable(
        "bleached_bamboo_coral_fan",
        HABlocks.BLEACHED_BAMBOO_CORAL_FAN,
        HABlocks.BLEACHED_BAMBOO_CORAL_WALL_FAN
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

    //#region Artificial Blocks
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
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle") { MessageInABottleItem(HAItemProperties.blockProps(HABlocks.MESSAGE_IN_A_BOTTLE.get())) }
    //#endregion

    //#region Nature Blocks
    val GRASSY_SAND = registerBlockItem("grassy_sand") { HABlocks.GRASSY_SAND.get() }
    val AERATED_SAND = registerBlockItem("aerated_sand") { HABlocks.AERATED_SAND.get() }
    val BUBBLE_GEYSER = registerBlockItem("bubble_geyser") { HABlocks.BUBBLE_GEYSER.get() }

    val WHITE_SAND = registerBlockItem("white_sand") { HABlocks.WHITE_SAND.get() }
    val CHISELED_WHITE_SANDSTONE = registerBlockItem("chiseled_white_sandstone") { HABlocks.CHISELED_WHITE_SANDSTONE.get() }
    val WHITE_SANDSTONE = registerBlockItem("white_sandstone") { HABlocks.WHITE_SANDSTONE.get() }
    val WHITE_SANDSTONE_STAIRS = register("white_sandstone_stairs") { BlockItem(HABlocks.WHITE_SANDSTONE_STAIRS.get(), HAItemProperties.blockProps()) }
    val WHITE_SANDSTONE_SLAB = register("white_sandstone_slab") { BlockItem(HABlocks.WHITE_SANDSTONE_SLAB.get(), HAItemProperties.blockProps()) }
    val WHITE_SANDSTONE_WALL = register("white_sandstone_wall") { BlockItem(HABlocks.WHITE_SANDSTONE_WALL.get(), HAItemProperties.blockProps()) }
    val SMOOTH_WHITE_SANDSTONE = registerBlockItem("smooth_white_sandstone") { HABlocks.SMOOTH_WHITE_SANDSTONE.get() }
    val SMOOTH_WHITE_SANDSTONE_STAIRS = register("smooth_white_sandstone_stairs") { BlockItem(HABlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.get(), HAItemProperties.blockProps()) }
    val SMOOTH_WHITE_SANDSTONE_SLAB = register("smooth_white_sandstone_slab") { BlockItem(HABlocks.SMOOTH_WHITE_SANDSTONE_SLAB.get(), HAItemProperties.blockProps()) }
    val CUT_WHITE_SANDSTONE = registerBlockItem("cut_white_sandstone") { HABlocks.CUT_WHITE_SANDSTONE.get() }
    val CUT_WHITE_SANDSTONE_SLAB = register("cut_white_sandstone_slab") { BlockItem(HABlocks.CUT_WHITE_SANDSTONE_SLAB.get(), HAItemProperties.blockProps()) }

    val BONE_STAIRS = register("bone_stairs") { BlockItem(HABlocks.BONE_STAIRS.get(), HAItemProperties.blockProps()) }
    val BONE_SLAB = register("bone_slab") { BlockItem(HABlocks.BONE_SLAB.get(), HAItemProperties.blockProps()) }
    val BONE_WALL = register("bone_wall") { BlockItem(HABlocks.BONE_WALL.get(), HAItemProperties.blockProps()) }
    val BONE_FENCE = register("bone_fence") { BlockItem(HABlocks.BONE_FENCE.get(), HAItemProperties.blockProps()) }

    val SUSPICIOUS_RED_SAND = registerBlockItem("suspicious_red_sand") { HABlocks.SUSPICIOUS_RED_SAND.get() }
    val CORALSTONE = registerBlockItem("coralstone") { HABlocks.CORALSTONE.get() }
    val SHORESTONE = registerBlockItem("shorestone") { HABlocks.SHORESTONE.get() }
    val BARNACLE_SHORESTONE = registerBlockItem("barnacle_shorestone") { HABlocks.BARNACLE_SHORESTONE.get() }
    val MARINE_SNOW = registerBlockItem("marine_snow") { HABlocks.MARINE_SNOW.get() }

    val RED_BRINESTONE = registerBlockItem("red_brinestone") { HABlocks.RED_BRINESTONE.get() }
    val RED_BRINESTONE_STAIRS = registerBlockItem("red_brinestone_stairs") { HABlocks.RED_BRINESTONE_STAIRS.get() }
    val RED_BRINESTONE_SLAB = registerBlockItem("red_brinestone_slab") { HABlocks.RED_BRINESTONE_SLAB.get() }
    val RED_BRINESTONE_WALL = registerBlockItem("red_brinestone_wall") { HABlocks.RED_BRINESTONE_WALL.get() }

    val RED_BRINESTONE_BRICKS = registerBlockItem("red_brinestone_bricks") { HABlocks.RED_BRINESTONE_BRICKS.get() }
    val RED_BRINESTONE_BRICK_STAIRS = registerBlockItem("red_brinestone_brick_stairs") { HABlocks.RED_BRINESTONE_BRICK_STAIRS.get() }
    val RED_BRINESTONE_BRICK_SLAB = registerBlockItem("red_brinestone_brick_slab") { HABlocks.RED_BRINESTONE_BRICK_SLAB.get() }
    val RED_BRINESTONE_BRICK_WALL = registerBlockItem("red_brinestone_brick_wall") { HABlocks.RED_BRINESTONE_BRICK_WALL.get() }

    val CHISELED_RED_BRINESTONE = registerBlockItem("chiseled_red_brinestone") { HABlocks.CHISELED_RED_BRINESTONE.get() }
    val POLISHED_RED_BRINESTONE = registerBlockItem("polished_red_brinestone") { HABlocks.POLISHED_RED_BRINESTONE.get() }
    val POLISHED_RED_BRINESTONE_STAIRS = registerBlockItem("polished_red_brinestone_stairs") { HABlocks.POLISHED_RED_BRINESTONE_STAIRS.get() }
    val POLISHED_RED_BRINESTONE_SLAB = registerBlockItem("polished_red_brinestone_slab") { HABlocks.POLISHED_RED_BRINESTONE_SLAB.get() }

    val ORANGE_BRINESTONE = registerBlockItem("orange_brinestone") { HABlocks.ORANGE_BRINESTONE.get() }
    val ORANGE_BRINESTONE_STAIRS = registerBlockItem("orange_brinestone_stairs") { HABlocks.ORANGE_BRINESTONE_STAIRS.get() }
    val ORANGE_BRINESTONE_SLAB = registerBlockItem("orange_brinestone_slab") { HABlocks.ORANGE_BRINESTONE_SLAB.get() }
    val ORANGE_BRINESTONE_WALL = registerBlockItem("orange_brinestone_wall") { HABlocks.ORANGE_BRINESTONE_WALL.get() }

    val ORANGE_BRINESTONE_BRICKS = registerBlockItem("orange_brinestone_bricks") { HABlocks.ORANGE_BRINESTONE_BRICKS.get() }
    val ORANGE_BRINESTONE_BRICK_STAIRS = registerBlockItem("orange_brinestone_brick_stairs") { HABlocks.ORANGE_BRINESTONE_BRICK_STAIRS.get() }
    val ORANGE_BRINESTONE_BRICK_SLAB = registerBlockItem("orange_brinestone_brick_slab") { HABlocks.ORANGE_BRINESTONE_BRICK_SLAB.get() }
    val ORANGE_BRINESTONE_BRICK_WALL = registerBlockItem("orange_brinestone_brick_wall") { HABlocks.ORANGE_BRINESTONE_BRICK_WALL.get() }

    val CHISELED_ORANGE_BRINESTONE = registerBlockItem("chiseled_orange_brinestone") { HABlocks.CHISELED_ORANGE_BRINESTONE.get() }
    val POLISHED_ORANGE_BRINESTONE = registerBlockItem("polished_orange_brinestone") { HABlocks.POLISHED_ORANGE_BRINESTONE.get() }
    val POLISHED_ORANGE_BRINESTONE_STAIRS = registerBlockItem("polished_orange_brinestone_stairs") { HABlocks.POLISHED_ORANGE_BRINESTONE_STAIRS.get() }
    val POLISHED_ORANGE_BRINESTONE_SLAB = registerBlockItem("polished_orange_brinestone_slab") { HABlocks.POLISHED_ORANGE_BRINESTONE_SLAB.get() }

    val YELLOW_BRINESTONE = registerBlockItem("yellow_brinestone") { HABlocks.YELLOW_BRINESTONE.get() }
    val YELLOW_BRINESTONE_STAIRS = registerBlockItem("yellow_brinestone_stairs") { HABlocks.YELLOW_BRINESTONE_STAIRS.get() }
    val YELLOW_BRINESTONE_SLAB = registerBlockItem("yellow_brinestone_slab") { HABlocks.YELLOW_BRINESTONE_SLAB.get() }
    val YELLOW_BRINESTONE_WALL = registerBlockItem("yellow_brinestone_wall") { HABlocks.YELLOW_BRINESTONE_WALL.get() }

    val YELLOW_BRINESTONE_BRICKS = registerBlockItem("yellow_brinestone_bricks") { HABlocks.YELLOW_BRINESTONE_BRICKS.get() }
    val YELLOW_BRINESTONE_BRICK_STAIRS = registerBlockItem("yellow_brinestone_brick_stairs") { HABlocks.YELLOW_BRINESTONE_BRICK_STAIRS.get() }
    val YELLOW_BRINESTONE_BRICK_SLAB = registerBlockItem("yellow_brinestone_brick_slab") { HABlocks.YELLOW_BRINESTONE_BRICK_SLAB.get() }
    val YELLOW_BRINESTONE_BRICK_WALL = registerBlockItem("yellow_brinestone_brick_wall") { HABlocks.YELLOW_BRINESTONE_BRICK_WALL.get() }

    val CHISELED_YELLOW_BRINESTONE = registerBlockItem("chiseled_yellow_brinestone") { HABlocks.CHISELED_YELLOW_BRINESTONE.get() }
    val POLISHED_YELLOW_BRINESTONE = registerBlockItem("polished_yellow_brinestone") { HABlocks.POLISHED_YELLOW_BRINESTONE.get() }
    val POLISHED_YELLOW_BRINESTONE_STAIRS = registerBlockItem("polished_yellow_brinestone_stairs") { HABlocks.POLISHED_YELLOW_BRINESTONE_STAIRS.get() }
    val POLISHED_YELLOW_BRINESTONE_SLAB = registerBlockItem("polished_yellow_brinestone_slab") { HABlocks.POLISHED_YELLOW_BRINESTONE_SLAB.get() }

    val SCHIST = registerBlockItem("schist") { HABlocks.SCHIST.get() }
    val SCHIST_STAIRS = registerBlockItem("schist_stairs") { HABlocks.SCHIST_STAIRS.get() }
    val SCHIST_SLAB = registerBlockItem("schist_slab") { HABlocks.SCHIST_SLAB.get() }
    val SCHIST_WALL = registerBlockItem("schist_wall") { HABlocks.SCHIST_WALL.get() }

    val SCHIST_BRICKS = registerBlockItem("schist_bricks") { HABlocks.SCHIST_BRICKS.get() }
    val SCHIST_BRICK_STAIRS = registerBlockItem("schist_brick_stairs") { HABlocks.SCHIST_BRICK_STAIRS.get() }
    val SCHIST_BRICK_SLAB = registerBlockItem("schist_brick_slab") { HABlocks.SCHIST_BRICK_SLAB.get() }
    val SCHIST_BRICK_WALL = registerBlockItem("schist_brick_wall") { HABlocks.SCHIST_BRICK_WALL.get() }

    val CHISELED_SCHIST = registerBlockItem("chiseled_schist") { HABlocks.CHISELED_SCHIST.get() }
    val POLISHED_SCHIST = registerBlockItem("polished_schist") { HABlocks.POLISHED_SCHIST.get() }
    val POLISHED_SCHIST_STAIRS = registerBlockItem("polished_schist_stairs") { HABlocks.POLISHED_SCHIST_STAIRS.get() }
    val POLISHED_SCHIST_SLAB = registerBlockItem("polished_schist_slab") { HABlocks.POLISHED_SCHIST_SLAB.get() }

    val CHIMNEYSTONE = registerBlockItem("chimneystone") { HABlocks.CHIMNEYSTONE.get() }
    val CHIMNEYSTONE_STAIRS = registerBlockItem("chimneystone_stairs") { HABlocks.CHIMNEYSTONE_STAIRS.get() }
    val CHIMNEYSTONE_SLAB = registerBlockItem("chimneystone_slab") { HABlocks.CHIMNEYSTONE_SLAB.get() }
    val CHIMNEYSTONE_WALL = registerBlockItem("chimneystone_wall") { HABlocks.CHIMNEYSTONE_WALL.get() }

    val CHIMNEYSTONE_BRICKS = registerBlockItem("chimneystone_bricks") { HABlocks.CHIMNEYSTONE_BRICKS.get() }
    val CHIMNEYSTONE_BRICK_STAIRS = registerBlockItem("chimneystone_brick_stairs") { HABlocks.CHIMNEYSTONE_BRICK_STAIRS.get() }
    val CHIMNEYSTONE_BRICK_SLAB = registerBlockItem("chimneystone_brick_slab") { HABlocks.CHIMNEYSTONE_BRICK_SLAB.get() }
    val CHIMNEYSTONE_BRICK_WALL = registerBlockItem("chimneystone_brick_wall") { HABlocks.CHIMNEYSTONE_BRICK_WALL.get() }

    val CHISELED_CHIMNEYSTONE = registerBlockItem("chiseled_chimneystone") { HABlocks.CHISELED_CHIMNEYSTONE.get() }
    val POLISHED_CHIMNEYSTONE = registerBlockItem("polished_chimneystone") { HABlocks.POLISHED_CHIMNEYSTONE.get() }
    val POLISHED_CHIMNEYSTONE_STAIRS = registerBlockItem("polished_chimneystone_stairs") { HABlocks.POLISHED_CHIMNEYSTONE_STAIRS.get() }
    val POLISHED_CHIMNEYSTONE_SLAB = registerBlockItem("polished_chimneystone_slab") { HABlocks.POLISHED_CHIMNEYSTONE_SLAB.get() }
    //#endregion

    //#region Plushies
    val BASKING_SHARK_PLUSHIE =
        register("basking_shark_plushie") { HABlockItem(HABlocks.BASKING_SHARK_PLUSHIE.get(), HAItemProperties.blockProps(), true) }
    val BULL_SHARK_PLUSHIE =
        register("bull_shark_plushie") { HABlockItem(HABlocks.BULL_SHARK_PLUSHIE.get(), HAItemProperties.blockProps(), true) }
    val FRILLED_SHARK_PLUSHIE =
        register("frilled_shark_plushie") { HABlockItem(HABlocks.FRILLED_SHARK_PLUSHIE.get(), HAItemProperties.blockProps(), true) }
    val GREAT_WHITE_SHARK_PLUSHIE =
        register("great_white_shark_plushie") { HABlockItem(HABlocks.GREAT_WHITE_SHARK_PLUSHIE.get(), HAItemProperties.blockProps(), true) }
    val HAMMERHEAD_SHARK_PLUSHIE =
        register("hammerhead_shark_plushie") { HABlockItem(HABlocks.HAMMERHEAD_SHARK_PLUSHIE.get(), HAItemProperties.blockProps(), true) }
    val THRESHER_SHARK_PLUSHIE =
        register("thresher_shark_plushie") { HABlockItem(HABlocks.THRESHER_SHARK_PLUSHIE.get(), HAItemProperties.blockProps(), true) }
    val TIGER_SHARK_PLUSHIE =
        register("tiger_shark_plushie") { HABlockItem(HABlocks.TIGER_SHARK_PLUSHIE.get(), HAItemProperties.blockProps(), true) }
    val WHALE_SHARK_PLUSHIE =
        register("whale_shark_plushie") { HABlockItem(HABlocks.WHALE_SHARK_PLUSHIE.get(), HAItemProperties.blockProps(), true) }
    //#endregion

    //#region Crates
    val CRAB_POT = register("crab_pot") { HABlockItem(HABlocks.CRAB_POT.get(), HAItemProperties.blockProps(), true) }
    val HYBRID_CRATE = register("hybrid_crate") { HABlockItem(HABlocks.HYBRID_CRATE.get(), HAItemProperties.blockProps(), true) }
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
    val GLOWSLIME = register("glowslime") { Item(HAItemProperties.props()) }
    val HAGSLIME = register("hagslime") { Item(HAItemProperties.props()) }
    val SEA_URCHIN_SPINE = register("sea_urchin_spine") { Item(HAItemProperties.props()) }
    val SHARK_TOOTH = register("shark_tooth") { Item(HAItemProperties.props()) }
    val SULFUR = register("sulfur") { Item(HAItemProperties.props()) }
    val CORAL_CHUNK = register("coral_chunk") { Item(HAItemProperties.props()) }
    val PRISMARINE_ROD = register("prismarine_rod") { Item(HAItemProperties.props()) }
    val DIVING_ARMOR_UPGRADE_TEMPLATE = register("diving_armor_upgrade_template") { Item(HAItemProperties.props()) }
    val STARFISH = register("starfish") { StarfishItem(HAItemProperties.props().stacksTo(16)) }
    val PEARL = register("pearl") { Item(HAItemProperties.props()) }
    val BLACK_PEARL = register("black_pearl") { Item(HAItemProperties.props()) }
    val COMICALLY_LARGE_NAUTILUS_SHELL = register("comically_large_nautilus_shell") { Item(HAItemProperties.props()) }
    val CUTTLEBONE = register("cuttlebone") { Item(HAItemProperties.props()) }
    val FISH_FOOD = register("fish_food") { Item(HAItemProperties.props()) }

    //#region Claws
    val LOBSTER_CLAW = register("lobster_claw") { Item(HAItemProperties.props()) }
    val DUNGENESS_CRAB_CLAW = register("dungeness_crab_claw") { Item(HAItemProperties.props()) }
    val FIDDLER_CRAB_CLAW = register("fiddler_crab_claw") { Item(HAItemProperties.props()) }
    val VAMPIRE_CRAB_CLAW = register("vampire_crab_claw") { Item(HAItemProperties.props()) }
    val FLOWER_CRAB_CLAW = register("flower_crab_claw") { Item(HAItemProperties.props()) }
    val GHOST_CRAB_CLAW = register("ghost_crab_claw") { Item(HAItemProperties.props()) }
    val SPIDER_CRAB_CLAW = register("spider_crab_claw") { Item(HAItemProperties.props()) }
    val COCONUT_CRAB_CLAW = register("coconut_crab_claw") { Item(HAItemProperties.props()) }
    val YETI_CRAB_CLAW = register("yeti_crab_claw") { Item(HAItemProperties.props()) }
    val LIGHTFOOT_CRAB_CLAW = register("lightfoot_crab_claw") { Item(HAItemProperties.props()) }
    //#endregion

    //#endregion

    //#region Food
    val UNI = register("uni") {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val RAW_SHRIMP = register(
        "raw_shrimp"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val COOKED_SHRIMP = register(
        "cooked_shrimp"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.5F)
                        .build()
                )
        )
    }

    val COOKED_CLAM = register(
        "cooked_clam"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.5F)
                        .build()
                )
        )
    }

    val CLAM = register(
        "clam"
    ) {
        BlockItem(
            HABlocks.CLAMS.get(),
            HAItemProperties.blockProps(HABlocks.CLAMS.get())
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.5F)
                        .build()
                )
        )
    }

    val COOKED_MUSSEL = register(
        "cooked_mussel"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.5F)
                        .build()
                )
        )
    }

    val MUSSEL = register(
        "mussel"
    ) {
        BlockItem(
            HABlocks.MUSSELS.get(),
            HAItemProperties.blockProps(HABlocks.MUSSELS.get())
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.5F)
                        .build()
                )
        )
    }

    val RAW_CRAYFISH = register(
        "raw_crayfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val COOKED_CRAYFISH = register(
        "cooked_crayfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.5F)
                        .build()
                )
        )
    }

    val RAW_CRAB = register(
        "raw_crab"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val COOKED_CRAB = register(
        "cooked_crab"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.5F)
                        .build()
                )
        )
    }

    val RAW_LOBSTER = register(
        "raw_lobster"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val COOKED_LOBSTER = register(
        "cooked_lobster"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(5)
                        .saturationModifier(0.6F)
                        .build()
                )
        )
    }

    val RAW_LOBSTER_TAIL = register(
        "raw_lobster_tail"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val COOKED_LOBSTER_TAIL = register(
        "cooked_lobster_tail"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(5)
                        .saturationModifier(0.6F)
                        .build()
                )
        )
    }

    val COOKED_FISH_STEAK = register(
        "cooked_fish_steak"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(8)
                        .saturationModifier(0.8F)
                        .build()
                )
        )
    }

    val RAW_FISH_STEAK = register(
        "raw_fish_steak"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationModifier(0.6F)
                        .build()
                )
        )
    }

    val COOKED_FISH_MEAT = register(
        "cooked_fish_meat"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.6F)
                        .build()
                )
        )
    }

    val RAW_FISH_MEAT = register(
        "raw_fish_meat"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val RAW_TENTACLE = register(
        "raw_tentacle"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val COOKED_TENTACLE = register(
        "cooked_tentacle"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.6F)
                        .build()
                )
        )
    }

    val SIRENIAN_STEAK = register(
        "sirenian_steak"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(8)
                        .saturationModifier(0.8F)
                        .build()
                )
        )
    }

    val SIRENIAN_BEEF = register(
        "sirenian_beef"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationModifier(0.6F)
                        .build()
                )
        )
    }

    val MACKEREL = register(
        "mackerel"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val HERRING = register(
        "herring"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val FLYING_FISH = register(
        "flying_fish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val PIRANHA = register(
        "piranha"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val ANGLERFISH = register(
        "anglerfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val BARRELEYE = register(
        "barreleye"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val DRAGONFISH = register(
        "dragonfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val BLOBFISH = register(
        "blobfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val HAGFISH = register(
        "hagfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
                .component(
                    DataComponents.CONSUMABLE,
                    Consumable.builder()
                        .onConsume(ApplyStatusEffectsConsumeEffect(MobEffectInstance(MobEffects.POISON, 600, 0), 1.0f))
                        .build()
                )
        )
    }

    val SURGEONFISH = register(
        "surgeonfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val CLOWNFISH = register(
        "clownfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val JOHN_DORY = register(
        "john_dory"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val DAMSELFISH = register(
        "damselfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val FLASHLIGHT_FISH = register(
        "flashlight_fish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val SQUIRRELFISH = register(
        "squirrelfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val GOURAMI = register(
        "gourami"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val BETTA = register(
        "betta"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val PEARLFISH = register(
        "pearlfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val SNAILFISH = register(
        "snailfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val DISCUS = register(
        "discus"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val DANIO = register(
        "danio"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val TETRA = register(
        "tetra"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val AFRICAN_BUTTERFLYFISH = register(
        "african_butterflyfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val TIGER_BARB = register(
        "tiger_barb"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val CICHLID = register(
        "cichlid"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val BOXFISH = register(
        "boxfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
                .component(
                    DataComponents.CONSUMABLE,
                    Consumable.builder()
                        .onConsume(ApplyStatusEffectsConsumeEffect(MobEffectInstance(MobEffects.POISON, 600, 1), 1.0f))
                        .build()
                )
        )
    }

    val CARP = register(
        "carp"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val GOLDFISH = register(
        "goldfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val SUNFISH = register(
        "sunfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val TROUT = register(
        "trout"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val PLECO = register(
        "pleco"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val SEAHORSE = register(
        "seahorse"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.2F)
                        .build()
                )
        )
    }

    val BLOWFISH = register(
        "blowfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.4F)
                        .build()
                )
                .component(
                    DataComponents.CONSUMABLE,
                    Consumable.builder()
                        .onConsume(ApplyStatusEffectsConsumeEffect(MobEffectInstance(MobEffects.POISON, 600, 1), 1.0f))
                        .build()
                )
        )
    }

    val STONEFISH = register(
        "stonefish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(1)
                        .saturationModifier(0.4F)
                        .build()
                )
                .component(
                    DataComponents.CONSUMABLE,
                    Consumable.builder()
                        .onConsume(ApplyStatusEffectsConsumeEffect(MobEffectInstance(MobEffects.POISON, 600, 1), 1.0f))
                        .build()
                )
        )
    }

    val LIONFISH = register(
        "lionfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
                .component(
                    DataComponents.CONSUMABLE,
                    Consumable.builder()
                        .onConsume(ApplyStatusEffectsConsumeEffect(MobEffectInstance(MobEffects.POISON, 1200, 0), 1.0f))
                        .build()
                )
        )
    }

    val ROCKFISH = register(
        "rockfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val SEA_BASS = register(
        "sea_bass"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val STINGRAY = register(
        "stingray"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
                .component(
                    DataComponents.CONSUMABLE,
                    Consumable.builder()
                        .onConsume(ApplyStatusEffectsConsumeEffect(MobEffectInstance(MobEffects.POISON, 600, 0), 1.0f))
                        .build()
                )
        )
    }

    val MORAY_EEL = register(
        "moray_eel"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val NEEDLEFISH = register(
        "needlefish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val RATFISH = register(
        "ratfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val TRIGGERFISH = register(
        "triggerfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val TREVALLY = register(
        "trevally"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val PARROTFISH = register(
        "parrotfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val SHEEPSHEAD_WRASSE = register(
        "sheepshead_wrasse"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val COELACANTH = register(
        "coelacanth"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val GOLDEN_DORADO = register(
        "golden_dorado"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(2)
                        .saturationModifier(0.4F)
                        .build()
                )
        )
    }

    val MAHI = register(
        "mahi"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationModifier(0.8F)
                        .build()
                )
        )
    }

    val TUNA = register(
        "tuna"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationModifier(0.8F)
                        .build()
                )
        )
    }

    val OPAH = register(
        "opah"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationModifier(0.8F)
                        .build()
                )
        )
    }

    val OARFISH = register(
        "oarfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationModifier(0.8F)
                        .build()
                )
        )
    }

    val OCEAN_SUNFISH = register(
        "ocean_sunfish"
    ) {
        Item(
            HAItemProperties.props()
                .food(
                    FoodProperties.Builder()
                        .nutrition(3)
                        .saturationModifier(0.8F)
                        .build()
                )
        )
    }

    //#endregion

    //#region Spawn Eggs
    //#region Fish Spawn Eggs
    val AFRICAN_BUTTERFLYFISH_SPAWN_EGG =
        registerSpawnEgg("african_butterflyfish_spawn_egg", HAEntityTypes.AFRICAN_BUTTERFLYFISH, 0xFFFFFF, 0xFFFFFF)
    val BARRELEYE_SPAWN_EGG =
        registerSpawnEgg("barreleye_spawn_egg", HAEntityTypes.BARRELEYE, 0xFFFFFF, 0xFFFFFF)
    val BETTA_SPAWN_EGG =
        registerSpawnEgg("betta_spawn_egg", HAEntityTypes.BETTA, 0xFFFFFF, 0xFFFFFF)
    val DAMSELFISH_SPAWN_EGG =
        registerSpawnEgg("damselfish_spawn_egg", HAEntityTypes.DAMSELFISH, 0xFFFFFF, 0xFFFFFF)
    val CARP_SPAWN_EGG =
        registerSpawnEgg("carp_spawn_egg", HAEntityTypes.CARP, 0xFFFFFF, 0xFFFFFF)
    val GOLDFISH_SPAWN_EGG =
        registerSpawnEgg("goldfish_spawn_egg", HAEntityTypes.GOLDFISH, 0xFFFFFF, 0xFFFFFF)
    val TROUT_SPAWN_EGG =
        registerSpawnEgg("trout_spawn_egg", HAEntityTypes.TROUT, 0xFFFFFF, 0xFFFFFF)
    val CATFISH_SPAWN_EGG =
        registerSpawnEgg("catfish_spawn_egg", HAEntityTypes.CATFISH, 0xFFFFFF, 0xFFFFFF)
    val SUNFISH_SPAWN_EGG =
        registerSpawnEgg("sunfish_spawn_egg", HAEntityTypes.SUNFISH, 0xFFFFFF, 0xFFFFFF)
    val CLOWNFISH_SPAWN_EGG =
        registerSpawnEgg("clownfish_spawn_egg", HAEntityTypes.CLOWNFISH, 0xFFFFFF, 0xFFFFFF)
    val GARDEN_EEL_SPAWN_EGG =
        registerSpawnEgg("garden_eel_spawn_egg", HAEntityTypes.GARDEN_EEL, 0xFFFFFF, 0xFFFFFF)
    val BOXFISH_SPAWN_EGG =
        registerSpawnEgg("boxfish_spawn_egg", HAEntityTypes.BOXFISH, 0xFFFFFF, 0xFFFFFF)
    val DANIO_SPAWN_EGG =
        registerSpawnEgg("danio_spawn_egg", HAEntityTypes.DANIO, 0xFFFFFF, 0xFFFFFF)
    val DISCUS_SPAWN_EGG =
        registerSpawnEgg("discus_spawn_egg", HAEntityTypes.DISCUS, 0xFFFFFF, 0xFFFFFF)
    val CORYDORA_SPAWN_EGG =
        registerSpawnEgg("corydora_spawn_egg", HAEntityTypes.CORYDORA, 0xFFFFFF, 0xFFFFFF)
    val FLASHLIGHT_FISH_SPAWN_EGG =
        registerSpawnEgg("flashlight_fish_spawn_egg", HAEntityTypes.FLASHLIGHT_FISH, 0xFFFFFF, 0xFFFFFF)
    val SQUIRRELFISH_SPAWN_EGG =
        registerSpawnEgg("squirrelfish_spawn_egg", HAEntityTypes.SQUIRRELFISH, 0xFFFFFF, 0xFFFFFF)
    val PEARLFISH_SPAWN_EGG =
        registerSpawnEgg("pearlfish_spawn_egg", HAEntityTypes.PEARLFISH, 0xFFFFFF, 0xFFFFFF)
    val FLYING_FISH_SPAWN_EGG =
        registerSpawnEgg("flying_fish_spawn_egg", HAEntityTypes.FLYING_FISH, 0xFFFFFF, 0xFFFFFF)
    val GOURAMI_SPAWN_EGG =
        registerSpawnEgg("gourami_spawn_egg", HAEntityTypes.GOURAMI, 0xFFFFFF, 0xFFFFFF)
    val PLECO_SPAWN_EGG =
        registerSpawnEgg("pleco_spawn_egg", HAEntityTypes.PLECO, 0xFFFFFF, 0xFFFFFF)
    val SHINER_SPAWN_EGG =
        registerSpawnEgg("shiner_spawn_egg", HAEntityTypes.SHINER, 0xFFFFFF, 0xFFFFFF)
    val LIONFISH_SPAWN_EGG =
        registerSpawnEgg("lionfish_spawn_egg", HAEntityTypes.LIONFISH, 0xFFFFFF, 0xFFFFFF)
    val MACKEREL_SPAWN_EGG =
        registerSpawnEgg("mackerel_spawn_egg", HAEntityTypes.MACKEREL, 0xFFFFFF, 0xFFFFFF)
    val HERRING_SPAWN_EGG =
        registerSpawnEgg("herring_spawn_egg", HAEntityTypes.HERRING, 0xFFFFFF, 0xFFFFFF)
    val MAHI_SPAWN_EGG =
        registerSpawnEgg("mahi_spawn_egg", HAEntityTypes.MAHI, 0xFFFFFF, 0xFFFFFF)
    val MANTA_RAY_SPAWN_EGG =
        registerSpawnEgg("manta_ray_spawn_egg", HAEntityTypes.MANTA_RAY, 0xFFFFFF, 0xFFFFFF)
    val MORAY_EEL_SPAWN_EGG =
        registerSpawnEgg("moray_eel_spawn_egg", HAEntityTypes.MORAY_EEL, 0xFFFFFF, 0xFFFFFF)
    val NEEDLEFISH_SPAWN_EGG =
        registerSpawnEgg("needlefish_spawn_egg", HAEntityTypes.NEEDLEFISH, 0xFFFFFF, 0xFFFFFF)
    val BARRACUDA_SPAWN_EGG =
        registerSpawnEgg("barracuda_spawn_egg", HAEntityTypes.BARRACUDA, 0xFFFFFF, 0xFFFFFF)
    val OPAH_SPAWN_EGG =
        registerSpawnEgg("opah_spawn_egg", HAEntityTypes.OPAH, 0xFFFFFF, 0xFFFFFF)
    val CICHLID_SPAWN_EGG =
        registerSpawnEgg("cichlid_spawn_egg", HAEntityTypes.CICHLID, 0xFFFFFF, 0xFFFFFF)
    val PARROTFISH_SPAWN_EGG =
        registerSpawnEgg("parrotfish_spawn_egg", HAEntityTypes.PARROTFISH, 0xFFFFFF, 0xFFFFFF)
    val PIRANHA_SPAWN_EGG =
        registerSpawnEgg("piranha_spawn_egg", HAEntityTypes.PIRANHA, 0xFFFFFF, 0xFFFFFF)
    val ROCKFISH_SPAWN_EGG =
        registerSpawnEgg("rockfish_spawn_egg", HAEntityTypes.ROCKFISH, 0xFFFFFF, 0xFFFFFF)
    val SEA_BASS_SPAWN_EGG =
        registerSpawnEgg("sea_bass_spawn_egg", HAEntityTypes.SEA_BASS, 0xFFFFFF, 0xFFFFFF)
    val SEAHORSE_SPAWN_EGG =
        registerSpawnEgg("seahorse_spawn_egg", HAEntityTypes.SEAHORSE, 0xFFFFFF, 0xFFFFFF)
    val SEADRAGON_SPAWN_EGG =
        registerSpawnEgg("seadragon_spawn_egg", HAEntityTypes.SEADRAGON, 0xFFFFFF, 0xFFFFFF)
    val STINGRAY_SPAWN_EGG =
        registerSpawnEgg("stingray_spawn_egg", HAEntityTypes.STINGRAY, 0xFFFFFF, 0xFFFFFF)
    val STONEFISH_SPAWN_EGG =
        registerSpawnEgg("stonefish_spawn_egg", HAEntityTypes.STONEFISH, 0xFFFFFF, 0xFFFFFF)
    val OCEAN_SUNFISH_SPAWN_EGG =
        registerSpawnEgg("ocean_sunfish_spawn_egg", HAEntityTypes.OCEAN_SUNFISH, 0xFFFFFF, 0xFFFFFF)
    val SURGEONFISH_SPAWN_EGG =
        registerSpawnEgg("surgeonfish_spawn_egg", HAEntityTypes.SURGEONFISH, 0xFFFFFF, 0xFFFFFF)
    val TETRA_SPAWN_EGG =
        registerSpawnEgg("tetra_spawn_egg", HAEntityTypes.TETRA, 0xFFFFFF, 0xFFFFFF)
    val PUPFISH_SPAWN_EGG =
        registerSpawnEgg("pupfish_spawn_egg", HAEntityTypes.PUPFISH, 0xFFFFFF, 0xFFFFFF)
    val TIGER_BARB_SPAWN_EGG =
        registerSpawnEgg("tiger_barb_spawn_egg", HAEntityTypes.TIGER_BARB, 0xFFFFFF, 0xFFFFFF)
    val BLOWFISH_SPAWN_EGG =
        registerSpawnEgg("blowfish_spawn_egg", HAEntityTypes.BLOWFISH, 0xFFFFFF, 0xFFFFFF)
    val TRIGGERFISH_SPAWN_EGG =
        registerSpawnEgg("triggerfish_spawn_egg", HAEntityTypes.TRIGGERFISH, 0xFFFFFF, 0xFFFFFF)
    val TREVALLY_SPAWN_EGG =
        registerSpawnEgg("trevally_spawn_egg", HAEntityTypes.TREVALLY, 0xFFFFFF, 0xFFFFFF)
    val TUNA_SPAWN_EGG =
        registerSpawnEgg("tuna_spawn_egg", HAEntityTypes.TUNA, 0xFFFFFF, 0xFFFFFF)
    val GOLDEN_DORADO_SPAWN_EGG =
        registerSpawnEgg("golden_dorado_spawn_egg", HAEntityTypes.GOLDEN_DORADO, 0xFFFFFF, 0xFFFFFF)
    val WRASSE_SPAWN_EGG =
        registerSpawnEgg("wrasse_spawn_egg", HAEntityTypes.WRASSE, 0xFFFFFF, 0xFFFFFF)
    val ANGLERFISH_SPAWN_EGG =
        registerSpawnEgg("anglerfish_spawn_egg", HAEntityTypes.ANGLERFISH, 0xFFFFFF, 0xFFFFFF)
    val VIPERFISH_SPAWN_EGG =
        registerSpawnEgg("viperfish_spawn_egg", HAEntityTypes.VIPERFISH, 0xFFFFFF, 0xFFFFFF)
    val HATCHETFISH_SPAWN_EGG =
        registerSpawnEgg("hatchetfish_spawn_egg", HAEntityTypes.HATCHETFISH, 0xFFFFFF, 0xFFFFFF)
    val BLOBFISH_SPAWN_EGG =
        registerSpawnEgg("blobfish_spawn_egg", HAEntityTypes.BLOBFISH, 0xFFFFFF, 0xFFFFFF)
    val HAGFISH_SPAWN_EGG =
        registerSpawnEgg("hagfish_spawn_egg", HAEntityTypes.HAGFISH, 0xFFFFFF, 0xFFFFFF)
    val TRIPOD_FISH_SPAWN_EGG =
        registerSpawnEgg("tripod_fish_spawn_egg", HAEntityTypes.TRIPOD_FISH, 0xFFFFFF, 0xFFFFFF)
    val FANGTOOTH_SPAWN_EGG =
        registerSpawnEgg("fangtooth_spawn_egg", HAEntityTypes.FANGTOOTH, 0xFFFFFF, 0xFFFFFF)
    val COELACANTH_SPAWN_EGG =
        registerSpawnEgg("coelacanth_spawn_egg", HAEntityTypes.COELACANTH, 0xFFFFFF, 0xFFFFFF)
    val SLICKHEAD_SPAWN_EGG =
        registerSpawnEgg("slickhead_spawn_egg", HAEntityTypes.SLICKHEAD, 0xFFFFFF, 0xFFFFFF)
    val DRAGONFISH_SPAWN_EGG =
        registerSpawnEgg("dragonfish_spawn_egg", HAEntityTypes.DRAGONFISH, 0xFFFFFF, 0xFFFFFF)
    val JOHN_DORY_SPAWN_EGG =
        registerSpawnEgg("john_dory_spawn_egg", HAEntityTypes.JOHN_DORY, 0xFFFFFF, 0xFFFFFF)
    val SNAILFISH_SPAWN_EGG =
        registerSpawnEgg("snailfish_spawn_egg", HAEntityTypes.SNAILFISH, 0xFFFFFF, 0xFFFFFF)
    val OARFISH_SPAWN_EGG =
        registerSpawnEgg("oarfish_spawn_egg", HAEntityTypes.OARFISH, 0xFFFFFF, 0xFFFFFF)
    val RATFISH_SPAWN_EGG =
        registerSpawnEgg("ratfish_spawn_egg", HAEntityTypes.RATFISH, 0xFFFFFF, 0xFFFFFF)
    //#endregion

    //#region Shark Spawn Eggs
    val BASKING_SHARK_SPAWN_EGG =
        registerSpawnEgg("basking_shark_spawn_egg", HAEntityTypes.BASKING_SHARK, 0xFFFFFF, 0xFFFFFF)
    val BULL_SHARK_SPAWN_EGG =
        registerSpawnEgg("bull_shark_spawn_egg", HAEntityTypes.BULL_SHARK, 0xFFFFFF, 0xFFFFFF)
    val FRILLED_SHARK_SPAWN_EGG =
        registerSpawnEgg("frilled_shark_spawn_egg", HAEntityTypes.FRILLED_SHARK, 0xFFFFFF, 0xFFFFFF)
    val SIXGILL_SHARK_SPAWN_EGG =
        registerSpawnEgg("sixgill_shark_spawn_egg", HAEntityTypes.SIXGILL_SHARK, 0xFFFFFF, 0xFFFFFF)
    val SLEEPER_SHARK_SPAWN_EGG =
        registerSpawnEgg("sleeper_shark_spawn_egg", HAEntityTypes.SLEEPER_SHARK, 0xFFFFFF, 0xFFFFFF)
    val GOBLIN_SHARK_SPAWN_EGG =
        registerSpawnEgg("goblin_shark_spawn_egg", HAEntityTypes.GOBLIN_SHARK, 0xFFFFFF, 0xFFFFFF)
    val LANTERN_SHARK_SPAWN_EGG =
        registerSpawnEgg("lantern_shark_spawn_egg", HAEntityTypes.LANTERN_SHARK, 0xFFFFFF, 0xFFFFFF)
    val GREAT_WHITE_SHARK_SPAWN_EGG =
        registerSpawnEgg("great_white_shark_spawn_egg", HAEntityTypes.GREAT_WHITE_SHARK, 0xFFFFFF, 0xFFFFFF)
    val HAMMERHEAD_SHARK_SPAWN_EGG =
        registerSpawnEgg("hammerhead_shark_spawn_egg", HAEntityTypes.HAMMERHEAD_SHARK, 0xFFFFFF, 0xFFFFFF)
    val HOUND_SHARK_SPAWN_EGG =
        registerSpawnEgg("hound_shark_spawn_egg", HAEntityTypes.HOUND_SHARK, 0xFFFFFF, 0xFFFFFF)
    val THRESHER_SHARK_SPAWN_EGG =
        registerSpawnEgg("thresher_shark_spawn_egg", HAEntityTypes.THRESHER_SHARK, 0xFFFFFF, 0xFFFFFF)
    val SAND_TIGER_SHARK_SPAWN_EGG =
        registerSpawnEgg("sand_tiger_shark_spawn_egg", HAEntityTypes.SAND_TIGER_SHARK, 0xFFFFFF, 0xFFFFFF)
    val WHALE_SHARK_SPAWN_EGG =
        registerSpawnEgg("whale_shark_spawn_egg", HAEntityTypes.WHALE_SHARK, 0xFFFFFF, 0xFFFFFF)
    //#endregion

    //#region Cephalopod Spawn Eggs
    val CUTTLEFISH_SPAWN_EGG =
        registerSpawnEgg("cuttlefish_spawn_egg", HAEntityTypes.CUTTLEFISH, 0xFFFFFF, 0xFFFFFF)
    val ARROW_SQUID_SPAWN_EGG =
        registerSpawnEgg("arrow_squid_spawn_egg", HAEntityTypes.ARROW_SQUID, 0xFFFFFF, 0xFFFFFF)
    val COLOSSAL_SQUID_SPAWN_EGG =
        registerSpawnEgg("colossal_squid_spawn_egg", HAEntityTypes.COLOSSAL_SQUID, 0xFFFFFF, 0xFFFFFF)
    val GIANT_SQUID_SPAWN_EGG =
        registerSpawnEgg("giant_squid_spawn_egg", HAEntityTypes.GIANT_SQUID, 0xFFFFFF, 0xFFFFFF)
    val FIREFLY_SQUID_SPAWN_EGG =
        registerSpawnEgg("firefly_squid_spawn_egg", HAEntityTypes.FIREFLY_SQUID, 0xFFFFFF, 0xFFFFFF)
    val OCTOPUS_SPAWN_EGG =
        registerSpawnEgg("octopus_spawn_egg", HAEntityTypes.OCTOPUS, 0xFFFFFF, 0xFFFFFF)
    val NAUTILUS_SPAWN_EGG =
        registerSpawnEgg("nautilus_spawn_egg", HAEntityTypes.NAUTILUS, 0xFFFFFF, 0xFFFFFF)
    val UMBRELLA_OCTOPUS_SPAWN_EGG =
        registerSpawnEgg("umbrella_octopus_spawn_egg", HAEntityTypes.UMBRELLA_OCTOPUS, 0xFFFFFF, 0xFFFFFF)
    val VAMPIRE_SQUID_SPAWN_EGG =
        registerSpawnEgg("vampire_squid_spawn_egg", HAEntityTypes.VAMPIRE_SQUID, 0xFFFFFF, 0xFFFFFF)
    //#endregion

    //#region Jellyfish Spawn Eggs
    val BARREL_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("barrel_jellyfish_spawn_egg", HAEntityTypes.BARREL_JELLYFISH, 0xFFFFFF, 0xFFFFFF)
    val BLUE_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("blue_jellyfish_spawn_egg", HAEntityTypes.BLUE_JELLYFISH, 0xFFFFFF, 0xFFFFFF)
    val CEPHEIDAE_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("cepheidae_jellyfish_spawn_egg", HAEntityTypes.CEPHEIDAE_JELLYFISH, 0xFFFFFF, 0xFFFFFF)
    val LIONS_MANE_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("lions_mane_jellyfish_spawn_egg", HAEntityTypes.LIONS_MANE_JELLYFISH, 0xFFFFFF, 0xFFFFFF)
    val MOON_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("moon_jellyfish_spawn_egg", HAEntityTypes.MOON_JELLYFISH, 0xFFFFFF, 0xFFFFFF)
    val NOMURA_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("nomura_jellyfish_spawn_egg", HAEntityTypes.NOMURA_JELLYFISH, 0xFFFFFF, 0xFFFFFF)
    val SEA_NETTLE_SPAWN_EGG =
        registerSpawnEgg("sea_nettle_spawn_egg", HAEntityTypes.SEA_NETTLE, 0xFFFFFF, 0xFFFFFF)
    val BOX_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("box_jellyfish_spawn_egg", HAEntityTypes.BOX_JELLYFISH, 0xFFFFFF, 0xFFFFFF)
    val MAUVE_STINGER_SPAWN_EGG =
        registerSpawnEgg("mauve_stinger_spawn_egg", HAEntityTypes.MAUVE_STINGER, 0xFFFFFF, 0xFFFFFF)
    val CROWN_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("crown_jellyfish_spawn_egg", HAEntityTypes.CROWN_JELLYFISH, 0xFFFFFF, 0xFFFFFF)
    val BIG_RED_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("big_red_jellyfish_spawn_egg", HAEntityTypes.BIG_RED_JELLYFISH, 0xFFFFFF, 0xFFFFFF)
    val COSMIC_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg("cosmic_jellyfish_spawn_egg", HAEntityTypes.COSMIC_JELLYFISH, 0xFFFFFF, 0xFFFFFF)
    val COMB_JELLY_SPAWN_EGG =
        registerSpawnEgg("comb_jelly_spawn_egg", HAEntityTypes.COMB_JELLY, 0xFFFFFF, 0xFFFFFF)
    val FIREWORK_JELLYFISH_SPAWN_EGG =
        registerSpawnEgg(
            "firework_jellyfish_spawn_egg", HAEntityTypes.FIREWORK_JELLYFISH, 0xFFFFFF, 0xFFFFFF)
    //#endregion

    //#region Crustacean Spawn Eggs
    val COCONUT_CRAB_SPAWN_EGG =
        registerSpawnEgg("coconut_crab_spawn_egg", HAEntityTypes.COCONUT_CRAB, 0xFFFFFF, 0xFFFFFF)
    val DUNGENESS_CRAB_SPAWN_EGG =
        registerSpawnEgg("dungeness_crab_spawn_egg", HAEntityTypes.DUNGENESS_CRAB, 0xFFFFFF, 0xFFFFFF)
    val CRAYFISH_SPAWN_EGG =
        registerSpawnEgg("crayfish_spawn_egg", HAEntityTypes.CRAYFISH, 0xFFFFFF, 0xFFFFFF)
    val FIDDLER_CRAB_SPAWN_EGG =
        registerSpawnEgg("fiddler_crab_spawn_egg", HAEntityTypes.FIDDLER_CRAB, 0xFFFFFF, 0xFFFFFF)
    val FLOWER_CRAB_SPAWN_EGG =
        registerSpawnEgg("flower_crab_spawn_egg", HAEntityTypes.FLOWER_CRAB, 0xFFFFFF, 0xFFFFFF)
    val DECORATOR_CRAB_SPAWN_EGG =
        registerSpawnEgg("decorator_crab_spawn_egg", HAEntityTypes.DECORATOR_CRAB, 0xFFFFFF, 0xFFFFFF)
    val GHOST_CRAB_SPAWN_EGG =
        registerSpawnEgg("ghost_crab_spawn_egg", HAEntityTypes.GHOST_CRAB, 0xFFFFFF, 0xFFFFFF)
    val HERMIT_CRAB_SPAWN_EGG =
        registerSpawnEgg("hermit_crab_spawn_egg", HAEntityTypes.HERMIT_CRAB, 0xFFFFFF, 0xFFFFFF)
    val HORSESHOE_CRAB_SPAWN_EGG =
        registerSpawnEgg("horseshoe_crab_spawn_egg", HAEntityTypes.HORSESHOE_CRAB, 0xFFFFFF, 0xFFFFFF)
    val LIGHTFOOT_CRAB_SPAWN_EGG =
        registerSpawnEgg("lightfoot_crab_spawn_egg", HAEntityTypes.LIGHTFOOT_CRAB, 0xFFFFFF, 0xFFFFFF)
    val LOBSTER_SPAWN_EGG =
        registerSpawnEgg("lobster_spawn_egg", HAEntityTypes.LOBSTER, 0xFFFFFF, 0xFFFFFF)
    val SHRIMP_SPAWN_EGG =
        registerSpawnEgg("shrimp_spawn_egg", HAEntityTypes.SHRIMP, 0xFFFFFF, 0xFFFFFF)
    val VAMPIRE_CRAB_SPAWN_EGG =
        registerSpawnEgg("vampire_crab_spawn_egg", HAEntityTypes.VAMPIRE_CRAB, 0xFFFFFF, 0xFFFFFF)
    val GIANT_ISOPOD_SPAWN_EGG =
        registerSpawnEgg("giant_isopod_spawn_egg", HAEntityTypes.GIANT_ISOPOD, 0xFFFFFF, 0xFFFFFF)
    val SPIDER_CRAB_SPAWN_EGG =
        registerSpawnEgg("spider_crab_spawn_egg", HAEntityTypes.SPIDER_CRAB, 0xFFFFFF, 0xFFFFFF)
    val YETI_CRAB_SPAWN_EGG =
        registerSpawnEgg("yeti_crab_spawn_egg", HAEntityTypes.YETI_CRAB, 0xFFFFFF, 0xFFFFFF)
    //#endregion

    //#region Critter Spawn Eggs
    val SEA_SLUG_SPAWN_EGG =
        registerSpawnEgg("sea_slug_spawn_egg", HAEntityTypes.SEA_SLUG, 0xFFFFFF, 0xFFFFFF)
    val SCALYFOOT_SNAIL_SPAWN_EGG =
        registerSpawnEgg("scalyfoot_snail_spawn_egg", HAEntityTypes.SCALYFOOT_SNAIL, 0xFFFFFF, 0xFFFFFF)
    val SEA_CUCUMBER_SPAWN_EGG =
        registerSpawnEgg("sea_cucumber_spawn_egg", HAEntityTypes.SEA_CUCUMBER, 0xFFFFFF, 0xFFFFFF)
    val SEA_URCHIN_SPAWN_EGG =
        registerSpawnEgg("sea_urchin_spawn_egg", HAEntityTypes.SEA_URCHIN, 0xFFFFFF, 0xFFFFFF)
    val STARFISH_SPAWN_EGG =
        registerSpawnEgg("starfish_spawn_egg", HAEntityTypes.STARFISH, 0xFFFFFF, 0xFFFFFF)
    val SEA_ANGEL_SPAWN_EGG =
        registerSpawnEgg("sea_angel_spawn_egg", HAEntityTypes.SEA_ANGEL, 0xFFFFFF, 0xFFFFFF)
    //#endregion

    //#region Mammal Spawn Eggs
    val OTTER_SPAWN_EGG =
        registerSpawnEgg("otter_spawn_egg", HAEntityTypes.OTTER, 0xFFFFFF, 0xFFFFFF)

    val BEAVER_SPAWN_EGG =
        registerSpawnEgg("beaver_spawn_egg", HAEntityTypes.BEAVER, 0xFFFFFF, 0xFFFFFF)

    val DUGONG_SPAWN_EGG =
        registerSpawnEgg("dugong_spawn_egg", HAEntityTypes.DUGONG, 0xFFFFFF, 0xFFFFFF)

    val MANATEE_SPAWN_EGG =
        registerSpawnEgg("manatee_spawn_egg", HAEntityTypes.MANATEE, 0xFFFFFF, 0xFFFFFF)

    val HIPPO_SPAWN_EGG =
        registerSpawnEgg("hippo_spawn_egg", HAEntityTypes.HIPPO, 0xFFFFFF, 0xFFFFFF)

    val ORCA_SPAWN_EGG =
        registerSpawnEgg("orca_spawn_egg", HAEntityTypes.ORCA, 0xFFFFFF, 0xFFFFFF)
    //#endregion

    //#region Miniboss & Minion Spawn Eggs
    val KARKINOS_SPAWN_EGG =
        registerSpawnEgg("karkinos_spawn_egg", HAEntityTypes.KARKINOS, 0xFFFFFF, 0xFFFFFF)
    val KARCINOGEN_SPAWN_EGG =
        registerSpawnEgg("karcinogen_spawn_egg", HAEntityTypes.KARCINOGEN, 0xFFFFFF, 0xFFFFFF)
    val KARCINOMA_SPAWN_EGG =
        registerSpawnEgg("karcinoma_spawn_egg", HAEntityTypes.KARCINOMA, 0xFFFFFF, 0xFFFFFF)
    val SHELL_BEAST_SPAWN_EGG =
        registerSpawnEgg("shell_beast_spawn_egg", HAEntityTypes.SHELL_BEAST, 0xFFFFFF, 0xFFFFFF)
    // val HYPNAUTILUS_SPAWN_EGG =
    //     registerSpawnEgg("hypnautilus_spawn_egg", HAEntityTypes.HYPNAUTILUS, 0xFFFFFF, 0xFFFFFF)
    val BEAKLING_SPAWN_EGG =
        registerSpawnEgg("beakling_spawn_egg", HAEntityTypes.BEAKLING, 0xFFFFFF, 0xFFFFFF)
    //#endregion
    //#endregion

    fun register(id: String, item: Supplier<Item>): Supplier<Item> {
        return CommonClass.ITEMS.register(id) { HAItemProperties.withId(id) { item.get() } }
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
        return register(id) { BlockItem(block.get(), HAItemProperties.blockProps(block.get())) }
    }

    /**
     * Armor durability is no longer carried by the armor material, so each piece has to declare it
     * here. [durabilityFactor] is the per-material multiplier the armor set used before the port.
     */
    private fun armorProperties(type: ArmorType, durabilityFactor: Int): Item.Properties {
        return HAItemProperties.props().stacksTo(1).durability(type.getDurability(durabilityFactor))
    }

    private fun registerPlaceableInWaterBlockItem(id: String, block: Supplier<Block>): Supplier<Item> {
        return register(id) { PlaceableInWaterItem(block.get(), HAItemProperties.blockProps(block.get())) }
    }

    /** Whether [stack] is one of the seashell tools, which keep their speed while underwater. */
    fun isSeashellTool(stack: ItemStack): Boolean =
        stack.`is`(SEASHELL_SPEAR.get()) || stack.`is`(SEASHELL_PICKAXE.get()) ||
            stack.`is`(SEASHELL_AXE.get()) || stack.`is`(SEASHELL_SHOVEL.get()) ||
            stack.`is`(SEASHELL_HOE.get())

    /** Whether [stack] is one of the coral tools, which slowly repair while underwater. */
    fun isCoralTool(stack: ItemStack): Boolean =
        stack.`is`(CORAL_BLADE.get()) || stack.`is`(CORAL_PICKAXE.get()) ||
            stack.`is`(CORAL_AXE.get()) || stack.`is`(CORAL_SHOVEL.get()) ||
            stack.`is`(CORAL_HOE.get())

    private fun registerPlaceableInWaterOrLandBlockItem(id: String, block: Supplier<Block>): Supplier<Item> {
        return register(id) { PlaceableInWaterOrLandItem(block.get(), HAItemProperties.blockProps(block.get())) }
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
                direction,
                HAItemProperties.blockProps(standingBlock.get())
            )
        }
    }
}
