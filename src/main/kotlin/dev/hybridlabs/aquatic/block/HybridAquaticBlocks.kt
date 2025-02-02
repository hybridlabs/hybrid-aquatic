package dev.hybridlabs.aquatic.block

import com.google.common.collect.ImmutableSet
import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.wood.HybridAquaticWoodTypes
import dev.hybridlabs.aquatic.fluid.HybridAquaticFluids
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockSetType
import net.minecraft.block.Blocks
import net.minecraft.block.ButtonBlock
import net.minecraft.block.CoralBlock
import net.minecraft.block.CoralBlockBlock
import net.minecraft.block.CoralFanBlock
import net.minecraft.block.CoralWallFanBlock
import net.minecraft.block.DeadCoralBlock
import net.minecraft.block.DeadCoralFanBlock
import net.minecraft.block.DeadCoralWallFanBlock
import net.minecraft.block.DoorBlock
import net.minecraft.block.FenceBlock
import net.minecraft.block.FenceGateBlock
import net.minecraft.block.FluidBlock
import net.minecraft.block.MapColor
import net.minecraft.block.PillarBlock
import net.minecraft.block.PressurePlateBlock
import net.minecraft.block.SlabBlock
import net.minecraft.block.StairsBlock
import net.minecraft.block.TrapdoorBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.block.enums.Instrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier

/**
 * The registry of all blocks in Hybrid Aquatic.
 */
object HybridAquaticBlocks {
    val ANEMONE = register(
        "anemone", AnemoneBlock(
            AbstractBlock.Settings.create()
                .mapColor(MapColor.DARK_DULL_PINK)
                .ticksRandomly()
                .strength(0.4f)
                .nonOpaque()
                .pistonBehavior(PistonBehavior.DESTROY)
                .sounds(BlockSoundGroup.SLIME)
        )
    )

    val STRAWBERRY_ANEMONE = register(
        "strawberry_anemone", StrawberryAnemoneBlock(
            AbstractBlock.Settings.create()
                .mapColor(MapColor.DARK_RED)
                .ticksRandomly()
                .strength(0.4f)
                .nonOpaque()
                .pistonBehavior(PistonBehavior.DESTROY)
                .sounds(BlockSoundGroup.SLIME)
        )
    )

    val MESSAGE_IN_A_BOTTLE = register(
        "message_in_a_bottle", MessageInABottleBlock(
            AbstractBlock.Settings.copy(Blocks.GLASS)
                .breakInstantly()
                .pistonBehavior(PistonBehavior.DESTROY)
        )
    )

