package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.HABlockFamilies
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.wood.HAPlatformBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.item.HAPlatformItems
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
                HABlocks.ANEMONE.get() to (null to TEMPLATE_ANEMONE),
                HABlocks.STRAWBERRY_ANEMONE.get() to (null to TEMPLATE_ANEMONE),
                HABlocks.GIANT_GREEN_ANEMONE.get() to (null to TEMPLATE_ANEMONE),
                HABlocks.MESSAGE_IN_A_BOTTLE.get() to (Blocks.GLASS to TEMPLATE_MESSAGE_IN_A_BOTTLE),
            ).forEach { (block, info) ->
                val (particleBlock, template) = info

                skipAutoItemBlock(block)

                particleBlock?.let { b -> createAirLikeBlock(block, TextureMapping.getBlockTexture(b)) }
                delegateItemModel(block, template)
            }

            createAirLikeBlock(
                HABlocks.ANEMONE.get(),
                TextureMapping.getBlockTexture(HABlocks.ANEMONE.get(), "_top")
            )

            createAirLikeBlock(
                HABlocks.GIANT_GREEN_ANEMONE.get(),
                TextureMapping.getBlockTexture(HABlocks.GIANT_GREEN_ANEMONE.get(), "_top")
            )

            createAirLikeBlock(
                HABlocks.STRAWBERRY_ANEMONE.get(),
                TextureMapping.getBlockTexture(HABlocks.STRAWBERRY_ANEMONE.get(), "_top")
            )

            // simple cubes
            setOf(
                HABlocks.PEARL_BLOCK.get(),
                HABlocks.BLACK_PEARL_BLOCK.get(),
                HABlocks.WHITE_SAND.get(),
                HABlocks.WHITE_SAND.get(),
                HABlocks.CORALSTONE.get(),
                HABlocks.CRYSTALLINE_SULFUR.get(),
                HABlocks.SHORESTONE.get(),
                HABlocks.BARNACLE_SHORESTONE.get(),
                HABlocks.MARINE_SNOW.get(),
            ).forEach(generator::createTrivialCube)

            setOf(
                HABlocks.SUSPICIOUS_RED_SAND.get(),
            ).forEach(generator::createBrushableBlock)

            setOf(
                HABlocks.DEPTH_CHARGE.get(),
                HABlocks.GRASSY_SAND.get(),
                HABlocks.CUT_WHITE_SANDSTONE.get(),
                HABlocks.CHISELED_WHITE_SANDSTONE.get(),
                HABlocks.WHITE_SANDSTONE.get(),
            ).forEach { block ->
                generator.createTrivialBlock(block, TexturedModel.CUBE_TOP_BOTTOM)
            }

            generator.family(HABlocks.SMOOTH_WHITE_SANDSTONE.get())
                .generateFor(HABlockFamilies.SMOOTH_WHITE_SANDSTONE)

            //#region Crates
            setOf(
                HABlocks.HYBRID_CRATE.get(),
                HABlocks.SPRUCE_CRATE.get(),
                HABlocks.BIRCH_CRATE.get(),
                HABlocks.DARK_OAK_CRATE.get(),
                HABlocks.JUNGLE_CRATE.get(),
                HABlocks.ACACIA_CRATE.get(),
                HABlocks.MANGROVE_CRATE.get(),
                HABlocks.OAK_CRATE.get(),
                HABlocks.CHERRY_CRATE.get(),
                HABlocks.BAMBOO_CRATE.get(),
                HABlocks.AERATED_SAND.get(),
                HABlocks.BUBBLE_GEYSER.get(),
            ).forEach { block ->
                generator.createTrivialBlock(block, TexturedModel.CUBE_TOP)
            }
            //#endregion

            //#region Bleached Corals
            setOf(
                HABlocks.BLEACHED_ROSE_CORAL_BLOCK.get(),
                HABlocks.BLEACHED_SUN_CORAL_BLOCK.get(),
                HABlocks.BLEACHED_LOPHELIA_CORAL_BLOCK.get(),
                HABlocks.BLEACHED_LEAF_CORAL_BLOCK.get(),
                HABlocks.BLEACHED_BUTTON_CORAL_BLOCK.get(),
                HABlocks.BLEACHED_THORN_CORAL_BLOCK.get(),
                HABlocks.BLEACHED_FIRE_CORAL_BLOCK.get(),
                HABlocks.BLEACHED_TUBE_CORAL_BLOCK.get(),
                HABlocks.BLEACHED_HORN_CORAL_BLOCK.get(),
                HABlocks.BLEACHED_BUBBLE_CORAL_BLOCK.get(),
                HABlocks.BLEACHED_BRAIN_CORAL_BLOCK.get(),
            ).forEach { block ->
                generator.createTrivialCube(block)
            }

            setOf(
                HABlocks.BLEACHED_SUN_CORAL.get(),
                HABlocks.BLEACHED_ROSE_CORAL.get(),
                HABlocks.BLEACHED_LOPHELIA_CORAL.get(),
                HABlocks.BLEACHED_LEAF_CORAL.get(),
                HABlocks.BLEACHED_BUTTON_CORAL.get(),
                HABlocks.BLEACHED_THORN_CORAL.get(),

                HABlocks.BLEACHED_FIRE_CORAL.get(),
                HABlocks.BLEACHED_TUBE_CORAL.get(),
                HABlocks.BLEACHED_HORN_CORAL.get(),
                HABlocks.BLEACHED_BUBBLE_CORAL.get(),
                HABlocks.BLEACHED_BRAIN_CORAL.get(),
            ).forEach { block ->
                generator.createCrossBlockWithDefaultItem(block, BlockModelGenerators.TintState.NOT_TINTED)
            }

            generator.createCoralFans(
                HABlocks.BLEACHED_SUN_CORAL_FAN.get(),
                HABlocks.BLEACHED_SUN_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HABlocks.BLEACHED_ROSE_CORAL_FAN.get(),
                HABlocks.BLEACHED_ROSE_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HABlocks.BLEACHED_LOPHELIA_CORAL_FAN.get(),
                HABlocks.BLEACHED_LOPHELIA_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HABlocks.BLEACHED_LEAF_CORAL_FAN.get(),
                HABlocks.BLEACHED_LEAF_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HABlocks.BLEACHED_BUTTON_CORAL_FAN.get(),
                HABlocks.BLEACHED_BUTTON_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HABlocks.BLEACHED_THORN_CORAL_FAN.get(),
                HABlocks.BLEACHED_THORN_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HABlocks.BLEACHED_FIRE_CORAL_FAN.get(),
                HABlocks.BLEACHED_FIRE_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HABlocks.BLEACHED_TUBE_CORAL_FAN.get(),
                HABlocks.BLEACHED_TUBE_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HABlocks.BLEACHED_HORN_CORAL_FAN.get(),
                HABlocks.BLEACHED_HORN_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HABlocks.BLEACHED_BUBBLE_CORAL_FAN.get(),
                HABlocks.BLEACHED_BUBBLE_CORAL_WALL_FAN.get()
            )

            generator.createCoralFans(
                HABlocks.BLEACHED_BRAIN_CORAL_FAN.get(),
                HABlocks.BLEACHED_BRAIN_CORAL_WALL_FAN.get()
            )
            //#endregion

            // wood
            val driftwoodPool = family(HAPlatformBlocks.DRIFTWOOD_PLANKS.get())

            woodProvider(HAPlatformBlocks.DRIFTWOOD_LOG.get()).log(HAPlatformBlocks.DRIFTWOOD_LOG.get())
                .wood(HAPlatformBlocks.DRIFTWOOD_WOOD.get())
            woodProvider(HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get()).log(HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get())
                .wood(HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get())

            createDoor(HAPlatformBlocks.DRIFTWOOD_DOOR.get())
            createTrapdoor(HAPlatformBlocks.DRIFTWOOD_TRAPDOOR.get())

            driftwoodPool.stairs(HAPlatformBlocks.DRIFTWOOD_STAIRS.get())
            driftwoodPool.slab(HAPlatformBlocks.DRIFTWOOD_SLAB.get())
            driftwoodPool.button(HAPlatformBlocks.DRIFTWOOD_BUTTON.get())
            driftwoodPool.pressurePlate(HAPlatformBlocks.DRIFTWOOD_PRESSURE_PLATE.get())
            driftwoodPool.fence(HAPlatformBlocks.DRIFTWOOD_FENCE.get())
            driftwoodPool.fenceGate(HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get())

            createNormalTorch(HABlocks.GLOWSTICK.get(), HABlocks.WALL_GLOWSTICK.get())

            createCropBlock(HABlocks.CLAMS.get(),CropBlock.AGE, 0, 0, 0, 1, 1, 2, 2, 3)

            //#region Corals
            createCoral(
                HABlocks.LOPHELIA_CORAL.get(),
                HABlocks.DEAD_LOPHELIA_CORAL.get(),
                HABlocks.LOPHELIA_CORAL_BLOCK.get(),
                HABlocks.DEAD_LOPHELIA_CORAL_BLOCK.get(),
                HABlocks.LOPHELIA_CORAL_FAN.get(),
                HABlocks.DEAD_LOPHELIA_CORAL_FAN.get(),
                HABlocks.LOPHELIA_CORAL_WALL_FAN.get(),
                HABlocks.DEAD_LOPHELIA_CORAL_WALL_FAN.get()
            )

            createCoral(
                HABlocks.ROSE_CORAL.get(),
                HABlocks.DEAD_ROSE_CORAL.get(),
                HABlocks.ROSE_CORAL_BLOCK.get(),
                HABlocks.DEAD_ROSE_CORAL_BLOCK.get(),
                HABlocks.ROSE_CORAL_FAN.get(),
                HABlocks.DEAD_ROSE_CORAL_FAN.get(),
                HABlocks.ROSE_CORAL_WALL_FAN.get(),
                HABlocks.DEAD_ROSE_CORAL_WALL_FAN.get()
            )

            createCoral(
                HABlocks.LEAF_CORAL.get(),
                HABlocks.DEAD_LEAF_CORAL.get(),
                HABlocks.LEAF_CORAL_BLOCK.get(),
                HABlocks.DEAD_LEAF_CORAL_BLOCK.get(),
                HABlocks.LEAF_CORAL_FAN.get(),
                HABlocks.DEAD_LEAF_CORAL_FAN.get(),
                HABlocks.LEAF_CORAL_WALL_FAN.get(),
                HABlocks.DEAD_LEAF_CORAL_WALL_FAN.get()
            )

            createCoral(
                HABlocks.BUTTON_CORAL.get(),
                HABlocks.DEAD_BUTTON_CORAL.get(),
                HABlocks.BUTTON_CORAL_BLOCK.get(),
                HABlocks.DEAD_BUTTON_CORAL_BLOCK.get(),
                HABlocks.BUTTON_CORAL_FAN.get(),
                HABlocks.DEAD_BUTTON_CORAL_FAN.get(),
                HABlocks.BUTTON_CORAL_WALL_FAN.get(),
                HABlocks.DEAD_BUTTON_CORAL_WALL_FAN.get()
            )

            createCoral(
                HABlocks.THORN_CORAL.get(),
                HABlocks.DEAD_THORN_CORAL.get(),
                HABlocks.THORN_CORAL_BLOCK.get(),
                HABlocks.DEAD_THORN_CORAL_BLOCK.get(),
                HABlocks.THORN_CORAL_FAN.get(),
                HABlocks.DEAD_THORN_CORAL_FAN.get(),
                HABlocks.THORN_CORAL_WALL_FAN.get(),
                HABlocks.DEAD_THORN_CORAL_WALL_FAN.get()
            )

            createCoral(
                HABlocks.SUN_CORAL.get(),
                HABlocks.DEAD_SUN_CORAL.get(),
                HABlocks.SUN_CORAL_BLOCK.get(),
                HABlocks.DEAD_SUN_CORAL_BLOCK.get(),
                HABlocks.SUN_CORAL_FAN.get(),
                HABlocks.DEAD_SUN_CORAL_FAN.get(),
                HABlocks.SUN_CORAL_WALL_FAN.get(),
                HABlocks.DEAD_SUN_CORAL_WALL_FAN.get()
            )
            //#endregion

            createCrossBlockWithDefaultItem(
                HABlocks.HARP_SPONGE.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createCrossBlock(
                HABlocks.SARGASSUM_PLANT.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createCrossBlock(
                HABlocks.SARGASSUM.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createCrossBlock(
                HABlocks.BULL_KELP_PLANT.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createCrossBlock(
                HABlocks.BULL_KELP.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createCrossBlock(
                HABlocks.SEA_LETTUCE.get(),
                BlockModelGenerators.TintState.NOT_TINTED,
            )

            createNonTemplateModelBlock(
                HABlocks.DECORATIVE_BUBBLE_COLUMN.get(),
                Blocks.WATER
            )

            createNonTemplateModelBlock(
                HABlocks.BUBBLE_NET.get(),
                Blocks.WATER
            )
        }
    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        //#region Flat Items
        setOf(
            HAItems.BUOY.get(),
            HAPlatformItems.DUNEGRASS.get(),
            HAPlatformItems.TALL_DUNEGRASS.get(),
            HAPlatformItems.CATTAIL.get(),
            HAItems.RED_ALGAE.get(),
            HAItems.SHORT_RED_ALGAE.get(),
            HAItems.SEA_LETTUCE.get(),
            HAItems.SARGASSUM.get(),
            HAItems.BULL_KELP.get(),
            HAItems.TUBE_SPONGE.get(),
            HAItems.UNI.get(),
            HAItems.RAW_CRAYFISH.get(),
            HAItems.COCONUT_CRAB_CLAW.get(),
            HAItems.DUNGENESS_CRAB_CLAW.get(),
            HAItems.FIDDLER_CRAB_CLAW.get(),
            HAItems.FLOWER_CRAB_CLAW.get(),
            HAItems.GHOST_CRAB_CLAW.get(),
            HAItems.LIGHTFOOT_CRAB_CLAW.get(),
            HAItems.LOBSTER_CLAW.get(),
            HAItems.SPIDER_CRAB_CLAW.get(),
            HAItems.VAMPIRE_CRAB_CLAW.get(),
            HAItems.YETI_CRAB_CLAW.get(),
            HAItems.RAW_CRAB.get(),
            HAItems.COOKED_CRAB.get(),
            HAItems.RAW_SHRIMP.get(),
            HAItems.COOKED_SHRIMP.get(),
            HAItems.COOKED_CLAM.get(),
            HAItems.COOKED_CRAYFISH.get(),
            HAItems.RAW_LOBSTER.get(),
            HAItems.COOKED_LOBSTER.get(),
            HAItems.RAW_LOBSTER_TAIL.get(),
            HAItems.COOKED_LOBSTER_TAIL.get(),
            HAItems.RAW_FISH_STEAK.get(),
            HAItems.COOKED_FISH_STEAK.get(),
            HAItems.RAW_FISH_MEAT.get(),
            HAItems.COOKED_FISH_MEAT.get(),
            HAItems.RAW_TENTACLE.get(),
            HAItems.COOKED_TENTACLE.get(),
            HAItems.GLOWSLIME.get(),
            HAItems.SHARK_TOOTH.get(),
            HAItems.PRISMARINE_ROD.get(),
            HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get(),
            HAItems.PEARL.get(),
            HAItems.BLACK_PEARL.get(),
            HAItems.GIANT_NAUTILUS_SHELL.get(),
            HAItems.ARGONAUT.get(),
            HAItems.DIVING_WEIGHT.get(),
            HAItems.SULFUR.get(),
            HAItems.ANGLERFISH.get(),
            HAItems.BARRELEYE.get(),
            HAItems.BETTA.get(),
            HAItems.PEARLFISH.get(),
            HAItems.SNAILFISH.get(),
            HAItems.BLUE_SPOTTED_STINGRAY.get(),
            HAItems.SURGEONFISH.get(),
            HAItems.CLOWNFISH.get(),
            HAItems.DAMSELFISH.get(),
            HAItems.JOHN_DORY.get(),
            HAItems.BOXFISH.get(),
            HAItems.DANIO.get(),
            HAItems.DISCUS.get(),
            HAItems.DRAGONFISH.get(),
            HAItems.FLASHLIGHT_FISH.get(),
            HAItems.GOURAMI.get(),
            HAItems.LIONFISH.get(),
            HAItems.MAHI.get(),
            HAItems.MORAY_EEL.get(),
            HAItems.NEEDLEFISH.get(),
            HAItems.MACKEREL.get(),
            HAItems.HERRING.get(),
            HAItems.FLYING_FISH.get(),
            HAItems.SQUIRRELFISH.get(),
            HAItems.COELACANTH.get(),
            HAItems.GOLDEN_DORADO.get(),
            HAItems.OPAH.get(),
            HAItems.OARFISH.get(),
            HAItems.OSCAR.get(),
            HAItems.PIRANHA.get(),
            HAItems.RATFISH.get(),
            HAItems.ROCKFISH.get(),
            HAItems.SEA_BASS.get(),
            HAItems.NEON_TETRA.get(),
            HAItems.TIGER_BARB.get(),
            HAItems.TRIGGERFISH.get(),
            HAItems.TREVALLY.get(),
            HAItems.TUNA.get(),
            HAItems.STONEFISH.get(),
            HAItems.BLOWFISH.get(),
            HAItems.PARROTFISH.get(),
            HAItems.SHEEPSHEAD_WRASSE.get(),
            HAItems.OCEAN_SUNFISH.get(),
            HAItems.CARP.get(),
            HAItems.TROUT.get(),
            HAItems.SUNFISH.get(),
            HAItems.PLECO.get(),
            HAItems.GOLDFISH.get(),
            HAItems.SPOTTED_EAGLE_RAY.get(),
            HAItems.SEAHORSE.get(),
            HAItems.CUTTLEBONE.get(),
            HAItems.SEA_URCHIN_SPINE.get(),
            HAItems.CORAL_CHUNK.get(),
            HAItems.BARBED_HOOK.get(),
            HAItems.GLOWING_HOOK.get(),
            HAItems.MAGNETIC_HOOK.get(),
            HAItems.CREEPERMAGNET_HOOK.get(),
            HAItems.OMINOUS_HOOK.get(),
            HAItems.DIVING_HELMET.get(),
            HAItems.DIVING_SUIT.get(),
            HAItems.DIVING_LEGGINGS.get(),
            HAItems.DIVING_BOOTS.get(),
            HAItems.REINFORCED_DIVING_HELMET.get(),
            HAItems.REINFORCED_DIVING_SUIT.get(),
            HAItems.REINFORCED_DIVING_LEGGINGS.get(),
            HAItems.REINFORCED_DIVING_BOOTS.get(),
            HAItems.GLOWING_DIVING_HELMET.get(),
            HAItems.GLOWING_DIVING_SUIT.get(),
            HAItems.GLOWING_DIVING_LEGGINGS.get(),
            HAItems.GLOWING_DIVING_BOOTS.get(),
            HAItems.NAUTILUS_HELMET.get(),
            HAItems.NAUTILUS_PAULDRONS.get(),
            HAItems.MANGLERFISH_LURE.get(),
            HAItems.MANGLERFISH_FIN.get(),
            HAItems.TURTLE_CHESTPLATE.get(),
            HAItems.EEL_SCARF.get(),
            HAItems.PINK_HATXOLOTL.get(),
            HAItems.BROWN_HATXOLOTL.get(),
            HAItems.CYAN_HATXOLOTL.get(),
            HAItems.BLUE_HATXOLOTL.get(),
            HAItems.GOLD_HATXOLOTL.get(),
            HAItems.BROWN_HATXOLOTL.get(),
            HAItems.MOON_JELLYFISH_HAT.get(),
            HAItems.SEA_MESSAGE_BOOK.get(),
        ).forEach { item ->
            generator.generateFlatItem(item, ModelTemplates.FLAT_ITEM)
        }
        //#endregion

        //#region Handheld Models
        setOf(
            HAItems.SEASHELL_SPEAR,
            HAItems.SEASHELL_PICKAXE,
            HAItems.SEASHELL_AXE,
            HAItems.SEASHELL_SHOVEL,
            HAItems.SEASHELL_HOE,
            HAItems.CORAL_BLADE,
            HAItems.CORAL_PICKAXE,
            HAItems.CORAL_AXE,
            HAItems.CORAL_SHOVEL,
            HAItems.CORAL_HOE
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