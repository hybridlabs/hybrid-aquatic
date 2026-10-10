package dev.hybridlabs.aquatic.block

import com.mojang.serialization.MapCodec
import net.minecraft.Util
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.function.BiFunction
import kotlin.math.min

open class IncrementableBlock protected constructor(properties: Properties) : BushBlock(properties), BonemealableBlock {
    public override fun codec(): MapCodec<IncrementableBlock?> {
        return CODEC
    }

    public override fun rotate(blockState: BlockState, rotation: Rotation): BlockState {
        return blockState.setValue(
            FACING,
            rotation.rotate(blockState.getValue(FACING) as Direction)
        ) as BlockState
    }

    public override fun mirror(blockState: BlockState, mirror: Mirror): BlockState {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING) as Direction))
    }

    override fun canBeReplaced(
        blockState: BlockState,
        blockPlaceContext: BlockPlaceContext,
    ): Boolean {
        return if (
            !blockPlaceContext.isSecondaryUseActive &&
            blockPlaceContext.itemInHand.`is`(this.asItem()) &&
            blockState.getValue(AMOUNT) < MAX_COUNT
        ) true else super.canBeReplaced(blockState, blockPlaceContext)
    }

    public override fun getShape(
        blockState: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        collisionContext: CollisionContext,
    ): VoxelShape {
        return SHAPE_BY_PROPERTIES.apply(
            blockState.getValue(FACING) as Direction,
            blockState.getValue(AMOUNT) as Int
        ) as VoxelShape
    }

    override fun getStateForPlacement(blockPlaceContext: BlockPlaceContext): BlockState? {
        val blockstate = blockPlaceContext.level.getBlockState(blockPlaceContext.clickedPos)
        return if (blockstate.`is`(this)) blockstate.setValue(
            AMOUNT,
            min(4, blockstate.getValue(AMOUNT) as Int + 1)
        ) as BlockState else this.defaultBlockState().setValue(
            FACING,
            blockPlaceContext.horizontalDirection.opposite
        ) as BlockState
    }

    override fun createBlockStateDefinition(stateDefinitionBuilder: StateDefinition.Builder<Block?, BlockState?>) {
        stateDefinitionBuilder.add(*arrayOf<Property<*>?>(FACING, AMOUNT))
    }

    override fun isValidBonemealTarget(levelReader: LevelReader, blockPos: BlockPos, blockState: BlockState): Boolean {
        return true
    }

    override fun isBonemealSuccess(
        level: Level,
        randomSource: RandomSource,
        blockPos: BlockPos,
        blockState: BlockState,
    ): Boolean {
        return true
    }

    override fun performBonemeal(
        serverLevel: ServerLevel,
        randomSource: RandomSource,
        blockPos: BlockPos,
        blockState: BlockState,
    ) {
        val i = blockState.getValue(AMOUNT) as Int
        if (i < 4) {
            serverLevel.setBlock(
                blockPos,
                blockState.setValue(AMOUNT, i + 1) as BlockState,
                2
            )
        } else {
            popResource(serverLevel, blockPos, ItemStack(this))
        }
    }

    init {
        this.registerDefaultState(
            ((this.stateDefinition.any() as BlockState).setValue<Direction?, Direction?>(
                FACING,
                Direction.NORTH
            ) as BlockState).setValue(AMOUNT, 1) as BlockState
        )
    }

    companion object {
        val CODEC: MapCodec<IncrementableBlock?> =
            simpleCodec<IncrementableBlock?> { properties: Properties? -> IncrementableBlock(properties!!) }
        const val MIN_COUNT: Int = 1
        const val MAX_COUNT: Int = 4
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val AMOUNT: IntegerProperty = BlockStateProperties.FLOWER_AMOUNT
        private val SHAPE_BY_PROPERTIES: BiFunction<Direction?, Int?, VoxelShape?> =
            Util.memoize<Direction?, Int?, VoxelShape> { direction: Direction?, count: Int? ->
                val avoxelshape: Array<VoxelShape> = arrayOf(
                    box(8.0, 0.0, 8.0, 16.0, 3.0, 16.0),
                    box(8.0, 0.0, 0.0, 16.0, 3.0, 8.0),
                    box(0.0, 0.0, 0.0, 8.0, 3.0, 8.0),
                    box(0.0, 0.0, 8.0, 8.0, 3.0, 16.0)
                )
                var voxelshape = Shapes.empty()

                for (i in 0..<count!!) {
                    val j = Math.floorMod(i - direction!!.get2DDataValue(), 4)
                    voxelshape = Shapes.or(voxelshape, avoxelshape[j])
                }
                voxelshape.singleEncompassing()
            }
    }
}
