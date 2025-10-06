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
import net.minecraft.util.Mth
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.ai.util.DefaultRandomPos
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation
import java.util.*
import java.util.function.IntFunction
import kotlin.math.max

@Suppress("DEPRECATION")
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
    private val swimControl = SmoothSwimmingMoveControl(this, 85, 10, 1.0F, 0.5F, true)
    private val floatControl = FloatControl(this)
    private var isFloating = false

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = swimControl
        navigation = AmphibiousPathNavigation(this, world)
    }

    override fun registerGoals() {
        goalSelector.addGoal(1, OtterDiveGoal(this, 1.0))
        goalSelector.addGoal(2, OtterSwimmingGoal(this, 0.7, 60))
        goalSelector.addGoal(2, OtterFloatGoal(this))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.6, 60))
        goalSelector.addGoal(3, TryFindWaterGoal(this))
        goalSelector.addGoal(4, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        goalSelector.addGoal(6, MeleeAttackGoal(this, 1.2, true))
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun travel(travelVector: Vec3) {
        if (this.isEffectiveAi && this.isInWater && !this.isFloating) {
            this.moveRelative((this.speed / 3 - 0.02F), travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            val slowdown: Double = Blocks.WATER.friction.toDouble()
            this.deltaMovement = deltaMovement.multiply(slowdown, 0.8, slowdown)
        }
        super.travel(travelVector)
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

    //#region SFX
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

    //#endregion

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.6f
    }

    override fun getWaterline(): Float {
        return 0.125f
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "otter_controller", 5) { state ->
                when {
                    isInWater && isFloating() -> {
                        state.setAndContinue(FLOAT_ANIMATION)
                    }

                    isInWater && state.isMoving -> {
                        state.setAndContinue(DefaultAnimations.SWIM)
                    }

                    isInWater && !state.isMoving -> {
                        state.setAndContinue(WATER_IDLE)
                    }

                    !isInWater && state.isMoving -> {
                        state.setAndContinue(DefaultAnimations.WALK)
                    }

                    !isInWater && !state.isMoving -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }

                    else -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            }
        )


    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val FLOAT_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.float_idle")
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

    internal class OtterSwimmingGoal(private val otter: OtterEntity, speedModifier: Double, interval: Int) : RandomStrollGoal(otter, speedModifier, interval) {
        init {
            this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        }

        override fun start() {
            otter.isFloating = false
            otter.moveControl = otter.swimControl
            super.start()
        }

        override fun requiresUpdateEveryTick(): Boolean {
            return true
        }

        override fun tick() {
            var deltaMovement: Vec3 = this.mob.deltaMovement
            if ((this.mob as HybridAquaticMammalEntity).isBelowWaterline()) {
                this.mob.deltaMovement = deltaMovement.add(0.0, 0.01, 0.0)
                if (this.mob.isUnderWater)
                    deltaMovement = this.mob.deltaMovement
                this.mob.deltaMovement = deltaMovement.add(0.0, 0.01, 0.0)
                deltaMovement = this.mob.deltaMovement
                this.mob.setDeltaMovement(deltaMovement.x, max(deltaMovement.y, 0.0), deltaMovement.z)
            }
            super.tick()
        }

        override fun getPosition(): Vec3? {
            val pos = DefaultRandomPos.getPos(otter, 16, 0)
            if (pos != null) {
                val height = otter.level().getHeight(Heightmap.Types.WORLD_SURFACE, pos.x.toInt(), pos.z.toInt())
                return Vec3(pos.x, height.toDouble(), pos.z)
            }
            return null
        }

        override fun canUse(): Boolean {
            return otter.isInWater && !otter.isFloating() && super.canUse()
        }
    }

    internal class OtterDiveGoal(private val otter: OtterEntity, private val speedModifier: Double) : Goal() {

        private var target: Vec3 = Vec3.ZERO
        private val minDiveHeight = 4
        private val minDiveDelay = 200
        private var nextDiveTime = 0L

        init {
            this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        }

        private fun waterDepth(): Int {
            val depth = Mth.abs(
                otter.y.toInt() - otter.level().getHeight(Heightmap.Types.OCEAN_FLOOR, otter.x.toInt(), otter.z.toInt())
            )
            return depth
        }

        override fun canUse(): Boolean {
            return otter.isInWater && !otter.isUnderWater && otter.level().gameTime > nextDiveTime && waterDepth() >= minDiveHeight
        }

        override fun canContinueToUse(): Boolean {
            return target != Vec3.ZERO && otter.navigation.isInProgress && !otter.isVehicle
        }

        override fun start() {
            otter.moveControl = otter.swimControl
            otter.isFloating = false
            val pos = DefaultRandomPos.getPos(otter, 16, 0)
            if (pos != null) {
                val height = otter.level().getHeight(Heightmap.Types.OCEAN_FLOOR, pos.x.toInt(), pos.z.toInt())
                target = Vec3(pos.x, height.toDouble() + 1, pos.z)
                otter.navigation.moveTo(target.x(), target.y(), target.z(), speedModifier)
            }
        }

        override fun stop() {
            target = Vec3.ZERO
            nextDiveTime = otter.level().gameTime + minDiveDelay
            otter.navigation.stop()

        }
    }

    internal class OtterFloatGoal(private val otter: OtterEntity) : Goal() {
        private var floatingTimer: Long = 0
        private var nextFloatTime: Long = 0
        private val minFloatDelay = 300

        init {
            this.flags = EnumSet.of(Flag.MOVE)
        }

        override fun canUse(): Boolean {
            return otter.isInWater && !otter.isUnderWater && otter.level().gameTime > nextFloatTime && otter.random.nextFloat() <= 0.1
        }

        override fun canContinueToUse(): Boolean {
            return otter.isInWater && otter.level().gameTime < floatingTimer
        }

        override fun start() {
            otter.setFloating(true)
            otter.moveControl = otter.floatControl
            this.floatingTimer = otter.random.nextInt(100, 300) + otter.level().gameTime
            this.otter.navigation.stop()
            otter.deltaMovement = Vec3.ZERO
        }

        override fun stop() {
            floatingTimer = 0
            nextFloatTime = otter.level().gameTime + minFloatDelay
            otter.setFloating(false)
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
            if (otter.isFloating()) return
            super.tick()
        }
    }
}