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
        getOrCreateTagBuilder(EntityTypeTags.CAN_BREATHE_UNDER_WATER)
            .addTag(HybridAquaticEntityTags.CRITTER)
            .addTag(HybridAquaticEntityTags.CRUSTACEAN)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.FISH)
            .addTag(HybridAquaticEntityTags.SHARK)

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

            .addOptional(Identifier.of("alexsmobs", "flying_fish"))
            .addOptional(Identifier.of("alexsmobs", "devils_hole_pupfish"))

            .addOptional(Identifier.of("seafarer", "barreleye"))
            .addOptional(Identifier.of("seafarer", "garden_eel"))
            .addOptional(Identifier.of("seafarer", "mandarin_goby"))
            .addOptional(Identifier.of("seafarer", "frogfish"))
            .addOptional(Identifier.of("seafarer", "blue_tang"))
            .addOptional(Identifier.of("seafarer", "copperband_butterflyfish"))
            .addOptional(Identifier.of("seafarer", "filefish"))
            .addOptional(Identifier.of("seafarer", "leafy_scorpionfish"))
            .addOptional(Identifier.of("seafarer", "squirrelfish"))

            .addOptional(Identifier.of("rainbowreef", "angelfish"))
            .addOptional(Identifier.of("rainbowreef", "basslet"))
            .addOptional(Identifier.of("rainbowreef", "boxfish"))
            .addOptional(Identifier.of("rainbowreef", "butterfish"))
            .addOptional(Identifier.of("rainbowreef", "clownfish"))
            .addOptional(Identifier.of("rainbowreef", "dwarf_angelfish"))
            .addOptional(Identifier.of("rainbowreef", "goby"))
            .addOptional(Identifier.of("rainbowreef", "moorish_idol"))
            .addOptional(Identifier.of("rainbowreef", "pipefish"))
            .addOptional(Identifier.of("rainbowreef", "tang"))

            .addOptional(Identifier.of("bountiful_critters", "flounder"))
            .addOptional(Identifier.of("bountiful_critters", "angelfish"))
            .addOptional(Identifier.of("bountiful_critters", "neon_tetra"))

            .addOptional(Identifier.of("spawn", "anglerfish"))

            .addOptional(Identifier.of("fishofthieves", "ancientscale"))
            .addOptional(Identifier.of("fishofthieves", "battlegill"))
            .addOptional(Identifier.of("fishofthieves", "devilfish"))
            .addOptional(Identifier.of("fishofthieves", "islehopper"))
            .addOptional(Identifier.of("fishofthieves", "plentifin"))
            .addOptional(Identifier.of("fishofthieves", "pondie"))
            .addOptional(Identifier.of("fishofthieves", "splashtail"))
            .addOptional(Identifier.of("fishofthieves", "stormfish"))
            .addOptional(Identifier.of("fishofthieves", "wildsplash"))
            .addOptional(Identifier.of("fishofthieves", "wrecker"))

            .addOptional(Identifier.of("crittersandcompanions", "koi_fish"))

            .addOptional(Identifier.of("aquaculture", "atlantic_herring"))
            .addOptional(Identifier.of("aquaculture", "boulti"))
            .addOptional(Identifier.of("aquaculture", "synodontis"))
            .addOptional(Identifier.of("aquaculture", "smallmouth_bass"))
            .addOptional(Identifier.of("aquaculture", "bluegill"))
            .addOptional(Identifier.of("aquaculture", "minnow"))
            .addOptional(Identifier.of("aquaculture", "perch"))
            .addOptional(Identifier.of("aquaculture", "piranha"))
            .addOptional(Identifier.of("aquaculture", "brown_shrooma"))
            .addOptional(Identifier.of("aquaculture", "red_shrooma"))
            .addOptional(Identifier.of("aquaculture", "pink_salmon"))
            .addOptional(Identifier.of("aquaculture", "pollock"))

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

            .addOptional(Identifier.of("alexsmobs", "blobfish"))
            .addOptional(Identifier.of("alexsmobs", "catfish"))

            .addOptional(Identifier.of("alexscaves", "tripodfish"))

            .addOptional(Identifier.of("seafarer", "chimaera"))
            .addOptional(Identifier.of("seafarer", "marine_iguana"))

            .addOptional(Identifier.of("naturalist", "bass"))
            .addOptional(Identifier.of("naturalist", "catfish"))

            .addOptional(Identifier.of("rainbowreef", "hogfish"))
            .addOptional(Identifier.of("rainbowreef", "parrotfish"))
            .addOptional(Identifier.of("rainbowreef", "ray"))

            .addOptional(Identifier.of("aquaculture", "carp"))
            .addOptional(Identifier.of("aquaculture", "catfish"))
            .addOptional(Identifier.of("aquaculture", "bayad"))
            .addOptional(Identifier.of("aquaculture", "blackfish"))

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

            .addOptional(Identifier.of("seafarer", "sunfish"))
            .addOptional(Identifier.of("seafarer", "manta_ray"))

            .addOptional(Identifier.of("bountiful_critters", "sunfish"))
            .addOptional(Identifier.of("bountiful_critters", "ray"))

            .addOptional(Identifier.of("spawn", "tuna"))

            .addOptional(Identifier.of("aquaculture", "arapaima"))
            .addOptional(Identifier.of("aquaculture", "tuna"))
            .addOptional(Identifier.of("aquaculture", "atlantic_cod"))
            .addOptional(Identifier.of("aquaculture", "pacific_halibut"))
            .addOptional(Identifier.of("aquaculture", "atlantic_halibut"))
            .addOptional(Identifier.of("aquaculture", "capitaine"))
            .addOptional(Identifier.of("aquaculture", "tambaqui"))
            .addOptional(Identifier.of("aquaculture", "gar"))
            .addOptional(Identifier.of("aquaculture", "muskellunge"))

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

            .addOptional(Identifier.of("alexsmobs", "lobster"))

            .addOptional(Identifier.of("shellfish", "crayfish"))
            .addOptional(Identifier.of("shellfish", "lobster"))
            .addOptional(Identifier.of("shellfish", "crab"))
            .addOptional(Identifier.of("shellfish", "shrimp"))

            .addOptional(Identifier.of("seafarer", "crab"))
            .addOptional(Identifier.of("seafarer", "horseshoe_crab"))
            .addOptional(Identifier.of("seafarer", "mantis_shrimp"))
            .addOptional(Identifier.of("seafarer", "sexy_shrimp"))
            .addOptional(Identifier.of("seafarer", "spider_crab"))

            .addOptional(Identifier.of("rainbowreef", "arrow_crab"))
            .addOptional(Identifier.of("rainbowreef", "crab"))

            .addOptional(Identifier.of("bountiful_critters", "krill"))

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

            .addOptional(Identifier.of("alexsmobs", "mimic_octopus"))
            .addOptional(Identifier.of("alexsmobs", "giant_squid"))

            .addOptional(Identifier.of("crittersandcompanions", "dumbo_octopus"))
            .addOptional(Identifier.of("crittersandcompanions", "dumbo_octopus"))

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

            .addOptional(Identifier.of("alexsmobs", "hammerhead_shark"))
            .addOptional(Identifier.of("alexsmobs", "frilled_shark"))

            .addOptional(Identifier.of("seafarer", "zebra_shark"))

            .addOptional(Identifier.of("rainbowreef", "small_shark"))

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

            .addOptional(Identifier.of("alexsmobs", "triops"))
            .addOptional(Identifier.of("alexscaves", "sea_pig"))

            .addOptional(Identifier.of("shellfish", "sea_snail"))
            .addOptional(Identifier.of("shellfish", "sea_urchin"))
            .addOptional(Identifier.of("shellfish", "clam"))
            .addOptional(Identifier.of("shellfish", "oyster"))
            .addOptional(Identifier.of("shellfish", "mussel"))

            .addOptional(Identifier.of("crittersandcompanions", "sea_bunny"))

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

            .addOptional(Identifier.of("alexsmobs", "comb_jelly"))

            .addOptional(Identifier.of("jellyfishing", "jellyfish"))
            .addOptional(Identifier.of("jellyfishing", "blue_jellyfish"))

            .addOptional(Identifier.of("rainbowreef", "jellyfish"))

            .addOptional(Identifier.of("aquaculture", "jellyfish"))

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
            .addOptional(Identifier.of("tide", "angelfish"))
            .addOptional(Identifier.of("tide", "barracuda"))
            .addOptional(Identifier.of("tide", "bass"))
            .addOptional(Identifier.of("tide", "bluegill"))
            .addOptional(Identifier.of("tide", "catfish"))
            .addOptional(Identifier.of("tide", "clayfish"))
            .addOptional(Identifier.of("tide", "guppy"))
            .addOptional(Identifier.of("tide", "mackerel"))
            .addOptional(Identifier.of("tide", "mint_carp"))
            .addOptional(Identifier.of("tide", "ocean_perch"))
            .addOptional(Identifier.of("tide", "pike"))
            .addOptional(Identifier.of("tide", "sailfish"))
            .addOptional(Identifier.of("tide", "trout"))
            .addOptional(Identifier.of("tide", "tuna"))
            .addOptional(Identifier.of("tide", "yellow_perch"))

            // Alexs Mods
            .addOptional(Identifier.of("alexsmobs", "blobfish"))
            .addOptional(Identifier.of("alexsmobs", "catfish"))
            .addOptional(Identifier.of("alexsmobs", "devils_hole_pupfish"))
            .addOptional(Identifier.of("alexsmobs", "flying_fish"))

            .addOptional(Identifier.of("alexscaves", "tripodfish"))
            .addOptional(Identifier.of("alexscaves", "lanternfish"))

            // Seafarer
            .addOptional(Identifier.of("seafarer", "barreleye"))
            .addOptional(Identifier.of("seafarer", "sunfish"))
            .addOptional(Identifier.of("seafarer", "garden_eel"))
            .addOptional(Identifier.of("seafarer", "mandarin_goby"))
            .addOptional(Identifier.of("seafarer", "frogfish"))
            .addOptional(Identifier.of("seafarer", "blue_tang"))
            .addOptional(Identifier.of("seafarer", "copperband_butterflyfish"))
            .addOptional(Identifier.of("seafarer", "parrotfish"))
            .addOptional(Identifier.of("seafarer", "filefish"))
            .addOptional(Identifier.of("seafarer", "leafy_scorpionfish"))
            .addOptional(Identifier.of("seafarer", "chimaera"))
            .addOptional(Identifier.of("seafarer", "squirrelfish"))

            // Naturalist
            .addOptional(Identifier.of("naturalist", "bass"))
            .addOptional(Identifier.of("naturalist", "catfish"))

            // Rainbow Reef
            .addOptional(Identifier.of("rainbowreef", "angelfish"))
            .addOptional(Identifier.of("rainbowreef", "basslet"))
            .addOptional(Identifier.of("rainbowreef", "boxfish"))
            .addOptional(Identifier.of("rainbowreef", "butterfish"))
            .addOptional(Identifier.of("rainbowreef", "clownfish"))
            .addOptional(Identifier.of("rainbowreef", "dwarf_angelfish"))
            .addOptional(Identifier.of("rainbowreef", "goby"))
            .addOptional(Identifier.of("rainbowreef", "moorish_idol"))
            .addOptional(Identifier.of("rainbowreef", "pipefish"))
            .addOptional(Identifier.of("rainbowreef", "tang"))
            .addOptional(Identifier.of("rainbowreef", "seahorse"))
            .addOptional(Identifier.of("rainbowreef", "hogfish"))
            .addOptional(Identifier.of("rainbowreef", "parrotfish"))
            .addOptional(Identifier.of("rainbowreef", "ray"))

            // Bountiful Critters
            .addOptional(Identifier.of("bountiful_critters", "stingray"))
            .addOptional(Identifier.of("bountiful_critters", "sunfish"))
            .addOptional(Identifier.of("bountiful_critters", "flounder"))
            .addOptional(Identifier.of("bountiful_critters", "barreleye"))
            .addOptional(Identifier.of("bountiful_critters", "angelfish"))
            .addOptional(Identifier.of("bountiful_critters", "neon_tetra"))

            // Spawn
            .addOptional(Identifier.of("spawn", "tuna"))
            .addOptional(Identifier.of("spawn", "seahorse"))
            .addOptional(Identifier.of("spawn", "anglerfish"))

            // Fish Of Thieves
            .addOptional(Identifier.of("fishofthieves", "ancientscale"))
            .addOptional(Identifier.of("fishofthieves", "battlegill"))
            .addOptional(Identifier.of("fishofthieves", "devilfish"))
            .addOptional(Identifier.of("fishofthieves", "islehopper"))
            .addOptional(Identifier.of("fishofthieves", "plentifin"))
            .addOptional(Identifier.of("fishofthieves", "pondie"))
            .addOptional(Identifier.of("fishofthieves", "splashtail"))
            .addOptional(Identifier.of("fishofthieves", "stormfish"))
            .addOptional(Identifier.of("fishofthieves", "wildsplash"))
            .addOptional(Identifier.of("fishofthieves", "wrecker"))

            // Critters & Companions
            .addOptional(Identifier.of("crittersandcompanions", "koi_fish"))

            // Aquaculture
            .addOptional(Identifier.of("aquaculture", "atlantic_cod"))
            .addOptional(Identifier.of("aquaculture", "blackfish"))
            .addOptional(Identifier.of("aquaculture", "pacific_halibut"))
            .addOptional(Identifier.of("aquaculture", "atlantic_halibut"))
            .addOptional(Identifier.of("aquaculture", "atlantic_herring"))
            .addOptional(Identifier.of("aquaculture", "pink_salmon"))
            .addOptional(Identifier.of("aquaculture", "pollock"))
            .addOptional(Identifier.of("aquaculture", "rainbow_trout"))
            .addOptional(Identifier.of("aquaculture", "bayad"))
            .addOptional(Identifier.of("aquaculture", "boulti"))
            .addOptional(Identifier.of("aquaculture", "capitaine"))
            .addOptional(Identifier.of("aquaculture", "synodontis"))
            .addOptional(Identifier.of("aquaculture", "smallmouth_bass"))
            .addOptional(Identifier.of("aquaculture", "bluegill"))
            .addOptional(Identifier.of("aquaculture", "brown_trout"))
            .addOptional(Identifier.of("aquaculture", "carp"))
            .addOptional(Identifier.of("aquaculture", "catfish"))
            .addOptional(Identifier.of("aquaculture", "gar"))
            .addOptional(Identifier.of("aquaculture", "minnow"))
            .addOptional(Identifier.of("aquaculture", "muskellunge"))
            .addOptional(Identifier.of("aquaculture", "perch"))
            .addOptional(Identifier.of("aquaculture", "arapaima"))
            .addOptional(Identifier.of("aquaculture", "piranha"))
            .addOptional(Identifier.of("aquaculture", "tambaqui"))
            .addOptional(Identifier.of("aquaculture", "brown_shrooma"))
            .addOptional(Identifier.of("aquaculture", "red_shrooma"))
            .addOptional(Identifier.of("aquaculture", "red_grouper"))
            .addOptional(Identifier.of("aquaculture", "tuna"))

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
