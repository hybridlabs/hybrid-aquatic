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
        // can breathe
        getOrCreateTagBuilder(EntityTypeTags.CAN_BREATHE_UNDER_WATER)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.FISHES)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .addTag(HybridAquaticEntityTags.CRITTER)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)
            .add(HybridAquaticEntityTypes.KARKINOS)

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
                HybridAquaticEntityTypes.UNICORN_FISH,
                HybridAquaticEntityTypes.ZEBRA_DANIO,
                HybridAquaticEntityTypes.SEAHORSE,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.RATFISH,
                HybridAquaticEntityTypes.BLUE_TANG,
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
                HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY,
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
                HybridAquaticEntityTypes.YELLOWFIN_TUNA,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.MAHIMAHI,
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
                EntityType.COW,
                EntityType.PIG,
                EntityType.SHEEP,
            )

        // prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.NONE)

        getOrCreateTagBuilder(HybridAquaticEntityTags.BULL_SHARK_PREY)
            .add(
                HybridAquaticEntityTypes.MAHIMAHI,
                HybridAquaticEntityTypes.YELLOWFIN_TUNA,
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
                HybridAquaticEntityTypes.MAHIMAHI,
                HybridAquaticEntityTypes.YELLOWFIN_TUNA,
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
                HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY,
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
                HybridAquaticEntityTypes.MAHIMAHI,
                HybridAquaticEntityTypes.YELLOWFIN_TUNA,
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

        getOrCreateTagBuilder(HybridAquaticEntityTags.BLUE_TANG_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.MORAY_EEL,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.UNICORN_FISH_PREDATOR)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.MORAY_EEL,
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
                HybridAquaticEntityTypes.BLUE_TANG,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.UNICORN_FISH,
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
                HybridAquaticEntityTypes.YELLOWFIN_TUNA,
                HybridAquaticEntityTypes.MAHIMAHI,
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
                HybridAquaticEntityTypes.BLUE_TANG,
                HybridAquaticEntityTypes.UNICORN_FISH,
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
                HybridAquaticEntityTypes.ZEBRA_DANIO,
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
                HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY,
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
            )

        // fishes
        getOrCreateTagBuilder(HybridAquaticEntityTags.FISHES)
            .add(
                HybridAquaticEntityTypes.ANGLERFISH,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.BETTA,
                HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY,
                HybridAquaticEntityTypes.BLUE_TANG,
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.COWFISH,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.DISCUS,
                HybridAquaticEntityTypes.DRAGONFISH,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
                HybridAquaticEntityTypes.GOURAMI,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.MAHIMAHI,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.NAUTILUS,
                HybridAquaticEntityTypes.NEEDLEFISH,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.OSCAR,
                HybridAquaticEntityTypes.PIRANHA,
                HybridAquaticEntityTypes.RATFISH,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.SEA_ANGEL,
                HybridAquaticEntityTypes.SEAHORSE,
                HybridAquaticEntityTypes.STONEFISH,
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.TOADFISH,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.UMBRELLA_OCTOPUS,
                HybridAquaticEntityTypes.UNICORN_FISH,
                HybridAquaticEntityTypes.VAMPIRE_SQUID,
                HybridAquaticEntityTypes.YELLOWFIN_TUNA,
                HybridAquaticEntityTypes.ZEBRA_DANIO,
            )

        // sharks
        getOrCreateTagBuilder(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.BULL_SHARK,
                HybridAquaticEntityTypes.BASKING_SHARK,
                HybridAquaticEntityTypes.THRESHER_SHARK,
                HybridAquaticEntityTypes.FRILLED_SHARK,
                HybridAquaticEntityTypes.GREAT_WHITE_SHARK,
                HybridAquaticEntityTypes.TIGER_SHARK,
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
                HybridAquaticEntityTypes.WHALE_SHARK,
            )

        // critters
        getOrCreateTagBuilder(HybridAquaticEntityTags.CRAB)
            .add(
                HybridAquaticEntityTypes.DUNGENESS_CRAB,
                HybridAquaticEntityTypes.FIDDLER_CRAB,
                HybridAquaticEntityTypes.HERMIT_CRAB,
                HybridAquaticEntityTypes.GHOST_CRAB,
                HybridAquaticEntityTypes.LIGHTFOOT_CRAB,
                HybridAquaticEntityTypes.FLOWER_CRAB,
                HybridAquaticEntityTypes.SPIDER_CRAB,
                HybridAquaticEntityTypes.HORSESHOE_CRAB,
                HybridAquaticEntityTypes.GIANT_ISOPOD,
                HybridAquaticEntityTypes.YETI_CRAB,
            )
        getOrCreateTagBuilder(HybridAquaticEntityTags.SHRIMP)
            .add(
                HybridAquaticEntityTypes.SHRIMP,
                HybridAquaticEntityTypes.CRAYFISH,
                HybridAquaticEntityTypes.LOBSTER,
        )

        getOrCreateTagBuilder(HybridAquaticEntityTags.CRITTER)
            .add(
                HybridAquaticEntityTypes.NUDIBRANCH,
                HybridAquaticEntityTypes.SEA_URCHIN,
                HybridAquaticEntityTypes.SEA_CUCUMBER,
                HybridAquaticEntityTypes.STARFISH,
            )

        // jellyfish
        getOrCreateTagBuilder(HybridAquaticEntityTags.JELLYFISH)
            .add(
                HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH,
                HybridAquaticEntityTypes.NOMURA_JELLYFISH,
                HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH,
                HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH,
                HybridAquaticEntityTypes.MOON_JELLYFISH,
                HybridAquaticEntityTypes.MAUVE_STINGER,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.ATOLLA_JELLYFISH,
                HybridAquaticEntityTypes.STARFISH,
                HybridAquaticEntityTypes.COMPASS_JELLYFISH,
                HybridAquaticEntityTypes.BLUE_JELLYFISH,
            )
    }
}
