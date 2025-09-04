package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Blocks
import net.minecraft.block.FluidBlock
import net.minecraft.data.client.*
import net.minecraft.item.Items
import net.minecraft.item.SpawnEggItem
import net.minecraft.registry.Registries
import net.minecraft.resources.ResourceLocation

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) {
        generator.run {
            // plushies
            Registries.BLOCK
                .filterIsInstance<PlushieBlock>()
                .forEach { block ->
                    excludeFromSimpleItemModelGeneration(block)

                    registerBuiltinWithParticle(block, TextureMap.getId(block.particleBlock))
                    registerParentedItemModel(block, TEMPLATE_PLUSHIE)
                }

            // fluids
            Registries.BLOCK
                .filterIsInstance<FluidBlock>()
                .forEach { block ->
                    val id = Registries.BLOCK.getId(block)
                    if (id.namespace == Constants.MOD_ID) {
                        registerSimpleState(block)
                    }
                }

            // spawn eggs
            Registries.ITEM
                .filter(filterHybridAquatic(Registries.ITEM))
                .forEach { item ->
                    if (item is SpawnEggItem) {
                        registerParentedItemModel(item, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"))
                    }
                }

            // builtin
            mapOf(
                HybridAquaticBlocks.ANEMONE to (null to TEMPLATE_ANEMONE),
                HybridAquaticBlocks.GIANT_GREEN_ANEMONE to (null to TEMPLATE_ANEMONE),
                HybridAquaticBlocks.STRAWBERRY_ANEMONE to (null to TEMPLATE_ANEMONE),
                HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE to (Blocks.GLASS to TEMPLATE_MESSAGE_IN_A_BOTTLE),
            ).forEach { (block, info) ->
                val (particleBlock, template) = info

                excludeFromSimpleItemModelGeneration(block)

                particleBlock?.let { b -> registerBuiltinWithParticle(block, TextureMap.getId(b)) }
                registerParentedItemModel(block, template)
            }

            registerBuiltinWithParticle(
                HybridAquaticBlocks.ANEMONE,
                TextureMap.getSubId(HybridAquaticBlocks.ANEMONE, "_top")
            )

            registerBuiltinWithParticle(
                HybridAquaticBlocks.STRAWBERRY_ANEMONE,
                TextureMap.getSubId(HybridAquaticBlocks.STRAWBERRY_ANEMONE, "_top")
            )

            registerBuiltinWithParticle(
                HybridAquaticBlocks.GIANT_GREEN_ANEMONE,
                TextureMap.getSubId(HybridAquaticBlocks.STRAWBERRY_ANEMONE, "_top")
            )

            // simple cubes
            setOf(
                HybridAquaticBlocks.HYBRID_CRATE,
                HybridAquaticBlocks.OAK_CRATE,
                HybridAquaticBlocks.SPRUCE_CRATE,
                HybridAquaticBlocks.BIRCH_CRATE,
                HybridAquaticBlocks.DARK_OAK_CRATE,
                HybridAquaticBlocks.JUNGLE_CRATE,
                HybridAquaticBlocks.ACACIA_CRATE,
                HybridAquaticBlocks.MANGROVE_CRATE,
                HybridAquaticBlocks.CHERRY_CRATE,
            ).forEach(generator::registerSimpleCubeAll)

            // wood
            val driftwoodPool = registerCubeAllModelTexturePool(HybridAquaticBlocks.DRIFTWOOD_PLANKS)

            registerLog(HybridAquaticBlocks.DRIFTWOOD_LOG).log(HybridAquaticBlocks.DRIFTWOOD_LOG)
                .wood(HybridAquaticBlocks.DRIFTWOOD_WOOD)
            registerLog(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG).log(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG)
                .wood(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD)

            registerDoor(HybridAquaticBlocks.DRIFTWOOD_DOOR)
            registerTrapdoor(HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR)

            driftwoodPool.stairs(HybridAquaticBlocks.DRIFTWOOD_STAIRS)
            driftwoodPool.slab(HybridAquaticBlocks.DRIFTWOOD_SLAB)
            driftwoodPool.button(HybridAquaticBlocks.DRIFTWOOD_BUTTON)
            driftwoodPool.pressurePlate(HybridAquaticBlocks.DRIFTWOOD_PRESSURE_PLATE)
            driftwoodPool.fence(HybridAquaticBlocks.DRIFTWOOD_FENCE)
            driftwoodPool.fenceGate(HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE)

            registerTorch(HybridAquaticBlocks.GLOWSTICK, HybridAquaticBlocks.WALL_GLOWSTICK)

            registerCoral(
                HybridAquaticBlocks.LOPHELIA_CORAL,
                HybridAquaticBlocks.DEAD_LOPHELIA_CORAL,
                HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK,
                HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK,
                HybridAquaticBlocks.LOPHELIA_CORAL_FAN,
                HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN,
                HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN,
                HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_WALL_FAN
            )

            registerCoral(
                HybridAquaticBlocks.BUTTON_CORAL,
                HybridAquaticBlocks.DEAD_BUTTON_CORAL,
                HybridAquaticBlocks.BUTTON_CORAL_BLOCK,
                HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK,
                HybridAquaticBlocks.BUTTON_CORAL_FAN,
                HybridAquaticBlocks.DEAD_BUTTON_CORAL_FAN,
                HybridAquaticBlocks.BUTTON_CORAL_WALL_FAN,
                HybridAquaticBlocks.DEAD_BUTTON_CORAL_WALL_FAN
            )

            registerCoral(
                HybridAquaticBlocks.THORN_CORAL,
                HybridAquaticBlocks.DEAD_THORN_CORAL,
                HybridAquaticBlocks.THORN_CORAL_BLOCK,
                HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK,
                HybridAquaticBlocks.THORN_CORAL_FAN,
                HybridAquaticBlocks.DEAD_THORN_CORAL_FAN,
                HybridAquaticBlocks.THORN_CORAL_WALL_FAN,
                HybridAquaticBlocks.DEAD_THORN_CORAL_WALL_FAN
            )

            registerCoral(
                HybridAquaticBlocks.SUN_CORAL,
                HybridAquaticBlocks.DEAD_SUN_CORAL,
                HybridAquaticBlocks.SUN_CORAL_BLOCK,
                HybridAquaticBlocks.DEAD_SUN_CORAL_BLOCK,
                HybridAquaticBlocks.SUN_CORAL_FAN,
                HybridAquaticBlocks.DEAD_SUN_CORAL_FAN,
                HybridAquaticBlocks.SUN_CORAL_WALL_FAN,
                HybridAquaticBlocks.DEAD_SUN_CORAL_WALL_FAN
            )

            registerTintableCrossBlockState(
                HybridAquaticBlocks.SARGASSUM_PLANT,
                BlockStateModelGenerator.TintType.NOT_TINTED,
            )

            registerTintableCrossBlockState(
                HybridAquaticBlocks.SARGASSUM,
                BlockStateModelGenerator.TintType.NOT_TINTED,
            )

            registerTintableCrossBlockState(
                HybridAquaticBlocks.BULL_KELP_PLANT,
                BlockStateModelGenerator.TintType.NOT_TINTED,
            )

            registerTintableCrossBlockState(
                HybridAquaticBlocks.BULL_KELP,
                BlockStateModelGenerator.TintType.NOT_TINTED,
            )

            registerTintableCrossBlockState(
                HybridAquaticBlocks.SEA_LETTUCE,
                BlockStateModelGenerator.TintType.TINTED,
            )
        }
    }

    override fun generateItemModels(generator: ItemModelGenerator) {
        setOf(
            HybridAquaticItems.BUOY,
            HybridAquaticItems.RED_ALGAE,
            HybridAquaticItems.SEA_LETTUCE,
            HybridAquaticItems.SARGASSUM,
            HybridAquaticItems.BULL_KELP,
            HybridAquaticItems.TUBE_SPONGE,
            HybridAquaticItems.UNI,
            HybridAquaticItems.RAW_CRAYFISH,
            HybridAquaticItems.COCONUT_CRAB_CLAW,
            HybridAquaticItems.DUNGENESS_CRAB_CLAW,
            HybridAquaticItems.FIDDLER_CRAB_CLAW,
            HybridAquaticItems.FLOWER_CRAB_CLAW,
            HybridAquaticItems.GHOST_CRAB_CLAW,
            HybridAquaticItems.LIGHTFOOT_CRAB_CLAW,
            HybridAquaticItems.LOBSTER_CLAW,
            HybridAquaticItems.SPIDER_CRAB_CLAW,
            HybridAquaticItems.VAMPIRE_CRAB_CLAW,
            HybridAquaticItems.YETI_CRAB_CLAW,
            HybridAquaticItems.RAW_CRAB,
            HybridAquaticItems.COOKED_CRAB,
            HybridAquaticItems.RAW_SHRIMP,
            HybridAquaticItems.COOKED_SHRIMP,
            HybridAquaticItems.COOKED_CRAYFISH,
            HybridAquaticItems.RAW_LOBSTER,
            HybridAquaticItems.COOKED_LOBSTER,
            HybridAquaticItems.RAW_LOBSTER_TAIL,
            HybridAquaticItems.COOKED_LOBSTER_TAIL,
            HybridAquaticItems.RAW_FISH_STEAK,
            HybridAquaticItems.COOKED_FISH_STEAK,
            HybridAquaticItems.RAW_FISH_MEAT,
            HybridAquaticItems.COOKED_FISH_MEAT,
            HybridAquaticItems.RAW_TENTACLE,
            HybridAquaticItems.COOKED_TENTACLE,
            HybridAquaticItems.GLOW_SLIME,
            HybridAquaticItems.SHARK_TOOTH,
            HybridAquaticItems.PEARL,
            HybridAquaticItems.BLACK_PEARL,
            HybridAquaticItems.SULFUR,
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.PEARLFISH,
            HybridAquaticItems.SNAILFISH,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.SURGEONFISH_SOHAL,
            HybridAquaticItems.SURGEONFISH_ORANGESHOULDER,
            HybridAquaticItems.SURGEONFISH_LINED,
            HybridAquaticItems.YELLOW_TANG,
            HybridAquaticItems.POWDER_BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.SERGEANT_MAJOR,
            HybridAquaticItems.JOHN_DORY,
            HybridAquaticItems.BOXFISH,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.DRAGONFISH,
            HybridAquaticItems.FLASHLIGHT_FISH,
            HybridAquaticItems.GOURAMI,
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.MAHI,
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.MACKEREL,
            HybridAquaticItems.FLYING_FISH,
            HybridAquaticItems.SQUIRRELFISH,
            HybridAquaticItems.COELACANTH,
            HybridAquaticItems.GOLDEN_DORADO,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.OARFISH,
            HybridAquaticItems.OSCAR,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.RATFISH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.SEA_BASS,
            HybridAquaticItems.NEON_TETRA,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.TRIGGERFISH,
            HybridAquaticItems.YELLOWFIN_TUNA,
            HybridAquaticItems.BLUEFIN_TUNA,
            HybridAquaticItems.UNICORNFISH,
            HybridAquaticItems.STONEFISH,
            HybridAquaticItems.BLOWFISH,
            HybridAquaticItems.PARROTFISH,
            HybridAquaticItems.SUNFISH,
            HybridAquaticItems.KOI,
            HybridAquaticItems.CARP,
            HybridAquaticItems.GOLDFISH,
            HybridAquaticItems.SPOTTED_EAGLE_RAY,
            HybridAquaticItems.SEAHORSE,
            HybridAquaticItems.CUTTLEBONE,
            HybridAquaticItems.SEA_URCHIN_SPINE,
            HybridAquaticItems.CORAL_CHUNK,
            HybridAquaticItems.BARBED_HOOK,
            HybridAquaticItems.GLOWING_HOOK,
            HybridAquaticItems.MAGNETIC_HOOK,
            HybridAquaticItems.CREEPERMAGNET_HOOK,
            HybridAquaticItems.OMINOUS_HOOK,
            HybridAquaticItems.DIVING_HELMET,
            HybridAquaticItems.DIVING_SUIT,
            HybridAquaticItems.DIVING_LEGGINGS,
            HybridAquaticItems.DIVING_BOOTS,
            HybridAquaticItems.NAUTILUS_HELMET,
            HybridAquaticItems.NAUTILUS_PAULDRONS,
            HybridAquaticItems.MANGLERFISH_LURE,
            HybridAquaticItems.MANGLERFISH_FIN,
            HybridAquaticItems.TURTLE_CHESTPLATE,
            HybridAquaticItems.EEL_SCARF,
            HybridAquaticItems.MOON_JELLYFISH_HAT
        ).forEach { item ->
            generator.register(item, Models.GENERATED)
        }

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
            generator.register(item, Models.HANDHELD)
        }

        generator.register(HybridAquaticItems.SEA_MESSAGE_BOOK, Items.WRITTEN_BOOK, Models.GENERATED)
    }

    companion object {
        private val TEMPLATE_ANEMONE = ResourceLocation(Constants.MOD_ID, "item/template_anemone")
        private val TEMPLATE_MESSAGE_IN_A_BOTTLE = ResourceLocation(Constants.MOD_ID, "item/template_message_in_a_bottle")
        private val TEMPLATE_PLUSHIE = ResourceLocation(Constants.MOD_ID, "item/template_plushie")
    }
}
