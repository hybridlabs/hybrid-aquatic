package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticMammalEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.MoverType
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.monster.Monster.isDarkEnoughToSpawn
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER")
open class HybridAquaticCephalopodEntity(
    type: EntityType<out HybridAquaticCephalopodEntity>,
    world: Level,
) : WaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    open val prey: TagKey<EntityType<*>>? = null
    open val predator: List<TagKey<EntityType<*>>> = emptyList()

    open val inkConfig: InkConfiguration? = null

    override fun registerGoals() {
        goalSelector.addGoal(1, RandomSwimmingGoal(this, 1.0, 10))
        goalSelector.addGoal(2, CephalopodAttackGoal(this))
        goalSelector.addGoal(3, AvoidEntityGoal(this, LivingEntity::class.java, 8.0f, 1.0, 1.0) { entity: LivingEntity -> predator.any { predatorTag -> entity.type.`is`(predatorTag) } })
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { hunger <= 1200 && prey != null && it.type.`is`(prey) })
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(MOISTNESS, getMaxMoistness())
        entityData.define(CEPHALOPOD_SIZE, 0)
        entityData.define(ATTEMPT_ATTACK, false)
        entityData.define(HUNGER, MAX_HUNGER)
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        this.xRot = 0.0f
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
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

        if (hunger > 0) hunger -= 1
    }

    override fun handleAirSupply(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 600
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

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putInt(CEPHALOPOD_SIZE_KEY, size)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        size = nbt.getInt(CEPHALOPOD_SIZE_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, false)
        lookControl = SmoothSwimmingLookControl(this, 10)
        navigation = WaterBoundPathNavigation(this, world)
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

    override fun dropFromLootTable(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.directEntity
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticCephalopodEntity && attacker !is HybridAquaticMammalEntity) {
            super.dropFromLootTable(source, causedByPlayer)
        }
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

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

    override fun createNavigation(world: Level): PathNavigation{
        return WaterBoundPathNavigation(this, world)
    }

    //region properties

    private var moistness: Int
        get() = entityData.get(MOISTNESS)
        set(moistness) {
            entityData.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = entityData.get(CEPHALOPOD_SIZE)
        set(size) {
            entityData.set(CEPHALOPOD_SIZE, size)
        }

    var hunger: Int
        get() = entityData.get(HUNGER)
        set(hunger) {
            entityData.set(HUNGER, hunger)
        }

    private var attemptAttack: Boolean
        get() = entityData.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            entityData.set(ATTEMPT_ATTACK, attemptAttack)
        }

    // endregion

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Swim/Idle", 10
            ) { state: AnimationState<*> ->
                state.setAndContinue(
                    if (state.isMoving) DefaultAnimations.SWIM else DefaultAnimations.IDLE
                )
            }
        )
        controllers.add(AnimationController(this, "Sit", 10) { state ->
            if (onGround()) {
                state.setAndContinue(DefaultAnimations.SIT)
                PlayState.CONTINUE
            } else {
                PlayState.STOP
            }
        })
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 3
    }

    private var fromFishingNet = false

    internal class CephalopodAttackGoal(private val cephalopod: HybridAquaticCephalopodEntity) :
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
                    cephalopod.hunger = HybridAquaticSharkEntity.MAX_HUNGER
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
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticCephalopodEntity::class.java, EntityDataSerializers.INT)
        val CEPHALOPOD_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticCephalopodEntity::class.java, EntityDataSerializers.INT)
        val HUNGER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticCephalopodEntity::class.java, EntityDataSerializers.INT)
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticCephalopodEntity::class.java, EntityDataSerializers.BOOLEAN)

        const val MAX_HUNGER = 2400
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"
        const val CEPHALOPOD_SIZE_KEY = "CephalopodSize"

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val topY = world.seaLevel - 4
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos) &&
                    !isDarkEnoughToSpawn(world, pos, random)
        }

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canDeepSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val topY = world.seaLevel - 48
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    isDarkEnoughToSpawn(world, pos, random)
        }

        fun getScaleAdjustment(cephalopod: HybridAquaticCephalopodEntity, adjustment: Float): Float {
            return 1.0f + (cephalopod.size * adjustment)
        }
    }
}
