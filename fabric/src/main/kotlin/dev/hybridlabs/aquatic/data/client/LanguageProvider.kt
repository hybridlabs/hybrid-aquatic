package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.data.server.seamessage.SeaMessageProvider
import dev.hybridlabs.aquatic.effect.HAMobEffects
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.item.HAItemGroups
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.Util
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob

class LanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {
    override fun generateTranslations(builder: TranslationBuilder) {
        // item group
        builder.add(
            BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(HAItemGroups.BLOCKS.get())
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Blocks"
        )

        builder.add(
            BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(HAItemGroups.ITEMS.get())
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Items"
        )

        builder.add(
            BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(HAItemGroups.SPAWN_EGGS.get())
                .orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Spawn Eggs"
        )

        // message in a bottle
        HABlocks.MESSAGE_IN_A_BOTTLE.get().descriptionId.let { key ->
            builder.add(key, "Message in a Bottle")
            builder.add("$key.jar", "Message in a Jar")
            builder.add("$key.longneck", "Message in a Longneck Bottle")
            builder.add("$key.potion", "Message in a Potion Bottle")
            builder.add("$key.wine", "Message in a Wine Bottle")
        }

        // sea messages
        SeaMessageProvider.BUILT_IN.forEach { message ->
            builder.add(message.translationKey, message.englishText)
            message.englishTitle?.let { title -> builder.add(message.titleTranslationKey, title) }
        }

        builder.add(HAItems.SEA_MESSAGE_BOOK.get(), "Sea Message")

        //advancements
        mapOf(
            "advancements.hybrid-aquatic.enter_water.title" to "Hybrid Aquatic",
            "advancements.hybrid-aquatic.enter_water.description" to "Discover an expanded world beneath the waves",

            "advancements.hybrid-aquatic.fishing_net.title" to "Not Quite A Bucket",
            "advancements.hybrid-aquatic.fishing_net.description" to "Craft a fishing net to pick up and transport sea creatures",

            "advancements.hybrid-aquatic.glowstick.title" to "Better Than Torches!",
            "advancements.hybrid-aquatic.glowstick.description" to "Craft a glowstick to light your way in the deep sea",

            "advancements.hybrid-aquatic.sulfur.title" to "Not Glowstone Dust",
            "advancements.hybrid-aquatic.sulfur.description" to "Find sulfur in a sulfuric cave",

            "advancements.hybrid-aquatic.depth_charge.title" to "Mining Fatigue?",
            "advancements.hybrid-aquatic.depth_charge.description" to "Craft a depth charge",

            "advancements.hybrid-aquatic.buoy.title" to "Oh Buoy!",
            "advancements.hybrid-aquatic.buoy.description" to "Craft a buoy to guide sailors across the sea",

            "advancements.hybrid-aquatic.coral_chunk.title" to "That's Not A Sheep",
            "advancements.hybrid-aquatic.coral_chunk.description" to "Shear a decorator crab to get a coral chunk",

            "advancements.hybrid-aquatic.coral_tools.title" to "Who Needs Mending?",
            "advancements.hybrid-aquatic.coral_tools.description" to "Craft a tool out of coral chunks",

            "advancements.hybrid-aquatic.get_clam.title" to "Happy As A Clam",
            "advancements.hybrid-aquatic.get_clam.description" to "Feed a dugong some sea lettuce to get a clam",

            "advancements.hybrid-aquatic.plant_clam.title" to "Shell Corporation",
            "advancements.hybrid-aquatic.plant_clam.description" to "Grow your own clams underwater",

            "advancements.hybrid-aquatic.nautilus_shell.title" to "Someone Used To Live Here",
            "advancements.hybrid-aquatic.nautilus_shell.description" to "Obtain a nautilus shell",

            "advancements.hybrid-aquatic.seashell_tools.title" to "Shell Yeah!",
            "advancements.hybrid-aquatic.seashell_tools.description" to "Craft a tool out of nautilus shells",

            "advancements.hybrid-aquatic.ominous_conch.title" to "If You Listen Closely..",
            "advancements.hybrid-aquatic.ominous_conch.description" to "Trade with a hermit crab for an Ominous Shell",

            "advancements.hybrid-aquatic.shell_beast.title" to "Shell-Shocked",
            "advancements.hybrid-aquatic.shell_beast.description" to "Kill the Shell Beast",

            "advancements.hybrid-aquatic.argonaut.title" to "This Boat Has Fins",
            "advancements.hybrid-aquatic.argonaut.description" to "Build the Argonaut",

            "advancements.hybrid-aquatic.conduit.title" to "Better Than Gills",
            "advancements.hybrid-aquatic.conduit.description" to "Craft a conduit",

            "advancements.hybrid-aquatic.turtle_scute.title" to "They Grow Up So Fast!",
            "advancements.hybrid-aquatic.turtle_scute.description" to "Obtain a turtle scute",

            "advancements.hybrid-aquatic.turtle_set.title" to "Cowabunga!",
            "advancements.hybrid-aquatic.turtle_set.description" to "Craft a piece of turtle armor",

            "advancements.hybrid-aquatic.diving_weight.title" to "The Fast Way Down",
            "advancements.hybrid-aquatic.diving_weight.description" to "Craft a diving weight",

            "advancements.hybrid-aquatic.diving_suit.title" to "Diving In",
            "advancements.hybrid-aquatic.diving_suit.description" to "Obtain a full set of diving gear",

            "advancements.hybrid-aquatic.diving_upgrade.title" to "Beachcombing",
            "advancements.hybrid-aquatic.diving_upgrade.description" to "Find a diving suit upgrade template",

            "advancements.hybrid-aquatic.reinforced_diving_suit.title" to "Diving Deeper",
            "advancements.hybrid-aquatic.reinforced_diving_suit.description" to "Reinforce your diving gear",

            "advancements.hybrid-aquatic.glowing_diving_suit.title" to "The Light In The Abyss",
            "advancements.hybrid-aquatic.glowing_diving_suit.description" to "Make your diving suit glow in the dark",

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

            "advancements.hybrid-aquatic.bigger_boat.title" to "We're Gonna Need A Bigger Boat",
            "advancements.hybrid-aquatic.bigger_boat.description" to "Block a shark attack with a shield to get a shark tooth",

            "advancements.hybrid-aquatic.trident.title" to "Poseidon Quivers Before Him!",
            "advancements.hybrid-aquatic.trident.description" to "Obtain a trident",
        ).forEach { (key, translation) ->
            builder.add(key, translation)
        }
        //-advancements

        //Sound Events
        mapOf(
            HASoundEvents.MANATEE_AMBIENT to "Manatee snorts",
            HASoundEvents.MANATEE_HURT to "Manatee hurts",
            HASoundEvents.MANATEE_DIE to "Manatee dies",
            HASoundEvents.MANATEE_SWIM to "Manatee swims",
            HASoundEvents.MANATEE_SPLASH to "Manatee splashes",

            HASoundEvents.DUGONG_AMBIENT to "Dugong chirps",
            HASoundEvents.DUGONG_HURT to "Dugong hurts",
            HASoundEvents.DUGONG_DIE to "Dugong dies",
            HASoundEvents.DUGONG_SWIM to "Dugong swims",
            HASoundEvents.DUGONG_SPLASH to "Dugong splashes",

            HASoundEvents.SIRENIAN_EAT to "Sirenian eats",

            HASoundEvents.KARKINOS_AMBIENT to "Karkinos chitters",
            HASoundEvents.KARKINOS_HURT to "Karkinos hurts",
            HASoundEvents.KARKINOS_DIE to "Karkinos dies",

            HASoundEvents.KARCINOMA_AMBIENT to "Karcinoma chitters",
            HASoundEvents.KARCINOMA_HURT to "Karcinoma hurts",
            HASoundEvents.KARCINOMA_DIE to "Karcinoma dies",

            HASoundEvents.KARCINOGEN_AMBIENT to "Karcinogen chitters",
            HASoundEvents.KARCINOGEN_HURT to "Karcinogen hurts",
            HASoundEvents.KARCINOGEN_DIE to "Karcinogen dies",

            HASoundEvents.SHELL_BEAST_SHOOT to "Shell Beast fires",
            HASoundEvents.SHELL_BEAST_AMBIENT to "Shell Beast chitters",
            HASoundEvents.SHELL_BEAST_HURT to "Shell Beast hurts",
            HASoundEvents.SHELL_BEAST_DIE to "Shell Beast dies",

            HASoundEvents.HYPNAUTILUS_AMBIENT to "Hypnautilus spirals",
            HASoundEvents.HYPNAUTILUS_HURT to "Hypnautilus hurts",
            HASoundEvents.HYPNAUTILUS_DIE to "Hypnautilus dies",

            HASoundEvents.OMINOUS_CONCH_BLOWS to "Ominous Conch plays"
        ).forEach { (soundEvent, translation) ->
            builder.add(Util.makeDescriptionId("subtitles", soundEvent.get().location), translation)
        }
        //-Sound Events

        mapOf(
            "journal.description.hybrid-aquatic.anglerfish" to "A deep-sea predator that lures unwary prey with a glowing bulb",
            "journal.description.hybrid-aquatic.barreleye" to "With a transparent head and upward-gazing eyes, it watches the waters above.",
            "journal.description.hybrid-aquatic.boxfish" to "The Boxfish, a small, square-shaped fish with a rigid, box-like body, known for its ability to release toxins when stressed.",
            "journal.description.hybrid-aquatic.betta" to "The Betta, also known as the Siamese fighting fish, is a colorful, territorial freshwater fish known for its vibrant fins and aggressive behavior towards other males.",
            "journal.description.hybrid-aquatic.carp" to "A resilient and adaptable fish, sometimes displaying beautiful patterns and colors",
            "journal.description.hybrid-aquatic.danio" to "The Danio, a small, colorful freshwater fish known for its active swimming behavior and popularity in home aquariums.",
            "journal.description.hybrid-aquatic.discus" to "The Discus, a vibrant, round-shaped freshwater fish admired for its striking patterns and colors, often kept in aquariums for its beauty.",
            "journal.description.hybrid-aquatic.dragonfish" to "The Dragonfish, a deep-sea predator with sharp teeth and bioluminescent photophores, known for its ability to produce light to attract prey and communicate in the dark ocean depths.",
            "journal.description.hybrid-aquatic.golden_dorado" to "The Golden Dorado, a large, powerful freshwater fish native to South America, prized by anglers for its strength and golden scales.",
            "journal.description.hybrid-aquatic.gourami" to "The Gourami, a diverse group of freshwater fish, recognized for their labyrinth organ allowing them to breathe air and their peaceful nature in community tanks.",
            "journal.description.hybrid-aquatic.mackerel" to "The Mackerel, a fast-swimming, pelagic fish with streamlined bodies, valued for its role in the food chain and commercial fishing.",
            "journal.description.hybrid-aquatic.moray_eel" to "The Moray Eel, a long, slender predator with a snake-like body and sharp teeth, known for hiding in crevices in reefs and ambushing prey.",
            "journal.description.hybrid-aquatic.oscar" to "The Oscar, a large, aggressive freshwater cichlid fish, popular in aquariums for its intelligence and striking patterns.",
            "journal.description.hybrid-aquatic.pearlfish" to "A secretive dweller that shelters within living hosts.",
            "journal.description.hybrid-aquatic.piranha" to "The Piranha, a notorious freshwater fish known for its sharp teeth and powerful bite, often exaggerated for its feeding frenzy behavior.",
            "journal.description.hybrid-aquatic.rockfish" to "The Rockfish, a long-lived marine fish with venomous spines, known for its camouflage abilities and preference for rocky sea floors.",
            "journal.description.hybrid-aquatic.sea_bass" to "The Sea Bass, a popular game and commercial fish, recognized for its firm texture and mild flavor, often found in coastal waters.",
            "journal.description.hybrid-aquatic.snailfish" to "The Snailfish, a soft-bodied, deep-sea fish adapted to extreme pressures, often found clinging to rocks or ice with its pelvic fins.",
            "journal.description.hybrid-aquatic.squirrelfish" to "The Squirrelfish, a nocturnal reef fish with large eyes and a bright red body, known for its loud vocalizations produced by grinding its teeth.",
            "journal.description.hybrid-aquatic.blue_spotted_stingray" to "The Blue-Spotted Stingray, a striking marine fish known for its vibrant blue spots and flattened body, often found gliding along sandy sea floors.",
            "journal.description.hybrid-aquatic.spotted_eagle_ray" to "The Spotted Eagle Ray, a graceful, large ray recognized by its dark body covered in white spots and long, whip-like tail, often seen swimming near the surface in tropical waters.",
            "journal.description.hybrid-aquatic.stonefish" to "Nearly invisible among rock, armed with venomous spines.",
            "journal.description.hybrid-aquatic.ocean_sunfish" to "The Ocean Sunfish, also known as the Mola, is one of the heaviest bony fish, recognizable by its flattened, disk-like body and tendency to bask near the ocean surface.",
            "journal.description.hybrid-aquatic.surgeonfish" to "The Surgeonfish, a vibrant, reef-dwelling fish known for its bright colors, popularized by its role in marine ecosystems as an algae grazer.",
            "journal.description.hybrid-aquatic.neon_tetra" to "The Neon Tetra, a small, brightly colored freshwater fish known for its iridescent blue and red stripes, making it a popular choice for home aquariums.",
            "journal.description.hybrid-aquatic.tiger_barb" to "The Tiger Barb, a lively, freshwater fish recognized by its bold black stripes over an orange-gold body, often kept in groups in community tanks.",
            "journal.description.hybrid-aquatic.toadfish" to "The Toadfish, a small, bottom-dwelling fish with a flattened body and rough skin, commonly found in coastal waters and known for its toxicity.",
            "journal.description.hybrid-aquatic.triggerfish" to "The Triggerfish, a brightly colored, reef-dwelling fish known for its strong jaws, sharp teeth, and the ability to lock its dorsal fin in an upright position for defense.",
            "journal.description.hybrid-aquatic.tuna" to "A tireless swimmer built for speed and open water pursuit.",

            ).forEach { (key, profile) ->
            builder.add(key, profile)
        }

        // entities
        generateEntities(builder)

        // blocks
        mapOf(
            HABlocks.BASKING_SHARK_PLUSHIE.get() to "Basking Shark Plushie",
            HABlocks.BULL_SHARK_PLUSHIE.get() to "Bull Shark Plushie",
            HABlocks.FRILLED_SHARK_PLUSHIE.get() to "Frilled Shark Plushie",
            HABlocks.GREAT_WHITE_SHARK_PLUSHIE.get() to "Great White Shark Plushie",
            HABlocks.HAMMERHEAD_SHARK_PLUSHIE.get() to "Hammerhead Shark Plushie",
            HABlocks.THRESHER_SHARK_PLUSHIE.get() to "Thresher Shark Plushie",
            HABlocks.TIGER_SHARK_PLUSHIE.get() to "Tiger Shark Plushie",
            HABlocks.WHALE_SHARK_PLUSHIE.get() to "Whale Shark Plushie",
            HABlocks.ANEMONE.get() to "Anemone",
            HABlocks.GIANT_GREEN_ANEMONE.get() to "Giant Green Anemone",
            HABlocks.STRAWBERRY_ANEMONE.get() to "Strawberry Anemone",
            HABlocks.TUBE_SPONGE.get() to "Tube Sponge",
            HABlocks.HARP_SPONGE.get() to "Harp Sponge",
            HABlocks.PING_PONG_SPONGE.get() to "Ping Pong Sponge",
            HABlocks.GLASS_SPONGE.get() to "Glass Sponge",
            HABlocks.CRAB_POT.get() to "Crab Pot",
            HABlocks.HYBRID_CRATE.get() to "Hybrid Crate",
            HABlocks.OAK_CRATE.get() to "Oak Crate",
            HABlocks.SPRUCE_CRATE.get() to "Spruce Crate",
            HABlocks.BIRCH_CRATE.get() to "Birch Crate",
            HABlocks.DARK_OAK_CRATE.get() to "Dark Oak Crate",
            HABlocks.JUNGLE_CRATE.get() to "Jungle Crate",
            HABlocks.ACACIA_CRATE.get() to "Acacia Crate",
            HABlocks.MANGROVE_CRATE.get() to "Mangrove Crate",
            HABlocks.CHERRY_CRATE.get() to "Cherry Crate",
            HABlocks.BAMBOO_CRATE.get() to "Bamboo Crate",
            HABlocks.GRASSY_SAND.get() to "Grassy Sand",
            HABlocks.AERATED_SAND.get() to "Aerated Sand",
            HABlocks.BUBBLE_GEYSER.get() to "Bubble Geyser",
            HABlocks.WHITE_SAND.get() to "White Sand",
            HABlocks.WHITE_SANDSTONE.get() to "White Sandstone",
            HABlocks.WHITE_SANDSTONE_STAIRS.get() to "White Sandstone Stairs",
            HABlocks.WHITE_SANDSTONE_SLAB.get() to "White Sandstone Slab",
            HABlocks.WHITE_SANDSTONE_WALL.get() to "White Sandstone Wall",
            HABlocks.SMOOTH_WHITE_SANDSTONE.get() to "Smooth White Sandstone",
            HABlocks.SMOOTH_WHITE_SANDSTONE_SLAB.get() to "Smooth White Sandstone Slab",
            HABlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.get() to "Smooth White Sandstone Stairs",
            HABlocks.CUT_WHITE_SANDSTONE.get() to "Cut White Sandstone",
            HABlocks.CUT_WHITE_SANDSTONE_SLAB.get() to "Cut White Sandstone Slab",
            HABlocks.CHISELED_WHITE_SANDSTONE.get() to "Chiseled White Sandstone",
            HABlocks.BONE_STAIRS.get() to "Bone Stairs",
            HABlocks.BONE_SLAB.get() to "Bone Slab",
            HABlocks.BONE_WALL.get() to "Bone Wall",
            HABlocks.BONE_FENCE.get() to "Bone Fence",
            HABlocks.SUSPICIOUS_RED_SAND.get() to "Suspicious Red Sand",
            HABlocks.CORALSTONE.get() to "Coralstone",
            HABlocks.SHORESTONE.get() to "Shorestone",
            HABlocks.BARNACLE_SHORESTONE.get() to "Barnacle Shorestone",
            HABlocks.MARINE_SNOW.get() to "Marine Snow",
            HABlocks.BUOY.get() to "Buoy",
            HABlocks.BELL_BUOY.get() to "Bell Buoy",
            HABlocks.GIANT_CLAM.get() to "Giant Clam",
            HABlocks.OYSTER.get() to "Oyster",
            HABlocks.CLAMS.get() to "Clam",
            HABlocks.CRYSTALLINE_SULFUR.get() to "Crystalline Sulfur",
            HABlocks.DEPTH_CHARGE.get() to "Depth Charge",

            HABlocks.SHORT_RED_ALGAE.get() to "Short Red Algae",
            HABlocks.RED_ALGAE.get() to "Red Algae",
            HABlocks.TALL_RED_ALGAE.get() to "Tall Red Algae",

            HAPlatformBlocks.DUNEGRASS.get() to "Dunegrass",
            HAPlatformBlocks.TALL_DUNEGRASS.get() to "Tall Dunegrass",

            HAPlatformBlocks.CATTAIL.get() to "Cattail",

            HABlocks.SARGASSUM.get() to "Sargassum",
            HABlocks.SARGASSUM_PLANT.get() to "Sargassum Plant",
            HABlocks.BULL_KELP.get() to "Bull Kelp",
            HABlocks.BULL_KELP_PLANT.get() to "Bull Kelp Plant",
            HABlocks.DELESSERIA.get() to "Delesseria",
            HABlocks.DELESSERIA_PLANT.get() to "Delesseria Plant",
            HABlocks.FLOATING_SARGASSUM.get() to "Floating Sargassum",
            HABlocks.WATER_LETTUCE.get() to "Water Lettuce",
            HABlocks.WATER_HYACINTH.get() to "Water Hyacinth",
            HABlocks.JUNGLE_LILY_PAD.get() to "Jungle Lily Pad",
            HABlocks.RAFT.get() to "Raft",
            HABlocks.OAK_RAFT.get() to "Oak Raft",
            HABlocks.SPRUCE_RAFT.get() to "Spruce Raft",
            HABlocks.BIRCH_RAFT.get() to "Birch Raft",
            HABlocks.DARK_OAK_RAFT.get() to "Dark Oak Raft",
            HABlocks.JUNGLE_RAFT.get() to "Jungle Raft",
            HABlocks.ACACIA_RAFT.get() to "Acacia Raft",
            HABlocks.MANGROVE_RAFT.get() to "Mangrove Raft",
            HABlocks.CHERRY_RAFT.get() to "Cherry Raft",
            HABlocks.DRIFTWOOD_RAFT.get() to "Driftwood Raft",

            HABlocks.GLOWING_PLANKTON.get() to "Glowing Plankton",

            HABlocks.SEA_LETTUCE.get() to "Sea Lettuce",
            HABlocks.TALL_SEA_LETTUCE.get() to "Tall Sea Lettuce",

            HABlocks.BONE_WORMS.get() to "Bone Worms",

            //#region Corals
            HABlocks.LOPHELIA_CORAL_BLOCK.get() to "Lophelia Coral Block",
            HABlocks.DEAD_LOPHELIA_CORAL_BLOCK.get() to "Dead Lophelia Coral Block",
            HABlocks.BLEACHED_LOPHELIA_CORAL_BLOCK.get() to "Bleached Lophelia Coral Block",
            HABlocks.LOPHELIA_CORAL.get() to "Lophelia Coral",
            HABlocks.DEAD_LOPHELIA_CORAL.get() to "Dead Lophelia Coral",
            HABlocks.BLEACHED_LOPHELIA_CORAL.get() to "Bleached Lophelia Coral",
            HABlocks.LOPHELIA_CORAL_FAN.get() to "Lophelia Coral Fan",
            HABlocks.DEAD_LOPHELIA_CORAL_FAN.get() to "Dead Lophelia Coral Fan",
            HABlocks.BLEACHED_LOPHELIA_CORAL_FAN.get() to "Bleached Lophelia Coral Fan",
            
            HABlocks.BAMBOO_CORAL_BLOCK.get() to "Bamboo Coral Block",
            HABlocks.DEAD_BAMBOO_CORAL_BLOCK.get() to "Dead Bamboo Coral Block",
            HABlocks.BLEACHED_BAMBOO_CORAL_BLOCK.get() to "Bleached Bamboo Coral Block",
            HABlocks.BAMBOO_CORAL.get() to "Bamboo Coral",
            HABlocks.DEAD_BAMBOO_CORAL.get() to "Dead Bamboo Coral",
            HABlocks.BLEACHED_BAMBOO_CORAL.get() to "Bleached Bamboo Coral",
            HABlocks.BAMBOO_CORAL_FAN.get() to "Bamboo Coral Fan",
            HABlocks.DEAD_BAMBOO_CORAL_FAN.get() to "Dead Bamboo Coral Fan",
            HABlocks.BLEACHED_BAMBOO_CORAL_FAN.get() to "Bleached Bamboo Coral Fan",

            HABlocks.ROSE_CORAL_BLOCK.get() to "Rose Coral Block",
            HABlocks.DEAD_ROSE_CORAL_BLOCK.get() to "Dead Rose Coral Block",
            HABlocks.BLEACHED_ROSE_CORAL_BLOCK.get() to "Bleached Rose Coral Block",
            HABlocks.ROSE_CORAL.get() to "Rose Coral",
            HABlocks.DEAD_ROSE_CORAL.get() to "Dead Rose Coral",
            HABlocks.BLEACHED_ROSE_CORAL.get() to "Bleached Rose Coral",
            HABlocks.ROSE_CORAL_FAN.get() to "Rose Coral Fan",
            HABlocks.DEAD_ROSE_CORAL_FAN.get() to "Dead Rose Coral Fan",
            HABlocks.BLEACHED_ROSE_CORAL_FAN.get() to "Bleached Rose Coral Fan",

            HABlocks.LEAF_CORAL_BLOCK.get() to "Leaf Coral Block",
            HABlocks.DEAD_LEAF_CORAL_BLOCK.get() to "Dead Leaf Coral Block",
            HABlocks.BLEACHED_LEAF_CORAL_BLOCK.get() to "Bleached Leaf Coral Block",
            HABlocks.LEAF_CORAL.get() to "Leaf Coral",
            HABlocks.DEAD_LEAF_CORAL.get() to "Dead Leaf Coral",
            HABlocks.BLEACHED_LEAF_CORAL.get() to "Bleached Leaf Coral",
            HABlocks.LEAF_CORAL_FAN.get() to "Leaf Coral Fan",
            HABlocks.DEAD_LEAF_CORAL_FAN.get() to "Dead Leaf Coral Fan",
            HABlocks.BLEACHED_LEAF_CORAL_FAN.get() to "Bleached Leaf Coral Fan",

            HABlocks.BUTTON_CORAL_BLOCK.get() to "Button Coral Block",
            HABlocks.DEAD_BUTTON_CORAL_BLOCK.get() to "Dead Button Coral Block",
            HABlocks.BLEACHED_BUTTON_CORAL_BLOCK.get() to "Bleached Button Coral Block",
            HABlocks.BUTTON_CORAL.get() to "Button Coral",
            HABlocks.DEAD_BUTTON_CORAL.get() to "Dead Button Coral",
            HABlocks.BLEACHED_BUTTON_CORAL.get() to "Bleached Button Coral",
            HABlocks.BUTTON_CORAL_FAN.get() to "Button Coral Fan",
            HABlocks.DEAD_BUTTON_CORAL_FAN.get() to "Dead Button Coral Fan",
            HABlocks.BLEACHED_BUTTON_CORAL_FAN.get() to "Bleached Button Coral Fan",

            HABlocks.ZIGZAG_CORAL_BLOCK.get() to "Zigzag Coral Block",
            HABlocks.DEAD_ZIGZAG_CORAL_BLOCK.get() to "Dead Zigzag Coral Block",
            HABlocks.BLEACHED_ZIGZAG_CORAL_BLOCK.get() to "Bleached Zigzag Coral Block",
            HABlocks.ZIGZAG_CORAL.get() to "Zigzag Coral",
            HABlocks.DEAD_ZIGZAG_CORAL.get() to "Dead Zigzag Coral",
            HABlocks.BLEACHED_ZIGZAG_CORAL.get() to "Bleached Zigzag Coral",
            HABlocks.ZIGZAG_CORAL_FAN.get() to "Zigzag Coral Fan",
            HABlocks.DEAD_ZIGZAG_CORAL_FAN.get() to "Dead Zigzag Coral Fan",
            HABlocks.BLEACHED_ZIGZAG_CORAL_FAN.get() to "Bleached Zigzag Coral Fan",

            HABlocks.SUN_CORAL_BLOCK.get() to "Sun Coral Block",
            HABlocks.DEAD_SUN_CORAL_BLOCK.get() to "Dead Sun Coral Block",
            HABlocks.BLEACHED_SUN_CORAL_BLOCK.get() to "Bleached Sun Coral Block",
            HABlocks.SUN_CORAL.get() to "Sun Coral",
            HABlocks.DEAD_SUN_CORAL.get() to "Dead Sun Coral",
            HABlocks.BLEACHED_SUN_CORAL.get() to "Bleached Sun Coral",
            HABlocks.SUN_CORAL_FAN.get() to "Sun Coral Fan",
            HABlocks.DEAD_SUN_CORAL_FAN.get() to "Dead Sun Coral Fan",
            HABlocks.BLEACHED_SUN_CORAL_FAN.get() to "Bleached Sun Coral Fan",

            HABlocks.THORN_CORAL_BLOCK.get() to "Thorn Coral Block",
            HABlocks.DEAD_THORN_CORAL_BLOCK.get() to "Dead Thorn Coral Block",
            HABlocks.BLEACHED_THORN_CORAL_BLOCK.get() to "Bleached Thorn Coral Block",
            HABlocks.THORN_CORAL.get() to "Thorn Coral",
            HABlocks.DEAD_THORN_CORAL.get() to "Dead Thorn Coral",
            HABlocks.BLEACHED_THORN_CORAL.get() to "Bleached Thorn Coral",
            HABlocks.THORN_CORAL_FAN.get() to "Thorn Coral Fan",
            HABlocks.DEAD_THORN_CORAL_FAN.get() to "Dead Thorn Coral Fan",
            HABlocks.BLEACHED_THORN_CORAL_FAN.get() to "Bleached Thorn Coral Fan",

            HABlocks.BLEACHED_FIRE_CORAL_BLOCK.get() to "Bleached Fire Coral Block",
            HABlocks.BLEACHED_FIRE_CORAL.get() to "Bleached Fire Coral",
            HABlocks.BLEACHED_FIRE_CORAL_FAN.get() to "Bleached Fire Coral Fan",

            HABlocks.BLEACHED_TUBE_CORAL_BLOCK.get() to "Bleached Tube Coral Block",
            HABlocks.BLEACHED_TUBE_CORAL.get() to "Bleached Tube Coral",
            HABlocks.BLEACHED_TUBE_CORAL_FAN.get() to "Bleached Tube Coral Fan",

            HABlocks.BLEACHED_HORN_CORAL_BLOCK.get() to "Bleached Horn Coral Block",
            HABlocks.BLEACHED_HORN_CORAL.get() to "Bleached Horn Coral",
            HABlocks.BLEACHED_HORN_CORAL_FAN.get() to "Bleached Horn Coral Fan",

            HABlocks.BLEACHED_BUBBLE_CORAL_BLOCK.get() to "Bleached Bubble Coral Block",
            HABlocks.BLEACHED_BUBBLE_CORAL.get() to "Bleached Bubble Coral",
            HABlocks.BLEACHED_BUBBLE_CORAL_FAN.get() to "Bleached Bubble Coral Fan",

            HABlocks.BLEACHED_BRAIN_CORAL_BLOCK.get() to "Bleached Brain Coral Block",
            HABlocks.BLEACHED_BRAIN_CORAL.get() to "Bleached Brain Coral",
            HABlocks.BLEACHED_BRAIN_CORAL_FAN.get() to "Bleached Brain Coral Fan",
            //#endregion

            HABlocks.GLOWSTICK.get() to "Glowstick",
            HAPlatformBlocks.GLOWSLIME_BLOCK.get() to "Glowslime Block",
            HAPlatformBlocks.HAGSLIME_BLOCK.get() to "Hagslime Block",
            HABlocks.PEARL_BLOCK.get() to "Pearl Block",
            HABlocks.BLACK_PEARL_BLOCK.get() to "Black Pearl Block",
            HAPlatformBlocks.DRIFTWOOD_LOG.get() to "Driftwood Log",
            HAPlatformBlocks.DRIFTWOOD_WOOD.get() to "Driftwood Wood",
            HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get() to "Stripped Driftwood Log",
            HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get() to "Stripped Driftwood Wood",
            HAPlatformBlocks.DRIFTWOOD_PLANKS.get() to "Driftwood Planks",
            HAPlatformBlocks.DRIFTWOOD_STAIRS.get() to "Driftwood Stairs",
            HAPlatformBlocks.DRIFTWOOD_SLAB.get() to "Driftwood Slab",
            HAPlatformBlocks.DRIFTWOOD_FENCE.get() to "Driftwood Fence",
            HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get() to "Driftwood Fence Gate",
            HAPlatformBlocks.DRIFTWOOD_DOOR.get() to "Driftwood Door",
            HAPlatformBlocks.DRIFTWOOD_TRAPDOOR.get() to "Driftwood Trapdoor",
            HAPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get() to "Driftwood Pressure Plate",
            HAPlatformBlocks.DRIFTWOOD_BUTTON.get() to "Driftwood Button",
            HABlocks.THERMAL_VENT.get() to "Thermal Vent",
            HABlocks.GIANT_THERMAL_VENT.get() to "Giant Thermal Vent",
            HABlocks.TUBE_WORM.get() to "Tube Worm",
        ).forEach { (block, translation) ->
            builder.add(block, translation)
        }

        // items
        mapOf(
            HAItems.UNI.get() to "Uni",
            HAItems.RAW_FISH_STEAK.get() to "Fish Steak",
            HAItems.COOKED_FISH_STEAK.get() to "Cooked Fish Steak",
            HAItems.RAW_FISH_MEAT.get() to "Raw Fish Meat",
            HAItems.COOKED_FISH_MEAT.get() to "Cooked Fish Meat",
            HAItems.RAW_TENTACLE.get() to "Raw Tentacle",
            HAItems.COOKED_TENTACLE.get() to "Cooked Tentacle",
            HAItems.RAW_CRAB.get() to "Raw Crab",
            HAItems.COOKED_CRAB.get() to "Cooked Crab",
            HAItems.RAW_LOBSTER.get() to "Raw Lobster",
            HAItems.COOKED_LOBSTER.get() to "Cooked Lobster",
            HAItems.RAW_LOBSTER_TAIL.get() to "Raw Lobster Tail",
            HAItems.COOKED_LOBSTER_TAIL.get() to "Cooked Lobster Tail",
            HAItems.RAW_SHRIMP.get() to "Raw Shrimp",
            HAItems.COOKED_SHRIMP.get() to "Cooked Shrimp",
            HAItems.COOKED_CLAM.get() to "Cooked Clam",
            HAItems.RAW_CRAYFISH.get() to "Raw Crayfish",
            HAItems.COOKED_CRAYFISH.get() to "Cooked Crayfish",
            HAItems.LIONFISH.get() to "Lionfish",
            HAItems.NEON_TETRA.get() to "Neon Tetra",
            HAItems.DAMSELFISH.get() to "Damselfish",
            HAItems.DRAGONFISH.get() to "Dragonfish",
            HAItems.HAGFISH.get() to "Hagfish",
            HAItems.FLASHLIGHT_FISH.get() to "Flashlight Fish",
            HAItems.SQUIRRELFISH.get() to "Squirrel Fish",
            HAItems.COELACANTH.get() to "Coelacanth",
            HAItems.GOLDEN_DORADO.get() to "Golden Dorado",
            HAItems.MAHI.get() to "Mahi",
            HAItems.TUNA.get() to "Tuna",
            HAItems.OPAH.get() to "Opah",
            HAItems.OARFISH.get() to "Oarfish",
            HAItems.ROCKFISH.get() to "Rockfish",
            HAItems.SEA_BASS.get() to "Sea Bass",
            HAItems.BLUE_SPOTTED_STINGRAY.get() to "Blue Spotted Stingray",
            HAItems.SPOTTED_EAGLE_RAY.get() to "Spotted Eagle Ray",
            HAItems.OCEAN_SUNFISH.get() to "Ocean Sunfish",
            HAItems.BLOWFISH.get() to "Blowfish",
            HAItems.PARROTFISH.get() to "Parrotfish",
            HAItems.SHEEPSHEAD_WRASSE.get() to "Sheepshead Wrasse",
            HAItems.STONEFISH.get() to "Stonefish",
            HAItems.SEAHORSE.get() to "Seahorse",
            HAItems.MORAY_EEL.get() to "Moray Eel",
            HAItems.NEEDLEFISH.get() to "Needlefish",
            HAItems.MACKEREL.get() to "Mackerel",
            HAItems.HERRING.get() to "Herring",
            HAItems.FLYING_FISH.get() to "Flying Fish",
            HAItems.PIRANHA.get() to "Piranha",
            HAItems.ANGLERFISH.get() to "Anglerfish",
            HAItems.CARP.get() to "Carp",
            HAItems.GOLDFISH.get() to "Goldfish",
            HAItems.TROUT.get() to "Trout",
            HAItems.SUNFISH.get() to "Sunfish",
            HAItems.PLECO.get() to "Pleco",
            HAItems.BARRELEYE.get() to "Barreleye",
            HAItems.SURGEONFISH.get() to "Surgeonfish",
            HAItems.CLOWNFISH.get() to "Clownfish",
            HAItems.TIGER_BARB.get() to "Tiger Barb",
            HAItems.OSCAR.get() to "Oscar",
            HAItems.TRIGGERFISH.get() to "Triggerfish",
            HAItems.TREVALLY.get() to "Trevally",
            HAItems.DANIO.get() to "Danio",
            HAItems.BETTA.get() to "Betta",
            HAItems.PEARLFISH.get() to "Pearlfish",
            HAItems.SNAILFISH.get() to "Snailfish",
            HAItems.JOHN_DORY.get() to "John Dory",
            HAItems.DISCUS.get() to "Discus",
            HAItems.GOURAMI.get() to "Gourami",
            HAItems.RATFISH.get() to "Ratfish",
            HAItems.BOXFISH.get() to "Boxfish",
            HAItems.LOBSTER_CLAW.get() to "Lobster Claw",
            HAItems.DUNGENESS_CRAB_CLAW.get() to "Dungeness Crab Claw",
            HAItems.COCONUT_CRAB_CLAW.get() to "Coconut Crab Claw",
            HAItems.FIDDLER_CRAB_CLAW.get() to "Fiddler Crab Claw",
            HAItems.YETI_CRAB_CLAW.get() to "Yeti Crab Claw",
            HAItems.LIGHTFOOT_CRAB_CLAW.get() to "Lightfoot Crab Claw",
            HAItems.GHOST_CRAB_CLAW.get() to "Ghost Crab Claw",
            HAItems.FLOWER_CRAB_CLAW.get() to "Flower Crab Claw",
            HAItems.VAMPIRE_CRAB_CLAW.get() to "Vampire Crab Claw",
            HAItems.SPIDER_CRAB_CLAW.get() to "Spider Crab Claw",
            HAItems.GLOWSLIME.get() to "Glowslime",
            HAItems.HAGSLIME.get() to "Hagslime",
            HAItems.CUTTLEBONE.get() to "Cuttlebone",
            HAItems.FISH_FOOD.get() to "Fish Food",
            HAItems.SEA_URCHIN_SPINE.get() to "Sea Urchin Spine",
            HAItems.PRISMARINE_ROD.get() to "Prismarine Rod",
            HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get() to "Diving Armor Upgrade Template",
            HAItems.SHARK_TOOTH.get() to "Shark Tooth",
            HAItems.PEARL.get() to "Pearl",
            HAItems.BLACK_PEARL.get() to "Black Pearl",
            HAItems.COMICALLY_LARGE_NAUTILUS_SHELL.get() to "Comically Large Nautilus Shell",
            HAItems.ARGONAUT.get() to "Argonaut",
            HAItems.DIVING_WEIGHT.get() to "Diving Weight",
            HAItems.SULFUR.get() to "Sulfur",
            HAItems.CORAL_CHUNK.get() to "Coral Chunk",
            HAItems.BARBED_HOOK.get() to "Barbed Hook",
            HAItems.GLOWING_HOOK.get() to "Glowing Hook",
            HAItems.MAGNETIC_HOOK.get() to "Magnetic Hook",
            HAItems.CREEPERMAGNET_HOOK.get() to "CreeperMagnet Hook",
            HAItems.OMINOUS_HOOK.get() to "Ominous Hook",
            HAItems.OMINOUS_CONCH.get() to "Ominous Conch",
            HAItems.FISHING_NET.get() to "Fishing Net",
            HAItems.KARKINOS_CLAW.get() to "Karkinos Claw",
            HAItems.SEASHELL_SPEAR.get() to "Seashell Spear",
            HAItems.SEASHELL_PICKAXE.get() to "Seashell Pickaxe",
            HAItems.SEASHELL_AXE.get() to "Seashell Axe",
            HAItems.SEASHELL_SHOVEL.get() to "Seashell Shovel",
            HAItems.SEASHELL_HOE.get() to "Seashell Hoe",
            HAItems.CORAL_BLADE.get() to "Coral Blade",
            HAItems.CORAL_PICKAXE.get() to "Coral Pickaxe",
            HAItems.CORAL_AXE.get() to "Coral Axe",
            HAItems.CORAL_SHOVEL.get() to "Coral Shovel",
            HAItems.CORAL_HOE.get() to "Coral Hoe",
            HAItems.DIVING_HELMET.get() to "Diving Helmet",
            HAItems.DIVING_SUIT.get() to "Diving Suit",
            HAItems.DIVING_LEGGINGS.get() to "Diving Leggings",
            HAItems.DIVING_BOOTS.get() to "Diving Boots",
            HAItems.REINFORCED_DIVING_HELMET.get() to "Reinforced Diving Helmet",
            HAItems.REINFORCED_DIVING_SUIT.get() to "Reinforced Diving Suit",
            HAItems.REINFORCED_DIVING_LEGGINGS.get() to "Reinforced Diving Leggings",
            HAItems.REINFORCED_DIVING_BOOTS.get() to "Reinforced Diving Boots",
            HAItems.GLOWING_DIVING_HELMET.get() to "Glowing Diving Helmet",
            HAItems.GLOWING_DIVING_SUIT.get() to "Glowing Diving Suit",
            HAItems.GLOWING_DIVING_LEGGINGS.get() to "Glowing Diving Leggings",
            HAItems.GLOWING_DIVING_BOOTS.get() to "Glowing Diving Boots",
            HAItems.NAUTILUS_HELMET.get() to "Nautilus Helmet",
            HAItems.NAUTILUS_PAULDRONS.get() to "Nautilus Pauldrons",
            HAItems.MANGLERFISH_LURE.get() to "Manglerfish Lure",
            HAItems.MANGLERFISH_FIN.get() to "Manglerfish Fin",
            HAItems.EEL_SCARF.get() to "Eel Scarf",
            HAItems.PINK_HATXOLOTL.get() to "Pink Hatxolotl",
            HAItems.GOLD_HATXOLOTL.get() to "Gold Hatxolotl",
            HAItems.BROWN_HATXOLOTL.get() to "Brown Hatxolotl",
            HAItems.BLUE_HATXOLOTL.get() to "Blue Hatxolotl",
            HAItems.CYAN_HATXOLOTL.get() to "Cyan Hatxolotl",
            HAItems.TURTLE_CHESTPLATE.get() to "Turtle Chestplate",
            HAItems.MOON_JELLYFISH_HAT.get() to "Moon Jellyfish Hat",
        ).forEach { (item, translation) ->
            builder.add(item, translation)
        }

        // effects
        mapOf(
            HAMobEffects.BLEEDING.get() to "Bleeding",
            HAMobEffects.CLARITY.get() to "Clarity",
            HAMobEffects.CORROSION.get() to "Corrosion",
            HAMobEffects.THALASSOPHOBIA.get() to "Thalassophobia",
            HAMobEffects.BUOYANCY.get() to "Buoyancy",
            HAMobEffects.THORNS.get() to "Thorns",
        ).forEach { (effect, translation) ->
            val identifier = BuiltInRegistries.MOB_EFFECT.getKey(effect)
            builder.add("effect.${identifier?.namespace}.${identifier?.path}", translation)
        }

        // Item Descriptions
        builder.add("item.hybrid-aquatic.hook.description_tide", "Apply at an angling table") // Tide specific description

        builder.add("tooltip.hybrid-aquatic.argonaut.shell", "%s Shell")
        builder.add("tooltip.hybrid-aquatic.argonaut.sail", "%s Sails")
        builder.add("tooltip.hybrid-aquatic.argonaut.glowing", "Glowing")

        builder.add("tooltip.hybrid-aquatic.ominous_conch.unused", "The deep ocean calls from within..")
        builder.add("tooltip.hybrid-aquatic.ominous_conch.used", "The conch is silent")

        mapOf(
            "item.hybrid-aquatic.hook" to "Needs to be put in the offhand",
            HAItems.BARBED_HOOK.get().descriptionId to "Increases fishing speed during the day",
            HAItems.GLOWING_HOOK.get().descriptionId to "Increases fishing speed at night",
            HAItems.MAGNETIC_HOOK.get().descriptionId to "Increases treasure chance",
            HAItems.CREEPERMAGNET_HOOK.get().descriptionId to "Don't use indoors",
            HAItems.OMINOUS_HOOK.get().descriptionId to "Summons Karkinos",
            HABlocks.CRAB_POT.get().descriptionId to "Break with an axe to open",
            HABlocks.HYBRID_CRATE.get().descriptionId to "Break with an axe to open",
            HABlocks.OAK_CRATE.get().descriptionId to "Break with an axe to open",
            HABlocks.SPRUCE_CRATE.get().descriptionId to "Break with an axe to open",
            HABlocks.BIRCH_CRATE.get().descriptionId to "Break with an axe to open",
            HABlocks.DARK_OAK_CRATE.get().descriptionId to "Break with an axe to open",
            HABlocks.JUNGLE_CRATE.get().descriptionId to "Break with an axe to open",
            HABlocks.MANGROVE_CRATE.get().descriptionId to "Break with an axe to open",
            HABlocks.ACACIA_CRATE.get().descriptionId to "Break with an axe to open",
            HABlocks.CHERRY_CRATE.get().descriptionId to "Break with an axe to open",
            HABlocks.BAMBOO_CRATE.get().descriptionId to "Break with an axe to open",

            HAItems.FISHING_NET.get().descriptionId to "Stored Entity: %s",
            HAItems.DIVING_WEIGHT.get().descriptionId to "Quite heavy",

            HAItems.CORAL_AXE.get().descriptionId to "Repairs itself when underwater",
            HAItems.CORAL_BLADE.get().descriptionId to "Repairs itself when underwater",
            HAItems.CORAL_HOE.get().descriptionId to "Repairs itself when underwater",
            HAItems.CORAL_PICKAXE.get().descriptionId to "Repairs itself when underwater",
            HAItems.CORAL_SHOVEL.get().descriptionId to "Repairs itself when underwater",

            HAItems.SEASHELL_AXE.get().descriptionId to "Increased mining speed underwater",
            HAItems.SEASHELL_SPEAR.get().descriptionId to "Increased mining speed underwater",
            HAItems.SEASHELL_HOE.get().descriptionId to "Increased mining speed underwater",
            HAItems.SEASHELL_PICKAXE.get().descriptionId to "Increased mining speed underwater",
            HAItems.SEASHELL_SHOVEL.get().descriptionId to "Increased mining speed underwater",

            HAItems.MOON_JELLYFISH_HAT.get().descriptionId to "Made by Jakotens",

            HAItems.GREAT_WHITE_SHARK_PLUSHIE.get().descriptionId to "Can be worn as a hat!",
            HAItems.BULL_SHARK_PLUSHIE.get().descriptionId to "Can be worn as a hat!",
            HAItems.TIGER_SHARK_PLUSHIE.get().descriptionId to "Can be worn as a hat!",
            HAItems.THRESHER_SHARK_PLUSHIE.get().descriptionId to "Can be worn as a hat!",
            HAItems.BASKING_SHARK_PLUSHIE.get().descriptionId to "Can be worn as a hat!",
            HAItems.WHALE_SHARK_PLUSHIE.get().descriptionId to "Can be worn as a hat!",
            HAItems.HAMMERHEAD_SHARK_PLUSHIE.get().descriptionId to "Can be worn as a hat!",
            HAItems.FRILLED_SHARK_PLUSHIE.get().descriptionId to "Can be worn as a hat!",
        ).forEach { (itemTranslationKey, translation) ->
            builder.add(itemTranslationKey.plus(".description"), translation)
        }

        // Item Functions
        mapOf(
            HAItems.FISHING_NET.get().descriptionId to "Lets you catch and move aquatic creatures",
            HAItems.OMINOUS_CONCH.get().descriptionId to "Summons the Shell Beast",
        ).forEach { (itemTranslationKey, translation) ->
            builder.add(itemTranslationKey.plus(".function"), translation)
        }

        mapOf(
            HAItems.FISHING_NET.get().descriptionId to "Placed creatures become passive and don't despawn",
        ).forEach { (itemTranslationKey, translation) ->
            builder.add(itemTranslationKey.plus(".properties"), translation)
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
            HAEntityTypes.CLOWNFISH.get() to "Clownfish",
            HAEntityTypes.DAMSELFISH.get() to "Damselfish",
            HAEntityTypes.AFRICAN_BUTTERFLYFISH.get() to "African Butterflyfish",
            HAEntityTypes.ANGLERFISH.get() to "Anglerfish",
            HAEntityTypes.VIPERFISH.get() to "Viperfish",
            HAEntityTypes.HATCHETFISH.get() to "Hatchetfish",
            HAEntityTypes.TRIPOD_FISH.get() to "Tripod Fish",
            HAEntityTypes.FANGTOOTH.get() to "Fangtooth",
            HAEntityTypes.JOHN_DORY.get() to "John Dory",
            HAEntityTypes.SNAILFISH.get() to "Snailfish",
            HAEntityTypes.PEARLFISH.get() to "Pearlfish",
            HAEntityTypes.DRAGONFISH.get() to "Dragonfish",
            HAEntityTypes.HAGFISH.get() to "Hagfish",
            HAEntityTypes.BARRELEYE.get() to "Barreleye",
            HAEntityTypes.TUNA.get() to "Tuna",
            HAEntityTypes.CUTTLEFISH.get() to "Cuttlefish",
            HAEntityTypes.FLASHLIGHT_FISH.get() to "Flashlight Fish",
            HAEntityTypes.SQUIRRELFISH.get() to "Squirrelfish",
            HAEntityTypes.FLYING_FISH.get() to "Flying Fish",
            HAEntityTypes.LIONFISH.get() to "Lionfish",
            HAEntityTypes.COELACANTH.get() to "Coelacanth",
            HAEntityTypes.SLICKHEAD.get() to "Slickhead",
            HAEntityTypes.OARFISH.get() to "Oarfish",
            HAEntityTypes.OPAH.get() to "Opah",
            HAEntityTypes.PIRANHA.get() to "Piranha",
            HAEntityTypes.SEA_ANGEL.get() to "Sea Angel",
            HAEntityTypes.OCEAN_SUNFISH.get() to "Ocean Sunfish",
            HAEntityTypes.PARROTFISH.get() to "Parrotfish",
            HAEntityTypes.VAMPIRE_SQUID.get() to "Vampire Squid",
            HAEntityTypes.MAHI.get() to "Mahi",
            HAEntityTypes.GOLDEN_DORADO.get() to "Golden Dorado",
            HAEntityTypes.MORAY_EEL.get() to "Moray Eel",
            HAEntityTypes.ROCKFISH.get() to "Rockfish",
            HAEntityTypes.SEA_BASS.get() to "Sea Bass",
            HAEntityTypes.TIGER_BARB.get() to "Tiger Barb",
            HAEntityTypes.CARP.get() to "Carp",
            HAEntityTypes.GOLDFISH.get() to "Goldfish",
            HAEntityTypes.TROUT.get() to "Trout",
            HAEntityTypes.SUNFISH.get() to "Sunfish",
            HAEntityTypes.NEEDLEFISH.get() to "Needlefish",
            HAEntityTypes.BARRACUDA.get() to "Barracuda",
            HAEntityTypes.GARDEN_EEL.get() to "Garden Eel",
            HAEntityTypes.MACKEREL.get() to "Mackerel",
            HAEntityTypes.HERRING.get() to "Herring",
            HAEntityTypes.RATFISH.get() to "Ratfish",
            HAEntityTypes.NAUTILUS.get() to "Nautilus",
            HAEntityTypes.UMBRELLA_OCTOPUS.get() to "Umbrella Octopus",
            HAEntityTypes.TRIGGERFISH.get() to "Triggerfish",
            HAEntityTypes.TREVALLY.get() to "Trevally",
            HAEntityTypes.OSCAR.get() to "Oscar",
            HAEntityTypes.DANIO.get() to "Danio",
            HAEntityTypes.BLOWFISH.get() to "Blowfish",
            HAEntityTypes.TETRA.get() to "Tetra",
            HAEntityTypes.PUPFISH.get() to "Pupfish",
            HAEntityTypes.STONEFISH.get() to "Stonefish",
            HAEntityTypes.SHINER.get() to "Shiner",
            HAEntityTypes.BETTA.get() to "Betta",
            HAEntityTypes.SEAHORSE.get() to "Seahorse",
            HAEntityTypes.MOON_JELLYFISH.get() to "Moon Jellyfish",
            HAEntityTypes.GOURAMI.get() to "Gourami",
            HAEntityTypes.PLECO.get() to "Pleco",
            HAEntityTypes.BOXFISH.get() to "Boxfish",
            HAEntityTypes.OCTOPUS.get() to "Octopus",
            HAEntityTypes.DISCUS.get() to "Discus",
            HAEntityTypes.CORYDORA.get() to "Corydora",
            HAEntityTypes.ARROW_SQUID.get() to "Arrow Squid",
            HAEntityTypes.COLOSSAL_SQUID.get() to "Colossal Squid",
            HAEntityTypes.GIANT_SQUID.get() to "Giant Squid",
            HAEntityTypes.FIREFLY_SQUID.get() to "Firefly Squid",
            HAEntityTypes.STINGRAY.get() to "Stingray",
            HAEntityTypes.MANTA_RAY.get() to "Manta Ray",
            HAEntityTypes.SURGEONFISH.get() to "Surgeonfish",
            HAEntityTypes.BULL_SHARK.get() to "Bull Shark",
            HAEntityTypes.BASKING_SHARK.get() to "Basking Shark",
            HAEntityTypes.THRESHER_SHARK.get() to "Thresher Shark",
            HAEntityTypes.FRILLED_SHARK.get() to "Frilled Shark",
            HAEntityTypes.SIXGILL_SHARK.get() to "Sixgill Shark",
            HAEntityTypes.SLEEPER_SHARK.get() to "Sleeper Shark",
            HAEntityTypes.GOBLIN_SHARK.get() to "Goblin Shark",
            HAEntityTypes.LANTERN_SHARK.get() to "Lantern Shark",
            HAEntityTypes.GREAT_WHITE_SHARK.get() to "Great White Shark",
            HAEntityTypes.SAND_TIGER_SHARK.get() to "Sand Tiger Shark",
            HAEntityTypes.HAMMERHEAD_SHARK.get() to "Hammerhead Shark",
            HAEntityTypes.WHALE_SHARK.get() to "Whale Shark",
            HAEntityTypes.KARKINOS.get() to "Karkinos",
            HAEntityTypes.KARCINOGEN.get() to "Karcinogen",
            HAEntityTypes.KARCINOMA.get() to "Karcinoma",
            HAEntityTypes.SHELL_BEAST.get() to "Shell Beast",
            HAEntityTypes.HYPNAUTILUS.get() to "Hypnautilus",
            HAEntityTypes.BEAKLING.get() to "Beakling",
            HAEntityTypes.DUNGENESS_CRAB.get() to "Dungeness Crab",
            HAEntityTypes.FIDDLER_CRAB.get() to "Fiddler Crab",
            HAEntityTypes.HERMIT_CRAB.get() to "Hermit Crab",
            HAEntityTypes.GHOST_CRAB.get() to "Ghost Crab",
            HAEntityTypes.LIGHTFOOT_CRAB.get() to "Lightfoot Crab",
            HAEntityTypes.FLOWER_CRAB.get() to "Flower Crab",
            HAEntityTypes.VAMPIRE_CRAB.get() to "Vampire Crab",
            HAEntityTypes.SPIDER_CRAB.get() to "Spider Crab",
            HAEntityTypes.YETI_CRAB.get() to "Yeti Crab",
            HAEntityTypes.DECORATOR_CRAB.get() to "Decorator Crab",
            HAEntityTypes.COCONUT_CRAB.get() to "Coconut Crab",
            HAEntityTypes.HORSESHOE_CRAB.get() to "Horseshoe Crab",
            HAEntityTypes.GIANT_ISOPOD.get() to "Giant Isopod",
            HAEntityTypes.SHRIMP.get() to "Shrimp",
            HAEntityTypes.CRAYFISH.get() to "Crayfish",
            HAEntityTypes.LOBSTER.get() to "Lobster",
            HAEntityTypes.SEA_SLUG.get() to "Sea Slug",
            HAEntityTypes.SCALYFOOT_SNAIL.get() to "Scalyfoot Snail",
            HAEntityTypes.SEA_CUCUMBER.get() to "Sea Cucumber",
            HAEntityTypes.SEA_URCHIN.get() to "Sea Urchin",
            HAEntityTypes.STARFISH.get() to "Starfish",
            HAEntityTypes.SEA_NETTLE.get() to "Sea Nettle",
            HAEntityTypes.BOX_JELLYFISH.get() to "Box Jellyfish",
            HAEntityTypes.CEPHEIDAE_JELLYFISH.get() to "Cepheidae Jellyfish",
            HAEntityTypes.NOMURA_JELLYFISH.get() to "Nomura Jellyfish",
            HAEntityTypes.BARREL_JELLYFISH.get() to "Barrel Jellyfish",
            HAEntityTypes.MAUVE_STINGER.get() to "Mauve Stinger",
            HAEntityTypes.LIONS_MANE_JELLYFISH.get() to "Lion's Mane Jellyfish",
            HAEntityTypes.CROWN_JELLYFISH.get() to "Crown Jellyfish",
            HAEntityTypes.BIG_RED_JELLYFISH.get() to "Big Red Jellyfish",
            HAEntityTypes.COSMIC_JELLYFISH.get() to "Cosmic Jellyfish",
            HAEntityTypes.COMB_JELLY.get() to "Comb Jelly",
            HAEntityTypes.FIREWORK_JELLYFISH.get() to "Firework Jellyfish",
            HAEntityTypes.BLUE_JELLYFISH.get() to "Blue Jellyfish",
            HAEntityTypes.SEADRAGON.get() to "Seadragon",
            HAEntityTypes.WRASSE.get() to "Wrasse",
            HAEntityTypes.HOUND_SHARK.get() to "Hound Shark",
            HAEntityTypes.OTTER.get() to "Otter",
            HAEntityTypes.DUGONG.get() to "Dugong",
            HAEntityTypes.MANATEE.get() to "Manatee",
            HAEntityTypes.ORCA.get() to "Orca",
            HAEntityTypes.DEPTH_CHARGE.get() to "Depth Charge",
            HAEntityTypes.SMALL_TNT.get() to "Small TNT",
            HAEntityTypes.ARGONAUT.get() to "Argonaut",
            HAEntityTypes.CAVITATION_BUBBLE.get() to "Cavitation Bubble",
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