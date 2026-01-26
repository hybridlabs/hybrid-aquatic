package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.boids.BoidGoal
import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
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
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState
import java.util.UUID

class PiranhaEntity(type: EntityType<out PiranhaEntity>, world: Level) : HybridAquaticSchoolingFishEntity(type, world), NeutralMob {

    override val targetConfig = MobTargetConfiguration.create(
        listOf(
            HybridAquaticEntityTags.SMALL_PREY
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        ),
    )

    private var angerTime = 0
    private var angryAt: UUID? = null

    override fun getMaxSpawnClusterSize(): Int {
        return 6
    }

    companion object {

        val ANGER_TIME_RANGE: IntProvider = TimeUtil.rangeOfSeconds(10, 30)

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.ATTACK_SPEED, 1.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        super.registerControllers(controllers)

        controllers.add(
            AnimationController(
                this, "Charge", 8,
                AnimationController.AnimationStateHandler { state: AnimationState<PiranhaEntity> ->
                    if (this.isUnderWater && this.isSprinting) {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.RUN)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )

        controllers.add(
            DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE)
        )
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, BoidGoal(this, 0.25f, 0.5f, 8 / 20f, 1 / 20f))
        goalSelector.addGoal(3, StayInWaterGoal(this))
        targetSelector.addGoal(1, HurtByTargetGoal(this).setAlertOthers())
        targetSelector.addGoal(1, ResetUniversalAngerTargetGoal(this, true))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, Player::class.java, 10, true, true) { this.isAngryAt(it) })
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { it.hasEffect(HybridAquaticMobEffects.BLEEDING.get()) && it !is PiranhaEntity })
    }

    override fun canCollideWith(entity: Entity): Boolean {
        if (entity is PiranhaEntity) {
            return false
        }
        return super.canCollideWith(entity)
    }

    override fun doPush(entity: Entity) {
        if (entity is PiranhaEntity) {
            return
        }
        super.doPush(entity)
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
