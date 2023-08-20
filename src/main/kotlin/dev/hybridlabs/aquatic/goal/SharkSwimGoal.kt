package dev.hybridlabs.aquatic.goal

import net.minecraft.entity.ai.brain.task.LookTargetUtil
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.ai.pathing.Path
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.util.math.Vec3d
import java.util.EnumSet

open class SharkSwimGoal (
    protected val mob: PathAwareEntity,
    protected val speed: Double,
    private val canDespawn: Boolean
) : Goal() {
    protected var targetX = 0.0
    protected var targetY = 0.0
    protected var targetZ = 0.0

    var currentTarget: Vec3d? = null

    init {
        controls = EnumSet.of(Control.MOVE)
    }

    override fun canStart(): Boolean {
        return true
    }

    override fun shouldRunEveryTick(): Boolean {
        return true
    }

    override fun canStop(): Boolean {
        return false
    }

    protected open val wanderTarget: Vec3d?
        get() = LookTargetUtil.find(this.mob, 10, 10)

    override fun shouldContinue(): Boolean {
        return !mob.navigation.isFollowingPath && !mob.hasPassengers()
    }

    override fun start() {

    }

    override fun stop() {

    }

    override fun tick() {
//        println(mob.navigation.currentPath?.isFinished == true)
        if(currentTarget == null) {


            var foundPath = false
            var path: Path? = null
            var retries = 0
            while(!foundPath && retries < 10) {
                currentTarget = wanderTarget
                currentTarget?.let { targetPos ->
                    path = mob.navigation.findPathTo(targetPos.x, targetPos.y, targetPos.z, 3)
                    path?.let { path ->
                        foundPath = true
                        println(path.length)
                        println(path.isFinished)
                    }
                    retries++
                }
            }
            println(mob.navigation.startMovingAlong(path, 1.0))
            path?.let { finalPath ->
                println(finalPath.target)
                // System.out.printf("Current Pos: %s%nCurrent Target: %s%nPathing Result: %s%n", mob.pos, currentTarget, result)
            }
        }
    }

}
