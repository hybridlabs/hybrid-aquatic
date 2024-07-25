package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.EntityTypeTags
import java.util.concurrent.CompletableFuture

class EntityTypeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.EntityTypeTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // prey source
        getOrCreateTagBuilder(HybridAquaticEntityTags.SMALL_PREY)
            .add(
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.COWFISH,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.BETTA,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.PIRANHA,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.DANIO,
                HybridAquaticEntityTypes.SEAHORSE,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.RATFISH,
                HybridAquaticEntityTypes.SURGEONFISH,
                HybridAquaticEntityTypes.NAUTILUS,
                HybridAquaticEntityTypes.DISCUS,
                HybridAquaticEntityTypes.GOURAMI,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.DUNGENESS_CRAB,
                HybridAquaticEntityTypes.FIDDLER_CRAB,
                HybridAquaticEntityTypes.HERMIT_CRAB,
                HybridAquaticEntityTypes.GHOST_CRAB,
                HybridAquaticEntityTypes.VAMPIRE_CRAB,
                HybridAquaticEntityTypes.LIGHTFOOT_CRAB,
                HybridAquaticEntityTypes.SPIDER_CRAB,
                HybridAquaticEntityTypes.YETI_CRAB,
                HybridAquaticEntityTypes.HORSESHOE_CRAB,
                HybridAquaticEntityTypes.FLOWER_CRAB,
                HybridAquaticEntityTypes.GIANT_ISOPOD,
                HybridAquaticEntityTypes.SHRIMP,
                HybridAquaticEntityTypes.CRAYFISH,
                HybridAquaticEntityTypes.LOBSTER,
                HybridAquaticEntityTypes.STINGRAY,
                HybridAquaticEntityTypes.VAMPIRE_SQUID,
                HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
                HybridAquaticEntityTypes.DRAGONFISH,
                HybridAquaticEntityTypes.ANGLERFISH,
                HybridAquaticEntityTypes.UMBRELLA_OCTOPUS,
                HybridAquaticEntityTypes.STONEFISH,
                HybridAquaticEntityTypes.TOADFISH,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.SEA_ANGEL,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.MEDIUM_PREY)
            .add(
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.MAHI,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.NEEDLEFISH,
                EntityType.SQUID,
                EntityType.GLOW_SQUID
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.LARGE_PREY)
            .add(
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.OARFISH,
                EntityType.TURTLE,
            )

        // prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.NONE)

        getOrCreateTagBuilder(HybridAquaticEntityTags.BULL_SHARK_PREY)
            .add(
                HybridAquaticEntityTypes.MAHI,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
                HybridAquaticEntityTypes.THRESHER_SHARK,
                EntityType.TURTLE,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.FRILLED_SHARK_PREY)
            .add(
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                EntityType.SQUID,
                EntityType.GLOW_SQUID
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.GREAT_WHITE_SHARK_PREY)
            .add(
                HybridAquaticEntityTypes.MAHI,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
                HybridAquaticEntityTypes.THRESHER_SHARK,
                EntityType.TURTLE,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.HAMMERHEAD_SHARK_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)
            .add(
                HybridAquaticEntityTypes.STINGRAY,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.CUTTLEFISH,
                EntityType.TROPICAL_FISH,
                EntityType.COD,
                EntityType.SALMON
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.THRESHER_SHARK_PREY)
            .add(
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.RATFISH,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.NEEDLEFISH,
                HybridAquaticEntityTypes.TRIGGERFISH,
                EntityType.TROPICAL_FISH,
                EntityType.COD,
                EntityType.SALMON
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.TIGER_SHARK_PREY)
            .add(
                HybridAquaticEntityTypes.MAHI,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
                HybridAquaticEntityTypes.THRESHER_SHARK,
                EntityType.TURTLE,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.CLOWNFISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.LIONFISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.SURGEONFISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.LIONFISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.ROCKFISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.MORAY_EEL,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.NAUTILUS_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.TRIGGERFISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.STINGRAY_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)

        getOrCreateTagBuilder(HybridAquaticEntityTags.STINGRAY_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)

        getOrCreateTagBuilder(HybridAquaticEntityTags.LIONFISH_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)
            .add(
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.SURGEONFISH,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.LIONFISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.MORAY_EEL
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.OPAH_PREY)
            .add(
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                EntityType.SQUID,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.OPAH_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)

        getOrCreateTagBuilder(HybridAquaticEntityTags.OARFISH_PREY)
            .add(
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                EntityType.SQUID,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.OARFISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)

        getOrCreateTagBuilder(HybridAquaticEntityTags.CUTTLEFISH_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)

        getOrCreateTagBuilder(HybridAquaticEntityTags.CUTTLEFISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.MORAY_EEL
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.FIREFLY_SQUID_PREY)
            .addTag(HybridAquaticEntityTags.SHRIMP)

        getOrCreateTagBuilder(HybridAquaticEntityTags.FIREFLY_SQUID_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.MAHI,
                HybridAquaticEntityTypes.NEEDLEFISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.TRIGGERFISH_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)
            .add(
                HybridAquaticEntityTypes.SEA_URCHIN,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.TRIGGERFISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.MORAY_EEL,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.SUNFISH_PREY)
            .add(
                HybridAquaticEntityTypes.BARREL_JELLYFISH,
                HybridAquaticEntityTypes.BLUE_JELLYFISH,
                HybridAquaticEntityTypes.COMPASS_JELLYFISH,
                HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH,
                HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH,
                HybridAquaticEntityTypes.SEA_NETTLE,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.SUNFISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.SHARKS)

        getOrCreateTagBuilder(HybridAquaticEntityTags.MORAY_EEL_PREY)
            .add(
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.SURGEONFISH,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.MORAY_EEL_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)

        getOrCreateTagBuilder(HybridAquaticEntityTags.TUNA_PREY)
            .add(
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
                EntityType.SQUID,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.TUNA_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)

        getOrCreateTagBuilder(HybridAquaticEntityTags.ANGLERFISH_PREY)
            .add(
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.ANGLERFISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)

        getOrCreateTagBuilder(HybridAquaticEntityTags.DRAGONFISH_PREY)
            .add(
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.DRAGONFISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)

        getOrCreateTagBuilder(HybridAquaticEntityTags.OCTOPUS_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)

        getOrCreateTagBuilder(HybridAquaticEntityTags.OCTOPUS_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.MORAY_EEL,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.MAHI_PREY)
            .add(
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
                EntityType.SQUID,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.MAHI_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)

        getOrCreateTagBuilder(HybridAquaticEntityTags.NEEDLEFISH_PREY)
            .add(
                HybridAquaticEntityTypes.SHRIMP,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.NEEDLEFISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .add(
                HybridAquaticEntityTypes.MORAY_EEL,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.PIRANHA_PREY)
            .add(
                EntityType.FROG,
                EntityType.CHICKEN,
                EntityType.RABBIT,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.DANIO,
                HybridAquaticEntityTypes.TIGER_BARB,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.PIRANHA_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)

        getOrCreateTagBuilder(HybridAquaticEntityTags.CRUSTACEAN_PREDATOR)
            .add(
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.NEEDLEFISH,
                HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.STINGRAY,
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
            )

        // fishes
        getOrCreateTagBuilder(HybridAquaticEntityTags.FISHES)
            .add(
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.ANGLERFISH,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.PIRANHA,
                HybridAquaticEntityTypes.SEA_ANGEL,
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.VAMPIRE_SQUID,
                HybridAquaticEntityTypes.MAHI,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.NEEDLEFISH,
                HybridAquaticEntityTypes.RATFISH,
                HybridAquaticEntityTypes.NAUTILUS,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.OSCAR,
                HybridAquaticEntityTypes.DANIO,
                HybridAquaticEntityTypes.TOADFISH,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.STONEFISH,
                HybridAquaticEntityTypes.BETTA,
                HybridAquaticEntityTypes.SEAHORSE,
                HybridAquaticEntityTypes.MOON_JELLYFISH,
                HybridAquaticEntityTypes.GOURAMI,
                HybridAquaticEntityTypes.COWFISH,
                HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
                HybridAquaticEntityTypes.UMBRELLA_OCTOPUS,
                HybridAquaticEntityTypes.DISCUS,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.DRAGONFISH,
                HybridAquaticEntityTypes.STINGRAY,
                HybridAquaticEntityTypes.SURGEONFISH
            )

        // sharks
        getOrCreateTagBuilder(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.BASKING_SHARK,
                HybridAquaticEntityTypes.BULL_SHARK,
                HybridAquaticEntityTypes.FRILLED_SHARK,
                HybridAquaticEntityTypes.GREAT_WHITE_SHARK,
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
                HybridAquaticEntityTypes.THRESHER_SHARK,
                HybridAquaticEntityTypes.TIGER_SHARK,
                HybridAquaticEntityTypes.WHALE_SHARK,
            )

        // critters
        getOrCreateTagBuilder(HybridAquaticEntityTags.CRAB)
            .add(
                HybridAquaticEntityTypes.COCONUT_CRAB,
                HybridAquaticEntityTypes.DUNGENESS_CRAB,
                HybridAquaticEntityTypes.FIDDLER_CRAB,
                HybridAquaticEntityTypes.FLOWER_CRAB,
                HybridAquaticEntityTypes.GHOST_CRAB,
                HybridAquaticEntityTypes.HERMIT_CRAB,
                HybridAquaticEntityTypes.HORSESHOE_CRAB,
                HybridAquaticEntityTypes.LIGHTFOOT_CRAB,
                HybridAquaticEntityTypes.SPIDER_CRAB,
                HybridAquaticEntityTypes.VAMPIRE_CRAB,
                HybridAquaticEntityTypes.GIANT_ISOPOD,
                HybridAquaticEntityTypes.YETI_CRAB,
            )
        getOrCreateTagBuilder(HybridAquaticEntityTags.SHRIMP)
            .add(
                HybridAquaticEntityTypes.CRAYFISH,
                HybridAquaticEntityTypes.LOBSTER,
                HybridAquaticEntityTypes.SHRIMP,
        )

        getOrCreateTagBuilder(HybridAquaticEntityTags.CRITTER)
            .add(
                HybridAquaticEntityTypes.NUDIBRANCH,
                HybridAquaticEntityTypes.SEA_CUCUMBER,
                HybridAquaticEntityTypes.SEA_URCHIN,
                HybridAquaticEntityTypes.STARFISH,
            )

        // jellyfish
        getOrCreateTagBuilder(HybridAquaticEntityTags.JELLYFISH)
            .add(
                HybridAquaticEntityTypes.ATOLLA_JELLYFISH,
                HybridAquaticEntityTypes.BARREL_JELLYFISH,
                HybridAquaticEntityTypes.BLUE_JELLYFISH,
                HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH,
                HybridAquaticEntityTypes.COMPASS_JELLYFISH,
                HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH,
                HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH,
                HybridAquaticEntityTypes.MAUVE_STINGER,
                HybridAquaticEntityTypes.MOON_JELLYFISH,
                HybridAquaticEntityTypes.NOMURA_JELLYFISH,
                HybridAquaticEntityTypes.SEA_NETTLE,
            )

        getOrCreateTagBuilder(EntityTypeTags.CAN_BREATHE_UNDER_WATER)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.FISHES)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)
            .addTag(HybridAquaticEntityTags.CRITTER)
            .add(HybridAquaticEntityTypes.KARKINOS)
    }
}
