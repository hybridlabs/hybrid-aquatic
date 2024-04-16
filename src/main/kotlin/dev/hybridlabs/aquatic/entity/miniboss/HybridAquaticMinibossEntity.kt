package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis")
open class HybridAquaticMinibossEntity(type: EntityType<out HybridAquaticMinibossEntity>, world: World) : WaterCreatureEntity(type, world),
    GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var attemptAttack: Boolean
        get() = dataTracker.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            dataTracker.set(ATTEMPT_ATTACK, attemptAttack)
        }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(ATTEMPT_ATTACK, false)
    }

    override fun tick() {
        super.tick()
        if (isAiDisabled) {
            return
        }

        if (isWet) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                damage(this.damageSources.dryOut(), 1.0f)
            }
        }
    }

    override fun tickWaterBreathingAir(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 600
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    open fun shouldFlopOnLand(): Boolean {
        return false
    }

    private var fromFishingNet = false
    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    private var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "controller",
                0,
                ::predicate
            )
        )
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (event.isMoving) {
            event.controller.setAnimation(WALK_ANIMATION)
        } else {
            event.controller.setAnimation(IDLE_ANIMATION)
        }
        return PlayState.CONTINUE
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    internal class AttackGoal(private val miniboss: HybridAquaticMinibossEntity) : MeleeAttackGoal(miniboss, 1.0, true) {
        override fun canStart(): Boolean {
            return !miniboss.fromFishingNet && super.canStart()
        }

        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.tryAttack(target)
                miniboss.isSprinting = false
                miniboss.attemptAttack = true
            }
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (1.0f + entity.width).toDouble()
        }

        override fun start() {
            super.start()
            miniboss.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            miniboss.attemptAttack = false
        }
    }
    companion object {

        val WALK_ANIMATION: RawAnimation  = RawAnimation.begin().then("walk", Animation.LoopType.LOOP)
        val IDLE_ANIMATION: RawAnimation  = RawAnimation.begin().then("idle", Animation.LoopType.LOOP)

        val MOISTNESS: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticMinibossEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticMinibossEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

        const val MOISTNESS_KEY = "Moistness"
    }
}

