package dev.hybridlabs.aquatic.block.wood

import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.ToolAction

open class BaseWoodBlock(settings: Properties) : RotatedPillarBlock(settings) {
    override fun isFlammable(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Boolean {
        return true
    }

    override fun getFlammability(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Int {
        return 5
    }

    override fun getFireSpreadSpeed(
        state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
    ): Int {
        return 5
    }

    override fun getToolModifiedState(
        state: BlockState?,
        context: UseOnContext?,
        toolAction: ToolAction?,
        simulate: Boolean
    ): BlockState? {
        if (context!!.itemInHand.item is AxeItem) {
            if (state!!.block == HAPlatformBlocks.DRIFTWOOD_LOG.get())
                return HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get().defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS))
            if (state.block == HAPlatformBlocks.DRIFTWOOD_WOOD.get())
                return HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get().defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS))
        }
        return super.getToolModifiedState(state, context, toolAction, simulate)
    }
}