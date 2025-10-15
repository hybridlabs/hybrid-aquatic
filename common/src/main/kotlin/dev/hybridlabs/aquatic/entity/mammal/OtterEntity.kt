package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.BlockPos
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
import net.minecraft.world.InteractionHand
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.ai.util.DefaultRandomPos
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.pathfinder.PathType
import net.minecraft.world.phys.Vec2
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.constant.DefaultAnimations
import java.util.*
import java.util.function.IntFunction

@Suppress("DEPRECATION")
class OtterEntity(entityType: EntityType<out OtterEntity>, world: Level) :
    HybridAquaticMammalEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.KELP_PREY
        ),
        listOf(
            HybridAquaticEntityTags.SHARK
        )
    ),
    VariantHolder<OtterEntity.Companion.Type> {
    private val swimControl = OtterMoveControl(this, 45, 3, 0.02F, 1.0F, true)

    var hunger: Int
        get() = entityData.get(HUNGER)
        set(hunger) {
            entityData.set(HUNGER, hunger)
        }

    override fun tick() {
        super.tick()

        if (hunger > 0) hunger -= 1
    }

    init {
        moveControl = swimControl
        lookControl = OtterLookControl(this)
        navigation = AmphibiousPathNavigation(this, this.level())

        // Setting WATER_BORDER to zero makes surface water blocks preferred
        setPathfindingMalus(PathType.WATER_BORDER, 0.0f)
        setPathfindingMalus(PathType.WATER, 0.0f)
    }

    /**
     * Override hurt() to disable drowning damage.
     *
     * We want otters to seek air for the behavior, but it's too sad when they drown.
     */
    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (source == damageSources().drown()) return false
        return super.hurt(source, amount)
    }

    /* A little boost to help short fellas get up slopes. */
    override fun maxUpStep(): Float {
        return 1.0f
    }

    override fun registerGoals() {
        goalSelector.addGoal(1, OtterBreathAirGoal(this))
        goalSelector.addGoal(2, OtterDiveGoal(this, 1.0))
        goalSelector.addGoal(2, OtterFloatGoal(this))
        goalSelector.addGoal(2, OtterSwimmingGoal(this, 0.8, 20))
        goalSelector.addGoal(3, OtterWalkingGoal(this, 0.7, 20))
        goalSelector.addGoal(4, LookAtPlayerGoal(this, Player::class.java, 5.0f, 0.1f, true))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        goalSelector.addGoal(0, OtterAttackGoal(this, 1.0, true))
        targetSelector.addGoal(
            1,
            NearestAttackableTargetGoal(
                this,
                LivingEntity::class.java,
                10,
                true,
                true
            ) { entity: LivingEntity -> prey.any { preyType -> entity.type.`is`(preyType) } && hunger < MAX_HUNGER / 4 })
    }

    /* Make otters seek air every 40 secs or so */
    override fun getMaxAirSupply(): Int {
        return 800
    }

    override fun increaseAirSupply(currentAir: Int): Int {
        return this.maxAirSupply
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    /* Override to lock head X rotation when underwater so that the otter's head follows its body */
    override fun getMaxHeadXRot(): Int {
        if (isUnderWater) return 0
        return super.getMaxHeadXRot()
    }

    /* Override to lock head Y rotation when underwater so that the otter's head follows its body */
    override fun getMaxHeadYRot(): Int {
        if (isUnderWater) return 0
        return super.getMaxHeadYRot()
    }

    override fun travel(travelVector: Vec3) {
        if (this.isEffectiveAi && this.isInWater && this.getAction() != OtterAction.FLOATING) {
            this.moveRelative(this.speed, travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            this.deltaMovement = deltaMovement.multiply(0.9, 1.0, 0.9)
        }
        super.travel(travelVector)
    }

    override fun isVisuallySwimming(): Boolean {
        return this.isSwimming
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        val biome = world.getBiome(this.blockPosition())
        val selectedType = Type.fromBiome(biome)
        this.variant = selectedType
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
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

    override fun getWaterline(): Float {
        return 0.125f
    }


    /* Animation controller triggers based on the OtterAction enum in otter.action, which is set by various goals */
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "otter_controller", 8) { state ->
                when {
                    state.isMoving && getAction() == OtterAction.WALKING ->
                        state.setAndContinue(DefaultAnimations.WALK)

                    state.isMoving && getAction() == OtterAction.SWIMMING ->
                        state.setAndContinue(DefaultAnimations.SWIM)

                    state.isMoving && getAction() == OtterAction.DIVING ->
                        state.setAndContinue(DefaultAnimations.SWIM)

                    !state.isMoving && isInWater && !onGround() && getAction() == OtterAction.FLOATING ->
                        state.setAndContinue(FLOAT_ANIMATION)

                    else ->
                        if (isInWater) {
                            state.setAndContinue(WATER_IDLE)
                        } else state.setAndContinue(DefaultAnimations.IDLE)
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

        const val MAX_HUNGER = 4800
        const val HUNGER_KEY = "Hunger"

        val FLOAT_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.float_idle")
        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(OtterEntity::class.java, EntityDataSerializers.INT)
        val HUNGER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(OtterEntity::class.java, EntityDataSerializers.INT)
        val ACTION: EntityDataAccessor<Int> = SynchedEntityData.defineId(
            OtterEntity::class.java,
            EntityDataSerializers.INT
        )

        /** Enum for the action state machine, with codec to store in NBT */
        enum class OtterAction(val id: Int, private val key: String) : StringRepresentable {
            IDLE(0, "idle"),
            SWIMMING(1, "swimming"),
            DIVING(2, "diving"),
            FLOATING(3, "floating"),
            WALKING(4, "walking");

            override fun getSerializedName(): String {
                return this.key
            }

            companion object {
                val CODEC: StringRepresentable.EnumCodec<OtterAction> =
                    StringRepresentable.fromEnum { entries.toTypedArray() }
                private val BY_ID: IntFunction<OtterAction> = ByIdMap.continuous(
                    { obj: OtterAction -> obj.id },
                    entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.ZERO
                )

                fun byName(name: String?): OtterAction {
                    return OtterAction.CODEC.byName(name, IDLE) as OtterAction
                }

                fun fromId(id: Int): OtterAction {
                    return BY_ID.apply(id) as OtterAction
                }
            }
        }

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

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(TYPE, 0)
        builder.define(HUNGER, MAX_HUNGER)
        builder.define(ACTION, 0) // OtterAction.IDLE
        super.defineSynchedData(builder)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.serializedName)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putString("Action", this.getAction().serializedName)

        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        this.variant = Type.byName(nbt.getString("Type"))
        hunger = nbt.getInt(HUNGER_KEY)
        this.setAction(OtterAction.byName(nbt.getString("Action")))
        super.readAdditionalSaveData(nbt)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }

    fun setAction(action: OtterAction) {
        entityData.set(ACTION, action.id)
    }

    fun getAction(): OtterAction {
        return OtterAction.fromId(entityData.get(ACTION))
    }

    /** Check if the otter is close to a given BlockPos. "Close" is arbitratrily 12. */
    private fun closeToBlockPos(blockpos: BlockPos?): Boolean {
        return blockpos?.closerToCenterThan(this.position(), 12.0) ?: false
    }

    /** Check if the otter is close to the next node in its current path */
    fun closeToNextPos(): Boolean {
        val blockpos = this.getNavigation().path?.nextNode?.asBlockPos()
        return closeToBlockPos(blockpos)
    }

    internal class OtterAttackGoal(
        val otter: OtterEntity,
        speedModifier: Double,
        followingTargetEvenIfNotSeen: Boolean
    ) : MeleeAttackGoal(
        otter,
        speedModifier, followingTargetEvenIfNotSeen
    ) {
        override fun checkAndPerformAttack(enemy: LivingEntity) {
            if (canPerformAttack(enemy))
            {
                this.resetAttackCooldown()
                otter.swing(InteractionHand.MAIN_HAND)
                otter.doHurtTarget(enemy)

                if (enemy.health <= 0) otter.hunger = MAX_HUNGER
                otter.health = otter.maxHealth
            }
            super.checkAndPerformAttack(enemy)
        }
    }

    /** Extend vanilla BreathAirGoal to add animation hints */
    internal class OtterBreathAirGoal(val otter: OtterEntity) : BreathAirGoal(otter) {
        override fun start() {
            super.start()
            otter.setAction(OtterAction.SWIMMING)
        }
    }

    /** RandomStrollGoal so that otters will walk around on land. */
    internal class OtterWalkingGoal(private val otter: OtterEntity, speedModifier: Double, interval: Int) :
        RandomStrollGoal(otter, speedModifier, interval) {

        override fun canUse(): Boolean {
            return !(otter.isInWater || otter.isUnderWater) && super.canUse()
        }

        override fun canContinueToUse(): Boolean {
            return !(otter.isInWater || otter.isUnderWater) && super.canContinueToUse()
        }

        override fun start() {
            otter.setAction(OtterAction.WALKING)
            super.start()
        }

    }

    /* Swimming goal for otters. Has a timeout to allow other goals to run */
    internal class OtterSwimmingGoal(private val otter: OtterEntity, speedModifier: Double, interval: Int) :
        RandomStrollGoal(otter, speedModifier, interval) {
        private var swimTimer = 0L
        private val maxSwimTime = 300L

        init {
            // We set LOOK here as well as MOVE so the otter doesn't randomly follow its eyeline when swimming
            this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        }

        override fun canUse(): Boolean {
            return otter.isInWater && otter.getAction() != OtterAction.FLOATING && swimTimer <= otter.level().gameTime && super.canUse()
        }

        override fun canContinueToUse(): Boolean {
            return otter.isInWater && super.canContinueToUse()
        }

        /**If we get sufficiently close to our next path node, set forceTrigger to recompute the path */
        override fun tick() {
            if (otter.navigation.isDone || otter.closeToNextPos()) {
                this.forceTrigger = true
            }
            super.tick()
        }

        override fun start() {
            otter.setAction(OtterAction.SWIMMING)
            swimTimer = otter.level().gameTime + maxSwimTime
            super.start()
        }

        override fun stop() {
            swimTimer = 0
            otter.navigation.stop()
        }

        /* Pick a random position at sea level to pursue */
        override fun getPosition(): Vec3? {
            var pos = DefaultRandomPos.getPos(otter, 8, 0)
            if (pos != null) {
                val height = otter.level().getHeight(Heightmap.Types.WORLD_SURFACE, pos.x.toInt(), pos.z.toInt()) + 1
                pos = Vec3(pos.x, height.toDouble(), pos.z)
            }
            return pos
        }
    }

    /* Dive goal with an enforced minimum delay between executions to prevent the otter from bobbing up and down too much. */
    internal class OtterDiveGoal(private val otter: OtterEntity, private val speedModifier: Double) : Goal() {

        private var target: Vec3 = Vec3.ZERO
        private val minDiveDelay = 500L
        private var nextDiveTime = 0L

        init {
            this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        }


        override fun canUse(): Boolean {
            return otter.isInWater && !otter.isUnderWater && otter.level().gameTime >= nextDiveTime
        }

        override fun canContinueToUse(): Boolean {
            return target != Vec3.ZERO && otter.navigation.isInProgress && !otter.isVehicle
        }

        override fun start() {
            otter.setAction(OtterAction.DIVING)
            // Disable applyGravity on the movement controller so that there's no y counterforce to our dive.
            otter.swimControl.applyGravity = false

            target = Vec3.ZERO

            /*
             Pick a random direction and select a spot on the Heightmap.Types.OCEAN_FLOOR heightmap 2-8 blocks from our current xz position.
             If we fail to find a valid position, the goal will be unusable next tick and we'll switch.
             */
            val pos = otter.blockPosition().mutable()
            val angle = otter.random.nextFloat() * Mth.TWO_PI
            val vec = Vec2(Mth.cos(angle), Mth.sin(angle))
            vec.scale((otter.random.nextFloat() * 6) + 2)
            pos.move(vec.x.toInt(), 0, vec.y.toInt())
            pos.y = otter.level().getHeight(Heightmap.Types.OCEAN_FLOOR, pos.x, pos.z) + 1

            if (otter.level().getBlockState(pos) == Blocks.WATER.defaultBlockState()) {
                target = pos.center
                otter.navigation.moveTo(target.x, target.y, target.z, speedModifier)
            }
        }

        override fun stop() {
            target = Vec3.ZERO
            nextDiveTime = otter.level().gameTime + minDiveDelay
            otter.navigation.stop()
            // Re-enable gravity counterforce on y-axis
            otter.swimControl.applyGravity = true
        }
    }


    /* Makes the otter float on its back. Adorable! */
    internal class OtterFloatGoal(private val otter: OtterEntity) : Goal() {
        private var floatingTimer: Long = 0
        private var nextFloatTime: Long = 0
        private val minFloatDelay = 300

        /* We don't lock LOOK here so the otter can look around */
        init {
            this.flags = EnumSet.of(Flag.MOVE)
        }

        override fun requiresUpdateEveryTick(): Boolean {
            return true
        }

        override fun canUse(): Boolean {
            return otter.isInWater && !otter.onGround() && !otter.isUnderWater && otter.level().gameTime > nextFloatTime && otter.random.nextFloat() <= 0.3
        }

        override fun canContinueToUse(): Boolean {
            return otter.isInWater && !otter.onGround() && otter.level().gameTime < floatingTimer
        }

        override fun start() {
            this.otter.navigation.stop()
            // Stop the otter in its tracks
            otter.deltaMovement = Vec3.ZERO
            this.floatingTimer = otter.random.nextInt(100, 300) + otter.level().gameTime
            otter.setAction(OtterAction.FLOATING)
        }

        override fun stop() {
            floatingTimer = 0
            nextFloatTime = otter.level().gameTime + minFloatDelay
            otter.setAction(OtterAction.IDLE)
        }
    }

    /* Extend SmoothSwimmingMoveControl to add a counterforce on Y to gravity. Stronger when the otter is floating.
    * Also allow toggling gravity at runtime. */
    internal class OtterMoveControl(
        val otter: OtterEntity, maxTurnX: Int, maxTurnY: Int, inWaterSpeedModifier: Float,
        outsideWaterSpeedModifier: Float, var applyGravity: Boolean
    ) : SmoothSwimmingMoveControl(otter, maxTurnX, maxTurnY, inWaterSpeedModifier, outsideWaterSpeedModifier, false) {

        override fun tick() {
            if (this.applyGravity && otter.isInWater) {
                val deltaMovement = otter.deltaMovement
                var factor = 0.006 // Basic counterforce to gravity added by vanilla movement code

                // If we're in FLOATING mode, give an even bigger boost to keep the otter above the waterline
                if (otter.getAction() == OtterAction.FLOATING) {
                    if (otter.isBelowWaterline()) {
                        factor += 0.025
                        // And even bigger if they're underwater.
                        if (otter.isUnderWater) factor += 0.02
                    }
                }

                // Clamp the counterforce to the distance between the current and ideal surface position.
                val distance = -(otter.y - (62.78 - otter.getWaterline()))
                factor = minOf(factor, distance)
                otter.deltaMovement = deltaMovement.add(0.0, factor, 0.0)
            }
            super.tick()
        }
    }

    /* Extend LookControl to prevent the otter's xRot from being reset to zero every tick when underwater */
    internal class OtterLookControl(val otter: OtterEntity) : LookControl(otter) {
        override fun resetXRotOnTick(): Boolean {
            if (otter.isUnderWater && otter.y < otter.level().seaLevel - 1) return false
            return super.resetXRotOnTick()
        }

        override fun setLookAt(x: Double, y: Double, z: Double) {
            if (otter.getAction() == OtterAction.FLOATING) {
                val lookVec = Vec3(x, y, z).scale(-1.0)
                super.setLookAt(lookVec.z, y, lookVec.x, mob.headRotSpeed.toFloat(), mob.maxHeadXRot.toFloat())
            }
        }
    }
}
