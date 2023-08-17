@file:Suppress("unused")

package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticItems {
    val ANEMONE = registerBlockItem("anemone", HybridAquaticBlocks.ANEMONE)
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleItem(FabricItemSettings()))

    val BASKING_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("basking_shark_blahaj_plushie", HybridAquaticBlocks.BASKING_SHARK_BLAHAJ_PLUSHIE)
    val BULL_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("bull_shark_blahaj_plushie", HybridAquaticBlocks.BULL_SHARK_BLAHAJ_PLUSHIE)
    val FRILLED_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("frilled_shark_blahaj_plushie", HybridAquaticBlocks.FRILLED_SHARK_BLAHAJ_PLUSHIE)
    val GREAT_WHITE_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("great_white_shark_blahaj_plushie", HybridAquaticBlocks.GREAT_WHITE_SHARK_BLAHAJ_PLUSHIE)
    val HAMMERHEAD_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("hammerhead_shark_blahaj_plushie", HybridAquaticBlocks.HAMMERHEAD_SHARK_BLAHAJ_PLUSHIE)
    val THRESHER_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("thresher_shark_blahaj_plushie", HybridAquaticBlocks.THRESHER_SHARK_BLAHAJ_PLUSHIE)
    val TIGER_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("tiger_shark_blahaj_plushie", HybridAquaticBlocks.TIGER_SHARK_BLAHAJ_PLUSHIE)
    val WHALE_SHARK_BLAHAJ_PLUSHIE = registerBlockItem("whale_shark_blahaj_plushie", HybridAquaticBlocks.WHALE_SHARK_BLAHAJ_PLUSHIE)

    val CLOWNFISH_SPAWN_EGG = registerSpawnEgg("clownfish_spawn_egg", HybridAquaticEntityTypes.CLOWNFISH, 0xff5000, 0xffffff)
    val ANGLERFISH_SPAWN_EGG = registerSpawnEgg("anglerfish_spawn_egg", HybridAquaticEntityTypes.ANGLERFISH, 0x4d4848, 0xc4faff)
    val BARRELEYE_SPAWN_EGG = registerSpawnEgg("barreleye_spawn_egg", HybridAquaticEntityTypes.BARRELEYE, 0x543d34, 0x95f649)
    val YELLOWFIN_TUNA_SPAWN_EGG = registerSpawnEgg("yellowfin_tuna_spawn_egg", HybridAquaticEntityTypes.YELLOWFIN_TUNA, 0x18143e, 0xffdc1f
    val CUTTLEFISH_SPAWN_EGG = registerSpawnEgg("cuttlefish_spawn_egg", HybridAquaticEntityTypes.CUTTLEFISH, 0x8a4836, 0xf9e6cf)
    val FLASHLIGHT_FISH_SPAWN_EGG = registerSpawnEgg("flashlight_fish_spawn_egg", HybridAquaticEntityTypes.FLASHLIGHT_FISH, 0, 0)
    val LIONFISH_SPAWN_EGG = registerSpawnEgg("lionfish_spawn_egg", HybridAquaticEntityTypes.LIONFISH, 0xf9e6cf, 0xc64524)
    val OARFISH_SPAWN_EGG = registerSpawnEgg("oarfish_spawn_egg", HybridAquaticEntityTypes.OARFISH, 0x8892ab, 0xb04743)
    val OPAH_SPAWN_EGG = registerSpawnEgg("opah_spawn_egg", HybridAquaticEntityTypes.OPAH, 0x868dbb, 0x9e2a38)
    val PIRANHA_SPAWN_EGG = registerSpawnEgg("piranha_spawn_egg", HybridAquaticEntityTypes.PIRANHA, 0, 0)
    val SEA_ANGEL_SPAWN_EGG = registerSpawnEgg("sea_angel_spawn_egg", HybridAquaticEntityTypes.SEA_ANGEL, 0, 0)
    val SUNFISH_SPAWN_EGG = registerSpawnEgg("sunfish_spawn_egg", HybridAquaticEntityTypes.SUNFISH, 0, 0)
    val VAMPIRE_SQUID_SPAWN_EGG = registerSpawnEgg("vampire_squid_spawn_egg", HybridAquaticEntityTypes.VAMPIRE_SQUID, 0x8a353b, 0x68faf3)
    val MAHIMAHI_SPAWN_EGG = registerSpawnEgg("mahimahi_spawn_egg", HybridAquaticEntityTypes.MAHIMAHI, 0x2ab742, 0x07334f)
    val MORAY_EEL_SPAWN_EGG = registerSpawnEgg("moray_eel_spawn_egg", HybridAquaticEntityTypes.MORAY_EEL, 0x543d34, 0x134c4c)
    val ROCKFISH_SPAWN_EGG = registerSpawnEgg("rockfish_spawn_egg", HybridAquaticEntityTypes.ROCKFISH, 0xc22a27, 0xe49a7b)
    val TIGER_BARB_SPAWN_EGG = registerSpawnEgg("tiger_barb_spawn_egg", HybridAquaticEntityTypes.TIGER_BARB, 0xf7be47, 0x38316b)
    val NEEDLEFISH_SPAWN_EGG = registerSpawnEgg("needlefish_spawn_egg", HybridAquaticEntityTypes.NEEDLEFISH, 0x4493c2, 0xe64d43)
    val RATFISH_SPAWN_EGG = registerSpawnEgg("ratfish_spawn_egg", HybridAquaticEntityTypes.RATFISH, 0, 0)
    val NAUTILUS_SPAWN_EGG = registerSpawnEgg("nautilus_spawn_egg", HybridAquaticEntityTypes.NAUTILUS, 0, 0)
    val TRIGGERFISH_SPAWN_EGG = registerSpawnEgg("triggerfish_spawn_egg", HybridAquaticEntityTypes.TRIGGERFISH, 0, 0)
    val OSCAR_SPAWN_EGG = registerSpawnEgg("oscar_spawn_egg", HybridAquaticEntityTypes.OSCAR, 0, 0)
    val UNICORN_FISH_SPAWN_EGG = registerSpawnEgg("unicorn_fish_spawn_egg", HybridAquaticEntityTypes.UNICORN_FISH, 0, 0)
    val ZEBRA_DANIO_SPAWN_EGG = registerSpawnEgg("zebra_danio_spawn_egg", HybridAquaticEntityTypes.ZEBRA_DANIO, 0xdcdced, 0x2a3f52)
    val TOADFISH_SPAWN_EGG = registerSpawnEgg("toadfish_spawn_egg", HybridAquaticEntityTypes.TOADFISH, 0, 0)
    val TETRA_SPAWN_EGG = registerSpawnEgg("tetra_spawn_egg", HybridAquaticEntityTypes.TETRA, 0x4eb1cc, 0xe64d43)
    val STONEFISH_SPAWN_EGG = registerSpawnEgg("stonefish_spawn_egg", HybridAquaticEntityTypes.STONEFISH, 0, 0)
    val BETTA_SPAWN_EGG = registerSpawnEgg("betta_spawn_egg", HybridAquaticEntityTypes.BETTA, 0, 0)
    val SEAHORSE_SPAWN_EGG = registerSpawnEgg("seahorse_spawn_egg", HybridAquaticEntityTypes.SEAHORSE, 0, 0)
    val MOON_JELLY_SPAWN_EGG = registerSpawnEgg("moon_jelly_spawn_egg", HybridAquaticEntityTypes.MOON_JELLY, 0, 0)
    val GOURAMI_SPAWN_EGG = registerSpawnEgg("gourami_spawn_egg", HybridAquaticEntityTypes.GOURAMI, 0, 0)
    val COWFISH_SPAWN_EGG = registerSpawnEgg("cowfish_spawn_egg", HybridAquaticEntityTypes.COWFISH, 0xffa214, 0xfff8b4)
    val GLOWING_SUCKER_OCTOPUS_SPAWN_EGG = registerSpawnEgg("glowing_sucker_octopus_spawn_egg", HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS, 0, 0)
    val DISCUS_SPAWN_EGG = registerSpawnEgg("discus_spawn_egg", HybridAquaticEntityTypes.DISCUS, 0, 0)
    val FIREFLY_SQUID_SPAWN_EGG = registerSpawnEgg("firefly_squid_spawn_egg", HybridAquaticEntityTypes.FIREFLY_SQUID, 0xffc825, 0x0069aa)
    val BLUE_SPOTTED_STINGRAY_SPAWN_EGG = registerSpawnEgg("blue_spotted_stingray_spawn_egg", HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY, 0xffc825, 0x0098dc)
    val BLUE_TANG_SPAWN_EGG = registerSpawnEgg("blue_tang_spawn_egg", HybridAquaticEntityTypes.BLUE_TANG, 0x00cdf9, 0xffa214)
    val BULL_SHARK_SPAWN_EGG = registerSpawnEgg("bull_shark_spawn_egg", HybridAquaticEntityTypes.BULL_SHARK, 0, 0)
    val BASKING_SHARK_SPAWN_EGG = registerSpawnEgg("basking_shark_spawn_egg", HybridAquaticEntityTypes.BASKING_SHARK, 0, 0)
    val THRESHER_SHARK_SPAWN_EGG = registerSpawnEgg("thresher_shark_spawn_egg", HybridAquaticEntityTypes.THRESHER_SHARK, 0, 0)
    val FRILLED_SHARK_SPAWN_EGG = registerSpawnEgg("frilled_shark_spawn_egg", HybridAquaticEntityTypes.FRILLED_SHARK, 0, 0)
    val GREAT_WHITE_SHARK_SPAWN_EGG = registerSpawnEgg("great_white_shark_spawn_egg", HybridAquaticEntityTypes.GREAT_WHITE_SHARK, 0, 0)
    val TIGER_SHARK_SPAWN_EGG = registerSpawnEgg("tiger_shark_spawn_egg", HybridAquaticEntityTypes.TIGER_SHARK, 0, 0)
    val HAMMERHEAD_SHARK_SPAWN_EGG = registerSpawnEgg("hammerhead_shark_spawn_egg", HybridAquaticEntityTypes.HAMMERHEAD_SHARK, 0, 0)
    val WHALE_SHARK_SPAWN_EGG = registerSpawnEgg("whale_shark_spawn_egg", HybridAquaticEntityTypes.WHALE_SHARK, 0x1a1932, 0xffffff)

    private fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier(HybridAquatic.MOD_ID, id), item)
    }

    private fun <T : MobEntity> registerSpawnEgg(id: String, type: EntityType<T>, primaryColor: Int, secondaryColor: Int): Item {
        return register(id, SpawnEggItem(type, primaryColor, secondaryColor, FabricItemSettings()))
    }

    private fun registerBlockItem(id: String, block: Block): Item {
        return register(id, BlockItem(block, FabricItemSettings()))
    }
}
