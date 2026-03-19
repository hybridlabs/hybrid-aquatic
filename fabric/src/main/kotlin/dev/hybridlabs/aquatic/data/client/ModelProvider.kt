package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.HybridAquaticPlatformItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.SpawnEggItem
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.LiquidBlock

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockModelGenerators) {
        generator.run {
            //#region Plushies
            BuiltInRegistries.BLOCK
                .filterIsInstance<PlushieBlock>()
                .forEach { block ->
                    skipAutoItemBlock(block)
                    createAirLikeBlock(block, TextureMapping.getBlockTexture(block.particleBlock))
                    delegateItemModel(block, TEMPLATE_PLUSHIE)
                }

            //#region Fluids
            BuiltInRegistries.BLOCK
                .filterIsInstance<LiquidBlock>()
                .forEach { block ->
                    val id = BuiltInRegistries.BLOCK.getKey(block)
                    if (id.namespace == Constants.MOD_ID) {
                        createNonTemplateModelBlock(block)
                    }
                }

            //#region Spawn Eggs
            BuiltInRegistries.ITEM
                .filter(filterHybridAquatic(BuiltInRegistries.ITEM))
                .forEach { item ->
                    if (item is SpawnEggItem) {
                        delegateItemModel(item, ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"))
                    }
                }

            // builtin
            mapOf<Block, Pair<Block?, ResourceLocation>>(
                HybridAquaticBlocks.ANEMONE.get() to (null to TEMPLATE_ANEMONE),
                HybridAquaticBlocks.STRAWBERRY_ANEMONE.get() to (null to TEMPLATE_ANEMONE),
                HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get() to (null to TEMPLATE_ANEMONE),
                HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get() to (Blocks.GLASS to TEMPLATE_MESSAGE_IN_A_BOTTLE),
            ).forEach { (block, info) ->
                val (particleBlock, template) = info

                skipAutoItemBlock(block)

                particleBlock?.let { b -> createAirLikeBlock(block, TextureMapping.getBlockTexture(b)) }
                delegateItemModel(block, template)
            }

            createAirLikeBlock(
                HybridAquaticBlocks.ANEMONE.get(),
                TextureMapping.getBlockTexture(HybridAquaticBlocks.ANEMONE.get(), "_top")
            )

            createAirLikeBlock(
                HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get(),
                TextureMapping.getBlockTexture(HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get(), "_top")
            )

            createAirLikeBlock(
                HybridAquaticBlocks.STRAWBERRY_ANEMONE.get(),
                TextureMapping.getBlockTexture(HybridAquaticBlocks.STRAWBERRY_ANEMONE.get(), "_top")
            )

            // simple cubes
            setOf(
                HybridAquaticBlocks.PEARL_BLOCK.get(),
                HybridAquaticBlocks.BLACK_PEARL_BLOCK.get(),
                HybridAquaticBlocks.WHITE_SAND.get(),
                HybridAquaticBlocks.WHITE_SAND.get(),
                HybridAquaticBlocks.CORALSTONE.get(),
                HybridAquaticBlocks.CRYSTALLINE_SULFUR.get(),
                HybridAquaticBlocks.SHORESTONE.get(),
                HybridAquaticBlocks.BARNACLE_SHORESTONE.get(),
                HybridAquaticBlocks.MARINE_SNOW.get(),
            ).forEach(generator::createTrivialCube)

            setOf(
                HybridAquaticBlocks.SUSPICIOUS_RED_SAND.get(),
            ).forEach(generator::createBrushableBlock)

            setOf(
                HybridAquaticBlocks.DEPTH_CHARGE.get(),
                HybridAquaticBlocks.GRASSY_SAND.get(),
                HybridAquaticBlocks.WHITE_SANDSTONE.get(),
            ).forEach { block ->
                generator.createTrivialBlock(block, TexturedModel.CUBE_TOP_BOTTOM)
            }

            //#region Crates
            setOf(
                HybridAquaticBlocks.HYBRID_CRATE.get(),
                HybridAquaticBlocks.SPRUCE_CRATE.get(),
                HybridAquaticBlocks.BIRCH_CRATE.get(),
                HybridAquaticBlocks.DARK_OAK_CRATE.get(),
                HybridAquaticBlocks.JUNGLE_CRATE.get(),
                HybridAquaticBlocks.ACACIA_CRATE.get(),
                HybridAquaticBlocks.MANGROVE_CRATE.get(),
                HybridAquaticBlocks.OAK_CRATE.get(),
                HybridAquaticBlocks.CHERRY_CRATE.get(),
                HybridAquaticBlocks.BAMBOO_CRATE.get(),
                HybridAquaticBlocks.AERATED_SAND.get(),
                HybridAquaticBlocks.BUBBLE_GEYSER.get(),
            ).forEach { block ->
                generator.createTrivialBlock(block, TexturedModel.CUBE_TOP)
            }
            //#endregion

            //#region Bleached Corals
            setOf(
                HybridAquaticBlocks.BLEACHED_ROSE_CORAL_BLOCK.get(),
                HybridAquaticBlocks.BLEACHED_SUN_CORAL_BLOCK.get(),
                HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL_BLOCK.get(),
                HybridAquaticBlocks.BLEACHED_LEAF_CORAL_BLOCK.get(),
                HybridAquaticBlocks.BLEACHED_BUTTON_CORAL_BLOCK.get(),
                HybridAquaticBlocks.BLEACHED_THORN_CORAL_BLOCK.get(),
                HybridAquaticBlocks.BLEACHED_FIRE_CORAL_BLOCK.get(),
                HybridAquaticBlocks.BLEACHED_TUBE_CORAL_BLOCK.get(),
                HybridAquaticBlocks.BLEACHED_HORN_CORAL_BLOCK.get(),
                HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL_BLOCK.get(),
                HybridAquaticBlocks.BLEACHED_BRAIN_CORAL_BLOCK.get(),
            ).forEach { block ->
                generator.createTrivialCube(block)
            }

            setOf(
                HybridAquaticBlocks.BLEACHED_SUN_CORAL.get(),
                HybridAquaticBlocks.BLEACHED_ROSE_CORAL.get(),
                HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL.get(),
                HybridAquaticBlocks.BLEACHED_LEAF_CORAL.get(),
                HybridAquaticBlocks.BLEACHED_BUTTON_CORAL.get(),
                HybridAquaticBlocks.BLEACHED_THORN_CORAL.get(),

                HybridAquaticBlocks.BLEACHED_FIRE_CORAL.get(),
                HybridAquaticBlocks.BLEACHED_TUBE_CORAL.get(),
                HybridAquaticBlocks.BLEACHED_HORN_CORAL.get(),
                HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL.get(),
                HybridAquaticBlocks.BLEACHED_BRAIN_CORAL.get(),
            ).forEach { block ->
                generator.createCrossBlockWithDefaultItem(block, BlockModelGenerators.TintState.NOT_TINTED)
            }

            generator.createCoralFans(
                HybridAquaticBlocks.BLEACHED_SUN_CORAL_FAN.get(),
                HybridAquaticBlocks.BLEACHED_SUN_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HybridAquaticBlocks.BLEACHED_ROSE_CORAL_FAN.get(),
                HybridAquaticBlocks.BLEACHED_ROSE_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL_FAN.get(),
                HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HybridAquaticBlocks.BLEACHED_LEAF_CORAL_FAN.get(),
                HybridAquaticBlocks.BLEACHED_LEAF_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HybridAquaticBlocks.BLEACHED_BUTTON_CORAL_FAN.get(),
                HybridAquaticBlocks.BLEACHED_BUTTON_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HybridAquaticBlocks.BLEACHED_THORN_CORAL_FAN.get(),
                HybridAquaticBlocks.BLEACHED_THORN_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HybridAquaticBlocks.BLEACHED_FIRE_CORAL_FAN.get(),
                HybridAquaticBlocks.BLEACHED_FIRE_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HybridAquaticBlocks.BLEACHED_TUBE_CORAL_FAN.get(),
                HybridAquaticBlocks.BLEACHED_TUBE_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HybridAquaticBlocks.BLEACHED_HORN_CORAL_FAN.get(),
                HybridAquaticBlocks.BLEACHED_HORN_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL_FAN.get(),
                HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HybridAquaticBlocks.BLEACHED_BRAIN_CORAL_FAN.get(),
                HybridAquaticBlocks.BLEACHED_BRAIN_CORAL_WALL_FAN.get()
            )
            //#endregion

            // wood
            val driftwoodPool = family(HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get())

            woodProvider(HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get()).log(HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get())
                .wood(HybridAquaticPlatformBlocks.DRIFTWOOD_WOOD.get())
            woodProvider(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get()).log(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get())
                .wood(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get())

            createDoor(HybridAquaticPlatformBlocks.DRIFTWOOD_DOOR.get())
            createTrapdoor(HybridAquaticPlatformBlocks.DRIFTWOOD_TRAPDOOR.get())

            driftwoodPool.stairs(HybridAquaticPlatformBlocks.DRIFTWOOD_STAIRS.get())
            driftwoodPool.slab(HybridAquaticPlatformBlocks.DRIFTWOOD_SLAB.get())
            driftwoodPool.button(HybridAquaticPlatformBlocks.DRIFTWOOD_BUTTON.get())
            driftwoodPool.pressurePlate(HybridAquaticPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get())
            driftwoodPool.fence(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE.get())
            driftwoodPool.fenceGate(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE_GATE.get())

            createNormalTorch(HybridAquaticBlocks.GLOWSTICK.get(), HybridAquaticBlocks.WALL_GLOWSTICK.get())

            createCropBlock(HybridAquaticBlocks.CLAMS.get(),CropBlock.AGE, 0, 0, 0, 1, 1, 2, 2, 3)

            //#region Corals
            createCoral(
                HybridAquaticBlocks.LOPHELIA_CORAL.get(),
                HybridAquaticBlocks.DEAD_LOPHELIA_CORAL.get(),
                HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK.get(),
                HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK.get(),
                HybridAquaticBlocks.LOPHELIA_CORAL_FAN.get(),
                HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN.get(),
                HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN.get(),
                HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_WALL_FAN.get()
            )

            createCoral(
                HybridAquaticBlocks.ROSE_CORAL.get(),
                HybridAquaticBlocks.DEAD_ROSE_CORAL.get(),
                HybridAquaticBlocks.ROSE_CORAL_BLOCK.get(),
                HybridAquaticBlocks.DEAD_ROSE_CORAL_BLOCK.get(),
                HybridAquaticBlocks.ROSE_CORAL_FAN.get(),
                HybridAquaticBlocks.DEAD_ROSE_CORAL_FAN.get(),
                HybridAquaticBlocks.ROSE_CORAL_WALL_FAN.get(),
                HybridAquaticBlocks.DEAD_ROSE_CORAL_WALL_FAN.get()
            )

            createCoral(
                HybridAquaticBlocks.LEAF_CORAL.get(),
                HybridAquaticBlocks.DEAD_LEAF_CORAL.get(),
                HybridAquaticBlocks.LEAF_CORAL_BLOCK.get(),
                HybridAquaticBlocks.DEAD_LEAF_CORAL_BLOCK.get(),
                HybridAquaticBlocks.LEAF_CORAL_FAN.get(),
                HybridAquaticBlocks.DEAD_LEAF_CORAL_FAN.get(),
                HybridAquaticBlocks.LEAF_CORAL_WALL_FAN.get(),
                HybridAquaticBlocks.DEAD_LEAF_CORAL_WALL_FAN.get()
            )

            createCoral(
                HybridAquaticBlocks.BUTTON_CORAL.get(),
                HybridAquaticBlocks.DEAD_BUTTON_CORAL.get(),
                HybridAquaticBlocks.BUTTON_CORAL_BLOCK.get(),
                HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK.get(),
                HybridAquaticBlocks.BUTTON_CORAL_FAN.get(),
                HybridAquaticBlocks.DEAD_BUTTON_CORAL_FAN.get(),
                HybridAquaticBlocks.BUTTON_CORAL_WALL_FAN.get(),
                HybridAquaticBlocks.DEAD_BUTTON_CORAL_WALL_FAN.get()
            )

            createCoral(
                HybridAquaticBlocks.THORN_CORAL.get(),
                HybridAquaticBlocks.DEAD_THORN_CORAL.get(),
                HybridAquaticBlocks.THORN_CORAL_BLOCK.get(),
                HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK.get(),
                HybridAquaticBlocks.THORN_CORAL_FAN.get(),
                HybridAquaticBlocks.DEAD_THORN_CORAL_FAN.get(),
                HybridAquaticBlocks.THORN_CORAL_WALL_FAN.get(),
                HybridAquaticBlocks.DEAD_THORN_CORAL_WALL_FAN.get()
            )

            createCoral(
                HybridAquaticBlocks.SUN_CORAL.get(),
                HybridAquaticBlocks.DEAD_SUN_CORAL.get(),
                HybridAquaticBlocks.SUN_CORAL_BLOCK.get(),
                HybridAquaticBlocks.DEAD_SUN_CORAL_BLOCK.get(),
                HybridAquaticBlocks.SUN_CORAL_FAN.get(),
                HybridAquaticBlocks.DEAD_SUN_CORAL_FAN.get(),
                HybridAquaticBlocks.SUN_CORAL_WALL_FAN.get(),
                HybridAquaticBlocks.DEAD_SUN_CORAL_WALL_FAN.get()
            )
            //#endregion

            createCrossBlockWithDefaultItem(
                HybridAquaticBlocks.HARP_SPONGE.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createCrossBlock(
                HybridAquaticBlocks.SARGASSUM_PLANT.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createCrossBlock(
                HybridAquaticBlocks.SARGASSUM.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createCrossBlock(
                HybridAquaticBlocks.BULL_KELP_PLANT.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createCrossBlock(
                HybridAquaticBlocks.BULL_KELP.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createCrossBlock(
                HybridAquaticBlocks.SEA_LETTUCE.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createNonTemplateModelBlock(
                HybridAquaticBlocks.DECORATIVE_BUBBLE_COLUMN.get(),
                Blocks.WATER
            )

            createNonTemplateModelBlock(
                HybridAquaticBlocks.BUBBLE_NET.get(),
                Blocks.WATER
            )
        }
    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        //#region Flat Items
        setOf(
            HybridAquaticItems.BUOY.get(),
            HybridAquaticPlatformItems.DUNEGRASS.get(),
            HybridAquaticPlatformItems.TALL_DUNEGRASS.get(),
            HybridAquaticPlatformItems.CATTAIL.get(),
            HybridAquaticItems.RED_ALGAE.get(),
            HybridAquaticItems.SHORT_RED_ALGAE.get(),
            HybridAquaticItems.SEA_LETTUCE.get(),
            HybridAquaticItems.SARGASSUM.get(),
            HybridAquaticItems.BULL_KELP.get(),
            HybridAquaticItems.TUBE_SPONGE.get(),
            HybridAquaticItems.UNI.get(),
            HybridAquaticItems.RAW_CRAYFISH.get(),
            HybridAquaticItems.COCONUT_CRAB_CLAW.get(),
            HybridAquaticItems.DUNGENESS_CRAB_CLAW.get(),
            HybridAquaticItems.FIDDLER_CRAB_CLAW.get(),
            HybridAquaticItems.FLOWER_CRAB_CLAW.get(),
            HybridAquaticItems.GHOST_CRAB_CLAW.get(),
            HybridAquaticItems.LIGHTFOOT_CRAB_CLAW.get(),
            HybridAquaticItems.LOBSTER_CLAW.get(),
            HybridAquaticItems.SPIDER_CRAB_CLAW.get(),
            HybridAquaticItems.VAMPIRE_CRAB_CLAW.get(),
            HybridAquaticItems.YETI_CRAB_CLAW.get(),
            HybridAquaticItems.RAW_CRAB.get(),
            HybridAquaticItems.COOKED_CRAB.get(),
            HybridAquaticItems.RAW_SHRIMP.get(),
            HybridAquaticItems.COOKED_SHRIMP.get(),
            HybridAquaticItems.COOKED_CLAM.get(),
            HybridAquaticItems.COOKED_CRAYFISH.get(),
            HybridAquaticItems.RAW_LOBSTER.get(),
            HybridAquaticItems.COOKED_LOBSTER.get(),
            HybridAquaticItems.RAW_LOBSTER_TAIL.get(),
            HybridAquaticItems.COOKED_LOBSTER_TAIL.get(),
            HybridAquaticItems.RAW_FISH_STEAK.get(),
            HybridAquaticItems.COOKED_FISH_STEAK.get(),
            HybridAquaticItems.RAW_FISH_MEAT.get(),
            HybridAquaticItems.COOKED_FISH_MEAT.get(),
            HybridAquaticItems.RAW_TENTACLE.get(),
            HybridAquaticItems.COOKED_TENTACLE.get(),
            HybridAquaticItems.GLOWSLIME.get(),
            HybridAquaticItems.SHARK_TOOTH.get(),
            HybridAquaticItems.PRISMARINE_ROD.get(),
            HybridAquaticItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get(),
            HybridAquaticItems.PEARL.get(),
            HybridAquaticItems.BLACK_PEARL.get(),
            HybridAquaticItems.GIANT_NAUTILUS_SHELL.get(),
            HybridAquaticItems.ARGONAUT.get(),
            HybridAquaticItems.DIVING_WEIGHT.get(),
            HybridAquaticItems.SULFUR.get(),
            HybridAquaticItems.ANGLERFISH.get(),
            HybridAquaticItems.BARRELEYE.get(),
            HybridAquaticItems.BETTA.get(),
            HybridAquaticItems.PEARLFISH.get(),
            HybridAquaticItems.SNAILFISH.get(),
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY.get(),
            HybridAquaticItems.SURGEONFISH.get(),
            HybridAquaticItems.CLOWNFISH.get(),
            HybridAquaticItems.DAMSELFISH.get(),
            HybridAquaticItems.JOHN_DORY.get(),
            HybridAquaticItems.BOXFISH.get(),
            HybridAquaticItems.DANIO.get(),
            HybridAquaticItems.DISCUS.get(),
            HybridAquaticItems.DRAGONFISH.get(),
            HybridAquaticItems.FLASHLIGHT_FISH.get(),
            HybridAquaticItems.GOURAMI.get(),
            HybridAquaticItems.LIONFISH.get(),
            HybridAquaticItems.MAHI.get(),
            HybridAquaticItems.MORAY_EEL.get(),
            HybridAquaticItems.NEEDLEFISH.get(),
            HybridAquaticItems.MACKEREL.get(),
            HybridAquaticItems.HERRING.get(),
            HybridAquaticItems.FLYING_FISH.get(),
            HybridAquaticItems.SQUIRRELFISH.get(),
            HybridAquaticItems.COELACANTH.get(),
            HybridAquaticItems.GOLDEN_DORADO.get(),
            HybridAquaticItems.OPAH.get(),
            HybridAquaticItems.OARFISH.get(),
            HybridAquaticItems.OSCAR.get(),
            HybridAquaticItems.PIRANHA.get(),
            HybridAquaticItems.RATFISH.get(),
            HybridAquaticItems.ROCKFISH.get(),
            HybridAquaticItems.SEA_BASS.get(),
            HybridAquaticItems.NEON_TETRA.get(),
            HybridAquaticItems.TIGER_BARB.get(),
            HybridAquaticItems.TRIGGERFISH.get(),
            HybridAquaticItems.TUNA.get(),
            HybridAquaticItems.STONEFISH.get(),
            HybridAquaticItems.BLOWFISH.get(),
            HybridAquaticItems.PARROTFISH.get(),
            HybridAquaticItems.SHEEPSHEAD_WRASSE.get(),
            HybridAquaticItems.OCEAN_SUNFISH.get(),
            HybridAquaticItems.CARP.get(),
            HybridAquaticItems.TROUT.get(),
            HybridAquaticItems.SUNFISH.get(),
            HybridAquaticItems.PLECO.get(),
            HybridAquaticItems.GOLDFISH.get(),
            HybridAquaticItems.SPOTTED_EAGLE_RAY.get(),
            HybridAquaticItems.SEAHORSE.get(),
            HybridAquaticItems.CUTTLEBONE.get(),
            HybridAquaticItems.SEA_URCHIN_SPINE.get(),
            HybridAquaticItems.CORAL_CHUNK.get(),
            HybridAquaticItems.BARBED_HOOK.get(),
            HybridAquaticItems.GLOWING_HOOK.get(),
            HybridAquaticItems.MAGNETIC_HOOK.get(),
            HybridAquaticItems.CREEPERMAGNET_HOOK.get(),
            HybridAquaticItems.OMINOUS_HOOK.get(),
            HybridAquaticItems.DIVING_HELMET.get(),
            HybridAquaticItems.DIVING_SUIT.get(),
            HybridAquaticItems.DIVING_LEGGINGS.get(),
            HybridAquaticItems.DIVING_BOOTS.get(),
            HybridAquaticItems.REINFORCED_DIVING_HELMET.get(),
            HybridAquaticItems.REINFORCED_DIVING_SUIT.get(),
            HybridAquaticItems.REINFORCED_DIVING_LEGGINGS.get(),
            HybridAquaticItems.REINFORCED_DIVING_BOOTS.get(),
            HybridAquaticItems.GLOWING_DIVING_HELMET.get(),
            HybridAquaticItems.GLOWING_DIVING_SUIT.get(),
            HybridAquaticItems.GLOWING_DIVING_LEGGINGS.get(),
            HybridAquaticItems.GLOWING_DIVING_BOOTS.get(),
            HybridAquaticItems.NAUTILUS_HELMET.get(),
            HybridAquaticItems.NAUTILUS_PAULDRONS.get(),
            HybridAquaticItems.MANGLERFISH_LURE.get(),
            HybridAquaticItems.MANGLERFISH_FIN.get(),
            HybridAquaticItems.TURTLE_CHESTPLATE.get(),
            HybridAquaticItems.EEL_SCARF.get(),
            HybridAquaticItems.MOON_JELLYFISH_HAT.get(),
            HybridAquaticItems.SEA_MESSAGE_BOOK.get(),
        ).forEach { item ->
            generator.generateFlatItem(item, ModelTemplates.FLAT_ITEM)
        }
        //#endregion

        //#region Handheld Models
        setOf(
            HybridAquaticItems.SEASHELL_SPEAR,
            HybridAquaticItems.SEASHELL_PICKAXE,
            HybridAquaticItems.SEASHELL_AXE,
            HybridAquaticItems.SEASHELL_SHOVEL,
            HybridAquaticItems.SEASHELL_HOE,
            HybridAquaticItems.CORAL_BLADE,
            HybridAquaticItems.CORAL_PICKAXE,
            HybridAquaticItems.CORAL_AXE,
            HybridAquaticItems.CORAL_SHOVEL,
            HybridAquaticItems.CORAL_HOE
        ).forEach { item ->
            generator.generateFlatItem(item.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
        }
        //#endregion
    }

    companion object {
        private val TEMPLATE_ANEMONE = CommonClass.locate("item/template_anemone")
        private val TEMPLATE_MESSAGE_IN_A_BOTTLE = CommonClass.locate("item/template_message_in_a_bottle")
        private val TEMPLATE_PLUSHIE = CommonClass.locate("item/template_plushie")
    }
}