package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.enchantment.HybridAquaticEnchantments
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.Registries

class LanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {
    override fun generateTranslations(builder: TranslationBuilder) {
        // item group
        builder.add(Registries.ITEM_GROUP.getKey(HybridAquaticItemGroups.ALL).orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic")

        // message in a bottle
        HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.translationKey.let { key ->
            builder.add(key, "Message in a Bottle")
            builder.add("$key.jar", "Message in a Jar")
            builder.add("$key.longneck", "Message in a Longneck Bottle")
        }

        // sea messages
        mapOf(
            "the_creepers_code" to "\"The creepers have a code...\"",
            "parrot_poison" to "\"Flint tried feeding parrots cookies earlier! They did not take. He coulda sworn they used to bite!\"\n*Steele",
            "lava_bathing" to "\"Steele went off and bathed in lava with my beautiful diamonds! That fiend! Least I've the time to myself to WRITE THIS COMPLAINT. STEELE.... STEALER! THIEF!\"\n@Flint",
            "fish_school" to "\"My underwater house flooded! Now it's just a school for fishies!\"\n~Oak",
            "loser" to "\"You opened the bottle!\nYou lose!\nVictim list - You!\"\n~Oak",
            "fart_bottle" to "\"Fart in a bottle! Gotcha!\nYou lose!\"\n@Flint",
            "tricked" to "\"I win! you fell for the classic ol' Message in a Bottle trick! I 'steele' the throne!\"\n*Steele",
            "marooned" to "\"marooned! beneath the night of a full moon (too)!\n(through the waves) which sound suggestions of lulling lays a gurgling\nit beckons me to take the plunge and swim (with him)\n(I) lay back down. tomorrow always comes.\n#Fischer #GlubGlub\"",
            "pumpkin_carving" to "\"Carve out a pumpkin and rely on your destiny!\"\n~Dean \"Ween\"",
        ).forEach { (id, translation) -> builder.add(SeaMessage(id).translationKey, translation) }

        // entities and spawn eggs
        mapOf(
            HybridAquaticEntityTypes.CLOWNFISH to "Clownfish",
            HybridAquaticEntityTypes.ANGLERFISH to "Anglerfish",
            HybridAquaticEntityTypes.DRAGONFISH to "Dragonfish",
            HybridAquaticEntityTypes.BARRELEYE to "Barreleye",
            HybridAquaticEntityTypes.YELLOWFIN_TUNA to "Yellowfin Tuna",
            HybridAquaticEntityTypes.CUTTLEFISH to "Cuttlefish",
            HybridAquaticEntityTypes.FLASHLIGHT_FISH to "Flashlight Fish",
            HybridAquaticEntityTypes.LIONFISH to "Lionfish",
            HybridAquaticEntityTypes.OARFISH to "Oarfish",
            HybridAquaticEntityTypes.OPAH to "Opah",
            HybridAquaticEntityTypes.PIRANHA to "Piranha",
            HybridAquaticEntityTypes.SEA_ANGEL to "Sea Angel",
            HybridAquaticEntityTypes.SUNFISH to "Sunfish",
            HybridAquaticEntityTypes.VAMPIRE_SQUID to "Vampire Squid",
            HybridAquaticEntityTypes.MAHIMAHI to "Mahimahi",
            HybridAquaticEntityTypes.MORAY_EEL to "Moray Eel",
            HybridAquaticEntityTypes.ROCKFISH to "Rockfish",
            HybridAquaticEntityTypes.TIGER_BARB to "Tiger Barb",
            HybridAquaticEntityTypes.NEEDLEFISH to "Needlefish",
            HybridAquaticEntityTypes.RATFISH to "Ratfish",
            HybridAquaticEntityTypes.NAUTILUS to "Nautilus",
            HybridAquaticEntityTypes.TRIGGERFISH to "Triggerfish",
            HybridAquaticEntityTypes.OSCAR to "Oscar",
            HybridAquaticEntityTypes.UNICORN_FISH to "Unicorn Fish",
            HybridAquaticEntityTypes.ZEBRA_DANIO to "Zebra Danio",
            HybridAquaticEntityTypes.TOADFISH to "Toadfish",
            HybridAquaticEntityTypes.TETRA to "Tetra",
            HybridAquaticEntityTypes.STONEFISH to "Stonefish",
            HybridAquaticEntityTypes.BETTA to "Betta",
            HybridAquaticEntityTypes.SEAHORSE to "Seahorse",
            HybridAquaticEntityTypes.MOON_JELLYFISH to "Moon Jellyfish",
            HybridAquaticEntityTypes.GOURAMI to "Gourami",
            HybridAquaticEntityTypes.COWFISH to "Cowfish",
            HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS to "Glowing Sucker Octopus",
            HybridAquaticEntityTypes.DISCUS to "Discus",
            HybridAquaticEntityTypes.FIREFLY_SQUID to "Firefly Squid",
            HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY to "Blue Spotted Stingray",
            HybridAquaticEntityTypes.BLUE_TANG to "Blue Tang",
            HybridAquaticEntityTypes.BULL_SHARK to "Bull Shark",
            HybridAquaticEntityTypes.BASKING_SHARK to "Basking Shark",
            HybridAquaticEntityTypes.THRESHER_SHARK to "Thresher Shark",
            HybridAquaticEntityTypes.FRILLED_SHARK to "Frilled Shark",
            HybridAquaticEntityTypes.GREAT_WHITE_SHARK to "Great White Shark",
            HybridAquaticEntityTypes.TIGER_SHARK to "Tiger Shark",
            HybridAquaticEntityTypes.HAMMERHEAD_SHARK to "Hammerhead Shark",
            HybridAquaticEntityTypes.WHALE_SHARK to "Whale Shark",
            HybridAquaticEntityTypes.CRAB to "Crab",
            HybridAquaticEntityTypes.FIDDLER_CRAB to "Fiddler Crab",
            HybridAquaticEntityTypes.HERMIT_CRAB to "Hermit Crab",
            HybridAquaticEntityTypes.NUDIBRANCH to "Nudibranch",
            HybridAquaticEntityTypes.SEA_CUCUMBER to "Sea Cucumber",
            HybridAquaticEntityTypes.SEA_URCHIN to "Sea Urchin",
            HybridAquaticEntityTypes.GIANT_CLAM to "Giant Clam",
            HybridAquaticEntityTypes.STARFISH to "Starfish",
            HybridAquaticEntityTypes.SEA_NETTLE to "Sea Nettle",
            HybridAquaticEntityTypes.FRIED_EGG_JELLY to "Fried Egg Jellyfish",
            HybridAquaticEntityTypes.CAULIFLOWER_JELLY to "Cauliflower Jellyfish",
            HybridAquaticEntityTypes.NOMURA_JELLY to "Nomura Jellyfish",
            HybridAquaticEntityTypes.BARREL_JELLY to "Barrel Jellyfish",
            HybridAquaticEntityTypes.COMPASS_JELLY to "Compass Jellyfish",
            HybridAquaticEntityTypes.MAUVE_STINGER to "Mauve Stinger",
            HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH to "Lions Mane Jellyfish",
        ).forEach { (entityType, translation) ->
            val id = Registries.ENTITY_TYPE.getId(entityType)
            val translationKey = entityType.translationKey
            val namespace = id.namespace
            val path = id.path
            builder.add(translationKey, translation)
            builder.add("item.$namespace.${path}_spawn_egg", "$translation Spawn Egg")
        }

        // blocks
        mapOf(
            HybridAquaticBlocks.BASKING_SHARK_PLUSHIE to "Basking Shark Plushie",
            HybridAquaticBlocks.BULL_SHARK_PLUSHIE to "Bull Shark Plushie",
            HybridAquaticBlocks.FRILLED_SHARK_PLUSHIE to "Frilled Shark Plushie",
            HybridAquaticBlocks.GREAT_WHITE_SHARK_PLUSHIE to "Great White Shark Plushie",
            HybridAquaticBlocks.HAMMERHEAD_SHARK_PLUSHIE to "Hammerhead Shark Plushie",
            HybridAquaticBlocks.THRESHER_SHARK_PLUSHIE to "Thresher Shark Plushie",
            HybridAquaticBlocks.TIGER_SHARK_PLUSHIE to "Tiger Shark Plushie",
            HybridAquaticBlocks.WHALE_SHARK_PLUSHIE to "Whale Shark Plushie",
            HybridAquaticBlocks.ANEMONE to "Anemone",
        ).forEach { (block, translation) ->
            builder.add(block, translation)
        }

        // items
        mapOf(
            HybridAquaticItems.RAW_FISH_MEAT to "Raw Fish Meat",
            HybridAquaticItems.COOKED_FISH_MEAT to "Cooked Fish Meat",
            HybridAquaticItems.RAW_TENTACLE to "Raw Tentacle",
            HybridAquaticItems.COOKED_TENTACLE to "Cooked Tentacle",
            HybridAquaticItems.RAW_CRAB_MEAT to "Raw Crab Meat",
            HybridAquaticItems.COOKED_CRAB_MEAT to "Cooked Crab Meat",
            HybridAquaticItems.LIONFISH to "Lionfish",
            HybridAquaticItems.MAHI_MAHI to "Mahi Mahi",
            HybridAquaticItems.YELLOWFIN_TUNA to "Yellowfin Tuna",
            HybridAquaticItems.OPAH to "Opah",
            HybridAquaticItems.ROCKFISH to "Rockfish",
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY to "Blue Spotted Stingray",
            HybridAquaticItems.MORAY_EEL to "Moray Eel",
            HybridAquaticItems.NEEDLEFISH to "Needlefish",
            HybridAquaticItems.PIRANHA to "Piranha",
            HybridAquaticItems.ANGLERFISH to "Anglerfish",
            HybridAquaticItems.BARRELEYE to "Barreleye",
            HybridAquaticItems.BLUE_TANG to "Blue Tang",
            HybridAquaticItems.CLOWNFISH to "Clownfish",
            HybridAquaticItems.UNICORN_FISH to "Unicorn Fish",
            HybridAquaticItems.CRAB_CLAW to "Crab Claw",
            HybridAquaticItems.GLOW_SLIME to "Glow Slime",
            HybridAquaticItems.SHARK_TOOTH to "Shark Tooth",
            HybridAquaticItems.PEARL to "Pearl",
            HybridAquaticItems.BLACK_PEARL to "Black Pearl",
        ).forEach { (item, translation) ->
            builder.add(item, translation)
        }

        // enchantments
        mapOf(
            HybridAquaticEnchantments.LIVECATCH to "Live Catch",
        ).forEach { (enchantment, translation) ->
            builder.add(enchantment, translation)
        }
    }
}
