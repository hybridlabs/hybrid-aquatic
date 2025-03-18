package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.world.World

class OpahEntity(entityType: EntityType<out OpahEntity>, world: World) :
    HybridAquaticFishEntity(
        entityType, world, emptyMap(),
        listOf(
            HybridAquaticEntityTags.CEPHALOPOD,
            HybridAquaticEntityTags.SMALL_PREY
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        goalSelector.add(2, FollowTunaGoal(this, 1.5, 4.0F, 8.0F))
        super.initGoals()
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0)
        }
    }

    internal class FollowTunaGoal(
        private val mob: PathAwareEntity,
        private val speed: Double,
        private val minDistance: Float,
        private val maxDistance: Float
    ) : Goal() {

        private lateinit var target: TunaEntity

        override fun canStart(): Boolean {
            val closestTuna = mob.world.getEntitiesByClass(
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