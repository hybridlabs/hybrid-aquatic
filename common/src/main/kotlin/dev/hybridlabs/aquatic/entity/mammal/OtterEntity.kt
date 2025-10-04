package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.entity.ai.control.FloatControl
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.Holder
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.BiomeTags
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import java.util.function.IntFunction

class OtterEntity(entityType: EntityType<out OtterEntity>, world: Level) :
    HybridAquaticMammalEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.CRUSTACEAN,
            HybridAquaticEntityTags.SMALL_PREY
        ),
        listOf(
            HybridAquaticEntityTags.NONE
        )
    ),
    VariantHolder<OtterEntity.Companion.Type> {
    private var floatingTimer: Int = 0
    private val swimControl = OtterMoveControl(this, 85, 10, 1.0F, 1.0F, true)
    private val floatControl = FloatControl(this)

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = swimControl
        lookControl = SmoothSwimmingLookControl(this, 10)
        navigation = AmphibiousPathNavigation(this, world)
    }

    override fun registerGoals() {
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.5, 2))
        goalSelector.addGoal(3, OtterSwimmingGoal(this, 0.5, 2))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        goalSelector.addGoal(4, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        goalSelector.addGoal(5, TryFindWaterGoal(this))
        goalSelector.addGoal(6, MeleeAttackGoal(this, 1.2, true))
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun aiStep() {
        super.aiStep()
        if (!level().isClientSide() && this.isEffectiveAi) {
            if (this.isInWater) {
                if (this.isFloating()) {
                    if (--this.floatingTimer <= 0) {
                        this.setFloating(false)
                        this.moveControl = swimControl
                    }
                    this.yHeadRot = 0F
                    this.yBodyRot = 0F
                } else if (random.nextFloat() <= 0.001f) {
                    this.floatingTimer = random.nextInt(500, 1000)
                    this.setFloating(true)
                    this.moveControl = floatControl
                }
            } else {
                this.setFloating(false)
            }
        }
    }

    override fun maxUpStep(): Float {
        return 1.0F
    }

    fun isFloating(): Boolean {
        return entityData.get(FLOATING)
    }

    private fun setFloating(floating: Boolean) {
        entityData.set(FLOATING, floating)
    }

    override fun isVisuallySwimming(): Boolean {
        return this.isSwimming
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        val biome = world.getBiome(this.blockPosition())
        val selectedType = Type.fromBiome(biome)
        this.variant = selectedType
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getAmbientSound(): SoundEvent? {
        return SoundEvents.FOX_AMBIENT
    }

    override fun getHurtSound(damageSource: DamageSource): SoundEvent? {
        return SoundEvents.FOX_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents.FOX_DEATH
    }

    override fun getSwimSplashSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SWIM
    }

    override fun travel(travelVector: Vec3) {
        if (this.isControlledByLocalInstance && this.isInWater) {
            this.moveRelative(this.speed, travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            this.deltaMovement = deltaMovement.scale(0.3)
        } else {
            super.travel(travelVector)
        }
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.6f
    }

    override fun getWaterline(): Float {
        return 0.125f
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "Sit/Swim/Idle",
                20
            ) { state: AnimationState<OtterEntity> ->
                if (isInWater) {
                    when {
                        isFloating() -> state.setAndContinue(FLOAT)
                        state.isMoving -> state.setAndContinue(DefaultAnimations.SWIM)
                        else -> state.setAndContinue(WATER_IDLE)
                    }
                } else {
                    when {
                        onGround() && state.isMoving -> state.setAndContinue(DefaultAnimations.WALK)
                        else -> state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            }
        )
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val FLOAT: RawAnimation = RawAnimation.begin().thenPlay("misc.float")
        val FLOATING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(OtterEntity::class.java, EntityDataSerializers.BOOLEAN)
        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(OtterEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            RIVER(0, "river"),
            SEA(1, "sea");

            override fun getSerializedName(): String {
                return this.key
            }

            companion object {
                val CODEC: StringRepresentable.EnumCodec<Type> = StringRepresentable.fromEnum { entries.toTypedArray() }
                private val BY_ID: IntFunction<Type> = ByIdMap.continuous(
                    { obj: Type -> obj.id },
                    entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.ZERO
                )

                fun byName(name: String?): Type {
                    return CODEC.byName(name, RIVER) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: Holder<Biome>): Type {
                    return when {
                        biome.`is`(BiomeTags.IS_BEACH) -> {
                            SEA
                        }

                        biome.`is`(BiomeTags.IS_OCEAN) -> {
                            SEA
                        }

                        else -> {
                            RIVER
                        }
                    }
                }
            }
        }
    }

    override fun defineSynchedData() {
        entityData.define(TYPE, 0)
        entityData.define(FLOATING, false)
        super.defineSynchedData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.serializedName)
        nbt.putBoolean("Floating", isFloating())

        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        this.variant = Type.byName(nbt.getString("Type"))
        this.setFloating(nbt.getBoolean("Floating"))
        super.readAdditionalSaveData(nbt)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }

    internal class OtterSwimmingGoal(private val otter: OtterEntity, speedModifier: Double, interval: Int) :
        RandomSwimmingGoal(otter, speedModifier, interval) {

        override fun canUse(): Boolean {
            return !otter.isFloating() && super.canUse()
        }
    }

    internal class OtterMoveControl(
        private val otter: OtterEntity,
        maxTurnX: Int,
        maxTurnY: Int,
        inWaterSpeedModifier: Float,
        outsideWaterSpeedModifier: Float,
        applyGravity: Boolean,
    ) : SmoothSwimmingMoveControl(
        otter,
        maxTurnX,
        maxTurnY,
        inWaterSpeedModifier,
        outsideWaterSpeedModifier,
        applyGravity
    ) {

        override fun tick() {
            if (!otter.isFloating()) {
                super.tick()
            }
        }
    }
}