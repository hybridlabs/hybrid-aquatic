package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalSitGoal
import dev.hybridlabs.aquatic.entity.base.HAFishEntity
import dev.hybridlabs.aquatic.tag.HAEntityTags
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class StonefishEntity(type: EntityType<out StonefishEntity>, world: Level) :
    HAFishEntity(type, world) {

    override fun getTargetConfig() = TARGET_CONFIG

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        goalSelector.addGoal(2, WaterAnimalSitGoal(this))
        super.registerGoals()
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAEntityTags.SMALL_CREATURES
            ),
            listOf(
                HAEntityTags.MEDIUM_CREATURES,
                HAEntityTags.LARGE_CREATURES,
                HAEntityTags.ALL_SHARKS
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (super.hurt(source, amount)) {

            val attacker = source.directEntity
            if (attacker is LivingEntity && attacker.mainHandItem.isEmpty) {
                attacker.addEffect(MobEffectInstance(MobEffects.POISON, 200, 2))
            }

            return true
        }

        return false
    }
}
