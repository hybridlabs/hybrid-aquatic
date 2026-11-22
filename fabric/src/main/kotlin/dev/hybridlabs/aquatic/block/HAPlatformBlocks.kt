package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.wood.HAWoodTypes
import dev.hybridlabs.hapi.block.HAPIBlockProperties
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.material.MapColor

object HAPlatformBlocks {
    val DRIFTWOOD_LOG =
        HABlocks.register("driftwood_log") { RotatedPillarBlock(HAPIBlockProperties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val STRIPPED_DRIFTWOOD_LOG =
        HABlocks.register("stripped_driftwood_log") { RotatedPillarBlock(HAPIBlockProperties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_WOOD =
        HABlocks.register("driftwood_wood") { RotatedPillarBlock(HAPIBlockProperties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val STRIPPED_DRIFTWOOD_WOOD =
        HABlocks.register("stripped_driftwood_wood") { RotatedPillarBlock(HAPIBlockProperties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_PLANKS =
        HABlocks.register("driftwood_planks") { Block(HAPIBlockProperties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_STAIRS = HABlocks.register(
        "driftwood_stairs"
    ) { StairBlock(DRIFTWOOD_PLANKS.get().defaultBlockState(), HAPIBlockProperties.ofFullCopy(Blocks.OAK_STAIRS)) }
    val DRIFTWOOD_SLAB =
        HABlocks.register("driftwood_slab") { SlabBlock(HAPIBlockProperties.ofFullCopy(Blocks.OAK_STAIRS)) }
    val DRIFTWOOD_BUTTON = HABlocks.register(
        "driftwood_button"
    ) {
        ButtonBlock(
            BlockSetType.OAK, 25,
            HAPIBlockProperties.ofFullCopy(
                Blocks.OAK_BUTTON
            )
        )
    }
    val DRIFTWOOD_PRESSURE_PLATE = HABlocks.register(
        "driftwood_pressure_plate"
    ) {
        PressurePlateBlock(
            BlockSetType.OAK, HAPIBlockProperties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
        )
    }
    val DRIFTWOOD_FENCE =
        HABlocks.register("driftwood_fence") { FenceBlock(HAPIBlockProperties.ofFullCopy(Blocks.OAK_FENCE)) }
    val DRIFTWOOD_FENCE_GATE = HABlocks.register(
        "driftwood_fence_gate"
    ) {
        FenceGateBlock(
            HAWoodTypes.DRIFTWOOD,
            HAPIBlockProperties.ofFullCopy(Blocks.OAK_FENCE)
        )
    }
    val DRIFTWOOD_DOOR =
        HABlocks.register("driftwood_door") {
            DoorBlock(
                BlockSetType.OAK,
                HAPIBlockProperties.ofFullCopy(Blocks.OAK_DOOR)
            )
        }
    val DRIFTWOOD_TRAPDOOR =
        HABlocks.register("driftwood_trapdoor") {
            TrapDoorBlock(
                BlockSetType.OAK,
                HAPIBlockProperties.ofFullCopy(Blocks.OAK_TRAPDOOR)
            )
        }

    val DUNEGRASS =
        HABlocks.register("dunegrass") { DunegrassBlock(HAPIBlockProperties.ofFullCopy(Blocks.SHORT_GRASS)) }
    val TALL_DUNEGRASS =
        HABlocks.register("tall_dunegrass") { TallDunegrassBlock(HAPIBlockProperties.ofFullCopy(Blocks.TALL_GRASS)) }
    val CATTAIL =
        HABlocks.register("cattail") { CattailBlock(HAPIBlockProperties.ofFullCopy(Blocks.TALL_GRASS)) }
    val PAPYRUS =
        HABlocks.register("papyrus") { PapyrusBlock(HAPIBlockProperties.ofFullCopy(Blocks.TALL_GRASS)) }

    val GLOWSLIME_BLOCK = HABlocks.register("glowslime_block") {
        GlowslimeBlock(
            HAPIBlockProperties.of()
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
            HAPIBlockProperties.of()
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
