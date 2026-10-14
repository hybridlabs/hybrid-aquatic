package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalAttackGoal
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalBreedGoal
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalFollowParentGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseMammalEntity
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.TimeUtil
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.PathType
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.constant.DefaultAnimations
import java.util.*

@Suppress("DEPRECATION")
class HippoEntity(entityType: EntityType<out HippoEntity>, world: Level) :
    BaseMammalEntity(entityType, world),
    NeutralMob {

    private var angerTime = 0
    private var angryAt: UUID? = null

    init {
        setPathfindingMalus(PathType.WATER_BORDER, 0.0f)
        setPathfindingMalus(PathType.WATER, 0.0f)
        setPathfindingMalus(PathType.DANGER_FIRE, 16.0f)
        setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0f)

        moveControl = MoveControl(this)
        lookControl = LookControl(this)
    }

    override fun createNavigation(level: Level): PathNavigation {
        return AmphibiousPathNavigation(this, level)
    }

    override fun registerGoals() {
        targetSelector.addGoal(1, HurtByTargetGoal(this))
        targetSelector.addGoal(3, ResetUniversalAngerTargetGoal(this, false))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, Player::class.java, 10, true, true) { this.isAngryAt(it) })
        this.targetSelector.addGoal(
            2,
            NearestAttackableTargetGoal(
                this,
                Player::class.java,
                10,
                true,
                false
            ) { target: LivingEntity -> this.okTarget(target) }
        )
        goalSelector.addGoal(1, WaterAnimalBreedGoal(this, 1.1))
        goalSelector.addGoal(1, WaterAnimalAttackGoal(this, 1.0, false))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.7, 20))
        goalSelector.addGoal(4, LookAtPlayerGoal(this, Player::class.java, 5.0f, 0.1f, true))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        goalSelector.addGoal(5, WaterAnimalFollowParentGoal(this, 1.1))
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        super.registerControllers(controllers)
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE))
    }

    override fun tick() {
        super.tick()

        if (hunger > 0) hunger -= 1
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(Items.MELON_SLICE)
    }

    /**
     * Override hurt() to disable drowning damage.
     *
     * We want hippos to seek air for the behavior, but it's too sad when they drown.
     */
    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (source == damageSources().drown()) return false
        return super.hurt(source, amount)
    }

    /* A little boost to help short fellas get up slopes. */
    override fun maxUpStep(): Float {
        return 1.5f
    }

    //#region Air & Moistness
    override fun isPushedByFluid(): Boolean {
        return false
    }

    fun okTarget(target: LivingEntity?): Boolean {
        return target?.isInWater ?: false
    }
    //#endregion

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
    ): SpawnGroupData? {

        if (this.random.nextFloat() < 0.01f) {
            this.setAge(-6000)
        }

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    //#region SFX
    override fun getAmbientSound(): SoundEvent? {
        return SoundEvents.HOGLIN_AMBIENT
    }

    override fun getHurtSound(damageSource: DamageSource): SoundEvent? {
        return SoundEvents.HOGLIN_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents.HOGLIN_DEATH
    }

    override fun getSwimSplashSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SWIM
    }
    //#endregion

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): HippoEntity? {
        return HAEntityTypes.HIPPO.get().create(p0)
    }

    override fun getMaxHeadYRot(): Int {
        return 45
    }

    override fun getMaxHeadXRot(): Int {
        return 45
    }

    companion object {

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val ANGER_TIME_RANGE: IntProvider = TimeUtil.rangeOfSeconds(10, 30)

        const val MAX_HUNGER = 4800
        const val HUNGER_KEY = "Hunger"

        val HUNGER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HippoEntity::class.java, EntityDataSerializers.INT)
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(HUNGER, MAX_HUNGER)
        super.defineSynchedData(builder)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        compound.putInt(HUNGER_KEY, hunger)

        super.addAdditionalSaveData(compound)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        hunger = compound.getInt(HUNGER_KEY)
        super.readAdditionalSaveData(compound)
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
