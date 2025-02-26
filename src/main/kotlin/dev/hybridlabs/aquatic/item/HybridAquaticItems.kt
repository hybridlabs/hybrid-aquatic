@file:Suppress("unused", "SameParameterValue")

package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.MobEntity
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction

object HybridAquaticItems {

    //#region Armor
    val DIVING_HELMET: Item = register(
        "diving_helmet",
        DivingArmorItem(HybridAquaticArmorMaterials.DIVING, ArmorItem.Type.HELMET, FabricItemSettings().maxCount(1))
    )

    val DIVING_SUIT: Item = register(
        "diving_suit",
        DivingArmorItem(HybridAquaticArmorMaterials.DIVING, ArmorItem.Type.CHESTPLATE, FabricItemSettings().maxCount(1))
    )

    val DIVING_LEGGINGS: Item = register(
        "diving_leggings",
        DivingArmorItem(HybridAquaticArmorMaterials.DIVING, ArmorItem.Type.LEGGINGS, FabricItemSettings().maxCount(1))
    )

    val DIVING_BOOTS: Item = register(
        "diving_boots",
        DivingArmorItem(HybridAquaticArmorMaterials.DIVING, ArmorItem.Type.BOOTS, FabricItemSettings().maxCount(1))
    )

    val NAUTILUS_HELMET: Item = register(
        "nautilus_helmet",
        SeashellArmorItem(HybridAquaticArmorMaterials.SEASHELL, ArmorItem.Type.HELMET, FabricItemSettings().maxCount(1))
    )

    val NAUTILUS_PAULDRONS: Item = register(
        "nautilus_pauldrons",
        SeashellArmorItem(
            HybridAquaticArmorMaterials.SEASHELL,
            ArmorItem.Type.CHESTPLATE,
            FabricItemSettings().maxCount(1)
        )
    )

    val TURTLE_CHESTPLATE: Item = register(
        "turtle_chestplate",
        TurtleArmorItem(HybridAquaticArmorMaterials.TURTLE, ArmorItem.Type.CHESTPLATE, FabricItemSettings().maxCount(1))
    )

    val MANGLERFISH_LURE: Item = register(
        "manglerfish_lure",
        ManglerfishArmorItem(
            HybridAquaticArmorMaterials.MANGLERFISH,
            ArmorItem.Type.HELMET,
            FabricItemSettings().maxCount(1)
        )
    )

    val MANGLERFISH_FIN: Item = register(
        "manglerfish_fin",
        ManglerfishArmorItem(
            HybridAquaticArmorMaterials.MANGLERFISH,
            ArmorItem.Type.CHESTPLATE,
            FabricItemSettings().maxCount(1)
        )
    )

    val EEL_SCARF: Item = register(
        "eel_scarf",
        EelArmorItem(HybridAquaticArmorMaterials.EEL, ArmorItem.Type.CHESTPLATE, FabricItemSettings().maxCount(1))
    )

    val MOON_JELLYFISH_HAT: Item = register(
        "moon_jellyfish_hat",
        MoonJellyfishArmorItem(
            HybridAquaticArmorMaterials.MOONJELLYFISH,
            ArmorItem.Type.HELMET,
            FabricItemSettings().maxCount(1)
        )
    )

    //#endregion

    //#region Tools - Weapons - Hooks

    val SEA_MESSAGE_BOOK = register("sea_message_book", SeaMessageBookItem(FabricItemSettings()))
    val FISHING_NET = register("fishing_net", FishingNetItem(FabricItemSettings().maxCount(1)))
    val KARKINOS_CLAW = register("karkinos_claw", KarkinosClawItem(FabricItemSettings().maxCount(1)))

    val BARBED_HOOK = register("barbed_hook", HookItem(Item.Settings().maxDamage(16)))
    val GLOWING_HOOK = register("glowing_hook", HookItem(Item.Settings().maxDamage(16)))
    val MAGNETIC_HOOK = register("magnetic_hook", HookItem(Item.Settings().maxDamage(8)))
    val CREEPERMAGNET_HOOK = register("creepermagnet_hook", HookItem(Item.Settings().maxDamage(1)))
    val OMINOUS_HOOK = register("ominous_hook", HookItem(Item.Settings().maxDamage(1)))

    val SEASHELL_SPEAR = register(
        "seashell_spear", SwordItem(
            HybridAquaticToolMaterials.SEASHELL,
            3,
            -2.4f,
            FabricItemSettings()
        )
    )

    val SEASHELL_PICKAXE = register(
        "seashell_pickaxe", PickaxeItem(
            HybridAquaticToolMaterials.SEASHELL,
            1,
            -2.8f,
            FabricItemSettings()
        )
    )

    val SEASHELL_AXE = register(
        "seashell_axe", AxeItem(
            HybridAquaticToolMaterials.SEASHELL,
            7F,
            -3.2f,
            FabricItemSettings()
        )
    )

    val SEASHELL_SHOVEL = register(
        "seashell_shovel", ShovelItem(
            HybridAquaticToolMaterials.SEASHELL,
            1.5F,
            -3.0f,
            FabricItemSettings()
        )
    )

    val SEASHELL_HOE = register(
        "seashell_hoe", HoeItem(
            HybridAquaticToolMaterials.SEASHELL,
            -1,
            -2.0f,
            FabricItemSettings()
        )
    )

    val CORAL_BLADE = register(
        "coral_blade", SwordItem(
            HybridAquaticToolMaterials.CORAL,
            1,
            3f,
            FabricItemSettings()
        )
    )

    val CORAL_PICKAXE = register(
        "coral_pickaxe", PickaxeItem(
            HybridAquaticToolMaterials.CORAL,
            1,
            3f,
            FabricItemSettings()
        )
    )

    val CORAL_AXE = register(
        "coral_axe", AxeItem(
            HybridAquaticToolMaterials.CORAL,
            1F,
            3f,
            FabricItemSettings()
        )
    )

    val CORAL_SHOVEL = register(
        "coral_shovel", ShovelItem(
            HybridAquaticToolMaterials.CORAL,
            1F,
            3f,
            FabricItemSettings()
        )
    )

