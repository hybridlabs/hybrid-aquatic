package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.data.server.seamessage.SeaMessageProvider
import dev.hybridlabs.aquatic.effect.HAMobEffects
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.item.HAItemGroups
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.item.HAPlatformItems
import dev.hybridlabs.aquatic.painting.HAPaintings
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
            builder.add("$key.change_hint", "Interact in-world to change variant")

            val variantKey = "$key.variant"
            builder.add("$variantKey.bottle", "Bottle")
            builder.add("$variantKey.jar", "Jar")
            builder.add("$variantKey.longneck", "Longneck Bottle")
            builder.add("$variantKey.potion", "Potion Bottle")
            builder.add("$variantKey.wine", "Wine Bottle")
        }

        // sea messages
        SeaMessageProvider.BUILT_IN.forEach { message ->
            builder.add(message.translationKey, message.englishText)
            message.englishTitle?.let { title -> builder.add(message.titleTranslationKey, title) }
        }

        builder.add(HAItems.SEA_MESSAGE_BOOK.get(), "Sea Message")

        //advancements
        mapOf(
            "advancements.hybrid_aquatic.enter_water.title" to "Hybrid Aquatic",
            "advancements.hybrid_aquatic.enter_water.description" to "Discover an expanded world beneath the waves",

            "advancements.hybrid_aquatic.fishing_net.title" to "Not Quite A Bucket",
            "advancements.hybrid_aquatic.fishing_net.description" to "Craft a fishing net to pick up and transport sea creatures",

            "advancements.hybrid_aquatic.glowstick.title" to "Better Than Torches!",
            "advancements.hybrid_aquatic.glowstick.description" to "Craft a glowstick to light your way in the deep sea",

            "advancements.hybrid_aquatic.sulfur.title" to "Not Glowstone Dust",
            "advancements.hybrid_aquatic.sulfur.description" to "Find sulfur in a sulfuric cave",

            "advancements.hybrid_aquatic.depth_charge.title" to "Mining Fatigue?",
            "advancements.hybrid_aquatic.depth_charge.description" to "Craft a depth charge",

            "advancements.hybrid_aquatic.buoy.title" to "Oh Buoy!",
            "advancements.hybrid_aquatic.buoy.description" to "Craft a buoy to guide sailors across the sea",

            "advancements.hybrid_aquatic.coral_chunk.title" to "That's Not A Sheep",
            "advancements.hybrid_aquatic.coral_chunk.description" to "Shear a decorator crab to get a coral chunk",

            "advancements.hybrid_aquatic.coral_tools.title" to "Who Needs Mending?",
            "advancements.hybrid_aquatic.coral_tools.description" to "Craft a tool out of coral chunks",

            "advancements.hybrid_aquatic.get_clam.title" to "Happy As A Clam",
            "advancements.hybrid_aquatic.get_clam.description" to "Feed a dugong some sea lettuce to get a clam",

            "advancements.hybrid_aquatic.plant_clam.title" to "Shell Corporation",
            "advancements.hybrid_aquatic.plant_clam.description" to "Grow your own clams underwater",

            "advancements.hybrid_aquatic.kill_sirenian.title" to "Sea Cow Tipper",
            "advancements.hybrid_aquatic.kill_sirenian.description" to "They're endangered, you know",

            "advancements.hybrid_aquatic.nautilus_shell.title" to "Someone Used To Live Here",
            "advancements.hybrid_aquatic.nautilus_shell.description" to "Obtain a nautilus shell",

            "advancements.hybrid_aquatic.seashell_tools.title" to "Shell Yeah!",
            "advancements.hybrid_aquatic.seashell_tools.description" to "Craft a tool out of nautilus shells",

            "advancements.hybrid_aquatic.ominous_conch.title" to "If You Listen Closely..",
            "advancements.hybrid_aquatic.ominous_conch.description" to "Trade shells with a hermit crab for an Ominous Conch",

            "advancements.hybrid_aquatic.shell_beast.title" to "Shell-Shocked",
            "advancements.hybrid_aquatic.shell_beast.description" to "Kill the Shell Beast",

            "advancements.hybrid_aquatic.argonaut.title" to "This Boat Has Fins",
            "advancements.hybrid_aquatic.argonaut.description" to "Build the Argonaut",

            "advancements.hybrid_aquatic.conduit.title" to "Better Than Gills",
            "advancements.hybrid_aquatic.conduit.description" to "Craft a conduit",

            "advancements.hybrid_aquatic.turtle_scute.title" to "They Grow Up So Fast!",
            "advancements.hybrid_aquatic.turtle_scute.description" to "Obtain a turtle scute",

            "advancements.hybrid_aquatic.turtle_set.title" to "Cowabunga!",
            "advancements.hybrid_aquatic.turtle_set.description" to "Craft a piece of turtle armor",

            "advancements.hybrid_aquatic.diving_weight.title" to "The Fast Way Down",
            "advancements.hybrid_aquatic.diving_weight.description" to "Craft a diving weight",

            "advancements.hybrid_aquatic.diving_suit.title" to "Diving In",
            "advancements.hybrid_aquatic.diving_suit.description" to "Obtain a full set of diving gear",

            "advancements.hybrid_aquatic.diving_upgrade.title" to "Beachcombing",
            "advancements.hybrid_aquatic.diving_upgrade.description" to "Find a diving suit upgrade template",

            "advancements.hybrid_aquatic.reinforced_diving_suit.title" to "Diving Deeper",
            "advancements.hybrid_aquatic.reinforced_diving_suit.description" to "Reinforce your diving gear",

            "advancements.hybrid_aquatic.glowing_diving_suit.title" to "The Light In The Abyss",
            "advancements.hybrid_aquatic.glowing_diving_suit.description" to "Make your diving suit glow in the dark",

            "advancements.hybrid_aquatic.hook.title" to "Hooked!",
            "advancements.hybrid_aquatic.hook.description" to "Craft a hook to help you catch fish faster",

            "advancements.hybrid_aquatic.creeper_hook.title" to "An Explosive Catch",
            "advancements.hybrid_aquatic.creeper_hook.description" to "Also try The Creeper's Code!",

            "advancements.hybrid_aquatic.pearl.title" to "Pearly Whites",
            "advancements.hybrid_aquatic.pearl.description" to "Obtain a pearl from a giant clam",

            "advancements.hybrid_aquatic.black_pearl.title" to "The Black Pearl",
            "advancements.hybrid_aquatic.black_pearl.description" to "What the Black Pearl really is... is freedom",

            "advancements.hybrid_aquatic.crab_claw.title" to "Clawesome",
            "advancements.hybrid_aquatic.crab_claw.description" to "Obtain any crab claw",

            "advancements.hybrid_aquatic.ominous_hook.title" to "Hook, Line, and Pincher",
            "advancements.hybrid_aquatic.ominous_hook.description" to "Obtain an Ominous Hook",

            "advancements.hybrid_aquatic.kill_karkinos.title" to "A Herculean Task",
            "advancements.hybrid_aquatic.kill_karkinos.description" to "Defeat Karkinos",

            "advancements.hybrid_aquatic.bigger_boat.title" to "We're Gonna Need A Bigger Boat",
            "advancements.hybrid_aquatic.bigger_boat.description" to "Block a shark attack with a shield to get a shark tooth",

            "advancements.hybrid_aquatic.trident.title" to "Poseidon Quivers Before Him!",
            "advancements.hybrid_aquatic.trident.description" to "Obtain a trident",
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
            HASoundEvents.SHELL_BEAST_ROAR to "Shell Beast roars",

            HASoundEvents.HYPNAUTILUS_AMBIENT to "Hypnautilus spirals",
            HASoundEvents.HYPNAUTILUS_HURT to "Hypnautilus hurts",
            HASoundEvents.HYPNAUTILUS_DIE to "Hypnautilus dies",

            HASoundEvents.OMINOUS_CONCH_BLOWS to "Ominous Conch plays"
        ).forEach { (soundEvent, translation) ->
            builder.add(Util.makeDescriptionId("subtitles", soundEvent.get().location), translation)
        }
        //-Sound Events

        mapOf(
            "journal.description.hybrid_aquatic.anglerfish" to
                    "A deep-sea fish with a glowing lure, used to attract prey.",
            "journal.description.hybrid_aquatic.barreleye" to
                    "A deep-sea fish with a transparent head, letting it see far above it.",
            "journal.description.hybrid_aquatic.boxfish" to
                    "A small box-shaped fish, capable of releasing powerful toxins when stressed.",
            "journal.description.hybrid_aquatic.betta" to
                    "A small freshwater fish, known for its flowing fins, territorial behaviour, and beautiful colors.",
            "journal.description.hybrid_aquatic.carp" to
                    "A hardy freshwater fish, can be bred to produce koi and goldfish.",
            "journal.description.hybrid_aquatic.danio" to
                    "The Danio, a small, colorful freshwater fish known for its active swimming behavior and popularity in home aquariums.",
            "journal.description.hybrid_aquatic.discus" to
                    "A disc-shaped freshwater fish, known for being territorial and having colorful scales.",
            "journal.description.hybrid_aquatic.dragonfish" to
                    "A deep-sea fish with a long glowing lure extending from its jaw, used to attract prey.",
            "journal.description.hybrid_aquatic.golden_dorado" to
                    "A large freshwater predator, known for its strong scales and large teeth.",
            "journal.description.hybrid_aquatic.gourami" to
                    "A small freshwater fish, known for being territorial and having colorful scales.",
            "journal.description.hybrid_aquatic.mackerel" to
                    "A small baitfish known for forming large schools.",
            "journal.description.hybrid_aquatic.herring" to
                    "A small baitfish known for forming large schools.",
            "journal.description.hybrid_aquatic.moray_eel" to
                    "A snake-like predatory fish that hides in crevices and caves on the coral reef.",
            "journal.description.hybrid_aquatic.cichlid" to
                    "A freshwater fish, popular in aquariums for its intelligence and striking patterns.",
            "journal.description.hybrid_aquatic.pearlfish" to
                    "A small fish that lives inside sea cucumbers.",
            "journal.description.hybrid_aquatic.piranha" to
                    "A small freshwater fish known for its sharp teeth and voracious appetite.",
            "journal.description.hybrid_aquatic.rockfish" to
                    "A saltwater fish, known for its large eyes and its tendency to hide among rocks.",
            "journal.description.hybrid_aquatic.sea_bass" to
                    "A predatory saltwater fish, often used as a food source and known for its delicious flavor.",
            "journal.description.hybrid_aquatic.snailfish" to
                    "A deep-sea fish with an extremely soft and fragile body",
            "journal.description.hybrid_aquatic.squirrelfish" to
                    "A nocturnal reef fish known for its large eyes and sharp spine on its underside",
            "journal.description.hybrid_aquatic.coelacanth" to
                    "A deep-sea fish once thought to be extinct, now considered a living fossil.",
            "journal.description.hybrid_aquatic.oarfish" to
                    "A long deep-sea fish that often appears before earthquakes, thought to be the inspiration behind sea serpents.",
            "journal.description.hybrid_aquatic.damselfish" to
                    "An extremely common reef fish, found in a variety of shapes and colors, often swimming in large schools.",
            "journal.description.hybrid_aquatic.parrotfish" to
                    "An important reef fish, known for its parrot-like beak, as well as its ability to eat corals and produce sand.",
            "journal.description.hybrid_aquatic.sheepshead_wrasse" to
                    "A large fish that lives in kelp forests, eating sea urchins and helping keep the kelp alive.",
            "journal.description.hybrid_aquatic.trevally" to
                    "A schooling fish that prefers to follow large animals around.",
            "journal.description.hybrid_aquatic.stingray" to
                    "A large circular fish that swims along the seafloor, known for having a venomous spine on the end of its tail.",
            "journal.description.hybrid_aquatic.stonefish" to
                    "A bottom-dwelling fish that mimics the appearance of rocks around it, known for its extremely potent venom.",
            "journal.description.hybrid_aquatic.ocean_sunfish" to
                    "The largest bony fish in the world, known for its indifference to pain.",
            "journal.description.hybrid_aquatic.surgeonfish" to
                    "A reef fish, named after sharp protrusions on either side of its tail.",
            "journal.description.hybrid_aquatic.clownfish" to
                    "A brightly colored reef fish, often found living in anemones, and popularized by a children's movie.",
            "journal.description.hybrid_aquatic.lionfish" to
                    "A predatory reef fish with venomous spines, considered invasive in some parts of the world.",
            "journal.description.hybrid_aquatic.tetra" to
                    "A small freshwater fish, known for its bright colors and schooling behaviour.",
            "journal.description.hybrid_aquatic.tiger_barb" to
                    "A small freshwater fish, known for its striped pattern and schooling behaviour.",
            "journal.description.hybrid_aquatic.blowfish" to
                    "A type of pufferfish, known for making intricate designs in the sandy seabed.",
            "journal.description.hybrid_aquatic.triggerfish" to
                    "A large reef fish, known for its sharp teeth and aggressive behaviour, named after a sharp spine protruding out of its back.",
            "journal.description.hybrid_aquatic.tuna" to
                    "An open-water predatory fish that never stops swimming.",
            "journal.description.hybrid_aquatic.mahi" to
                    "An open-water predatory fish, known for its bright colors.",
            "journal.description.hybrid_aquatic.blobfish" to
                    "A deep-sea fish with a soft gelatinous body, adapted to survive under immense pressure.",
            "journal.description.hybrid_aquatic.hagfish" to
                    "A primitive eel-like scavenger, capable of producing large amounts of slime when threatened.",
            "journal.description.hybrid_aquatic.flashlight_fish" to
                    "A nocturnal deep-sea fish with glowing organs beneath its eyes, used for communication and attracting prey.",
            "journal.description.hybrid_aquatic.opah" to
                    "A large open-ocean fish, notable for being one of the few warm-blooded fish in the world.",
            "journal.description.hybrid_aquatic.seahorse" to
                    "A small reef fish that swims upright, known for its curled tail and the male's ability to carry eggs.",
            "journal.description.hybrid_aquatic.needlefish" to
                    "A slender predatory fish with a long beak filled with sharp teeth, often found near the water's surface.",
            "journal.description.hybrid_aquatic.flying_fish" to
                    "An open-ocean fish capable of gliding above the water using its enlarged fins.",
            "journal.description.hybrid_aquatic.goldfish" to
                    "A domesticated freshwater fish bred from carp, known for its bright colors and many varieties.",
            "journal.description.hybrid_aquatic.trout" to
                    "A freshwater fish commonly found in rivers and lakes, valued for its speed and ability to swim upstream.",
            "journal.description.hybrid_aquatic.sunfish" to
                    "A common freshwater fish known for its rounded body and willingness to bite almost anything.",
            "journal.description.hybrid_aquatic.pleco" to
                    "An armored freshwater catfish that feeds on algae and uses its sucker-like mouth to cling to surfaces.",
            "journal.description.hybrid_aquatic.john_dory" to
                    "A predatory saltwater fish recognized by the large dark spot on its side and highly protrusible jaws.",
            "journal.description.hybrid_aquatic.ratfish" to
                    "A deep-sea relative of sharks, known for its large eyes, long tail, and unusual appearance.",

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
            HABlocks.RED_BRINESTONE.get() to "Red Brinestone",
            HABlocks.RED_BRINESTONE_STAIRS.get() to "Red Brinestone Stairs",
            HABlocks.RED_BRINESTONE_SLAB.get() to "Red Brinestone Slab",
            HABlocks.RED_BRINESTONE_WALL.get() to "Red Brinestone Wall",
            HABlocks.RED_BRINESTONE_BRICKS.get() to "Red Brinestone Bricks",
            HABlocks.RED_BRINESTONE_BRICK_STAIRS.get() to "Red Brinestone Brick Stairs",
            HABlocks.RED_BRINESTONE_BRICK_SLAB.get() to "Red Brinestone Brick Slab",
            HABlocks.RED_BRINESTONE_BRICK_WALL.get() to "Red Brinestone Brick Wall",
            HABlocks.CHISELED_RED_BRINESTONE.get() to "Chiseled Red Brinestone",
            HABlocks.POLISHED_RED_BRINESTONE.get() to "Polished Red Brinestone",
            HABlocks.POLISHED_RED_BRINESTONE_STAIRS.get() to "Polished Red Brinestone Stairs",
            HABlocks.POLISHED_RED_BRINESTONE_SLAB.get() to "Polished Red Brinestone Slab",
            HABlocks.ORANGE_BRINESTONE.get() to "Orange Brinestone",
            HABlocks.ORANGE_BRINESTONE_STAIRS.get() to "Orange Brinestone Stairs",
            HABlocks.ORANGE_BRINESTONE_SLAB.get() to "Orange Brinestone Slab",
            HABlocks.ORANGE_BRINESTONE_WALL.get() to "Orange Brinestone Wall",
            HABlocks.ORANGE_BRINESTONE_BRICKS.get() to "Orange Brinestone Bricks",
            HABlocks.ORANGE_BRINESTONE_BRICK_STAIRS.get() to "Orange Brinestone Brick Stairs",
            HABlocks.ORANGE_BRINESTONE_BRICK_SLAB.get() to "Orange Brinestone Brick Slab",
            HABlocks.ORANGE_BRINESTONE_BRICK_WALL.get() to "Orange Brinestone Brick Wall",
            HABlocks.CHISELED_ORANGE_BRINESTONE.get() to "Chiseled Orange Brinestone",
            HABlocks.POLISHED_ORANGE_BRINESTONE.get() to "Polished Orange Brinestone",
            HABlocks.POLISHED_ORANGE_BRINESTONE_STAIRS.get() to "Polished Orange Brinestone Stairs",
            HABlocks.POLISHED_ORANGE_BRINESTONE_SLAB.get() to "Polished Orange Brinestone Slab",
            HABlocks.YELLOW_BRINESTONE.get() to "Yellow Brinestone",
            HABlocks.YELLOW_BRINESTONE_STAIRS.get() to "Yellow Brinestone Stairs",
            HABlocks.YELLOW_BRINESTONE_SLAB.get() to "Yellow Brinestone Slab",
            HABlocks.YELLOW_BRINESTONE_WALL.get() to "Yellow Brinestone Wall",
            HABlocks.YELLOW_BRINESTONE_BRICKS.get() to "Yellow Brinestone Bricks",
            HABlocks.YELLOW_BRINESTONE_BRICK_STAIRS.get() to "Yellow Brinestone Brick Stairs",
            HABlocks.YELLOW_BRINESTONE_BRICK_SLAB.get() to "Yellow Brinestone Brick Slab",
            HABlocks.YELLOW_BRINESTONE_BRICK_WALL.get() to "Yellow Brinestone Brick Wall",
            HABlocks.CHISELED_YELLOW_BRINESTONE.get() to "Chiseled Yellow Brinestone",
            HABlocks.POLISHED_YELLOW_BRINESTONE.get() to "Polished Yellow Brinestone",
            HABlocks.POLISHED_YELLOW_BRINESTONE_STAIRS.get() to "Polished Yellow Brinestone Stairs",
            HABlocks.POLISHED_YELLOW_BRINESTONE_SLAB.get() to "Polished Yellow Brinestone Slab",
            HABlocks.SCHIST.get() to "Schist",
            HABlocks.SCHIST_STAIRS.get() to "Schist Stairs",
            HABlocks.SCHIST_SLAB.get() to "Schist Slab",
            HABlocks.SCHIST_WALL.get() to "Schist Wall",
            HABlocks.SCHIST_BRICKS.get() to "Schist Bricks",
            HABlocks.SCHIST_BRICK_STAIRS.get() to "Schist Brick Stairs",
            HABlocks.SCHIST_BRICK_SLAB.get() to "Schist Brick Slab",
            HABlocks.SCHIST_BRICK_WALL.get() to "Schist Brick Wall",
            HABlocks.CHISELED_SCHIST.get() to "Chiseled Schist",
            HABlocks.POLISHED_SCHIST.get() to "Polished Schist",
            HABlocks.POLISHED_SCHIST_STAIRS.get() to "Polished Schist Stairs",
            HABlocks.POLISHED_SCHIST_SLAB.get() to "Polished Schist Slab",
            HABlocks.CHIMNEYSTONE.get() to "Chimneystone",
            HABlocks.CHIMNEYSTONE_STAIRS.get() to "Chimneystone Stairs",
            HABlocks.CHIMNEYSTONE_SLAB.get() to "Chimneystone Slab",
            HABlocks.CHIMNEYSTONE_WALL.get() to "Chimneystone Wall",
            HABlocks.CHIMNEYSTONE_BRICKS.get() to "Chimneystone Bricks",
            HABlocks.CHIMNEYSTONE_BRICK_STAIRS.get() to "Chimneystone Brick Stairs",
            HABlocks.CHIMNEYSTONE_BRICK_SLAB.get() to "Chimneystone Brick Slab",
            HABlocks.CHIMNEYSTONE_BRICK_WALL.get() to "Chimneystone Brick Wall",
            HABlocks.CHISELED_CHIMNEYSTONE.get() to "Chiseled Chimneystone",
            HABlocks.POLISHED_CHIMNEYSTONE.get() to "Polished Chimneystone",
            HABlocks.POLISHED_CHIMNEYSTONE_STAIRS.get() to "Polished Chimneystone Stairs",
            HABlocks.POLISHED_CHIMNEYSTONE_SLAB.get() to "Polished Chimneystone Slab",
            HABlocks.BUOY.get() to "Buoy",
            HABlocks.BELL_BUOY.get() to "Bell Buoy",
            HABlocks.GIANT_CLAM.get() to "Giant Clam",
            HABlocks.OYSTER.get() to "Oyster",
            HABlocks.CLAMS.get() to "Clam",
            HABlocks.MUSSELS.get() to "Mussel",
            HABlocks.WILD_MUSSELS.get() to "Wild Mussels",
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
            HAItems.SIRENIAN_BEEF.get() to "Sirenian Beef",
            HAItems.SIRENIAN_STEAK.get() to "Sirenian Steak",
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
            HAItems.COOKED_MUSSEL.get() to "Cooked Mussel",
            HAItems.RAW_CRAYFISH.get() to "Raw Crayfish",
            HAItems.COOKED_CRAYFISH.get() to "Cooked Crayfish",
            HAItems.LIONFISH.get() to "Lionfish",
            HAItems.TETRA.get() to "Neon Tetra",
            HAItems.DAMSELFISH.get() to "Damselfish",
            HAItems.DRAGONFISH.get() to "Dragonfish",
            HAItems.BLOBFISH.get() to "Blobfish",
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
            HAItems.STINGRAY.get() to "Stingray",
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
            HAItems.CICHLID.get() to "Cichlid",
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
            HAItems.STARFISH.get() to "Starfish",
            HAItems.PEARL.get() to "Pearl",
            HAItems.BLACK_PEARL.get() to "Black Pearl",
            HAItems.COMICALLY_LARGE_NAUTILUS_SHELL.get() to "Comically Large Nautilus Shell",
            HAItems.ARGONAUT.get() to "Argonaut",
            HAItems.DIVING_WEIGHT.get() to "Diving Weight",
            HAPlatformItems.BRINE_BUCKET.get() to "Brine Bucket",
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
        builder.add("item.hybrid_aquatic.hook.description_tide", "Apply at an angling table") // Tide specific description

        builder.add("tooltip.hybrid_aquatic.argonaut.shell", "%s Shell")
        builder.add("tooltip.hybrid_aquatic.argonaut.sail", "%s Sails")
        builder.add("tooltip.hybrid_aquatic.argonaut.glowing", "Glowing")

        builder.add("tooltip.hybrid_aquatic.ominous_conch.unused", "The deep ocean calls from within..")
        builder.add("tooltip.hybrid_aquatic.ominous_conch.used", "The conch is silent")

        mapOf(
            "item.hybrid_aquatic.hook" to "Needs to be put in the offhand",
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
        builder.add("modmenu.nameTranslation.hybrid_aquatic", "Hybrid Aquatic")

        mapOf(
            HAPaintings.TEST_PAINTING1 to listOf("Test Painting", "Aqua"),
            HAPaintings.TEST_PAINTING2 to listOf("Test Huge Painting", "Aqua"),
            HAPaintings.MELON to listOf("Melon", "Palestine"),
            HAPaintings.PRAYA_DUBIA to listOf("Praya Dubia", "UnorthodoxSketch"),
            HAPaintings.KING_OF_HERRING to listOf("King Of Herring", "UnorthodoxSketch"),
            HAPaintings.PALESTINE_FLAG to listOf("Palestine Flag", "Palestine"),
            HAPaintings.JOLLY_ROGER to listOf("Jolly Roger", "MysticKoko"),
            HAPaintings.PRIDE_FLAG to listOf("Pride Flag", "Be Proud"),
            HAPaintings.GAY_PRIDE_FLAG to listOf("Gay Pride Flag", "Be Proud"),
            HAPaintings.LESBIAN_PRIDE_FLAG to listOf("Lesbian Pride Flag", "Be Proud"),
            HAPaintings.ASEXUAL_PRIDE_FLAG to listOf("Asexual Pride Flag", "Be Proud"),
            HAPaintings.BISEXUAL_PRIDE_FLAG to listOf("Bisexual Pride Flag", "Be Proud"),
            HAPaintings.TRANS_PRIDE_FLAG to listOf("Trans Pride Flag", "Be Proud"),
        ).forEach { (painting, name) ->
            builder.add("painting.hybrid_aquatic.${painting.path}.title", name[0])
            builder.add("painting.hybrid_aquatic.${painting.path}.author", name[1])
        }
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
            HAEntityTypes.BLOBFISH.get() to "Blobfish",
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
            HAEntityTypes.CICHLID.get() to "Cichlid",
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
            // HAEntityTypes.HYPNAUTILUS.get() to "Hypnautilus",
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
            HAEntityTypes.STARFISH_PROJECTILE.get() to "Thrown Starfish",
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
