package dev.hybridlabs.aquatic.entity.ai

import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal
import net.minecraft.world.entity.ai.goal.GoalSelector
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal

data class MobTargetConfiguration(
    val prey: List<TagKey<EntityType<*>>>,
    val predators: List<TagKey<EntityType<*>>>,
) {
    fun <T : Mob> addAttackTarget(targetSelector: GoalSelector, hungerLimit: Int, mob: T, hungerProvider: (T) -> Int) {
        if (prey.isEmpty()) return

        targetSelector.addGoal(1, NearestAttackableTargetGoal(mob, LivingEntity::class.java, 10, true, true) { entity ->
            prey.any { tag -> entity.type.`is`(tag) } && hungerProvider.invoke(mob) < hungerLimit }
        )
    }

    fun addAvoidanceGoal(goalSelector: GoalSelector, mob: PathfinderMob) {
        if (predators.isEmpty()) return

        goalSelector.addGoal(2, AvoidEntityGoal(mob, LivingEntity::class.java, 16.0f, 1.5, 1.5) { entity ->
            predators.any { tag -> entity.type.`is`(tag) }
        })
    }

    companion object {
        fun create(prey: List<TagKey<EntityType<*>>>, predators: List<TagKey<EntityType<*>>>): MobTargetConfiguration {
            return MobTargetConfiguration(prey, predators)
        }

        fun ofPrey(vararg predators: TagKey<EntityType<*>>): MobTargetConfiguration {
            return MobTargetConfiguration(emptyList(), predators.toList())
        }

        fun ofPredator(vararg prey: TagKey<EntityType<*>>): MobTargetConfiguration {
            return MobTargetConfiguration(prey.toList(), emptyList())
        }
    }
}
