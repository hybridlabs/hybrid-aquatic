package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.data.server.seamessage.SeaMessageProvider
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob

class LanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {
    override fun generateTranslations(builder: TranslationBuilder) {
        // item group
        builder.add(
            BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(HybridAquaticItemGroups.BLOCKS.get())
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Blocks"
        )

        builder.add(
            BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(HybridAquaticItemGroups.ITEMS.get())
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Items"
        )

        builder.add(
            BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(HybridAquaticItemGroups.SPAWN_EGGS.get())
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Spawn Eggs"
        )

        // message in a bottle
        HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get().descriptionId.let { key ->
            builder.add(key, "Message in a Bottle")
            builder.add("$key.jar", "Message in a Jar")
            builder.add("$key.longneck", "Message in a Longneck Bottle")
        }

        // sea messages
        SeaMessageProvider.BUILT_IN.forEach { message ->
            builder.add(message.translationKey, message.englishText)
            message.englishTitle?.let { title -> builder.add(message.titleTranslationKey, title) }
        }

        builder.add(HybridAquaticItems.SEA_MESSAGE_BOOK.get(), "Sea Message")

        //advancements
        mapOf(
            "advancements.hybrid-aquatic.enter_water.title" to "Hybrid Aquatic",
            "advancements.hybrid-aquatic.enter_water.description" to "Discover an expanded world beneath the waves",

            "advancements.hybrid-aquatic.fishing_net.title" to "Not Quite A Bucket",
            "advancements.hybrid-aquatic.fishing_net.description" to "Craft a fishing net to pick up and transport sea creatures",

            "advancements.hybrid-aquatic.glowstick.title" to "Better Than Torches!",
            "advancements.hybrid-aquatic.glowstick.description" to "Craft a glowstick to light your way in the deep sea",

            "advancements.hybrid-aquatic.buoy.title" to "Oh Buoy!",
            "advancements.hybrid-aquatic.buoy.description" to "Craft a buoy to guide sailors across the sea",

            "advancements.hybrid-aquatic.diving_suit.title" to "Diving In",
            "advancements.hybrid-aquatic.diving_suit.description" to "Obtain a full set of diving gear",

            "advancements.hybrid-aquatic.hook.title" to "Hooked!",
            "advancements.hybrid-aquatic.hook.description" to "Craft a hook to help you catch fish faster",

            "advancements.hybrid-aquatic.creeper_hook.title" to "An Explosive Catch",
            "advancements.hybrid-aquatic.creeper_hook.description" to "Also try The Creeper's Code!",

            "advancements.hybrid-aquatic.pearl.title" to "Pearly Whites",
            "advancements.hybrid-aquatic.pearl.description" to "Obtain a pearl from a giant clam",

            "advancements.hybrid-aquatic.black_pearl.title" to "The Black Pearl",
            "advancements.hybrid-aquatic.black_pearl.description" to "What the Black Pearl really is... is freedom",

            "advancements.hybrid-aquatic.crab_claw.title" to "Clawesome",
            "advancements.hybrid-aquatic.crab_claw.description" to "Obtain any crab claw",

            "advancements.hybrid-aquatic.ominous_hook.title" to "Hook, Line, and Pincher",
            "advancements.hybrid-aquatic.ominous_hook.description" to "Obtain an Ominous Hook",

            "advancements.hybrid-aquatic.kill_karkinos.title" to "A Herculean Task",
            "advancements.hybrid-aquatic.kill_karkinos.description" to "Defeat Karkinos",

            "advancements.hybrid-aquatic.boat.title" to "Set Sail",
            "advancements.hybrid-aquatic.boat.description" to "Craft a boat and explore the oceans of Minecraft",

            "advancements.hybrid-aquatic.bigger_boat.title" to "We're Gonna Need A Bigger Boat",
            "advancements.hybrid-aquatic.bigger_boat.description" to "Kill a shark",
        ).forEach { (key, translation) ->
            builder.add(key, translation)
        }

        mapOf(
            "profile.item.hybrid-aquatic.anglerfish" to "The Anglerfish, a deep sea predator known for its bioluminescent lure, which it uses to attract prey in the dark ocean depths.",
            "profile.item.hybrid-aquatic.barreleye" to "The Barreleye, a deep-sea fish known for its transparent head and tubular eyes, which allow it to look upward to detect prey and predators above.",
            "profile.item.hybrid-aquatic.boxfish" to "The Boxfish, a small, square-shaped fish with a rigid, box-like body, known for its ability to release toxins when stressed.",
            "profile.item.hybrid-aquatic.betta" to "The Betta, also known as the Siamese fighting fish, is a colorful, territorial freshwater fish known for its vibrant fins and aggressive behavior towards other males.",
            "profile.item.hybrid-aquatic.carp" to "The Carp, a hardy, freshwater fish widely distributed and valued for its adaptability, playing a significant role in aquaculture and recreational fishing.",
            "profile.item.hybrid-aquatic.danio" to "The Danio, a small, colorful freshwater fish known for its active swimming behavior and popularity in home aquariums.",
            "profile.item.hybrid-aquatic.discus" to "The Discus, a vibrant, round-shaped freshwater fish admired for its striking patterns and colors, often kept in aquariums for its beauty.",
            "profile.item.hybrid-aquatic.dragonfish" to "The Dragonfish, a deep-sea predator with sharp teeth and bioluminescent photophores, known for its ability to produce light to attract prey and communicate in the dark ocean depths.",
            "profile.item.hybrid-aquatic.golden_dorado" to "The Golden Dorado, a large, powerful freshwater fish native to South America, prized by anglers for its strength and golden scales.",
            "profile.item.hybrid-aquatic.goldfish" to "The Goldfish, a domesticated freshwater fish, well-known for its bright orange color and common presence in ponds and aquariums.",
            "profile.item.hybrid-aquatic.gourami" to "The Gourami, a diverse group of freshwater fish, recognized for their labyrinth organ allowing them to breathe air and their peaceful nature in community tanks.",
            "profile.item.hybrid-aquatic.mackerel" to "The Mackerel, a fast-swimming, pelagic fish with streamlined bodies, valued for its role in the food chain and commercial fishing.",
            "profile.item.hybrid-aquatic.moray_eel" to "The Moray Eel, a long, slender predator with a snake-like body and sharp teeth, known for hiding in crevices in reefs and ambushing prey.",
            "profile.item.hybrid-aquatic.oscar" to "The Oscar, a large, aggressive freshwater cichlid fish, popular in aquariums for its intelligence and striking patterns.",
            "profile.item.hybrid-aquatic.pearlfish" to "The Pearlfish, a slender fish that often lives symbiotically within the body cavities of sea cucumbers, known for its unusual habitat choice.",
            "profile.item.hybrid-aquatic.piranha" to "The Piranha, a notorious freshwater fish known for its sharp teeth and powerful bite, often exaggerated for its feeding frenzy behavior.",
            "profile.item.hybrid-aquatic.rockfish" to "The Rockfish, a long-lived marine fish with venomous spines, known for its camouflage abilities and preference for rocky sea floors.",
            "profile.item.hybrid-aquatic.sea_bass" to "The Sea Bass, a popular game and commercial fish, recognized for its firm texture and mild flavor, often found in coastal waters.",
            "profile.item.hybrid-aquatic.snailfish" to "The Snailfish, a soft-bodied, deep-sea fish adapted to extreme pressures, often found clinging to rocks or ice with its pelvic fins.",
            "profile.item.hybrid-aquatic.squirrelfish" to "The Squirrelfish, a nocturnal reef fish with large eyes and a bright red body, known for its loud vocalizations produced by grinding its teeth.",
            "profile.item.hybrid-aquatic.blue_spotted_stingray" to "The Blue-Spotted Stingray, a striking marine fish known for its vibrant blue spots and flattened body, often found gliding along sandy sea floors.",
            "profile.item.hybrid-aquatic.spotted_eagle_ray" to "The Spotted Eagle Ray, a graceful, large ray recognized by its dark body covered in white spots and long, whip-like tail, often seen swimming near the surface in tropical waters.",
            "profile.item.hybrid-aquatic.stonefish" to "The Stonefish, a venomous, camouflaged fish that blends into the seafloor, equipped with potent spines capable of delivering a dangerous sting.",
            "profile.item.hybrid-aquatic.sunfish" to "The Sunfish, also known as the Mola, is one of the heaviest bony fish, recognizable by its flattened, disk-like body and tendency to bask near the ocean surface.",
            "profile.item.hybrid-aquatic.surgeonfish" to "The Surgeonfish, a vibrant, reef-dwelling fish known for its bright colors, popularized by its role in marine ecosystems as an algae grazer.",
            "profile.item.hybrid-aquatic.neon_tetra" to "The Neon Tetra, a small, brightly colored freshwater fish known for its iridescent blue and red stripes, making it a popular choice for home aquariums.",
            "profile.item.hybrid-aquatic.tiger_barb" to "The Tiger Barb, a lively, freshwater fish recognized by its bold black stripes over an orange-gold body, often kept in groups in community tanks.",
            "profile.item.hybrid-aquatic.toadfish" to "The Toadfish, a small, bottom-dwelling fish with a flattened body and rough skin, commonly found in coastal waters and known for its toxicity.",
            "profile.item.hybrid-aquatic.triggerfish" to "The Triggerfish, a brightly colored, reef-dwelling fish known for its strong jaws, sharp teeth, and the ability to lock its dorsal fin in an upright position for defense.",
            "profile.item.hybrid-aquatic.tuna" to "The Tuna, a fast-swimming, large, pelagic fish with a streamlined body and yellow-colored fins, highly valued in commercial and sport fishing.",

            ).forEach { (key, profile) ->
            builder.add(key, profile)
        }

        // entities
        generateEntities(builder)

        // blocks
        mapOf(
            HybridAquaticBlocks.BASKING_SHARK_PLUSHIE.get() to "Basking Shark Plushie",
            HybridAquaticBlocks.BULL_SHARK_PLUSHIE.get() to "Bull Shark Plushie",
            HybridAquaticBlocks.FRILLED_SHARK_PLUSHIE.get() to "Frilled Shark Plushie",
            HybridAquaticBlocks.GREAT_WHITE_SHARK_PLUSHIE.get() to "Great White Shark Plushie",
            HybridAquaticBlocks.HAMMERHEAD_SHARK_PLUSHIE.get() to "Hammerhead Shark Plushie",
            HybridAquaticBlocks.THRESHER_SHARK_PLUSHIE.get() to "Thresher Shark Plushie",
            HybridAquaticBlocks.TIGER_SHARK_PLUSHIE.get() to "Tiger Shark Plushie",
            HybridAquaticBlocks.WHALE_SHARK_PLUSHIE.get() to "Whale Shark Plushie",
            HybridAquaticBlocks.ANEMONE.get() to "Anemone",
            HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get() to "Giant Green Anemone",
            HybridAquaticBlocks.STRAWBERRY_ANEMONE.get() to "Strawberry Anemone",
            HybridAquaticBlocks.TUBE_SPONGE.get() to "Tube Sponge",
            HybridAquaticBlocks.CRAB_POT.get() to "Crab Pot",
            HybridAquaticBlocks.HYBRID_CRATE.get() to "Hybrid Crate",
            HybridAquaticBlocks.OAK_CRATE.get() to "Oak Crate",
            HybridAquaticBlocks.SPRUCE_CRATE.get() to "Spruce Crate",
            HybridAquaticBlocks.BIRCH_CRATE.get() to "Birch Crate",
            HybridAquaticBlocks.DARK_OAK_CRATE.get() to "Dark Oak Crate",
            HybridAquaticBlocks.JUNGLE_CRATE.get() to "Jungle Crate",
            HybridAquaticBlocks.ACACIA_CRATE.get() to "Acacia Crate",
            HybridAquaticBlocks.MANGROVE_CRATE.get() to "Mangrove Crate",
            HybridAquaticBlocks.CHERRY_CRATE.get() to "Cherry Crate",
            HybridAquaticBlocks.BAMBOO_CRATE.get() to "Bamboo Crate",
            HybridAquaticBlocks.BUOY.get() to "Buoy",
            HybridAquaticBlocks.GIANT_CLAM.get() to "Giant Clam",

            HybridAquaticBlocks.RED_ALGAE.get() to "Red Algae",
            HybridAquaticBlocks.TALL_RED_ALGAE.get() to "Tall Red Algae",

            HybridAquaticPlatformBlocks.DUNEGRASS.get() to "Dunegrass",
            HybridAquaticPlatformBlocks.TALL_DUNEGRASS.get() to "Tall Dunegrass",

            HybridAquaticPlatformBlocks.CATTAIL.get() to "Cattail",

            HybridAquaticBlocks.SARGASSUM.get() to "Sargassum",
            HybridAquaticBlocks.SARGASSUM_PLANT.get() to "Sargassum Plant",
            HybridAquaticBlocks.BULL_KELP.get() to "Bull Kelp",
            HybridAquaticBlocks.BULL_KELP_PLANT.get() to "Bull Kelp Plant",
            HybridAquaticBlocks.FLOATING_SARGASSUM.get() to "Floating Sargassum",
            HybridAquaticBlocks.WATER_LETTUCE.get() to "Water Lettuce",
            HybridAquaticBlocks.JUNGLE_LILY_PAD.get() to "Jungle Lily Pad",
            HybridAquaticBlocks.RAFT.get() to "Raft",

            HybridAquaticBlocks.GLOWING_PLANKTON.get() to "Glowing Plankton",

            HybridAquaticBlocks.SEA_LETTUCE.get() to "Sea Lettuce",
            HybridAquaticBlocks.TALL_SEA_LETTUCE.get() to "Tall Sea Lettuce",

            HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK.get() to "Lophelia Coral Block",
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK.get() to "Dead Lophelia Coral Block",
            HybridAquaticBlocks.LOPHELIA_CORAL.get() to "Lophelia Coral",
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL.get() to "Dead Lophelia Coral",
            HybridAquaticBlocks.LOPHELIA_CORAL_FAN.get() to "Lophelia Coral Fan",
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN.get() to "Dead Lophelia Coral Fan",
            HybridAquaticBlocks.ROSE_CORAL_BLOCK.get() to "Rose Coral Block",
            HybridAquaticBlocks.DEAD_ROSE_CORAL_BLOCK.get() to "Dead Rose Coral Block",
            HybridAquaticBlocks.ROSE_CORAL.get() to "Rose Coral",
            HybridAquaticBlocks.DEAD_ROSE_CORAL.get() to "Dead Rose Coral",
            HybridAquaticBlocks.ROSE_CORAL_FAN.get() to "Rose Coral Fan",
            HybridAquaticBlocks.DEAD_ROSE_CORAL_FAN.get() to "Dead Rose Coral Fan",
            HybridAquaticBlocks.LEAF_CORAL_BLOCK.get() to "Leaf Coral Block",
            HybridAquaticBlocks.DEAD_LEAF_CORAL_BLOCK.get() to "Dead Leaf Coral Block",
            HybridAquaticBlocks.LEAF_CORAL.get() to "Leaf Coral",
            HybridAquaticBlocks.DEAD_LEAF_CORAL.get() to "Dead Leaf Coral",
            HybridAquaticBlocks.LEAF_CORAL_FAN.get() to "Leaf Coral Fan",
            HybridAquaticBlocks.DEAD_LEAF_CORAL_FAN.get() to "Dead Leaf Coral Fan",
            HybridAquaticBlocks.BUTTON_CORAL_BLOCK.get() to "Button Coral Block",
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK.get() to "Dead Button Coral Block",
            HybridAquaticBlocks.BUTTON_CORAL.get() to "Button Coral",
            HybridAquaticBlocks.DEAD_BUTTON_CORAL.get() to "Dead Button Coral",
            HybridAquaticBlocks.BUTTON_CORAL_FAN.get() to "Button Coral Fan",
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_FAN.get() to "Dead Button Coral Fan",
            HybridAquaticBlocks.SUN_CORAL_BLOCK.get() to "Sun Coral Block",
            HybridAquaticBlocks.DEAD_SUN_CORAL_BLOCK.get() to "Dead Sun Coral Block",
            HybridAquaticBlocks.SUN_CORAL.get() to "Sun Coral",
            HybridAquaticBlocks.DEAD_SUN_CORAL.get() to "Dead Sun Coral",
            HybridAquaticBlocks.SUN_CORAL_FAN.get() to "Sun Coral Fan",
            HybridAquaticBlocks.DEAD_SUN_CORAL_FAN.get() to "Dead Sun Coral Fan",
            HybridAquaticBlocks.THORN_CORAL_BLOCK.get() to "Thorn Coral Block",
            HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK.get() to "Dead Thorn Coral Block",
            HybridAquaticBlocks.THORN_CORAL.get() to "Thorn Coral",
            HybridAquaticBlocks.DEAD_THORN_CORAL.get() to "Dead Thorn Coral",
            HybridAquaticBlocks.THORN_CORAL_FAN.get() to "Thorn Coral Fan",
            HybridAquaticBlocks.DEAD_THORN_CORAL_FAN.get() to "Dead Thorn Coral Fan",
            HybridAquaticBlocks.GLOWSTICK.get() to "Glowstick",
            HybridAquaticBlocks.GLOWSLIME_BLOCK.get() to "Glowslime Block",
            HybridAquaticBlocks.PEARL_BLOCK.get() to "Pearl Block",
            HybridAquaticBlocks.BLACK_PEARL_BLOCK.get() to "Black Pearl Block",
            HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get() to "Driftwood Log",
            HybridAquaticPlatformBlocks.DRIFTWOOD_WOOD.get() to "Driftwood Wood",
            HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get() to "Stripped Driftwood Log",
            HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get() to "Stripped Driftwood Wood",
            HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get() to "Driftwood Planks",
            HybridAquaticPlatformBlocks.DRIFTWOOD_STAIRS.get() to "Driftwood Stairs",
            HybridAquaticPlatformBlocks.DRIFTWOOD_SLAB.get() to "Driftwood Slab",
            HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE.get() to "Driftwood Fence",
            HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE_GATE.get() to "Driftwood Fence Gate",
            HybridAquaticPlatformBlocks.DRIFTWOOD_DOOR.get() to "Driftwood Door",
            HybridAquaticPlatformBlocks.DRIFTWOOD_TRAPDOOR.get() to "Driftwood Trapdoor",
            HybridAquaticPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get() to "Driftwood Pressure Plate",
            HybridAquaticPlatformBlocks.DRIFTWOOD_BUTTON.get() to "Driftwood Button",
            HybridAquaticBlocks.THERMAL_VENT.get() to "Thermal Vent",
            HybridAquaticBlocks.TUBE_WORM.get() to "Tube Worm",
        ).forEach { (block, translation) ->
            builder.add(block, translation)
        }

        // items
        mapOf(
            HybridAquaticItems.UNI.get() to "Uni",
            HybridAquaticItems.RAW_FISH_STEAK.get() to "Fish Steak",
            HybridAquaticItems.COOKED_FISH_STEAK.get() to "Cooked Fish Steak",
            HybridAquaticItems.RAW_FISH_MEAT.get() to "Raw Fish Meat",
            HybridAquaticItems.COOKED_FISH_MEAT.get() to "Cooked Fish Meat",
            HybridAquaticItems.RAW_TENTACLE.get() to "Raw Tentacle",
            HybridAquaticItems.COOKED_TENTACLE.get() to "Cooked Tentacle",
            HybridAquaticItems.RAW_CRAB.get() to "Raw Crab",
            HybridAquaticItems.COOKED_CRAB.get() to "Cooked Crab",
            HybridAquaticItems.RAW_LOBSTER.get() to "Raw Lobster",
            HybridAquaticItems.COOKED_LOBSTER.get() to "Cooked Lobster",
            HybridAquaticItems.RAW_LOBSTER_TAIL.get() to "Raw Lobster Tail",
            HybridAquaticItems.COOKED_LOBSTER_TAIL.get() to "Cooked Lobster Tail",
            HybridAquaticItems.RAW_SHRIMP.get() to "Raw Shrimp",
            HybridAquaticItems.COOKED_SHRIMP.get() to "Cooked Shrimp",
            HybridAquaticItems.RAW_CRAYFISH.get() to "Raw Crayfish",
            HybridAquaticItems.COOKED_CRAYFISH.get() to "Cooked Crayfish",
            HybridAquaticItems.LIONFISH.get() to "Lionfish",
            HybridAquaticItems.NEON_TETRA.get() to "Neon Tetra",
            HybridAquaticItems.DAMSELFISH.get() to "Damselfish",
            HybridAquaticItems.DRAGONFISH.get() to "Dragonfish",
            HybridAquaticItems.FLASHLIGHT_FISH.get() to "Flashlight Fish",
            HybridAquaticItems.SQUIRRELFISH.get() to "Squirrel Fish",
            HybridAquaticItems.COELACANTH.get() to "Coelacanth",
            HybridAquaticItems.GOLDEN_DORADO.get() to "Golden Dorado",
            HybridAquaticItems.MAHI.get() to "Mahi",
            HybridAquaticItems.TUNA.get() to "Tuna",
            HybridAquaticItems.OPAH.get() to "Opah",
            HybridAquaticItems.OARFISH.get() to "Oarfish",
            HybridAquaticItems.ROCKFISH.get() to "Rockfish",
            HybridAquaticItems.SEA_BASS.get() to "Sea Bass",
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY.get() to "Blue Spotted Stingray",
            HybridAquaticItems.SPOTTED_EAGLE_RAY.get() to "Spotted Eagle Ray",
            HybridAquaticItems.SUNFISH.get() to "Sunfish",
            HybridAquaticItems.BLOWFISH.get() to "Blowfish",
            HybridAquaticItems.PARROTFISH.get() to "Parrotfish",
            HybridAquaticItems.SHEEPSHEAD_WRASSE.get() to "Sheepshead Wrasse",
            HybridAquaticItems.STONEFISH.get() to "Stonefish",
            HybridAquaticItems.SEAHORSE.get() to "Seahorse",
            HybridAquaticItems.GOLDFISH.get() to "Goldfish",
            HybridAquaticItems.MORAY_EEL.get() to "Moray Eel",
            HybridAquaticItems.NEEDLEFISH.get() to "Needlefish",
            HybridAquaticItems.MACKEREL.get() to "Mackerel",
            HybridAquaticItems.HERRING.get() to "Herring",
            HybridAquaticItems.FLYING_FISH.get() to "Flying Fish",
            HybridAquaticItems.PIRANHA.get() to "Piranha",
            HybridAquaticItems.ANGLERFISH.get() to "Anglerfish",
            HybridAquaticItems.CARP.get() to "Carp",
            HybridAquaticItems.BARRELEYE.get() to "Barreleye",
            HybridAquaticItems.SURGEONFISH.get() to "Surgeonfish",
            HybridAquaticItems.CLOWNFISH.get() to "Clownfish",
            HybridAquaticItems.TIGER_BARB.get() to "Tiger Barb",
            HybridAquaticItems.OSCAR.get() to "Oscar",
            HybridAquaticItems.TRIGGERFISH.get() to "Triggerfish",
            HybridAquaticItems.DANIO.get() to "Danio",
            HybridAquaticItems.BETTA.get() to "Betta",
            HybridAquaticItems.PEARLFISH.get() to "Pearlfish",
            HybridAquaticItems.SNAILFISH.get() to "Snailfish",
            HybridAquaticItems.JOHN_DORY.get() to "John Dory",
            HybridAquaticItems.DISCUS.get() to "Discus",
            HybridAquaticItems.GOURAMI.get() to "Gourami",
            HybridAquaticItems.RATFISH.get() to "Ratfish",
            HybridAquaticItems.BOXFISH.get() to "Boxfish",
            HybridAquaticItems.LOBSTER_CLAW.get() to "Lobster Claw",
            HybridAquaticItems.DUNGENESS_CRAB_CLAW.get() to "Dungeness Crab Claw",
            HybridAquaticItems.COCONUT_CRAB_CLAW.get() to "Coconut Crab Claw",
            HybridAquaticItems.FIDDLER_CRAB_CLAW.get() to "Fiddler Crab Claw",
            HybridAquaticItems.YETI_CRAB_CLAW.get() to "Yeti Crab Claw",
            HybridAquaticItems.LIGHTFOOT_CRAB_CLAW.get() to "Lightfoot Crab Claw",
            HybridAquaticItems.GHOST_CRAB_CLAW.get() to "Ghost Crab Claw",
            HybridAquaticItems.FLOWER_CRAB_CLAW.get() to "Flower Crab Claw",
            HybridAquaticItems.VAMPIRE_CRAB_CLAW.get() to "Vampire Crab Claw",
            HybridAquaticItems.SPIDER_CRAB_CLAW.get() to "Spider Crab Claw",
            HybridAquaticItems.GLOWSLIME.get() to "Glowslime",
            HybridAquaticItems.CUTTLEBONE.get() to "Cuttlebone",
            HybridAquaticItems.SEA_URCHIN_SPINE.get() to "Sea Urchin Spine",
            HybridAquaticItems.PRISMARINE_ROD.get() to "Prismarine Rod",
            HybridAquaticItems.SHARK_TOOTH.get() to "Shark Tooth",
            HybridAquaticItems.PEARL.get() to "Pearl",
            HybridAquaticItems.BLACK_PEARL.get() to "Black Pearl",
            HybridAquaticItems.SULFUR.get() to "Sulfur",
            HybridAquaticItems.CORAL_CHUNK.get() to "Coral Chunk",
            HybridAquaticItems.BARBED_HOOK.get() to "Barbed Hook",
            HybridAquaticItems.GLOWING_HOOK.get() to "Glowing Hook",
            HybridAquaticItems.MAGNETIC_HOOK.get() to "Magnetic Hook",
            HybridAquaticItems.CREEPERMAGNET_HOOK.get() to "CreeperMagnet Hook",
            HybridAquaticItems.OMINOUS_HOOK.get() to "Ominous Hook",
            HybridAquaticItems.FISHING_NET.get() to "Fishing Net",
            HybridAquaticItems.KARKINOS_CLAW.get() to "Karkinos Claw",
            HybridAquaticItems.SEASHELL_SPEAR.get() to "Seashell Spear",
            HybridAquaticItems.SEASHELL_PICKAXE.get() to "Seashell Pickaxe",
            HybridAquaticItems.SEASHELL_AXE.get() to "Seashell Axe",
            HybridAquaticItems.SEASHELL_SHOVEL.get() to "Seashell Shovel",
            HybridAquaticItems.SEASHELL_HOE.get() to "Seashell Hoe",
            HybridAquaticItems.CORAL_BLADE.get() to "Coral Blade",
            HybridAquaticItems.CORAL_PICKAXE.get() to "Coral Pickaxe",
            HybridAquaticItems.CORAL_AXE.get() to "Coral Axe",
            HybridAquaticItems.CORAL_SHOVEL.get() to "Coral Shovel",
            HybridAquaticItems.CORAL_HOE.get() to "Coral Hoe",
            HybridAquaticItems.DIVING_HELMET.get() to "Diving Helmet",
            HybridAquaticItems.DIVING_SUIT.get() to "Diving Suit",
            HybridAquaticItems.DIVING_LEGGINGS.get() to "Diving Leggings",
            HybridAquaticItems.DIVING_BOOTS.get() to "Diving Boots",
            HybridAquaticItems.NAUTILUS_HELMET.get() to "Nautilus Helmet",
            HybridAquaticItems.NAUTILUS_PAULDRONS.get() to "Nautilus Pauldrons",
            HybridAquaticItems.MANGLERFISH_LURE.get() to "Manglerfish Lure",
            HybridAquaticItems.MANGLERFISH_FIN.get() to "Manglerfish Fin",
            HybridAquaticItems.EEL_SCARF.get() to "Eel Scarf",
            HybridAquaticItems.TURTLE_CHESTPLATE.get() to "Turtle Chestplate",
            HybridAquaticItems.MOON_JELLYFISH_HAT.get() to "Moon Jellyfish Hat",
        ).forEach { (item, translation) ->
            builder.add(item, translation)
        }

        // effects
        mapOf(
            HybridAquaticMobEffects.BLEEDING.get() to "Bleeding",
            HybridAquaticMobEffects.CLARITY.get() to "Clarity",
            HybridAquaticMobEffects.CORROSION.get() to "Corrosion",
            HybridAquaticMobEffects.THALASSOPHOBIA.get() to "Thalassophobia",
            HybridAquaticMobEffects.BUOYANCY.get() to "Buoyancy",
            HybridAquaticMobEffects.THORNS.get() to "Thorns",
        ).forEach { (effect, translation) ->
            val identifier = BuiltInRegistries.MOB_EFFECT.getKey(effect)
            builder.add("effect.${identifier?.namespace}.${identifier?.path}", translation)
        }

        // Item descriptions
        mapOf(
            "item.hybrid-aquatic.hook" to "Needs to be put in the offhand",
            HybridAquaticItems.BARBED_HOOK.get().descriptionId to "Increases fishing speed during the day",
            HybridAquaticItems.GLOWING_HOOK.get().descriptionId to "Increases fishing speed at night",
            HybridAquaticItems.MAGNETIC_HOOK.get().descriptionId to "Increases treasure chance",
            HybridAquaticItems.CREEPERMAGNET_HOOK.get().descriptionId to "Don't use indoors",
            HybridAquaticItems.OMINOUS_HOOK.get().descriptionId to "Summons Karkinos",
            HybridAquaticBlocks.CRAB_POT.get().descriptionId to "Break with an axe to open",
            HybridAquaticBlocks.HYBRID_CRATE.get().descriptionId to "Break with an axe to open",
            HybridAquaticBlocks.OAK_CRATE.get().descriptionId to "Break with an axe to open",
            HybridAquaticBlocks.SPRUCE_CRATE.get().descriptionId to "Break with an axe to open",
            HybridAquaticBlocks.BIRCH_CRATE.get().descriptionId to "Break with an axe to open",
            HybridAquaticBlocks.DARK_OAK_CRATE.get().descriptionId to "Break with an axe to open",
            HybridAquaticBlocks.JUNGLE_CRATE.get().descriptionId to "Break with an axe to open",
            HybridAquaticBlocks.MANGROVE_CRATE.get().descriptionId to "Break with an axe to open",
            HybridAquaticBlocks.ACACIA_CRATE.get().descriptionId to "Break with an axe to open",
            HybridAquaticBlocks.CHERRY_CRATE.get().descriptionId to "Break with an axe to open",
            HybridAquaticBlocks.BAMBOO_CRATE.get().descriptionId to "Break with an axe to open",
            HybridAquaticItems.FISHING_NET.get().descriptionId to "Stored Entity: %s",
            HybridAquaticItems.MOON_JELLYFISH_HAT.get().descriptionId to "Made by Jakotens",
        ).forEach { (itemTranslationKey, translation) ->
            builder.add(itemTranslationKey.plus(".description"), translation)
        }

        mapOf(
            "glowing" to "Glowing",
            "clarity" to "Clarity",
            "corrosion" to "Corrosion",
            "thalassophobia" to "Thalassophobia",
            "bleeding" to "Bleeding",
            "swimming" to "Swimming",
            "buoyancy" to "Buoyancy",
            "thorns" to "Thorns",
            "minor_luck" to "Minor Luck",
            "major_luck" to "Major Luck",
            "blindness" to "Blindness",
        ).forEach { (potion, translation) ->
            builder.add("item.minecraft.potion.effect.$potion", "Potion of $translation")
            builder.add("item.minecraft.splash_potion.effect.$potion", "Splash Potion of $translation")
            builder.add("item.minecraft.lingering_potion.effect.$potion", "Lingering Potion of $translation")
            builder.add("item.minecraft.tipped_arrow.effect.$potion", "Arrow of $translation")
        }


        // Workaround for Jade/WAILA not being able to find the mod display name on Forge
        builder.add("modmenu.nameTranslation.hybrid-aquatic", "Hybrid Aquatic")

//        mapOf(
//            HybridAquaticPaintings.TEST_PAINTING1 to listOf("Test Painting", "Aqua"),
//            HybridAquaticPaintings.TEST_PAINTING2 to listOf("Test Huge Painting", "Aqua")
//        ).forEach { (painting, name) ->
//            builder.add("painting.hybrid-aquatic.${painting.path}.title", name[0])
//            builder.add("painting.hybrid-aquatic.${painting.path}.author", name[1])
//        }
    }

