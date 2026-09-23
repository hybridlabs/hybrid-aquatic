package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import dev.hybridlabs.aquatic.entity.mammal.OtterEntity.Companion.OtterAction
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.FluidTags
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.phys.Vec3
import java.util.*

class OtterFollowOwnerGoal(
    private val otter: OtterEntity,
    private val speedModifier: Double,
    private val startDistance: Float,
    private val stopDistance: Float,
) : Goal() {

    private var owner: LivingEntity? = null
    private var repathDelay = 0

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        if (!otter.isTame() || otter.isSitting() || otter.isLeashed || otter.isPassenger) return false

        val candidate = otter.owner ?: return false
        if (candidate.isSpectator) return false
        if (otter.distanceToSqr(candidate) < (startDistance * startDistance)) return false

        owner = candidate
        return true
    }

    override fun canContinueToUse(): Boolean {
        val current = owner ?: return false
        return !otter.navigation.isDone &&
                otter.isTame() &&
                !otter.isSitting() &&
                !otter.isLeashed &&
                otter.distanceToSqr(current) > (stopDistance * stopDistance)
    }

    override fun start() {
        repathDelay = 0
    }

    override fun stop() {
        owner = null
        otter.navigation.stop()
    }

    override fun tick() {
        val current = owner ?: return

        otter.lookControl.setLookAt(current, 10.0f, otter.maxHeadXRot.toFloat())
        otter.setAction(if (otter.isInWater) OtterAction.SWIMMING else OtterAction.WALKING)

        if (--repathDelay > 0) return
        repathDelay = adjustedTickDelay(REPATH_INTERVAL)

        if (otter.distanceToSqr(current) >= TELEPORT_DISTANCE_SQR) {
            teleportToOwner(current)
            return
        }

        otter.navigation.moveTo(current, speedModifier)
    }

    private fun teleportToOwner(current: LivingEntity) {
        val ownerPos = current.blockPosition()

        repeat(TELEPORT_ATTEMPTS) {
            val pos = ownerPos.offset(
                randomOffset(-3, 3),
                randomOffset(-1, 1),
                randomOffset(-3, 3)
            )

            if (!canTeleportTo(pos)) return@repeat

            otter.moveTo(pos.x + 0.5, pos.y.toDouble(), pos.z + 0.5, otter.yRot, otter.xRot)
            otter.navigation.stop()
            return
        }
    }

    private fun randomOffset(min: Int, max: Int): Int {
        return otter.random.nextInt(max - min + 1) + min
    }

    private fun canTeleportTo(pos: BlockPos): Boolean {
        val level = otter.level()

        val floor = level.getBlockState(pos.below())
        val standable = floor.isFaceSturdy(level, pos.below(), Direction.UP) ||
                level.getFluidState(pos).`is`(FluidTags.WATER)
        if (!standable) return false

        val offset = Vec3.atBottomCenterOf(pos).subtract(otter.position())
        return level.noCollision(otter, otter.boundingBox.move(offset))
    }

    companion object {
        private const val REPATH_INTERVAL = 10
        private const val TELEPORT_DISTANCE_SQR = 144.0
        private const val TELEPORT_ATTEMPTS = 10
    }
}