    val CORAL_HOE = register(
        "coral_hoe", HoeItem(
            HybridAquaticToolMaterials.CORAL,
            1,
            3f,
            FabricItemSettings()
        )
    )

    //#endregion

    //#region Blocks

        //#region Nature Blocks

    val ANEMONE = registerBlockItem("anemone", HybridAquaticBlocks.ANEMONE)
    val STRAWBERRY_ANEMONE = registerBlockItem("strawberry_anemone", HybridAquaticBlocks.STRAWBERRY_ANEMONE)
    val GIANT_CLAM = registerBlockItem("giant_clam", HybridAquaticBlocks.GIANT_CLAM)
    val SARGASSUM = registerBlockItem("sargassum", HybridAquaticBlocks.SARGASSUM)
    val FLOATING_SARGASSUM = registerPlaceableInWaterBlockItem("floating_sargassum", HybridAquaticBlocks.FLOATING_SARGASSUM)
    val WATER_LETTUCE = registerPlaceableInWaterBlockItem("water_lettuce", HybridAquaticBlocks.WATER_LETTUCE)
    val JUNGLE_LILY_PAD = registerPlaceableInWaterBlockItem("jungle_lily_pad", HybridAquaticBlocks.JUNGLE_LILY_PAD)
    val RED_ALGAE = registerBlockItem("red_algae", HybridAquaticBlocks.RED_ALGAE)
    val SEA_LETTUCE = registerBlockItem("sea_lettuce", HybridAquaticBlocks.SEA_LETTUCE)
    val HYDROTHERMAL_VENT = registerBlockItem("hydrothermal_vent", HybridAquaticBlocks.THERMAL_VENT)
    val TUBE_WORM = registerBlockItem("tube_worm", HybridAquaticBlocks.TUBE_WORM)
    val TUBE_SPONGE = registerBlockItem("tube_sponge", HybridAquaticBlocks.TUBE_SPONGE)

            //#region Coral Blocks

    val BUTTON_CORAL_BLOCK = registerBlockItem("button_coral_block", HybridAquaticBlocks.BUTTON_CORAL_BLOCK)
    val DEAD_BUTTON_CORAL_BLOCK = registerBlockItem("dead_button_coral_block", HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK)
    val BUTTON_CORAL = registerBlockItem("button_coral", HybridAquaticBlocks.BUTTON_CORAL)
    val DEAD_BUTTON_CORAL = registerBlockItem("dead_button_coral", HybridAquaticBlocks.DEAD_BUTTON_CORAL)
    val BUTTON_CORAL_FAN = registerVerticallyAttachable("button_coral_fan", HybridAquaticBlocks.BUTTON_CORAL_FAN, HybridAquaticBlocks.BUTTON_CORAL_WALL_FAN)
    val DEAD_BUTTON_CORAL_FAN = registerVerticallyAttachable("dead_button_coral_fan", HybridAquaticBlocks.DEAD_BUTTON_CORAL_FAN, HybridAquaticBlocks.DEAD_BUTTON_CORAL_WALL_FAN)

    val SUN_CORAL_BLOCK = registerBlockItem("sun_coral_block", HybridAquaticBlocks.SUN_CORAL_BLOCK)
    val DEAD_SUN_CORAL_BLOCK = registerBlockItem("dead_sun_coral_block", HybridAquaticBlocks.DEAD_SUN_CORAL_BLOCK)
    val SUN_CORAL = registerBlockItem("sun_coral", HybridAquaticBlocks.SUN_CORAL)
    val DEAD_SUN_CORAL = registerBlockItem("dead_sun_coral", HybridAquaticBlocks.DEAD_SUN_CORAL)
    val SUN_CORAL_FAN = registerVerticallyAttachable("sun_coral_fan", HybridAquaticBlocks.SUN_CORAL_FAN, HybridAquaticBlocks.SUN_CORAL_WALL_FAN)
    val DEAD_SUN_CORAL_FAN = registerVerticallyAttachable("dead_sun_coral_fan", HybridAquaticBlocks.DEAD_SUN_CORAL_FAN, HybridAquaticBlocks.DEAD_SUN_CORAL_WALL_FAN)

    val LOPHELIA_CORAL_BLOCK = registerBlockItem("lophelia_coral_block", HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK)
    val DEAD_LOPHELIA_CORAL_BLOCK = registerBlockItem("dead_lophelia_coral_block", HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK)
    val LOPHELIA_CORAL = registerBlockItem("lophelia_coral", HybridAquaticBlocks.LOPHELIA_CORAL)
    val DEAD_LOPHELIA_CORAL = registerBlockItem("dead_lophelia_coral", HybridAquaticBlocks.DEAD_LOPHELIA_CORAL)
    val LOPHELIA_CORAL_FAN = registerVerticallyAttachable("lophelia_coral_fan", HybridAquaticBlocks.LOPHELIA_CORAL_FAN, HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN)

    val DEAD_LOPHELIA_CORAL_FAN = registerVerticallyAttachable("dead_lophelia_coral_fan", HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN, HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_WALL_FAN)
    val THORN_CORAL_BLOCK = registerBlockItem("thorn_coral_block", HybridAquaticBlocks.THORN_CORAL_BLOCK)
    val DEAD_THORN_CORAL_BLOCK = registerBlockItem("dead_thorn_coral_block", HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK)
    val THORN_CORAL = registerBlockItem("thorn_coral", HybridAquaticBlocks.THORN_CORAL)
    val DEAD_THORN_CORAL = registerBlockItem("dead_thorn_coral", HybridAquaticBlocks.DEAD_THORN_CORAL)
    val THORN_CORAL_FAN = registerVerticallyAttachable("thorn_coral_fan", HybridAquaticBlocks.THORN_CORAL_FAN, HybridAquaticBlocks.THORN_CORAL_WALL_FAN)
    val DEAD_THORN_CORAL_FAN = registerVerticallyAttachable("dead_thorn_coral_fan", HybridAquaticBlocks.DEAD_THORN_CORAL_FAN, HybridAquaticBlocks.DEAD_THORN_CORAL_WALL_FAN)

            //#endregion

        //#endregion

