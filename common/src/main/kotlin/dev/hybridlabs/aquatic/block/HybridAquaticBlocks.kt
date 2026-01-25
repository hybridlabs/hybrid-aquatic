package dev.hybridlabs.aquatic.block

import com.google.common.collect.ImmutableSet
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.block.BaseCoralFanBlock
import net.minecraft.world.level.block.BaseCoralPlantBlock
import net.minecraft.world.level.block.BaseCoralWallFanBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BrushableBlock
import net.minecraft.world.level.block.CoralBlock
import net.minecraft.world.level.block.CoralFanBlock
import net.minecraft.world.level.block.CoralPlantBlock
import net.minecraft.world.level.block.CoralWallFanBlock
import net.minecraft.world.level.block.SandBlock
import net.minecraft.world.level.block.SlimeBlock
import net.minecraft.world.level.block.SoundType
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
object HybridAquaticBlocks {
    val ANEMONE = register(
        "anemone"
    ) {
        AnemoneBlock(
            Properties.of().mapColor(MapColor.TERRACOTTA_PINK).randomTicks().strength(0.4f).noOcclusion()
                .pushReaction(PushReaction.DESTROY).sound(SoundType.SLIME_BLOCK).noParticlesOnBreak()
        )
    }

    val GIANT_GREEN_ANEMONE = register(
        "giant_green_anemone"
    ) {
        GiantGreenAnemoneBlock(
            Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).randomTicks().strength(0.4f).noOcclusion()
                .pushReaction(PushReaction.DESTROY).sound(SoundType.SLIME_BLOCK).noParticlesOnBreak()
        )
    }

    val STRAWBERRY_ANEMONE = register(
        "strawberry_anemone"
    ) {
        StrawberryAnemoneBlock(
            Properties.of().mapColor(MapColor.COLOR_RED).randomTicks().strength(0.4f).noOcclusion()
                .pushReaction(PushReaction.DESTROY).sound(SoundType.SLIME_BLOCK).noParticlesOnBreak()
        )
    }

    val MESSAGE_IN_A_BOTTLE = register(
        "message_in_a_bottle",
    ) {
        MessageInABottleBlock(
            Properties.copy(Blocks.GLASS).instabreak().pushReaction(PushReaction.DESTROY)
        )
    }

    val WHITE_SAND = register("white_sand") { SandBlock(14406560, Properties.copy(Blocks.SAND)) }
    val WHITE_SANDSTONE = register("white_sandstone") { Block(Properties.copy(Blocks.SANDSTONE)) }

    val SUSPICIOUS_RED_SAND = register("suspicious_red_sand") { BrushableBlock(
        Blocks.RED_SAND,
        Properties.copy(Blocks.RED_SAND),
        SoundEvents.BRUSH_SAND,
        SoundEvents.BRUSH_SAND_COMPLETED) }
    
    val CRYSTALLINE_SULFUR = register("crystalline_sulfur") { Block(Properties.copy(Blocks.AMETHYST_BLOCK)) }
    val GRASSY_SAND = register("grassy_sand") { SandBlock(14406560, Properties.copy(Blocks.SAND)) }
    val CORALSTONE = register("coralstone") { Block(Properties.copy(Blocks.SANDSTONE)) }
    val SHORESTONE = register("shorestone") { Block(Properties.copy(Blocks.SANDSTONE)) }
    val BARNACLE_SHORESTONE = register("barnacle_shorestone") { Block(Properties.copy(Blocks.SANDSTONE)) }

    val MARINE_SNOW = register("marine_snow") { SandBlock(14406560, Properties.copy(Blocks.MUD)) }

    val BASKING_SHARK_PLUSHIE = register("basking_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.BASKING_SHARK, Blocks.GRAY_WOOL) }
    val BULL_SHARK_PLUSHIE = register("bull_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.BULL_SHARK, Blocks.LIGHT_GRAY_WOOL) }
    val FRILLED_SHARK_PLUSHIE = register("frilled_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.FRILLED_SHARK, Blocks.GRAY_WOOL) }
    val GREAT_WHITE_SHARK_PLUSHIE = register("great_white_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.GREAT_WHITE_SHARK, Blocks.LIGHT_GRAY_WOOL) }
    val HAMMERHEAD_SHARK_PLUSHIE = register("hammerhead_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.HAMMERHEAD_SHARK, Blocks.LIGHT_GRAY_WOOL) }
    val THRESHER_SHARK_PLUSHIE = register("thresher_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.THRESHER_SHARK, Blocks.LIGHT_BLUE_WOOL) }
    val TIGER_SHARK_PLUSHIE = register("tiger_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.TIGER_SHARK, Blocks.BLACK_WOOL) }
    val WHALE_SHARK_PLUSHIE = register("whale_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.WHALE_SHARK, Blocks.LIGHT_GRAY_WOOL) }

    val PEARL_BLOCK = register("pearl_block") { Block(Properties.copy(Blocks.QUARTZ_BLOCK)) }
    val BLACK_PEARL_BLOCK = register("black_pearl_block") { Block(Properties.copy(Blocks.QUARTZ_BLOCK)) }

    val CRAB_POT = register("crab_pot") { CrateBlock(Properties.copy(Blocks.OAK_PLANKS).noOcclusion()) }
    val HYBRID_CRATE = register("hybrid_crate") { CrateBlock(Properties.copy(Blocks.OAK_PLANKS)) }
    val OAK_CRATE = register("oak_crate") { CrateBlock(Properties.copy(Blocks.OAK_PLANKS)) }
    val SPRUCE_CRATE = register("spruce_crate") { CrateBlock(Properties.copy(Blocks.SPRUCE_PLANKS)) }
    val BIRCH_CRATE = register("birch_crate") { CrateBlock(Properties.copy(Blocks.BIRCH_PLANKS)) }
    val DARK_OAK_CRATE = register("dark_oak_crate") { CrateBlock(Properties.copy(Blocks.DARK_OAK_PLANKS)) }
    val ACACIA_CRATE = register("acacia_crate") { CrateBlock(Properties.copy(Blocks.ACACIA_PLANKS)) }
    val JUNGLE_CRATE = register("jungle_crate") { CrateBlock(Properties.copy(Blocks.JUNGLE_PLANKS)) }
    val MANGROVE_CRATE = register("mangrove_crate") { CrateBlock(Properties.copy(Blocks.MANGROVE_PLANKS)) }
    val CHERRY_CRATE = register("cherry_crate") { CrateBlock(Properties.copy(Blocks.CHERRY_PLANKS)) }
    val BAMBOO_CRATE = register("bamboo_crate") { CrateBlock(Properties.copy(Blocks.BAMBOO_PLANKS)) }

    val SARGASSUM = register(
        "sargassum"
    ) {
        SargassumBlock(
            Properties.copy(Blocks.KELP).noOcclusion()
        )
    }

    val SARGASSUM_PLANT = register(
        "sargassum_plant"
    ) {
        SargassumPlantBlock(
            Properties.copy(Blocks.KELP_PLANT).noOcclusion().dropsLike(SARGASSUM.get())
        )
    }

    val BULL_KELP = register(
        "bull_kelp"
    ) {
        BullKelpBlock(
            Properties.copy(Blocks.KELP).noOcclusion()
        )
    }

    val BULL_KELP_PLANT = register(
        "bull_kelp_plant"
    ) {
        BullKelpPlantBlock(
            Properties.copy(Blocks.KELP_PLANT).noOcclusion().dropsLike(BULL_KELP.get())
        )
    }

    val FLOATING_SARGASSUM = register(
        "floating_sargassum"
    ) {
        FloatingSargassumBlock(
            Properties.copy(Blocks.LILY_PAD).noCollission().instabreak().mapColor(MapColor.WOOD)
        )
    }

    val WATER_LETTUCE = register(
        "water_lettuce"
    ) {
        WaterLettuceBlock(
            Properties.copy(Blocks.LILY_PAD).noCollission().instabreak()
        )
    }

    val JUNGLE_LILY_PAD = register(
        "jungle_lily_pad"
    ) {
        JungleLilyPadBlock(
            Properties.copy(Blocks.LILY_PAD).instabreak()
        )
    }

    val RAFT = register(
        "raft"
    ) {
        RaftBlock(
            Properties.copy(Blocks.OAK_WOOD)
        )
    }

    val GLOWING_PLANKTON = register(
        "glowing_plankton"
    ) {
        GlowingPlanktonBlock(
            Properties.copy(Blocks.LILY_PAD).noOcclusion().replaceable().noCollission().instabreak()
        )
    }


    val SHORT_RED_ALGAE = register("short_red_algae") { ShortRedAlgaeBlock(Properties.copy(Blocks.SEAGRASS).offsetType(BlockBehaviour.OffsetType.XZ)) }
    val RED_ALGAE = register("red_algae") { RedAlgaeBlock(Properties.copy(Blocks.SEAGRASS).offsetType(BlockBehaviour.OffsetType.XZ)) }
    val TALL_RED_ALGAE = register("tall_red_algae") { TallRedAlgaeBlock(Properties.copy(Blocks.TALL_SEAGRASS).offsetType(BlockBehaviour.OffsetType.NONE)) }

    val SEA_LETTUCE = register("sea_lettuce") { SeaLettuceBlock(Properties.copy(Blocks.SEAGRASS).offsetType(BlockBehaviour.OffsetType.XZ)) }
    val TALL_SEA_LETTUCE = register("tall_sea_lettuce") { TallSeaLettuceBlock(Properties.copy(Blocks.TALL_SEAGRASS).offsetType(BlockBehaviour.OffsetType.NONE)) }

    val DEAD_LOPHELIA_CORAL_BLOCK = register(
        "dead_lophelia_coral_block"
    ) {
        Block(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE)
        )
    }

    val LOPHELIA_CORAL_BLOCK = register(
        "lophelia_coral_block"
    ) {
        CoralBlock(
            DEAD_LOPHELIA_CORAL_BLOCK.get(),
            Properties.copy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .sound(SoundType.CORAL_BLOCK)
        )
    }

    val DEAD_LOPHELIA_CORAL_FAN = register("dead_lophelia_coral_fan") { BaseCoralFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val LOPHELIA_CORAL_FAN = register("lophelia_coral_fan") { CoralFanBlock(DEAD_LOPHELIA_CORAL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_LOPHELIA_CORAL = register("dead_lophelia_coral") { BaseCoralPlantBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL)) }
    val LOPHELIA_CORAL = register("lophelia_coral") { CoralPlantBlock(DEAD_LOPHELIA_CORAL.get(), Properties.copy(Blocks.FIRE_CORAL)) }
    val DEAD_LOPHELIA_CORAL_WALL_FAN = register("dead_lophelia_coral_wall_fan") { BaseCoralWallFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val LOPHELIA_CORAL_WALL_FAN = register("lophelia_coral_wall_fan") { CoralWallFanBlock(DEAD_LOPHELIA_CORAL_WALL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_ROSE_CORAL_BLOCK = register(
        "dead_rose_coral_block"
    ) {
        Block(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_PINK)
        )
    }

    val ROSE_CORAL_BLOCK = register(
        "rose_coral_block"
    ) {
        CoralBlock(
            DEAD_ROSE_CORAL_BLOCK.get(),
            Properties.copy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_PINK)
                .sound(SoundType.CORAL_BLOCK)
        )
    }

    val DEAD_ROSE_CORAL_FAN = register("dead_rose_coral_fan") { BaseCoralFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val ROSE_CORAL_FAN = register("rose_coral_fan") { CoralFanBlock(DEAD_ROSE_CORAL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_ROSE_CORAL = register("dead_rose_coral") { BaseCoralPlantBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL)) }
    val ROSE_CORAL = register("rose_coral") { CoralPlantBlock(DEAD_ROSE_CORAL.get(), Properties.copy(Blocks.FIRE_CORAL)) }
    val DEAD_ROSE_CORAL_WALL_FAN = register("dead_rose_coral_wall_fan") { BaseCoralWallFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val ROSE_CORAL_WALL_FAN = register("rose_coral_wall_fan") { CoralWallFanBlock(DEAD_ROSE_CORAL_WALL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_LEAF_CORAL_BLOCK = register(
        "dead_leaf_coral_block"
    ) {
        Block(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_GREEN)
        )
    }

    val LEAF_CORAL_BLOCK = register(
        "leaf_coral_block"
    ) {
        CoralBlock(
            DEAD_LEAF_CORAL_BLOCK.get(),
            Properties.copy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_GREEN)
                .sound(SoundType.CORAL_BLOCK)
        )
    }

    val DEAD_LEAF_CORAL_FAN = register("dead_leaf_coral_fan") { BaseCoralFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val LEAF_CORAL_FAN = register("leaf_coral_fan") { CoralFanBlock(DEAD_LEAF_CORAL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_LEAF_CORAL = register("dead_leaf_coral") { BaseCoralPlantBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL)) }
    val LEAF_CORAL = register("leaf_coral") { CoralPlantBlock(DEAD_LEAF_CORAL.get(), Properties.copy(Blocks.FIRE_CORAL)) }
    val DEAD_LEAF_CORAL_WALL_FAN = register("dead_leaf_coral_wall_fan") { BaseCoralWallFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val LEAF_CORAL_WALL_FAN = register("leaf_coral_wall_fan") { CoralWallFanBlock(DEAD_LEAF_CORAL_WALL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_SUN_CORAL_BLOCK = register(
        "dead_sun_coral_block"
    ) {
        Block(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE)
        )
    }

    val SUN_CORAL_BLOCK = register(
        "sun_coral_block"
    ) {
        CoralBlock(
            DEAD_SUN_CORAL_BLOCK.get(),
            Properties.copy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_ORANGE)
                .sound(SoundType.CORAL_BLOCK)
        )
    }
    val DEAD_SUN_CORAL_FAN = register("dead_sun_coral_fan") { BaseCoralFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val SUN_CORAL_FAN = register("sun_coral_fan") { CoralFanBlock(DEAD_SUN_CORAL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_SUN_CORAL = register("dead_sun_coral") { BaseCoralPlantBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL)) }
    val SUN_CORAL = register("sun_coral") { CoralPlantBlock(DEAD_SUN_CORAL.get(), Properties.copy(Blocks.FIRE_CORAL)) }
    val DEAD_SUN_CORAL_WALL_FAN = register("dead_sun_coral_wall_fan") { BaseCoralWallFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val SUN_CORAL_WALL_FAN = register("sun_coral_wall_fan") { CoralWallFanBlock(DEAD_SUN_CORAL_WALL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_BUTTON_CORAL_BLOCK = register(
        "dead_button_coral_block"
    ) {
        Block(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE)
        )
    }

    val BUTTON_CORAL_BLOCK = register(
        "button_coral_block"
    ) {
        CoralBlock(
            DEAD_BUTTON_CORAL_BLOCK.get(),
            Properties.copy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .sound(SoundType.CORAL_BLOCK)
        )
    }

    val DEAD_BUTTON_CORAL_FAN = register("dead_button_coral_fan") { BaseCoralFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val BUTTON_CORAL_FAN = register("button_coral_fan") { CoralFanBlock(DEAD_BUTTON_CORAL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_BUTTON_CORAL = register("dead_button_coral") { BaseCoralPlantBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL)) }
    val BUTTON_CORAL = register("button_coral") { CoralPlantBlock(DEAD_BUTTON_CORAL.get(), Properties.copy(Blocks.FIRE_CORAL)) }
    val DEAD_BUTTON_CORAL_WALL_FAN = register("dead_button_coral_wall_fan") { BaseCoralWallFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val BUTTON_CORAL_WALL_FAN = register("button_coral_wall_fan") { CoralWallFanBlock(DEAD_BUTTON_CORAL_WALL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val DEAD_THORN_CORAL_BLOCK = register(
        "dead_thorn_coral_block"
    ) {
        Block(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_BLOCK)
                .mapColor(MapColor.TERRACOTTA_WHITE)
        )
    }

    val THORN_CORAL_BLOCK = register(
        "thorn_coral_block"
    ) {
        CoralBlock(
            DEAD_THORN_CORAL_BLOCK.get(),
            Properties.copy(Blocks.FIRE_CORAL_BLOCK)
                .mapColor(MapColor.COLOR_BLACK)
                .sound(SoundType.CORAL_BLOCK)
        )
    }

    val DEAD_THORN_CORAL_FAN = register("dead_thorn_coral_fan") { BaseCoralFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_FAN)) }
    val THORN_CORAL_FAN = register("thorn_coral_fan") { CoralFanBlock(DEAD_THORN_CORAL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_FAN)) }
    val DEAD_THORN_CORAL = register("dead_thorn_coral") { BaseCoralPlantBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL)) }
    val DEAD_THORN_CORAL_WALL_FAN = register("dead_thorn_coral_wall_fan") { BaseCoralWallFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN)) }
    val THORN_CORAL = register("thorn_coral") { CoralPlantBlock(DEAD_THORN_CORAL.get(), Properties.copy(Blocks.FIRE_CORAL)) }
    val THORN_CORAL_WALL_FAN = register("thorn_coral_wall_fan") { CoralWallFanBlock(DEAD_THORN_CORAL_WALL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_WALL_FAN)) }

    val GLOWSTICK = register(
        "glowstick"
    ) {
        GlowstickBlock(
            Properties.copy(Blocks.TORCH).noCollission().lightLevel(GlowstickBlock::luminance).noOcclusion()
        )
    }

    val WALL_GLOWSTICK = register(
        "wall_glowstick"
    ) {
        WallGlowstickBlock(
            Properties.copy(Blocks.WALL_TORCH).dropsLike(GLOWSTICK.get()).noCollission()
                .lightLevel(GlowstickBlock::luminance).noOcclusion()
        )
    }

    val THERMAL_VENT = register(
        "hydrothermal_vent_shaft"
    ) {
        ThermalVentBlock(
            true, 2, Properties.copy(Blocks.TUFF).noOcclusion().strength(0.5F).pushReaction(PushReaction.DESTROY)
        )
    }

    val TUBE_WORM = register(
        "tube_worm"
    ) {
        TubeWormBlock(
            Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.SLIME_BLOCK).noOcclusion()
                .pushReaction(PushReaction.DESTROY)
        )
    }

    val TUBE_SPONGE = register(
        "tube_sponge"
    ) {
        TubeSpongeBlock(
            true,
            Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.SLIME_BLOCK).noOcclusion()
                .pushReaction(PushReaction.DESTROY)
        )
    }

    val GIANT_CLAM = register(
        "giant_clam"
    ) {
        GiantClamBlock(
            true, Properties.copy(Blocks.TUFF).noOcclusion().strength(1.0F).pushReaction(PushReaction.DESTROY)
        )
    }

    val OYSTER = register(
        "oyster"
    ) {
        OysterBlock(
            true, Properties.copy(Blocks.TUFF).noOcclusion().strength(1.0F).pushReaction(PushReaction.DESTROY)
        )
    }

    val BUOY =
        register(
            "buoy"
        ) {
            BuoyBlock(Properties.copy(Blocks.OAK_PLANKS).noOcclusion().noParticlesOnBreak().lightLevel { 12 }
            )
        }

    val GLOWSLIME_BLOCK =
        register(
            "glowslime_block"
        ) {
            SlimeBlock(Properties.copy(Blocks.SLIME_BLOCK).lightLevel { 14 }
            )
        }

    private fun createPlushieBlock(variant: PlushieBlock.Variant, particleBlock: Block ): PlushieBlock {
        return PlushieBlock(
            variant,
            particleBlock,
            Properties.of().instabreak().pushReaction(PushReaction.DESTROY).sound(SoundType.WOOL)
                .instrument(NoteBlockInstrument.CUSTOM_HEAD)
                .noParticlesOnBreak()
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
