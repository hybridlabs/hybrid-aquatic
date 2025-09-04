package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.EntityTypeTags
import net.minecraft.resources.ResourceLocation
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

            .addOptional(ResourceLocation("alexsmobs", "flying_fish"))
            .addOptional(ResourceLocation("alexsmobs", "devils_hole_pupfish"))

            .addOptional(ResourceLocation("seafarer", "barreleye"))
            .addOptional(ResourceLocation("seafarer", "garden_eel"))
            .addOptional(ResourceLocation("seafarer", "mandarin_goby"))
            .addOptional(ResourceLocation("seafarer", "frogfish"))
            .addOptional(ResourceLocation("seafarer", "blue_tang"))
            .addOptional(ResourceLocation("seafarer", "copperband_butterflyfish"))
            .addOptional(ResourceLocation("seafarer", "filefish"))
            .addOptional(ResourceLocation("seafarer", "leafy_scorpionfish"))
            .addOptional(ResourceLocation("seafarer", "squirrelfish"))

            .addOptional(ResourceLocation("rainbowreef", "angelfish"))
            .addOptional(ResourceLocation("rainbowreef", "basslet"))
            .addOptional(ResourceLocation("rainbowreef", "boxfish"))
            .addOptional(ResourceLocation("rainbowreef", "butterfish"))
            .addOptional(ResourceLocation("rainbowreef", "clownfish"))
            .addOptional(ResourceLocation("rainbowreef", "dwarf_angelfish"))
            .addOptional(ResourceLocation("rainbowreef", "goby"))
            .addOptional(ResourceLocation("rainbowreef", "moorish_idol"))
            .addOptional(ResourceLocation("rainbowreef", "pipefish"))
            .addOptional(ResourceLocation("rainbowreef", "tang"))

            .addOptional(ResourceLocation("bountiful_critters", "flounder"))
            .addOptional(ResourceLocation("bountiful_critters", "angelfish"))
            .addOptional(ResourceLocation("bountiful_critters", "neon_tetra"))

            .addOptional(ResourceLocation("spawn", "anglerfish"))

            .addOptional(ResourceLocation("fishofthieves", "ancientscale"))
            .addOptional(ResourceLocation("fishofthieves", "battlegill"))
            .addOptional(ResourceLocation("fishofthieves", "devilfish"))
            .addOptional(ResourceLocation("fishofthieves", "islehopper"))
            .addOptional(ResourceLocation("fishofthieves", "plentifin"))
            .addOptional(ResourceLocation("fishofthieves", "pondie"))
            .addOptional(ResourceLocation("fishofthieves", "splashtail"))
            .addOptional(ResourceLocation("fishofthieves", "stormfish"))
            .addOptional(ResourceLocation("fishofthieves", "wildsplash"))
            .addOptional(ResourceLocation("fishofthieves", "wrecker"))

            .addOptional(ResourceLocation("crittersandcompanions", "koi_fish"))

            .addOptional(ResourceLocation("aquaculture", "atlantic_herring"))
            .addOptional(ResourceLocation("aquaculture", "boulti"))
            .addOptional(ResourceLocation("aquaculture", "synodontis"))
            .addOptional(ResourceLocation("aquaculture", "smallmouth_bass"))
            .addOptional(ResourceLocation("aquaculture", "bluegill"))
            .addOptional(ResourceLocation("aquaculture", "minnow"))
            .addOptional(ResourceLocation("aquaculture", "perch"))
            .addOptional(ResourceLocation("aquaculture", "piranha"))
            .addOptional(ResourceLocation("aquaculture", "brown_shrooma"))
            .addOptional(ResourceLocation("aquaculture", "red_shrooma"))
            .addOptional(ResourceLocation("aquaculture", "pink_salmon"))
            .addOptional(ResourceLocation("aquaculture", "pollock"))

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

            .addOptional(ResourceLocation("alexsmobs", "blobfish"))
            .addOptional(ResourceLocation("alexsmobs", "catfish"))

            .addOptional(ResourceLocation("alexscaves", "tripodfish"))

            .addOptional(ResourceLocation("seafarer", "chimaera"))
            .addOptional(ResourceLocation("seafarer", "marine_iguana"))

            .addOptional(ResourceLocation("naturalist", "bass"))
            .addOptional(ResourceLocation("naturalist", "catfish"))

            .addOptional(ResourceLocation("rainbowreef", "hogfish"))
            .addOptional(ResourceLocation("rainbowreef", "parrotfish"))
            .addOptional(ResourceLocation("rainbowreef", "ray"))

            .addOptional(ResourceLocation("aquaculture", "carp"))
            .addOptional(ResourceLocation("aquaculture", "catfish"))
            .addOptional(ResourceLocation("aquaculture", "bayad"))
            .addOptional(ResourceLocation("aquaculture", "blackfish"))

        // large prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.LARGE_PREY)
            .add(
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.MAHI,
                HybridAquaticEntityTypes.COELACANTH,
                HybridAquaticEntityTypes.GOLDEN_DORADO,
                EntityType.PLAYER,
                EntityType.TURTLE,
            )

            .addOptional(ResourceLocation("seafarer", "sunfish"))
            .addOptional(ResourceLocation("seafarer", "manta_ray"))

            .addOptional(ResourceLocation("bountiful_critters", "sunfish"))
            .addOptional(ResourceLocation("bountiful_critters", "ray"))

            .addOptional(ResourceLocation("spawn", "tuna"))

            .addOptional(ResourceLocation("aquaculture", "arapaima"))
            .addOptional(ResourceLocation("aquaculture", "tuna"))
            .addOptional(ResourceLocation("aquaculture", "atlantic_cod"))
            .addOptional(ResourceLocation("aquaculture", "pacific_halibut"))
            .addOptional(ResourceLocation("aquaculture", "atlantic_halibut"))
            .addOptional(ResourceLocation("aquaculture", "capitaine"))
            .addOptional(ResourceLocation("aquaculture", "tambaqui"))
            .addOptional(ResourceLocation("aquaculture", "gar"))
            .addOptional(ResourceLocation("aquaculture", "muskellunge"))

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

            .addOptional(ResourceLocation("alexsmobs", "lobster"))

            .addOptional(ResourceLocation("shellfish", "crayfish"))
            .addOptional(ResourceLocation("shellfish", "lobster"))
            .addOptional(ResourceLocation("shellfish", "crab"))
            .addOptional(ResourceLocation("shellfish", "shrimp"))

            .addOptional(ResourceLocation("seafarer", "crab"))
            .addOptional(ResourceLocation("seafarer", "horseshoe_crab"))
            .addOptional(ResourceLocation("seafarer", "mantis_shrimp"))
            .addOptional(ResourceLocation("seafarer", "sexy_shrimp"))
            .addOptional(ResourceLocation("seafarer", "spider_crab"))

            .addOptional(ResourceLocation("rainbowreef", "arrow_crab"))
            .addOptional(ResourceLocation("rainbowreef", "crab"))

            .addOptional(ResourceLocation("bountiful_critters", "krill"))

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

            .addOptional(ResourceLocation("alexsmobs", "mimic_octopus"))
            .addOptional(ResourceLocation("alexsmobs", "giant_squid"))

            .addOptional(ResourceLocation("crittersandcompanions", "dumbo_octopus"))
            .addOptional(ResourceLocation("crittersandcompanions", "dumbo_octopus"))

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

            .addOptional(ResourceLocation("alexsmobs", "hammerhead_shark"))
            .addOptional(ResourceLocation("alexsmobs", "frilled_shark"))

            .addOptional(ResourceLocation("seafarer", "zebra_shark"))

            .addOptional(ResourceLocation("rainbowreef", "small_shark"))

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

            .addOptional(ResourceLocation("alexsmobs", "triops"))
            .addOptional(ResourceLocation("alexscaves", "sea_pig"))

            .addOptional(ResourceLocation("shellfish", "sea_snail"))
            .addOptional(ResourceLocation("shellfish", "sea_urchin"))
            .addOptional(ResourceLocation("shellfish", "clam"))
            .addOptional(ResourceLocation("shellfish", "oyster"))
            .addOptional(ResourceLocation("shellfish", "mussel"))

            .addOptional(ResourceLocation("crittersandcompanions", "sea_bunny"))

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

            .addOptional(ResourceLocation("alexsmobs", "comb_jelly"))

            .addOptional(ResourceLocation("jellyfishing", "jellyfish"))
            .addOptional(ResourceLocation("jellyfishing", "blue_jellyfish"))

            .addOptional(ResourceLocation("rainbowreef", "jellyfish"))

            .addOptional(ResourceLocation("aquaculture", "jellyfish"))

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
            .addOptional(ResourceLocation("tide", "angelfish"))
            .addOptional(ResourceLocation("tide", "barracuda"))
            .addOptional(ResourceLocation("tide", "bass"))
            .addOptional(ResourceLocation("tide", "bluegill"))
            .addOptional(ResourceLocation("tide", "catfish"))
            .addOptional(ResourceLocation("tide", "clayfish"))
            .addOptional(ResourceLocation("tide", "guppy"))
            .addOptional(ResourceLocation("tide", "mackerel"))
            .addOptional(ResourceLocation("tide", "mint_carp"))
            .addOptional(ResourceLocation("tide", "ocean_perch"))
            .addOptional(ResourceLocation("tide", "pike"))
            .addOptional(ResourceLocation("tide", "sailfish"))
            .addOptional(ResourceLocation("tide", "trout"))
            .addOptional(ResourceLocation("tide", "tuna"))
            .addOptional(ResourceLocation("tide", "yellow_perch"))

            // Alexs Mods
            .addOptional(ResourceLocation("alexsmobs", "blobfish"))
            .addOptional(ResourceLocation("alexsmobs", "catfish"))
            .addOptional(ResourceLocation("alexsmobs", "devils_hole_pupfish"))
            .addOptional(ResourceLocation("alexsmobs", "flying_fish"))

            .addOptional(ResourceLocation("alexscaves", "tripodfish"))
            .addOptional(ResourceLocation("alexscaves", "lanternfish"))

            // Seafarer
            .addOptional(ResourceLocation("seafarer", "barreleye"))
            .addOptional(ResourceLocation("seafarer", "sunfish"))
            .addOptional(ResourceLocation("seafarer", "garden_eel"))
            .addOptional(ResourceLocation("seafarer", "mandarin_goby"))
            .addOptional(ResourceLocation("seafarer", "frogfish"))
            .addOptional(ResourceLocation("seafarer", "blue_tang"))
            .addOptional(ResourceLocation("seafarer", "copperband_butterflyfish"))
            .addOptional(ResourceLocation("seafarer", "parrotfish"))
            .addOptional(ResourceLocation("seafarer", "filefish"))
            .addOptional(ResourceLocation("seafarer", "leafy_scorpionfish"))
            .addOptional(ResourceLocation("seafarer", "chimaera"))
            .addOptional(ResourceLocation("seafarer", "squirrelfish"))

            // Naturalist
            .addOptional(ResourceLocation("naturalist", "bass"))
            .addOptional(ResourceLocation("naturalist", "catfish"))

            // Rainbow Reef
            .addOptional(ResourceLocation("rainbowreef", "angelfish"))
            .addOptional(ResourceLocation("rainbowreef", "basslet"))
            .addOptional(ResourceLocation("rainbowreef", "boxfish"))
            .addOptional(ResourceLocation("rainbowreef", "butterfish"))
            .addOptional(ResourceLocation("rainbowreef", "clownfish"))
            .addOptional(ResourceLocation("rainbowreef", "dwarf_angelfish"))
            .addOptional(ResourceLocation("rainbowreef", "goby"))
            .addOptional(ResourceLocation("rainbowreef", "moorish_idol"))
            .addOptional(ResourceLocation("rainbowreef", "pipefish"))
            .addOptional(ResourceLocation("rainbowreef", "tang"))
            .addOptional(ResourceLocation("rainbowreef", "seahorse"))
            .addOptional(ResourceLocation("rainbowreef", "hogfish"))
            .addOptional(ResourceLocation("rainbowreef", "parrotfish"))
            .addOptional(ResourceLocation("rainbowreef", "ray"))

            // Bountiful Critters
            .addOptional(ResourceLocation("bountiful_critters", "stingray"))
            .addOptional(ResourceLocation("bountiful_critters", "sunfish"))
            .addOptional(ResourceLocation("bountiful_critters", "flounder"))
            .addOptional(ResourceLocation("bountiful_critters", "barreleye"))
            .addOptional(ResourceLocation("bountiful_critters", "angelfish"))
            .addOptional(ResourceLocation("bountiful_critters", "neon_tetra"))

            // Spawn
            .addOptional(ResourceLocation("spawn", "tuna"))
            .addOptional(ResourceLocation("spawn", "seahorse"))
            .addOptional(ResourceLocation("spawn", "anglerfish"))

            // Fish Of Thieves
            .addOptional(ResourceLocation("fishofthieves", "ancientscale"))
            .addOptional(ResourceLocation("fishofthieves", "battlegill"))
            .addOptional(ResourceLocation("fishofthieves", "devilfish"))
            .addOptional(ResourceLocation("fishofthieves", "islehopper"))
            .addOptional(ResourceLocation("fishofthieves", "plentifin"))
            .addOptional(ResourceLocation("fishofthieves", "pondie"))
            .addOptional(ResourceLocation("fishofthieves", "splashtail"))
            .addOptional(ResourceLocation("fishofthieves", "stormfish"))
            .addOptional(ResourceLocation("fishofthieves", "wildsplash"))
            .addOptional(ResourceLocation("fishofthieves", "wrecker"))

            // Critters & Companions
            .addOptional(ResourceLocation("crittersandcompanions", "koi_fish"))

            // Aquaculture
            .addOptional(ResourceLocation("aquaculture", "atlantic_cod"))
            .addOptional(ResourceLocation("aquaculture", "blackfish"))
            .addOptional(ResourceLocation("aquaculture", "pacific_halibut"))
            .addOptional(ResourceLocation("aquaculture", "atlantic_halibut"))
            .addOptional(ResourceLocation("aquaculture", "atlantic_herring"))
            .addOptional(ResourceLocation("aquaculture", "pink_salmon"))
            .addOptional(ResourceLocation("aquaculture", "pollock"))
            .addOptional(ResourceLocation("aquaculture", "rainbow_trout"))
            .addOptional(ResourceLocation("aquaculture", "bayad"))
            .addOptional(ResourceLocation("aquaculture", "boulti"))
            .addOptional(ResourceLocation("aquaculture", "capitaine"))
            .addOptional(ResourceLocation("aquaculture", "synodontis"))
            .addOptional(ResourceLocation("aquaculture", "smallmouth_bass"))
            .addOptional(ResourceLocation("aquaculture", "bluegill"))
            .addOptional(ResourceLocation("aquaculture", "brown_trout"))
            .addOptional(ResourceLocation("aquaculture", "carp"))
            .addOptional(ResourceLocation("aquaculture", "catfish"))
            .addOptional(ResourceLocation("aquaculture", "gar"))
            .addOptional(ResourceLocation("aquaculture", "minnow"))
            .addOptional(ResourceLocation("aquaculture", "muskellunge"))
            .addOptional(ResourceLocation("aquaculture", "perch"))
            .addOptional(ResourceLocation("aquaculture", "arapaima"))
            .addOptional(ResourceLocation("aquaculture", "piranha"))
            .addOptional(ResourceLocation("aquaculture", "tambaqui"))
            .addOptional(ResourceLocation("aquaculture", "brown_shrooma"))
            .addOptional(ResourceLocation("aquaculture", "red_shrooma"))
            .addOptional(ResourceLocation("aquaculture", "red_grouper"))
            .addOptional(ResourceLocation("aquaculture", "tuna"))

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
