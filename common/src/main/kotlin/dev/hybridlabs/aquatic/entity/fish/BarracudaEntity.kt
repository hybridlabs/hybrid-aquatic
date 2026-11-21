package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.world.entity.EntityReference

import net.minecraft.server.level.ServerLevel

import dev.hybridlabs.aquatic.effect.HAMobEffects
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import dev.hybridlabs.aquatic.tag.HAItemTags
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalEatItemGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
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
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import java.util.*

class BarracudaEntity(type: EntityType<out BarracudaEntity>, world: Level) :
    BaseFishEntity(type, world),
    NeutralMob {

    private var angerEndTime: Long = -1L
    private var angryAt: EntityReference<LivingEntity>? = null

    override fun getTargetConfig() = TARGET_CONFIG

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItemTags.SMALL_FISH) ||
                stack.`is`(HAItemTags.MEDIUM_FISH)
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAPIEntityTags.SMALL_CREATURES,
                HAPIEntityTags.MEDIUM_CREATURES,
            ),
            listOf(
                HAPIEntityTags.ALL_SHARKS
            ),
        )

        val ANGER_TIME_RANGE: IntProvider = TimeUtil.rangeOfSeconds(10, 30)
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
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
        goalSelector.addGoal(2, WaterAnimalEatItemGoal(this))
        targetSelector.addGoal(3, ResetUniversalAngerTargetGoal(this, false))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, Player::class.java, 10, true, true) { target, level -> this.isAngryAt(target, level) })
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { target, _ -> target.hasEffect(HAMobEffects.BLEEDING.asHolder()) && target !is BarracudaEntity })
    }

    override fun doHurtTarget(level: ServerLevel, target: Entity): Boolean {
        if (super.doHurtTarget(level, target)) {
            if (target is LivingEntity) {
                var i = 0
                if (level().difficulty == Difficulty.NORMAL) {
                    i = 7
                } else if (level().difficulty == Difficulty.HARD) {
                    i = 15
                }

                if (i > 0) {
                    target.addEffect(MobEffectInstance(HAMobEffects.BLEEDING.asHolder(), i * 20, 0), this)
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
    override fun getPersistentAngerEndTime(): Long = this.angerEndTime

    override fun setPersistentAngerEndTime(time: Long) {
        this.angerEndTime = time
    }

    override fun getPersistentAngerTarget(): EntityReference<LivingEntity>? = this.angryAt

    override fun setPersistentAngerTarget(target: EntityReference<LivingEntity>?) {
        this.angryAt = target
    }

    override fun startPersistentAngerTimer() {
        this.setTimeToRemainAngry(ANGER_TIME_RANGE.sample(this.random).toLong())
    }

}