        //#region Artificial Blocks

    val BUOY = registerPlaceableInWaterBlockItem("buoy", HybridAquaticBlocks.BUOY)
    val RAFT = registerPlaceableInWaterBlockItem("raft", HybridAquaticBlocks.RAFT)
    val GLOWSTICK = registerVerticallyAttachable("glowstick", HybridAquaticBlocks.GLOWSTICK, HybridAquaticBlocks.WALL_GLOWSTICK)
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleItem(FabricItemSettings()))

            //#region Plushies

    val BASKING_SHARK_PLUSHIE = registerBlockItem("basking_shark_plushie", HybridAquaticBlocks.BASKING_SHARK_PLUSHIE)
    val BULL_SHARK_PLUSHIE = registerBlockItem("bull_shark_plushie", HybridAquaticBlocks.BULL_SHARK_PLUSHIE)
    val FRILLED_SHARK_PLUSHIE = registerBlockItem("frilled_shark_plushie", HybridAquaticBlocks.FRILLED_SHARK_PLUSHIE)
    val GREAT_WHITE_SHARK_PLUSHIE = registerBlockItem("great_white_shark_plushie", HybridAquaticBlocks.GREAT_WHITE_SHARK_PLUSHIE)
    val HAMMERHEAD_SHARK_PLUSHIE = registerBlockItem("hammerhead_shark_plushie", HybridAquaticBlocks.HAMMERHEAD_SHARK_PLUSHIE)
    val THRESHER_SHARK_PLUSHIE = registerBlockItem("thresher_shark_plushie", HybridAquaticBlocks.THRESHER_SHARK_PLUSHIE)
    val TIGER_SHARK_PLUSHIE = registerBlockItem("tiger_shark_plushie", HybridAquaticBlocks.TIGER_SHARK_PLUSHIE)
    val WHALE_SHARK_PLUSHIE = registerBlockItem("whale_shark_plushie", HybridAquaticBlocks.WHALE_SHARK_PLUSHIE)

            //#endregion

            //#region Crates

    val CRAB_POT = registerBlockItem("crab_pot", HybridAquaticBlocks.CRAB_POT)
    val HYBRID_CRATE = registerBlockItem("hybrid_crate", HybridAquaticBlocks.HYBRID_CRATE)
    val OAK_CRATE = registerBlockItem("oak_crate", HybridAquaticBlocks.OAK_CRATE)
    val SPRUCE_CRATE = registerBlockItem("spruce_crate", HybridAquaticBlocks.SPRUCE_CRATE)
    val BIRCH_CRATE = registerBlockItem("birch_crate", HybridAquaticBlocks.BIRCH_CRATE)
    val DARK_OAK_CRATE = registerBlockItem("dark_oak_crate", HybridAquaticBlocks.DARK_OAK_CRATE)
    val JUNGLE_CRATE = registerBlockItem("jungle_crate", HybridAquaticBlocks.JUNGLE_CRATE)
    val ACACIA_CRATE = registerBlockItem("acacia_crate", HybridAquaticBlocks.ACACIA_CRATE)
    val MANGROVE_CRATE = registerBlockItem("mangrove_crate", HybridAquaticBlocks.MANGROVE_CRATE)
    val CHERRY_CRATE = registerBlockItem("cherry_crate", HybridAquaticBlocks.CHERRY_CRATE)

            //#endregion

            //#region Wood Blocks

    val DRIFTWOOD_PLANKS = registerBlockItem("driftwood_planks", HybridAquaticBlocks.DRIFTWOOD_PLANKS)
    val DRIFTWOOD_LOG = registerBlockItem("driftwood_log", HybridAquaticBlocks.DRIFTWOOD_LOG)
    val DRIFTWOOD_WOOD = registerBlockItem("driftwood_wood", HybridAquaticBlocks.DRIFTWOOD_WOOD)
    val STRIPPED_DRIFTWOOD_LOG = registerBlockItem("stripped_driftwood_log", HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG)
    val STRIPPED_DRIFTWOOD_WOOD = registerBlockItem("stripped_driftwood_wood", HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD)
    val DRIFTWOOD_DOOR = registerBlockItem("driftwood_door", HybridAquaticBlocks.DRIFTWOOD_DOOR)
    val DRIFTWOOD_TRAPDOOR = registerBlockItem("driftwood_trapdoor", HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR)
    val DRIFTWOOD_SLAB = registerBlockItem("driftwood_slab", HybridAquaticBlocks.DRIFTWOOD_SLAB)
    val DRIFTWOOD_STAIRS = registerBlockItem("driftwood_stairs", HybridAquaticBlocks.DRIFTWOOD_STAIRS)
    val DRIFTWOOD_FENCE = registerBlockItem("driftwood_fence", HybridAquaticBlocks.DRIFTWOOD_FENCE)
    val DRIFTWOOD_FENCE_GATE = registerBlockItem("driftwood_fence_gate", HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE)
    val DRIFTWOOD_PRESSURE_PLATE = registerBlockItem("driftwood_pressure_plate", HybridAquaticBlocks.DRIFTWOOD_PRESSURE_PLATE)
    val DRIFTWOOD_BUTTON = registerBlockItem("driftwood_button", HybridAquaticBlocks.DRIFTWOOD_BUTTON)

            //#endregion

        //#endregion

    //#endregion

    //#region Crafting Ingredients

    val GLOW_SLIME = register("glow_slime", Item(FabricItemSettings()))
    val SEA_URCHIN_SPINE = register("sea_urchin_spine", Item(FabricItemSettings()))
    val SHARK_TOOTH = register("shark_tooth", Item(FabricItemSettings()))
    val SULFUR = register("sulfur", Item(FabricItemSettings()))
    val CORAL_CHUNK = register("coral_chunk", Item(FabricItemSettings()))
    val PEARL = register("pearl", Item(FabricItemSettings()))
    val BLACK_PEARL = register("black_pearl", Item(FabricItemSettings()))
    val CUTTLEBONE = register("cuttlebone", Item(FabricItemSettings()))
    val LOBSTER_CLAW = register("lobster_claw", Item(FabricItemSettings()))
    val DUNGENESS_CRAB_CLAW = register("dungeness_crab_claw", Item(FabricItemSettings()))
    val FIDDLER_CRAB_CLAW = register("fiddler_crab_claw", Item(FabricItemSettings()))
    val VAMPIRE_CRAB_CLAW = register("vampire_crab_claw", Item(FabricItemSettings()))
    val FLOWER_CRAB_CLAW = register("flower_crab_claw", Item(FabricItemSettings()))
    val GHOST_CRAB_CLAW = register("ghost_crab_claw", Item(FabricItemSettings()))
    val SPIDER_CRAB_CLAW = register("spider_crab_claw", Item(FabricItemSettings()))
    val COCONUT_CRAB_CLAW = register("coconut_crab_claw", Item(FabricItemSettings()))
    val YETI_CRAB_CLAW = register("yeti_crab_claw", Item(FabricItemSettings()))
    val LIGHTFOOT_CRAB_CLAW = register("lightfoot_crab_claw", Item(FabricItemSettings()))

    //#endregion

    //# region Food

    val UNI = register("uni",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val RAW_SHRIMP = register(
        "raw_shrimp",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val COOKED_SHRIMP = register(
        "cooked_shrimp",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(4)
                        .saturationModifier(0.5F)
                        .meat()
                        .build()
                )
        )
    )

    val RAW_CRAYFISH = register(
        "raw_crayfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val COOKED_CRAYFISH = register(
        "cooked_crayfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(4)
                        .saturationModifier(0.5F)
                        .meat()
                        .build()
                )
        )
    )

    val RAW_CRAB = register(
        "raw_crab",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val COOKED_CRAB = register(
        "cooked_crab",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(4)
                        .saturationModifier(0.5F)
                        .meat()
                        .build()
                )
        )
    )

    val RAW_LOBSTER = register(
        "raw_lobster",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val COOKED_LOBSTER = register(
        "cooked_lobster",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(5)
                        .saturationModifier(0.6F)
                        .meat()
                        .build()
                )
        )
    )

    val RAW_LOBSTER_TAIL = register(
        "raw_lobster_tail",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val COOKED_LOBSTER_TAIL = register(
        "cooked_lobster_tail",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(5)
                        .saturationModifier(0.6F)
                        .meat()
                        .build()
                )
        )
    )

    val COOKED_FISH_STEAK = register(
        "cooked_fish_steak",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(8)
                        .saturationModifier(0.8F)
                        .meat()
                        .build()
                )
        )
    )

    val RAW_FISH_STEAK = register(
        "raw_fish_steak",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(3)
                        .saturationModifier(0.6F)
                        .meat()
                        .build()
                )
        )
    )

    val COOKED_FISH_MEAT = register(
        "cooked_fish_meat",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(4)
                        .saturationModifier(0.6F)
                        .meat()
                        .build()
                )
        )
    )

    val RAW_FISH_MEAT = register(
        "raw_fish_meat",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val RAW_TENTACLE = register(
        "raw_tentacle",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val COOKED_TENTACLE = register(
        "cooked_tentacle",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(4)
                        .saturationModifier(0.6F)
                        .meat()
                        .build()
                )
        )
    )

    val MACKEREL = register(
        "mackerel",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val FLYING_FISH = register(
        "flying_fish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val PIRANHA = register(
        "piranha",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val ANGLERFISH = register(
        "anglerfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val BARRELEYE = register(
        "barreleye",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val DRAGONFISH = register(
        "dragonfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val BLUE_TANG = register(
        "blue_tang",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val SURGEONFISH_SOHAL = register(
        "surgeonfish_sohal",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val SURGEONFISH_LINED = register(
        "surgeonfish_lined",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val SURGEONFISH_ORANGESHOULDER = register(
        "surgeonfish_orangeshoulder",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val YELLOW_TANG = register(
        "yellow_tang",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val POWDER_BLUE_TANG = register(
        "powder_blue_tang",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val CLOWNFISH = register(
        "clownfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val JOHN_DORY = register(
        "john_dory",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val UNICORNFISH = register(
        "unicorn_fish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )

    )

    val SERGEANT_MAJOR = register(
        "sergeant_major",
        Item(
             FabricItemSettings()
                .food(
                     FoodComponent.Builder()
                            .hunger(1)
                            .saturationModifier(0.2F)
                            .meat()
                            .build()
                )
        )
    )

    val FLASHLIGHT_FISH = register(
        "flashlight_fish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val SQUIRRELFISH = register(
        "squirrelfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val GOURAMI = register(
        "gourami",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val BETTA = register(
        "betta",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val PEARLFISH = register(
        "pearlfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val SNAILFISH = register(
        "snailfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val DISCUS = register(
        "discus",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val DANIO = register(
        "danio",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val NEON_TETRA = register(
        "neon_tetra",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val TIGER_BARB = register(
        "tiger_barb",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val OSCAR = register(
        "oscar",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val BOXFISH = register(
        "boxfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .statusEffect(StatusEffectInstance(StatusEffects.POISON, 1200, 2), 1.0f)
                        .meat()
                        .build()
                )
        )
    )

    val KOI = register(
        "koi",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val CARP = register(
        "carp",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val GOLDFISH = register(
        "goldfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val SEAHORSE = register(
        "seahorse",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.2F)
                        .meat()
                        .build()
                )
        )
    )

    val TOADFISH = register(
        "toadfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.4F)
                        .statusEffect(StatusEffectInstance(StatusEffects.POISON, 1200, 1), 1.0f)
                        .meat()
                        .build()
                )
        )
    )

    val STONEFISH = register(
        "stonefish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(1)
                        .saturationModifier(0.4F)
                        .statusEffect(StatusEffectInstance(StatusEffects.POISON, 1200, 1), 1.0f)
                        .meat()
                        .build()
                )
        )
    )

    val LIONFISH = register(
        "lionfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .statusEffect(StatusEffectInstance(StatusEffects.POISON, 1200, 0), 1.0f)
                        .meat()
                        .build()
                )
        )
    )

    val ROCKFISH = register(
        "rockfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val SEA_BASS = register(
        "sea_bass",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val BLUE_SPOTTED_STINGRAY = register(
        "blue_spotted_stingray",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .statusEffect(StatusEffectInstance(StatusEffects.POISON, 1200, 0), 1.0f)
                        .meat()
                        .build()
                )
        )
    )

    val SPOTTED_EAGLE_RAY = register(
        "spotted_eagle_ray",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .statusEffect(StatusEffectInstance(StatusEffects.POISON, 1200, 0), 1.0f)
                        .meat()
                        .build()
                )
        )
    )

    val MORAY_EEL = register(
        "moray_eel",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val NEEDLEFISH = register(
        "needlefish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val RATFISH = register(
        "ratfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val TRIGGERFISH = register(
        "triggerfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val PARROTFISH = register(
        "parrotfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val COELACANTH = register(
        "coelacanth",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val GOLDEN_DORADO = register(
        "golden_dorado",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )

    val MAHI = register(
        "mahi",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(3)
                        .saturationModifier(1.8F)
                        .meat()
                        .build()
                )
        )
    )

    val YELLOWFIN_TUNA = register(
        "yellowfin_tuna",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(3)
                        .saturationModifier(1.8F)
                        .meat()
                        .build()
                )
        )
    )

    val BLUEFIN_TUNA = register(
        "bluefin_tuna",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(3)
                        .saturationModifier(1.8F)
                        .meat()
                        .build()
                )
        )
    )

    val OPAH = register(
        "opah",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(3)
                        .saturationModifier(1.8F)
                        .meat()
                        .build()
                )
        )
    )

    val OARFISH = register(
        "oarfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(3)
                        .saturationModifier(1.8F)
                        .meat()
                        .build()
                )
        )
    )

    val SUNFISH = register(
        "sunfish",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(3)
                        .saturationModifier(1.8F)
                        .meat()
                        .build()
                )
        )
    )

    //#endregion

    //#region Spawn Eggs

        //#region Fish

    val AFRICAN_BUTTERFLY_SPAWN_EGG = registerSpawnEgg("african_butterfly_spawn_egg", HybridAquaticEntityTypes.AFRICAN_BUTTERFLY, 0xb57955, 0x1e3555)
    val BARRELEYE_SPAWN_EGG = registerSpawnEgg("barreleye_spawn_egg", HybridAquaticEntityTypes.BARRELEYE, 0x4b4343, 0x6bc96c)
    val BETTA_SPAWN_EGG = registerSpawnEgg("betta_spawn_egg", HybridAquaticEntityTypes.BETTA, 0xcc425e, 0x504198)
    val DAMSELFISH_SPAWN_EGG = registerSpawnEgg("damselfish_spawn_egg", HybridAquaticEntityTypes.DAMSELFISH,0x96aba5, 0xf9d100)
    val CARP_SPAWN_EGG = registerSpawnEgg("carp_spawn_egg", HybridAquaticEntityTypes.CARP, 0x725234, 0xd3ad8c)
    val CLOWNFISH_SPAWN_EGG = registerSpawnEgg("clownfish_spawn_egg", HybridAquaticEntityTypes.CLOWNFISH, 0xff9166, 0xfdf7f9)
    val BOXFISH_SPAWN_EGG = registerSpawnEgg("boxfish_spawn_egg", HybridAquaticEntityTypes.BOXFISH, 0xfffeac, 0xffc056)
    val DANIO_SPAWN_EGG = registerSpawnEgg("danio_spawn_egg", HybridAquaticEntityTypes.DANIO, 0xdcdced, 0x2a3f52)
    val DISCUS_SPAWN_EGG = registerSpawnEgg("discus_spawn_egg", HybridAquaticEntityTypes.DISCUS, 0xeeeecd, 0xf4a957)
    val FLASHLIGHT_FISH_SPAWN_EGG = registerSpawnEgg("flashlight_fish_spawn_egg", HybridAquaticEntityTypes.FLASHLIGHT_FISH, 0x5c433e, 0xfffaa9)
    val SQUIRRELFISH_FISH_SPAWN_EGG = registerSpawnEgg("squirrelfish_spawn_egg", HybridAquaticEntityTypes.SQUIRRELFISH, 0x9b3f3d, 0xcfa184)
    val PEARLFISH_FISH_SPAWN_EGG = registerSpawnEgg("pearlfish_spawn_egg", HybridAquaticEntityTypes.PEARLFISH, 0x464c59, 0xc7cfd3)
    val FLYING_FISH_SPAWN_EGG = registerSpawnEgg("flying_fish_spawn_egg", HybridAquaticEntityTypes.FLYING_FISH, 0x7c93e1, 0xfbf7e6)
    val GOLDFISH_SPAWN_EGG = registerSpawnEgg("goldfish_spawn_egg", HybridAquaticEntityTypes.GOLDFISH, 0xefedf6, 0xff9166)
    val GOURAMI_SPAWN_EGG = registerSpawnEgg("gourami_spawn_egg", HybridAquaticEntityTypes.GOURAMI, 0x7bb6cf, 0x722a37)
    val LIONFISH_SPAWN_EGG = registerSpawnEgg("lionfish_spawn_egg", HybridAquaticEntityTypes.LIONFISH, 0xf9e6cf, 0xc64524)
    val MACKEREL_SPAWN_EGG = registerSpawnEgg("mackerel_spawn_egg", HybridAquaticEntityTypes.MACKEREL, 0x395562, 0xfff09c)
    val MAHI_SPAWN_EGG = registerSpawnEgg("mahi_spawn_egg", HybridAquaticEntityTypes.MAHI, 0x528c4e, 0xfffd69)
    val MANTA_RAY_SPAWN_EGG = registerSpawnEgg("manta_ray_spawn_egg", HybridAquaticEntityTypes.MANTA_RAY, 0x000000, 0xFFFFFF)
    val MORAY_EEL_SPAWN_EGG = registerSpawnEgg("moray_eel_spawn_egg", HybridAquaticEntityTypes.MORAY_EEL, 0x8da163, 0x1d4435)
    val NEEDLEFISH_SPAWN_EGG = registerSpawnEgg("needlefish_spawn_egg", HybridAquaticEntityTypes.NEEDLEFISH, 0xc0e4f7, 0x537da8)
    val OPAH_SPAWN_EGG = registerSpawnEgg("opah_spawn_egg", HybridAquaticEntityTypes.OPAH, 0x6472a7, 0xea6262)
    val OSCAR_SPAWN_EGG = registerSpawnEgg("oscar_spawn_egg", HybridAquaticEntityTypes.OSCAR, 0xd5c97e, 0x836136)
    val PARROTFISH_SPAWN_EGG = registerSpawnEgg("parrotfish_spawn_egg", HybridAquaticEntityTypes.PARROTFISH, 0x728e6b, 0xe5c5c3)
    val PIRANHA_SPAWN_EGG = registerSpawnEgg("piranha_spawn_egg", HybridAquaticEntityTypes.PIRANHA, 0x535f92, 0xaf3b3d)
    val ROCKFISH_SPAWN_EGG = registerSpawnEgg("rockfish_spawn_egg", HybridAquaticEntityTypes.ROCKFISH, 0x711b2f, 0xeb5948)
    val SEA_BASS_SPAWN_EGG = registerSpawnEgg("sea_bass_spawn_egg", HybridAquaticEntityTypes.SEA_BASS, 0x323337, 0xe7e8e8)
    val SEAHORSE_SPAWN_EGG = registerSpawnEgg("seahorse_spawn_egg", HybridAquaticEntityTypes.SEAHORSE, 0xffc9ab, 0xe63f5e)
    val STINGRAY_SPAWN_EGG = registerSpawnEgg("stingray_spawn_egg", HybridAquaticEntityTypes.STINGRAY, 0xffa214, 0x0069aa)
    val STONEFISH_SPAWN_EGG = registerSpawnEgg("stonefish_spawn_egg", HybridAquaticEntityTypes.STONEFISH, 0xaf8b68, 0x574435)
    val SUNFISH_SPAWN_EGG = registerSpawnEgg("sunfish_spawn_egg", HybridAquaticEntityTypes.SUNFISH, 0x687f96, 0x455764)
    val SURGEONFISH_SPAWN_EGG = registerSpawnEgg("surgeonfish_spawn_egg", HybridAquaticEntityTypes.SURGEONFISH, 0x88a1d7, 0x211b2f)
    val TETRA_SPAWN_EGG = registerSpawnEgg("tetra_spawn_egg", HybridAquaticEntityTypes.TETRA, 0x4eb1cc, 0xe64d43)
    val TIGER_BARB_SPAWN_EGG = registerSpawnEgg("tiger_barb_spawn_egg", HybridAquaticEntityTypes.TIGER_BARB, 0xfbbf2d, 0x611851)
    val TOADFISH_SPAWN_EGG = registerSpawnEgg("toadfish_spawn_egg", HybridAquaticEntityTypes.TOADFISH, 0xfcf2ce, 0x885e6d)
    val TRIGGERFISH_SPAWN_EGG = registerSpawnEgg("triggerfish_spawn_egg", HybridAquaticEntityTypes.TRIGGERFISH, 0x5b7c7e, 0xbdcdda)
    val TUNA_SPAWN_EGG = registerSpawnEgg("tuna_spawn_egg", HybridAquaticEntityTypes.TUNA, 0x36668d, 0xf5d58d)
    val GOLDEN_DORADO_SPAWN_EGG = registerSpawnEgg("golden_dorado_spawn_egg", HybridAquaticEntityTypes.GOLDEN_DORADO, 0xd16020, 0xa4975f)

        //#endregion

        //#region Deep Sea Fish

    val ANGLERFISH_SPAWN_EGG = registerSpawnEgg("anglerfish_spawn_egg", HybridAquaticEntityTypes.ANGLERFISH, 0x4b4257, 0xa7f1eb)
    val COELACANTH_SPAWN_EGG = registerSpawnEgg("coelacanth_spawn_egg", HybridAquaticEntityTypes.COELACANTH, 0x2f517a, 0xbac4d3)
    val DRAGONFISH_SPAWN_EGG = registerSpawnEgg("dragonfish_spawn_egg", HybridAquaticEntityTypes.DRAGONFISH, 0x2e2e33, 0xfffaa9)
    val JOHN_DORY_SPAWN_EGG = registerSpawnEgg("john_dory_spawn_egg", HybridAquaticEntityTypes.JOHN_DORY, 0xdcc6c6, 0x8a7f55)
    val SNAILFISH_SPAWN_EGG = registerSpawnEgg("snailfish_spawn_egg", HybridAquaticEntityTypes.SNAILFISH, 0xe0c2ed, 0xf0dcef)
    val OARFISH_SPAWN_EGG = registerSpawnEgg("oarfish_spawn_egg", HybridAquaticEntityTypes.OARFISH, 0x8892ab, 0xb04743)
    val RATFISH_SPAWN_EGG = registerSpawnEgg("ratfish_spawn_egg", HybridAquaticEntityTypes.RATFISH, 0xa16470, 0x673146)

        //#endregion

        //#region Cephalopod

    val CUTTLEFISH_SPAWN_EGG = registerSpawnEgg("cuttlefish_spawn_egg", HybridAquaticEntityTypes.CUTTLEFISH, 0x8a4836, 0xf6deae)
    val ARROW_SQUID_SPAWN_EGG = registerSpawnEgg("arrow_squid_spawn_egg", HybridAquaticEntityTypes.ARROW_SQUID, 0x761f31, 0xd56360)
    val FIREFLY_SQUID_SPAWN_EGG = registerSpawnEgg("firefly_squid_spawn_egg", HybridAquaticEntityTypes.FIREFLY_SQUID, 0xc93a61, 0x4ec0e8)

        //#endregion

        //#region Deep Sea Cephalopod

    val GLOWING_SUCKER_OCTOPUS_SPAWN_EGG = registerSpawnEgg("glowing_sucker_octopus_spawn_egg", HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS, 0x892f4f, 0x84d5fe)
    val NAUTILUS_SPAWN_EGG = registerSpawnEgg("nautilus_spawn_egg", HybridAquaticEntityTypes.NAUTILUS, 0xd4ccc3, 0xae4635)
    val UMBRELLA_OCTOPUS_SPAWN_EGG = registerSpawnEgg("umbrella_octopus_spawn_egg", HybridAquaticEntityTypes.UMBRELLA_OCTOPUS, 0xffaf25, 0xfeff92)
    val VAMPIRE_SQUID_SPAWN_EGG = registerSpawnEgg("vampire_squid_spawn_egg", HybridAquaticEntityTypes.VAMPIRE_SQUID, 0x73363c, 0xc3e9e2)

        //#endregion

        //#region Jellyfish

    val BARREL_JELLYFISH_SPAWN_EGG = registerSpawnEgg("barrel_jellyfish_spawn_egg", HybridAquaticEntityTypes.BARREL_JELLYFISH, 0xd6f3ea, 0x413c83)
    val BLUE_JELLYFISH_SPAWN_EGG = registerSpawnEgg("blue_jellyfish_spawn_egg", HybridAquaticEntityTypes.BLUE_JELLYFISH, 0x4dc0e8, 0xff6b97)
    val CAULIFLOWER_JELLYFISH_SPAWN_EGG = registerSpawnEgg("cauliflower_jellyfish_spawn_egg", HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH, 0x623062, 0x89a1d8)
    val COMPASS_JELLYFISH_SPAWN_EGG = registerSpawnEgg("compass_jellyfish_spawn_egg", HybridAquaticEntityTypes.COMPASS_JELLYFISH, 0xfcf9bd, 0xa16470)
    val FRIED_EGG_JELLYFISH_SPAWN_EGG = registerSpawnEgg("fried_egg_jellyfish_spawn_egg", HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH, 0xfbbf2d, 0x76445f)
    val LIONS_MANE_JELLYFISH_SPAWN_EGG = registerSpawnEgg("lions_mane_jellyfish_spawn_egg", HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH, 0xf6d5b1, 0x541e48)
    val MOON_JELLYFISH_SPAWN_EGG = registerSpawnEgg("moon_jellyfish_spawn_egg", HybridAquaticEntityTypes.MOON_JELLYFISH, 0xa293f3, 0xe0caf8)
    val NOMURA_JELLYFISH_SPAWN_EGG = registerSpawnEgg("nomura_jellyfish_spawn_egg", HybridAquaticEntityTypes.NOMURA_JELLYFISH, 0xe5dccf, 0x64353b)
    val SEA_NETTLE_SPAWN_EGG = registerSpawnEgg("sea_nettle_spawn_egg", HybridAquaticEntityTypes.SEA_NETTLE, 0xf7bc78, 0x76435f)
    val BOX_JELLYFISH_SPAWN_EGG = registerSpawnEgg("box_jellyfish_spawn_egg", HybridAquaticEntityTypes.BOX_JELLYFISH, 0x9ba6de, 0xebeff8)

        //#endregion

        //#region Deep Sea Jellyfish

    val MAUVE_STINGER_SPAWN_EGG = registerSpawnEgg("mauve_stinger_spawn_egg", HybridAquaticEntityTypes.MAUVE_STINGER, 0x633063, 0xbc787a)
    val ATOLLA_JELLYFISH_SPAWN_EGG = registerSpawnEgg("atolla_jellyfish_spawn_egg", HybridAquaticEntityTypes.ATOLLA_JELLYFISH, 0xa32858, 0x4dc0e8)
    val BIG_RED_JELLYFISH_SPAWN_EGG = registerSpawnEgg("big_red_jellyfish_spawn_egg", HybridAquaticEntityTypes.BIG_RED_JELLYFISH, 0xf4e5e5, 0xe72e46)
    val COSMIC_JELLYFISH_SPAWN_EGG = registerSpawnEgg("cosmic_jellyfish_spawn_egg", HybridAquaticEntityTypes.COSMIC_JELLYFISH, 0xe7debb, 0xffd375)
    val FIREWORK_JELLYFISH_SPAWN_EGG = registerSpawnEgg("firework_jellyfish_spawn_egg", HybridAquaticEntityTypes.FIREWORK_JELLYFISH, 0x6975e8, 0xfc7fb7)

        //#endregion

        //#region Crustaceans

    val COCONUT_CRAB_SPAWN_EGG = registerSpawnEgg("coconut_crab_spawn_egg", HybridAquaticEntityTypes.COCONUT_CRAB, 0x3e2d25, 0x3c546d)
    val DUNGENESS_CRAB_SPAWN_EGG = registerSpawnEgg("dungeness_crab_spawn_egg", HybridAquaticEntityTypes.DUNGENESS_CRAB, 0x81353f, 0xeecfce)
    val CRAYFISH_SPAWN_EGG = registerSpawnEgg("crayfish_spawn_egg", HybridAquaticEntityTypes.CRAYFISH, 0x697152, 0x7c4452)
    val FIDDLER_CRAB_SPAWN_EGG = registerSpawnEgg("fiddler_crab_spawn_egg", HybridAquaticEntityTypes.FIDDLER_CRAB, 0x80366b, 0xf39949)
    val FLOWER_CRAB_SPAWN_EGG = registerSpawnEgg("flower_crab_spawn_egg", HybridAquaticEntityTypes.FLOWER_CRAB, 0x9b8a6e, 0x20a094)
    val DECORATOR_CRAB_SPAWN_EGG = registerSpawnEgg("decorator_crab_spawn_egg", HybridAquaticEntityTypes.DECORATOR_CRAB, 0xffb570, 0x314fdd)
    val GHOST_CRAB_SPAWN_EGG = registerSpawnEgg("ghost_crab_spawn_egg", HybridAquaticEntityTypes.GHOST_CRAB, 0xf2be69, 0xf5fcd9)
    val HERMIT_CRAB_SPAWN_EGG = registerSpawnEgg("hermit_crab_spawn_egg", HybridAquaticEntityTypes.HERMIT_CRAB, 0xe97b13, 0xf2a65e)
    val HORSESHOE_CRAB_SPAWN_EGG = registerSpawnEgg("horseshoe_crab_spawn_egg", HybridAquaticEntityTypes.HORSESHOE_CRAB, 0x6e6b55, 0x403b31)
    val LIGHTFOOT_CRAB_SPAWN_EGG = registerSpawnEgg("lightfoot_crab_spawn_egg", HybridAquaticEntityTypes.LIGHTFOOT_CRAB, 0xb0305c, 0xff8c41)
    val LOBSTER_SPAWN_EGG = registerSpawnEgg("lobster_spawn_egg", HybridAquaticEntityTypes.LOBSTER, 0x421b2f, 0x8a4836)
    val SHRIMP_SPAWN_EGG = registerSpawnEgg("shrimp_spawn_egg", HybridAquaticEntityTypes.SHRIMP, 0xeb564b, 0xff9166)
    val VAMPIRE_CRAB_SPAWN_EGG = registerSpawnEgg("vampire_crab_spawn_egg", HybridAquaticEntityTypes.VAMPIRE_CRAB, 0x322947, 0x752053)

        //#endregion

        //#region Deep Sea Crustaceans

    val GIANT_ISOPOD_SPAWN_EGG = registerSpawnEgg("giant_isopod_spawn_egg", HybridAquaticEntityTypes.GIANT_ISOPOD, 0xe6d3d6, 0x3c2236)
    val SPIDER_CRAB_SPAWN_EGG = registerSpawnEgg("spider_crab_spawn_egg", HybridAquaticEntityTypes.SPIDER_CRAB, 0x9d3e41, 0xc6836f)
    val YETI_CRAB_SPAWN_EGG = registerSpawnEgg("yeti_crab_spawn_egg", HybridAquaticEntityTypes.YETI_CRAB, 0xfff4dd, 0xffd16b)

         //#endregion

        //#region Miniboss

    val KARKINOS_SPAWN_EGG = registerSpawnEgg("karkinos_spawn_egg", HybridAquaticEntityTypes.KARKINOS, 0x852c2a, 0x3d1031)

        //#endregion

        //#region Critters

    val NUDIBRANCH_SPAWN_EGG = registerSpawnEgg("nudibranch_spawn_egg", HybridAquaticEntityTypes.NUDIBRANCH, 0xf7be47, 0xb853a3)
    val SEA_CUCUMBER_SPAWN_EGG = registerSpawnEgg("sea_cucumber_spawn_egg", HybridAquaticEntityTypes.SEA_CUCUMBER, 0x225b6d, 0x0c2627)
    val SEA_URCHIN_SPAWN_EGG = registerSpawnEgg("sea_urchin_spawn_egg", HybridAquaticEntityTypes.SEA_URCHIN, 0x994066, 0x41142c)
    val STARFISH_SPAWN_EGG = registerSpawnEgg("starfish_spawn_egg", HybridAquaticEntityTypes.STARFISH, 0x994066, 0x592645)
    val SEA_ANGEL_SPAWN_EGG = registerSpawnEgg("sea_angel_spawn_egg", HybridAquaticEntityTypes.SEA_ANGEL, 0xc6d5f9, 0xf38135)

        //#endregion

      //#region Sharks

    val BASKING_SHARK_SPAWN_EGG = registerSpawnEgg("basking_shark_spawn_egg", HybridAquaticEntityTypes.BASKING_SHARK, 0x6a6558, 0xb5b3a6)
    val BULL_SHARK_SPAWN_EGG = registerSpawnEgg("bull_shark_spawn_egg", HybridAquaticEntityTypes.BULL_SHARK, 0x5d6b7a, 0xb4c1c6)
    val FRILLED_SHARK_SPAWN_EGG = registerSpawnEgg("frilled_shark_spawn_egg", HybridAquaticEntityTypes.FRILLED_SHARK, 0x5a4d50, 0x3a2f31)
    val LANTERN_SHARK_SPAWN_EGG = registerSpawnEgg("lantern_shark_spawn_egg", HybridAquaticEntityTypes.LANTERN_SHARK, 0x543f46, 0x84d5fe)
    val GREAT_WHITE_SHARK_SPAWN_EGG = registerSpawnEgg("great_white_shark_spawn_egg", HybridAquaticEntityTypes.GREAT_WHITE_SHARK, 0x5e6e7d, 0xf3f3f8)
    val HAMMERHEAD_SHARK_SPAWN_EGG = registerSpawnEgg("hammerhead_shark_spawn_egg", HybridAquaticEntityTypes.HAMMERHEAD_SHARK, 0x78909a, 0xd7e1dd)
    val THRESHER_SHARK_SPAWN_EGG = registerSpawnEgg("thresher_shark_spawn_egg", HybridAquaticEntityTypes.THRESHER_SHARK, 0x5591af, 0xd7e1dd)
    val TIGER_SHARK_SPAWN_EGG = registerSpawnEgg("tiger_shark_spawn_egg", HybridAquaticEntityTypes.TIGER_SHARK, 0xb79167, 0xf0f3e6)
    val WHALE_SHARK_SPAWN_EGG = registerSpawnEgg("whale_shark_spawn_egg", HybridAquaticEntityTypes.WHALE_SHARK, 0x4c6d98, 0xeff0f4)

        //#endregion

    //#endregion

    private fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier(HybridAquatic.MOD_ID, id), item)
    }

    private fun <T : MobEntity> registerSpawnEgg(
        id: String,
        type: EntityType<T>,
        primaryColor: Int,
        secondaryColor: Int
    ): Item {
        return register(id, SpawnEggItem(type, primaryColor, secondaryColor, FabricItemSettings()))
    }

    private fun registerBlockItem(id: String, block: Block): Item {
        return register(id, BlockItem(block, FabricItemSettings()))
    }

    private fun registerPlaceableInWaterBlockItem(id: String, block: Block): Item {
        return register(id, PlaceableInWaterItem(block, FabricItemSettings()))
    }

    private fun registerVerticallyAttachable(
        id: String,
        standingBlock: Block,
        wallBlock: Block,
        direction: Direction = Direction.DOWN
    ): Item {
        return register(id, VerticallyAttachableBlockItem(standingBlock, wallBlock, FabricItemSettings(), direction))
    }
}
