package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.enchantment.HybridAquaticEnchantments
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity
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
            "poyo" to "\"I hate litterbugs.\"\n~Poyo",
            "rick_roll" to "\"Never gonna give you up!\nNever gonna let you down!\nNever gonna run around and desert you!\n-Rick Astley",
            "bold_muddy" to "\"AW MAN I DROWNED!\"\n~Bold Muddy",
            "adventure" to "\"To the brave explorer who discovers this bottle, know that adventure awaits around every corner!\"",
            "dream" to "\"Dear reader,\nyour presence is proof that hope can survive the waves.\nKeep dreaming.\"",
            "fortune" to "\"In this bottle lies a wish for fortune and prosperity to find its way to you.\"",
            "sunshine" to "\"In this bottle, I send you rays of sunshine and a gentle breeze to brighten your day.\"",
            "distant" to "\"Greetings from a distant land!\nI hope this message finds you well.\"",
            "depths" to "\"Within the depths of this world, secrets lie buried.\nUnearth them, and unlock the secrets of a forgotten civilization\"",
            "clownfish" to "\"Clownfish and sea anemones share a unique bond.\nThe clownfish finds protection within the anemone's tentacles, while providing it with food.\nWitness this symbiotic relationship up close.\"",
            "vampire_squid" to "\"The elusive vampire squid, despite its name, does not suck blood.\nIt actually uses bioluminescent displays and webbed arms to navigate and catch prey in the deep sea.\nMarvel at this mysterious creature as you explore the depths.\"",
            "turtle" to "\"Explore the depths and encounter the mesmerizing sea turtles!\nThese gentle creatures can navigate thousands of miles to return to their birthplace.\nWitness the miracle of their journey and marvel at their determination.\"",
            "anglerfish" to "\"The anglerfish lures its prey with a bioluminescent appendage, dangling in the dark depths.\nVenture into the unknown and learn about the fascinating adaptations of deep-sea creatures.\"",
            "corals" to "\"The vibrant colors of coral reefs are due to a symbiotic relationship between coral polyps and algae.\nDive into this underwater paradise and discover the intricate web of life thriving within.\"",
            "polar_bear" to "\"Journey to the Arctic and witness the resilience of polar bears!\nThese magnificent creatures are perfectly adapted to survive in one of the harshest environments on Earth.\nLearn about their unique adaptations and the challenges they face in a changing climate\"",
            "sea_cucumber" to "\"Sea cucumbers play a vital role in maintaining the health of the reef ecosystem.\nThese fascinating creatures help recycle nutrients and keep the reef clean by consuming decaying matter.\"",
            "seahorse" to "\"Did you know that seahorses are one of the few species where the males give birth to their young?\nWitness this unique phenomenon as they release their tiny offspring into the sea.\"",

            ).forEach { (id, translation) -> builder.add(SeaMessage(id).translationKey, translation) }

        // entities
        generateEntities(builder)

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
            HybridAquaticBlocks.TUBE_SPONGE to "Tube Sponge",
            HybridAquaticBlocks.CRATE to "Crate",
            HybridAquaticBlocks.BUOY to "Buoy",
            HybridAquaticBlocks.CRAB_POT to "Crab Pot",
            HybridAquaticBlocks.HYDROTHERMAL_VENT to "Hydrothermal Vent",
            HybridAquaticBlocks.GIANT_CLAM to "Giant Clam",
        ).forEach { (block, translation) ->
            builder.add(block, translation)
        }

        // items
        mapOf(
            HybridAquaticItems.FISH_MEAT to "Fish Meat",
            HybridAquaticItems.COOKED_FISH_MEAT to "Cooked Fish Meat",
            HybridAquaticItems.SMALL_FISH_MEAT to "Small Fish Meat",
            HybridAquaticItems.COOKED_SMALL_FISH_MEAT to "Cooked Small Fish Meat",
            HybridAquaticItems.TENTACLE to "Raw Tentacle",
            HybridAquaticItems.COOKED_TENTACLE to "Cooked Tentacle",
            HybridAquaticItems.CRAB_MEAT to "Raw Crab Meat",
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
            HybridAquaticItems.TIGER_BARB to "Tiger Barb",
            HybridAquaticItems.OSCAR to "Oscar",
            HybridAquaticItems.TRIGGERFISH to "Triggerfish",
            HybridAquaticItems.COWFISH to "Cowfish",
            HybridAquaticItems.CRAB_CLAW to "Crab Claw",
            HybridAquaticItems.GLOW_SLIME to "Glow Slime",
            HybridAquaticItems.SHARK_TOOTH to "Shark Tooth",
            HybridAquaticItems.SPONGE_CHUNK to "Sponge Chunk",
            HybridAquaticItems.PEARL to "Pearl",
            HybridAquaticItems.BLACK_PEARL to "Black Pearl",
            HybridAquaticItems.BARBED_HOOK to "Barbed Hook",
            HybridAquaticItems.GLOWING_HOOK to "Glowing Hook",
            HybridAquaticItems.MAGNETIC_HOOK to "Magnetic Hook",
            HybridAquaticItems.FISHING_NET to "Fishing Net",
        ).forEach { (item, translation) ->
            builder.add(item, translation)
        }

        // effects
        mapOf(
            HybridAquaticStatusEffects.BLEEDING to "Bleeding",
            HybridAquaticStatusEffects.THALASSOPHOBIA to "Thalassophobia"
        ).forEach { (effect, translation) ->
            val identifier = Registries.STATUS_EFFECT.getId(effect)
            builder.add("effect.${identifier?.namespace}.${identifier?.path}", translation)
        }

        // Item descriptions
        mapOf(
            "item.hybrid-aquatic.hook" to "Needs to be put in the offhand",
            HybridAquaticItems.BARBED_HOOK.translationKey to "Increases fishing speed during the day",
            HybridAquaticItems.GLOWING_HOOK.translationKey to "Increases fishing speed at night",
            HybridAquaticItems.MAGNETIC_HOOK.translationKey to "Increases treasure chance",
            HybridAquaticBlocks.CRATE.translationKey to "Break with an axe to open",
            HybridAquaticItems.FISHING_NET.translationKey to "Stored Entity: %s"
        ).forEach { (itemTranslationKey, translation) ->
            builder.add(itemTranslationKey.plus(".description"), translation)
        }

        // enchantments
        mapOf(
            HybridAquaticEnchantments.LIVECATCH to "Live Catch",
        ).forEach { (enchantment, translation) ->
            builder.add(enchantment, translation)
        }

        mapOf(
            "glowing" to "Glowing"
        ).forEach { (potion, translation) ->
            builder.add("item.minecraft.potion.effect.$potion", "Potion of $translation")
            builder.add("item.minecraft.splash_potion.effect.$potion", "Splash Potion of $translation")
            builder.add("item.minecraft.lingering_potion.effect.$potion", "Lingering Potion of $translation")
            builder.add("item.minecraft.tipped_arrow.effect.$potion", "Arrow of $translation")
        }
    }

    private fun generateEntities(builder: TranslationBuilder) {
        // create map of entities to their display names
        val entityNameMap = mapOf(
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
            HybridAquaticEntityTypes.GHOST_CRAB to "Ghost Crab",
            HybridAquaticEntityTypes.LIGHTFOOT_CRAB to "Lightfoot Crab",
            HybridAquaticEntityTypes.FLOWER_CRAB to "Flower Crab",
            HybridAquaticEntityTypes.VAMPIRE_CRAB to "Vampire Crab",
            HybridAquaticEntityTypes.SPIDER_CRAB to "Spider Crab",
            HybridAquaticEntityTypes.YETI_CRAB to "Yeti Crab",
            HybridAquaticEntityTypes.COCONUT_CRAB to "Coconut Crab",
            HybridAquaticEntityTypes.HORSESHOE_CRAB to "Horseshoe Crab",
            HybridAquaticEntityTypes.GIANT_ISOPOD to "Giant Isopod",
            HybridAquaticEntityTypes.SHRIMP to "Shrimp",
            HybridAquaticEntityTypes.MANTIS_SHRIMP to "Mantis Shrimp",
            HybridAquaticEntityTypes.CRAYFISH to "Crayfish",
            HybridAquaticEntityTypes.LOBSTER to "Lobster",
            HybridAquaticEntityTypes.NUDIBRANCH to "Nudibranch",
            HybridAquaticEntityTypes.SEA_CUCUMBER to "Sea Cucumber",
            HybridAquaticEntityTypes.SEA_URCHIN to "Sea Urchin",
            HybridAquaticEntityTypes.STARFISH to "Starfish",
            HybridAquaticEntityTypes.SEA_NETTLE to "Sea Nettle",
            HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH to "Fried Egg Jellyfish",
            HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH to "Cauliflower Jellyfish",
            HybridAquaticEntityTypes.NOMURA_JELLYFISH to "Nomura Jellyfish",
            HybridAquaticEntityTypes.BARREL_JELLYFISH to "Barrel Jellyfish",
            HybridAquaticEntityTypes.COMPASS_JELLYFISH to "Compass Jellyfish",
            HybridAquaticEntityTypes.MAUVE_STINGER to "Mauve Stinger",
            HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH to "Lion's Mane Jellyfish",
            HybridAquaticEntityTypes.ATOLLA_JELLYFISH to "Atolla Jellyfish",
            HybridAquaticEntityTypes.BLUE_JELLYFISH to "Blue Jellyfish",
            HybridAquaticEntityTypes.KARKINOS to "Karkinos",
        )

        // verify display name list is valid
        val nonPresentEntityNames = mutableListOf<EntityType<*>>()

        Registries.ENTITY_TYPE
            .filter(filterHybridAquatic(Registries.ENTITY_TYPE))
            .forEach { type ->
                if (type.baseClass.isAssignableFrom(MobEntity::class.java)) {
                    if (!entityNameMap.containsKey(type)) {
                        nonPresentEntityNames.add(type)
                    }
                }
            }

        if (nonPresentEntityNames.isNotEmpty()) {
            throw throw IllegalStateException("Entity to display name map does not contain ${nonPresentEntityNames.joinToString()}. Please modify ${javaClass.simpleName} accordingly.")
        }

        // generate entity and entity spawn egg translations
        entityNameMap.forEach { (entityType, translation) ->
            val id = Registries.ENTITY_TYPE.getId(entityType)
            val translationKey = entityType.translationKey
            val namespace = id.namespace
            val path = id.path
            builder.add(translationKey, translation)
            builder.add("item.$namespace.${path}_spawn_egg", "$translation Spawn Egg")
        }
    }
}
