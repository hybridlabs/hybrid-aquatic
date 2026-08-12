package dev.hybridlabs.aquatic.config

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.hapi.tag.HAPIBiomeTags
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome

/**
 * Applies biome modifications for entities when initialised.
 */
@Suppress("SameParameterValue")
class EntitySpawnConfigGenerator {
    private val list: MutableList<EntitySpawnConfig> = mutableListOf()

    fun finalizeSpawn() {
        //#region River Fish
        addRiverFish(
            HAEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
            listOf(
                HAPIBiomeTags.TROPICAL_RIVERS,
                HAPIBiomeTags.JUNGLE,
                HAPIBiomeTags.MARSHES,
                HAPIBiomeTags.MANGROVES),
            2, 1, 1
        )

        addRiverFish(
            HAEntityTypes.GOLDEN_DORADO.get(),
            listOf(
                HAPIBiomeTags.TROPICAL_RIVERS,
                HAPIBiomeTags.JUNGLE),
            1, 1, 1
        )

        addRiverFish(
            HAEntityTypes.TETRA.get(),
            listOf(
                HAPIBiomeTags.JUNGLE,
                HAPIBiomeTags.MARSHES,
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.TROPICAL_RIVERS,
                HAPIBiomeTags.CAVES),
            3, 2, 3
        )

        addRiverFish(
            HAEntityTypes.PUPFISH.get(),
            listOf(BiomeTags.IS_BADLANDS),
            1, 1, 1)

        addRiverFish(
            HAEntityTypes.TIGER_BARB.get(),
            listOf(
                HAPIBiomeTags.JUNGLE,
                HAPIBiomeTags.SWAMP,
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.MARSHES,
                HAPIBiomeTags.TROPICAL_RIVERS),
            3, 2, 3
        )

        addRiverFish(
            HAEntityTypes.GOURAMI.get(),
            listOf(
                HAPIBiomeTags.JUNGLE,
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.MARSHES,
                HAPIBiomeTags.TROPICAL_RIVERS),
            1, 1, 1
        )

        addRiverFish(
            HAEntityTypes.PLECO.get(),
            listOf(
                HAPIBiomeTags.SWAMP,
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.JUNGLE,
                HAPIBiomeTags.TROPICAL_RIVERS,
                HAPIBiomeTags.MARSHES),
            1, 1, 2
        )

        addRiverFish(
            HAEntityTypes.DISCUS.get(),
            listOf(
                HAPIBiomeTags.JUNGLE,
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.TROPICAL_RIVERS),
            1, 1, 1
        )

        addRiverFish(
            HAEntityTypes.CORYDORA.get(),
            listOf(
                HAPIBiomeTags.JUNGLE,
                HAPIBiomeTags.TROPICAL_RIVERS),
            2, 1, 3
        )

        addRiverFish(
            HAEntityTypes.PIRANHA.get(),
            listOf(
                HAPIBiomeTags.JUNGLE,
                HAPIBiomeTags.TROPICAL_RIVERS),
            2, 4, 8
        )

        addRiverFish(
            HAEntityTypes.CICHLID.get(),
            listOf(
                HAPIBiomeTags.JUNGLE,
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.TROPICAL_RIVERS),
            1, 1, 1
        )

        addRiverFish(
            HAEntityTypes.DANIO.get(),
            listOf(
                HAPIBiomeTags.JUNGLE,
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.MARSHES,
                HAPIBiomeTags.TROPICAL_RIVERS),
            3, 2, 3
        )

        addRiverFish(
            HAEntityTypes.BETTA.get(),
            listOf(
                HAPIBiomeTags.SWAMP,
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.MARSHES),
            1, 1, 1
        )

        addRiverFish(
            HAEntityTypes.CARP.get(),
            listOf(
                HAPIBiomeTags.RIVERS),
            3, 1, 2
        )

        addRiverFish(
            HAEntityTypes.GOLDFISH.get(),
            listOf(),
            3, 1, 1
        )

        addRiverFish(
            HAEntityTypes.SUNFISH.get(),
            listOf(
                HAPIBiomeTags.RIVERS),
            3, 1, 2
        )

        addRiverFish(
            HAEntityTypes.TROUT.get(),
            listOf(
                HAPIBiomeTags.COLD_RIVERS,
                BiomeTags.IS_RIVER),
            2, 1, 1
        )

        addRiverFish(
            HAEntityTypes.SHINER.get(),
            listOf(
                HAPIBiomeTags.COLD_RIVERS,
                BiomeTags.IS_RIVER),
            3, 1, 2
        )
        //#endregion

        //#region Marine Fish
        addFish(
            HAEntityTypes.MANTA_RAY.get(),
            listOf(
                HAPIBiomeTags.TEMPERATE_OCEANS,
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.WARM_OCEANS),
            1, 1, 3
        )

        addFish(
            HAEntityTypes.FLASHLIGHT_FISH.get(),
            listOf(
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.CORAL_REEF),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.SQUIRRELFISH.get(),
            listOf(
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.CORAL_REEF),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.FLYING_FISH.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.LUKEWARM_OCEANS,),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.DAMSELFISH.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.LUKEWARM_OCEANS),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.MACKEREL.get(),
            listOf(
                HAPIBiomeTags.COLD_OCEANS,
                HAPIBiomeTags.TEMPERATE_OCEANS),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.HERRING.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_COLD_OCEANS,
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.NEEDLEFISH.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.SEAGRASS_BED),
            4, 1, 4
        )

        addFish(
            HAEntityTypes.MAHI.get(),
            listOf(
                HAPIBiomeTags.DEEP_LUKEWARM_OCEANS,
                HAPIBiomeTags.DEEP_WARM_OCEANS),
            3, 1, 4
        )

        addFish(
            HAEntityTypes.TUNA.get(),
            listOf(
                HAPIBiomeTags.DEEP_TEMPERATE_OCEANS,
                HAPIBiomeTags.DEEP_LUKEWARM_OCEANS,
                HAPIBiomeTags.DEEP_WARM_OCEANS),
            3, 1, 4
        )

        addFish(
            HAEntityTypes.ROCKFISH.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.LUKEWARM_OCEANS),
            3, 1, 4
        )

        addFish(
            HAEntityTypes.SEA_BASS.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.LUKEWARM_OCEANS),
            3, 1, 3
        )

        addFish(
            HAEntityTypes.BARRACUDA.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.LUKEWARM_OCEANS),
            3, 1, 1
        )

        addFish(
            HAEntityTypes.GARDEN_EEL.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.SEAGRASS_BED),
            5, 3, 8
        )

        addFish(
            HAEntityTypes.OPAH.get(),
            listOf(
                HAPIBiomeTags.DEEP_TEMPERATE_OCEANS,
                HAPIBiomeTags.DEEP_LUKEWARM_OCEANS,
                HAPIBiomeTags.DEEP_WARM_OCEANS),
            2, 1, 2
        )

        addFish(
            HAEntityTypes.WRASSE.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.SEAHORSE.get(),
            listOf(
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.SEAGRASS_BED),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.SEADRAGON.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HAPIBiomeTags.DEEP_LUKEWARM_OCEANS,
                HAPIBiomeTags.SEAGRASS_BED),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.LIONFISH.get(),
            listOf(
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.CORAL_REEF),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.STONEFISH.get(),
            listOf(
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.SEAGRASS_BED),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.STINGRAY.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.SEAGRASS_BED),
            2, 1, 2
        )

        addFish(
            HAEntityTypes.BLOWFISH.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.SEAGRASS_BED),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.SURGEONFISH.get(),
            listOf(
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.CORAL_REEF),
            4, 1, 4
        )

        addFish(
            HAEntityTypes.CLOWNFISH.get(),
            listOf(
                HAPIBiomeTags.CORAL_REEF),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.BOXFISH.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.CORAL_REEF),
            2, 1, 1
        )

        addFish(
            HAEntityTypes.TRIGGERFISH.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.SEAGRASS_BED),
            3, 1, 1
        )

        addFish(
            HAEntityTypes.TREVALLY.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.SEAGRASS_BED),
            3, 1, 1
        )

        addFish(
            HAEntityTypes.PARROTFISH.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.CORAL_REEF),
            4, 1, 2
        )

        addFish(
            HAEntityTypes.MORAY_EEL.get(),
            listOf(
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.SEAGRASS_BED),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.OCEAN_SUNFISH.get(),
            listOf(
                HAPIBiomeTags.DEEP_TEMPERATE_OCEANS,
                HAPIBiomeTags.DEEP_LUKEWARM_OCEANS,
                HAPIBiomeTags.DEEP_WARM_OCEANS),
            1, 1, 2
        )

        addFish(HAEntityTypes.RATFISH.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.ALL_TRENCHES),
            5, 1, 3
        )

        addFish(
            HAEntityTypes.SNAILFISH.get(),
            listOf(
                HAPIBiomeTags.ALL_TRENCHES),
            5, 1, 3
        )

        addFish(
            HAEntityTypes.OARFISH.get(),
            listOf(
                HAPIBiomeTags.DEEP_LUKEWARM_OCEANS,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.DEEP_TEMPERATE_OCEANS,
                HAPIBiomeTags.DEEP_WARM_OCEANS),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.ANGLERFISH.get(),
            listOf(
                HAPIBiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.FANGTOOTH.get(),
            listOf(
                HAPIBiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.VIPERFISH.get(),
            listOf(
                HAPIBiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.HATCHETFISH.get(),
            listOf(
                HAPIBiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.BLOBFISH.get(),
            listOf(
                HAPIBiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.HAGFISH.get(),
            listOf(),
            2, 2, 6
        )

        addFish(
            HAEntityTypes.TRIPOD_FISH.get(),
            listOf(
                HAPIBiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.JOHN_DORY.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.DEEP_WARM_OCEANS,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.DEEP_LUKEWARM_OCEANS,
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.DEEP_TEMPERATE_OCEANS),
            4, 1, 2
        )

        addFish(
            HAEntityTypes.BARRELEYE.get(),
            listOf(
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.TEMPERATE_TRENCH),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.DRAGONFISH.get(),
            listOf(
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.TEMPERATE_TRENCH),
            2, 1, 2
        )

        addFish(
            HAEntityTypes.SEA_ANGEL.get(),
            listOf(
                HAPIBiomeTags.FROZEN_TRENCH,
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.TEMPERATE_TRENCH),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.COELACANTH.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.TEMPERATE_TRENCH),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.SLICKHEAD.get(),
            listOf(
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.TEMPERATE_TRENCH),
            1, 1, 1
        )
        //#endregion

        //#region Cephalopods
        addCephalopod(
            HAEntityTypes.ARROW_SQUID.get(),
            listOf(
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.TEMPERATE_OCEANS),
            11, 1, 2
        )

        addCephalopod(
            HAEntityTypes.GIANT_SQUID.get(),
            listOf(
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.TEMPERATE_TRENCH,),
            1, 1, 1
        )

        addCephalopod(
            HAEntityTypes.COLOSSAL_SQUID.get(),
            listOf(
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.FROZEN_TRENCH),
            1, 1, 1
        )

        addCephalopod(
            HAEntityTypes.FIREFLY_SQUID.get(),
            listOf(
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_OCEANS,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.CORAL_REEF),
            11, 1, 2
        )

        addCephalopod(
            HAEntityTypes.CUTTLEFISH.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS),
            11, 1, 1
        )

        addCephalopod(
            HAEntityTypes.OCTOPUS.get(),
            listOf(
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS
            ),
            11, 1, 1
        )
        //#endregion

        //#region Jellyfish
        addJelly(
            HAEntityTypes.BARREL_JELLYFISH.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.LUKEWARM_OCEANS),
            3, 1, 2
        )

        addJelly(
            HAEntityTypes.MOON_JELLYFISH.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.SANDY_BEACHES
            ),
            5, 2, 5
        )

        addJelly(
            HAEntityTypes.CEPHEIDAE_JELLYFISH.get(),
            listOf(
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS),
            3, 1, 2
        )

        addJelly(
            HAEntityTypes.BLUE_JELLYFISH.get(),
            listOf(
                HAPIBiomeTags.COLD_OCEANS,
                HAPIBiomeTags.TEMPERATE_OCEANS),
            3, 1, 2
        )

        addJelly(
            HAEntityTypes.SEA_NETTLE.get(),
            listOf(
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.TEMPERATE_OCEANS),
            3, 1, 4
        )

        addJelly(
            HAEntityTypes.NOMURA_JELLYFISH.get(),
            listOf(
                HAPIBiomeTags.FROZEN_OCEANS,
                HAPIBiomeTags.COLD_OCEANS),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.LIONS_MANE_JELLYFISH.get(),
            listOf(
                HAPIBiomeTags.FROZEN_OCEANS,
                HAPIBiomeTags.COLD_OCEANS),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.BOX_JELLYFISH.get(),
            listOf(
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.SANDY_BEACHES
            ),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.CROWN_JELLYFISH.get(),
            listOf(
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.FROZEN_TRENCH),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.BIG_RED_JELLYFISH.get(),
            listOf(
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.FROZEN_TRENCH),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.COSMIC_JELLYFISH.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.FROZEN_TRENCH),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.COMB_JELLY.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.ALL_TRENCHES,
                BiomeTags.IS_DEEP_OCEAN),
            2, 1, 2
        )

        addJelly(
            HAEntityTypes.FIREWORK_JELLYFISH.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.FROZEN_TRENCH),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.MAUVE_STINGER.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.FROZEN_TRENCH),
            2, 1, 3
        )
        //#endregion

        //#region Sharks
        addShark(
            HAEntityTypes.GREAT_WHITE_SHARK.get(),
            listOf(
                HAPIBiomeTags.DEEP_TEMPERATE_OCEANS,
                HAPIBiomeTags.DEEP_LUKEWARM_OCEANS,
                HAPIBiomeTags.DEEP_WARM_OCEANS),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.SAND_TIGER_SHARK.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS),
            2, 1, 1
        )

        addShark(
            HAEntityTypes.HAMMERHEAD_SHARK.get(),
            listOf(
                HAPIBiomeTags.TEMPERATE_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS),
            3, 1, 2
        )

        addShark(
            HAEntityTypes.HOUND_SHARK.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS),
            3, 1, 2
        )

        addShark(
            HAEntityTypes.THRESHER_SHARK.get(),
            listOf(
                HAPIBiomeTags.DEEP_TEMPERATE_OCEANS,
                HAPIBiomeTags.DEEP_LUKEWARM_OCEANS,
                HAPIBiomeTags.DEEP_WARM_OCEANS),
            2, 1, 1
        )

        addShark(
            HAEntityTypes.BULL_SHARK.get(),
            listOf(
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.WARM_OCEANS),
            2, 1, 2
        )

        addShark(
            HAEntityTypes.WHALE_SHARK.get(),
            listOf(
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.WARM_OCEANS),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.BASKING_SHARK.get(),
            listOf(
                HAPIBiomeTags.DEEP_TEMPERATE_OCEANS,
                HAPIBiomeTags.DEEP_COLD_OCEANS,
                HAPIBiomeTags.FROZEN_OCEANS
            ),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.FRILLED_SHARK.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.SIXGILL_SHARK.get(),
            listOf(
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.SLEEPER_SHARK.get(),
            listOf(
                HAPIBiomeTags.FROZEN_TRENCH,
                HAPIBiomeTags.COLD_TRENCH),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.GOBLIN_SHARK.get(),
            listOf(
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.WARM_TRENCH),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.LANTERN_SHARK.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.DEEP_TEMPERATE_OCEANS,
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.DEEP_LUKEWARM_OCEANS,
                HAPIBiomeTags.LUKEWARM_TRENCH,
                HAPIBiomeTags.DEEP_WARM_OCEANS),
            2, 1, 2
        )
        //#endregion

        //#region Mammal
        addMammal(
            HAEntityTypes.OTTER.get(),
            listOf(
                HAPIBiomeTags.RIVERS,
                HAPIBiomeTags.SANDY_BEACHES,
                HAPIBiomeTags.ROCKY_BEACHES
            ),
            1, 1, 2
        )

        addSirenian(
            HAEntityTypes.DUGONG.get(),
            listOf(
                HAPIBiomeTags.SEAGRASS_BED
            ),
            1, 1, 2
        )

        addSirenian(
            HAEntityTypes.MANATEE.get(),
            listOf(
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.TROPICAL_RIVERS
            ),
            1, 1, 2
        )

        addDolphin(
            HAEntityTypes.ORCA.get(),
            listOf(
                HAPIBiomeTags.FROZEN_OCEANS,
                HAPIBiomeTags.COLD_OCEANS,
                HAPIBiomeTags.TEMPERATE_OCEANS,
            ),
            1, 1, 3
        )
        //#endregion

        //#region Crustaceans
        addCrustacean(
            HAEntityTypes.DUNGENESS_CRAB.get(),
            listOf(
                HAPIBiomeTags.SANDY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.FIDDLER_CRAB.get(),
            listOf(
                HAPIBiomeTags.SWAMP,
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.MARSHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.GHOST_CRAB.get(),
            listOf(
                HAPIBiomeTags.SANDY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.HORSESHOE_CRAB.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HAPIBiomeTags.SANDY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.LIGHTFOOT_CRAB.get(),
            listOf(
                HAPIBiomeTags.ROCKY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.FLOWER_CRAB.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HAPIBiomeTags.MANGROVES,
                HAPIBiomeTags.MARSHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.VAMPIRE_CRAB.get(),
            listOf(
                HAPIBiomeTags.JUNGLE,
                HAPIBiomeTags.TROPICAL_RIVERS),
            1, 1, 2
        )

        addCrustacean(
            HAEntityTypes.SHRIMP.get(),
            listOf(
                HAPIBiomeTags.TROPICAL_RIVERS,
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.CORAL_REEF),
            3, 2, 3
        )

        addCrustacean(
            HAEntityTypes.LOBSTER.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS),
            2, 1, 2
        )
        addCrustacean(
            HAEntityTypes.DECORATOR_CRAB.get(),
            listOf(
                HAPIBiomeTags.CORAL_REEF,
                HAPIBiomeTags.RED_MEADOW),
            2, 1, 2
        )

        addCrustacean(
            HAEntityTypes.CRAYFISH.get(),
            listOf(
                HAPIBiomeTags.TROPICAL_RIVERS,
                HAPIBiomeTags.COLD_RIVERS,
                HAPIBiomeTags.RIVERS),
            2, 1, 2
        )

        addCrustacean(
            HAEntityTypes.COCONUT_CRAB.get(),
            listOf(
                HAPIBiomeTags.SANDY_BEACHES),
            2, 1, 2
        )

        addCrustacean(
            HAEntityTypes.HERMIT_CRAB.get(),
            listOf(
                HAPIBiomeTags.SANDY_BEACHES,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS),
            1, 1, 2
        )

        addCrustacean(
            HAEntityTypes.YETI_CRAB.get(),
            listOf(
                HAPIBiomeTags.HAS_THERMAL_VENTS,
                HAPIBiomeTags.FROZEN_TRENCH,
                HAPIBiomeTags.COLD_TRENCH),
            2, 1, 2
        )

        addCrustacean(
            HAEntityTypes.GIANT_ISOPOD.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.TEMPERATE_OCEANS,
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.COLD_OCEANS),
            1, 1, 2
        )

        addCrustacean(
            HAEntityTypes.SPIDER_CRAB.get(),
            listOf(
                HAPIBiomeTags.DEEP_REEF,
                HAPIBiomeTags.WARM_TRENCH,
                HAPIBiomeTags.TEMPERATE_OCEANS,
                HAPIBiomeTags.TEMPERATE_TRENCH,
                HAPIBiomeTags.COLD_TRENCH,
                HAPIBiomeTags.COLD_OCEANS),
            2, 1, 2
        )
        //#endregion

        //#region Critters
        addCritter(
            HAEntityTypes.SEA_SLUG.get(),
            listOf(
                HAPIBiomeTags.SHALLOW_COLD_OCEANS,
                HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HAPIBiomeTags.LUKEWARM_OCEANS,
                HAPIBiomeTags.SHALLOW_WARM_OCEANS,
                HAPIBiomeTags.SEAGRASS_BED,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.CORAL_REEF),
            1, 1, 2
        )

        addCritter(
            HAEntityTypes.SCALYFOOT_SNAIL.get(),
            listOf(
                HAPIBiomeTags.HAS_THERMAL_VENTS),
            1, 1, 2
        )

        addCritter(
            HAEntityTypes.STARFISH.get(),
            listOf(
                BiomeTags.IS_OCEAN,
                BiomeTags.IS_DEEP_OCEAN,
                HAPIBiomeTags.ALL_TRENCHES,
                HAPIBiomeTags.SANDY_BEACHES),
            2, 1, 2
        )

        addCritter(
            HAEntityTypes.SEA_CUCUMBER.get(),
            listOf(
                BiomeTags.IS_OCEAN,
                BiomeTags.IS_DEEP_OCEAN,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addCritter(
            HAEntityTypes.SEA_URCHIN.get(),
            listOf(
                BiomeTags.IS_OCEAN,
                BiomeTags.IS_DEEP_OCEAN,
                HAPIBiomeTags.RED_MEADOW,
                HAPIBiomeTags.ALL_TRENCHES),
            3, 1, 2
        )
        //#endregion
    }

    private fun addFish(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType,
            spawnTags,
            Services.PLATFORM.getHybridMobCategoryByName("fish"),
            weight,
            minGroup,
            maxGroup
        )
    }

    private fun addRiverFish(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType,
            spawnTags,
            Services.PLATFORM.getHybridMobCategoryByName("river_fish"),
            weight,
            minGroup,
            maxGroup
        )
    }

    private fun addCephalopod(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getHybridMobCategoryByName("cephalopod"),
            weight, minGroup, maxGroup
        )
    }

    private fun addShark(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getHybridMobCategoryByName("shark"),
            weight, minGroup, maxGroup
        )
    }

    private fun addMammal(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType,
            spawnTags,
            Services.PLATFORM.getHybridMobCategoryByName("mammal"),
            weight,
            minGroup,
            maxGroup
        )
    }

    private fun addSirenian(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType,
            spawnTags,
            Services.PLATFORM.getHybridMobCategoryByName("mammal"),
            weight,
            minGroup,
            maxGroup
        )
    }

    private fun addDolphin(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType,
            spawnTags,
            Services.PLATFORM.getHybridMobCategoryByName("mammal"),
            weight,
            minGroup,
            maxGroup
        )
    }

    private fun addJelly(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getHybridMobCategoryByName("jelly"),
            weight, minGroup, maxGroup
        )
    }

    private fun addCrustacean(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getHybridMobCategoryByName("crustacean"),
            weight, minGroup, maxGroup
        )
    }

    private fun addCritter(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getHybridMobCategoryByName("critter"),
            weight, minGroup, maxGroup
        )
    }

    private fun add(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        spawnGroup: MobCategory,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        spawnTags.forEach { spawnTag ->
            list.add(EntitySpawnConfig(entityType, spawnTag, spawnGroup, weight, minGroup, maxGroup))
        }
    }

    companion object {
        fun generate(): List<EntitySpawnConfig> {
            val generator = EntitySpawnConfigGenerator()
            generator.finalizeSpawn()
            return generator.list
        }
    }
}