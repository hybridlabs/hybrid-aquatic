package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeKeys
import java.util.concurrent.CompletableFuture

class BiomeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider<Biome>(output, RegistryKeys.BIOME, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup?) {
        // spawn biomes
        getOrCreateTagBuilder(HybridAquaticBiomeTags.ANGLERFISH_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.WARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BARRELEYE_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.WARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BASKING_SHARK_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.DEEP_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BETTA_SPAWN_BIOMES).add(
            BiomeKeys.JUNGLE,
            BiomeKeys.SPARSE_JUNGLE,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BLUE_SPOTTED_STINGRAY_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BLUE_TANG_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BULL_SHARK_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CLOWNFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.COWFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CRAB_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.BEACH,
            BiomeKeys.STONY_SHORE,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.GHOST_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.LIGHTFOOT_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH,
            BiomeKeys.STONY_SHORE,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.FLOWER_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.VAMPIRE_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.JUNGLE,
            BiomeKeys.BAMBOO_JUNGLE,
            BiomeKeys.MANGROVE_SWAMP
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.HORSESHOE_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.BEACH
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SPIDER_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.YETI_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.GIANT_ISOPOD_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SHRIMP_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MANTIS_SHRIMP_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CRAYFISH_SPAWN_BIOMES).add(
            BiomeKeys.SWAMP,
            BiomeKeys.MANGROVE_SWAMP,
            BiomeKeys.RIVER,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.LOBSTER_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.OCEAN
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.COCONUT_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.BEACH,
            BiomeKeys.WARM_OCEAN
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CUTTLEFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DISCUS_SPAWN_BIOMES).add(
            BiomeKeys.JUNGLE,
            BiomeKeys.SPARSE_JUNGLE,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DRAGONFISH_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.WARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.FIDDLER_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.MANGROVE_SWAMP,
            BiomeKeys.BEACH,
            BiomeKeys.STONY_SHORE,
            BiomeKeys.WARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.FIREFLY_SQUID_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.FLASHLIGHT_FISH_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.WARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.FRILLED_SHARK_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.GIANT_CLAM_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SEA_NETTLE_SPAWN_BIOMES).add(
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.GLOWING_SUCKER_OCTOPUS_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.WARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.GOURAMI_SPAWN_BIOMES).add(
            BiomeKeys.SWAMP,
            BiomeKeys.MANGROVE_SWAMP,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.GREAT_WHITE_SHARK_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.HAMMERHEAD_SHARK_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.HERMIT_CRAB_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH,
            BiomeKeys.STONY_SHORE,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.LIONFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MAHIMAHI_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MOON_JELLYFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.FRIED_EGG_JELLYFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CAULIFLOWER_JELLYFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.NOMURA_JELLYFISH_SPAWN_BIOMES).add(
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ATOLLA_JELLYFISH_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.FROZEN_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.LIONS_MANE_JELLYFISH_SPAWN_BIOMES).add(
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BARREL_JELLYFISH_SPAWN_BIOMES).add(
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.COMPASS_JELLYFISH_SPAWN_BIOMES).add(
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BLUE_JELLYFISH_SPAWN_BIOMES).add(
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MAUVE_STINGER_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MORAY_EEL_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.NAUTILUS_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.NEEDLEFISH_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.NUDIBRANCH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.OARFISH_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.OPAH_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.OSCAR_SPAWN_BIOMES).add(
            BiomeKeys.JUNGLE,
            BiomeKeys.SPARSE_JUNGLE,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.PIRANHA_SPAWN_BIOMES).add(
            BiomeKeys.JUNGLE,
            BiomeKeys.SPARSE_JUNGLE,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.RATFISH_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.WARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ROCKFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SEA_ANGEL_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SEA_CUCUMBER_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,

            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SEA_URCHIN_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,

            )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SEAHORSE_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.STARFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.STONEFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SUNFISH_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.DEEP_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.THRESHER_SHARK_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TIGER_BARB_SPAWN_BIOMES).add(
            BiomeKeys.SWAMP,
            BiomeKeys.MANGROVE_SWAMP,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TIGER_SHARK_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TOADFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.BEACH,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TRIGGERFISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.UNICORN_FISH_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.VAMPIRE_SQUID_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.WARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.WHALE_SHARK_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.YELLOWFIN_SPAWN_BIOMES).add(
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ZEBRA_DANIO_SPAWN_BIOMES).add(
            BiomeKeys.JUNGLE,
            BiomeKeys.SPARSE_JUNGLE,
        )
        getOrCreateTagBuilder(HybridAquaticBiomeTags.SEA_NETTLE_SPAWN_BIOMES).add(
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ANEMONE_SPAWN_BIOMES).add(
            BiomeKeys.WARM_OCEAN
        )

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MESSAGE_IN_A_BOTTLE_SPAWN_BIOMES)
            .forceAddTag(BiomeTags.IS_OCEAN)
            .forceAddTag(BiomeTags.IS_BEACH)
    }
}
