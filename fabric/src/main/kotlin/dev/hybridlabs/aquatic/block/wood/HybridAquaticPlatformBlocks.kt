package dev.hybridlabs.aquatic.block.wood

import dev.hybridlabs.aquatic.block.CattailBlock
import dev.hybridlabs.aquatic.block.DunegrassBlock
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.register
import dev.hybridlabs.aquatic.block.TallDunegrassBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.ButtonBlock
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.FenceBlock
import net.minecraft.world.level.block.FenceGateBlock
import net.minecraft.world.level.block.PressurePlateBlock
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.TrapDoorBlock
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.properties.BlockSetType

object HybridAquaticPlatformBlocks {
    val DRIFTWOOD_LOG = register("driftwood_log") { RotatedPillarBlock(Properties.copy((Blocks.OAK_PLANKS))) }
    val STRIPPED_DRIFTWOOD_LOG =
        register("stripped_driftwood_log") { RotatedPillarBlock(Properties.copy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_WOOD = register("driftwood_wood") { RotatedPillarBlock(Properties.copy((Blocks.OAK_PLANKS))) }
    val STRIPPED_DRIFTWOOD_WOOD =
        register("stripped_driftwood_wood") { RotatedPillarBlock(Properties.copy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_PLANKS = register("driftwood_planks") { Block(Properties.copy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_STAIRS = register(
        "driftwood_stairs"
    ) { StairBlock(DRIFTWOOD_PLANKS.get().defaultBlockState(), Properties.copy(Blocks.OAK_STAIRS)) }
    val DRIFTWOOD_SLAB = register("driftwood_slab") { SlabBlock(Properties.copy(Blocks.OAK_STAIRS)) }
    val DRIFTWOOD_BUTTON = register(
        "driftwood_button"
    ) {
        ButtonBlock(Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 25, true)
    }
    val DRIFTWOOD_PRESSURE_PLATE = register(
        "driftwood_pressure_plate"
    ) {
        PressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING, Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK
        )
    }
    val DRIFTWOOD_FENCE = register("driftwood_fence") { FenceBlock(Properties.copy(Blocks.OAK_FENCE)) }
    val DRIFTWOOD_FENCE_GATE = register(
        "driftwood_fence_gate"
    ) {
        FenceGateBlock(Properties.copy(Blocks.OAK_FENCE), HybridAquaticWoodTypes.DRIFTWOOD)
    }
    val DRIFTWOOD_DOOR = register("driftwood_door") { DoorBlock(Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK) }
    val DRIFTWOOD_TRAPDOOR =
        register("driftwood_trapdoor") { TrapDoorBlock(Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK) }

    val DUNEGRASS = register("dunegrass") { DunegrassBlock(Properties.copy(Blocks.GRASS)) }
    val TALL_DUNEGRASS = register("tall_dunegrass") { TallDunegrassBlock(Properties.copy(Blocks.TALL_GRASS)) }
    val CATTAIL = register("cattail") { CattailBlock(Properties.copy(Blocks.TALL_GRASS)) }
}
