package dev.hybridlabs.aquatic.block

import com.google.common.collect.ImmutableSet
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.ColorRGBA
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import java.util.function.Supplier


/**
 * The registry of all blocks in Hybrid Aquatic.
 */
object HABlocks {
    val ANEMONE = register("anemone") {
        AnemoneBlock(Properties.of()
            .mapColor(MapColor.TERRACOTTA_PINK)
            .randomTicks()
            .strength(0.4f)
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY)
            .sound(SoundType.SLIME_BLOCK)
            .noTerrainParticles()
        )
    }

    val GIANT_GREEN_ANEMONE = register("giant_green_anemone") {
        GiantGreenAnemoneBlock(Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GREEN)
            .randomTicks()
            .strength(0.4f)
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY)
            .sound(SoundType.SLIME_BLOCK)
            .noTerrainParticles()
        )
    }

    val STRAWBERRY_ANEMONE = register("strawberry_anemone") {
        StrawberryAnemoneBlock(Properties.of()
            .mapColor(MapColor.COLOR_RED)
            .randomTicks()
            .strength(0.4f)
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY)
            .sound(SoundType.SLIME_BLOCK)
            .noTerrainParticles()
        )
    }

    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle") {
        MessageInABottleBlock(Properties.ofFullCopy(Blocks.GLASS)
            .instabreak()
            .pushReaction(PushReaction.DESTROY)
            .noCollission()
        )
    }

    val CLAMS = register("clams") { ClamBlock(Properties.ofFullCopy(Blocks.CARROTS)) }
    val MUSSELS = register("mussels") { MusselBlock(Properties.ofFullCopy(Blocks.CARROTS)) }
    val WILD_MUSSELS = register("wild_mussels") { WildMusselBlock(Properties.ofFullCopy(Blocks.TALL_GRASS).dropsLike(MUSSELS.get())) }
    val AERATED_SAND = register("aerated_sand") { AeratedSandBlock(ColorRGBA(14406560), Properties.ofFullCopy(Blocks.SAND).hasPostProcess { _, _, _ -> true }) }
    val BUBBLE_GEYSER = register("bubble_geyser") { BubbleGeyserBlock(ColorRGBA(14406560), Properties.ofFullCopy(Blocks.SAND).hasPostProcess { _, _, _ -> true }) }
    val WHITE_SAND = register("white_sand") { ColoredFallingBlock(ColorRGBA(14406560), Properties.ofFullCopy(Blocks.SAND)) }

    val WHITE_SANDSTONE = register("white_sandstone") {
        Block(Properties
            .ofFullCopy(Blocks.SANDSTONE)) }
    val WHITE_SANDSTONE_STAIRS = register("white_sandstone_stairs") {
        StairBlock(WHITE_SANDSTONE.get().defaultBlockState(), Properties
            .ofFullCopy(WHITE_SANDSTONE.get())
            .mapColor(DyeColor.WHITE)) }
    val WHITE_SANDSTONE_SLAB = register("white_sandstone_slab") {
        SlabBlock(Properties
            .ofFullCopy(WHITE_SANDSTONE.get())
            .mapColor(DyeColor.WHITE)) }
    val WHITE_SANDSTONE_WALL = register("white_sandstone_wall") {
        WallBlock(Properties
            .ofFullCopy(WHITE_SANDSTONE.get())) }

    val SMOOTH_WHITE_SANDSTONE = register("smooth_white_sandstone") {
        Block(Properties
            .ofFullCopy(Blocks.SANDSTONE)) }
    val SMOOTH_WHITE_SANDSTONE_SLAB = register("smooth_white_sandstone_slab") {
        SlabBlock(Properties
            .ofFullCopy(WHITE_SANDSTONE.get())
            .mapColor(DyeColor.WHITE)) }
    val SMOOTH_WHITE_SANDSTONE_STAIRS = register("smooth_white_sandstone_stairs") {
        StairBlock(WHITE_SANDSTONE.get().defaultBlockState(), Properties
            .ofFullCopy(WHITE_SANDSTONE.get())
            .mapColor(DyeColor.WHITE)) }

    val CUT_WHITE_SANDSTONE = register("cut_white_sandstone") {
        Block(Properties
            .ofFullCopy(WHITE_SANDSTONE.get())
            .mapColor(DyeColor.WHITE)) }
    val CUT_WHITE_SANDSTONE_SLAB = register("cut_white_sandstone_slab") {
        SlabBlock(Properties
            .ofFullCopy(WHITE_SANDSTONE.get())
            .mapColor(DyeColor.WHITE)) }

    val CHISELED_WHITE_SANDSTONE = register("chiseled_white_sandstone") {
        Block(Properties
            .ofFullCopy(WHITE_SANDSTONE.get())
            .mapColor(DyeColor.WHITE)) }

    val BONE_STAIRS = register("bone_stairs") {
        StairBlock(Blocks.BONE_BLOCK.defaultBlockState(), Properties
            .ofFullCopy(Blocks.BONE_BLOCK)
            .mapColor(DyeColor.WHITE)) }
    val BONE_SLAB = register("bone_slab") {
        SlabBlock(Properties
            .ofFullCopy(Blocks.BONE_BLOCK)
            .mapColor(DyeColor.WHITE)) }
    val BONE_WALL = register("bone_wall") {
        WallBlock(Properties
            .ofFullCopy(Blocks.BONE_BLOCK)) }
    val BONE_FENCE = register("bone_fence") {
        FenceBlock(Properties
            .ofFullCopy(Blocks.BONE_BLOCK)) }

    val SUSPICIOUS_RED_SAND = register("suspicious_red_sand") {
        BrushableBlock(Blocks.RED_SAND,
            SoundEvents.BRUSH_SAND,
            SoundEvents.BRUSH_SAND_COMPLETED,
        Properties.ofFullCopy(Blocks.RED_SAND)) }

    val CRYSTALLINE_SULFUR = register("crystalline_sulfur") { CrystallineSulfurBlock(Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)) }
    val GRASSY_SAND = register("grassy_sand") { GrassySandBlock(Properties.ofFullCopy(Blocks.SAND)) }
    val DEPTH_CHARGE = register("depth_charge") { DepthChargeBlock(Properties.ofFullCopy(Blocks.TNT)) }
    val CORALSTONE = register("coralstone") { Block(Properties.ofFullCopy(Blocks.SANDSTONE)) }
    val SHORESTONE = register("shorestone") { Block(Properties.ofFullCopy(Blocks.SANDSTONE)) }
    val BARNACLE_SHORESTONE = register("barnacle_shorestone") { Block(Properties.ofFullCopy(Blocks.SANDSTONE)) }

    val MARINE_SNOW = register("marine_snow") { ColoredFallingBlock(ColorRGBA(14406560), Properties.ofFullCopy(Blocks.MUD)) }

    val RED_BRINESTONE = register("red_brinestone") {
        Block(Properties
            .ofFullCopy(Blocks.DEEPSLATE)) }
    val RED_BRINESTONE_STAIRS = register("red_brinestone_stairs") {
        StairBlock(RED_BRINESTONE.get().defaultBlockState(), Properties
            .ofFullCopy(RED_BRINESTONE.get())
            .mapColor(DyeColor.RED)) }
    val RED_BRINESTONE_SLAB = register("red_brinestone_slab") {
        SlabBlock(Properties
        .ofFullCopy(RED_BRINESTONE.get())
        .mapColor(DyeColor.RED)) }
    val RED_BRINESTONE_WALL = register("red_brinestone_wall") {
        WallBlock(Properties
        .ofFullCopy(RED_BRINESTONE.get())) }

    val RED_BRINESTONE_BRICKS = register("red_brinestone_bricks") {
        Block(Properties
            .ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val RED_BRINESTONE_BRICK_STAIRS = register("red_brinestone_brick_stairs") {
        StairBlock(RED_BRINESTONE_BRICKS.get().defaultBlockState(), Properties
            .ofFullCopy(RED_BRINESTONE.get())
            .mapColor(DyeColor.RED)) }
    val RED_BRINESTONE_BRICK_SLAB = register("red_brinestone_brick_slab") {
        SlabBlock(Properties
            .ofFullCopy(RED_BRINESTONE.get())
            .mapColor(DyeColor.RED)) }
    val RED_BRINESTONE_BRICK_WALL = register("red_brinestone_brick_wall") {
        WallBlock(Properties
            .ofFullCopy(RED_BRINESTONE.get())) }

    val CHISELED_RED_BRINESTONE = register("chiseled_red_brinestone") {
        Block(Properties
            .ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val POLISHED_RED_BRINESTONE = register("polished_red_brinestone") {
        Block(Properties
            .ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val POLISHED_RED_BRINESTONE_STAIRS = register("polished_red_brinestone_stairs") {
        StairBlock(POLISHED_RED_BRINESTONE.get().defaultBlockState(), Properties
            .ofFullCopy(POLISHED_RED_BRINESTONE.get())
            .mapColor(DyeColor.RED)) }
    val POLISHED_RED_BRINESTONE_SLAB = register("polished_red_brinestone_slab") {
        SlabBlock(Properties.ofFullCopy(POLISHED_RED_BRINESTONE.get())
            .mapColor(DyeColor.RED)) }

    val ORANGE_BRINESTONE = register("orange_brinestone") {
        Block(Properties
            .ofFullCopy(Blocks.DEEPSLATE)) }
    val ORANGE_BRINESTONE_STAIRS = register("orange_brinestone_stairs") {
        StairBlock(ORANGE_BRINESTONE.get().defaultBlockState(), Properties
            .ofFullCopy(ORANGE_BRINESTONE.get())
            .mapColor(DyeColor.ORANGE)) }
    val ORANGE_BRINESTONE_SLAB = register("orange_brinestone_slab") {
        SlabBlock(Properties
            .ofFullCopy(ORANGE_BRINESTONE.get())
            .mapColor(DyeColor.ORANGE)) }
    val ORANGE_BRINESTONE_WALL = register("orange_brinestone_wall") {
        WallBlock(Properties
            .ofFullCopy(ORANGE_BRINESTONE.get())) }

    val ORANGE_BRINESTONE_BRICKS = register("orange_brinestone_bricks") {
        Block(Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val ORANGE_BRINESTONE_BRICK_STAIRS = register("orange_brinestone_brick_stairs") {
        StairBlock(ORANGE_BRINESTONE_BRICKS.get().defaultBlockState(), Properties
            .ofFullCopy(ORANGE_BRINESTONE.get())
            .mapColor(DyeColor.ORANGE)) }
    val ORANGE_BRINESTONE_BRICK_SLAB = register("orange_brinestone_brick_slab") {
        SlabBlock(Properties
            .ofFullCopy(ORANGE_BRINESTONE.get())
            .mapColor(DyeColor.ORANGE)) }
    val ORANGE_BRINESTONE_BRICK_WALL = register("orange_brinestone_brick_wall") {
        WallBlock(Properties
            .ofFullCopy(ORANGE_BRINESTONE.get())) }

    val CHISELED_ORANGE_BRINESTONE = register("chiseled_orange_brinestone") {
        Block(Properties
            .ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val POLISHED_ORANGE_BRINESTONE = register("polished_orange_brinestone") {
        Block(Properties
            .ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val POLISHED_ORANGE_BRINESTONE_STAIRS = register("polished_orange_brinestone_stairs") {
        StairBlock(POLISHED_ORANGE_BRINESTONE.get().defaultBlockState(), Properties
            .ofFullCopy(POLISHED_ORANGE_BRINESTONE.get())
            .mapColor(DyeColor.ORANGE)) }
    val POLISHED_ORANGE_BRINESTONE_SLAB = register("polished_orange_brinestone_slab") {
        SlabBlock(Properties.ofFullCopy(POLISHED_ORANGE_BRINESTONE.get())
            .mapColor(DyeColor.ORANGE)) }

    val YELLOW_BRINESTONE = register("yellow_brinestone") {
        Block(Properties.ofFullCopy(Blocks.DEEPSLATE)) }
    val YELLOW_BRINESTONE_STAIRS = register("yellow_brinestone_stairs") {
        StairBlock(YELLOW_BRINESTONE.get().defaultBlockState(), Properties
            .ofFullCopy(YELLOW_BRINESTONE.get())
            .mapColor(DyeColor.YELLOW)) }
    val YELLOW_BRINESTONE_SLAB = register("yellow_brinestone_slab") {
        SlabBlock(Properties
            .ofFullCopy(YELLOW_BRINESTONE.get())
            .mapColor(DyeColor.YELLOW)) }
    val YELLOW_BRINESTONE_WALL = register("yellow_brinestone_wall") {
        WallBlock(Properties
            .ofFullCopy(YELLOW_BRINESTONE.get())) }

    val YELLOW_BRINESTONE_BRICKS = register("yellow_brinestone_bricks") {
        Block(Properties
            .ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val YELLOW_BRINESTONE_BRICK_STAIRS = register("yellow_brinestone_brick_stairs") {
        StairBlock(YELLOW_BRINESTONE_BRICKS.get().defaultBlockState(), Properties
            .ofFullCopy(YELLOW_BRINESTONE.get())
            .mapColor(DyeColor.YELLOW)) }
    val YELLOW_BRINESTONE_BRICK_SLAB = register("yellow_brinestone_brick_slab") {
        SlabBlock(Properties.ofFullCopy(YELLOW_BRINESTONE.get())
            .mapColor(DyeColor.YELLOW)) }
    val YELLOW_BRINESTONE_BRICK_WALL = register("yellow_brinestone_brick_wall") {
        WallBlock(Properties.ofFullCopy(YELLOW_BRINESTONE.get())) }

    val CHISELED_YELLOW_BRINESTONE = register("chiseled_yellow_brinestone") {
        Block(Properties
            .ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val POLISHED_YELLOW_BRINESTONE = register("polished_yellow_brinestone") {
        Block(Properties
            .ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val POLISHED_YELLOW_BRINESTONE_STAIRS = register("polished_yellow_brinestone_stairs") {
        StairBlock(POLISHED_YELLOW_BRINESTONE.get().defaultBlockState(), Properties
            .ofFullCopy(POLISHED_YELLOW_BRINESTONE.get())
            .mapColor(DyeColor.YELLOW)) }
    val POLISHED_YELLOW_BRINESTONE_SLAB = register("polished_yellow_brinestone_slab") {
        SlabBlock(Properties
            .ofFullCopy(POLISHED_YELLOW_BRINESTONE.get())
            .mapColor(DyeColor.YELLOW)) }

    val SCHIST = register("schist") {
        Block(Properties
            .ofFullCopy(Blocks.DEEPSLATE)) }
    val SCHIST_STAIRS = register("schist_stairs") {
        StairBlock(SCHIST.get().defaultBlockState(), Properties
        .ofFullCopy(SCHIST.get())
        .mapColor(DyeColor.BLACK)) }
    val SCHIST_SLAB = register("schist_slab") {
        SlabBlock(Properties
        .ofFullCopy(SCHIST.get())
        .mapColor(DyeColor.BLACK)) }
    val SCHIST_WALL = register("schist_wall") {
        WallBlock(Properties
        .ofFullCopy(SCHIST.get())) }

    val SCHIST_BRICKS = register("schist_bricks") {
        Block(Properties
            .ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val SCHIST_BRICK_STAIRS = register("schist_brick_stairs") {
        StairBlock(SCHIST_BRICKS.get().defaultBlockState(), Properties
        .ofFullCopy(SCHIST.get()).mapColor(DyeColor.BLACK)) }
    val SCHIST_BRICK_SLAB = register("schist_brick_slab") {
        SlabBlock(Properties.ofFullCopy(SCHIST.get())
            .mapColor(DyeColor.BLACK)) }
    val SCHIST_BRICK_WALL = register("schist_brick_wall") {
        WallBlock(Properties.ofFullCopy(SCHIST.get())) }

    val CHISELED_SCHIST = register("chiseled_schist") {
        Block(Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val POLISHED_SCHIST = register("polished_schist") {
        Block(Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE)) }
    val POLISHED_SCHIST_STAIRS = register("polished_schist_stairs") {
        StairBlock(POLISHED_SCHIST.get().defaultBlockState(), Properties
            .ofFullCopy(POLISHED_SCHIST.get())
            .mapColor(DyeColor.BLACK)) }
    val POLISHED_SCHIST_SLAB = register("polished_schist_slab") {
        SlabBlock(Properties.ofFullCopy(POLISHED_SCHIST.get())
            .mapColor(DyeColor.BLACK)) }

    val CHIMNEYSTONE = register("chimneystone") {
        Block(Properties
            .ofFullCopy(Blocks.TUFF)) }
    val CHIMNEYSTONE_STAIRS = register("chimneystone_stairs") {
        StairBlock(CHIMNEYSTONE.get().defaultBlockState(), Properties
            .ofFullCopy(CHIMNEYSTONE.get())
            .mapColor(DyeColor.BROWN)) }
    val CHIMNEYSTONE_SLAB = register("chimneystone_slab") {
        SlabBlock(Properties
            .ofFullCopy(CHIMNEYSTONE.get())
            .mapColor(DyeColor.BROWN)) }
    val CHIMNEYSTONE_WALL = register("chimneystone_wall") {
        WallBlock(Properties
            .ofFullCopy(CHIMNEYSTONE.get())) }

    val CHIMNEYSTONE_BRICKS = register("chimneystone_bricks") {
        Block(Properties
            .ofFullCopy(Blocks.TUFF)) }
    val CHIMNEYSTONE_BRICK_STAIRS = register("chimneystone_brick_stairs") {
        StairBlock(CHIMNEYSTONE_BRICKS.get().defaultBlockState(), Properties
            .ofFullCopy(CHIMNEYSTONE.get())
            .mapColor(DyeColor.BROWN)) }
    val CHIMNEYSTONE_BRICK_SLAB = register("chimneystone_brick_slab") {
        SlabBlock(Properties
            .ofFullCopy(CHIMNEYSTONE.get())
            .mapColor(DyeColor.BROWN)) }
    val CHIMNEYSTONE_BRICK_WALL = register("chimneystone_brick_wall") {
        WallBlock(Properties
            .ofFullCopy(CHIMNEYSTONE.get())) }

    val CHISELED_CHIMNEYSTONE = register("chiseled_chimneystone") {
        Block(Properties
            .ofFullCopy(Blocks.TUFF)) }
    val POLISHED_CHIMNEYSTONE = register("polished_chimneystone") {
        Block(Properties
            .ofFullCopy(Blocks.TUFF)) }
    val POLISHED_CHIMNEYSTONE_STAIRS = register("polished_chimneystone_stairs") {
        StairBlock(POLISHED_CHIMNEYSTONE.get().defaultBlockState(), Properties
            .ofFullCopy(POLISHED_CHIMNEYSTONE.get())
            .mapColor(DyeColor.BROWN)) }
    val POLISHED_CHIMNEYSTONE_SLAB = register("polished_chimneystone_slab") {
        SlabBlock(Properties
            .ofFullCopy(POLISHED_CHIMNEYSTONE.get())
            .mapColor(DyeColor.BROWN)) }

    val BASKING_SHARK_PLUSHIE = register("basking_shark_plushie") {
        createPlushieBlock(PlushieBlock.Variant.BASKING_SHARK) }
    val BULL_SHARK_PLUSHIE = register("bull_shark_plushie") {
        createPlushieBlock(PlushieBlock.Variant.BULL_SHARK) }
    val FRILLED_SHARK_PLUSHIE = register("frilled_shark_plushie") {
        createPlushieBlock(PlushieBlock.Variant.FRILLED_SHARK) }
    val GREAT_WHITE_SHARK_PLUSHIE = register("great_white_shark_plushie") {
        createPlushieBlock(PlushieBlock.Variant.GREAT_WHITE_SHARK) }
    val HAMMERHEAD_SHARK_PLUSHIE = register("hammerhead_shark_plushie") {
        createPlushieBlock(PlushieBlock.Variant.HAMMERHEAD_SHARK) }
    val THRESHER_SHARK_PLUSHIE = register("thresher_shark_plushie") {
        createPlushieBlock(PlushieBlock.Variant.THRESHER_SHARK) }
    val TIGER_SHARK_PLUSHIE = register("tiger_shark_plushie") {
        createPlushieBlock(PlushieBlock.Variant.TIGER_SHARK) }
    val WHALE_SHARK_PLUSHIE = register("whale_shark_plushie") {
        createPlushieBlock(PlushieBlock.Variant.WHALE_SHARK) }

    val PEARL_BLOCK = register("pearl_block") { Block(Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)) }
    val BLACK_PEARL_BLOCK = register("black_pearl_block") { Block(Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)) }

    val CRAB_POT = register("crab_pot") { CrateBlock(Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()) }
    val HYBRID_CRATE = register("hybrid_crate") { CrateBlock(Properties.ofFullCopy(Blocks.OAK_PLANKS)) }
    val OAK_CRATE = register("oak_crate") { CrateBlock(Properties.ofFullCopy(Blocks.OAK_PLANKS)) }
    val SPRUCE_CRATE = register("spruce_crate") { CrateBlock(Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)) }
    val BIRCH_CRATE = register("birch_crate") { CrateBlock(Properties.ofFullCopy(Blocks.BIRCH_PLANKS)) }
    val DARK_OAK_CRATE = register("dark_oak_crate") { CrateBlock(Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS)) }
    val ACACIA_CRATE = register("acacia_crate") { CrateBlock(Properties.ofFullCopy(Blocks.ACACIA_PLANKS)) }
    val JUNGLE_CRATE = register("jungle_crate") { CrateBlock(Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)) }
    val MANGROVE_CRATE = register("mangrove_crate") { CrateBlock(Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)) }
    val CHERRY_CRATE = register("cherry_crate") { CrateBlock(Properties.ofFullCopy(Blocks.CHERRY_PLANKS)) }
    val BAMBOO_CRATE = register("bamboo_crate") { CrateBlock(Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)) }

    val SARGASSUM = register("sargassum") {
        SargassumBlock(Properties.ofFullCopy(Blocks.KELP)
            .noOcclusion())
    }

    val SARGASSUM_PLANT = register("sargassum_plant") {
        SargassumPlantBlock(Properties.ofFullCopy(Blocks.KELP_PLANT)
            .noOcclusion()
            .dropsLike(SARGASSUM.get()))
    }

    val BULL_KELP = register("bull_kelp") {
        BullKelpBlock(Properties.ofFullCopy(Blocks.KELP)
            .noOcclusion())
    }

    val BULL_KELP_PLANT = register("bull_kelp_plant") {
        BullKelpPlantBlock(Properties.ofFullCopy(Blocks.KELP_PLANT)
            .noOcclusion()
            .dropsLike(BULL_KELP.get()))
    }

    val DELESSERIA = register("delesseria") {
        DelesseriaBlock(Properties.ofFullCopy(Blocks.KELP)
            .noOcclusion())
    }

    val DELESSERIA_PLANT = register("delesseria_plant") {
        DelesseriaPlantBlock(Properties.ofFullCopy(Blocks.KELP_PLANT)
            .noOcclusion()
            .dropsLike(DELESSERIA.get()))
    }

    val FLOATING_SARGASSUM = register("floating_sargassum") {
        FloatingSargassumBlock(Properties.ofFullCopy(Blocks.LILY_PAD)
            .noCollission()
            .instabreak()
            .mapColor(MapColor.WOOD))
    }

    val WATER_LETTUCE = register("water_lettuce") {
        WaterLettuceBlock(Properties.ofFullCopy(Blocks.LILY_PAD)
            .noCollission()
            .instabreak())
    }

    val WATER_HYACINTH = register("water_hyacinth") {
        WaterHyacinthBlock(Properties.ofFullCopy(Blocks.LILY_PAD)
            .noCollission()
            .instabreak())
    }

    val JUNGLE_LILY_PAD = register("jungle_lily_pad") {
        JungleLilyPadBlock(Properties.ofFullCopy(Blocks.LILY_PAD)
            .instabreak())
    }

    val RAFT = register("raft") {
        RaftBlock(Properties.ofFullCopy(Blocks.OAK_WOOD))
    }

    val OAK_RAFT = register("oak_raft") {
        RaftBlock(Properties.ofFullCopy(Blocks.OAK_WOOD))
    }

    val SPRUCE_RAFT = register("spruce_raft") {
        RaftBlock(Properties.ofFullCopy(Blocks.SPRUCE_WOOD))
    }

    val DARK_OAK_RAFT = register("dark_oak_raft") {
        RaftBlock(Properties.ofFullCopy(Blocks.DARK_OAK_WOOD))
    }

    val BIRCH_RAFT = register("birch_raft") {
        RaftBlock(Properties.ofFullCopy(Blocks.BIRCH_WOOD))
    }

    val CHERRY_RAFT = register("cherry_raft") {
        RaftBlock(Properties.ofFullCopy(Blocks.CHERRY_WOOD))
    }

    val JUNGLE_RAFT = register("jungle_raft") {
        RaftBlock(Properties.ofFullCopy(Blocks.JUNGLE_WOOD))
    }

    val ACACIA_RAFT = register("acacia_raft") {
        RaftBlock(Properties.ofFullCopy(Blocks.ACACIA_WOOD))
    }

    val MANGROVE_RAFT = register("mangrove_raft") {
        RaftBlock(Properties.ofFullCopy(Blocks.MANGROVE_WOOD))
    }

    val DRIFTWOOD_RAFT = register("driftwood_raft") {
        RaftBlock(Properties.ofFullCopy(Blocks.OAK_WOOD))
    }

    val GLOWING_PLANKTON = register("glowing_plankton") {
        GlowingPlanktonBlock(Properties.ofFullCopy(Blocks.LILY_PAD)
        .noOcclusion()
        .replaceable()
        .noCollission()
        .instabreak())
    }

    val SHORT_RED_ALGAE = register("short_red_algae") { ShortRedAlgaeBlock(Properties.ofFullCopy(Blocks.SEAGRASS).offsetType(BlockBehaviour.OffsetType.XZ)) }
    val RED_ALGAE = register("red_algae") { RedAlgaeBlock(Properties.ofFullCopy(Blocks.SEAGRASS).offsetType(BlockBehaviour.OffsetType.XZ)) }
    val TALL_RED_ALGAE = register("tall_red_algae") { TallRedAlgaeBlock(Properties.ofFullCopy(Blocks.TALL_SEAGRASS).offsetType(BlockBehaviour.OffsetType.NONE)) }

    val SEA_LETTUCE = register("sea_lettuce") { SeaLettuceBlock(Properties.ofFullCopy(Blocks.SEAGRASS).offsetType(BlockBehaviour.OffsetType.XZ)) }
    val TALL_SEA_LETTUCE = register("tall_sea_lettuce") { TallSeaLettuceBlock(Properties.ofFullCopy(Blocks.TALL_SEAGRASS).offsetType(BlockBehaviour.OffsetType.NONE)) }

    val BONE_WORMS = register("bone_worms") { BoneWormsBlock(Properties.ofFullCopy(Blocks.SEAGRASS)) }

    val DEAD_LOPHELIA_CORAL_BLOCK = register("dead_lophelia_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BLEACHED_LOPHELIA_CORAL_BLOCK = register("bleached_lophelia_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val LOPHELIA_CORAL_BLOCK = register("lophelia_coral_block") {
        CoralBlock(DEAD_LOPHELIA_CORAL_BLOCK.get(),
            Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .sound(SoundType.CORAL_BLOCK))
    }

    val DEAD_LOPHELIA_CORAL_FAN = register("dead_lophelia_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_LOPHELIA_CORAL_FAN = register("bleached_lophelia_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val LOPHELIA_CORAL_FAN = register("lophelia_coral_fan") { CoralFanBlock(DEAD_LOPHELIA_CORAL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_LOPHELIA_CORAL = register("dead_lophelia_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_LOPHELIA_CORAL = register("bleached_lophelia_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val LOPHELIA_CORAL = register("lophelia_coral") { CoralPlantBlock(DEAD_LOPHELIA_CORAL.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL)) }
    val DEAD_LOPHELIA_CORAL_WALL_FAN = register("dead_lophelia_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val BLEACHED_LOPHELIA_CORAL_WALL_FAN = register("bleached_lophelia_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val LOPHELIA_CORAL_WALL_FAN = register("lophelia_coral_wall_fan") { CoralWallFanBlock(DEAD_LOPHELIA_CORAL_WALL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_BAMBOO_CORAL_BLOCK = register("dead_bamboo_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BLEACHED_BAMBOO_CORAL_BLOCK = register("bleached_bamboo_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BAMBOO_CORAL_BLOCK = register("bamboo_coral_block") {
        CoralBlock(DEAD_BAMBOO_CORAL_BLOCK.get(),
            Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .sound(SoundType.CORAL_BLOCK))
    }

    val DEAD_BAMBOO_CORAL_FAN = register("dead_bamboo_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_BAMBOO_CORAL_FAN = register("bleached_bamboo_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BAMBOO_CORAL_FAN = register("bamboo_coral_fan") { CoralFanBlock(DEAD_BAMBOO_CORAL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_BAMBOO_CORAL = register("dead_bamboo_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_BAMBOO_CORAL = register("bleached_bamboo_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BAMBOO_CORAL = register("bamboo_coral") { CoralPlantBlock(DEAD_BAMBOO_CORAL.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL)) }
    val DEAD_BAMBOO_CORAL_WALL_FAN = register("dead_bamboo_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val BLEACHED_BAMBOO_CORAL_WALL_FAN = register("bleached_bamboo_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val BAMBOO_CORAL_WALL_FAN = register("bamboo_coral_wall_fan") { CoralWallFanBlock(DEAD_BAMBOO_CORAL_WALL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_ROSE_CORAL_BLOCK = register("dead_rose_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_PINK))
    }

    val BLEACHED_ROSE_CORAL_BLOCK = register("bleached_rose_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val ROSE_CORAL_BLOCK = register("rose_coral_block") {
        CoralBlock(
            DEAD_ROSE_CORAL_BLOCK.get(),
            Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_PINK)
                .sound(SoundType.CORAL_BLOCK))
    }

    val DEAD_ROSE_CORAL_FAN = register("dead_rose_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_ROSE_CORAL_FAN = register("bleached_rose_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val ROSE_CORAL_FAN = register("rose_coral_fan") { CoralFanBlock(DEAD_ROSE_CORAL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_ROSE_CORAL = register("dead_rose_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_ROSE_CORAL = register("bleached_rose_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val ROSE_CORAL = register("rose_coral") { CoralPlantBlock(DEAD_ROSE_CORAL.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL)) }
    val DEAD_ROSE_CORAL_WALL_FAN = register("dead_rose_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val BLEACHED_ROSE_CORAL_WALL_FAN = register("bleached_rose_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val ROSE_CORAL_WALL_FAN = register("rose_coral_wall_fan") { CoralWallFanBlock(DEAD_ROSE_CORAL_WALL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_LEAF_CORAL_BLOCK = register("dead_leaf_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_GREEN))
    }

    val BLEACHED_LEAF_CORAL_BLOCK = register("bleached_leaf_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_GREEN))
    }

    val LEAF_CORAL_BLOCK = register("leaf_coral_block") {
        CoralBlock(DEAD_LEAF_CORAL_BLOCK.get(),
            Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_GREEN)
                .sound(SoundType.CORAL_BLOCK))
    }

    val DEAD_LEAF_CORAL_FAN = register("dead_leaf_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_LEAF_CORAL_FAN = register("bleached_leaf_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val LEAF_CORAL_FAN = register("leaf_coral_fan") { CoralFanBlock(DEAD_LEAF_CORAL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_LEAF_CORAL = register("dead_leaf_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_LEAF_CORAL = register("bleached_leaf_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val LEAF_CORAL = register("leaf_coral") { CoralPlantBlock(DEAD_LEAF_CORAL.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL)) }
    val DEAD_LEAF_CORAL_WALL_FAN = register("dead_leaf_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val BLEACHED_LEAF_CORAL_WALL_FAN = register("bleached_leaf_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val LEAF_CORAL_WALL_FAN = register("leaf_coral_wall_fan") { CoralWallFanBlock(DEAD_LEAF_CORAL_WALL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_SUN_CORAL_BLOCK = register("dead_sun_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BLEACHED_SUN_CORAL_BLOCK = register("bleached_sun_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val SUN_CORAL_BLOCK = register("sun_coral_block") {
        CoralBlock(DEAD_SUN_CORAL_BLOCK.get(),
            Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_ORANGE)
                .sound(SoundType.CORAL_BLOCK))
    }

    val DEAD_SUN_CORAL_FAN = register("dead_sun_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_SUN_CORAL_FAN = register("bleached_sun_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val SUN_CORAL_FAN = register("sun_coral_fan") { CoralFanBlock(DEAD_SUN_CORAL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_SUN_CORAL = register("dead_sun_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_SUN_CORAL = register("bleached_sun_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val SUN_CORAL = register("sun_coral") { CoralPlantBlock(DEAD_SUN_CORAL.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL)) }
    val DEAD_SUN_CORAL_WALL_FAN = register("dead_sun_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val BLEACHED_SUN_CORAL_WALL_FAN = register("bleached_sun_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val SUN_CORAL_WALL_FAN = register("sun_coral_wall_fan") { CoralWallFanBlock(DEAD_SUN_CORAL_WALL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_BUTTON_CORAL_BLOCK = register("dead_button_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BLEACHED_BUTTON_CORAL_BLOCK = register("bleached_button_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BUTTON_CORAL_BLOCK = register("button_coral_block") {
        CoralBlock(DEAD_BUTTON_CORAL_BLOCK.get(),
            Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .sound(SoundType.CORAL_BLOCK))
    }

    val DEAD_BUTTON_CORAL_FAN = register("dead_button_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_BUTTON_CORAL_FAN = register("bleached_button_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BUTTON_CORAL_FAN = register("button_coral_fan") { CoralFanBlock(DEAD_BUTTON_CORAL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_BUTTON_CORAL = register("dead_button_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_BUTTON_CORAL = register("bleached_button_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BUTTON_CORAL = register("button_coral") { CoralPlantBlock(DEAD_BUTTON_CORAL.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL)) }
    val DEAD_BUTTON_CORAL_WALL_FAN = register("dead_button_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val BLEACHED_BUTTON_CORAL_WALL_FAN = register("bleached_button_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val BUTTON_CORAL_WALL_FAN = register("button_coral_wall_fan") { CoralWallFanBlock(DEAD_BUTTON_CORAL_WALL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_ZIGZAG_CORAL_BLOCK = register("dead_zigzag_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BLEACHED_ZIGZAG_CORAL_BLOCK = register("bleached_zigzag_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val ZIGZAG_CORAL_BLOCK = register("zigzag_coral_block") {
        CoralBlock(DEAD_ZIGZAG_CORAL_BLOCK.get(),
            Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .sound(SoundType.CORAL_BLOCK))
    }

    val DEAD_ZIGZAG_CORAL_FAN = register("dead_zigzag_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_ZIGZAG_CORAL_FAN = register("bleached_zigzag_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val ZIGZAG_CORAL_FAN = register("zigzag_coral_fan") { CoralFanBlock(DEAD_ZIGZAG_CORAL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_ZIGZAG_CORAL = register("dead_zigzag_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_ZIGZAG_CORAL = register("bleached_zigzag_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val ZIGZAG_CORAL = register("zigzag_coral") { CoralPlantBlock(DEAD_ZIGZAG_CORAL.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL)) }
    val DEAD_ZIGZAG_CORAL_WALL_FAN = register("dead_zigzag_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val BLEACHED_ZIGZAG_CORAL_WALL_FAN = register("bleached_zigzag_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val ZIGZAG_CORAL_WALL_FAN = register("zigzag_coral_wall_fan") { CoralWallFanBlock(DEAD_ZIGZAG_CORAL_WALL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_THORN_CORAL_BLOCK = register("dead_thorn_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BLEACHED_THORN_CORAL_BLOCK = register("bleached_thorn_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val THORN_CORAL_BLOCK = register("thorn_coral_block") {
        CoralBlock(DEAD_THORN_CORAL_BLOCK.get(),
            Properties.ofFullCopy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.COLOR_BLACK)
                .sound(SoundType.CORAL_BLOCK))
    }

    val DEAD_THORN_CORAL_FAN = register("dead_thorn_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_THORN_CORAL_FAN = register("bleached_thorn_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val THORN_CORAL_FAN = register("thorn_coral_fan") { CoralFanBlock(DEAD_THORN_CORAL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_THORN_CORAL = register("dead_thorn_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_THORN_CORAL = register("bleached_thorn_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val DEAD_THORN_CORAL_WALL_FAN = register("dead_thorn_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val BLEACHED_THORN_CORAL_WALL_FAN = register("bleached_thorn_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val THORN_CORAL = register("thorn_coral") { CoralPlantBlock(DEAD_THORN_CORAL.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL)) }
    val THORN_CORAL_WALL_FAN = register("thorn_coral_wall_fan") { CoralWallFanBlock(DEAD_THORN_CORAL_WALL_FAN.get(), Properties.ofFullCopy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val BLEACHED_FIRE_CORAL_BLOCK = register("bleached_fire_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BLEACHED_FIRE_CORAL_FAN = register("bleached_fire_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_FIRE_CORAL = register("bleached_fire_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_FIRE_CORAL_WALL_FAN = register("bleached_fire_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }

    val BLEACHED_TUBE_CORAL_BLOCK = register("bleached_tube_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BLEACHED_TUBE_CORAL_FAN = register("bleached_tube_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_TUBE_CORAL = register("bleached_tube_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_TUBE_CORAL_WALL_FAN = register("bleached_tube_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }

    val BLEACHED_HORN_CORAL_BLOCK = register("bleached_horn_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BLEACHED_HORN_CORAL_FAN = register("bleached_horn_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_HORN_CORAL = register("bleached_horn_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_HORN_CORAL_WALL_FAN = register("bleached_horn_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }

    val BLEACHED_BUBBLE_CORAL_BLOCK = register("bleached_bubble_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BLEACHED_BUBBLE_CORAL_FAN = register("bleached_bubble_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_BUBBLE_CORAL = register("bleached_bubble_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_BUBBLE_CORAL_WALL_FAN = register("bleached_bubble_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }

    val BLEACHED_BRAIN_CORAL_BLOCK = register("bleached_brain_coral_block") {
        Block(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_BLOCK)
            .mapColor(MapColor.TERRACOTTA_WHITE))
    }

    val BLEACHED_BRAIN_CORAL_FAN = register("bleached_brain_coral_fan") { BaseCoralFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BLEACHED_BRAIN_CORAL = register("bleached_brain_coral") { BaseCoralPlantBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL)) }
    val BLEACHED_BRAIN_CORAL_WALL_FAN = register("bleached_brain_coral_wall_fan") { BaseCoralWallFanBlock(Properties.ofFullCopy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }


    val GLOWSTICK = register("glowstick") {
        GlowstickBlock(Properties.ofFullCopy(Blocks.TORCH)
            .noCollission()
            .lightLevel(GlowstickBlock::luminance)
            .noOcclusion())
    }

    val WALL_GLOWSTICK = register("wall_glowstick") {
        WallGlowstickBlock(Properties.ofFullCopy(Blocks.WALL_TORCH)
            .dropsLike(GLOWSTICK.get())
            .noCollission()
            .lightLevel(GlowstickBlock::luminance)
            .noOcclusion())
    }

    val THERMAL_VENT = register("hydrothermal_vent_shaft") {
        ThermalVentBlock(2,
            Properties.ofFullCopy(Blocks.TUFF)
            .noOcclusion()
            .strength(0.5F)
            .pushReaction(PushReaction.DESTROY))
    }

    val GIANT_THERMAL_VENT = register("giant_thermal_vent") {
        GiantThermalVentBlock(2,
            Properties.ofFullCopy(Blocks.TUFF)
            .strength(0.5F)
            .pushReaction(PushReaction.NORMAL))
    }

    val TUBE_WORM = register("tube_worm") {
        TubeWormBlock(Properties.of()
            .mapColor(MapColor.COLOR_GREEN)
            .sound(SoundType.SLIME_BLOCK)
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY))
    }

    val TUBE_SPONGE = register("tube_sponge") {
        LivingSpongeBlock(Properties.of()
            .mapColor(MapColor.COLOR_YELLOW)
            .sound(SoundType.SLIME_BLOCK)
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY))
    }

    val GLASS_SPONGE = register("glass_sponge") {
        LivingSpongeBlock(Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GRAY)
            .sound(SoundType.SLIME_BLOCK)
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY)
        )
    }

    val HARP_SPONGE = register("harp_sponge") {
        LivingSpongeBlock(Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GRAY)
            .sound(SoundType.SLIME_BLOCK)
            .noCollission()
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY)
        )
    }

    val PING_PONG_SPONGE = register("ping_pong_sponge") {
        LivingSpongeBlock(Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GRAY)
            .sound(SoundType.SLIME_BLOCK)
            .noCollission()
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY)
        )
    }

    val GIANT_CLAM = register("giant_clam") {
        GiantClamBlock(true, Properties.ofFullCopy(Blocks.TUFF)
            .noOcclusion()
            .strength(1.0F)
            .pushReaction(PushReaction.DESTROY))
    }

    val OYSTER = register("oyster_block") {
        OysterBlock(true, Properties.ofFullCopy(Blocks.TUFF)
            .noOcclusion()
            .strength(1.0F)
            .pushReaction(PushReaction.DESTROY))
    }

    val BUOY = register("buoy") {
        BuoyBlock(Properties.ofFullCopy(Blocks.OAK_PLANKS)
            .noOcclusion()
            .noTerrainParticles()
            .lightLevel { 12 })
    }

    val BELL_BUOY = register("bell_buoy") {
        BellBuoyBlock(Properties.ofFullCopy(Blocks.OAK_PLANKS)
            .noOcclusion()
            .noTerrainParticles())
    }

    val DECORATIVE_BUBBLE_COLUMN = register("decorative_bubble_column") {
        DecorativeBubbleColumnBlock(Properties.ofFullCopy(Blocks.BUBBLE_COLUMN))
    }

    val BUBBLE_NET = register("bubble_net") {
        BubbleNetBlock(Properties.ofFullCopy(Blocks.BUBBLE_COLUMN))
    }

    private fun createPlushieBlock(variant: PlushieBlock.Variant): PlushieBlock {
        return PlushieBlock(
            variant,
            Properties.of().instabreak().pushReaction(PushReaction.DESTROY).sound(SoundType.WOOL)
                .instrument(NoteBlockInstrument.CUSTOM_HEAD)
                .noTerrainParticles()
        )
    }

    fun <T : Block> register(id: String, block: Supplier<T>): RegistryObject<Block> {
        return CommonClass.BLOCKS.register(id, block)
    }

    /**
     * Adds [addedBlocks] to the supported blocks list of a block entity type.
     */
    fun <T : BlockEntity> BlockEntityType<T>.addBlocks(vararg addedBlocks: Block) {
        if (validBlocks !is ImmutableSet) {
            validBlocks.addAll(addedBlocks)
        } else {
            validBlocks = validBlocks.toMutableSet().apply {
                addAll(addedBlocks)
            }
        }
    }
}