    val BRINE = register(
        "brine",
        FluidBlock(
            HybridAquaticFluids.BRINE,
            AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).replaceable().noCollision().strength(100.0f)
                .pistonBehavior(PistonBehavior.DESTROY).dropsNothing().liquid()
                .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)
        )
    )

    val BASKING_SHARK_PLUSHIE =
        register("basking_shark_plushie", createPlushieBlock(PlushieBlock.Variant.BASKING_SHARK, Blocks.GRAY_WOOL))
    val BULL_SHARK_PLUSHIE =
        register("bull_shark_plushie", createPlushieBlock(PlushieBlock.Variant.BULL_SHARK, Blocks.LIGHT_GRAY_WOOL))
    val FRILLED_SHARK_PLUSHIE =
        register("frilled_shark_plushie", createPlushieBlock(PlushieBlock.Variant.FRILLED_SHARK, Blocks.GRAY_WOOL))
    val GREAT_WHITE_SHARK_PLUSHIE = register(
        "great_white_shark_plushie",
        createPlushieBlock(PlushieBlock.Variant.GREAT_WHITE_SHARK, Blocks.LIGHT_GRAY_WOOL)
    )
    val HAMMERHEAD_SHARK_PLUSHIE = register(
        "hammerhead_shark_plushie",
        createPlushieBlock(PlushieBlock.Variant.HAMMERHEAD_SHARK, Blocks.LIGHT_GRAY_WOOL)
    )
    val THRESHER_SHARK_PLUSHIE = register(
        "thresher_shark_plushie",
        createPlushieBlock(PlushieBlock.Variant.THRESHER_SHARK, Blocks.LIGHT_BLUE_WOOL)
    )
    val TIGER_SHARK_PLUSHIE =
        register("tiger_shark_plushie", createPlushieBlock(PlushieBlock.Variant.TIGER_SHARK, Blocks.BLACK_WOOL))
    val WHALE_SHARK_PLUSHIE =
        register("whale_shark_plushie", createPlushieBlock(PlushieBlock.Variant.WHALE_SHARK, Blocks.LIGHT_GRAY_WOOL))

    val CRAB_POT = register(
        "crab_pot", CrateBlock(
            AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
                .nonOpaque()
                .hardness(0.75F)
        )
    )

    val HYBRID_CRATE = register(
        "hybrid_crate", CrateBlock(
            AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
                .hardness(0.75F)
        )
    )

    val OAK_CRATE = register(
        "oak_crate", CrateBlock(
            AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
                .hardness(0.75F)
        )
    )

    val SPRUCE_CRATE = register(
        "spruce_crate", CrateBlock(
            AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)
                .hardness(0.75F)
        )
    )

    val BIRCH_CRATE = register(
        "birch_crate", CrateBlock(
            AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS)
                .hardness(0.75F)
        )
    )

    val DARK_OAK_CRATE = register(
        "dark_oak_crate", CrateBlock(
            AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS)
                .hardness(0.75F)
        )
    )

    val ACACIA_CRATE = register(
        "acacia_crate", CrateBlock(
            AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS)
                .hardness(0.75F)
        )
    )

    val JUNGLE_CRATE = register(
        "jungle_crate", CrateBlock(
            AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS)
                .hardness(0.75F)
        )
    )

    val MANGROVE_CRATE = register(
        "mangrove_crate", CrateBlock(
            AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS)
                .hardness(0.75F)
        )
    )

    val CHERRY_CRATE = register(
        "cherry_crate", CrateBlock(
            AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS)
                .hardness(0.75F)
        )
    )

    val SARGASSUM = register("sargassum", SargassumBlock(AbstractBlock.Settings.copy(Blocks.KELP).nonOpaque()))

    val SARGASSUM_PLANT = register(
        "sargassum_plant",
        SargassumPlantBlock(AbstractBlock.Settings.copy(Blocks.KELP_PLANT).nonOpaque().dropsLike(SARGASSUM))
    )

    val FLOATING_SARGASSUM = register(
        "floating_sargassum", FloatingSargassumBlock(
            AbstractBlock.Settings.copy(Blocks.LILY_PAD)
                .noCollision()
                .breakInstantly()
                .mapColor(MapColor.OAK_TAN)
        )
    )

    val WATER_LETTUCE = register(
        "water_lettuce", WaterLettuceBlock(
            AbstractBlock.Settings.copy(Blocks.LILY_PAD)
                .noCollision()
                .breakInstantly()
        )
    )

    val JUNGLE_LILY_PAD = register(
        "jungle_lily_pad", JungleLilyPadBlock(
            AbstractBlock.Settings.copy(Blocks.LILY_PAD)
                .breakInstantly()
        )
    )

    val RAFT = register(
        "raft", RaftBlock(
            AbstractBlock.Settings.copy(Blocks.OAK_WOOD)
        )
    )

    val GLOWING_PLANKTON = register(
        "glowing_plankton", GlowingPlanktonBlock(
            AbstractBlock.Settings.copy(Blocks.LILY_PAD)
                .nonOpaque()
                .replaceable()
                .noCollision()
                .breakInstantly()
        )
    )

    val RED_ALGAE = register("red_algae", RedAlgaeBlock(AbstractBlock.Settings.copy((Blocks.SEAGRASS))))
    val TALL_RED_ALGAE =
        register("tall_red_algae", TallRedAlgaeBlock(AbstractBlock.Settings.copy((Blocks.TALL_SEAGRASS))))

    val SEA_LETTUCE = register("sea_lettuce", SeaLettuceBlock(AbstractBlock.Settings.copy((Blocks.SEAGRASS))))
    val TALL_SEA_LETTUCE =
        register("tall_sea_lettuce", TallSeaLettuceBlock(AbstractBlock.Settings.copy((Blocks.TALL_SEAGRASS))))

    val DRIFTWOOD_LOG = register("driftwood_log", PillarBlock(AbstractBlock.Settings.copy((Blocks.OAK_PLANKS))))
    val STRIPPED_DRIFTWOOD_LOG =
        register("stripped_driftwood_log", PillarBlock(AbstractBlock.Settings.copy((Blocks.OAK_PLANKS))))
    val DRIFTWOOD_WOOD = register("driftwood_wood", PillarBlock(AbstractBlock.Settings.copy((Blocks.OAK_PLANKS))))
    val STRIPPED_DRIFTWOOD_WOOD =
        register("stripped_driftwood_wood", PillarBlock(AbstractBlock.Settings.copy((Blocks.OAK_PLANKS))))
    val DRIFTWOOD_PLANKS = register("driftwood_planks", Block(AbstractBlock.Settings.copy((Blocks.OAK_PLANKS))))
    val DRIFTWOOD_STAIRS = register(
        "driftwood_stairs",
        StairsBlock(DRIFTWOOD_PLANKS.defaultState, AbstractBlock.Settings.copy(Blocks.OAK_STAIRS))
    )
    val DRIFTWOOD_SLAB = register("driftwood_slab", SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_STAIRS)))
    val DRIFTWOOD_BUTTON = register(
        "driftwood_button",
        ButtonBlock(BlockSetType.OAK, 25, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON))
    )
    val DRIFTWOOD_PRESSURE_PLATE = register(
        "driftwood_pressure_plate",
        PressurePlateBlock(
            BlockSetType.OAK,
            AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE),
        )
    )
    val DRIFTWOOD_FENCE = register("driftwood_fence", FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)))
    val DRIFTWOOD_FENCE_GATE = register(
        "driftwood_fence_gate",
        FenceGateBlock(HybridAquaticWoodTypes.DRIFTWOOD, AbstractBlock.Settings.copy(Blocks.OAK_FENCE))
    )
    val DRIFTWOOD_DOOR =
        register("driftwood_door", DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)))
    val DRIFTWOOD_TRAPDOOR =
        register("driftwood_trapdoor", TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)))

    val DEAD_LOPHELIA_CORAL_BLOCK = register(
        "dead_lophelia_coral_block", DeadCoralBlock(
            AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.OFF_WHITE)
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .hardness(1.0F)
                .sounds(BlockSoundGroup.CORAL)
        )
    )

    val LOPHELIA_CORAL_BLOCK = register(
        "lophelia_coral_block", CoralBlockBlock(
            DEAD_LOPHELIA_CORAL_BLOCK, AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.OFF_WHITE)
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .hardness(1.0F)
                .sounds(BlockSoundGroup.CORAL)
        )
    )

    val DEAD_BUTTON_CORAL_BLOCK = register(
        "dead_button_coral_block", DeadCoralBlock(
            AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.OFF_WHITE)
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .hardness(1.0F)
                .sounds(BlockSoundGroup.CORAL)
        )
    )

    val BUTTON_CORAL_BLOCK = register(
        "button_coral_block", CoralBlockBlock(
            DEAD_BUTTON_CORAL_BLOCK, AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.PURPLE)
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .hardness(1.0F)
                .sounds(BlockSoundGroup.CORAL)
        )
    )

    val DEAD_SUN_CORAL_BLOCK = register(
        "dead_sun_coral_block", DeadCoralBlock(
            AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.OFF_WHITE)
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .hardness(1.0F)
                .sounds(BlockSoundGroup.CORAL)
        )
    )

    val SUN_CORAL_BLOCK = register(
        "sun_coral_block", CoralBlockBlock(
            DEAD_SUN_CORAL_BLOCK, AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.PURPLE)
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .hardness(1.0F)
                .sounds(BlockSoundGroup.CORAL)
        )
    )

    val DEAD_LOPHELIA_CORAL_FAN =
        register("dead_lophelia_coral_fan", DeadCoralFanBlock(AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_FAN)))

    val LOPHELIA_CORAL_FAN = register(
        "lophelia_coral_fan",
        CoralFanBlock(DEAD_LOPHELIA_CORAL_FAN, AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_FAN))
    )

    val DEAD_LOPHELIA_CORAL =
        register("dead_lophelia_coral", DeadCoralBlock(AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL)))

    val DEAD_LOPHELIA_CORAL_WALL_FAN = register(
        "dead_lophelia_coral_wall_fan",
        DeadCoralWallFanBlock(
            AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN).dropsLike(DEAD_LOPHELIA_CORAL)
        )
    )

    val LOPHELIA_CORAL =
        register("lophelia_coral", CoralBlock(DEAD_LOPHELIA_CORAL, AbstractBlock.Settings.copy(Blocks.FIRE_CORAL)))

    val LOPHELIA_CORAL_WALL_FAN = register(
        "lophelia_coral_wall_fan",
        CoralWallFanBlock(
            DEAD_LOPHELIA_CORAL_WALL_FAN,
            AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_WALL_FAN).dropsLike(LOPHELIA_CORAL)
        )
    )

    val DEAD_SUN_CORAL_FAN =
        register("dead_sun_coral_fan", DeadCoralFanBlock(AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_FAN)))

    val SUN_CORAL_FAN =
        register("sun_coral_fan", CoralFanBlock(DEAD_SUN_CORAL_FAN, AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_FAN)))

    val DEAD_SUN_CORAL = register("dead_sun_coral", DeadCoralBlock(AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL)))

    val DEAD_SUN_CORAL_WALL_FAN = register(
        "dead_sun_coral_wall_fan",
        DeadCoralWallFanBlock(
            AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN).dropsLike(DEAD_LOPHELIA_CORAL)
        )
    )

    val SUN_CORAL = register("sun_coral", CoralBlock(DEAD_SUN_CORAL, AbstractBlock.Settings.copy(Blocks.FIRE_CORAL)))

    val SUN_CORAL_WALL_FAN = register(
        "sun_coral_wall_fan",
        CoralWallFanBlock(
            DEAD_SUN_CORAL_WALL_FAN,
            AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_WALL_FAN).dropsLike(LOPHELIA_CORAL)
        )
    )

    val DEAD_BUTTON_CORAL_FAN =
        register("dead_button_coral_fan", DeadCoralFanBlock(AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_FAN)))

    val BUTTON_CORAL_FAN = register(
        "button_coral_fan",
        CoralFanBlock(DEAD_BUTTON_CORAL_FAN, AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_FAN))
    )

    val DEAD_BUTTON_CORAL =
        register("dead_button_coral", DeadCoralBlock(AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL)))

    val DEAD_BUTTON_CORAL_WALL_FAN = register(
        "dead_button_coral_wall_fan",
        DeadCoralWallFanBlock(
            AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN).dropsLike(DEAD_LOPHELIA_CORAL)
        )
    )

    val BUTTON_CORAL =
        register("button_coral", CoralBlock(DEAD_BUTTON_CORAL, AbstractBlock.Settings.copy(Blocks.FIRE_CORAL)))

    val BUTTON_CORAL_WALL_FAN = register(
        "button_coral_wall_fan",
        CoralWallFanBlock(
            DEAD_BUTTON_CORAL_WALL_FAN,
            AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_WALL_FAN).dropsLike(LOPHELIA_CORAL)
        )
    )

    val DEAD_THORN_CORAL_BLOCK = register(
        "dead_thorn_coral_block",
        DeadCoralBlock(
            AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_BLOCK).mapColor(MapColor.OFF_WHITE)
                .instrument(Instrument.BASEDRUM).requiresTool().hardness(1.0F).sounds(BlockSoundGroup.CORAL)
        )
    )

    val THORN_CORAL_BLOCK = register(
        "thorn_coral_block",
        CoralBlockBlock(
            DEAD_THORN_CORAL_BLOCK,
            AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_BLOCK).mapColor(MapColor.BLACK).instrument(Instrument.BASEDRUM)
                .requiresTool().hardness(1.0F).sounds(BlockSoundGroup.CORAL)
        )
    )

    val DEAD_THORN_CORAL_FAN =
        register("dead_thorn_coral_fan", DeadCoralFanBlock(AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_FAN)))

    val THORN_CORAL_FAN = register(
        "thorn_coral_fan",
        CoralFanBlock(DEAD_THORN_CORAL_FAN, AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_FAN))
    )

    val DEAD_THORN_CORAL =
        register("dead_thorn_coral", DeadCoralBlock(AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL)))

    val DEAD_THORN_CORAL_WALL_FAN = register(
        "dead_thorn_coral_wall_fan",
        DeadCoralWallFanBlock(AbstractBlock.Settings.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN).dropsLike(DEAD_THORN_CORAL))
    )

    val THORN_CORAL =
        register("thorn_coral", CoralBlock(DEAD_THORN_CORAL, AbstractBlock.Settings.copy(Blocks.FIRE_CORAL)))

    val THORN_CORAL_WALL_FAN = register(
        "thorn_coral_wall_fan",
        CoralWallFanBlock(
            DEAD_THORN_CORAL_WALL_FAN,
            AbstractBlock.Settings.copy(Blocks.FIRE_CORAL_WALL_FAN).dropsLike(THORN_CORAL)
        )
    )

    val GLOWSTICK = register(
        "glowstick", GlowstickBlock(
            AbstractBlock.Settings.copy(Blocks.TORCH)
                .noCollision()
                .luminance(GlowstickBlock::luminance)
                .nonOpaque()
        )
    )

    val WALL_GLOWSTICK = register(
        "wall_glowstick", WallGlowstickBlock(
            AbstractBlock.Settings.copy(Blocks.WALL_TORCH)
                .dropsLike(GLOWSTICK)
                .noCollision()
                .luminance(GlowstickBlock::luminance)
                .nonOpaque()
        )
    )

    val THERMAL_VENT = register(
        "hydrothermal_vent_shaft", ThermalVentBlock(
            true, 2, AbstractBlock.Settings.copy(Blocks.TUFF)
                .nonOpaque()
                .hardness(0.5F)
                .pistonBehavior(PistonBehavior.DESTROY)
        )
    )

    val TUBE_WORM = register(
        "tube_worm", TubeWormBlock(
            AbstractBlock.Settings.create()
                .mapColor(MapColor.GREEN)
                .sounds(BlockSoundGroup.SLIME)
                .nonOpaque()
                .pistonBehavior(PistonBehavior.DESTROY)
        )
    )

    val TUBE_SPONGE = register(
        "tube_sponge", TubeSpongeBlock(
            true,
            AbstractBlock.Settings.create()
                .mapColor(MapColor.YELLOW)
                .sounds(BlockSoundGroup.SLIME)
                .nonOpaque()
                .pistonBehavior(PistonBehavior.DESTROY)
        )
    )

    val GIANT_CLAM = register(
        "giant_clam", GiantClamBlock(
            true, AbstractBlock.Settings.copy(Blocks.TUFF)
                .nonOpaque()
                .hardness(1.0F)
                .pistonBehavior(PistonBehavior.DESTROY)
        )
    )

    val BUOY = register(
        "buoy", BuoyBlock(
            AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
                .nonOpaque()
                .noBlockBreakParticles()
                .luminance { 12 }
                .hardness(0.75F)
        )
    )

    private fun createPlushieBlock(variant: PlushieBlock.Variant, particleBlock: Block): PlushieBlock {
        return PlushieBlock(
            variant, particleBlock,
            AbstractBlock.Settings.create()
                .breakInstantly()
                .pistonBehavior(PistonBehavior.DESTROY)
                .sounds(BlockSoundGroup.WOOL)
                .instrument(Instrument.CUSTOM_HEAD)
        )
    }

    private fun register(id: String, block: Block): Block {
        return Registry.register(Registries.BLOCK, Identifier(HybridAquatic.MOD_ID, id), block)
    }

    /**
     * Adds [addedBlocks] to the supported blocks list of a block entity type.
     */
    fun <T : BlockEntity> BlockEntityType<T>.addBlocks(vararg addedBlocks: Block) {
        if (blocks !is ImmutableSet) {
            blocks.addAll(addedBlocks)
        } else {
            blocks = blocks.toMutableSet().apply {
                addAll(addedBlocks)
            }
        }
    }
}
