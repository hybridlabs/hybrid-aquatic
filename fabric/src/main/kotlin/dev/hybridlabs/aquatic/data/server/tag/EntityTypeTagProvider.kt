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
                HAEntityTypes.CICHLID.get(),
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
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "minnow"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "herring"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "flying_fish"))

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
                HAEntityTypes.GOLDFISH.get(),
                HAEntityTypes.PEARLFISH.get(),
                HAEntityTypes.SNAILFISH.get(),
                HAEntityTypes.BOXFISH.get(),
                HAEntityTypes.CICHLID.get(),
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
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "clownfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "basslet"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "boxfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "goby"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "tang"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "pipefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "seahorse"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "dwarf_angelfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "butterflyfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "moorish_idol"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "angelfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "flounder"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "neon_tetra"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "barreleye"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "gourami"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "guppy"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "minnow"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "moony"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "pleco"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "angler_fish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "seahorse"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "herring"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "mudskipper"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "devils_hole_pupfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "flying_fish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexscaves", "lanternfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("babyfat", "ranchu"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("bettas", "betta_fish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "golden_river_ray"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "high_finned_blue"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "ornate_bugfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "teal_arrowfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "wee"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "pea_wee"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "blu_wee"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "vibra_wee"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "papa_wee"))

        getOrCreateTagBuilder(HAEntityTags.MEDIUM_CREATURES)
            .add(
                HAEntityTypes.RATFISH.get(),
                HAEntityTypes.TRIPOD_FISH.get(),
                HAEntityTypes.BLOBFISH.get(),
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
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "angelfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "hogfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "parrotfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "ray"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "stingray"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "featherback"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "freshwater_shark"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "blobfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexscaves", "tripodfish"))

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
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "sunfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "arapaima"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "catfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "coelacanth"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "sunfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "tuna"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "barracuda"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "sea_cow"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "catfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "seal"))

        getOrCreateTagBuilder(HAEntityTags.OTTER_PREY)
            .add(HAEntityTypes.SEA_URCHIN.get())
            .forceAddTag(HAEntityTags.ALL_CRUSTACEANS)
            .forceAddTag(HAEntityTags.SMALL_CREATURES)
        //#endregion

        //#region Fish Tags
        getOrCreateTagBuilder(HAEntityTags.ALL_FISH)
            .addTag(HAEntityTags.RAY)
            .addTag(HAEntityTags.REEF_FISH)
            .addTag(HAEntityTags.OPEN_OCEAN_FISH)
            .addTag(HAEntityTags.DEEP_FISH)
            .addTag(HAEntityTags.RIVER_FISH)
            .addTag(HAEntityTags.TROPICAL_RIVER_FISH)
            .addTag(HAEntityTags.SWAMP_FISH)
            .addTag(HAEntityTags.MANGROVE_FISH)
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "flounder"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "herring"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "devils_hole_pupfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "sunfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "sunfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "tuna"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "barracuda"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "high_finned_blue"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "teal_arrowfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "swamp_mucker"))

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
                HAEntityTypes.SEADRAGON.get(),
                HAEntityTypes.SQUIRRELFISH.get(),
                HAEntityTypes.STONEFISH.get(),
                HAEntityTypes.SURGEONFISH.get(),
                HAEntityTypes.TRIGGERFISH.get(),
                HAEntityTypes.TREVALLY.get(),
                HAEntityTypes.WRASSE.get(),
                EntityType.TROPICAL_FISH,
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "flying_fish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "angelfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "seahorse"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "angelfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "basslet"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "boxfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "butterflyfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "clownfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "dwarf_angelfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "goby"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "hogfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "moorish_idol"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "parrotfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "pipefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "ray"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "seahorse"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "tang"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "tiger_scatfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "tiger_scatfish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "threadfin_lookdown"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "threadfin_lookdown_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "teira_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "teira_spadefish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "atlantic_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "atlantic_spadefish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "golden_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "golden_spadefish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "pacific_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "pacific_spadefish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "pinnate_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "pinnate_spadefish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "orbicular_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "orbicular_spadefish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "ornate_bugfish"))

        getOrCreateTagBuilder(HAEntityTags.RAY)
            .add(
                HAEntityTypes.STINGRAY.get(),
                HAEntityTypes.MANTA_RAY.get(),
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "stingray"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "ray"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "golden_river_ray"))

        getOrCreateTagBuilder(HAEntityTags.OPEN_OCEAN_FISH)
            .add(
                HAEntityTypes.TUNA.get(),
                HAEntityTypes.MAHI.get(),
                HAEntityTypes.OPAH.get(),
                HAEntityTypes.OCEAN_SUNFISH.get(),
                HAEntityTypes.BARRACUDA.get(),
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "sunfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "tuna"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "barracuda"))

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
                HAEntityTypes.BLOBFISH.get(),
                HAEntityTypes.HAGFISH.get(),
                HAEntityTypes.TRIPOD_FISH.get(),
                HAEntityTypes.JOHN_DORY.get(),
                HAEntityTypes.OARFISH.get(),
                HAEntityTypes.RATFISH.get(),
                HAEntityTypes.SEA_ANGEL.get(),
                HAEntityTypes.SNAILFISH.get(),
                HAEntityTypes.VIPERFISH.get(),
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "angler_fish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "coelacanth"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "blobfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexscaves", "tripodfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexscaves", "lanternfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexscaves", "gossamer_worm"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "barreleye"))

        getOrCreateTagBuilder(HAEntityTags.RIVER_FISH)
            .add(
                HAEntityTypes.CARP.get(),
                HAEntityTypes.GOLDFISH.get(),
                HAEntityTypes.SHINER.get(),
                HAEntityTypes.SUNFISH.get(),
                HAEntityTypes.TROUT.get(),
                EntityType.COD,
                EntityType.SALMON
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "catfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "minnow"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "freshwater_shark"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "catfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "golden_river_ray"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "wee_wee"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "blu_wee"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "wee"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "papa_wee"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "flatback_sucker"))

        getOrCreateTagBuilder(HAEntityTags.TROPICAL_RIVER_FISH)
            .add(
                HAEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
                HAEntityTypes.DANIO.get(),
                HAEntityTypes.DISCUS.get(),
                HAEntityTypes.CORYDORA.get(),
                HAEntityTypes.GOLDEN_DORADO.get(),
                HAEntityTypes.GOURAMI.get(),
                HAEntityTypes.CICHLID.get(),
                HAEntityTypes.PIRANHA.get(),
                HAEntityTypes.PLECO.get(),
                HAEntityTypes.TETRA.get(),
                HAEntityTypes.TIGER_BARB.get(),
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "catfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "minnow"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "freshwater_shark"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "arapaima"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "pleco"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "guppy"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "neon_tetra"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "pea_wee"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "vibra_wee"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "flatback_sucker"))

        getOrCreateTagBuilder(HAEntityTags.SWAMP_FISH)
            .add(
                HAEntityTypes.TIGER_BARB.get(),
                HAEntityTypes.PLECO.get(),
                HAEntityTypes.BETTA.get(),
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "featherback"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "catfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "minnow"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "pleco"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "guppy"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "catfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "swamp_mucker"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "flatback_sucker"))

        getOrCreateTagBuilder(HAEntityTags.MANGROVE_FISH)
            .add(
                HAEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
                HAEntityTypes.TETRA.get(),
                HAEntityTypes.TIGER_BARB.get(),
                HAEntityTypes.GOURAMI.get(),
                HAEntityTypes.PLECO.get(),
                HAEntityTypes.DISCUS.get(),
                HAEntityTypes.CICHLID.get(),
                HAEntityTypes.DANIO.get(),
                HAEntityTypes.BETTA.get(),
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "featherback"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "catfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "minnow"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "pleco"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "guppy"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "catfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "mudskipper"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "tiger_scatfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "tiger_scatfish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "threadfin_lookdown"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "threadfin_lookdown_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "teira_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "teira_spadefish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "atlantic_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "atlantic_spadefish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "golden_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "golden_spadefish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "pacific_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "pacific_spadefish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "pinnate_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "pinnate_spadefish_2"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "orbicular_spadefish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "orbicular_spadefish_2"))
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
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "small_shark"))

        getOrCreateTagBuilder(HAEntityTags.MEDIUM_SHARK)
            .add(
                HAEntityTypes.FRILLED_SHARK.get(),
                HAEntityTypes.HAMMERHEAD_SHARK.get(),
                HAEntityTypes.THRESHER_SHARK.get(),
                HAEntityTypes.SIXGILL_SHARK.get(),
                HAEntityTypes.SLEEPER_SHARK.get(),
                HAEntityTypes.GOBLIN_SHARK.get(),
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "frilled_shark"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "hammerhead_shark"))

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
            .add(HAEntityTypes.GIANT_ISOPOD.get(),)
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "triops"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "daphnia"))

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
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "crab"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "arrow_crab"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "spindly_gem_crab"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "white_bull_crab"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "red_bull_crab"))

        getOrCreateTagBuilder(HAEntityTags.LOBSTER)
            .add(
                HAEntityTypes.CRAYFISH.get(),
                HAEntityTypes.LOBSTER.get(),
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "lobster"))

        getOrCreateTagBuilder(HAEntityTags.SHRIMP)
            .add(
                HAEntityTypes.SHRIMP.get(),
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("fintastic", "fairy_shrimp"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "mantis_shrimp"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "banded_redback_shrimp"))

        getOrCreateTagBuilder(HAEntityTags.KRILL)
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "krill"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "antarctic_krill"))
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
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "octopus"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "mimic_octopus"))

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
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "giant_squid"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "night_light_squid"))
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
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "sea_otter"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "sea_otter_swimming"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "sea_otter_tagged"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "sea_otter_tagged_swimming"))

        getOrCreateTagBuilder(HAEntityTags.SIRENIAN)
            .add(
                HAEntityTypes.DUGONG.get(),
                HAEntityTypes.MANATEE.get(),
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "sea_cow"))

        getOrCreateTagBuilder(HAEntityTags.SEAL)
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "seal"))

        getOrCreateTagBuilder(HAEntityTags.DOLPHIN)
            .add(
                EntityType.DOLPHIN,
                HAEntityTypes.ORCA.get(),
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "orca"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "bottlenose_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_bottlenose_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "burmeister_porpoise"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_burmeister_porpoise"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "chilean_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_chilean_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "clymene_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_clymene_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "commersons_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_commersons_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "common_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_common_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "dalls_porpoise"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_dalls_porpoise"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "dusky_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_dusky_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "finless_porpoise"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_finless_porpoise"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "harbor_porpoise"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_harbor_porpoise"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "heavisides_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_heavisides_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "hectors_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_hectors_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "hourglass_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_hourglass_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "narwhal"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_narwhal"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "pacific_white_sided_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_pacific_white_sided_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "peales_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_peales_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "rissos_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_rissos_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "southern_right_whale_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_southern_right_whale_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "spectacled_porpoise"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_spectacled_porpoise"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "spinner_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_spinner_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "spotted_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_spotted_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_spotted_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "striped_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_striped_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "tucuxi"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_tucuxi"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "vaquita"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_vaquita"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "white_beaked_dolphin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_white_beaked_dolphin"))

        getOrCreateTagBuilder(HAEntityTags.WHALE)
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "cachalot_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("bountiful_critters", "humpback_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "beluga_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_beluga_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "bottlenose_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_bottlenose_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "minke_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_minke_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "humpback_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_humpback_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "gray_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_gray_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "right_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_right_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "bowhead_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_bowhead_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "fin_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_fin_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "blue_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "baby_blue_whale"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "ancient_mother"))
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
            .addOptional(ResourceLocation.fromNamespaceAndPath("spawn", "clam"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "marine_flatworm"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("wonderoussea", "blue_glaucus"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "river_pebble_snail"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("finsandtails", "phantom_nudibranch"))

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
            .addOptional(ResourceLocation.fromNamespaceAndPath("rainbowreef", "jellyfish"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexmobs", "comb_jelly"))

        getOrCreateTagBuilder(HAEntityTags.TURTLE)
            .add(
                EntityType.TURTLE,
            )
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "terrapin"))
            .addOptional(ResourceLocation.fromNamespaceAndPath("alexsmobs", "alligator_snapping_turtle"))

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
