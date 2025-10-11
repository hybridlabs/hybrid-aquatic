package dev.hybridlabs.aquatic.block.wood

import dev.hybridlabs.aquatic.block.CattailBlock
import dev.hybridlabs.aquatic.block.DunegrassBlock
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.register
import dev.hybridlabs.aquatic.block.TallDunegrassBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockSetType

object HybridAquaticPlatformBlocks {
    val DRIFTWOOD_LOG = register("driftwood_log") {
        BaseWoodBlock(
            Properties.ofFullCopy(
                Blocks.OAK_LOG
            )
        )
    }
    val STRIPPED_DRIFTWOOD_LOG = register("stripped_driftwood_log") {
        BaseWoodBlock(
            Properties.ofFullCopy(
                Blocks.OAK_LOG
            )
        )
    }
    val DRIFTWOOD_WOOD = register("driftwood_wood") {
        BaseWoodBlock(
            Properties.ofFullCopy(
                Blocks.OAK_WOOD
            )
        )
    }
    val STRIPPED_DRIFTWOOD_WOOD = register("stripped_driftwood_wood") {
        BaseWoodBlock(
            Properties.ofFullCopy(
                Blocks.OAK_WOOD
            )
        )
    }

    val DRIFTWOOD_PLANKS = register("driftwood_planks") {
        object : Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
            override fun isFlammable(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_SLAB = register("driftwood_slab") {
        object : SlabBlock(Properties.ofFullCopy(Blocks.OAK_SLAB)) {
            override fun isFlammable(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_STAIRS = register("driftwood_stairs") {
        object : StairBlock(DRIFTWOOD_PLANKS.get().defaultBlockState(), Properties.ofFullCopy(Blocks.OAK_SLAB)) {
            override fun isFlammable(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_FENCE = register("driftwood_fence") {
        object : FenceBlock(Properties.ofFullCopy(Blocks.OAK_FENCE)) {
            override fun isFlammable(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 20
            }


        }
    }

    val DRIFTWOOD_FENCE_GATE = register(
        "driftwood_fence_gate"
    ) {
        object : FenceGateBlock(
            HybridAquaticWoodTypes.DRIFTWOOD, Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
        ) {
            override fun isFlammable(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
            ): Int {
                return 20
            }
        }
    }
    val DRIFTWOOD_DOOR =
        register("driftwood_door") { DoorBlock(BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_DOOR)) }
    val DRIFTWOOD_TRAPDOOR =
        register("driftwood_trapdoor") { TrapDoorBlock(BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)) }
    val DRIFTWOOD_BUTTON = register(
        "driftwood_button"
    ) {
        ButtonBlock(
            BlockSetType.OAK, 25, Properties.ofFullCopy(Blocks.OAK_BUTTON)
        )

    }
    val DRIFTWOOD_PRESSURE_PLATE = register(
        "driftwood_pressure_plate"
    ) {
        PressurePlateBlock(
            BlockSetType.OAK, Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
        )
    }

    val DUNEGRASS = register("dunegrass") { DunegrassBlock(Properties.ofFullCopy(Blocks.SHORT_GRASS)) }
    val TALL_DUNEGRASS = register("tall_dunegrass") { TallDunegrassBlock(Properties.ofFullCopy(Blocks.TALL_GRASS)) }
    val CATTAIL = register("cattail") { CattailBlock(Properties.ofFullCopy(Blocks.TALL_GRASS)) }

}