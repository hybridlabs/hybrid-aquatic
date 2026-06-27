package dev.hybridlabs.aquatic.entity.base

import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalSitGoal
import dev.hybridlabs.aquatic.entity.cephalopod.InkConfiguration
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.PathType
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.animation.PlayState
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "unused")
open class HAOctopusEntity(type: EntityType<out HAOctopusEntity>, world: Level) : HAWaterAnimal(type, world) {
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

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
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

    override fun registerGoals() {
        goalSelector.addGoal(2, WaterAnimalSitGoal(this))
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 1.0, 10))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        getTargetConfig()?.addAvoidanceGoal(goalSelector, this)
    }

    //#region Data
    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(ATTEMPT_ATTACK, false)
        builder.define(TARGET_COLOR, 12799593)
        builder.define(CURRENT_COLOR, 12799593)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt("targetColor", getTargetColor())
        compound.putInt("currentColor", getCurrentColor())
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        this.setTargetColor(compound.getInt("targetColor"))
        this.setCurrentColor(compound.getInt("currentColor"))
    }
    //#endregion

    override fun tick() {
        super.tick()

        this.determineTargetColor()

        if (this.isInWater) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                hurt(this.damageSources().dryOut(), 2.0f)
                this.xRot = 0.0f
                this.yRot = 0.0f
                this.yHeadRot = 0.0f
            }
        }
    }

    override fun getMaxHeadXRot(): Int {
        return 1
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }

    //#region Color
    fun getTargetColor(): Int {
        return entityData.get(TARGET_COLOR)
    }

    private fun setTargetColor(targetColor: Int) {
        entityData.set(TARGET_COLOR, targetColor)
    }

    fun getCurrentColor(): Int {
        return entityData.get(CURRENT_COLOR)
    }

    fun setCurrentColor(currentColor: Int) {
        entityData.set(CURRENT_COLOR, currentColor)
    }

    private fun determineTargetColor() {
        val currentPos = this.blockPosition()
        val floorPos = this.onPos
        val sharedBlock = level().getBlockState(currentPos)
        val floor = level().getBlockState(floorPos)

        if (!sharedBlock.isAir && !sharedBlock.fluidState.`is`(FluidTags.WATER)) {
            val sharedColor = sharedBlock.getMapColor(this.level(), currentPos).col
            if (this.getTargetColor() != sharedColor && sharedColor != 0) {
                this.setTargetColor(sharedColor)
            }
        } else if (!floor.fluidState.`is`(FluidTags.WATER)) {
            val floorColor = floor.getMapColor(this.level(), floorPos).col
            if (this.getTargetColor() != floorColor && floorColor != 0) {
                this.setTargetColor(floorColor)
            }
        }
    }
    //#endregion

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

    private fun squirt(config: InkConfiguration) {
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
                config.particle,
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
    private var attemptAttack: Boolean
        get() = entityData.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            entityData.set(ATTEMPT_ATTACK, attemptAttack)
        }

    override fun getMinSize(): Int {
        return -3
    }

    override fun getMaxSize(): Int {
        return 3
    }

    override fun isVisuallySwimming(): Boolean {
        return this.isSwimming
    }
    //#endregion

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Run/Swim/Idle", 4) {
                state: AnimationState<HAOctopusEntity> ->
                if (this.isInWaterOrBubble && state.isMoving) state.setAndContinue(
                    if (this.isSprinting) DefaultAnimations.RUN else DefaultAnimations.SWIM)
                else state.setAndContinue(DefaultAnimations.IDLE
                )
            }
        )

        controllers.add(
            AnimationController(
                this, "Sit",
                AnimationStateHandler { state: AnimationState<HAOctopusEntity> ->
                    if (this.isSitting())
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.SIT)
                    PlayState.STOP
                }
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }
    //#endregion

    companion object {
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HAOctopusEntity::class.java, EntityDataSerializers.BOOLEAN)
        private val CURRENT_COLOR: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HAOctopusEntity::class.java, EntityDataSerializers.INT)
        private val TARGET_COLOR: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HAOctopusEntity::class.java, EntityDataSerializers.INT)

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canSpawn(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            return pos.y >= seaLevel - 64 &&
                    world.getBlockState(pos.below()).isSolid
        }
    }
}
