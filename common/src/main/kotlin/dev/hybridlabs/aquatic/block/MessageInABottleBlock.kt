@file:Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")

package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.item.SeaMessageBookItem
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.pathing.PathComputationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockItem
import net.minecraft.item.BlockPlaceContext
import net.minecraft.item.ItemStack
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties.WATERLOGGED
import net.minecraft.util.StringRepresentable
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockGetter
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.LevelReader
import kotlin.jvm.optionals.getOrNull

/**
 * Represents the Message in a Bottle block.
 * @see MessageInABottleBlockEntity
 */
class MessageInABottleBlock(settings: Properties) : BlockWithEntity(settings), SimpleWaterloggedBlcok {
    init {
        // add waterlogged to default state
        defaultBlockState() = defaultBlockState().with(WATERLOGGED, false)
    }

    override fun getCloneItemStack(world: BlockGetter, pos: BlockPos, state: BlockState): ItemStack {
        val blockEntity = world.getBlockEntity(pos)
        if (blockEntity !is MessageInABottleBlockEntity) {
            return super.getCloneItemStack(world, pos, state)
        }
        return createItemStack(blockEntity)
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        // cannot place below water
        val fluidStateAbove = world.getFluidState(pos.above())
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
        return fluidState.fluid == Fluids.WATER || sideCoversSmallSquare(world, pos.below(), Direction.UP)
    }

    override fun onPlaced(
        world: World,
        pos: BlockPos,
        state: BlockState,
        placer: LivingEntity?,
        stack: ItemStack
    ) {
        stack.getSubNbt(BlockItem.BLOCK_ENTITY_TAG_KEY)?.let { nbt ->
            // if not present, generate a random message
            if (MessageInABottleBlockEntity.MESSAGE_KEY !in nbt) {
                // get a random message
                val registryManager = world.registryManager
                val registry = registryManager.get(HybridAquaticRegistryKeys.SEA_MESSAGE)
                val messageKey = registry.getRandom(world.random).getOrNull()?.registryKey() ?: return
                val message = registry.get(messageKey) ?: return

                // get block entity
                val blockEntity = world.getBlockEntity(pos) as? MessageInABottleBlockEntity ?: return
                blockEntity.messageItemStack = SeaMessageBookItem.createItemStack(message, registryManager)
            }
        }
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        // place in water as waterlogged
        val world = context.world
        val pos = context.blockPos
        val fluidState = world.getFluidState(pos)
        return super.getStateForPlacement(context)?.with(WATERLOGGED, fluidState.fluid == Fluids.WATER)
    }

    override fun isPathfindable(state: BlockState, world: BlockGetter, pos: BlockPos, type: PathComputationType): Boolean {
        return false
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        // tick fluid when waterlogged
        if (state.get(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        // update placement validity
        if (!canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState()
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext?
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
    enum class Variant(val id: String) : StringRepresentable {
        BOTTLE("bottle"),
        JAR("jar"),
        LONGNECK("longneck");

        override fun asString(): String {
            return id
        }

        companion object {
            private val BY_ID = entries.associateBy(Variant::id)

            fun byId(id: String): Variant {
                return BY_ID[id] ?: BOTTLE
            }
        }
    }

    companion object {
        val SHAPE: VoxelShape = Block.box(2.0, 0.0, 2.0, 13.0, 6.0, 14.0)

        val WATER_SHAPE: VoxelShape = Block.box(1.0, 13.0, 1.0, 15.0, 16.0, 15.0)

        fun createItemStack(blockEntity: MessageInABottleBlockEntity): ItemStack {
            val stack = ItemStack(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE)
            stack.orCreateNbt.put(BlockItem.BLOCK_ENTITY_TAG_KEY, blockEntity.createNbt())
            return stack
        }
    }
}