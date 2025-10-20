package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.util.TimeUtil
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.Difficulty
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.NeutralMob
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import java.util.*

class BarracudaEntity(entityType: EntityType<out BarracudaEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.SMALL_PREY,
            HybridAquaticEntityTags.MEDIUM_PREY,
        ),
        listOf(
            HybridAquaticEntityTags.SHARK
        )
    ), NeutralMob {

    private var angerTime = 0
    private var angryAt: UUID? = null

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    companion object {

        val ANGER_TIME_RANGE: IntProvider = TimeUtil.rangeOfSeconds(10, 30)
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }
    }

    override fun registerGoals() {
        super.registerGoals()
        targetSelector.addGoal(1, HurtByTargetGoal(this).setAlertOthers())
        targetSelector.addGoal(3, ResetUniversalAngerTargetGoal(this, false))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, Player::class.java, 10, true, true) { this.isAngryAt(it) })
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { it.hasEffect(HybridAquaticMobEffects.BLEEDING.get()) && it !is BarracudaEntity })
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
            attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.8
        }
    }

    //#region Angerable Implementation Details
    override fun getRemainingPersistentAngerTime(): Int {
        return angerTime
    }

    override fun setRemainingPersistentAngerTime(angerTime: Int) {
        this.angerTime = angerTime
    }

    override fun getPersistentAngerTarget(): UUID? {
        return angryAt
    }

    override fun setPersistentAngerTarget(angryAt: UUID?) {
        this.angryAt = angryAt
    }

    override fun startPersistentAngerTimer() {
        this.remainingPersistentAngerTime = ANGER_TIME_RANGE.sample(this.random)
    }
    //#endregion
}