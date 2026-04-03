package dev.hybridlabs.aquatic.block.wood

import dev.hybridlabs.aquatic.block.CattailBlock
import dev.hybridlabs.aquatic.block.DunegrassBlock
import dev.hybridlabs.aquatic.block.HABlocks.register
import dev.hybridlabs.aquatic.block.TallDunegrassBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.ButtonBlock
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.FenceBlock
import net.minecraft.world.level.block.FenceGateBlock
import net.minecraft.world.level.block.PressurePlateBlock
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.TrapDoorBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockSetType

object HAPlatformBlocks {
    val DRIFTWOOD_LOG = register("driftwood_log") {
        BaseWoodBlock(
            Properties.copy(
                Blocks.OAK_LOG
            )
        )
    }
    val STRIPPED_DRIFTWOOD_LOG = register("stripped_driftwood_log") {
        BaseWoodBlock(
            Properties.copy(
                Blocks.OAK_LOG
            )
        )
    }
    val DRIFTWOOD_WOOD = register("driftwood_wood") {
        BaseWoodBlock(
            Properties.copy(
                Blocks.OAK_WOOD
            )
        )
    }
    val STRIPPED_DRIFTWOOD_WOOD = register("stripped_driftwood_wood") {
        BaseWoodBlock(
            Properties.copy(
                Blocks.OAK_WOOD
            )
        )
    }

    val DRIFTWOOD_PLANKS = register("driftwood_planks") {
        object : Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
            override fun isFlammable(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_SLAB = register("driftwood_slab") {
        object : SlabBlock(Properties.copy(Blocks.OAK_SLAB)) {
            override fun isFlammable(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_STAIRS = register("driftwood_stairs") {
        object : StairBlock(DRIFTWOOD_PLANKS.get().defaultBlockState(), Properties.copy(Blocks.OAK_SLAB)) {
            override fun isFlammable(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 20
            }
        }
    }

    val DRIFTWOOD_FENCE = register("driftwood_fence") {
        object : FenceBlock(Properties.copy(Blocks.OAK_FENCE)) {
            override fun isFlammable(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 20
            }


        }
    }

    val DRIFTWOOD_FENCE_GATE = register(
        "driftwood_fence_gate"
    ) {
        object : FenceGateBlock(Properties.copy(Blocks.OAK_FENCE_GATE), HAWoodTypes.DRIFTWOOD) {
            override fun isFlammable(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 5
            }

            override fun getFireSpreadSpeed(
                state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
            ): Int {
                return 20
            }
        }
    }
    val DRIFTWOOD_DOOR = register("driftwood_door") { DoorBlock(Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK) }
    val DRIFTWOOD_TRAPDOOR =
        register("driftwood_trapdoor") { TrapDoorBlock(Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK) }
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

    val DUNEGRASS = register("dunegrass") { DunegrassBlock(Properties.copy(Blocks.GRASS)) }
    val TALL_DUNEGRASS = register("tall_dunegrass") { TallDunegrassBlock(Properties.copy(Blocks.TALL_GRASS)) }
    val CATTAIL = register("cattail") { CattailBlock(Properties.copy(Blocks.TALL_GRASS)) }

}
