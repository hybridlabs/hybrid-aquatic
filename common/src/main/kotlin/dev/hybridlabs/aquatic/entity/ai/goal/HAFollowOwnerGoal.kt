package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HATameableWaterAnimal
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.LeavesBlock
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator
import java.util.*
import kotlin.math.abs

class HAFollowOwnerGoal(
    private val tamable: HATameableWaterAnimal,
    private val speedModifier: Double,
    private val startDistance: Float,
    private val stopDistance: Float,
    private val canFly: Boolean,
) : Goal() {
    private var owner: LivingEntity? = null
    private val level: LevelReader = tamable.level()
    private val navigation: PathNavigation = tamable.getNavigation()
    private var timeToRecalcPath = 0
    private var oldWaterCost = 0f

    init {
        this.flags = EnumSet.of<Flag?>(Flag.MOVE, Flag.LOOK)
        require(!(tamable.getNavigation() !is GroundPathNavigation && tamable.getNavigation() !is FlyingPathNavigation)) { "Unsupported mob type for FollowOwnerGoal" }
    }

    override fun canUse(): Boolean {
        val livingentity = this.tamable.owner
        if (livingentity == null) {
            return false
        } else if (livingentity.isSpectator) {
            return false
        } else if (this.unableToMove()) {
            return false
        } else if (this.tamable.distanceToSqr(livingentity) < (this.startDistance * this.startDistance).toDouble()) {
            return false
        } else {
            this.owner = livingentity
            return true
        }
    }

    override fun canContinueToUse(): Boolean {
        if (this.navigation.isDone) {
            return false
        } else if (this.unableToMove()) {
            return false
        } else {
            return !(this.tamable.distanceToSqr(this.owner) <= (this.stopDistance * this.stopDistance).toDouble())
        }
    }

    private fun unableToMove(): Boolean {
        return this.tamable.isOrderedToSit() || this.tamable.isPassenger || this.tamable.isLeashed
    }

    override fun start() {
        this.timeToRecalcPath = 0
        this.oldWaterCost = this.tamable.getPathfindingMalus(BlockPathTypes.WATER)
        this.tamable.setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
    }

    override fun stop() {
        this.owner = null
        this.navigation.stop()
        this.tamable.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost)
    }

    override fun tick() {
        this.tamable.getLookControl().setLookAt(this.owner, 10.0f, this.tamable.maxHeadXRot.toFloat())
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10)
            if (this.tamable.distanceToSqr(this.owner) >= 144.0) {
                this.teleportToOwner()
            } else {
                this.navigation.moveTo(this.owner, this.speedModifier)
            }
        }
    }

    private fun teleportToOwner() {
        val blockpos = this.owner!!.blockPosition()

        for (i in 0..9) {
            val j = this.randomIntInclusive(-3, 3)
            val k = this.randomIntInclusive(-1, 1)
            val l = this.randomIntInclusive(-3, 3)
            val flag = this.maybeTeleportTo(blockpos.x + j, blockpos.y + k, blockpos.z + l)
            if (flag) {
                return
            }
        }
    }

    private fun maybeTeleportTo(x: Int, y: Int, z: Int): Boolean {
        if (abs(x.toDouble() - this.owner!!.x) < 2.0 && abs(z.toDouble() - this.owner!!.z) < 2.0) {
            return false
        } else if (!this.canTeleportTo(BlockPos(x, y, z))) {
            return false
        } else {
            this.tamable.moveTo(
                x.toDouble() + 0.5,
                y.toDouble(),
                z.toDouble() + 0.5,
                this.tamable.yRot,
                this.tamable.xRot
            )
            this.navigation.stop()
            return true
        }
    }

    private fun canTeleportTo(pos: BlockPos): Boolean {
        val blockpathtypes = WalkNodeEvaluator.getBlockPathTypeStatic(this.level, pos.mutable())
        if (blockpathtypes != BlockPathTypes.WALKABLE) {
            return false
        } else {
            val blockstate = this.level.getBlockState(pos.below())
            if (!this.canFly && blockstate.block is LeavesBlock) {
                return false
            } else {
                val blockpos = pos.subtract(this.tamable.blockPosition())
                return this.level.noCollision(this.tamable, this.tamable.boundingBox.move(blockpos))
            }
        }
    }

    private fun randomIntInclusive(min: Int, max: Int): Int {
        return this.tamable.getRandom().nextInt(max - min + 1) + min
    }

    companion object {
        const val TELEPORT_WHEN_DISTANCE_IS: Int = 12
        private const val MIN_HORIZONTAL_DISTANCE_FROM_PLAYER_WHEN_TELEPORTING = 2
        private const val MAX_HORIZONTAL_DISTANCE_FROM_PLAYER_WHEN_TELEPORTING = 3
        private const val MAX_VERTICAL_DISTANCE_FROM_PLAYER_WHEN_TELEPORTING = 1
    }
}