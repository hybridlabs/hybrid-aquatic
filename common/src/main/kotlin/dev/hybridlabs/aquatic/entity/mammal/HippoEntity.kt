package dev.hybridlabs.aquatic.entity.mammal

import net.minecraft.world.entity.LivingEntity

import net.minecraft.world.entity.EntityReference

import net.minecraft.world.entity.EntitySpawnReason

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalAttackGoal
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalBreedGoal
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalFollowParentGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseMammalEntity
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
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.PathType
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.constant.DefaultAnimations
import java.util.*

@Suppress("DEPRECATION")
class HippoEntity(entityType: EntityType<out HippoEntity>, world: Level) :
    BaseMammalEntity(entityType, world),
    NeutralMob {

    private var angerEndTime: Long = -1L
    private var angryAt: EntityReference<LivingEntity>? = null

    init {
        setPathfindingMalus(PathType.WATER_BORDER, 0.0f)
        setPathfindingMalus(PathType.WATER, 0.0f)
        setPathfindingMalus(PathType.FIRE_IN_NEIGHBOR, 16.0f)
        setPathfindingMalus(PathType.FIRE, -1.0f)

        moveControl = MoveControl(this)
        lookControl = LookControl(this)
    }

    override fun createNavigation(level: Level): PathNavigation {
        return AmphibiousPathNavigation(this, level)
    }

    override fun registerGoals() {
        targetSelector.addGoal(1, HurtByTargetGoal(this))
        targetSelector.addGoal(3, ResetUniversalAngerTargetGoal(this, false))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, Player::class.java, 10, true, true) { target, level -> this.isAngryAt(target, level) })
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, Player::class.java, 10, true, false) { target, _ -> this.okTarget(target) })
        goalSelector.addGoal(1, WaterAnimalBreedGoal(this, 1.1))
        goalSelector.addGoal(1, WaterAnimalAttackGoal(this, 1.0, false))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.7, 20))
        goalSelector.addGoal(4, LookAtPlayerGoal(this, Player::class.java, 5.0f, 0.1f, true))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        goalSelector.addGoal(5, WaterAnimalFollowParentGoal(this, 1.1))
        goalSelector.addGoal(2, TemptGoal(this, 1.1, BREEDING_INGREDIENT, false))
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        super.registerControllers(controllers)
        controllers.add(DefaultAnimations.genericAttackAnimation<HippoEntity>(DefaultAnimations.ATTACK_BITE))
    }

    override fun tick() {
        super.tick()

        if (hunger > 0) hunger -= 1
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(Items.MELON_SLICE) || stack.`is`(Items.MELON)
    }

    /**
     * Override hurt() to disable drowning damage.
     *
     * We want hippos to seek air for the behavior, but it's too sad when they drown.
     */
    override fun hurtServer(level: ServerLevel, source: DamageSource, amount: Float): Boolean {
        if (source == damageSources().drown()) return false
        return super.hurtServer(level, source, amount)
    }

    /* A little boost to help short fellas get up slopes. */
    override fun maxUpStep(): Float {
        return 1.5f
    }

    //#region Air & Moistness
    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun getWaterSlowDown(): Float {
        return 0.85f
    }

    fun okTarget(target: LivingEntity?): Boolean {
        if (target == null || !target.isInWater) return false

        val mainHand = target.mainHandItem.item
        val offHand = target.offhandItem.item

        return !(mainHand == BREEDING_INGREDIENT || offHand == BREEDING_INGREDIENT)
    }
    //#endregion

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: EntitySpawnReason,
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
        return HAEntityTypes.HIPPO.get().create(p0, EntitySpawnReason.MOB_SUMMONED)
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
                .add(Attributes.TEMPT_RANGE, 10.0)
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

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(
            Items.MELON, Items.MELON_SLICE
        )
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(HUNGER, MAX_HUNGER)
        super.defineSynchedData(builder)
    }

    override fun addAdditionalSaveData(output: ValueOutput) {
        output.putInt(HUNGER_KEY, hunger)

        super.addAdditionalSaveData(output)
    }

    override fun readAdditionalSaveData(input: ValueInput) {
        hunger = input.getIntOr(HUNGER_KEY, hunger)
        super.readAdditionalSaveData(input)
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
