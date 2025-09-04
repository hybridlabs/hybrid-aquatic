package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.StayDeepGoal
import dev.hybridlabs.aquatic.entity.ai.goal.StayNearSurfaceGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.mob.PathfinderMob
import net.minecraft.world.World

class OpahEntity(entityType: EntityType<out OpahEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.CEPHALOPOD,
            HybridAquaticEntityTags.SMALL_PREY
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    override fun getSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, FollowTunaGoal(this, 1.5, 4.0F, 8.0F))
        if (world.isDay) {
            goalSelector.addGoal(1, StayDeepGoal(this, 1.0, 1, 12))
        } else {
            goalSelector.addGoal(1, StayNearSurfaceGoal(this, 1.0, 1, 4))
        }
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }

    internal class FollowTunaGoal(
        private val mob: PathfinderMob,
        private val speed: Double,
        private val minDistance: Float,
        private val maxDistance: Float
    ) : Goal() {

        private lateinit var target: TunaEntity

        override fun canUse(): Boolean {
            val closestTuna = mob.level()..getEntitiesByClass(
                TunaEntity::class.java,
                mob.boundingBox.expand(maxDistance.toDouble())
            ) { true }
                .minByOrNull { it.squaredDistanceTo(mob) }

            if (closestTuna != null && closestTuna.squaredDistanceTo(mob) > (minDistance * minDistance)) {
                target = closestTuna
                return true
            }
            return false
        }

        override fun shouldContinue(): Boolean {
            return target.isAlive && mob.squaredDistanceTo(target) > (minDistance * minDistance)
        }

        override fun start() {
            mob.navigation.startMovingTo(target, speed)
        }

        override fun stop() {
            mob.navigation.stop()
        }

        override fun tick() {
            target.let {
                mob.navigation.startMovingTo(it, speed)
            }
        }
    }
}