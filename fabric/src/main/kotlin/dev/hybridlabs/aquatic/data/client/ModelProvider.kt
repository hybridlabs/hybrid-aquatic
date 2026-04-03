package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.HABlockFamilies
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.wood.HAPlatformBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HAAquaticItems
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
            ).forEach { block ->
                generator.createTrivialBlock(block, TexturedModel.CUBE_TOP_BOTTOM)
            }

            generator.family(HABlocks.WHITE_SANDSTONE.get())
                .generateFor(HABlockFamilies.WHITE_SANDSTONE)

            generator.family(HABlocks.SMOOTH_WHITE_SANDSTONE.get())
                .generateFor(HABlockFamilies.SMOOTH_WHITE_SANDSTONE)

            generator.family(HABlocks.CUT_WHITE_SANDSTONE.get())
                .generateFor(HABlockFamilies.CUT_WHITE_SANDSTONE)

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
            HAAquaticItems.BUOY.get(),
            HAPlatformItems.DUNEGRASS.get(),
            HAPlatformItems.TALL_DUNEGRASS.get(),
            HAPlatformItems.CATTAIL.get(),
            HAAquaticItems.RED_ALGAE.get(),
            HAAquaticItems.SHORT_RED_ALGAE.get(),
            HAAquaticItems.SEA_LETTUCE.get(),
            HAAquaticItems.SARGASSUM.get(),
            HAAquaticItems.BULL_KELP.get(),
            HAAquaticItems.TUBE_SPONGE.get(),
            HAAquaticItems.UNI.get(),
            HAAquaticItems.RAW_CRAYFISH.get(),
            HAAquaticItems.COCONUT_CRAB_CLAW.get(),
            HAAquaticItems.DUNGENESS_CRAB_CLAW.get(),
            HAAquaticItems.FIDDLER_CRAB_CLAW.get(),
            HAAquaticItems.FLOWER_CRAB_CLAW.get(),
            HAAquaticItems.GHOST_CRAB_CLAW.get(),
            HAAquaticItems.LIGHTFOOT_CRAB_CLAW.get(),
            HAAquaticItems.LOBSTER_CLAW.get(),
            HAAquaticItems.SPIDER_CRAB_CLAW.get(),
            HAAquaticItems.VAMPIRE_CRAB_CLAW.get(),
            HAAquaticItems.YETI_CRAB_CLAW.get(),
            HAAquaticItems.RAW_CRAB.get(),
            HAAquaticItems.COOKED_CRAB.get(),
            HAAquaticItems.RAW_SHRIMP.get(),
            HAAquaticItems.COOKED_SHRIMP.get(),
            HAAquaticItems.COOKED_CLAM.get(),
            HAAquaticItems.COOKED_CRAYFISH.get(),
            HAAquaticItems.RAW_LOBSTER.get(),
            HAAquaticItems.COOKED_LOBSTER.get(),
            HAAquaticItems.RAW_LOBSTER_TAIL.get(),
            HAAquaticItems.COOKED_LOBSTER_TAIL.get(),
            HAAquaticItems.RAW_FISH_STEAK.get(),
            HAAquaticItems.COOKED_FISH_STEAK.get(),
            HAAquaticItems.RAW_FISH_MEAT.get(),
            HAAquaticItems.COOKED_FISH_MEAT.get(),
            HAAquaticItems.RAW_TENTACLE.get(),
            HAAquaticItems.COOKED_TENTACLE.get(),
            HAAquaticItems.GLOWSLIME.get(),
            HAAquaticItems.SHARK_TOOTH.get(),
            HAAquaticItems.PRISMARINE_ROD.get(),
            HAAquaticItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get(),
            HAAquaticItems.PEARL.get(),
            HAAquaticItems.BLACK_PEARL.get(),
            HAAquaticItems.GIANT_NAUTILUS_SHELL.get(),
            HAAquaticItems.ARGONAUT.get(),
            HAAquaticItems.DIVING_WEIGHT.get(),
            HAAquaticItems.SULFUR.get(),
            HAAquaticItems.ANGLERFISH.get(),
            HAAquaticItems.BARRELEYE.get(),
            HAAquaticItems.BETTA.get(),
            HAAquaticItems.PEARLFISH.get(),
            HAAquaticItems.SNAILFISH.get(),
            HAAquaticItems.BLUE_SPOTTED_STINGRAY.get(),
            HAAquaticItems.SURGEONFISH.get(),
            HAAquaticItems.CLOWNFISH.get(),
            HAAquaticItems.DAMSELFISH.get(),
            HAAquaticItems.JOHN_DORY.get(),
            HAAquaticItems.BOXFISH.get(),
            HAAquaticItems.DANIO.get(),
            HAAquaticItems.DISCUS.get(),
            HAAquaticItems.DRAGONFISH.get(),
            HAAquaticItems.FLASHLIGHT_FISH.get(),
            HAAquaticItems.GOURAMI.get(),
            HAAquaticItems.LIONFISH.get(),
            HAAquaticItems.MAHI.get(),
            HAAquaticItems.MORAY_EEL.get(),
            HAAquaticItems.NEEDLEFISH.get(),
            HAAquaticItems.MACKEREL.get(),
            HAAquaticItems.HERRING.get(),
            HAAquaticItems.FLYING_FISH.get(),
            HAAquaticItems.SQUIRRELFISH.get(),
            HAAquaticItems.COELACANTH.get(),
            HAAquaticItems.GOLDEN_DORADO.get(),
            HAAquaticItems.OPAH.get(),
            HAAquaticItems.OARFISH.get(),
            HAAquaticItems.OSCAR.get(),
            HAAquaticItems.PIRANHA.get(),
            HAAquaticItems.RATFISH.get(),
            HAAquaticItems.ROCKFISH.get(),
            HAAquaticItems.SEA_BASS.get(),
            HAAquaticItems.NEON_TETRA.get(),
            HAAquaticItems.TIGER_BARB.get(),
            HAAquaticItems.TRIGGERFISH.get(),
            HAAquaticItems.TREVALLY.get(),
            HAAquaticItems.TUNA.get(),
            HAAquaticItems.STONEFISH.get(),
            HAAquaticItems.BLOWFISH.get(),
            HAAquaticItems.PARROTFISH.get(),
            HAAquaticItems.SHEEPSHEAD_WRASSE.get(),
            HAAquaticItems.OCEAN_SUNFISH.get(),
            HAAquaticItems.CARP.get(),
            HAAquaticItems.TROUT.get(),
            HAAquaticItems.SUNFISH.get(),
            HAAquaticItems.PLECO.get(),
            HAAquaticItems.GOLDFISH.get(),
            HAAquaticItems.SPOTTED_EAGLE_RAY.get(),
            HAAquaticItems.SEAHORSE.get(),
            HAAquaticItems.CUTTLEBONE.get(),
            HAAquaticItems.SEA_URCHIN_SPINE.get(),
            HAAquaticItems.CORAL_CHUNK.get(),
            HAAquaticItems.BARBED_HOOK.get(),
            HAAquaticItems.GLOWING_HOOK.get(),
            HAAquaticItems.MAGNETIC_HOOK.get(),
            HAAquaticItems.CREEPERMAGNET_HOOK.get(),
            HAAquaticItems.OMINOUS_HOOK.get(),
            HAAquaticItems.DIVING_HELMET.get(),
            HAAquaticItems.DIVING_SUIT.get(),
            HAAquaticItems.DIVING_LEGGINGS.get(),
            HAAquaticItems.DIVING_BOOTS.get(),
            HAAquaticItems.REINFORCED_DIVING_HELMET.get(),
            HAAquaticItems.REINFORCED_DIVING_SUIT.get(),
            HAAquaticItems.REINFORCED_DIVING_LEGGINGS.get(),
            HAAquaticItems.REINFORCED_DIVING_BOOTS.get(),
            HAAquaticItems.GLOWING_DIVING_HELMET.get(),
            HAAquaticItems.GLOWING_DIVING_SUIT.get(),
            HAAquaticItems.GLOWING_DIVING_LEGGINGS.get(),
            HAAquaticItems.GLOWING_DIVING_BOOTS.get(),
            HAAquaticItems.NAUTILUS_HELMET.get(),
            HAAquaticItems.NAUTILUS_PAULDRONS.get(),
            HAAquaticItems.MANGLERFISH_LURE.get(),
            HAAquaticItems.MANGLERFISH_FIN.get(),
            HAAquaticItems.TURTLE_CHESTPLATE.get(),
            HAAquaticItems.EEL_SCARF.get(),
            HAAquaticItems.PINK_HATXOLOTL.get(),
            HAAquaticItems.BROWN_HATXOLOTL.get(),
            HAAquaticItems.CYAN_HATXOLOTL.get(),
            HAAquaticItems.BLUE_HATXOLOTL.get(),
            HAAquaticItems.GOLD_HATXOLOTL.get(),
            HAAquaticItems.BROWN_HATXOLOTL.get(),
            HAAquaticItems.MOON_JELLYFISH_HAT.get(),
            HAAquaticItems.SEA_MESSAGE_BOOK.get(),
        ).forEach { item ->
            generator.generateFlatItem(item, ModelTemplates.FLAT_ITEM)
        }
        //#endregion

        //#region Handheld Models
        setOf(
            HAAquaticItems.SEASHELL_SPEAR,
            HAAquaticItems.SEASHELL_PICKAXE,
            HAAquaticItems.SEASHELL_AXE,
            HAAquaticItems.SEASHELL_SHOVEL,
            HAAquaticItems.SEASHELL_HOE,
            HAAquaticItems.CORAL_BLADE,
            HAAquaticItems.CORAL_PICKAXE,
            HAAquaticItems.CORAL_AXE,
            HAAquaticItems.CORAL_SHOVEL,
            HAAquaticItems.CORAL_HOE
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