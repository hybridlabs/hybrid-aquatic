package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.boids.BoidGoal
import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticSirenianEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.level.Level

class TrevallyEntity(type: EntityType<out TrevallyEntity>, world: Level) :
    HybridAquaticSchoolingFishEntity(type, world) {

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, FollowSharkGoal(this, 1.5, 4.0F, 8.0F))
        goalSelector.addGoal(1, FollowSirenianGoal(this, 1.5, 4.0F, 8.0F))
        goalSelector.addGoal(5, BoidGoal(this, 0.25f, 0.5f, 8 / 20f, 1 / 20f))
        goalSelector.addGoal(3, StayInWaterGoal(this))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }

    internal class FollowSharkGoal(
        private val mob: PathfinderMob,
        private val speed: Double,
        private val minDistance: Float,
        private val maxDistance: Float
    ) : Goal() {

        private lateinit var target: HybridAquaticSharkEntity

        override fun canUse(): Boolean {
            val closestShark = mob.level().getEntitiesOfClass(
                HybridAquaticSharkEntity::class.java,
                mob.boundingBox.inflate(maxDistance.toDouble())
            ) { true }
                .minByOrNull { it.distanceToSqr(mob) }

            if (closestShark != null && closestShark.distanceToSqr(mob) > (minDistance * minDistance)) {
                target = closestShark
                return true
            }
            return false
        }

        override fun canContinueToUse(): Boolean {
            return target.isAlive && mob.distanceTo(target) > (minDistance * minDistance)
        }

        override fun start() {
            mob.navigation.moveTo(target, speed)
        }

        override fun stop() {
            mob.navigation.stop()
        }

        override fun tick() {
            target.let {
                mob.navigation.moveTo(it, speed)
            }
        }
    }

    internal class FollowSirenianGoal(
        private val mob: PathfinderMob,
        private val speed: Double,
        private val minDistance: Float,
        private val maxDistance: Float
    ) : Goal() {

        private lateinit var target: HybridAquaticSirenianEntity

        override fun canUse(): Boolean {
            val closestSirenian = mob.level().getEntitiesOfClass(
                HybridAquaticSirenianEntity::class.java,
                mob.boundingBox.inflate(maxDistance.toDouble())
            ) { true }
                .minByOrNull { it.distanceToSqr(mob) }

            if (closestSirenian != null && closestSirenian.distanceToSqr(mob) > (minDistance * minDistance)) {
                target = closestSirenian
                return true
            }
            return false
        }

        override fun canContinueToUse(): Boolean {
            return target.isAlive && mob.distanceTo(target) > (minDistance * minDistance)
        }

        override fun start() {
            mob.navigation.moveTo(target, speed)
        }

        override fun stop() {
            mob.navigation.stop()
        }

        override fun tick() {
            target.let {
                mob.navigation.moveTo(it, speed)
            }
        }
    }
}
