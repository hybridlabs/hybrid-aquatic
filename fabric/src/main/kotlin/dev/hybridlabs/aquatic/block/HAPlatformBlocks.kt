package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.wood.HAWoodTypes
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.material.MapColor

object HAPlatformBlocks {
    val DRIFTWOOD_LOG =
        HABlocks.register("driftwood_log") { RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val STRIPPED_DRIFTWOOD_LOG =
        HABlocks.register("stripped_driftwood_log") { RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_WOOD =
        HABlocks.register("driftwood_wood") { RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val STRIPPED_DRIFTWOOD_WOOD =
        HABlocks.register("stripped_driftwood_wood") { RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_PLANKS =
        HABlocks.register("driftwood_planks") { Block(BlockBehaviour.Properties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_STAIRS = HABlocks.register(
        "driftwood_stairs"
    ) { StairBlock(DRIFTWOOD_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)) }
    val DRIFTWOOD_SLAB =
        HABlocks.register("driftwood_slab") { SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)) }
    val DRIFTWOOD_BUTTON = HABlocks.register(
        "driftwood_button"
    ) {
        ButtonBlock(
            BlockSetType.OAK, 25,
            BlockBehaviour.Properties.ofFullCopy(
                Blocks.OAK_BUTTON
            )
        )
    }
    val DRIFTWOOD_PRESSURE_PLATE = HABlocks.register(
        "driftwood_pressure_plate"
    ) {
        PressurePlateBlock(
            BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
        )
    }
    val DRIFTWOOD_FENCE =
        HABlocks.register("driftwood_fence") { FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)) }
    val DRIFTWOOD_FENCE_GATE = HABlocks.register(
        "driftwood_fence_gate"
    ) {
        FenceGateBlock(
            HAWoodTypes.DRIFTWOOD,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
        )
    }
    val DRIFTWOOD_DOOR =
        HABlocks.register("driftwood_door") {
            DoorBlock(
                BlockSetType.OAK,
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
            )
        }
    val DRIFTWOOD_TRAPDOOR =
        HABlocks.register("driftwood_trapdoor") {
            TrapDoorBlock(
                BlockSetType.OAK,
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
            )
        }

    val DUNEGRASS = HABlocks.register("dunegrass") { DunegrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)) }
    val TALL_DUNEGRASS =
        HABlocks.register("tall_dunegrass") { TallDunegrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)) }
    val CATTAIL = HABlocks.register("cattail") { CattailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)) }

    val GLOWSLIME_BLOCK = HABlocks.register("glowslime_block") {
        GlowslimeBlock(
            BlockBehaviour.Properties.of()
            .friction(0.8F)
            .instabreak()
            .noOcclusion()
            .sound(SoundType.SLIME_BLOCK)
            .mapColor { MapColor.COLOR_CYAN }
            .lightLevel { 14 }
        )
    }

    val HAGSLIME_BLOCK = HABlocks.register("hagslime_block") {
        HagslimeBlock(
            BlockBehaviour.Properties.of()
                .instabreak()
                .noOcclusion()
                .sound(SoundType.HONEY_BLOCK)
                .mapColor { MapColor.TERRACOTTA_WHITE }
                .isSuffocating { _, _, _ -> false }
                .speedFactor(0.4F)
                .jumpFactor(0.5F)
        )
    }
}