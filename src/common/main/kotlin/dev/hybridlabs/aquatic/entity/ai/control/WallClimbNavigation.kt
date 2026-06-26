package dev.hybridlabs.aquatic.entity.ai.control

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.Path
import org.jetbrains.annotations.Nullable
import kotlin.math.max

class WallClimbNavigation(mob: Mob, world: Level) :
    GroundPathNavigation(mob, world) {
    @Nullable
    private var targetPos: BlockPos? = null

    override fun createPath(target: BlockPos, distance: Int): Path? {
        this.targetPos = target
        return super.createPath(target, distance)
    }

    override fun createPath(entity: Entity, distance: Int): Path? {
        this.targetPos = entity.blockPosition()
        return super.createPath(entity, distance)
    }

    override fun moveTo(entity: Entity, speed: Double): Boolean {
        val path: Path? = this.createPath(entity, 0)
        if (path != null) {
            return this.moveTo(path, speed)
        }
        this.targetPos = entity.blockPosition()
        this.speedModifier = speed
        return true
    }

    override fun tick() {
        if (!this.isDone) {
            super.tick()
            return
        }

        if (this.targetPos != null) {
            if (!this.targetPos!!.closerToCenterThan(
                    mob.position(), max(
                        mob.bbWidth.toDouble(), 1.0
                    )
                )
                && (!(mob.y > this.targetPos!!.y.toDouble())
                        || !BlockPos(
                    this.targetPos!!.x,
                    mob.blockY, this.targetPos!!.z
                ).closerToCenterThan(
                    mob.position(), max(mob.bbWidth.toDouble(), 1.0)
                )
                        )
            ) {
                mob.moveControl.setWantedPosition(
                    this.targetPos!!.x.toDouble(),
                    this.targetPos!!.y.toDouble(),
                    this.targetPos!!.z.toDouble(),
                    0.5
                )
            } else {
                this.targetPos = null
            }
        }
    }
}