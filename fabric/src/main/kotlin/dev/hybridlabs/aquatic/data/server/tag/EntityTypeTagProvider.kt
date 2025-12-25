package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.EntityTypeTags
import net.minecraft.world.entity.EntityType
import java.util.concurrent.CompletableFuture

class EntityTypeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.EntityTypeTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        // small prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.SMALL_PREY)
            .add(
                HybridAquaticEntityTypes.CLOWNFISH.get(),
                HybridAquaticEntityTypes.PLECO.get(),
                HybridAquaticEntityTypes.SHINER.get(),
                HybridAquaticEntityTypes.SUNFISH.get(),
                HybridAquaticEntityTypes.CARP.get(),
                HybridAquaticEntityTypes.PEARLFISH.get(),
                HybridAquaticEntityTypes.SNAILFISH.get(),
                HybridAquaticEntityTypes.BOXFISH.get(),
                HybridAquaticEntityTypes.OSCAR.get(),
                HybridAquaticEntityTypes.FLASHLIGHT_FISH.get(),
                HybridAquaticEntityTypes.MACKEREL.get(),
                HybridAquaticEntityTypes.HERRING.get(),
                HybridAquaticEntityTypes.BARRELEYE.get(),
                HybridAquaticEntityTypes.BETTA.get(),
                HybridAquaticEntityTypes.TETRA.get(),
                HybridAquaticEntityTypes.DANIO.get(),
                HybridAquaticEntityTypes.TIGER_BARB.get(),
                HybridAquaticEntityTypes.SURGEONFISH.get(),
                HybridAquaticEntityTypes.DISCUS.get(),
                HybridAquaticEntityTypes.DAMSELFISH.get(),
                HybridAquaticEntityTypes.GOURAMI.get(),
                HybridAquaticEntityTypes.CUTTLEFISH.get(),
                HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
                HybridAquaticEntityTypes.FLYING_FISH.get(),
                HybridAquaticEntityTypes.SQUIRRELFISH.get(),
                HybridAquaticEntityTypes.STONEFISH.get(),
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH
            )
            .addOptional(ResourceLocation("rainbowreef", "clownfish"))
            .addOptional(ResourceLocation("rainbowreef", "basslet"))
            .addOptional(ResourceLocation("rainbowreef", "boxfish"))
            .addOptional(ResourceLocation("rainbowreef", "goby"))
            .addOptional(ResourceLocation("rainbowreef", "tang"))
            .addOptional(ResourceLocation("rainbowreef", "pipefish"))
            .addOptional(ResourceLocation("rainbowreef", "seahorse"))
            .addOptional(ResourceLocation("rainbowreef", "dwarf_angelfish"))
            .addOptional(ResourceLocation("rainbowreef", "butterflyfish"))
            .addOptional(ResourceLocation("rainbowreef", "moorish_idol"))
            .addOptional(ResourceLocation("bountiful_critters", "angelfish"))
            .addOptional(ResourceLocation("bountiful_critters", "flounder"))
            .addOptional(ResourceLocation("bountiful_critters", "neon_tetra"))
            .addOptional(ResourceLocation("bountiful_critters", "barreleye"))
            .addOptional(ResourceLocation("fintastic", "gourami"))
            .addOptional(ResourceLocation("fintastic", "guppy"))
            .addOptional(ResourceLocation("fintastic", "minnow"))
            .addOptional(ResourceLocation("fintastic", "moony"))
            .addOptional(ResourceLocation("fintastic", "pleco"))
            .addOptional(ResourceLocation("spawn", "angler_fish"))
            .addOptional(ResourceLocation("spawn", "seahorse"))
            .addOptional(ResourceLocation("spawn", "herring"))
            .addOptional(ResourceLocation("alexsmobs", "mudskipper"))
            .addOptional(ResourceLocation("alexsmobs", "devils_hole_pupfish"))
            .addOptional(ResourceLocation("alexsmobs", "flying_fish"))

        // medium prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.MEDIUM_PREY)
            .add(
                HybridAquaticEntityTypes.RATFISH.get(),
                HybridAquaticEntityTypes.STINGRAY.get(),
                HybridAquaticEntityTypes.TRIGGERFISH.get(),
                HybridAquaticEntityTypes.NEEDLEFISH.get(),
                HybridAquaticEntityTypes.TROUT.get(),
                HybridAquaticEntityTypes.ROCKFISH.get(),
                HybridAquaticEntityTypes.SEA_BASS.get(),
                HybridAquaticEntityTypes.LIONFISH.get(),
                HybridAquaticEntityTypes.PARROTFISH.get(),
                HybridAquaticEntityTypes.WRASSE.get(),
                HybridAquaticEntityTypes.MORAY_EEL.get(),
                HybridAquaticEntityTypes.JOHN_DORY.get(),
                HybridAquaticEntityTypes.LANTERN_SHARK.get(),
            )
            .addOptional(ResourceLocation("rainbowreef", "angelfish"))
            .addOptional(ResourceLocation("rainbowreef", "hogfish"))
            .addOptional(ResourceLocation("rainbowreef", "parrotfish"))
            .addOptional(ResourceLocation("rainbowreef", "ray"))
            .addOptional(ResourceLocation("bountiful_critters", "stingray"))
            .addOptional(ResourceLocation("fintastic", "featherback"))
            .addOptional(ResourceLocation("fintastic", "freshwater_shark"))
            .addOptional(ResourceLocation("alexsmobs", "blobfish"))

        // large prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.LARGE_PREY)
            .add(
                HybridAquaticEntityTypes.OCEAN_SUNFISH.get(),
                HybridAquaticEntityTypes.OARFISH.get(),
                HybridAquaticEntityTypes.OPAH.get(),
                HybridAquaticEntityTypes.TUNA.get(),
                HybridAquaticEntityTypes.MAHI.get(),
                HybridAquaticEntityTypes.BARRACUDA.get(),
                HybridAquaticEntityTypes.COELACANTH.get(),
                HybridAquaticEntityTypes.GOLDEN_DORADO.get(),
                EntityType.PLAYER,
                EntityType.TURTLE,
            )
            .addOptional(ResourceLocation("bountiful_critters", "sunfish"))
            .addOptional(ResourceLocation("fintastic", "arapaima"))
            .addOptional(ResourceLocation("fintastic", "catfish"))
            .addOptional(ResourceLocation("fintastic", "coelacanth"))
            .addOptional(ResourceLocation("spawn", "sunfish"))
            .addOptional(ResourceLocation("spawn", "tuna"))
            .addOptional(ResourceLocation("spawn", "barracuda"))
            .addOptional(ResourceLocation("spawn", "sea_cow"))
            .addOptional(ResourceLocation("alexsmobs", "catfish"))
            .addOptional(ResourceLocation("alexsmobs", "seal"))

        // otter prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.KELP_PREY)
            .add(HybridAquaticEntityTypes.SEA_URCHIN.get())
            .forceAddTag(HybridAquaticEntityTags.CRUSTACEAN)
            .forceAddTag(HybridAquaticEntityTags.SMALL_PREY)

        // crustaceans
        getOrCreateTagBuilder(HybridAquaticEntityTags.CRUSTACEAN)
            .add(
                HybridAquaticEntityTypes.COCONUT_CRAB.get(),
                HybridAquaticEntityTypes.CRAYFISH.get(),
                HybridAquaticEntityTypes.DECORATOR_CRAB.get(),
                HybridAquaticEntityTypes.DUNGENESS_CRAB.get(),
                HybridAquaticEntityTypes.FIDDLER_CRAB.get(),
                HybridAquaticEntityTypes.FLOWER_CRAB.get(),
                HybridAquaticEntityTypes.GHOST_CRAB.get(),
                HybridAquaticEntityTypes.GIANT_ISOPOD.get(),
                HybridAquaticEntityTypes.HERMIT_CRAB.get(),
                HybridAquaticEntityTypes.HORSESHOE_CRAB.get(),
                HybridAquaticEntityTypes.LIGHTFOOT_CRAB.get(),
                HybridAquaticEntityTypes.LOBSTER.get(),
                HybridAquaticEntityTypes.SHRIMP.get(),
                HybridAquaticEntityTypes.SPIDER_CRAB.get(),
                HybridAquaticEntityTypes.VAMPIRE_CRAB.get(),
                HybridAquaticEntityTypes.YETI_CRAB.get(),
            )
            .addOptional(ResourceLocation("rainbowreef", "crab"))
            .addOptional(ResourceLocation("rainbowreef", "arrow_crab"))
            .addOptional(ResourceLocation("bountiful_critters", "krill"))
            .addOptional(ResourceLocation("fintastic", "fairy_shrimp"))
            .addOptional(ResourceLocation("fintastic", "daphnia"))
            .addOptional(ResourceLocation("alexsmobs", "lobster"))
            .addOptional(ResourceLocation("alexsmobs", "mantis_shrimp"))
            .addOptional(ResourceLocation("alexsmobs", "triops"))

        // cephalopods
        getOrCreateTagBuilder(HybridAquaticEntityTags.CEPHALOPOD)
            .add(
                HybridAquaticEntityTypes.ARROW_SQUID.get(),
                HybridAquaticEntityTypes.CUTTLEFISH.get(),
                HybridAquaticEntityTypes.FIREFLY_SQUID.get(),
                HybridAquaticEntityTypes.OCTOPUS.get(),
                HybridAquaticEntityTypes.NAUTILUS.get(),
                HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(),
                HybridAquaticEntityTypes.VAMPIRE_SQUID.get(),
                EntityType.SQUID,
                EntityType.GLOW_SQUID
            )
            .addOptional(ResourceLocation("spawn", "octopus"))
            .addOptional(ResourceLocation("alexsmobs", "mimic_octopus"))
            .addOptional(ResourceLocation("alexsmobs", "giant_squid"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.NONE)

        getOrCreateTagBuilder(EntityTypeTags.AXOLOTL_HUNT_TARGETS)
            .add(
                HybridAquaticEntityTypes.TETRA.get(),
                HybridAquaticEntityTypes.DANIO.get(),
                HybridAquaticEntityTypes.TIGER_BARB.get(),
                HybridAquaticEntityTypes.BETTA.get(),
                HybridAquaticEntityTypes.OSCAR.get(),
                HybridAquaticEntityTypes.DISCUS.get(),
            )

        // sharks
        getOrCreateTagBuilder(HybridAquaticEntityTags.SHARK)
            .add(
                HybridAquaticEntityTypes.BASKING_SHARK.get(),
                HybridAquaticEntityTypes.BULL_SHARK.get(),
                HybridAquaticEntityTypes.FRILLED_SHARK.get(),
                HybridAquaticEntityTypes.GREAT_WHITE_SHARK.get(),
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK.get(),
                HybridAquaticEntityTypes.THRESHER_SHARK.get(),
                HybridAquaticEntityTypes.TIGER_SHARK.get(),
                HybridAquaticEntityTypes.WHALE_SHARK.get(),
                HybridAquaticEntityTypes.LANTERN_SHARK.get(),
                HybridAquaticEntityTypes.HOUND_SHARK.get(),
            )
            .addOptional(ResourceLocation("rainbowreef", "small_shark"))
            .addOptional(ResourceLocation("alexsmobs", "frilled_shark"))
            .addOptional(ResourceLocation("alexsmobs", "hammerhead_shark"))

        // critters
        getOrCreateTagBuilder(HybridAquaticEntityTags.CRITTER)
            .add(
                HybridAquaticEntityTypes.SEA_SLUG.get(),
                HybridAquaticEntityTypes.SEA_CUCUMBER.get(),
                HybridAquaticEntityTypes.SEA_URCHIN.get(),
                HybridAquaticEntityTypes.STARFISH.get(),
                HybridAquaticEntityTypes.SEA_ANGEL.get(),
            )
            .addOptional(ResourceLocation("spawn", "clam"))

        // jellyfish
        getOrCreateTagBuilder(HybridAquaticEntityTags.JELLYFISH)
            .add(
                HybridAquaticEntityTypes.CROWN_JELLYFISH.get(),
                HybridAquaticEntityTypes.BARREL_JELLYFISH.get(),
                HybridAquaticEntityTypes.BLUE_JELLYFISH.get(),
                HybridAquaticEntityTypes.BIG_RED_JELLYFISH.get(),
                HybridAquaticEntityTypes.CEPHEIDAE_JELLYFISH.get(),
                HybridAquaticEntityTypes.COSMIC_JELLYFISH.get(),
                HybridAquaticEntityTypes.FIREWORK_JELLYFISH.get(),
                HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH.get(),
                HybridAquaticEntityTypes.MAUVE_STINGER.get(),
                HybridAquaticEntityTypes.MOON_JELLYFISH.get(),
                HybridAquaticEntityTypes.NOMURA_JELLYFISH.get(),
                HybridAquaticEntityTypes.SEA_NETTLE.get(),
                HybridAquaticEntityTypes.BOX_JELLYFISH.get(),
            )
            .addOptional(ResourceLocation("rainbowreef", "jellyfish"))

        // fish
        getOrCreateTagBuilder(HybridAquaticEntityTags.FISH)
            .add(
                HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
                HybridAquaticEntityTypes.BLOWFISH.get(),
                HybridAquaticEntityTypes.STONEFISH.get(),
                HybridAquaticEntityTypes.PLECO.get(),
                HybridAquaticEntityTypes.SHINER.get(),
                HybridAquaticEntityTypes.TROUT.get(),
                HybridAquaticEntityTypes.SUNFISH.get(),
                HybridAquaticEntityTypes.DAMSELFISH.get(),
                HybridAquaticEntityTypes.PEARLFISH.get(),
                HybridAquaticEntityTypes.BETTA.get(),
                HybridAquaticEntityTypes.JOHN_DORY.get(),
                HybridAquaticEntityTypes.SNAILFISH.get(),
                HybridAquaticEntityTypes.CARP.get(),
                HybridAquaticEntityTypes.CLOWNFISH.get(),
                HybridAquaticEntityTypes.BOXFISH.get(),
                HybridAquaticEntityTypes.DANIO.get(),
                HybridAquaticEntityTypes.DISCUS.get(),
                HybridAquaticEntityTypes.FLASHLIGHT_FISH.get(),
                HybridAquaticEntityTypes.SQUIRRELFISH.get(),
                HybridAquaticEntityTypes.FLYING_FISH.get(),
                HybridAquaticEntityTypes.GOLDFISH.get(),
                HybridAquaticEntityTypes.GOURAMI.get(),
                HybridAquaticEntityTypes.LIONFISH.get(),
                HybridAquaticEntityTypes.MACKEREL.get(),
                HybridAquaticEntityTypes.HERRING.get(),
                HybridAquaticEntityTypes.MAHI.get(),
                HybridAquaticEntityTypes.MORAY_EEL.get(),
                HybridAquaticEntityTypes.NEEDLEFISH.get(),
                HybridAquaticEntityTypes.BARRACUDA.get(),
                HybridAquaticEntityTypes.OARFISH.get(),
                HybridAquaticEntityTypes.OPAH.get(),
                HybridAquaticEntityTypes.OSCAR.get(),
                HybridAquaticEntityTypes.PARROTFISH.get(),
                HybridAquaticEntityTypes.PIRANHA.get(),
                HybridAquaticEntityTypes.ROCKFISH.get(),
                HybridAquaticEntityTypes.SEA_BASS.get(),
                HybridAquaticEntityTypes.SEAHORSE.get(),
                HybridAquaticEntityTypes.SEADRAGON.get(),
                HybridAquaticEntityTypes.OCEAN_SUNFISH.get(),
                HybridAquaticEntityTypes.SURGEONFISH.get(),
                HybridAquaticEntityTypes.TETRA.get(),
                HybridAquaticEntityTypes.TIGER_BARB.get(),
                HybridAquaticEntityTypes.TRIGGERFISH.get(),
                HybridAquaticEntityTypes.TUNA.get(),
                HybridAquaticEntityTypes.MANTA_RAY.get(),
                HybridAquaticEntityTypes.STINGRAY.get(),
                HybridAquaticEntityTypes.ANGLERFISH.get(),
                HybridAquaticEntityTypes.BARRELEYE.get(),
                HybridAquaticEntityTypes.DRAGONFISH.get(),
                HybridAquaticEntityTypes.RATFISH.get(),
                HybridAquaticEntityTypes.GOLDEN_DORADO.get(),
                HybridAquaticEntityTypes.COELACANTH.get(),
                HybridAquaticEntityTypes.WRASSE.get(),
                EntityType.COD,
                EntityType.SALMON,
                EntityType.TROPICAL_FISH,
                EntityType.AXOLOTL,
            )

        // entities that you can catch with the fishing net
        getOrCreateTagBuilder(HybridAquaticEntityTags.CAN_USE_FISHING_NET_ON)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.CRITTER)
            .addTag(HybridAquaticEntityTags.CRUSTACEAN)
            .addTag(HybridAquaticEntityTags.CEPHALOPOD)
            .addTag(HybridAquaticEntityTags.FISH)
            .addTag(HybridAquaticEntityTags.SHARK)
    }
}