    private fun generateEntities(builder: TranslationBuilder) {
        // create map of entities to their display names
        val entityNameMap = mapOf(
            HybridAquaticEntityTypes.CLOWNFISH.get() to "Clownfish",
            HybridAquaticEntityTypes.DAMSELFISH.get() to "Damselfish",
            HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH.get() to "African Butterflyfish",
            HybridAquaticEntityTypes.ANGLERFISH.get() to "Anglerfish",
            HybridAquaticEntityTypes.VIPERFISH.get() to "Viperfish",
            HybridAquaticEntityTypes.HATCHETFISH.get() to "Hatchetfish",
            HybridAquaticEntityTypes.FANGTOOTH.get() to "Fangtooth",
            HybridAquaticEntityTypes.JOHN_DORY.get() to "John Dory",
            HybridAquaticEntityTypes.SNAILFISH.get() to "Snailfish",
            HybridAquaticEntityTypes.PEARLFISH.get() to "Pearlfish",
            HybridAquaticEntityTypes.DRAGONFISH.get() to "Dragonfish",
            HybridAquaticEntityTypes.BARRELEYE.get() to "Barreleye",
            HybridAquaticEntityTypes.TUNA.get() to "Tuna",
            HybridAquaticEntityTypes.CUTTLEFISH.get() to "Cuttlefish",
            HybridAquaticEntityTypes.FLASHLIGHT_FISH.get() to "Flashlight Fish",
            HybridAquaticEntityTypes.SQUIRRELFISH.get() to "Squirrelfish",
            HybridAquaticEntityTypes.FLYING_FISH.get() to "Flying Fish",
            HybridAquaticEntityTypes.LIONFISH.get() to "Lionfish",
            HybridAquaticEntityTypes.COELACANTH.get() to "Coelacanth",
            HybridAquaticEntityTypes.OARFISH.get() to "Oarfish",
            HybridAquaticEntityTypes.OPAH.get() to "Opah",
            HybridAquaticEntityTypes.PIRANHA.get() to "Piranha",
            HybridAquaticEntityTypes.SEA_ANGEL.get() to "Sea Angel",
            HybridAquaticEntityTypes.SUNFISH.get() to "Sunfish",
            HybridAquaticEntityTypes.PARROTFISH.get() to "Parrotfish",
            HybridAquaticEntityTypes.VAMPIRE_SQUID.get() to "Vampire Squid",
            HybridAquaticEntityTypes.MAHI.get() to "Mahi",
            HybridAquaticEntityTypes.GOLDEN_DORADO.get() to "Golden Dorado",
            HybridAquaticEntityTypes.MORAY_EEL.get() to "Moray Eel",
            HybridAquaticEntityTypes.ROCKFISH.get() to "Rockfish",
            HybridAquaticEntityTypes.SEA_BASS.get() to "Sea Bass",
            HybridAquaticEntityTypes.TIGER_BARB.get() to "Tiger Barb",
            HybridAquaticEntityTypes.CARP.get() to "Carp",
            HybridAquaticEntityTypes.NEEDLEFISH.get() to "Needlefish",
            HybridAquaticEntityTypes.BARRACUDA.get() to "Barracuda",
            HybridAquaticEntityTypes.MACKEREL.get() to "Mackerel",
            HybridAquaticEntityTypes.HERRING.get() to "Herring",
            HybridAquaticEntityTypes.RATFISH.get() to "Ratfish",
            HybridAquaticEntityTypes.NAUTILUS.get() to "Nautilus",
            HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get() to "Umbrella Octopus",
            HybridAquaticEntityTypes.TRIGGERFISH.get() to "Triggerfish",
            HybridAquaticEntityTypes.OSCAR.get() to "Oscar",
            HybridAquaticEntityTypes.DANIO.get() to "Danio",
            HybridAquaticEntityTypes.BLOWFISH.get() to "Blowfish",
            HybridAquaticEntityTypes.TETRA.get() to "Tetra",
            HybridAquaticEntityTypes.PUPFISH.get() to "Pupfish",
            HybridAquaticEntityTypes.STONEFISH.get() to "Stonefish",
            HybridAquaticEntityTypes.BETTA.get() to "Betta",
            HybridAquaticEntityTypes.GOLDFISH.get() to "Goldfish",
            HybridAquaticEntityTypes.SEAHORSE.get() to "Seahorse",
            HybridAquaticEntityTypes.MOON_JELLYFISH.get() to "Moon Jellyfish",
            HybridAquaticEntityTypes.GOURAMI.get() to "Gourami",
            HybridAquaticEntityTypes.BOXFISH.get() to "Boxfish",
            HybridAquaticEntityTypes.OCTOPUS.get() to "Octopus",
            HybridAquaticEntityTypes.DISCUS.get() to "Discus",
            HybridAquaticEntityTypes.ARROW_SQUID.get() to "Arrow Squid",
            HybridAquaticEntityTypes.COLOSSAL_SQUID.get() to "Colossal Squid",
            HybridAquaticEntityTypes.FIREFLY_SQUID.get() to "Firefly Squid",
            HybridAquaticEntityTypes.STINGRAY.get() to "Stingray",
            HybridAquaticEntityTypes.MANTA_RAY.get() to "Manta Ray",
            HybridAquaticEntityTypes.SURGEONFISH.get() to "Surgeonfish",
            HybridAquaticEntityTypes.BULL_SHARK.get() to "Bull Shark",
            HybridAquaticEntityTypes.BASKING_SHARK.get() to "Basking Shark",
            HybridAquaticEntityTypes.THRESHER_SHARK.get() to "Thresher Shark",
            HybridAquaticEntityTypes.FRILLED_SHARK.get() to "Frilled Shark",
            HybridAquaticEntityTypes.LANTERN_SHARK.get() to "Lantern Shark",
            HybridAquaticEntityTypes.GREAT_WHITE_SHARK.get() to "Great White Shark",
            HybridAquaticEntityTypes.TIGER_SHARK.get() to "Tiger Shark",
            HybridAquaticEntityTypes.HAMMERHEAD_SHARK.get() to "Hammerhead Shark",
            HybridAquaticEntityTypes.WHALE_SHARK.get() to "Whale Shark",
            HybridAquaticEntityTypes.KARKINOS.get() to "Karkinos",
            HybridAquaticEntityTypes.KARCINOGEN.get() to "Karcinogen",
            HybridAquaticEntityTypes.KARCINOMA.get() to "Karcinoma",
            HybridAquaticEntityTypes.DUNGENESS_CRAB.get() to "Dungeness Crab",
            HybridAquaticEntityTypes.FIDDLER_CRAB.get() to "Fiddler Crab",
            HybridAquaticEntityTypes.HERMIT_CRAB.get() to "Hermit Crab",
            HybridAquaticEntityTypes.GHOST_CRAB.get() to "Ghost Crab",
            HybridAquaticEntityTypes.LIGHTFOOT_CRAB.get() to "Lightfoot Crab",
            HybridAquaticEntityTypes.FLOWER_CRAB.get() to "Flower Crab",
            HybridAquaticEntityTypes.VAMPIRE_CRAB.get() to "Vampire Crab",
            HybridAquaticEntityTypes.SPIDER_CRAB.get() to "Spider Crab",
            HybridAquaticEntityTypes.YETI_CRAB.get() to "Yeti Crab",
            HybridAquaticEntityTypes.DECORATOR_CRAB.get() to "Decorator Crab",
            HybridAquaticEntityTypes.COCONUT_CRAB.get() to "Coconut Crab",
            HybridAquaticEntityTypes.HORSESHOE_CRAB.get() to "Horseshoe Crab",
            HybridAquaticEntityTypes.GIANT_ISOPOD.get() to "Giant Isopod",
            HybridAquaticEntityTypes.SHRIMP.get() to "Shrimp",
            HybridAquaticEntityTypes.CRAYFISH.get() to "Crayfish",
            HybridAquaticEntityTypes.LOBSTER.get() to "Lobster",
            HybridAquaticEntityTypes.SEA_SLUG.get() to "Sea Slug",
            HybridAquaticEntityTypes.SCALYFOOT_SNAIL.get() to "Scalyfoot Snail",
            HybridAquaticEntityTypes.SEA_CUCUMBER.get() to "Sea Cucumber",
            HybridAquaticEntityTypes.SEA_URCHIN.get() to "Sea Urchin",
            HybridAquaticEntityTypes.STARFISH.get() to "Starfish",
            HybridAquaticEntityTypes.SEA_NETTLE.get() to "Sea Nettle",
            HybridAquaticEntityTypes.BOX_JELLYFISH.get() to "Box Jellyfish",
            HybridAquaticEntityTypes.CEPHEIDAE_JELLYFISH.get() to "Cepheidae Jellyfish",
            HybridAquaticEntityTypes.NOMURA_JELLYFISH.get() to "Nomura Jellyfish",
            HybridAquaticEntityTypes.BARREL_JELLYFISH.get() to "Barrel Jellyfish",
            HybridAquaticEntityTypes.MAUVE_STINGER.get() to "Mauve Stinger",
            HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH.get() to "Lion's Mane Jellyfish",
            HybridAquaticEntityTypes.CROWN_JELLYFISH.get() to "Crown Jellyfish",
            HybridAquaticEntityTypes.BIG_RED_JELLYFISH.get() to "Big Red Jellyfish",
            HybridAquaticEntityTypes.COSMIC_JELLYFISH.get() to "Cosmic Jellyfish",
            HybridAquaticEntityTypes.FIREWORK_JELLYFISH.get() to "Firework Jellyfish",
            HybridAquaticEntityTypes.BLUE_JELLYFISH.get() to "Blue Jellyfish",
            HybridAquaticEntityTypes.SEADRAGON.get() to "Seadragon",
            HybridAquaticEntityTypes.WRASSE.get() to "Wrasse",
            HybridAquaticEntityTypes.HOUND_SHARK.get() to "Hound Shark",
            HybridAquaticEntityTypes.OTTER.get() to "Otter",
        )

        // verify display name list is valid
        val nonPresentEntityNames = mutableListOf<EntityType<*>>()

        BuiltInRegistries.ENTITY_TYPE
            .filter(filterHybridAquatic(BuiltInRegistries.ENTITY_TYPE))
            .forEach { type ->
                if (type.baseClass.isAssignableFrom(Mob::class.java)) {
                    if (!entityNameMap.containsKey(type)) {
                        nonPresentEntityNames.add(type)
                    }
                }
            }

        if (nonPresentEntityNames.isNotEmpty()) {
            throw IllegalStateException("Entity to display name map does not contain ${nonPresentEntityNames.joinToString()}. Please modify ${javaClass.simpleName} accordingly.")
        }

        // generate entity and entity spawn egg translations
        entityNameMap.forEach { (entityType, translation) ->
            val id = BuiltInRegistries.ENTITY_TYPE.getKey(entityType)
            val translationKey = entityType.descriptionId
            val namespace = id.namespace
            val path = id.path
            builder.add(translationKey, translation)
            builder.add("item.$namespace.${path}_spawn_egg", "$translation Spawn Egg")
        }
    }
}