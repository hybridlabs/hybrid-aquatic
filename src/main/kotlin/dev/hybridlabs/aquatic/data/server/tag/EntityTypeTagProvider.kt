package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.EntityTypeTags
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture

class EntityTypeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.EntityTypeTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // small prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.SMALL_PREY)
            .add(
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.PEARLFISH,
                HybridAquaticEntityTypes.SNAILFISH,
                HybridAquaticEntityTypes.BOXFISH,
                HybridAquaticEntityTypes.OSCAR,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.MACKEREL,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.BETTA,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.DANIO,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.SURGEONFISH,
                HybridAquaticEntityTypes.DISCUS,
                HybridAquaticEntityTypes.DAMSELFISH,
                HybridAquaticEntityTypes.GOURAMI,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH,
                HybridAquaticEntityTypes.FLYING_FISH,
                HybridAquaticEntityTypes.SQUIRRELFISH,
                HybridAquaticEntityTypes.STONEFISH,
                HybridAquaticEntityTypes.ANGLERFISH,
                HybridAquaticEntityTypes.CARP,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH
            )

            .addOptional(Identifier("alexsmobs", "flying_fish"))
            .addOptional(Identifier("alexsmobs", "devils_hole_pupfish"))

            .addOptional(Identifier("seafarer", "barreleye"))
            .addOptional(Identifier("seafarer", "garden_eel"))
            .addOptional(Identifier("seafarer", "mandarin_goby"))
            .addOptional(Identifier("seafarer", "frogfish"))
            .addOptional(Identifier("seafarer", "blue_tang"))
            .addOptional(Identifier("seafarer", "copperband_butterflyfish"))
            .addOptional(Identifier("seafarer", "filefish"))
            .addOptional(Identifier("seafarer", "leafy_scorpionfish"))
            .addOptional(Identifier("seafarer", "squirrelfish"))

            .addOptional(Identifier("rainbowreef", "angelfish"))
            .addOptional(Identifier("rainbowreef", "basslet"))
            .addOptional(Identifier("rainbowreef", "boxfish"))
            .addOptional(Identifier("rainbowreef", "butterfish"))
            .addOptional(Identifier("rainbowreef", "clownfish"))
            .addOptional(Identifier("rainbowreef", "dwarf_angelfish"))
            .addOptional(Identifier("rainbowreef", "goby"))
            .addOptional(Identifier("rainbowreef", "moorish_idol"))
            .addOptional(Identifier("rainbowreef", "pipefish"))
            .addOptional(Identifier("rainbowreef", "tang"))

            .addOptional(Identifier("bountiful_critters", "flounder"))
            .addOptional(Identifier("bountiful_critters", "angelfish"))
            .addOptional(Identifier("bountiful_critters", "neon_tetra"))

            .addOptional(Identifier("spawn", "anglerfish"))

            .addOptional(Identifier("fishofthieves", "ancientscale"))
            .addOptional(Identifier("fishofthieves", "battlegill"))
            .addOptional(Identifier("fishofthieves", "devilfish"))
            .addOptional(Identifier("fishofthieves", "islehopper"))
            .addOptional(Identifier("fishofthieves", "plentifin"))
            .addOptional(Identifier("fishofthieves", "pondie"))
            .addOptional(Identifier("fishofthieves", "splashtail"))
            .addOptional(Identifier("fishofthieves", "stormfish"))
            .addOptional(Identifier("fishofthieves", "wildsplash"))
            .addOptional(Identifier("fishofthieves", "wrecker"))

            .addOptional(Identifier("crittersandcompanions", "koi_fish"))

            .addOptional(Identifier("aquaculture", "atlantic_herring"))
            .addOptional(Identifier("aquaculture", "boulti"))
            .addOptional(Identifier("aquaculture", "synodontis"))
            .addOptional(Identifier("aquaculture", "smallmouth_bass"))
            .addOptional(Identifier("aquaculture", "bluegill"))
            .addOptional(Identifier("aquaculture", "minnow"))
            .addOptional(Identifier("aquaculture", "perch"))
            .addOptional(Identifier("aquaculture", "piranha"))
            .addOptional(Identifier("aquaculture", "brown_shrooma"))
            .addOptional(Identifier("aquaculture", "red_shrooma"))
            .addOptional(Identifier("aquaculture", "pink_salmon"))
            .addOptional(Identifier("aquaculture", "pollock"))

        // medium prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.MEDIUM_PREY)
            .add(
                HybridAquaticEntityTypes.RATFISH,
                HybridAquaticEntityTypes.STINGRAY,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.NEEDLEFISH,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.SEA_BASS,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.PARROTFISH,
                HybridAquaticEntityTypes.SHEEPSHEAD_WRASSE,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.JOHN_DORY,
                HybridAquaticEntityTypes.LANTERN_SHARK,
                HybridAquaticEntityTypes.HOUND_SHARK,
            )

            .addOptional(Identifier("alexsmobs", "blobfish"))
            .addOptional(Identifier("alexsmobs", "catfish"))

            .addOptional(Identifier("alexscaves", "tripodfish"))

            .addOptional(Identifier("seafarer", "chimaera"))
            .addOptional(Identifier("seafarer", "marine_iguana"))

            .addOptional(Identifier("naturalist", "bass"))
            .addOptional(Identifier("naturalist", "catfish"))

            .addOptional(Identifier("rainbowreef", "hogfish"))
            .addOptional(Identifier("rainbowreef", "parrotfish"))
            .addOptional(Identifier("rainbowreef", "ray"))

            .addOptional(Identifier("aquaculture", "carp"))
            .addOptional(Identifier("aquaculture", "catfish"))
            .addOptional(Identifier("aquaculture", "bayad"))
            .addOptional(Identifier("aquaculture", "blackfish"))

        // large prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.LARGE_PREY)
            .add(
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.WRECKFISH,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.MAHI,
                HybridAquaticEntityTypes.COELACANTH,
                HybridAquaticEntityTypes.GOLDEN_DORADO,
                EntityType.PLAYER,
                EntityType.TURTLE,
            )

            .addOptional(Identifier("seafarer", "sunfish"))
            .addOptional(Identifier("seafarer", "manta_ray"))

            .addOptional(Identifier("bountiful_critters", "sunfish"))
            .addOptional(Identifier("bountiful_critters", "ray"))

            .addOptional(Identifier("spawn", "tuna"))

            .addOptional(Identifier("aquaculture", "arapaima"))
            .addOptional(Identifier("aquaculture", "tuna"))
            .addOptional(Identifier("aquaculture", "atlantic_cod"))
            .addOptional(Identifier("aquaculture", "pacific_halibut"))
            .addOptional(Identifier("aquaculture", "atlantic_halibut"))
            .addOptional(Identifier("aquaculture", "capitaine"))
            .addOptional(Identifier("aquaculture", "tambaqui"))
            .addOptional(Identifier("aquaculture", "gar"))
            .addOptional(Identifier("aquaculture", "muskellunge"))

        // crustaceans
        getOrCreateTagBuilder(HybridAquaticEntityTags.CRUSTACEAN)
            .add(
                HybridAquaticEntityTypes.COCONUT_CRAB,
                HybridAquaticEntityTypes.CRAYFISH,
                HybridAquaticEntityTypes.DECORATOR_CRAB,
                HybridAquaticEntityTypes.DUNGENESS_CRAB,
                HybridAquaticEntityTypes.FIDDLER_CRAB,
                HybridAquaticEntityTypes.FLOWER_CRAB,
                HybridAquaticEntityTypes.GHOST_CRAB,
                HybridAquaticEntityTypes.GIANT_ISOPOD,
                HybridAquaticEntityTypes.HERMIT_CRAB,
                HybridAquaticEntityTypes.HORSESHOE_CRAB,
                HybridAquaticEntityTypes.LIGHTFOOT_CRAB,
                HybridAquaticEntityTypes.LOBSTER,
                HybridAquaticEntityTypes.SHRIMP,
                HybridAquaticEntityTypes.SPIDER_CRAB,
                HybridAquaticEntityTypes.VAMPIRE_CRAB,
                HybridAquaticEntityTypes.YETI_CRAB,
            )

            .addOptional(Identifier("alexsmobs", "lobster"))

            .addOptional(Identifier("shellfish", "crayfish"))
            .addOptional(Identifier("shellfish", "lobster"))
            .addOptional(Identifier("shellfish", "crab"))
            .addOptional(Identifier("shellfish", "shrimp"))

            .addOptional(Identifier("seafarer", "crab"))
            .addOptional(Identifier("seafarer", "horseshoe_crab"))
            .addOptional(Identifier("seafarer", "mantis_shrimp"))
            .addOptional(Identifier("seafarer", "sexy_shrimp"))
            .addOptional(Identifier("seafarer", "spider_crab"))

            .addOptional(Identifier("rainbowreef", "arrow_crab"))
            .addOptional(Identifier("rainbowreef", "crab"))

            .addOptional(Identifier("bountiful_critters", "krill"))

        // cephalopods
        getOrCreateTagBuilder(HybridAquaticEntityTags.CEPHALOPOD)
            .add(
                HybridAquaticEntityTypes.ARROW_SQUID,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
                HybridAquaticEntityTypes.NAUTILUS,
                HybridAquaticEntityTypes.UMBRELLA_OCTOPUS,
                HybridAquaticEntityTypes.VAMPIRE_SQUID,
                EntityType.SQUID,
                EntityType.GLOW_SQUID
            )

            .addOptional(Identifier("alexsmobs", "mimic_octopus"))
            .addOptional(Identifier("alexsmobs", "giant_squid"))

            .addOptional(Identifier("crittersandcompanions", "dumbo_octopus"))
            .addOptional(Identifier("crittersandcompanions", "dumbo_octopus"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.NONE)

        getOrCreateTagBuilder(EntityTypeTags.AXOLOTL_HUNT_TARGETS)
            .add(
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.DANIO,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.BETTA,
                HybridAquaticEntityTypes.OSCAR,
                HybridAquaticEntityTypes.DISCUS,
            )

        // sharks
        getOrCreateTagBuilder(HybridAquaticEntityTags.SHARK)
            .add(
                HybridAquaticEntityTypes.BASKING_SHARK,
                HybridAquaticEntityTypes.BULL_SHARK,
                HybridAquaticEntityTypes.FRILLED_SHARK,
                HybridAquaticEntityTypes.GREAT_WHITE_SHARK,
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
                HybridAquaticEntityTypes.HOUND_SHARK,
                HybridAquaticEntityTypes.THRESHER_SHARK,
                HybridAquaticEntityTypes.TIGER_SHARK,
                HybridAquaticEntityTypes.WHALE_SHARK,
                HybridAquaticEntityTypes.LANTERN_SHARK,
            )

            .addOptional(Identifier("alexsmobs", "hammerhead_shark"))
            .addOptional(Identifier("alexsmobs", "frilled_shark"))

            .addOptional(Identifier("seafarer", "zebra_shark"))

            .addOptional(Identifier("rainbowreef", "small_shark"))

        // dolphins
        getOrCreateTagBuilder(HybridAquaticEntityTags.DOLPHIN)
            .add(
                HybridAquaticEntityTypes.KILLER_WHALE,
                EntityType.DOLPHIN,
            )

        // critters
        getOrCreateTagBuilder(HybridAquaticEntityTags.CRITTER)
            .add(
                HybridAquaticEntityTypes.SEA_CUCUMBER,
                HybridAquaticEntityTypes.SEA_URCHIN,
                HybridAquaticEntityTypes.SEA_SLUG,
                HybridAquaticEntityTypes.STARFISH,
                HybridAquaticEntityTypes.SEA_ANGEL,
            )

            .addOptional(Identifier("alexsmobs", "triops"))
            .addOptional(Identifier("alexscaves", "sea_pig"))

            .addOptional(Identifier("shellfish", "sea_snail"))
            .addOptional(Identifier("shellfish", "sea_urchin"))
            .addOptional(Identifier("shellfish", "clam"))
            .addOptional(Identifier("shellfish", "oyster"))
            .addOptional(Identifier("shellfish", "mussel"))

            .addOptional(Identifier("crittersandcompanions", "sea_bunny"))

        // jellyfish
        getOrCreateTagBuilder(HybridAquaticEntityTags.JELLYFISH)
            .add(
                HybridAquaticEntityTypes.ATOLLA_JELLYFISH,
                HybridAquaticEntityTypes.BARREL_JELLYFISH,
                HybridAquaticEntityTypes.BLUE_JELLYFISH,
                HybridAquaticEntityTypes.BIG_RED_JELLYFISH,
                HybridAquaticEntityTypes.CEPHEIDAE_JELLYFISH,
                HybridAquaticEntityTypes.COSMIC_JELLYFISH,
                HybridAquaticEntityTypes.FIREWORK_JELLYFISH,
                HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH,
                HybridAquaticEntityTypes.MAUVE_STINGER,
                HybridAquaticEntityTypes.MOON_JELLYFISH,
                HybridAquaticEntityTypes.NOMURA_JELLYFISH,
                HybridAquaticEntityTypes.SEA_NETTLE,
                HybridAquaticEntityTypes.BOX_JELLYFISH,
            )

            .addOptional(Identifier("alexsmobs", "comb_jelly"))

            .addOptional(Identifier("jellyfishing", "jellyfish"))
            .addOptional(Identifier("jellyfishing", "blue_jellyfish"))

            .addOptional(Identifier("rainbowreef", "jellyfish"))

            .addOptional(Identifier("aquaculture", "jellyfish"))

        // fish
        getOrCreateTagBuilder(HybridAquaticEntityTags.FISH)
            .add(
                HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH,
                HybridAquaticEntityTypes.STONEFISH,
                HybridAquaticEntityTypes.DAMSELFISH,
                HybridAquaticEntityTypes.PEARLFISH,
                HybridAquaticEntityTypes.BETTA,
                HybridAquaticEntityTypes.JOHN_DORY,
                HybridAquaticEntityTypes.SNAILFISH,
                HybridAquaticEntityTypes.CARP,
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.BOXFISH,
                HybridAquaticEntityTypes.DANIO,
                HybridAquaticEntityTypes.DISCUS,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.SQUIRRELFISH,
                HybridAquaticEntityTypes.FLYING_FISH,
                HybridAquaticEntityTypes.GOLDFISH,
                HybridAquaticEntityTypes.GOURAMI,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.MACKEREL,
                HybridAquaticEntityTypes.MAHI,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.NEEDLEFISH,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.OSCAR,
                HybridAquaticEntityTypes.PARROTFISH,
                HybridAquaticEntityTypes.SHEEPSHEAD_WRASSE,
                HybridAquaticEntityTypes.PIRANHA,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.WRECKFISH,
                HybridAquaticEntityTypes.SEA_BASS,
                HybridAquaticEntityTypes.SEAHORSE,
                HybridAquaticEntityTypes.SEADRAGON,
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.SURGEONFISH,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.MANTA_RAY,
                HybridAquaticEntityTypes.STINGRAY,
                HybridAquaticEntityTypes.ANGLERFISH,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.DRAGONFISH,
                HybridAquaticEntityTypes.RATFISH,
                HybridAquaticEntityTypes.GOLDEN_DORADO,
                HybridAquaticEntityTypes.COELACANTH,
                EntityType.COD,
                EntityType.SALMON,
                EntityType.TROPICAL_FISH,
                EntityType.AXOLOTL,
            )

            // Tide Mod
            .addOptional(Identifier("tide", "angelfish"))
            .addOptional(Identifier("tide", "barracuda"))
            .addOptional(Identifier("tide", "bass"))
            .addOptional(Identifier("tide", "bluegill"))
            .addOptional(Identifier("tide", "catfish"))
            .addOptional(Identifier("tide", "clayfish"))
            .addOptional(Identifier("tide", "guppy"))
            .addOptional(Identifier("tide", "mackerel"))
            .addOptional(Identifier("tide", "mint_carp"))
            .addOptional(Identifier("tide", "ocean_perch"))
            .addOptional(Identifier("tide", "pike"))
            .addOptional(Identifier("tide", "sailfish"))
            .addOptional(Identifier("tide", "trout"))
            .addOptional(Identifier("tide", "tuna"))
            .addOptional(Identifier("tide", "yellow_perch"))

            // Alexs Mods
            .addOptional(Identifier("alexsmobs", "blobfish"))
            .addOptional(Identifier("alexsmobs", "catfish"))
            .addOptional(Identifier("alexsmobs", "devils_hole_pupfish"))
            .addOptional(Identifier("alexsmobs", "flying_fish"))

            .addOptional(Identifier("alexscaves", "tripodfish"))
            .addOptional(Identifier("alexscaves", "lanternfish"))

            // Seafarer
            .addOptional(Identifier("seafarer", "barreleye"))
            .addOptional(Identifier("seafarer", "sunfish"))
            .addOptional(Identifier("seafarer", "garden_eel"))
            .addOptional(Identifier("seafarer", "mandarin_goby"))
            .addOptional(Identifier("seafarer", "frogfish"))
            .addOptional(Identifier("seafarer", "blue_tang"))
            .addOptional(Identifier("seafarer", "copperband_butterflyfish"))
            .addOptional(Identifier("seafarer", "parrotfish"))
            .addOptional(Identifier("seafarer", "filefish"))
            .addOptional(Identifier("seafarer", "leafy_scorpionfish"))
            .addOptional(Identifier("seafarer", "chimaera"))
            .addOptional(Identifier("seafarer", "squirrelfish"))

            // Naturalist
            .addOptional(Identifier("naturalist", "bass"))
            .addOptional(Identifier("naturalist", "catfish"))

            // Rainbow Reef
            .addOptional(Identifier("rainbowreef", "angelfish"))
            .addOptional(Identifier("rainbowreef", "basslet"))
            .addOptional(Identifier("rainbowreef", "boxfish"))
            .addOptional(Identifier("rainbowreef", "butterfish"))
            .addOptional(Identifier("rainbowreef", "clownfish"))
            .addOptional(Identifier("rainbowreef", "dwarf_angelfish"))
            .addOptional(Identifier("rainbowreef", "goby"))
            .addOptional(Identifier("rainbowreef", "moorish_idol"))
            .addOptional(Identifier("rainbowreef", "pipefish"))
            .addOptional(Identifier("rainbowreef", "tang"))
            .addOptional(Identifier("rainbowreef", "seahorse"))
            .addOptional(Identifier("rainbowreef", "hogfish"))
            .addOptional(Identifier("rainbowreef", "parrotfish"))
            .addOptional(Identifier("rainbowreef", "ray"))

            // Bountiful Critters
            .addOptional(Identifier("bountiful_critters", "stingray"))
            .addOptional(Identifier("bountiful_critters", "sunfish"))
            .addOptional(Identifier("bountiful_critters", "flounder"))
            .addOptional(Identifier("bountiful_critters", "barreleye"))
            .addOptional(Identifier("bountiful_critters", "angelfish"))
            .addOptional(Identifier("bountiful_critters", "neon_tetra"))

            // Spawn
            .addOptional(Identifier("spawn", "tuna"))
            .addOptional(Identifier("spawn", "seahorse"))
            .addOptional(Identifier("spawn", "anglerfish"))

            // Fish Of Thieves
            .addOptional(Identifier("fishofthieves", "ancientscale"))
            .addOptional(Identifier("fishofthieves", "battlegill"))
            .addOptional(Identifier("fishofthieves", "devilfish"))
            .addOptional(Identifier("fishofthieves", "islehopper"))
            .addOptional(Identifier("fishofthieves", "plentifin"))
            .addOptional(Identifier("fishofthieves", "pondie"))
            .addOptional(Identifier("fishofthieves", "splashtail"))
            .addOptional(Identifier("fishofthieves", "stormfish"))
            .addOptional(Identifier("fishofthieves", "wildsplash"))
            .addOptional(Identifier("fishofthieves", "wrecker"))

            // Critters & Companions
            .addOptional(Identifier("crittersandcompanions", "koi_fish"))

            // Aquaculture
            .addOptional(Identifier("aquaculture", "atlantic_cod"))
            .addOptional(Identifier("aquaculture", "blackfish"))
            .addOptional(Identifier("aquaculture", "pacific_halibut"))
            .addOptional(Identifier("aquaculture", "atlantic_halibut"))
            .addOptional(Identifier("aquaculture", "atlantic_herring"))
            .addOptional(Identifier("aquaculture", "pink_salmon"))
            .addOptional(Identifier("aquaculture", "pollock"))
            .addOptional(Identifier("aquaculture", "rainbow_trout"))
            .addOptional(Identifier("aquaculture", "bayad"))
            .addOptional(Identifier("aquaculture", "boulti"))
            .addOptional(Identifier("aquaculture", "capitaine"))
            .addOptional(Identifier("aquaculture", "synodontis"))
            .addOptional(Identifier("aquaculture", "smallmouth_bass"))
            .addOptional(Identifier("aquaculture", "bluegill"))
            .addOptional(Identifier("aquaculture", "brown_trout"))
            .addOptional(Identifier("aquaculture", "carp"))
            .addOptional(Identifier("aquaculture", "catfish"))
            .addOptional(Identifier("aquaculture", "gar"))
            .addOptional(Identifier("aquaculture", "minnow"))
            .addOptional(Identifier("aquaculture", "muskellunge"))
            .addOptional(Identifier("aquaculture", "perch"))
            .addOptional(Identifier("aquaculture", "arapaima"))
            .addOptional(Identifier("aquaculture", "piranha"))
            .addOptional(Identifier("aquaculture", "tambaqui"))
            .addOptional(Identifier("aquaculture", "brown_shrooma"))
            .addOptional(Identifier("aquaculture", "red_shrooma"))
            .addOptional(Identifier("aquaculture", "red_grouper"))
            .addOptional(Identifier("aquaculture", "tuna"))

        // entities that you can catch with the fishing net
        getOrCreateTagBuilder(HybridAquaticEntityTags.CAN_USE_FISHING_NET_ON)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.CRITTER)
            .addTag(HybridAquaticEntityTags.CRUSTACEAN)
            .addTag(HybridAquaticEntityTags.DOLPHIN)
            .addTag(HybridAquaticEntityTags.FISH)
            .addTag(HybridAquaticEntityTags.SHARK)
    }
}
