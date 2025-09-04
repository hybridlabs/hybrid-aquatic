package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.ai.goal.UniversalAngerGoal
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.effect.MobEffectInstance
import net.minecraft.entity.mob.Angerable
import net.minecraft.entity.player.Player
import net.minecraft.util.TimeHelper
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.world.Difficulty
import net.minecraft.world.World
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
    ), Angerable {

    private var angerTime = 0
    private var angryAt: UUID? = null

    override fun getSpawnClusterSize(): Int {
        return 4
    }

    companion object {

        val ANGER_TIME_RANGE: UniformIntProvider = TimeHelper.betweenSeconds(10, 30)
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
        targetSelector.addGoal(3, RevengeGoal(this).setGroupRevenge())
        targetSelector.addGoal(3, UniversalAngerGoal(this, true))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this,Player::class.java, 10, true, true) { this.shouldAngerAt(it) })
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { it.hasMobEffect(HybridAquaticMobEffects.BLEEDING) && it !is PiranhaEntity })
    }

    override fun doHurtTarget(target: Entity?): Boolean {
        if (super.doHurtTarget(target)) {
            if (target is LivingEntity) {
                var i = 0
                if (world.difficulty == Difficulty.NORMAL) {
                    i = 7
                } else if (world.difficulty == Difficulty.HARD) {
                    i = 15
                }

                if (i > 0) {
                    target.addMobEffect(MobEffectInstance(HybridAquaticMobEffects.BLEEDING, i * 20, 0), this)
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
            attributes.getCustomInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 1.5
        }
    }

    //#region Angerable Implementation Details
    override fun getAngerTime(): Int {
        return angerTime
    }

    override fun setAngerTime(angerTime: Int) {
        this.angerTime = angerTime
    }

    override fun getAngryAt(): UUID? {
        return angryAt
    }

    override fun setAngryAt(angryAt: UUID?) {
        this.angryAt = angryAt
    }

    override fun chooseRandomAngerTime() {
        setAngerTime(ANGER_TIME_RANGE.get(random))
    }
    //#endregion
}