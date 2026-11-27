package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.ai.goal.SharkAttackGoal
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.boids.StayInWaterGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSharkEntity
import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.FollowBoatGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level

class SixgillSharkEntity(type: EntityType<out SixgillSharkEntity>, world: Level) :
    BaseSharkEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPredator(
        HAPIEntityTags.MEDIUM_CREATURES,
        HAPIEntityTags.SMALL_SHARK,
        HAPIEntityTags.SQUID,
    )

    override val isPassive: Boolean = false
    override val closePlayerAttack: Boolean = false

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, SharkAttackGoal(this, 1.1, true))
        goalSelector.addGoal(0, StayInWaterGoal(this))
        goalSelector.addGoal(1, TemptGoal(this, 1.1, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(2, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(1, HurtByTargetGoal(this))
        targetSelector.addGoal(
            1,
            NearestAttackableTargetGoal(this, Player::class.java, 10, true, true) {
                    entity: LivingEntity -> isAngryAt(entity) && !isPassive
            }
        )
        getTargetConfig().addAttackTarget(targetSelector, MAX_HUNGER / 4, this, BaseWaterAnimal::hunger)
        getTargetConfig().addAvoidanceGoal(goalSelector, this)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }
    }
}
