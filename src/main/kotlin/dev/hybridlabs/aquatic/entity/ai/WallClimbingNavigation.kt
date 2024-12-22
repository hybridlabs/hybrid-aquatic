package dev.hybridlabs.aquatic.entity.ai

import net.minecraft.entity.Entity
import net.minecraft.entity.ai.pathing.MobNavigation
import net.minecraft.entity.ai.pathing.Path
import net.minecraft.entity.mob.MobEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import kotlin.math.max


class WallClimbingNavigation(mobEntity: MobEntity?, world: World?) : MobNavigation(mobEntity, world) {
    private var targetPos: BlockPos? = null

    override fun findPathTo(target: BlockPos, distance: Int): Path? {
        this.targetPos = target
        return super.findPathTo(target, distance)
    }

    override fun findPathTo(entity: Entity, distance: Int): Path? {
        this.targetPos = entity.blockPos
        return super.findPathTo(entity, distance)
    }

    override fun startMovingTo(entity: Entity, speed: Double): Boolean {
        val path: Path? = this.findPathTo(entity, 0)
        if (path != null) {
            return this.startMovingAlong(path, speed)
        }
        this.targetPos = entity.blockPos
        this.speed = speed
        return true
    }

    override fun tick() {
        if (!this.isIdle) {
            super.tick()
            return
        }

        if (this.targetPos != null) {
            if (!targetPos!!.isWithinDistance(
                    entity.pos, max(
                        entity.width.toDouble(), 1.0
                    )
                )
                && (!(entity.y > targetPos!!.y.toDouble())
                        || !BlockPos(
                    targetPos!!.x,
                    entity.blockY, targetPos!!.z
                ).isWithinDistance(
                    entity.pos, max(entity.width.toDouble(), 1.0)
                )
                        )
            ) {
                entity.moveControl.moveTo(
                    targetPos!!.x.toDouble(),
                    targetPos!!.y.toDouble(),
                    targetPos!!.z.toDouble(),
                    0.5
                )
            } else {
                this.targetPos = null
            }
        }
    }


}