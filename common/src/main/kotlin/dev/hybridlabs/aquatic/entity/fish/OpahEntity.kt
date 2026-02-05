package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.level.Level

class OpahEntity(type: EntityType<out OpahEntity>, world: Level) : HybridAquaticFishEntity(type, world) {
    override fun getTargetConfig() = TARGET_CONFIG

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, FollowTunaGoal(this, 1.5, 4.0F, 8.0F))
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HybridAquaticEntityTags.SMALL_PREY,
                HybridAquaticEntityTags.ALL_CEPHALOPODS
            ),
            listOf(
                HybridAquaticEntityTags.MEDIUM_PREY,
                HybridAquaticEntityTags.ALL_SHARKS
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
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
            val closestTuna = mob.level().getEntitiesOfClass(
                TunaEntity::class.java,
                mob.boundingBox.inflate(maxDistance.toDouble())
            ) { true }
                .minByOrNull { it.distanceToSqr(mob) }

            if (closestTuna != null && closestTuna.distanceToSqr(mob) > (minDistance * minDistance)) {
                target = closestTuna
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
