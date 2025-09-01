package dev.hybridlabs.aquatic.block

import com.google.common.collect.ImmutableSet
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.wood.HybridAquaticWoodTypes
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import java.util.function.Supplier

/**
 * The registry of all blocks in Hybrid Aquatic.
 */
object HybridAquaticBlocks {
    val ANEMONE = register("anemone") {
        AnemoneBlock(
            Properties.of().mapColor(MapColor.TERRACOTTA_PINK).randomTicks().strength(0.4f).noOcclusion()
                .pushReaction(PushReaction.DESTROY).sound(SoundType.SLIME_BLOCK)
        )
    }

    val STRAWBERRY_ANEMONE = register("strawberry_anemone") {
        StrawberryAnemoneBlock(
            Properties.of().mapColor(MapColor.TERRACOTTA_RED).randomTicks().strength(0.4f).noOcclusion()
                .pushReaction(PushReaction.DESTROY).sound(SoundType.SLIME_BLOCK)
        )
    }

    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle") {
        MessageInABottleBlock(
            Properties.copy(Blocks.GLASS).instabreak().pushReaction(PushReaction.DESTROY)
        )
    }

    val BASKING_SHARK_PLUSHIE =
        register("basking_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.BASKING_SHARK, Blocks.GRAY_WOOL) }
    val BULL_SHARK_PLUSHIE =
        register("bull_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.BULL_SHARK, Blocks.LIGHT_GRAY_WOOL) }
    val FRILLED_SHARK_PLUSHIE =
        register("frilled_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.FRILLED_SHARK, Blocks.GRAY_WOOL) }
    val GREAT_WHITE_SHARK_PLUSHIE = register(
        "great_white_shark_plushie"
    ) {
        createPlushieBlock(PlushieBlock.Variant.GREAT_WHITE_SHARK, Blocks.LIGHT_GRAY_WOOL)
    }
    val HAMMERHEAD_SHARK_PLUSHIE = register(
        "hammerhead_shark_plushie"
    ) {
        createPlushieBlock(PlushieBlock.Variant.HAMMERHEAD_SHARK, Blocks.LIGHT_GRAY_WOOL)
    }
    val THRESHER_SHARK_PLUSHIE = register(
        "thresher_shark_plushie"
    ) {
        createPlushieBlock(PlushieBlock.Variant.THRESHER_SHARK, Blocks.LIGHT_BLUE_WOOL)
    }
    val TIGER_SHARK_PLUSHIE =
        register("tiger_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.TIGER_SHARK, Blocks.BLACK_WOOL) }
    val WHALE_SHARK_PLUSHIE =
        register("whale_shark_plushie") { createPlushieBlock(PlushieBlock.Variant.WHALE_SHARK, Blocks.LIGHT_GRAY_WOOL) }

    val CRAB_POT = register(
        "crab_pot"
    ) {
        CrateBlock(
            Properties.copy(Blocks.OAK_PLANKS).noOcclusion().strength(0.75F)
        )
    }

    val HYBRID_CRATE = register(
        "hybrid_crate"
    ) {
        CrateBlock(
            Properties.copy(Blocks.OAK_PLANKS).strength(0.75F)
        )
    }

    val OAK_CRATE = register("oak_crate") {
        CrateBlock(
            Properties.copy(Blocks.OAK_PLANKS).strength(0.75F)
        )
    }

    val SPRUCE_CRATE = register("spruce_crate") {
        CrateBlock(
            Properties.copy(Blocks.SPRUCE_PLANKS).strength(0.75F)
        )
    }

    val BIRCH_CRATE = register("birch_crate") {
        CrateBlock(Properties.copy(Blocks.BIRCH_PLANKS).strength(0.75F))
    }

    val DARK_OAK_CRATE = register("dark_oak_crate") {
        CrateBlock(Properties.copy(Blocks.DARK_OAK_PLANKS).strength(0.75F))
    }

    val ACACIA_CRATE = register(
        "acacia_crate"
    ) {
        CrateBlock(
            Properties.copy(Blocks.ACACIA_PLANKS).strength(0.75F)
        )
    }

    val JUNGLE_CRATE = register("jungle_crate") {
        CrateBlock(
            Properties.copy(Blocks.JUNGLE_PLANKS).strength(0.75F)
        )
    }

    val MANGROVE_CRATE = register(
        "mangrove_crate"
    ) {
        CrateBlock(
            Properties.copy(Blocks.MANGROVE_PLANKS).strength(0.75F)
        )
    }

    val CHERRY_CRATE = register(
        "cherry_crate"
    ) {
        CrateBlock(
            Properties.copy(Blocks.CHERRY_PLANKS).strength(0.75F)
        )
    }

    val SARGASSUM = register("sargassum") {
        SargassumBlock(Properties.copy(Blocks.KELP).noOcclusion())
    }

    val SARGASSUM_PLANT = register("sargassum_plant") {
        SargassumBushBlock(
            Properties.copy(Blocks.KELP).noOcclusion()
                .dropsLike(SARGASSUM.get())
        )
    }

    val FLOATING_SARGASSUM = register(
        "floating_sargassum"
    ) {
        FloatingSargassumBlock(
            FabricBlockSettings.copyOf(Blocks.LILY_PAD).noCollision().breakInstantly().mapColor(MapColor.WOOD)
        )
    }

    val WATER_LETTUCE = register("water_lettuce") {
        WaterLettuceBlock(
            FabricBlockSettings.copyOf(Blocks.LILY_PAD).noCollision().breakInstantly()
        )
    }

    val JUNGLE_LILY_PAD = register("jungle_lily_pad") {
        JungleLilyPadBlock(
            FabricBlockSettings.copyOf(Blocks.LILY_PAD).breakInstantly()
        )
    }

    val RAFT = register("raft") {
        RaftBlock(
            FabricBlockSettings.copyOf(Blocks.OAK_WOOD)
        )
    }

    val GLOWING_PLANKTON = register(
        "glowing_plankton"
    ) {
        GlowingPlanktonBlock(
            FabricBlockSettings.copyOf(Blocks.LILY_PAD).noOcclusion().replaceable().noCollission().instabreak()
        )
    }

    val RED_ALGAE = register("red_algae") { RedAlgaeBlock(Properties.copy((Blocks.SEAGRASS))) }
    val TALL_RED_ALGAE = register("tall_red_algae") {
        TallRedAlgaeBlock(Properties.copy(Blocks.TALL_SEAGRASS))
    }

    val SEA_LETTUCE = register("sea_lettuce") { SeaLettuceBlock(Properties.copy((Blocks.SEAGRASS))) }
    val TALL_SEA_LETTUCE = register("tall_sea_lettuce") { TallSeaLettuceBlock(Properties.copy((Blocks.TALL_SEAGRASS))) }

    val DRIFTWOOD_LOG = register("driftwood_log") { RotatedPillarBlock(Properties.copy((Blocks.OAK_PLANKS))) }
    val STRIPPED_DRIFTWOOD_LOG =
        register("stripped_driftwood_log") { RotatedPillarBlock(Properties.copy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_WOOD = register("driftwood_wood") { RotatedPillarBlock(Properties.copy((Blocks.OAK_PLANKS))) }
    val STRIPPED_DRIFTWOOD_WOOD =
        register("stripped_driftwood_wood") { RotatedPillarBlock(Properties.copy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_PLANKS = register("driftwood_planks") { Block(Properties.copy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_STAIRS =
        register("driftwood_stairs") {
            StairBlock(
                DRIFTWOOD_PLANKS.get().defaultBlockState(),
                Properties.copy(Blocks.OAK_STAIRS)
            )
        }
    val DRIFTWOOD_SLAB = register("driftwood_slab") { SlabBlock(Properties.copy(Blocks.OAK_STAIRS)) }
    val DRIFTWOOD_BUTTON =
        register("driftwood_button") { ButtonBlock(Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 25, true) }
    val DRIFTWOOD_PRESSURE_PLATE = register("driftwood_pressure_plate") {
        PressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            Properties.copy(Blocks.OAK_PRESSURE_PLATE),
            BlockSetType.OAK
        )
    }
    val DRIFTWOOD_FENCE = register("driftwood_fence") { FenceBlock(Properties.copy(Blocks.OAK_FENCE)) }
    val DRIFTWOOD_FENCE_GATE = register("driftwood_fence_gate") {
        FenceGateBlock(Properties.copy(Blocks.OAK_FENCE), HybridAquaticWoodTypes.DRIFTWOOD)
    }
    val DRIFTWOOD_DOOR = register("driftwood_door") { DoorBlock(Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK) }
    val DRIFTWOOD_TRAPDOOR =
        register("driftwood_trapdoor") { TrapDoorBlock(Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK) }

    val DEAD_LOPHELIA_CORAL_BLOCK = register(
        "dead_lophelia_coral_block"
    ) {
        BaseCoralPlantBlock(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_BLOCK).mapColor(MapColor.TERRACOTTA_WHITE).instrument(
                NoteBlockInstrument.BASEDRUM
            )
                .requiresCorrectToolForDrops().strength(1.0F).sound(SoundType.CORAL_BLOCK)
        )
    }

    val LOPHELIA_CORAL_BLOCK = register(
        "lophelia_coral_block"
    ) {
        CoralBlock(
            DEAD_LOPHELIA_CORAL_BLOCK.get(),
            Properties.copy(Blocks.FIRE_CORAL_BLOCK).mapColor(MapColor.TERRACOTTA_WHITE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops().strength(1.0F).sound(SoundType.CORAL_BLOCK)
        )
    }

    val DEAD_BUTTON_CORAL_BLOCK = register(
        "dead_button_coral_block"
    ) {
        BaseCoralPlantBlock(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_BLOCK).mapColor(MapColor.TERRACOTTA_WHITE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops().strength(1.0F).sound(SoundType.CORAL_BLOCK)
        )
    }

    val BUTTON_CORAL_BLOCK = register(
        "button_coral_block"
    ) {
        CoralBlock(
            DEAD_BUTTON_CORAL_BLOCK.get(),
            Properties.copy(Blocks.FIRE_CORAL_BLOCK).mapColor(MapColor.COLOR_PURPLE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops().strength(1.0F).sound(SoundType.CORAL_BLOCK)
        )
    }

    val DEAD_SUN_CORAL_BLOCK = register(
        "dead_sun_coral_block"
    ) {
        BaseCoralPlantBlock(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_BLOCK).mapColor(MapColor.TERRACOTTA_WHITE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops().strength(1.0F).sound(SoundType.CORAL_BLOCK)
        )
    }

    val SUN_CORAL_BLOCK = register(
        "sun_coral_block"
    ) {
        CoralBlock(
            DEAD_SUN_CORAL_BLOCK.get(),
            Properties.copy(Blocks.FIRE_CORAL_BLOCK).mapColor(MapColor.COLOR_PURPLE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops().strength(1.0F).sound(SoundType.CORAL_BLOCK)
        )
    }

    val DEAD_LOPHELIA_CORAL_FAN =
        register("dead_lophelia_coral_fan") { BaseCoralFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_FAN)) }

    val LOPHELIA_CORAL_FAN = register(
        "lophelia_coral_fan"
    ) { CoralFanBlock(DEAD_LOPHELIA_CORAL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_FAN)) }

    val DEAD_LOPHELIA_CORAL =
        register("dead_lophelia_coral") { BaseCoralPlantBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL)) }

    val DEAD_LOPHELIA_CORAL_WALL_FAN = register(
        "dead_lophelia_coral_wall_fan"
    ) {
        BaseCoralWallFanBlock(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN).dropsLike(DEAD_LOPHELIA_CORAL.get())
        )
    }

    val LOPHELIA_CORAL =
        register("lophelia_coral") { CoralBlock(DEAD_LOPHELIA_CORAL.get(), Properties.copy(Blocks.FIRE_CORAL)) }

    val LOPHELIA_CORAL_WALL_FAN = register(
        "lophelia_coral_wall_fan"
    ) {
        CoralWallFanBlock(
            DEAD_LOPHELIA_CORAL_WALL_FAN.get(),
            Properties.copy(Blocks.FIRE_CORAL_WALL_FAN).dropsLike(LOPHELIA_CORAL.get())
        )
    }

    val DEAD_SUN_CORAL_FAN =
        register("dead_sun_coral_fan") { BaseCoralFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_FAN)) }

    val SUN_CORAL_FAN =
        register("sun_coral_fan") { CoralFanBlock(DEAD_SUN_CORAL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_FAN)) }

    val DEAD_SUN_CORAL = register("dead_sun_coral") { BaseCoralPlantBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL)) }

    val DEAD_SUN_CORAL_WALL_FAN = register(
        "dead_sun_coral_wall_fan"
    ) {
        BaseCoralWallFanBlock(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN).dropsLike(DEAD_LOPHELIA_CORAL.get())
        )
    }

    val SUN_CORAL = register("sun_coral") { CoralBlock(DEAD_SUN_CORAL.get(), Properties.copy(Blocks.FIRE_CORAL)) }

    val SUN_CORAL_WALL_FAN = register(
        "sun_coral_wall_fan"
    ) {
        CoralWallFanBlock(
            DEAD_SUN_CORAL_WALL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_WALL_FAN).dropsLike(LOPHELIA_CORAL.get())
        )
    }

    val DEAD_BUTTON_CORAL_FAN =
        register("dead_button_coral_fan") { BaseCoralFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_FAN)) }

    val BUTTON_CORAL_FAN = register(
        "button_coral_fan"
    ) {
        CoralFanBlock(DEAD_BUTTON_CORAL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_FAN))
    }

    val DEAD_BUTTON_CORAL =
        register("dead_button_coral") { BaseCoralPlantBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL)) }

    val DEAD_BUTTON_CORAL_WALL_FAN = register(
        "dead_button_coral_wall_fan"
    ) {
        BaseCoralWallFanBlock(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN).dropsLike(DEAD_LOPHELIA_CORAL.get())
        )
    }

    val BUTTON_CORAL =
        register("button_coral") { CoralBlock(DEAD_BUTTON_CORAL.get(), Properties.copy(Blocks.FIRE_CORAL)) }

    val BUTTON_CORAL_WALL_FAN = register(
        "button_coral_wall_fan"
    ) {
        CoralWallFanBlock(
            DEAD_BUTTON_CORAL_WALL_FAN.get(),
            Properties.copy(Blocks.FIRE_CORAL_WALL_FAN).dropsLike(LOPHELIA_CORAL.get())
        )
    }

    val DEAD_THORN_CORAL_BLOCK = register(
        "dead_thorn_coral_block"
    ) {
        BaseCoralPlantBlock(
            Properties.copy(Blocks.DEAD_FIRE_CORAL_BLOCK).mapColor(MapColor.TERRACOTTA_WHITE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops().strength(1.0F).sound(SoundType.CORAL_BLOCK)
        )
    }

    val THORN_CORAL_BLOCK = register(
        "thorn_coral_block"
    ) {
        CoralBlock(
            DEAD_THORN_CORAL_BLOCK.get(),
            Properties.copy(Blocks.FIRE_CORAL_BLOCK).mapColor(MapColor.COLOR_BLACK)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops().strength(1.0F).sound(SoundType.CORAL_BLOCK)
        )
    }

    val DEAD_THORN_CORAL_FAN =
        register("dead_thorn_coral_fan") { BaseCoralFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_FAN)) }

    val THORN_CORAL_FAN = register(
        "thorn_coral_fan"
    ) {
        CoralFanBlock(DEAD_THORN_CORAL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_FAN))
    }

    val DEAD_THORN_CORAL = register("dead_thorn_coral") { BaseCoralPlantBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL)) }

    val DEAD_THORN_CORAL_WALL_FAN = register(
        "dead_thorn_coral_wall_fan"
    )
    {
        BaseCoralWallFanBlock(Properties.copy(Blocks.DEAD_FIRE_CORAL_WALL_FAN).dropsLike(DEAD_THORN_CORAL.get()))
    }

    val THORN_CORAL = register("thorn_coral") { CoralBlock(DEAD_THORN_CORAL.get(), Properties.copy(Blocks.FIRE_CORAL)) }

    val THORN_CORAL_WALL_FAN = register(
        "thorn_coral_wall_fan"
    ) {
        CoralWallFanBlock(
            DEAD_THORN_CORAL_WALL_FAN.get(), Properties.copy(Blocks.FIRE_CORAL_WALL_FAN).dropsLike(THORN_CORAL.get())
        )
    }

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
                .lightLevel(GlowstickBlock::luminance)
                .noOcclusion()
        )
    }

    val THERMAL_VENT = register("hydrothermal_vent_shaft") {
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

    val TUBE_SPONGE = register("tube_sponge") {
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
            true,
            Properties.copy(Blocks.TUFF).noOcclusion().strength(1.0F).pushReaction(PushReaction.DESTROY)
            //.drops(ResourceLocation(HybridAquatic.MOD_ID, "blocks/giant_clam"))
        )
    }

    val BUOY = register(
        "buoy"
    ) {
        BuoyBlock(
            FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).noOcclusion().noParticlesOnBreak().lightLevel { 12 }
                .strength(0.75F)
        )
    }

    private fun createPlushieBlock(variant: PlushieBlock.Variant, particleBlock: Block): Block {
        return PlushieBlock(
            variant,
            particleBlock,
            FabricBlockSettings.create().breakInstantly().pushReaction(PushReaction.DESTROY).sound(SoundType.WOOL)
                .instrument(NoteBlockInstrument.CUSTOM_HEAD)
        )
    }

    private fun register(id: String, block: Supplier<Block>): RegistryObject<Block> {
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
