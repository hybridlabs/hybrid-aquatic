package dev.hybridlabs.aquatic.block.wood

import dev.hybridlabs.aquatic.block.CattailBlock
import dev.hybridlabs.aquatic.block.DunegrassBlock
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.register
import dev.hybridlabs.aquatic.block.TallDunegrassBlock
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.properties.BlockSetType

object HybridAquaticPlatformBlocks {
    val DRIFTWOOD_LOG = register("driftwood_log") { RotatedPillarBlock(Properties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val STRIPPED_DRIFTWOOD_LOG =
        register("stripped_driftwood_log") { RotatedPillarBlock(Properties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_WOOD = register("driftwood_wood") { RotatedPillarBlock(Properties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val STRIPPED_DRIFTWOOD_WOOD =
        register("stripped_driftwood_wood") { RotatedPillarBlock(Properties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_PLANKS = register("driftwood_planks") { Block(Properties.ofFullCopy((Blocks.OAK_PLANKS))) }
    val DRIFTWOOD_STAIRS = register(
        "driftwood_stairs"
    ) { StairBlock(DRIFTWOOD_PLANKS.get().defaultBlockState(), Properties.ofFullCopy(Blocks.OAK_STAIRS)) }
    val DRIFTWOOD_SLAB = register("driftwood_slab") { SlabBlock(Properties.ofFullCopy(Blocks.OAK_STAIRS)) }
    val DRIFTWOOD_BUTTON = register(
        "driftwood_button"
    ) {
        ButtonBlock(
            BlockSetType.OAK, 25,
            Properties.ofFullCopy(
                Blocks.OAK_BUTTON
            )
        )
    }
    val DRIFTWOOD_PRESSURE_PLATE = register(
        "driftwood_pressure_plate"
    ) {
        PressurePlateBlock(
            BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
        )
    }
    val DRIFTWOOD_FENCE = register("driftwood_fence") { FenceBlock(Properties.ofFullCopy(Blocks.OAK_FENCE)) }
    val DRIFTWOOD_FENCE_GATE = register(
        "driftwood_fence_gate"
    ) {
        FenceGateBlock(
            HybridAquaticWoodTypes.DRIFTWOOD,
            Properties.ofFullCopy(Blocks.OAK_FENCE)
        )
    }
    val DRIFTWOOD_DOOR =
        register("driftwood_door") {
            DoorBlock(
                BlockSetType.OAK,
                Properties.ofFullCopy(Blocks.OAK_DOOR)
            )
        }
    val DRIFTWOOD_TRAPDOOR =
        register("driftwood_trapdoor") {
            TrapDoorBlock(
                BlockSetType.OAK,
                Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
            )
        }

    val DUNEGRASS = register("dunegrass") { DunegrassBlock(Properties.ofFullCopy(Blocks.SHORT_GRASS)) }
    val TALL_DUNEGRASS = register("tall_dunegrass") { TallDunegrassBlock(Properties.ofFullCopy(Blocks.TALL_GRASS)) }
    val CATTAIL = register("cattail") { CattailBlock(Properties.ofFullCopy(Blocks.TALL_GRASS)) }
}