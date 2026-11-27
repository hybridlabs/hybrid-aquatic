package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.ai.goal.SharkAttackGoal
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.boids.StayInWaterGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSharkEntity
import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class HammerheadSharkEntity(type: EntityType<out HammerheadSharkEntity>, world: Level) :
    BaseSharkEntity(type, world) {
    override fun getTargetConfig() = TARGET_CONFIG

    override val isPassive: Boolean = false
    override val closePlayerAttack: Boolean = false

    override fun getMaxSpawnClusterSize(): Int {
        return 2
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

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItems.STINGRAY.get())
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAPIEntityTags.CRAB,
                HAPIEntityTags.LOBSTER,
                HAPIEntityTags.SMALL_CREATURES,
                HAPIEntityTags.RAY,
            ),
            listOf(
                HAPIEntityTags.LARGE_SHARK
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 26.0)
        }
    }
}
