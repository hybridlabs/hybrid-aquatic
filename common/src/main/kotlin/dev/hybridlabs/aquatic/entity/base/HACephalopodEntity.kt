package dev.hybridlabs.aquatic.entity.base

import dev.hybridlabs.aquatic.entity.cephalopod.InkConfiguration
import dev.hybridlabs.aquatic.world.WorldHelper
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.monster.Monster.isDarkEnoughToSpawn
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER")
open class HACephalopodEntity(type: EntityType<out HACephalopodEntity>, world: Level) :
    HAWaterAnimal(type, world) {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    open val inkConfig: InkConfiguration? = null

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(PathType.WATER, 0.0f)
        setPathfindingMalus(PathType.DANGER_FIRE, 16.0f)
        setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0f)

        moveControl = SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, false)
        lookControl = SmoothSwimmingLookControl(this, 10)

        return WaterBoundPathNavigation(this, level)
    }

    override fun registerGoals() {
        goalSelector.addGoal(1, RandomSwimmingGoal(this, 1.0, 10))
        goalSelector.addGoal(2, CephalopodAttackGoal(this))

        getTargetConfig()?.let { config ->
            config.addAttackTarget(targetSelector, 1200, this, HAWaterAnimal::hunger)
            config.addAvoidanceGoal(goalSelector, this)
        }
    }

    //#region Data
    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(ATTEMPT_ATTACK, false)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
    }
    //#endregion

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        this.xRot = 0.0f
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun tick() {
        super.tick()

        if (isInWaterRainOrBubble) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                hurt(this.damageSources().dryOut(), 1.0f)
            }

            if (!this.isUnderWater) {
                this.xRot = 0.0f
                this.yRot = this.yRotO
                this.yHeadRot = this.yHeadRotO
            }
        }

        isSprinting = isAggressive
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (super.hurt(source, amount) && this.lastHurtByMob != null) {
            if (!level().isClientSide) {
                if (this.isUnderWater) {
                    inkConfig?.run(::squirt)
                }

                val attackerPos = this.lastHurtByMob?.position()
                if (attackerPos != null) {
                    val directionAway = this.position().subtract(attackerPos).normalize().scale(10.0)
                    val targetPos = this.position().add(directionAway.x, 0.0, directionAway.z)

                    this.navigation.moveTo(targetPos.x, targetPos.y, targetPos.z, 1.5)
                }
            }
            return true
        }
        return false
    }

    private fun squirt(inkConfig: InkConfiguration) {
        this.playSound(this.getSquirtSound(), this.soundVolume, this.voicePitch)

        val entityPosition = Vec3(this.x, this.y, this.z)
        val radius = 3.0

        val affectedEntities = level().getEntitiesOfClass(
            LivingEntity::class.java,
            AABB(
                entityPosition.x - radius, entityPosition.y - radius, entityPosition.z - radius,
                entityPosition.x + radius, entityPosition.y + radius, entityPosition.z + radius
            )
        ) { it != this && it.isAlive }

        for (entity in affectedEntities) {
            entity.addEffect(MobEffectInstance(MobEffects.BLINDNESS, 100, 0))
            entity.addEffect(MobEffectInstance(MobEffects.DARKNESS, 100, 0))
        }

        for (i in 0..199) {
            val offsetX = (random.nextDouble() - 0.5) * 2.0
            val offsetY = (random.nextDouble() - 0.5) * 2.0
            val offsetZ = (random.nextDouble() - 0.5) * 2.0

            val randomMultiplier = 0.5 + random.nextDouble() * 1.5
            val velocity = Vec3(offsetX, offsetY, offsetZ).normalize().scale(randomMultiplier)

            (level() as ServerLevel).sendParticles(
                inkConfig.particle,
                entityPosition.x,
                entityPosition.y,
                entityPosition.z,
                1,
                velocity.x * 0.25,
                velocity.y * 0.25,
                velocity.z * 0.25,
                0.1
            )
        }
    }

    override fun travel(travelVector: Vec3) {
        if (this.isEffectiveAi && this.isInWater) {
            this.moveRelative(this.speed, travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            this.deltaMovement = deltaMovement.scale(0.9)
        } else {
            super.travel(travelVector)
        }
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    //#region SFX
    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.SQUID_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.SQUID_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.SQUID_DEATH
    }

    private fun getSquirtSound(): SoundEvent {
        return SoundEvents.SQUID_SQUIRT
    }
    //#endregion

    //#region Properties
    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    private var attemptAttack: Boolean
        get() = entityData.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            entityData.set(ATTEMPT_ATTACK, attemptAttack)
        }
    //#endregion

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Cephalopod Animation Controller", 4) { state ->
                when {
                    isInWater && state.isMoving -> {
                        state.setAndContinue(DefaultAnimations.SWIM)
                    }

                    this.moistness < 590 -> {
                        state.setAndContinue(FLOP_ANIMATION)
                    }

                    else -> {
                        state.setAndContinue(DefaultAnimations.SWIM)
                    }
                }
            }
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }
    //#endregion

    internal class CephalopodAttackGoal(private val cephalopod: HACephalopodEntity) :
        MeleeAttackGoal(cephalopod, 1.0, true) {
        override fun canUse(): Boolean {
            return !cephalopod.fromFishingNet && super.canUse()
        }

        override fun checkAndPerformAttack(target: LivingEntity, squaredDistance: Double) {
            val d = getAttackReachSqr(target)
            if (squaredDistance <= d && this.isTimeToAttack) {
                resetAttackCooldown()
                mob.doHurtTarget(target)
                cephalopod.isSprinting = true
                cephalopod.attemptAttack = true

                if (target.health <= 0)
                    cephalopod.hunger = MAX_HUNGER

                cephalopod.health = cephalopod.maxHealth
            }
        }

        override fun getAttackReachSqr(entity: LivingEntity): Double {
            return (1.25f + entity.bbWidth).toDouble()
        }

        override fun start() {
            super.start()
            cephalopod.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            cephalopod.attemptAttack = false
        }
    }

    companion object {
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HACephalopodEntity::class.java, EntityDataSerializers.BOOLEAN)

        val FLOP_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.flop")

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canSpawn(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val topY = seaLevel - 4
            val bottomY = seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos) &&
                    !isDarkEnoughToSpawn(world, pos, random)
        }

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canDeepSpawn(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val topY = seaLevel - 48
            val bottomY = seaLevel - 256

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    isDarkEnoughToSpawn(world, pos, random)
        }
    }
}