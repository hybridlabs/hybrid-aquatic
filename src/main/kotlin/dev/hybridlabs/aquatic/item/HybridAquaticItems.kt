@file:Suppress("unused")

package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.FishingPlaque
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.PlaceableOnWaterItem
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticItems {
    val ANEMONE = registerBlockItem("anemone", HybridAquaticBlocks.ANEMONE)
    val FISHING_PLAQUE = registerBlockItem("fishing_plaque", HybridAquaticBlocks.FISHING_PLAQUE)
    val BUOY = registerPlaceableInWaterBlockItem("buoy", HybridAquaticBlocks.BUOY)
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleItem(FabricItemSettings()))

    val FISHING_NET = register("fishing_net", FishingNetItem(FabricItemSettings().maxCount(1)))

    val GLOW_SLIME = register("glow_slime", Item(FabricItemSettings()))
    val SHARK_TOOTH = register("shark_tooth", Item(FabricItemSettings()))

    // pearls
    val PEARL = register("pearl", Item(FabricItemSettings()))
    val BLACK_PEARL = register("black_pearl", Item(FabricItemSettings()))

    val CRAB_CLAW = register("crab_claw", Item(FabricItemSettings()))

    // food items
    val COOKED_CRAB_MEAT = register("cooked_crab_meat",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(6)
                        .saturationModifier(0.8F)
                        .meat()
                        .build()
                )
        )
    )
    val CRAB_MEAT = register("crab_meat",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(2)
                        .saturationModifier(0.8F)
                        .meat()
                        .build()
                )
        )
    )
    val COOKED_FISH_MEAT = register("cooked_fish_meat",
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
    val FISH_MEAT = register("fish_meat",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(4)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )
    val COOKED_SMALL_FISH_MEAT = register("cooked_small_fish_meat",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(4)
                        .saturationModifier(0.4F)
                        .meat()
                        .build()
                )
        )
    )
    val SMALL_FISH_MEAT = register("small_fish_meat",
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
    val COOKED_TENTACLE = register("cooked_tentacle",
        Item(
            FabricItemSettings()
                .food(
                    FoodComponent.Builder()
                        .hunger(4)
                        .saturationModifier(0.8F)
                        .meat()
                        .build()
                )
        )
    )
    val TENTACLE = register("tentacle",
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
    val LIONFISH = register("lionfish",
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
    val MAHI_MAHI = register("mahi_mahi",
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
    val YELLOWFIN_TUNA = register("yellowfin_tuna",
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
    val OPAH = register("opah",
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
    val ROCKFISH = register("rockfish",
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
    val BLUE_SPOTTED_STINGRAY = register("blue_spotted_stingray",
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
    val MORAY_EEL = register("moray_eel",
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
    val NEEDLEFISH = register("needlefish",
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
    val PIRANHA = register("piranha",
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
    val ANGLERFISH = register("anglerfish",
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
    val BARRELEYE = register("barreleye",
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
    val BLUE_TANG = register("blue_tang",
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
    val CLOWNFISH = register("clownfish",
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
    val UNICORN_FISH = register("unicorn_fish",
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
    val TIGER_BARB = register("tiger_barb",
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
    val TRIGGERFISH = register("triggerfish",
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
    val OSCAR = register("oscar",
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
    val COWFISH = register("cowfish",
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

    // plushies
    val BASKING_SHARK_PLUSHIE = registerBlockItem("basking_shark_plushie", HybridAquaticBlocks.BASKING_SHARK_PLUSHIE)
    val BULL_SHARK_PLUSHIE = registerBlockItem("bull_shark_plushie", HybridAquaticBlocks.BULL_SHARK_PLUSHIE)
    val FRILLED_SHARK_PLUSHIE = registerBlockItem("frilled_shark_plushie", HybridAquaticBlocks.FRILLED_SHARK_PLUSHIE)
    val GREAT_WHITE_SHARK_PLUSHIE = registerBlockItem("great_white_shark_plushie", HybridAquaticBlocks.GREAT_WHITE_SHARK_PLUSHIE)
    val HAMMERHEAD_SHARK_PLUSHIE = registerBlockItem("hammerhead_shark_plushie", HybridAquaticBlocks.HAMMERHEAD_SHARK_PLUSHIE)
    val THRESHER_SHARK_PLUSHIE = registerBlockItem("thresher_shark_plushie", HybridAquaticBlocks.THRESHER_SHARK_PLUSHIE)
    val TIGER_SHARK_PLUSHIE = registerBlockItem("tiger_shark_plushie", HybridAquaticBlocks.TIGER_SHARK_PLUSHIE)
    val WHALE_SHARK_PLUSHIE = registerBlockItem("whale_shark_plushie", HybridAquaticBlocks.WHALE_SHARK_PLUSHIE)

    val CRATE = registerBlockItem("crate", HybridAquaticBlocks.CRATE)

    // fish spawn eggs
    val CLOWNFISH_SPAWN_EGG = registerSpawnEgg("clownfish_spawn_egg", HybridAquaticEntityTypes.CLOWNFISH, 0xea6c36, 0xfdf7f9)
    val ANGLERFISH_SPAWN_EGG = registerSpawnEgg("anglerfish_spawn_egg", HybridAquaticEntityTypes.ANGLERFISH, 0x4d4848, 0xc4faff)
    val BARRELEYE_SPAWN_EGG = registerSpawnEgg("barreleye_spawn_egg", HybridAquaticEntityTypes.BARRELEYE, 0x543d34, 0x95f649)
    val YELLOWFIN_TUNA_SPAWN_EGG = registerSpawnEgg("yellowfin_tuna_spawn_egg", HybridAquaticEntityTypes.YELLOWFIN_TUNA, 0x36668d, 0xf5d58d)
    val CUTTLEFISH_SPAWN_EGG = registerSpawnEgg("cuttlefish_spawn_egg", HybridAquaticEntityTypes.CUTTLEFISH, 0x8a4836, 0xf9e6cf)
    val FLASHLIGHT_FISH_SPAWN_EGG = registerSpawnEgg("flashlight_fish_spawn_egg", HybridAquaticEntityTypes.FLASHLIGHT_FISH, 0x84576b, 0xf5d58d)
    val LIONFISH_SPAWN_EGG = registerSpawnEgg("lionfish_spawn_egg", HybridAquaticEntityTypes.LIONFISH, 0xf9e6cf, 0xc64524)
    val OARFISH_SPAWN_EGG = registerSpawnEgg("oarfish_spawn_egg", HybridAquaticEntityTypes.OARFISH, 0x8892ab, 0xb04743)
    val OPAH_SPAWN_EGG = registerSpawnEgg("opah_spawn_egg", HybridAquaticEntityTypes.OPAH, 0x6472a7, 0xea6262)
    val PIRANHA_SPAWN_EGG = registerSpawnEgg("piranha_spawn_egg", HybridAquaticEntityTypes.PIRANHA, 0x535f92, 0xaf3b3d)
    val SEA_ANGEL_SPAWN_EGG = registerSpawnEgg("sea_angel_spawn_egg", HybridAquaticEntityTypes.SEA_ANGEL, 0xc6d5f9, 0xf38135)
    val SUNFISH_SPAWN_EGG = registerSpawnEgg("sunfish_spawn_egg", HybridAquaticEntityTypes.SUNFISH, 0x687f96, 0x455764)
    val VAMPIRE_SQUID_SPAWN_EGG = registerSpawnEgg("vampire_squid_spawn_egg", HybridAquaticEntityTypes.VAMPIRE_SQUID, 0x73363c, 0xc3e9e2)
    val MAHIMAHI_SPAWN_EGG = registerSpawnEgg("mahimahi_spawn_egg", HybridAquaticEntityTypes.MAHIMAHI, 0x356c69, 0xd7e482)
    val MORAY_EEL_SPAWN_EGG = registerSpawnEgg("moray_eel_spawn_egg", HybridAquaticEntityTypes.MORAY_EEL, 0x8da163, 0x1d4435)
    val ROCKFISH_SPAWN_EGG = registerSpawnEgg("rockfish_spawn_egg", HybridAquaticEntityTypes.ROCKFISH, 0xa52d59, 0xe5aca0)
    val TIGER_BARB_SPAWN_EGG = registerSpawnEgg("tiger_barb_spawn_egg", HybridAquaticEntityTypes.TIGER_BARB, 0xfbbf2d, 0x611851)
    val NEEDLEFISH_SPAWN_EGG = registerSpawnEgg("needlefish_spawn_egg", HybridAquaticEntityTypes.NEEDLEFISH, 0xc0e4f7, 0x537da8)
    val RATFISH_SPAWN_EGG = registerSpawnEgg("ratfish_spawn_egg", HybridAquaticEntityTypes.RATFISH, 0xa16470, 0x673146)
    val NAUTILUS_SPAWN_EGG = registerSpawnEgg("nautilus_spawn_egg", HybridAquaticEntityTypes.NAUTILUS, 0xd4ccc3, 0xae4635)
    val TRIGGERFISH_SPAWN_EGG = registerSpawnEgg("triggerfish_spawn_egg", HybridAquaticEntityTypes.TRIGGERFISH, 0x5b7c7e, 0xbdcdda)
    val OSCAR_SPAWN_EGG = registerSpawnEgg("oscar_spawn_egg", HybridAquaticEntityTypes.OSCAR, 0xd5c97e, 0x836136)
    val UNICORN_FISH_SPAWN_EGG = registerSpawnEgg("unicorn_fish_spawn_egg", HybridAquaticEntityTypes.UNICORN_FISH, 0xf0f0f0, 0x7b5480)
    val ZEBRA_DANIO_SPAWN_EGG = registerSpawnEgg("zebra_danio_spawn_egg", HybridAquaticEntityTypes.ZEBRA_DANIO, 0xdcdced, 0x2a3f52)
    val TOADFISH_SPAWN_EGG = registerSpawnEgg("toadfish_spawn_egg", HybridAquaticEntityTypes.TOADFISH, 0xfcf2ce, 0x885e6d)
    val TETRA_SPAWN_EGG = registerSpawnEgg("tetra_spawn_egg", HybridAquaticEntityTypes.TETRA, 0x4eb1cc, 0xe64d43)
    val STONEFISH_SPAWN_EGG = registerSpawnEgg("stonefish_spawn_egg", HybridAquaticEntityTypes.STONEFISH, 0xaf8b68, 0x574435)
    val BETTA_SPAWN_EGG = registerSpawnEgg("betta_spawn_egg", HybridAquaticEntityTypes.BETTA, 0xba3569, 0x581f45)
    val SEAHORSE_SPAWN_EGG = registerSpawnEgg("seahorse_spawn_egg", HybridAquaticEntityTypes.SEAHORSE, 0xffc9ab, 0xe63f5e)
    val GOURAMI_SPAWN_EGG = registerSpawnEgg("gourami_spawn_egg", HybridAquaticEntityTypes.GOURAMI, 0x7bb6cf, 0x722a37)
    val COWFISH_SPAWN_EGG = registerSpawnEgg("cowfish_spawn_egg", HybridAquaticEntityTypes.COWFISH, 0xfffeac, 0xffc056)
    val GLOWING_SUCKER_OCTOPUS_SPAWN_EGG = registerSpawnEgg("glowing_sucker_octopus_spawn_egg", HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS, 0x892f4f, 0x84d5fe)
    val DISCUS_SPAWN_EGG = registerSpawnEgg("discus_spawn_egg", HybridAquaticEntityTypes.DISCUS, 0xeeeecd, 0xf4a957)
    val FIREFLY_SQUID_SPAWN_EGG = registerSpawnEgg("firefly_squid_spawn_egg", HybridAquaticEntityTypes.FIREFLY_SQUID, 0x6793cc, 0x84d5fe)
    val BLUE_SPOTTED_STINGRAY_SPAWN_EGG = registerSpawnEgg("blue_spotted_stingray_spawn_egg", HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY, 0xecbf52, 0x2563a4)
    val DRAGONFISH_SPAWN_EGG = registerSpawnEgg("dragonfish_spawn_egg", HybridAquaticEntityTypes.DRAGONFISH, 0x4d4455, 0xe9d0cf)
    val BLUE_TANG_SPAWN_EGG = registerSpawnEgg("blue_tang_spawn_egg", HybridAquaticEntityTypes.BLUE_TANG, 0x88a1d7, 0x211b2f)
    val MOON_JELLYFISH_SPAWN_EGG = registerSpawnEgg("moon_jellyfish_spawn_egg", HybridAquaticEntityTypes.MOON_JELLYFISH, 0xa293f3, 0xe0caf8)
    val SEA_NETTLE_SPAWN_EGG = registerSpawnEgg("sea_nettle_spawn_egg", HybridAquaticEntityTypes.SEA_NETTLE, 0xf7bc78, 0x76435f)
    val FRIED_EGG_JELLYFISH_SPAWN_EGG = registerSpawnEgg("fried_egg_jellyfish_spawn_egg", HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH, 0xfbbf2d, 0x76445f)
    val CAULIFLOWER_JELLYFISH_SPAWN_EGG = registerSpawnEgg("cauliflower_jellyfish_spawn_egg", HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH, 0x623062, 0x89a1d8)
    val NOMURA_JELLYFISH_SPAWN_EGG = registerSpawnEgg("nomura_jellyfish_spawn_egg", HybridAquaticEntityTypes.NOMURA_JELLYFISH, 0xe5dccf, 0x64353b)
    val BLUE_JELLYFISH_SPAWN_EGG = registerSpawnEgg("blue_jellyfish_spawn_egg", HybridAquaticEntityTypes.BLUE_JELLYFISH, 0x4dc0e8, 0xff6b97)
    val MAUVE_STINGER_SPAWN_EGG = registerSpawnEgg("mauve_stinger_spawn_egg", HybridAquaticEntityTypes.MAUVE_STINGER, 0x633063, 0xbc787a)
    val BARREL_JELLYFISH_SPAWN_EGG = registerSpawnEgg("barrel_jellyfish_spawn_egg", HybridAquaticEntityTypes.BARREL_JELLYFISH, 0xd6f3ea, 0x413c83)
    val COMPASS_JELLYFISH_SPAWN_EGG = registerSpawnEgg("compass_jellyfish_spawn_egg", HybridAquaticEntityTypes.COMPASS_JELLYFISH, 0xfcf9bd, 0xa16470)
    val LIONS_MANE_JELLYFISH_SPAWN_EGG = registerSpawnEgg("lions_mane_jellyfish_spawn_egg", HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH, 0xf6d5b1, 0x541e48)
    val ATOLLA_JELLYFISH_SPAWN_EGG = registerSpawnEgg("atolla_jellyfish_spawn_egg", HybridAquaticEntityTypes.ATOLLA_JELLYFISH, 0xa32858, 0x4dc0e8)

    // critter spawn eggs
    val CRAB_SPAWN_EGG = registerSpawnEgg("crab_spawn_egg", HybridAquaticEntityTypes.CRAB, 0xb85249, 0xf1e4db)
    val FIDDLER_CRAB_SPAWN_EGG = registerSpawnEgg("fiddler_crab_spawn_egg", HybridAquaticEntityTypes.FIDDLER_CRAB, 0xcf552f, 0xe2dea8)
    val HERMIT_CRAB_SPAWN_EGG = registerSpawnEgg("hermit_crab_spawn_egg", HybridAquaticEntityTypes.HERMIT_CRAB, 0xedab50, 0x8a4836)
    val GHOST_CRAB_SPAWN_EGG = registerSpawnEgg("ghost_crab_spawn_egg", HybridAquaticEntityTypes.GHOST_CRAB, 0xedab50, 0x8a4836)
    val FLOWER_CRAB_SPAWN_EGG = registerSpawnEgg("flower_crab_spawn_egg", HybridAquaticEntityTypes.FLOWER_CRAB, 0xedab50, 0x8a4836)
    val LIGHTFOOT_CRAB_SPAWN_EGG = registerSpawnEgg("lightfoot_crab_spawn_egg", HybridAquaticEntityTypes.LIGHTFOOT_CRAB, 0xedab50, 0x8a4836)
    val HORSESHOE_CRAB_SPAWN_EGG = registerSpawnEgg("horseshoe_crab_spawn_egg", HybridAquaticEntityTypes.HORSESHOE_CRAB, 0xedab50, 0x8a4836)
    val SPIDER_CRAB_SPAWN_EGG = registerSpawnEgg("spider_crab_spawn_egg", HybridAquaticEntityTypes.SPIDER_CRAB, 0xedab50, 0x8a4836)
    val YETI_CRAB_SPAWN_EGG = registerSpawnEgg("yeti_crab_spawn_egg", HybridAquaticEntityTypes.YETI_CRAB, 0xedab50, 0x8a4836)
    val VAMPIRE_CRAB_SPAWN_EGG = registerSpawnEgg("vampire_crab_spawn_egg", HybridAquaticEntityTypes.VAMPIRE_CRAB, 0xedab50, 0x8a4836)
    val GIANT_ISOPOD_SPAWN_EGG = registerSpawnEgg("giant_isopod_crab_spawn_egg", HybridAquaticEntityTypes.GIANT_ISOPOD, 0xedab50, 0x8a4836)
    val COCONUT_CRAB_SPAWN_EGG = registerSpawnEgg("coconut_crab_spawn_egg", HybridAquaticEntityTypes.COCONUT_CRAB, 0xedab50, 0x8a4836)
    val SHRIMP_SPAWN_EGG = registerSpawnEgg("shrimp_spawn_egg", HybridAquaticEntityTypes.SHRIMP, 0xedab50, 0x8a4836)
    val MANTIS_SHRIMP_SPAWN_EGG = registerSpawnEgg("mantis_shrimp_spawn_egg", HybridAquaticEntityTypes.MANTIS_SHRIMP, 0xedab50, 0x8a4836)
    val CRAYFISH_SPAWN_EGG = registerSpawnEgg("crayfish_spawn_egg", HybridAquaticEntityTypes.CRAYFISH, 0xedab50, 0x8a4836)
    val STARFISH_SPAWN_EGG = registerSpawnEgg("starfish_spawn_egg", HybridAquaticEntityTypes.STARFISH, 0x994066, 0x592645)
    val SEA_CUCUMBER_SPAWN_EGG = registerSpawnEgg("sea_cucumber_spawn_egg", HybridAquaticEntityTypes.SEA_CUCUMBER, 0x225b6d, 0x0c2627)
    val NUDIBRANCH_SPAWN_EGG = registerSpawnEgg("nudibranch_spawn_egg", HybridAquaticEntityTypes.NUDIBRANCH, 0xf7be47, 0xb853a3)
    val SEA_URCHIN_SPAWN_EGG = registerSpawnEgg("sea_urchin_spawn_egg", HybridAquaticEntityTypes.SEA_URCHIN, 0x994066, 0x41142c)
    val GIANT_CLAM_SPAWN_EGG = registerSpawnEgg("giant_clam_spawn_egg", HybridAquaticEntityTypes.GIANT_CLAM, 0xd4d4c8, 0x1b4377)

    // shark spawn eggs
    val BULL_SHARK_SPAWN_EGG = registerSpawnEgg("bull_shark_spawn_egg", HybridAquaticEntityTypes.BULL_SHARK, 0x676b8d, 0xd0ccda)
    val BASKING_SHARK_SPAWN_EGG = registerSpawnEgg("basking_shark_spawn_egg", HybridAquaticEntityTypes.BASKING_SHARK, 0x725e6b, 0x201b1b)
    val THRESHER_SHARK_SPAWN_EGG = registerSpawnEgg("thresher_shark_spawn_egg", HybridAquaticEntityTypes.THRESHER_SHARK, 0x4b9ebf, 0xd2efed)
    val FRILLED_SHARK_SPAWN_EGG = registerSpawnEgg("frilled_shark_spawn_egg", HybridAquaticEntityTypes.FRILLED_SHARK, 0x201b1b, 0x4a3d47)
    val GREAT_WHITE_SHARK_SPAWN_EGG = registerSpawnEgg("great_white_shark_spawn_egg", HybridAquaticEntityTypes.GREAT_WHITE_SHARK, 0x283a39, 0xd4cdde)
    val TIGER_SHARK_SPAWN_EGG = registerSpawnEgg("tiger_shark_spawn_egg", HybridAquaticEntityTypes.TIGER_SHARK, 0x3e3943, 0xf4f2f3)
    val HAMMERHEAD_SHARK_SPAWN_EGG = registerSpawnEgg("hammerhead_shark_spawn_egg", HybridAquaticEntityTypes.HAMMERHEAD_SHARK, 0x889bac, 0xf1edf6)
    val WHALE_SHARK_SPAWN_EGG = registerSpawnEgg("whale_shark_spawn_egg", HybridAquaticEntityTypes.WHALE_SHARK, 0x1a1932, 0xffffff)

    val BARBED_HOOK = register("barbed_hook", HookItem(Item.Settings().maxDamage(32)))
    val GLOWING_HOOK = register("glowing_hook", HookItem(Item.Settings().maxDamage(32)))
    val MAGNETIC_HOOK = register("magnetic_hook", HookItem(Item.Settings().maxDamage(8)))

    private fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier(HybridAquatic.MOD_ID, id), item)
    }

    private fun <T : MobEntity> registerSpawnEgg(id: String, type: EntityType<T>, primaryColor: Int, secondaryColor: Int): Item {
        return register(id, SpawnEggItem(type, primaryColor, secondaryColor, FabricItemSettings()))
    }

    private fun registerBlockItem(id: String, block: Block): Item {
        return register(id, BlockItem(block, FabricItemSettings()))
    }

    private fun registerPlaceableInWaterBlockItem(id: String, block: Block): Item {
        return register(id, PlaceableInWaterItem(block, FabricItemSettings()))
    }
}
