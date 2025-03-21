package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.EntityTypeTags
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture

class EntityTypeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.EntityTypeTagProvider(output, registriesFuture) {
    override fun configure(lookup: RegistryWrapper.WrapperLookup) {
        lookup.getWrapperOrThrow(RegistryKeys.ENTITY_TYPE).streamKeys().forEach { key ->
            val id = key.value
            if (id.namespace == HybridAquatic.MOD_ID) {
                getOrCreateTagBuilder(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(id)
            }
        }

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
                HybridAquaticEntityTypes.AFRICAN_BUTTERFLY,
                HybridAquaticEntityTypes.FLYING_FISH,
                HybridAquaticEntityTypes.SQUIRRELFISH,
                HybridAquaticEntityTypes.STONEFISH,
                HybridAquaticEntityTypes.ANGLERFISH,
                HybridAquaticEntityTypes.CARP,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH
            )
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
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.JOHN_DORY,
                HybridAquaticEntityTypes.LANTERN_SHARK,
            )
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
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.MAHI,
                HybridAquaticEntityTypes.COELACANTH,
                HybridAquaticEntityTypes.GOLDEN_DORADO,
                EntityType.PLAYER,
                EntityType.TURTLE,
            )
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
                HybridAquaticEntityTypes.THRESHER_SHARK,
                HybridAquaticEntityTypes.TIGER_SHARK,
                HybridAquaticEntityTypes.WHALE_SHARK,
                HybridAquaticEntityTypes.LANTERN_SHARK,
            )
            .addOptional(Identifier("rainbowreef", "small_shark"))

        // critters
        getOrCreateTagBuilder(HybridAquaticEntityTags.CRITTER)
            .add(
                HybridAquaticEntityTypes.NUDIBRANCH,
                HybridAquaticEntityTypes.SEA_CUCUMBER,
                HybridAquaticEntityTypes.SEA_URCHIN,
                HybridAquaticEntityTypes.STARFISH,
                HybridAquaticEntityTypes.SEA_ANGEL,
            )
            .addOptional(Identifier("crittersandcompanions", "sea_bunny"))

        // jellyfish
        getOrCreateTagBuilder(HybridAquaticEntityTags.JELLYFISH)
            .add(
                HybridAquaticEntityTypes.ATOLLA_JELLYFISH,
                HybridAquaticEntityTypes.BARREL_JELLYFISH,
                HybridAquaticEntityTypes.BLUE_JELLYFISH,
                HybridAquaticEntityTypes.BIG_RED_JELLYFISH,
                HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH,
                HybridAquaticEntityTypes.COMPASS_JELLYFISH,
                HybridAquaticEntityTypes.COSMIC_JELLYFISH,
                HybridAquaticEntityTypes.FIREWORK_JELLYFISH,
                HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH,
                HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH,
                HybridAquaticEntityTypes.MAUVE_STINGER,
                HybridAquaticEntityTypes.MOON_JELLYFISH,
                HybridAquaticEntityTypes.NOMURA_JELLYFISH,
                HybridAquaticEntityTypes.SEA_NETTLE,
                HybridAquaticEntityTypes.BOX_JELLYFISH,
            )
            .addOptional(Identifier("rainbowreef", "jellyfish"))

            .addOptional(Identifier("aquaculture", "jellyfish"))

        // fish
        getOrCreateTagBuilder(HybridAquaticEntityTags.FISH)
            .add(
                HybridAquaticEntityTypes.AFRICAN_BUTTERFLY,
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
                HybridAquaticEntityTypes.PIRANHA,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.SEA_BASS,
                HybridAquaticEntityTypes.SEAHORSE,
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
            .addTag(HybridAquaticEntityTags.FISH)
            .addTag(HybridAquaticEntityTags.SHARK)
    }
}
