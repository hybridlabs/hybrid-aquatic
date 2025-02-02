package dev.hybridlabs.aquatic.block

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.component.HybridAquaticComponentTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import io.netty.buffer.ByteBuf
import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.Blocks
import net.minecraft.block.ShapeContext
import net.minecraft.block.Waterloggable
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties.WATERLOGGED
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.function.ValueLists
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import java.util.function.IntFunction
import kotlin.jvm.optionals.getOrNull

/**
 * Represents the Message in a Bottle block.
 * @see MessageInABottleBlockEntity
 */
class MessageInABottleBlock(settings: Settings) : BlockWithEntity(settings), Waterloggable {
    init {
        // add waterlogged to default state
        defaultState = defaultState.with(WATERLOGGED, false)
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        // cannot place below water
        val fluidStateAbove = world.getFluidState(pos.up())
        if (fluidStateAbove.fluid != Fluids.EMPTY) {
            return false
        }

        // cannot stack
        val stateBelow = world.getBlockState(pos.down())
        if (stateBelow.block == this) {
            return false
        }

        // check valid placement
        val fluidState = world.getFluidState(pos)
        return fluidState.fluid == Fluids.WATER || sideCoversSmallSquare(world, pos.down(), Direction.UP)
    }

    override fun onPlaced(
        world: World,
        pos: BlockPos,
        state: BlockState,
        placer: LivingEntity?,
        stack: ItemStack
    ) {
        if (!stack.contains(HybridAquaticComponentTypes.STORED_BOTTLE_MESSAGE)) {
            // get a random message
            val registryManager = world.registryManager
            val registry = registryManager.get(HybridAquaticRegistryKeys.SEA_MESSAGE)
            val messageEntry = registry.getRandom(world.random).getOrNull() ?: return

            // get block entity
            val blockEntity = world.getBlockEntity(pos) as? MessageInABottleBlockEntity ?: return
            val stack = ItemStack(HybridAquaticItems.SEA_MESSAGE_BOOK)
            stack.set(HybridAquaticComponentTypes.SEA_MESSAGE, messageEntry)
            blockEntity.messageItemStack = stack
        }
    }

    override fun getPlacementState(context: ItemPlacementContext): BlockState? {
        // place in water as waterlogged
        val world = context.world
        val pos = context.blockPos
        val fluidState = world.getFluidState(pos)
        return super.getPlacementState(context)?.with(WATERLOGGED, fluidState.fluid == Fluids.WATER)
    }

    override fun canPathfindThrough(state: BlockState, type: NavigationType): Boolean {
        return false
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        // tick fluid when waterlogged
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }

        // update placement validity
        if (!canPlaceAt(state, world, pos)) {
            return Blocks.AIR.defaultState
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return if (state.get(WATERLOGGED)) WATER_SHAPE else SHAPE
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(
            // append waterlogged
            builder.add(WATERLOGGED)
        )
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.ENTITYBLOCK_ANIMATED
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return MessageInABottleBlockEntity(pos, state)
    }

    /**
     * Represents the variants of a Message in a Bottle.
     */
    enum class Variant(val index: Int, val id: String) : StringIdentifiable {
        /**
         * The default bottle variant.
         */
        BOTTLE(0, "bottle"),

        /**
         * The jar variant.
         */
        JAR(1, "jar"),

        /**
         * The longneck variant.
         */
        LONGNECK(2, "longneck");

        override fun asString(): String {
            return id
        }

        companion object {
            private val FROM_INDEX: IntFunction<Variant> = ValueLists.createIdToValueFunction(Variant::index, entries.toTypedArray(), ValueLists.OutOfBoundsHandling.ZERO)

            val CODEC: Codec<Variant> = StringIdentifiable.createCodec(::values)
            val PACKET_CODEC: PacketCodec<ByteBuf, Variant> = PacketCodecs.indexed(FROM_INDEX, Variant::index)
        }
    }

    override fun getCodec(): MapCodec<MessageInABottleBlock> {
        return CODEC
    }

    companion object {
        val CODEC: MapCodec<MessageInABottleBlock> = createCodec(::MessageInABottleBlock)

        /**
         * The default shape of a Message in a Bottle block.
         */
        val SHAPE: VoxelShape = createCuboidShape(2.0, 0.0, 2.0, 13.0, 6.0, 14.0)

        /**
         * The shape of a Message in a Bottle block in water.
         */
        val WATER_SHAPE: VoxelShape = createCuboidShape(1.0, 13.0, 1.0, 15.0, 16.0, 15.0)
    }
}
