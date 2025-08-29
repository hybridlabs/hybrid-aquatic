package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.Difficulty
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.level.Level

class PiranhaEntity(entityType: EntityType<out PiranhaEntity>, world: Level) :
    HybridAquaticSchoolingFishEntity(
        entityType, world,
        listOf(HybridAquaticEntityTags.SMALL_PREY),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }

    override fun registerGoals() {
        super.registerGoals()
        targetSelector.addGoal(1, HurtByTargetGoal(this, *arrayOfNulls(0)).setAlertOthers(*arrayOfNulls(0)))
        targetSelector.addGoal(
            1,
            NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) {
                it.hasEffect(HybridAquaticMobEffects.BLEEDING.get()) && it !is PiranhaEntity
            })
    }

    override fun doHurtTarget(target: Entity): Boolean {
        if (super.doHurtTarget(target)) {
            if (target is LivingEntity) {
                var i = 0
                if (level().difficulty == Difficulty.NORMAL) {
                    i = 7
                } else if (level().difficulty == Difficulty.HARD) {
                    i = 15
                }

                if (i > 0) {
                    target.addEffect(MobEffectInstance(HybridAquaticMobEffects.BLEEDING.get(), i * 20, 0), this)
                }
            }

            return true
        } else {
            return false
        }
    }

    override fun tick() {
        super.tick()

        if (isSprinting) {
            attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 1.5
        }
    }
}