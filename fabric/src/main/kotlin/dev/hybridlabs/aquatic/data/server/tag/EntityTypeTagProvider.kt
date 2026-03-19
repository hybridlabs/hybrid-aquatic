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

        //#region Vanilla Tags
        getOrCreateTagBuilder(EntityTypeTags.AXOLOTL_HUNT_TARGETS)
            .add(
                HybridAquaticEntityTypes.TETRA.get(),
                HybridAquaticEntityTypes.DANIO.get(),
                HybridAquaticEntityTypes.TIGER_BARB.get(),
                HybridAquaticEntityTypes.BETTA.get(),
                HybridAquaticEntityTypes.OSCAR.get(),
                HybridAquaticEntityTypes.DISCUS.get(),
                HybridAquaticEntityTypes.CORYDORA.get(),
            )
        //#endregion

        //#region Food Chain Tags
        getOrCreateTagBuilder(HybridAquaticEntityTags.BAIT_FISH)
            .add(
                HybridAquaticEntityTypes.MACKEREL.get(),
                HybridAquaticEntityTypes.HERRING.get(),
                HybridAquaticEntityTypes.FLYING_FISH.get(),
                HybridAquaticEntityTypes.SQUIRRELFISH.get(),
                HybridAquaticEntityTypes.FLASHLIGHT_FISH.get(),
                EntityType.COD
            )
            .addOptional(ResourceLocation("fintastic", "minnow"))
            .addOptional(ResourceLocation("spawn", "herring"))
            .addOptional(ResourceLocation("alexsmobs", "flying_fish"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.TOXIC_ANIMALS)
            .add(
                HybridAquaticEntityTypes.STONEFISH.get(),
                HybridAquaticEntityTypes.LIONFISH.get(),
                HybridAquaticEntityTypes.BLOWFISH.get(),
                EntityType.PUFFERFISH
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.SMALL_CREATURES)
            .add(
                HybridAquaticEntityTypes.CLOWNFISH.get(),
                HybridAquaticEntityTypes.GARDEN_EEL.get(),
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
                HybridAquaticEntityTypes.CORYDORA.get(),
                HybridAquaticEntityTypes.DAMSELFISH.get(),
                HybridAquaticEntityTypes.GOURAMI.get(),
                HybridAquaticEntityTypes.CUTTLEFISH.get(),
                HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
                HybridAquaticEntityTypes.FLYING_FISH.get(),
                HybridAquaticEntityTypes.SQUIRRELFISH.get(),
                HybridAquaticEntityTypes.STONEFISH.get(),
                HybridAquaticEntityTypes.PUPFISH.get(),
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
            .addOptional(ResourceLocation("alexscaves", "lanternfish"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.MEDIUM_CREATURES)
            .add(
                HybridAquaticEntityTypes.RATFISH.get(),
                HybridAquaticEntityTypes.TRIPOD_FISH.get(),
                HybridAquaticEntityTypes.STINGRAY.get(),
                HybridAquaticEntityTypes.TRIGGERFISH.get(),
                HybridAquaticEntityTypes.TREVALLY.get(),
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
            .addOptional(ResourceLocation("alexscaves", "tripodfish"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.LARGE_CREATURES)
            .add(
                HybridAquaticEntityTypes.OCEAN_SUNFISH.get(),
                HybridAquaticEntityTypes.OARFISH.get(),
                HybridAquaticEntityTypes.OPAH.get(),
                HybridAquaticEntityTypes.TUNA.get(),
                HybridAquaticEntityTypes.MAHI.get(),
                HybridAquaticEntityTypes.BARRACUDA.get(),
                HybridAquaticEntityTypes.COELACANTH.get(),
                HybridAquaticEntityTypes.SLICKHEAD.get(),
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

        getOrCreateTagBuilder(HybridAquaticEntityTags.OTTER_PREY)
            .add(HybridAquaticEntityTypes.SEA_URCHIN.get())
            .forceAddTag(HybridAquaticEntityTags.ALL_CRUSTACEANS)
            .forceAddTag(HybridAquaticEntityTags.SMALL_CREATURES)
        //#endregion

        //#region Fish Tags
        getOrCreateTagBuilder(HybridAquaticEntityTags.ALL_FISH)
            .addTag(HybridAquaticEntityTags.RAY)
            .addTag(HybridAquaticEntityTags.REEF_FISH)
            .addTag(HybridAquaticEntityTags.DEEP_FISH)
            .addTag(HybridAquaticEntityTags.RIVER_FISH)
            .addTag(HybridAquaticEntityTags.TROPICAL_RIVER_FISH)
            .addTag(HybridAquaticEntityTags.SWAMP_FISH)
            .addTag(HybridAquaticEntityTags.MANGROVE_FISH)
            .addOptional(ResourceLocation("bountiful_critters", "flounder"))
            .addOptional(ResourceLocation("spawn", "herring"))
            .addOptional(ResourceLocation("alexsmobs", "devils_hole_pupfish"))
            .addOptional(ResourceLocation("bountiful_critters", "sunfish"))
            .addOptional(ResourceLocation("spawn", "sunfish"))
            .addOptional(ResourceLocation("spawn", "tuna"))
            .addOptional(ResourceLocation("spawn", "barracuda"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.REEF_FISH)
            .add(
                HybridAquaticEntityTypes.BLOWFISH.get(),
                HybridAquaticEntityTypes.BOXFISH.get(),
                HybridAquaticEntityTypes.CLOWNFISH.get(),
                HybridAquaticEntityTypes.GARDEN_EEL.get(),
                HybridAquaticEntityTypes.DAMSELFISH.get(),
                HybridAquaticEntityTypes.FLYING_FISH.get(),
                HybridAquaticEntityTypes.LIONFISH.get(),
                HybridAquaticEntityTypes.MORAY_EEL.get(),
                HybridAquaticEntityTypes.NEEDLEFISH.get(),
                HybridAquaticEntityTypes.PARROTFISH.get(),
                HybridAquaticEntityTypes.SEAHORSE.get(),
                HybridAquaticEntityTypes.SQUIRRELFISH.get(),
                HybridAquaticEntityTypes.STONEFISH.get(),
                HybridAquaticEntityTypes.SURGEONFISH.get(),
                HybridAquaticEntityTypes.TRIGGERFISH.get(),
                HybridAquaticEntityTypes.TREVALLY.get(),
                HybridAquaticEntityTypes.WRASSE.get(),
                EntityType.TROPICAL_FISH,
            )
            .addOptional(ResourceLocation("alexsmobs", "flying_fish"))
            .addOptional(ResourceLocation("bountiful_critters", "angelfish"))
            .addOptional(ResourceLocation("spawn", "seahorse"))
            .addOptional(ResourceLocation("rainbowreef", "angelfish"))
            .addOptional(ResourceLocation("rainbowreef", "basslet"))
            .addOptional(ResourceLocation("rainbowreef", "boxfish"))
            .addOptional(ResourceLocation("rainbowreef", "butterflyfish"))
            .addOptional(ResourceLocation("rainbowreef", "clownfish"))
            .addOptional(ResourceLocation("rainbowreef", "dwarf_angelfish"))
            .addOptional(ResourceLocation("rainbowreef", "goby"))
            .addOptional(ResourceLocation("rainbowreef", "hogfish"))
            .addOptional(ResourceLocation("rainbowreef", "moorish_idol"))
            .addOptional(ResourceLocation("rainbowreef", "parrotfish"))
            .addOptional(ResourceLocation("rainbowreef", "pipefish"))
            .addOptional(ResourceLocation("rainbowreef", "ray"))
            .addOptional(ResourceLocation("rainbowreef", "seahorse"))
            .addOptional(ResourceLocation("rainbowreef", "tang"))
            .addOptional(ResourceLocation("wonderoussea", "tiger_scatfish"))
            .addOptional(ResourceLocation("wonderoussea", "tiger_scatfish_2"))
            .addOptional(ResourceLocation("wonderoussea", "threadfin_lookdown"))
            .addOptional(ResourceLocation("wonderoussea", "threadfin_lookdown_2"))
            .addOptional(ResourceLocation("wonderoussea", "teira_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "teira_spadefish_2"))
            .addOptional(ResourceLocation("wonderoussea", "atlantic_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "atlantic_spadefish_2"))
            .addOptional(ResourceLocation("wonderoussea", "golden_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "golden_spadefish_2"))
            .addOptional(ResourceLocation("wonderoussea", "pacific_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "pacific_spadefish_2"))
            .addOptional(ResourceLocation("wonderoussea", "pinnate_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "pinnate_spadefish_2"))
            .addOptional(ResourceLocation("wonderoussea", "orbicular_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "orbicular_spadefish_2"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.RAY)
            .add(
                HybridAquaticEntityTypes.STINGRAY.get(),
                HybridAquaticEntityTypes.MANTA_RAY.get(),
            )
            .addOptional(ResourceLocation("bountiful_critters", "stingray"))
            .addOptional(ResourceLocation("rainbowreef", "ray"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.DEEP_FISH)
            .add(
                HybridAquaticEntityTypes.ANGLERFISH.get(),
                HybridAquaticEntityTypes.BARRELEYE.get(),
                HybridAquaticEntityTypes.COELACANTH.get(),
                HybridAquaticEntityTypes.SLICKHEAD.get(),
                HybridAquaticEntityTypes.DRAGONFISH.get(),
                HybridAquaticEntityTypes.FANGTOOTH.get(),
                HybridAquaticEntityTypes.FLASHLIGHT_FISH.get(),
                HybridAquaticEntityTypes.HATCHETFISH.get(),
                HybridAquaticEntityTypes.TRIPOD_FISH.get(),
                HybridAquaticEntityTypes.JOHN_DORY.get(),
                HybridAquaticEntityTypes.OARFISH.get(),
                HybridAquaticEntityTypes.RATFISH.get(),
                HybridAquaticEntityTypes.SEA_ANGEL.get(),
                HybridAquaticEntityTypes.SNAILFISH.get(),
                HybridAquaticEntityTypes.VIPERFISH.get(),
            )
            .addOptional(ResourceLocation("spawn", "angler_fish"))
            .addOptional(ResourceLocation("fintastic", "coelacanth"))
            .addOptional(ResourceLocation("alexsmobs", "blobfish"))
            .addOptional(ResourceLocation("alexscaves", "tripodfish"))
            .addOptional(ResourceLocation("alexscaves", "lanternfish"))
            .addOptional(ResourceLocation("alexscaves", "gossamer_worm"))
            .addOptional(ResourceLocation("bountiful_critters", "barreleye"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.RIVER_FISH)
            .add(
                HybridAquaticEntityTypes.CARP.get(),
                HybridAquaticEntityTypes.SHINER.get(),
                HybridAquaticEntityTypes.SUNFISH.get(),
                HybridAquaticEntityTypes.TROUT.get(),
                EntityType.COD,
                EntityType.SALMON
            )
            .addOptional(ResourceLocation("fintastic", "catfish"))
            .addOptional(ResourceLocation("fintastic", "minnow"))
            .addOptional(ResourceLocation("fintastic", "freshwater_shark"))
            .addOptional(ResourceLocation("alexsmobs", "catfish"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.TROPICAL_RIVER_FISH)
            .add(
                HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
                HybridAquaticEntityTypes.DANIO.get(),
                HybridAquaticEntityTypes.DISCUS.get(),
                HybridAquaticEntityTypes.CORYDORA.get(),
                HybridAquaticEntityTypes.GOLDEN_DORADO.get(),
                HybridAquaticEntityTypes.GOURAMI.get(),
                HybridAquaticEntityTypes.OSCAR.get(),
                HybridAquaticEntityTypes.PIRANHA.get(),
                HybridAquaticEntityTypes.PLECO.get(),
                HybridAquaticEntityTypes.TETRA.get(),
                HybridAquaticEntityTypes.TIGER_BARB.get(),
            )
            .addOptional(ResourceLocation("fintastic", "catfish"))
            .addOptional(ResourceLocation("fintastic", "minnow"))
            .addOptional(ResourceLocation("fintastic", "freshwater_shark"))
            .addOptional(ResourceLocation("fintastic", "arapaima"))
            .addOptional(ResourceLocation("fintastic", "pleco"))
            .addOptional(ResourceLocation("fintastic", "guppy"))
            .addOptional(ResourceLocation("bountiful_critters", "neon_tetra"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.SWAMP_FISH)
            .add(
                HybridAquaticEntityTypes.TIGER_BARB.get(),
                HybridAquaticEntityTypes.PLECO.get(),
                HybridAquaticEntityTypes.BETTA.get(),
            )
            .addOptional(ResourceLocation("fintastic", "featherback"))
            .addOptional(ResourceLocation("fintastic", "catfish"))
            .addOptional(ResourceLocation("fintastic", "minnow"))
            .addOptional(ResourceLocation("fintastic", "pleco"))
            .addOptional(ResourceLocation("fintastic", "guppy"))
            .addOptional(ResourceLocation("alexsmobs", "catfish"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.MANGROVE_FISH)
            .add(
                HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
                HybridAquaticEntityTypes.TETRA.get(),
                HybridAquaticEntityTypes.TIGER_BARB.get(),
                HybridAquaticEntityTypes.GOURAMI.get(),
                HybridAquaticEntityTypes.PLECO.get(),
                HybridAquaticEntityTypes.DISCUS.get(),
                HybridAquaticEntityTypes.OSCAR.get(),
                HybridAquaticEntityTypes.DANIO.get(),
                HybridAquaticEntityTypes.BETTA.get(),
            )
            .addOptional(ResourceLocation("fintastic", "featherback"))
            .addOptional(ResourceLocation("fintastic", "catfish"))
            .addOptional(ResourceLocation("fintastic", "minnow"))
            .addOptional(ResourceLocation("fintastic", "pleco"))
            .addOptional(ResourceLocation("fintastic", "guppy"))
            .addOptional(ResourceLocation("alexsmobs", "catfish"))
            .addOptional(ResourceLocation("alexsmobs", "mudskipper"))
            .addOptional(ResourceLocation("wonderoussea", "tiger_scatfish"))
            .addOptional(ResourceLocation("wonderoussea", "tiger_scatfish_2"))
            .addOptional(ResourceLocation("wonderoussea", "threadfin_lookdown"))
            .addOptional(ResourceLocation("wonderoussea", "threadfin_lookdown_2"))
            .addOptional(ResourceLocation("wonderoussea", "teira_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "teira_spadefish_2"))
            .addOptional(ResourceLocation("wonderoussea", "atlantic_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "atlantic_spadefish_2"))
            .addOptional(ResourceLocation("wonderoussea", "golden_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "golden_spadefish_2"))
            .addOptional(ResourceLocation("wonderoussea", "pacific_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "pacific_spadefish_2"))
            .addOptional(ResourceLocation("wonderoussea", "pinnate_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "pinnate_spadefish_2"))
            .addOptional(ResourceLocation("wonderoussea", "orbicular_spadefish"))
            .addOptional(ResourceLocation("wonderoussea", "orbicular_spadefish_2"))
        //#endregion

        //#region Shark Tags
        getOrCreateTagBuilder(HybridAquaticEntityTags.ALL_SHARKS)
            .addTag(HybridAquaticEntityTags.SMALL_SHARK)
            .addTag(HybridAquaticEntityTags.MEDIUM_SHARK)
            .addTag(HybridAquaticEntityTags.LARGE_SHARK)

        getOrCreateTagBuilder(HybridAquaticEntityTags.SMALL_SHARK)
            .add(
                HybridAquaticEntityTypes.LANTERN_SHARK.get(),
                HybridAquaticEntityTypes.HOUND_SHARK.get(),
            )
            .addOptional(ResourceLocation("rainbowreef", "small_shark"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.MEDIUM_SHARK)
            .add(
                HybridAquaticEntityTypes.FRILLED_SHARK.get(),
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK.get(),
                HybridAquaticEntityTypes.THRESHER_SHARK.get(),
                HybridAquaticEntityTypes.SIXGILL_SHARK.get(),
                HybridAquaticEntityTypes.SLEEPER_SHARK.get(),
            )
            .addOptional(ResourceLocation("alexsmobs", "frilled_shark"))
            .addOptional(ResourceLocation("alexsmobs", "hammerhead_shark"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.LARGE_SHARK)
            .add(
                HybridAquaticEntityTypes.BASKING_SHARK.get(),
                HybridAquaticEntityTypes.BULL_SHARK.get(),
                HybridAquaticEntityTypes.GREAT_WHITE_SHARK.get(),
                HybridAquaticEntityTypes.SAND_TIGER_SHARK.get(),
                HybridAquaticEntityTypes.WHALE_SHARK.get(),
            )
        //#endregion

        //#region Crustacean Tags
        getOrCreateTagBuilder(HybridAquaticEntityTags.ALL_CRUSTACEANS)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.LOBSTER)
            .addTag(HybridAquaticEntityTags.SHRIMP)
            .addOptional(ResourceLocation("alexsmobs", "triops"))
            .addOptional(ResourceLocation("fintastic", "daphnia"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.CRAB)
            .add(
                HybridAquaticEntityTypes.COCONUT_CRAB.get(),
                HybridAquaticEntityTypes.DECORATOR_CRAB.get(),
                HybridAquaticEntityTypes.DUNGENESS_CRAB.get(),
                HybridAquaticEntityTypes.FIDDLER_CRAB.get(),
                HybridAquaticEntityTypes.FLOWER_CRAB.get(),
                HybridAquaticEntityTypes.GHOST_CRAB.get(),
                HybridAquaticEntityTypes.HERMIT_CRAB.get(),
                HybridAquaticEntityTypes.HORSESHOE_CRAB.get(),
                HybridAquaticEntityTypes.LIGHTFOOT_CRAB.get(),
                HybridAquaticEntityTypes.SPIDER_CRAB.get(),
                HybridAquaticEntityTypes.VAMPIRE_CRAB.get(),
                HybridAquaticEntityTypes.YETI_CRAB.get(),
            )
            .addOptional(ResourceLocation("rainbowreef", "crab"))
            .addOptional(ResourceLocation("rainbowreef", "arrow_crab"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.LOBSTER)
            .add(
                HybridAquaticEntityTypes.CRAYFISH.get(),
                HybridAquaticEntityTypes.LOBSTER.get(),
            )
            .addOptional(ResourceLocation("alexsmobs", "lobster"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.SHRIMP)
            .add(
                HybridAquaticEntityTypes.SHRIMP.get(),
            )
            .addOptional(ResourceLocation("fintastic", "fairy_shrimp"))
            .addOptional(ResourceLocation("alexsmobs", "mantis_shrimp"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.KRILL)
            .addOptional(ResourceLocation("bountiful_critters", "krill"))
            .addOptional(ResourceLocation("wonderoussea", "antarctic_krill"))
        //#endregion

        //#region Cephalopod Tags
        getOrCreateTagBuilder(HybridAquaticEntityTags.ALL_CEPHALOPODS)
            .addTag(HybridAquaticEntityTags.OCTOPUS)
            .addTag(HybridAquaticEntityTags.SQUID)
            .add(
                HybridAquaticEntityTypes.VAMPIRE_SQUID.get(),
                HybridAquaticEntityTypes.NAUTILUS.get(),
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.OCTOPUS)
            .add(
                HybridAquaticEntityTypes.OCTOPUS.get(),
                HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(),
                HybridAquaticEntityTypes.VAMPIRE_SQUID.get(),
            )
            .addOptional(ResourceLocation("spawn", "octopus"))
            .addOptional(ResourceLocation("alexsmobs", "mimic_octopus"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.SQUID)
            .add(
                HybridAquaticEntityTypes.ARROW_SQUID.get(),
                HybridAquaticEntityTypes.CUTTLEFISH.get(),
                HybridAquaticEntityTypes.FIREFLY_SQUID.get(),
                HybridAquaticEntityTypes.COLOSSAL_SQUID.get(),
                HybridAquaticEntityTypes.GIANT_SQUID.get(),
                EntityType.SQUID,
                EntityType.GLOW_SQUID
            )
            .addOptional(ResourceLocation("alexsmobs", "giant_squid"))
        //#endregion

        //#region Mammal Tags
        getOrCreateTagBuilder(HybridAquaticEntityTags.ALL_MAMMALS)
            .addTag(HybridAquaticEntityTags.SIRENIAN)
            .addTag(HybridAquaticEntityTags.SEAL)
            .addTag(HybridAquaticEntityTags.DOLPHIN)
            .addTag(HybridAquaticEntityTags.WHALE)
            .add(
                HybridAquaticEntityTypes.OTTER.get(),
            )
            .addOptional(ResourceLocation("wonderoussea", "sea_otter"))
            .addOptional(ResourceLocation("wonderoussea", "sea_otter_swimming"))
            .addOptional(ResourceLocation("wonderoussea", "sea_otter_tagged"))
            .addOptional(ResourceLocation("wonderoussea", "sea_otter_tagged_swimming"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.SIRENIAN)
            .add(
                HybridAquaticEntityTypes.DUGONG.get(),
                HybridAquaticEntityTypes.MANATEE.get(),
            )
            .addOptional(ResourceLocation("spawn", "sea_cow"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.SEAL)
            .addOptional(ResourceLocation("alexsmobs", "seal"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.DOLPHIN)
            .add(
                EntityType.DOLPHIN,
                HybridAquaticEntityTypes.ORCA.get(),
            )
            .addOptional(ResourceLocation("alexsmobs", "orca"))
            .addOptional(ResourceLocation("wonderoussea", "bottlenose_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_bottlenose_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "burmeister_porpoise"))
            .addOptional(ResourceLocation("wonderoussea", "baby_burmeister_porpoise"))
            .addOptional(ResourceLocation("wonderoussea", "chilean_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_chilean_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "clymene_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_clymene_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "commersons_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_commersons_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "common_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_common_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "dalls_porpoise"))
            .addOptional(ResourceLocation("wonderoussea", "baby_dalls_porpoise"))
            .addOptional(ResourceLocation("wonderoussea", "dusky_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_dusky_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "finless_porpoise"))
            .addOptional(ResourceLocation("wonderoussea", "baby_finless_porpoise"))
            .addOptional(ResourceLocation("wonderoussea", "harbor_porpoise"))
            .addOptional(ResourceLocation("wonderoussea", "baby_harbor_porpoise"))
            .addOptional(ResourceLocation("wonderoussea", "heavisides_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_heavisides_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "hectors_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_hectors_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "hourglass_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_hourglass_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "narwhal"))
            .addOptional(ResourceLocation("wonderoussea", "baby_narwhal"))
            .addOptional(ResourceLocation("wonderoussea", "pacific_white_sided_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_pacific_white_sided_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "peales_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_peales_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "rissos_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_rissos_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "southern_right_whale_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_southern_right_whale_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "spectacled_porpoise"))
            .addOptional(ResourceLocation("wonderoussea", "baby_spectacled_porpoise"))
            .addOptional(ResourceLocation("wonderoussea", "spinner_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_spinner_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "spotted_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_spotted_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_spotted_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "striped_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_striped_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "tucuxi"))
            .addOptional(ResourceLocation("wonderoussea", "baby_tucuxi"))
            .addOptional(ResourceLocation("wonderoussea", "vaquita"))
            .addOptional(ResourceLocation("wonderoussea", "baby_vaquita"))
            .addOptional(ResourceLocation("wonderoussea", "white_beaked_dolphin"))
            .addOptional(ResourceLocation("wonderoussea", "baby_white_beaked_dolphin"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.WHALE)
            .addOptional(ResourceLocation("alexsmobs", "cachalot_whale"))
            .addOptional(ResourceLocation("bountiful", "humpback_whale"))
            .addOptional(ResourceLocation("wonderoussea", "beluga_whale"))
            .addOptional(ResourceLocation("wonderoussea", "baby_beluga_whale"))
            .addOptional(ResourceLocation("wonderoussea", "bottlenose_whale"))
            .addOptional(ResourceLocation("wonderoussea", "baby_bottlenose_whale"))
            .addOptional(ResourceLocation("wonderoussea", "minke_whale"))
            .addOptional(ResourceLocation("wonderoussea", "baby_minke_whale"))
            .addOptional(ResourceLocation("wonderoussea", "humpback_whale"))
            .addOptional(ResourceLocation("wonderoussea", "baby_humpback_whale"))
            .addOptional(ResourceLocation("wonderoussea", "gray_whale"))
            .addOptional(ResourceLocation("wonderoussea", "baby_gray_whale"))
            .addOptional(ResourceLocation("wonderoussea", "right_whale"))
            .addOptional(ResourceLocation("wonderoussea", "baby_right_whale"))
            .addOptional(ResourceLocation("wonderoussea", "bowhead_whale"))
            .addOptional(ResourceLocation("wonderoussea", "baby_bowhead_whale"))
            .addOptional(ResourceLocation("wonderoussea", "fin_whale"))
            .addOptional(ResourceLocation("wonderoussea", "baby_fin_whale"))
            .addOptional(ResourceLocation("wonderoussea", "blue_whale"))
            .addOptional(ResourceLocation("wonderoussea", "baby_blue_whale"))
            .addOptional(ResourceLocation("wonderoussea", "ancient_mother"))
        //#endregion

        //#region Misc Creature Tags
        getOrCreateTagBuilder(HybridAquaticEntityTags.ALL_CRITTERS)
            .add(
                HybridAquaticEntityTypes.SEA_SLUG.get(),
                HybridAquaticEntityTypes.SEA_CUCUMBER.get(),
                HybridAquaticEntityTypes.SEA_URCHIN.get(),
                HybridAquaticEntityTypes.STARFISH.get(),
                HybridAquaticEntityTypes.SEA_ANGEL.get(),
            )
            .addOptional(ResourceLocation("spawn", "clam"))
            .addOptional(ResourceLocation("wonderoussea", "marine_flatworm"))
            .addOptional(ResourceLocation("wonderoussea", "blue_glaucus"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.ALL_JELLYFISH)
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
            .addOptional(ResourceLocation("alexmobs", "comb_jelly"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.TURTLE)
            .add(
                EntityType.TURTLE,
            )
            .addOptional(ResourceLocation("alexsmobs", "terrapin"))
            .addOptional(ResourceLocation("alexsmobs", "alligator_snapping_turtle"))

        getOrCreateTagBuilder(HybridAquaticEntityTags.ALL_AMPHIBIANS)
            .add(
                EntityType.AXOLOTL,
                EntityType.FROG,
            )
        //#endregion

        //#region Fishing Net Implementation
        getOrCreateTagBuilder(HybridAquaticEntityTags.CAN_USE_FISHING_NET_ON)
            .addTag(HybridAquaticEntityTags.ALL_FISH)
            .addTag(HybridAquaticEntityTags.ALL_SHARKS)
            .addTag(HybridAquaticEntityTags.ALL_CRUSTACEANS)
            .addTag(HybridAquaticEntityTags.ALL_CEPHALOPODS)
            .addTag(HybridAquaticEntityTags.ALL_MAMMALS)
            .addTag(HybridAquaticEntityTags.ALL_AMPHIBIANS)
            .addTag(HybridAquaticEntityTags.ALL_JELLYFISH)
            .addTag(HybridAquaticEntityTags.ALL_CRITTERS)
            .addTag(HybridAquaticEntityTags.TURTLE)
        //#endregion
    }
}
