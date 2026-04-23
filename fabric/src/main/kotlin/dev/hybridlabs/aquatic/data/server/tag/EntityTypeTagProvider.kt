package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.tag.HAEntityTags
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
                HAEntityTypes.TETRA.get(),
                HAEntityTypes.DANIO.get(),
                HAEntityTypes.TIGER_BARB.get(),
                HAEntityTypes.BETTA.get(),
                HAEntityTypes.OSCAR.get(),
                HAEntityTypes.DISCUS.get(),
                HAEntityTypes.CORYDORA.get(),
            )
        //#endregion

        //#region Food Chain Tags
        getOrCreateTagBuilder(HAEntityTags.BAIT_FISH)
            .add(
                HAEntityTypes.MACKEREL.get(),
                HAEntityTypes.HERRING.get(),
                HAEntityTypes.FLYING_FISH.get(),
                HAEntityTypes.SQUIRRELFISH.get(),
                HAEntityTypes.FLASHLIGHT_FISH.get(),
                EntityType.COD
            )
            .addOptional(ResourceLocation("fintastic", "minnow"))
            .addOptional(ResourceLocation("spawn", "herring"))
            .addOptional(ResourceLocation("alexsmobs", "flying_fish"))

        getOrCreateTagBuilder(HAEntityTags.TOXIC_ANIMALS)
            .add(
                HAEntityTypes.STONEFISH.get(),
                HAEntityTypes.LIONFISH.get(),
                HAEntityTypes.BLOWFISH.get(),
                EntityType.PUFFERFISH
            )

        getOrCreateTagBuilder(HAEntityTags.SMALL_CREATURES)
            .add(
                HAEntityTypes.CLOWNFISH.get(),
                HAEntityTypes.GARDEN_EEL.get(),
                HAEntityTypes.PLECO.get(),
                HAEntityTypes.SHINER.get(),
                HAEntityTypes.SUNFISH.get(),
                HAEntityTypes.CARP.get(),
                HAEntityTypes.PEARLFISH.get(),
                HAEntityTypes.SNAILFISH.get(),
                HAEntityTypes.BOXFISH.get(),
                HAEntityTypes.OSCAR.get(),
                HAEntityTypes.FLASHLIGHT_FISH.get(),
                HAEntityTypes.MACKEREL.get(),
                HAEntityTypes.HERRING.get(),
                HAEntityTypes.BARRELEYE.get(),
                HAEntityTypes.BETTA.get(),
                HAEntityTypes.TETRA.get(),
                HAEntityTypes.DANIO.get(),
                HAEntityTypes.TIGER_BARB.get(),
                HAEntityTypes.SURGEONFISH.get(),
                HAEntityTypes.DISCUS.get(),
                HAEntityTypes.CORYDORA.get(),
                HAEntityTypes.DAMSELFISH.get(),
                HAEntityTypes.GOURAMI.get(),
                HAEntityTypes.CUTTLEFISH.get(),
                HAEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
                HAEntityTypes.FLYING_FISH.get(),
                HAEntityTypes.SQUIRRELFISH.get(),
                HAEntityTypes.STONEFISH.get(),
                HAEntityTypes.PUPFISH.get(),
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
            .addOptional(ResourceLocation("babyfat", "ranchu"))
            .addOptional(ResourceLocation("bettas", "betta_fish"))
            .addOptional(ResourceLocation("finsandtails", "golden_river_ray"))
            .addOptional(ResourceLocation("finsandtails", "high_finned_blue"))
            .addOptional(ResourceLocation("finsandtails", "ornate_bugfish"))
            .addOptional(ResourceLocation("finsandtails", "teal_arrowfish"))
            .addOptional(ResourceLocation("finsandtails", "wee"))
            .addOptional(ResourceLocation("finsandtails", "pea_wee"))
            .addOptional(ResourceLocation("finsandtails", "blu_wee"))
            .addOptional(ResourceLocation("finsandtails", "vibra_wee"))
            .addOptional(ResourceLocation("finsandtails", "papa_wee"))

        getOrCreateTagBuilder(HAEntityTags.MEDIUM_CREATURES)
            .add(
                HAEntityTypes.RATFISH.get(),
                HAEntityTypes.TRIPOD_FISH.get(),
                HAEntityTypes.STINGRAY.get(),
                HAEntityTypes.TRIGGERFISH.get(),
                HAEntityTypes.TREVALLY.get(),
                HAEntityTypes.NEEDLEFISH.get(),
                HAEntityTypes.TROUT.get(),
                HAEntityTypes.ROCKFISH.get(),
                HAEntityTypes.SEA_BASS.get(),
                HAEntityTypes.LIONFISH.get(),
                HAEntityTypes.PARROTFISH.get(),
                HAEntityTypes.WRASSE.get(),
                HAEntityTypes.MORAY_EEL.get(),
                HAEntityTypes.JOHN_DORY.get(),
                HAEntityTypes.LANTERN_SHARK.get(),
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

        getOrCreateTagBuilder(HAEntityTags.LARGE_CREATURES)
            .add(
                HAEntityTypes.OCEAN_SUNFISH.get(),
                HAEntityTypes.OARFISH.get(),
                HAEntityTypes.OPAH.get(),
                HAEntityTypes.TUNA.get(),
                HAEntityTypes.MAHI.get(),
                HAEntityTypes.BARRACUDA.get(),
                HAEntityTypes.COELACANTH.get(),
                HAEntityTypes.SLICKHEAD.get(),
                HAEntityTypes.GOLDEN_DORADO.get(),
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

        getOrCreateTagBuilder(HAEntityTags.OTTER_PREY)
            .add(HAEntityTypes.SEA_URCHIN.get())
            .forceAddTag(HAEntityTags.ALL_CRUSTACEANS)
            .forceAddTag(HAEntityTags.SMALL_CREATURES)
        //#endregion

        //#region Fish Tags
        getOrCreateTagBuilder(HAEntityTags.ALL_FISH)
            .addTag(HAEntityTags.RAY)
            .addTag(HAEntityTags.REEF_FISH)
            .addTag(HAEntityTags.DEEP_FISH)
            .addTag(HAEntityTags.RIVER_FISH)
            .addTag(HAEntityTags.TROPICAL_RIVER_FISH)
            .addTag(HAEntityTags.SWAMP_FISH)
            .addTag(HAEntityTags.MANGROVE_FISH)
            .addOptional(ResourceLocation("bountiful_critters", "flounder"))
            .addOptional(ResourceLocation("spawn", "herring"))
            .addOptional(ResourceLocation("alexsmobs", "devils_hole_pupfish"))
            .addOptional(ResourceLocation("bountiful_critters", "sunfish"))
            .addOptional(ResourceLocation("spawn", "sunfish"))
            .addOptional(ResourceLocation("spawn", "tuna"))
            .addOptional(ResourceLocation("spawn", "barracuda"))
            .addOptional(ResourceLocation("finsandtails", "high_finned_blue"))
            .addOptional(ResourceLocation("finsandtails", "teal_arrowfish"))
            .addOptional(ResourceLocation("finsandtails", "swamp_mucker"))

        getOrCreateTagBuilder(HAEntityTags.REEF_FISH)
            .add(
                HAEntityTypes.BLOWFISH.get(),
                HAEntityTypes.BOXFISH.get(),
                HAEntityTypes.CLOWNFISH.get(),
                HAEntityTypes.GARDEN_EEL.get(),
                HAEntityTypes.DAMSELFISH.get(),
                HAEntityTypes.FLYING_FISH.get(),
                HAEntityTypes.LIONFISH.get(),
                HAEntityTypes.MORAY_EEL.get(),
                HAEntityTypes.NEEDLEFISH.get(),
                HAEntityTypes.PARROTFISH.get(),
                HAEntityTypes.SEAHORSE.get(),
                HAEntityTypes.SQUIRRELFISH.get(),
                HAEntityTypes.STONEFISH.get(),
                HAEntityTypes.SURGEONFISH.get(),
                HAEntityTypes.TRIGGERFISH.get(),
                HAEntityTypes.TREVALLY.get(),
                HAEntityTypes.WRASSE.get(),
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
            .addOptional(ResourceLocation("finsandtails", "ornate_bugfish"))

        getOrCreateTagBuilder(HAEntityTags.RAY)
            .add(
                HAEntityTypes.STINGRAY.get(),
                HAEntityTypes.MANTA_RAY.get(),
            )
            .addOptional(ResourceLocation("bountiful_critters", "stingray"))
            .addOptional(ResourceLocation("rainbowreef", "ray"))
            .addOptional(ResourceLocation("finsandtails", "golden_river_ray"))

        getOrCreateTagBuilder(HAEntityTags.DEEP_FISH)
            .add(
                HAEntityTypes.ANGLERFISH.get(),
                HAEntityTypes.BARRELEYE.get(),
                HAEntityTypes.COELACANTH.get(),
                HAEntityTypes.SLICKHEAD.get(),
                HAEntityTypes.DRAGONFISH.get(),
                HAEntityTypes.FANGTOOTH.get(),
                HAEntityTypes.FLASHLIGHT_FISH.get(),
                HAEntityTypes.HATCHETFISH.get(),
                HAEntityTypes.HAGFISH.get(),
                HAEntityTypes.TRIPOD_FISH.get(),
                HAEntityTypes.JOHN_DORY.get(),
                HAEntityTypes.OARFISH.get(),
                HAEntityTypes.RATFISH.get(),
                HAEntityTypes.SEA_ANGEL.get(),
                HAEntityTypes.SNAILFISH.get(),
                HAEntityTypes.VIPERFISH.get(),
            )
            .addOptional(ResourceLocation("spawn", "angler_fish"))
            .addOptional(ResourceLocation("fintastic", "coelacanth"))
            .addOptional(ResourceLocation("alexsmobs", "blobfish"))
            .addOptional(ResourceLocation("alexscaves", "tripodfish"))
            .addOptional(ResourceLocation("alexscaves", "lanternfish"))
            .addOptional(ResourceLocation("alexscaves", "gossamer_worm"))
            .addOptional(ResourceLocation("bountiful_critters", "barreleye"))

        getOrCreateTagBuilder(HAEntityTags.RIVER_FISH)
            .add(
                HAEntityTypes.CARP.get(),
                HAEntityTypes.SHINER.get(),
                HAEntityTypes.SUNFISH.get(),
                HAEntityTypes.TROUT.get(),
                EntityType.COD,
                EntityType.SALMON
            )
            .addOptional(ResourceLocation("fintastic", "catfish"))
            .addOptional(ResourceLocation("fintastic", "minnow"))
            .addOptional(ResourceLocation("fintastic", "freshwater_shark"))
            .addOptional(ResourceLocation("alexsmobs", "catfish"))
            .addOptional(ResourceLocation("finsandtails", "golden_river_ray"))
            .addOptional(ResourceLocation("finsandtails", "wee_wee"))
            .addOptional(ResourceLocation("finsandtails", "blu_wee"))
            .addOptional(ResourceLocation("finsandtails", "wee"))
            .addOptional(ResourceLocation("finsandtails", "papa_wee"))
            .addOptional(ResourceLocation("finsandtails", "flatback_sucker"))

        getOrCreateTagBuilder(HAEntityTags.TROPICAL_RIVER_FISH)
            .add(
                HAEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
                HAEntityTypes.DANIO.get(),
                HAEntityTypes.DISCUS.get(),
                HAEntityTypes.CORYDORA.get(),
                HAEntityTypes.GOLDEN_DORADO.get(),
                HAEntityTypes.GOURAMI.get(),
                HAEntityTypes.OSCAR.get(),
                HAEntityTypes.PIRANHA.get(),
                HAEntityTypes.PLECO.get(),
                HAEntityTypes.TETRA.get(),
                HAEntityTypes.TIGER_BARB.get(),
            )
            .addOptional(ResourceLocation("fintastic", "catfish"))
            .addOptional(ResourceLocation("fintastic", "minnow"))
            .addOptional(ResourceLocation("fintastic", "freshwater_shark"))
            .addOptional(ResourceLocation("fintastic", "arapaima"))
            .addOptional(ResourceLocation("fintastic", "pleco"))
            .addOptional(ResourceLocation("fintastic", "guppy"))
            .addOptional(ResourceLocation("bountiful_critters", "neon_tetra"))
            .addOptional(ResourceLocation("finsandtails", "pea_wee"))
            .addOptional(ResourceLocation("finsandtails", "vibra_wee"))
            .addOptional(ResourceLocation("finsandtails", "flatback_sucker"))

        getOrCreateTagBuilder(HAEntityTags.SWAMP_FISH)
            .add(
                HAEntityTypes.TIGER_BARB.get(),
                HAEntityTypes.PLECO.get(),
                HAEntityTypes.BETTA.get(),
            )
            .addOptional(ResourceLocation("fintastic", "featherback"))
            .addOptional(ResourceLocation("fintastic", "catfish"))
            .addOptional(ResourceLocation("fintastic", "minnow"))
            .addOptional(ResourceLocation("fintastic", "pleco"))
            .addOptional(ResourceLocation("fintastic", "guppy"))
            .addOptional(ResourceLocation("alexsmobs", "catfish"))
            .addOptional(ResourceLocation("finsandtails", "swamp_mucker"))
            .addOptional(ResourceLocation("finsandtails", "flatback_sucker"))

        getOrCreateTagBuilder(HAEntityTags.MANGROVE_FISH)
            .add(
                HAEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
                HAEntityTypes.TETRA.get(),
                HAEntityTypes.TIGER_BARB.get(),
                HAEntityTypes.GOURAMI.get(),
                HAEntityTypes.PLECO.get(),
                HAEntityTypes.DISCUS.get(),
                HAEntityTypes.OSCAR.get(),
                HAEntityTypes.DANIO.get(),
                HAEntityTypes.BETTA.get(),
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
        getOrCreateTagBuilder(HAEntityTags.ALL_SHARKS)
            .addTag(HAEntityTags.SMALL_SHARK)
            .addTag(HAEntityTags.MEDIUM_SHARK)
            .addTag(HAEntityTags.LARGE_SHARK)

        getOrCreateTagBuilder(HAEntityTags.SMALL_SHARK)
            .add(
                HAEntityTypes.LANTERN_SHARK.get(),
                HAEntityTypes.HOUND_SHARK.get(),
            )
            .addOptional(ResourceLocation("rainbowreef", "small_shark"))

        getOrCreateTagBuilder(HAEntityTags.MEDIUM_SHARK)
            .add(
                HAEntityTypes.FRILLED_SHARK.get(),
                HAEntityTypes.HAMMERHEAD_SHARK.get(),
                HAEntityTypes.THRESHER_SHARK.get(),
                HAEntityTypes.SIXGILL_SHARK.get(),
                HAEntityTypes.SLEEPER_SHARK.get(),
            )
            .addOptional(ResourceLocation("alexsmobs", "frilled_shark"))
            .addOptional(ResourceLocation("alexsmobs", "hammerhead_shark"))

        getOrCreateTagBuilder(HAEntityTags.LARGE_SHARK)
            .add(
                HAEntityTypes.BASKING_SHARK.get(),
                HAEntityTypes.BULL_SHARK.get(),
                HAEntityTypes.GREAT_WHITE_SHARK.get(),
                HAEntityTypes.SAND_TIGER_SHARK.get(),
                HAEntityTypes.WHALE_SHARK.get(),
            )
        //#endregion

        //#region Crustacean Tags
        getOrCreateTagBuilder(HAEntityTags.ALL_CRUSTACEANS)
            .addTag(HAEntityTags.CRAB)
            .addTag(HAEntityTags.LOBSTER)
            .addTag(HAEntityTags.SHRIMP)
            .addOptional(ResourceLocation("alexsmobs", "triops"))
            .addOptional(ResourceLocation("fintastic", "daphnia"))

        getOrCreateTagBuilder(HAEntityTags.CRAB)
            .add(
                HAEntityTypes.COCONUT_CRAB.get(),
                HAEntityTypes.DECORATOR_CRAB.get(),
                HAEntityTypes.DUNGENESS_CRAB.get(),
                HAEntityTypes.FIDDLER_CRAB.get(),
                HAEntityTypes.FLOWER_CRAB.get(),
                HAEntityTypes.GHOST_CRAB.get(),
                HAEntityTypes.HERMIT_CRAB.get(),
                HAEntityTypes.HORSESHOE_CRAB.get(),
                HAEntityTypes.LIGHTFOOT_CRAB.get(),
                HAEntityTypes.SPIDER_CRAB.get(),
                HAEntityTypes.VAMPIRE_CRAB.get(),
                HAEntityTypes.YETI_CRAB.get(),
            )
            .addOptional(ResourceLocation("rainbowreef", "crab"))
            .addOptional(ResourceLocation("rainbowreef", "arrow_crab"))
            .addOptional(ResourceLocation("finsandtails", "spindly_gem_crab"))
            .addOptional(ResourceLocation("finsandtails", "white_bull_crab"))
            .addOptional(ResourceLocation("finsandtails", "red_bull_crab"))

        getOrCreateTagBuilder(HAEntityTags.LOBSTER)
            .add(
                HAEntityTypes.CRAYFISH.get(),
                HAEntityTypes.LOBSTER.get(),
            )
            .addOptional(ResourceLocation("alexsmobs", "lobster"))

        getOrCreateTagBuilder(HAEntityTags.SHRIMP)
            .add(
                HAEntityTypes.SHRIMP.get(),
            )
            .addOptional(ResourceLocation("fintastic", "fairy_shrimp"))
            .addOptional(ResourceLocation("alexsmobs", "mantis_shrimp"))
            .addOptional(ResourceLocation("finsandtails", "banded_redback_shrimp"))

        getOrCreateTagBuilder(HAEntityTags.KRILL)
            .addOptional(ResourceLocation("bountiful_critters", "krill"))
            .addOptional(ResourceLocation("wonderoussea", "antarctic_krill"))
        //#endregion

        //#region Cephalopod Tags
        getOrCreateTagBuilder(HAEntityTags.ALL_CEPHALOPODS)
            .addTag(HAEntityTags.OCTOPUS)
            .addTag(HAEntityTags.SQUID)
            .add(
                HAEntityTypes.VAMPIRE_SQUID.get(),
                HAEntityTypes.NAUTILUS.get(),
            )

        getOrCreateTagBuilder(HAEntityTags.OCTOPUS)
            .add(
                HAEntityTypes.OCTOPUS.get(),
                HAEntityTypes.UMBRELLA_OCTOPUS.get(),
                HAEntityTypes.VAMPIRE_SQUID.get(),
            )
            .addOptional(ResourceLocation("spawn", "octopus"))
            .addOptional(ResourceLocation("alexsmobs", "mimic_octopus"))

        getOrCreateTagBuilder(HAEntityTags.SQUID)
            .add(
                HAEntityTypes.ARROW_SQUID.get(),
                HAEntityTypes.CUTTLEFISH.get(),
                HAEntityTypes.FIREFLY_SQUID.get(),
                HAEntityTypes.COLOSSAL_SQUID.get(),
                HAEntityTypes.GIANT_SQUID.get(),
                EntityType.SQUID,
                EntityType.GLOW_SQUID
            )
            .addOptional(ResourceLocation("alexsmobs", "giant_squid"))
            .addOptional(ResourceLocation("finsandtails", "night_light_squid"))
        //#endregion

        //#region Mammal Tags
        getOrCreateTagBuilder(HAEntityTags.ALL_MAMMALS)
            .addTag(HAEntityTags.SIRENIAN)
            .addTag(HAEntityTags.SEAL)
            .addTag(HAEntityTags.DOLPHIN)
            .addTag(HAEntityTags.WHALE)
            .add(
                HAEntityTypes.OTTER.get(),
            )
            .addOptional(ResourceLocation("wonderoussea", "sea_otter"))
            .addOptional(ResourceLocation("wonderoussea", "sea_otter_swimming"))
            .addOptional(ResourceLocation("wonderoussea", "sea_otter_tagged"))
            .addOptional(ResourceLocation("wonderoussea", "sea_otter_tagged_swimming"))

        getOrCreateTagBuilder(HAEntityTags.SIRENIAN)
            .add(
                HAEntityTypes.DUGONG.get(),
                HAEntityTypes.MANATEE.get(),
            )
            .addOptional(ResourceLocation("spawn", "sea_cow"))

        getOrCreateTagBuilder(HAEntityTags.SEAL)
            .addOptional(ResourceLocation("alexsmobs", "seal"))

        getOrCreateTagBuilder(HAEntityTags.DOLPHIN)
            .add(
                EntityType.DOLPHIN,
                HAEntityTypes.ORCA.get(),
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

        getOrCreateTagBuilder(HAEntityTags.WHALE)
            .addOptional(ResourceLocation("alexsmobs", "cachalot_whale"))
            .addOptional(ResourceLocation("bountiful_critters", "humpback_whale"))
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
        getOrCreateTagBuilder(HAEntityTags.ALL_CRITTERS)
            .add(
                HAEntityTypes.SEA_SLUG.get(),
                HAEntityTypes.SEA_CUCUMBER.get(),
                HAEntityTypes.SEA_URCHIN.get(),
                HAEntityTypes.STARFISH.get(),
                HAEntityTypes.SCALYFOOT_SNAIL.get(),
                HAEntityTypes.SEA_ANGEL.get(),
            )
            .addOptional(ResourceLocation("spawn", "clam"))
            .addOptional(ResourceLocation("wonderoussea", "marine_flatworm"))
            .addOptional(ResourceLocation("wonderoussea", "blue_glaucus"))
            .addOptional(ResourceLocation("finsandtails", "river_pebble_snail"))
            .addOptional(ResourceLocation("finsandtails", "phantom_nudibranch"))

        getOrCreateTagBuilder(HAEntityTags.ALL_JELLYFISH)
            .add(
                HAEntityTypes.CROWN_JELLYFISH.get(),
                HAEntityTypes.BARREL_JELLYFISH.get(),
                HAEntityTypes.BLUE_JELLYFISH.get(),
                HAEntityTypes.BIG_RED_JELLYFISH.get(),
                HAEntityTypes.CEPHEIDAE_JELLYFISH.get(),
                HAEntityTypes.COSMIC_JELLYFISH.get(),
                HAEntityTypes.COMB_JELLY.get(),
                HAEntityTypes.FIREWORK_JELLYFISH.get(),
                HAEntityTypes.LIONS_MANE_JELLYFISH.get(),
                HAEntityTypes.MAUVE_STINGER.get(),
                HAEntityTypes.MOON_JELLYFISH.get(),
                HAEntityTypes.NOMURA_JELLYFISH.get(),
                HAEntityTypes.SEA_NETTLE.get(),
                HAEntityTypes.BOX_JELLYFISH.get(),
            )
            .addOptional(ResourceLocation("rainbowreef", "jellyfish"))
            .addOptional(ResourceLocation("alexmobs", "comb_jelly"))

        getOrCreateTagBuilder(HAEntityTags.TURTLE)
            .add(
                EntityType.TURTLE,
            )
            .addOptional(ResourceLocation("alexsmobs", "terrapin"))
            .addOptional(ResourceLocation("alexsmobs", "alligator_snapping_turtle"))

        getOrCreateTagBuilder(HAEntityTags.ALL_AMPHIBIANS)
            .add(
                EntityType.AXOLOTL,
                EntityType.FROG,
            )
        //#endregion

        //#region Fishing Net Implementation
        getOrCreateTagBuilder(HAEntityTags.CAN_USE_FISHING_NET_ON)
            .addTag(HAEntityTags.ALL_FISH)
            .addTag(HAEntityTags.ALL_SHARKS)
            .addTag(HAEntityTags.ALL_CRUSTACEANS)
            .addTag(HAEntityTags.ALL_CEPHALOPODS)
            .addTag(HAEntityTags.ALL_MAMMALS)
            .addTag(HAEntityTags.ALL_AMPHIBIANS)
            .addTag(HAEntityTags.ALL_JELLYFISH)
            .addTag(HAEntityTags.ALL_CRITTERS)
            .addTag(HAEntityTags.TURTLE)
        //#endregion
    }
}
