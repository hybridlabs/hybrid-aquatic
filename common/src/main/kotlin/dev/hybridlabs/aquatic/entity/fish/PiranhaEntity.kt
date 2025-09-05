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
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import java.util.*

class PiranhaEntity(entityType: EntityType<out PiranhaEntity>, world: Level) :
    HybridAquaticSchoolingFishEntity(
        entityType, world,
        listOf(HybridAquaticEntityTags.SMALL_PREY),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ), NeutralMob {

    private var angerTime = 0
    private var angryAt: UUID? = null

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    companion object {

        val ANGER_TIME_RANGE: IntProvider = TimeUtil.rangeOfSeconds(10, 30)

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.ATTACK_SPEED, 1.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE)
        )
        super.registerControllers(controllerRegistrar)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, MeleeAttackGoal(this, 1.5, false))
        targetSelector.addGoal(3, HurtByTargetGoal(this).setAlertOthers())
        targetSelector.addGoal(3, ResetUniversalAngerTargetGoal(this, true))
        targetSelector.addGoal(
            1,
            NearestAttackableTargetGoal(this, Player::class.java, 10, true, true) { this.isAngryAt(it) })
        targetSelector.addGoal(
            2,
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

    //#region Angerable Implementation Details
    override fun getRemainingPersistentAngerTime(): Int {
        return angerTime
    }

    override fun setRemainingPersistentAngerTime(p0: Int) {
        this.angerTime = angerTime
    }

    override fun getPersistentAngerTarget(): UUID? {
        return angryAt
    }

    override fun setPersistentAngerTarget(p0: UUID?) {
        this.angryAt = angryAt
    }

    override fun startPersistentAngerTimer() {
        this.remainingPersistentAngerTime = ANGER_TIME_RANGE.sample(this.random)
    }
    //#endregion
}