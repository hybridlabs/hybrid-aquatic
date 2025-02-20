package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.data.server.seamessage.SeaMessageProvider
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
        builder.add(
            Registries.ITEM_GROUP.getKey(HybridAquaticItemGroups.BLOCKS)
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Blocks"
        )

        builder.add(
            Registries.ITEM_GROUP.getKey(HybridAquaticItemGroups.ITEMS)
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Items"
        )

        builder.add(
            Registries.ITEM_GROUP.getKey(HybridAquaticItemGroups.SPAWN_EGGS)
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Spawn Eggs"
        )

        // message in a bottle
        HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.translationKey.let { key ->
            builder.add(key, "Message in a Bottle")
            builder.add("$key.jar", "Message in a Jar")
            builder.add("$key.longneck", "Message in a Longneck Bottle")
        }

        // sea messages
        SeaMessageProvider.BUILT_IN.forEach { message ->
            builder.add(message.translationKey, message.englishText)
            message.englishTitle?.let { title -> builder.add(message.titleTranslationKey, title) }
        }

        builder.add(HybridAquaticItems.SEA_MESSAGE_BOOK, "Sea Message")

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
            "profile.item.hybrid-aquatic.koi" to "The Koi, a domesticated variety of common carp, is highly prized for its beautiful, vibrant patterns and colors, often kept in ornamental ponds.",
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
            "profile.item.hybrid-aquatic.blue_tang" to "The Blue Tang, a vibrant, reef-dwelling fish known for its bright blue color and yellow tail, popularized by its role in marine ecosystems as an algae grazer.",
            "profile.item.hybrid-aquatic.powder_blue_tang" to "The Powder Blue Tang, a reef fish distinguished by its striking blue body, yellow dorsal fin, and black face mask, often found in the Indian Ocean.",
            "profile.item.hybrid-aquatic.surgeonfish_lined" to "The Lined Surgeonfish, also known as the Clown Tang, is a reef-dwelling fish with bold horizontal stripes and a sharp spine near its tail, often found in the Indo-Pacific region.",
            "profile.item.hybrid-aquatic.surgeonfish_orangeshoulder" to "The Orange Shoulder Surgeonfish, a tropical reef fish known for the distinctive orange patch on its side and its role in grazing on algae to maintain coral reef ecosystems.",
            "profile.item.hybrid-aquatic.surgeonfish_sohal" to "The Sohal Surgeonfish, a striking fish with bold, horizontal blue and white stripes and a sharp spine, commonly found in the Red Sea and the Arabian Gulf.",
            "profile.item.hybrid-aquatic.unicornfish" to "The Unicornfish, a tropical fish named for the horn-like projection on its forehead, known for its elongated body and ability to adapt to various reef environments.",
            "profile.item.hybrid-aquatic.yellow_tang" to "The Yellow Tang, a bright yellow reef fish popular in aquariums, known for its grazing on algae and playing a role in maintaining reef health.",
            "profile.item.hybrid-aquatic.neon_tetra" to "The Neon Tetra, a small, brightly colored freshwater fish known for its iridescent blue and red stripes, making it a popular choice for home aquariums.",
            "profile.item.hybrid-aquatic.tiger_barb" to "The Tiger Barb, a lively, freshwater fish recognized by its bold black stripes over an orange-gold body, often kept in groups in community tanks.",
            "profile.item.hybrid-aquatic.toadfish" to "The Toadfish, a small, bottom-dwelling fish with a flattened body and rough skin, commonly found in coastal waters and known for its toxicity.",
            "profile.item.hybrid-aquatic.triggerfish" to "The Triggerfish, a brightly colored, reef-dwelling fish known for its strong jaws, sharp teeth, and the ability to lock its dorsal fin in an upright position for defense.",
            "profile.item.hybrid-aquatic.yellowfin_tuna" to "The Yellowfin Tuna, a fast-swimming, large, pelagic fish with a streamlined body and yellow-colored fins, highly valued in commercial and sport fishing.",
            "profile.item.hybrid-aquatic.bluefin_tuna" to "The Bluefin Tuna, a large, powerful tuna species known for its high-speed swimming and prized status in sushi cuisine, often subject to conservation efforts due to overfishing.",

        ).forEach { (key, profile) ->
            builder.add(key, profile)
        }

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
            HybridAquaticBlocks.STRAWBERRY_ANEMONE to "Strawberry Anemone",
            HybridAquaticBlocks.TUBE_SPONGE to "Tube Sponge",
            HybridAquaticBlocks.CRAB_POT to "Crab Pot",
            HybridAquaticBlocks.HYBRID_CRATE to "Hybrid Crate",
            HybridAquaticBlocks.OAK_CRATE to "Oak Crate",
            HybridAquaticBlocks.SPRUCE_CRATE to "Spruce Crate",
            HybridAquaticBlocks.BIRCH_CRATE to "Birch Crate",
            HybridAquaticBlocks.DARK_OAK_CRATE to "Dark Oak Crate",
            HybridAquaticBlocks.JUNGLE_CRATE to "Jungle Crate",
            HybridAquaticBlocks.ACACIA_CRATE to "Acacia Crate",
            HybridAquaticBlocks.MANGROVE_CRATE to "Mangrove Crate",
            HybridAquaticBlocks.CHERRY_CRATE to "Cherry Crate",
            HybridAquaticBlocks.BUOY to "Buoy",
            HybridAquaticBlocks.GIANT_CLAM to "Giant Clam",

            HybridAquaticBlocks.RED_ALGAE to "Red Algae",
            HybridAquaticBlocks.TALL_RED_ALGAE to "Tall Red Algae",

            HybridAquaticBlocks.SARGASSUM to "Sargassum",
            HybridAquaticBlocks.SARGASSUM_PLANT to "Sargassum Plant",
            HybridAquaticBlocks.FLOATING_SARGASSUM to "Floating Sargassum",
            HybridAquaticBlocks.WATER_LETTUCE to "Water Lettuce",
            HybridAquaticBlocks.JUNGLE_LILY_PAD to "Jungle Lily Pad",
            HybridAquaticBlocks.RAFT to "Raft",

            HybridAquaticBlocks.GLOWING_PLANKTON to "Glowing Plankton",

            HybridAquaticBlocks.SEA_LETTUCE to "Sea Lettuce",
            HybridAquaticBlocks.TALL_SEA_LETTUCE to "Tall Sea Lettuce",

            HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK to "Lophelia Coral Block",
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK to "Dead Lophelia Coral Block",
            HybridAquaticBlocks.LOPHELIA_CORAL to "Lophelia Coral",
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL to "Dead Lophelia Coral",
            HybridAquaticBlocks.LOPHELIA_CORAL_FAN to "Lophelia Coral Fan",
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN to "Dead Lophelia Coral Fan",
            HybridAquaticBlocks.BUTTON_CORAL_BLOCK to "Button Coral Block",
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK to "Dead Button Coral Block",
            HybridAquaticBlocks.BUTTON_CORAL to "Button Coral",
            HybridAquaticBlocks.DEAD_BUTTON_CORAL to "Dead Button Coral",
            HybridAquaticBlocks.BUTTON_CORAL_FAN to "Button Coral Fan",
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_FAN to "Dead Button Coral Fan",
            HybridAquaticBlocks.SUN_CORAL_BLOCK to "Sun Coral Block",
            HybridAquaticBlocks.DEAD_SUN_CORAL_BLOCK to "Dead Sun Coral Block",
            HybridAquaticBlocks.SUN_CORAL to "Sun Coral",
            HybridAquaticBlocks.DEAD_SUN_CORAL to "Dead Sun Coral",
            HybridAquaticBlocks.SUN_CORAL_FAN to "Sun Coral Fan",
            HybridAquaticBlocks.DEAD_SUN_CORAL_FAN to "Dead Sun Coral Fan",
            HybridAquaticBlocks.THORN_CORAL_BLOCK to "Thorn Coral Block",
            HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK to "Dead Thorn Coral Block",
            HybridAquaticBlocks.THORN_CORAL to "Thorn Coral",
            HybridAquaticBlocks.DEAD_THORN_CORAL to "Dead Thorn Coral",
            HybridAquaticBlocks.THORN_CORAL_FAN to "Thorn Coral Fan",
            HybridAquaticBlocks.DEAD_THORN_CORAL_FAN to "Dead Thorn Coral Fan",
            HybridAquaticBlocks.GLOWSTICK to "Glowstick",
            HybridAquaticBlocks.DRIFTWOOD_LOG to "Driftwood Log",
            HybridAquaticBlocks.DRIFTWOOD_WOOD to "Driftwood Wood",
            HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG to "Stripped Driftwood Log",
            HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD to "Stripped Driftwood Wood",
            HybridAquaticBlocks.DRIFTWOOD_PLANKS to "Driftwood Planks",
            HybridAquaticBlocks.DRIFTWOOD_STAIRS to "Driftwood Stairs",
            HybridAquaticBlocks.DRIFTWOOD_SLAB to "Driftwood Slab",
            HybridAquaticBlocks.DRIFTWOOD_FENCE to "Driftwood Fence",
            HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE to "Driftwood Fence Gate",
            HybridAquaticBlocks.DRIFTWOOD_DOOR to "Driftwood Door",
            HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR to "Driftwood Trapdoor",
            HybridAquaticBlocks.DRIFTWOOD_PRESSURE_PLATE to "Driftwood Pressure Plate",
            HybridAquaticBlocks.DRIFTWOOD_BUTTON to "Driftwood Button",
            HybridAquaticBlocks.THERMAL_VENT to "Thermal Vent",
            HybridAquaticBlocks.TUBE_WORM to "Tube Worm",
        ).forEach { (block, translation) ->
            builder.add(block, translation)
        }

        // items
        mapOf(
            HybridAquaticItems.UNI to "Uni",
            HybridAquaticItems.RAW_FISH_STEAK to "Fish Steak",
            HybridAquaticItems.COOKED_FISH_STEAK to "Cooked Fish Steak",
            HybridAquaticItems.RAW_FISH_MEAT to "Raw Fish Meat",
            HybridAquaticItems.COOKED_FISH_MEAT to "Cooked Fish Meat",
            HybridAquaticItems.RAW_TENTACLE to "Raw Tentacle",
            HybridAquaticItems.COOKED_TENTACLE to "Cooked Tentacle",
            HybridAquaticItems.RAW_CRAB to "Raw Crab",
            HybridAquaticItems.COOKED_CRAB to "Cooked Crab",
            HybridAquaticItems.RAW_LOBSTER to "Raw Lobster",
            HybridAquaticItems.COOKED_LOBSTER to "Cooked Lobster",
            HybridAquaticItems.RAW_LOBSTER_TAIL to "Raw Lobster Tail",
            HybridAquaticItems.COOKED_LOBSTER_TAIL to "Cooked Lobster Tail",
            HybridAquaticItems.RAW_SHRIMP to "Raw Shrimp",
            HybridAquaticItems.COOKED_SHRIMP to "Cooked Shrimp",
            HybridAquaticItems.RAW_CRAYFISH to "Raw Crayfish",
            HybridAquaticItems.COOKED_CRAYFISH to "Cooked Crayfish",
            HybridAquaticItems.LIONFISH to "Lionfish",
            HybridAquaticItems.NEON_TETRA to "Neon Tetra",
            HybridAquaticItems.DAMSELFISH to "Damselfish",
            HybridAquaticItems.DRAGONFISH to "Dragonfish",
            HybridAquaticItems.FLASHLIGHT_FISH to "Flashlight Fish",
            HybridAquaticItems.SQUIRRELFISH to "Squirrel Fish",
            HybridAquaticItems.COELACANTH to "Coelacanth",
            HybridAquaticItems.GOLDEN_DORADO to "Golden Dorado",
            HybridAquaticItems.MAHI to "Mahi",
            HybridAquaticItems.YELLOWFIN_TUNA to "Yellowfin Tuna",
            HybridAquaticItems.BLUEFIN_TUNA to "Bluefin Tuna",
            HybridAquaticItems.OPAH to "Opah",
            HybridAquaticItems.OARFISH to "Oarfish",
            HybridAquaticItems.ROCKFISH to "Rockfish",
            HybridAquaticItems.SEA_BASS to "Sea Bass",
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY to "Blue Spotted Stingray",
            HybridAquaticItems.SPOTTED_EAGLE_RAY to "Spotted Eagle Ray",
            HybridAquaticItems.SUNFISH to "Sunfish",
            HybridAquaticItems.TOADFISH to "Toadfish",
            HybridAquaticItems.PARROTFISH to "Parrotfish",
            HybridAquaticItems.STONEFISH to "Stonefish",
            HybridAquaticItems.SEAHORSE to "Seahorse",
            HybridAquaticItems.KOI to "Koi",
            HybridAquaticItems.GOLDFISH to "Goldfish",
            HybridAquaticItems.MORAY_EEL to "Moray Eel",
            HybridAquaticItems.NEEDLEFISH to "Needlefish",
            HybridAquaticItems.MACKEREL to "Mackerel",
            HybridAquaticItems.FLYING_FISH to "Flying Fish",
            HybridAquaticItems.PIRANHA to "Piranha",
            HybridAquaticItems.ANGLERFISH to "Anglerfish",
            HybridAquaticItems.CARP to "Carp",
            HybridAquaticItems.BARRELEYE to "Barreleye",
            HybridAquaticItems.BLUE_TANG to "Blue Tang",
            HybridAquaticItems.SURGEONFISH_SOHAL to "Sohal Surgeonfish",
            HybridAquaticItems.YELLOW_TANG to "Yellow Tang",
            HybridAquaticItems.POWDER_BLUE_TANG to "Powder Blue Tang",
            HybridAquaticItems.SURGEONFISH_ORANGESHOULDER to "Orangeshoulder Surgeonfish",
            HybridAquaticItems.SURGEONFISH_LINED to "Lined Surgeonfish",
            HybridAquaticItems.CLOWNFISH to "Clownfish",
            HybridAquaticItems.UNICORNFISH to "Unicornfish",
            HybridAquaticItems.TIGER_BARB to "Tiger Barb",
            HybridAquaticItems.OSCAR to "Oscar",
            HybridAquaticItems.TRIGGERFISH to "Triggerfish",
            HybridAquaticItems.DANIO to "Danio",
            HybridAquaticItems.BETTA to "Betta",
            HybridAquaticItems.PEARLFISH to "Pearlfish",
            HybridAquaticItems.SNAILFISH to "Snailfish",
            HybridAquaticItems.DISCUS to "Discus",
            HybridAquaticItems.GOURAMI to "Gourami",
            HybridAquaticItems.RATFISH to "Ratfish",
            HybridAquaticItems.BOXFISH to "Boxfish",
            HybridAquaticItems.LOBSTER_CLAW to "Lobster Claw",
            HybridAquaticItems.DUNGENESS_CRAB_CLAW to "Dungeness Crab Claw",
            HybridAquaticItems.COCONUT_CRAB_CLAW to "Coconut Crab Claw",
            HybridAquaticItems.FIDDLER_CRAB_CLAW to "Fiddler Crab Claw",
            HybridAquaticItems.YETI_CRAB_CLAW to "Yeti Crab Claw",
            HybridAquaticItems.LIGHTFOOT_CRAB_CLAW to "Lightfoot Crab Claw",
            HybridAquaticItems.GHOST_CRAB_CLAW to "Ghost Crab Claw",
            HybridAquaticItems.FLOWER_CRAB_CLAW to "Flower Crab Claw",
            HybridAquaticItems.VAMPIRE_CRAB_CLAW to "Vampire Crab Claw",
            HybridAquaticItems.SPIDER_CRAB_CLAW to "Spider Crab Claw",
            HybridAquaticItems.GLOW_SLIME to "Glow Slime",
            HybridAquaticItems.CUTTLEBONE to "Cuttlebone",
            HybridAquaticItems.SEA_URCHIN_SPINE to "Sea Urchin Spine",
            HybridAquaticItems.SHARK_TOOTH to "Shark Tooth",
            HybridAquaticItems.PEARL to "Pearl",
            HybridAquaticItems.BLACK_PEARL to "Black Pearl",
            HybridAquaticItems.SULFUR to "Sulfur",
            HybridAquaticItems.CORAL_CHUNK to "Coral Chunk",
            HybridAquaticItems.BARBED_HOOK to "Barbed Hook",
            HybridAquaticItems.GLOWING_HOOK to "Glowing Hook",
            HybridAquaticItems.MAGNETIC_HOOK to "Magnetic Hook",
            HybridAquaticItems.CREEPERMAGNET_HOOK to "CreeperMagnet Hook",
            HybridAquaticItems.OMINOUS_HOOK to "Ominous Hook",
            HybridAquaticItems.FISHING_NET to "Fishing Net",
            HybridAquaticItems.KARKINOS_CLAW to "Karkinos Claw",
            HybridAquaticItems.SEASHELL_SPEAR to "Seashell Spear",
            HybridAquaticItems.SEASHELL_PICKAXE to "Seashell Pickaxe",
            HybridAquaticItems.SEASHELL_AXE to "Seashell Axe",
            HybridAquaticItems.SEASHELL_SHOVEL to "Seashell Shovel",
            HybridAquaticItems.SEASHELL_HOE to "Seashell Hoe",
            HybridAquaticItems.CORAL_BLADE to "Coral Blade",
            HybridAquaticItems.CORAL_PICKAXE to "Coral Pickaxe",
            HybridAquaticItems.CORAL_AXE to "Coral Axe",
            HybridAquaticItems.CORAL_SHOVEL to "Coral Shovel",
            HybridAquaticItems.CORAL_HOE to "Coral Hoe",
            HybridAquaticItems.DIVING_HELMET to "Diving Helmet",
            HybridAquaticItems.DIVING_SUIT to "Diving Suit",
            HybridAquaticItems.DIVING_LEGGINGS to "Diving Leggings",
            HybridAquaticItems.DIVING_BOOTS to "Diving Boots",
            HybridAquaticItems.NAUTILUS_HELMET to "Nautilus Helmet",
            HybridAquaticItems.NAUTILUS_PAULDRONS to "Nautilus Pauldrons",
            HybridAquaticItems.MANGLERFISH_LURE to "Manglerfish Lure",
            HybridAquaticItems.MANGLERFISH_FIN to "Manglerfish Fin",
            HybridAquaticItems.EEL_SCARF to "Eel Scarf",
            HybridAquaticItems.TURTLE_CHESTPLATE to "Turtle Chestplate",
            HybridAquaticItems.MOON_JELLYFISH_HAT to "Moon Jellyfish Hat",
        ).forEach { (item, translation) ->
            builder.add(item, translation)
        }

        // effects
        mapOf(
            HybridAquaticStatusEffects.BLEEDING to "Bleeding",
            HybridAquaticStatusEffects.CLARITY to "Clarity",
            HybridAquaticStatusEffects.CORROSION to "Corrosion",
            HybridAquaticStatusEffects.THALASSOPHOBIA to "Thalassophobia",
            HybridAquaticStatusEffects.BUOYANCY to "Buoyancy",
            HybridAquaticStatusEffects.SPININESS to "Spininess",
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
            HybridAquaticItems.CREEPERMAGNET_HOOK.translationKey to "Don't use indoors",
            HybridAquaticItems.OMINOUS_HOOK.translationKey to "Summons Karkinos",
            HybridAquaticBlocks.CRAB_POT.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.HYBRID_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.OAK_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.SPRUCE_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.BIRCH_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.DARK_OAK_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.JUNGLE_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.MANGROVE_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.ACACIA_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.CHERRY_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticItems.FISHING_NET.translationKey to "Stored Entity: %s",
            HybridAquaticItems.MOON_JELLYFISH_HAT.translationKey to "Made by Jakotens",
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
            "glowing" to "Glowing",
            "clarity" to "Clarity",
            "corrosion" to "Corrosion",
            "thalassophobia" to "Thalassophobia",
            "bleeding" to "Bleeding",
            "swimming" to "Swimming",
            "buoyancy" to "Buoyancy",
            "spininess" to "Spininess",
            "minor_luck" to "Minor Luck",
            "major_luck" to "Major Luck",
            "blindness" to "Blindness",
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
            HybridAquaticEntityTypes.DAMSELFISH to "Damselfish",
            HybridAquaticEntityTypes.AFRICAN_BUTTERFLY to "African Butterfly Fish",
            HybridAquaticEntityTypes.ANGLERFISH to "Anglerfish",
            HybridAquaticEntityTypes.JOHN_DORY to "John Dory",
            HybridAquaticEntityTypes.SNAILFISH to "Snailfish",
            HybridAquaticEntityTypes.PEARLFISH to "Pearlfish",
            HybridAquaticEntityTypes.DRAGONFISH to "Dragonfish",
            HybridAquaticEntityTypes.BARRELEYE to "Barreleye",
            HybridAquaticEntityTypes.TUNA to "Tuna",
            HybridAquaticEntityTypes.CUTTLEFISH to "Cuttlefish",
            HybridAquaticEntityTypes.FLASHLIGHT_FISH to "Flashlight Fish",
            HybridAquaticEntityTypes.SQUIRRELFISH to "Squirrelfish",
            HybridAquaticEntityTypes.FLYING_FISH to "Flying Fish",
            HybridAquaticEntityTypes.LIONFISH to "Lionfish",
            HybridAquaticEntityTypes.COELACANTH to "Coelacanth",
            HybridAquaticEntityTypes.OARFISH to "Oarfish",
            HybridAquaticEntityTypes.OPAH to "Opah",
            HybridAquaticEntityTypes.PIRANHA to "Piranha",
            HybridAquaticEntityTypes.SEA_ANGEL to "Sea Angel",
            HybridAquaticEntityTypes.SUNFISH to "Sunfish",
            HybridAquaticEntityTypes.PARROTFISH to "Parrotfish",
            HybridAquaticEntityTypes.VAMPIRE_SQUID to "Vampire Squid",
            HybridAquaticEntityTypes.MAHI to "Mahi",
            HybridAquaticEntityTypes.GOLDEN_DORADO to "Golden Dorado",
            HybridAquaticEntityTypes.MORAY_EEL to "Moray Eel",
            HybridAquaticEntityTypes.ROCKFISH to "Rockfish",
            HybridAquaticEntityTypes.SEA_BASS to "Sea Bass",
            HybridAquaticEntityTypes.TIGER_BARB to "Tiger Barb",
            HybridAquaticEntityTypes.CARP to "Carp",
            HybridAquaticEntityTypes.NEEDLEFISH to "Needlefish",
            HybridAquaticEntityTypes.MACKEREL to "Mackerel",
            HybridAquaticEntityTypes.RATFISH to "Ratfish",
            HybridAquaticEntityTypes.NAUTILUS to "Nautilus",
            HybridAquaticEntityTypes.UMBRELLA_OCTOPUS to "Umbrella Octopus",
            HybridAquaticEntityTypes.TRIGGERFISH to "Triggerfish",
            HybridAquaticEntityTypes.OSCAR to "Oscar",
            HybridAquaticEntityTypes.DANIO to "Danio",
            HybridAquaticEntityTypes.TOADFISH to "Toadfish",
            HybridAquaticEntityTypes.TETRA to "Tetra",
            HybridAquaticEntityTypes.STONEFISH to "Stonefish",
            HybridAquaticEntityTypes.BETTA to "Betta",
            HybridAquaticEntityTypes.GOLDFISH to "Goldfish",
            HybridAquaticEntityTypes.SEAHORSE to "Seahorse",
            HybridAquaticEntityTypes.MOON_JELLYFISH to "Moon Jellyfish",
            HybridAquaticEntityTypes.GOURAMI to "Gourami",
            HybridAquaticEntityTypes.BOXFISH to "Boxfish",
            HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS to "Glowing Sucker Octopus",
            HybridAquaticEntityTypes.DISCUS to "Discus",
            HybridAquaticEntityTypes.ARROW_SQUID to "Arrow Squid",
            HybridAquaticEntityTypes.FIREFLY_SQUID to "Firefly Squid",
            HybridAquaticEntityTypes.STINGRAY to "Stingray",
            HybridAquaticEntityTypes.MANTA_RAY to "Manta Ray",
            HybridAquaticEntityTypes.SURGEONFISH to "Surgeonfish",
            HybridAquaticEntityTypes.BULL_SHARK to "Bull Shark",
            HybridAquaticEntityTypes.BASKING_SHARK to "Basking Shark",
            HybridAquaticEntityTypes.THRESHER_SHARK to "Thresher Shark",
            HybridAquaticEntityTypes.FRILLED_SHARK to "Frilled Shark",
            HybridAquaticEntityTypes.LANTERN_SHARK to "Lantern Shark",
            HybridAquaticEntityTypes.GREAT_WHITE_SHARK to "Great White Shark",
            HybridAquaticEntityTypes.TIGER_SHARK to "Tiger Shark",
            HybridAquaticEntityTypes.HAMMERHEAD_SHARK to "Hammerhead Shark",
            HybridAquaticEntityTypes.WHALE_SHARK to "Whale Shark",
            HybridAquaticEntityTypes.KARKINOS to "Karkinos",
            HybridAquaticEntityTypes.DUNGENESS_CRAB to "Crab",
            HybridAquaticEntityTypes.FIDDLER_CRAB to "Fiddler Crab",
            HybridAquaticEntityTypes.HERMIT_CRAB to "Hermit Crab",
            HybridAquaticEntityTypes.GHOST_CRAB to "Ghost Crab",
            HybridAquaticEntityTypes.LIGHTFOOT_CRAB to "Lightfoot Crab",
            HybridAquaticEntityTypes.FLOWER_CRAB to "Flower Crab",
            HybridAquaticEntityTypes.VAMPIRE_CRAB to "Vampire Crab",
            HybridAquaticEntityTypes.SPIDER_CRAB to "Spider Crab",
            HybridAquaticEntityTypes.YETI_CRAB to "Yeti Crab",
            HybridAquaticEntityTypes.DECORATOR_CRAB to "Decorator Crab",
            HybridAquaticEntityTypes.COCONUT_CRAB to "Coconut Crab",
            HybridAquaticEntityTypes.HORSESHOE_CRAB to "Horseshoe Crab",
            HybridAquaticEntityTypes.GIANT_ISOPOD to "Giant Isopod",
            HybridAquaticEntityTypes.SHRIMP to "Shrimp",
            HybridAquaticEntityTypes.CRAYFISH to "Crayfish",
            HybridAquaticEntityTypes.LOBSTER to "Lobster",
            HybridAquaticEntityTypes.NUDIBRANCH to "Nudibranch",
            HybridAquaticEntityTypes.SEA_CUCUMBER to "Sea Cucumber",
            HybridAquaticEntityTypes.SEA_URCHIN to "Sea Urchin",
            HybridAquaticEntityTypes.STARFISH to "Starfish",
            HybridAquaticEntityTypes.SEA_NETTLE to "Sea Nettle",
            HybridAquaticEntityTypes.BOX_JELLYFISH to "Box Jellyfish",
            HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH to "Fried Egg Jellyfish",
            HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH to "Cauliflower Jellyfish",
            HybridAquaticEntityTypes.NOMURA_JELLYFISH to "Nomura Jellyfish",
            HybridAquaticEntityTypes.BARREL_JELLYFISH to "Barrel Jellyfish",
            HybridAquaticEntityTypes.COMPASS_JELLYFISH to "Compass Jellyfish",
            HybridAquaticEntityTypes.MAUVE_STINGER to "Mauve Stinger",
            HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH to "Lion's Mane Jellyfish",
            HybridAquaticEntityTypes.ATOLLA_JELLYFISH to "Atolla Jellyfish",
            HybridAquaticEntityTypes.BIG_RED_JELLYFISH to "Big Red Jellyfish",
            HybridAquaticEntityTypes.COSMIC_JELLYFISH to "Cosmic Jellyfish",
            HybridAquaticEntityTypes.FIREWORK_JELLYFISH to "Firework Jellyfish",
            HybridAquaticEntityTypes.BLUE_JELLYFISH to "Blue Jellyfish",
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