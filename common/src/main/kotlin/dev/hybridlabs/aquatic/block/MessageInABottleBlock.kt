@file:Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")

package dev.hybridlabs.aquatic.block

import com.mojang.serialization.MapCodec
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.item.SeaMessageBookItem
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.component.DataComponents
import net.minecraft.util.StringRepresentable
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.CustomData
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import kotlin.jvm.optionals.getOrNull

/**
 * Represents the Message in a Bottle block.
 * @see MessageInABottleBlockEntity
 */
class MessageInABottleBlock(settings: Properties) : BaseEntityBlock(settings), SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, false))
    }

    override fun getCloneItemStack(level: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        val blockEntity = level.getBlockEntity(pos)
        if (blockEntity !is MessageInABottleBlockEntity) {
            return super.getCloneItemStack(level, pos, state)
        }
        return createItemStack(blockEntity)
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val fluidStateAbove = world.getFluidState(pos.above())
        if (!fluidStateAbove.`is`(Fluids.EMPTY)) {
            return false
        }

        val stateBelow = world.getBlockState(pos.below())
        if (stateBelow.block == this) {
            return false
        }

        val fluidState = world.getFluidState(pos)
        return fluidState.`is`(Fluids.WATER) || canSupportCenter(world, pos.below(), Direction.UP)
    }

    override fun setPlacedBy(
        world: Level,
        pos: BlockPos,
        state: BlockState,
        placer: LivingEntity?,
        stack: ItemStack
    ) {
        stack.get(DataComponents.BLOCK_ENTITY_DATA)?.let { component ->
            // if not present, generate a random message
            if (MessageInABottleBlockEntity.MESSAGE_KEY !in component) {
                // get a random message
                val registryManager = world.registryAccess()
                val registry = registryManager.registryOrThrow(HybridAquaticRegistryKeys.SEA_MESSAGE)
                val messageKey = registry.getRandom(world.random).getOrNull()?.key() ?: return
                val message = registry.get(messageKey) ?: return

                // get block entity
                val blockEntity = world.getBlockEntity(pos) as? MessageInABottleBlockEntity ?: return
                blockEntity.messageItemStack = SeaMessageBookItem.createItemStack(message, registryManager)
            }
        }
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        val waterlogged = ctx.level.getFluidState(ctx.clickedPos) == Fluids.WATER.getSource(false)
        return defaultBlockState()
            .setValue(WATERLOGGED, waterlogged)
    }

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean {
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
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        // update placement validity
        if (!canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState()
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return if (state.getValue(WATERLOGGED)) WATER_SHAPE else SHAPE
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
    }

    override fun codec(): MapCodec<out BaseEntityBlock?> {
        TODO("Not yet implemented")
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.ENTITYBLOCK_ANIMATED
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return MessageInABottleBlockEntity(pos, state)
    }

    /**
     * Represents the variants of a Message in a Bottle.
     */
    enum class Variant(val id: String) : StringRepresentable {
        BOTTLE("bottle"),
        JAR("jar"),
        LONGNECK("longneck");

        override fun getSerializedName(): String {
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
        val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 13.0, 6.0, 14.0)

        val WATER_SHAPE: VoxelShape = box(1.0, 13.0, 1.0, 15.0, 16.0, 15.0)

        fun createItemStack(blockEntity: MessageInABottleBlockEntity): ItemStack {
            val stack = ItemStack(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get())
            stack.set(
                DataComponents.BLOCK_ENTITY_DATA,
                CustomData.of(blockEntity.saveWithoutMetadata(blockEntity.level!!.registryAccess()))
            )
            return stack
        }
    }
}