package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.FluidTags
import net.minecraft.util.Mth
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.level.block.state.BlockState
import java.util.*

class WaterAnimalSitGoal(
    private val waterAnimal: HAWaterAnimal,
) : Goal() {

    private var sitTime = 0
    private var sitCooldown: Int

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)

        sitCooldown =
            waterAnimal.tickCount +
                    (10 * 40 + waterAnimal.random.nextInt(10) * 80)
    }

    override fun canUse(): Boolean {

        if (
            sitCooldown > waterAnimal.tickCount ||
            !waterAnimal.isInWaterOrBubble ||
            waterAnimal.isSitting()
        ) {
            return false
        }

        return getSitPos() != null
    }

    override fun start() {

        sitTime =
            (5 * 20 + waterAnimal.random.nextInt(10) * 30)

        sitCooldown =
            waterAnimal.tickCount +
                    (10 * 20 + waterAnimal.random.nextInt(10) * 20)

        waterAnimal.startSitting()
    }

    override fun stop() {
        waterAnimal.stopSitting()
    }

    override fun canContinueToUse(): Boolean {
        return sitTime > 0 && waterAnimal.isInWaterOrBubble
    }

    override fun tick() {
        sitTime--

        if (!waterAnimal.onGround()) {
            waterAnimal.deltaMovement =
                waterAnimal.deltaMovement.subtract(0.0, 0.01, 0.0)
        }

        waterAnimal.xRot = 0.0f
        waterAnimal.yRotO = waterAnimal.yRot
    }

    private fun getSitPos(): BlockPos? {

        val blockpos: BlockPos = waterAnimal.blockPosition()

        val mutableBlockPos = BlockPos.MutableBlockPos()
        val mutableBlockPos1 = BlockPos.MutableBlockPos()

        for (blockpos1 in BlockPos.betweenClosed(
            Mth.floor(waterAnimal.x - 3.0),
            Mth.floor(waterAnimal.y - 6.0),
            Mth.floor(waterAnimal.z - 3.0),
            Mth.floor(waterAnimal.x + 3.0),
            Mth.floor(waterAnimal.y + 6.0),
            Mth.floor(waterAnimal.z + 3.0)
        )) {

            if (blockpos != blockpos1) {

                val blockstate: BlockState =
                    waterAnimal.level().getBlockState(
                        mutableBlockPos1.setWithOffset(
                            blockpos1,
                            Direction.DOWN
                        )
                    )

                val flag = blockstate.isSolid

                if (
                    flag &&
                    waterAnimal.level()
                        .getFluidState(blockpos1)
                        .`is`(FluidTags.WATER) &&

                    waterAnimal.level()
                        .getFluidState(
                            mutableBlockPos.setWithOffset(
                                blockpos1,
                                Direction.UP
                            )
                        )
                        .`is`(FluidTags.WATER)
                ) {
                    return blockpos1.immutable()
                }
            }
        }

        return null
    }
}