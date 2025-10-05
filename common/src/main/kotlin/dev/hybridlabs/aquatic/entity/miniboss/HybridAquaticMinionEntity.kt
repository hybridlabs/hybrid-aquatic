package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.RandomSource
import net.minecraft.world.Difficulty
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.ai.goal.target.TargetGoal
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.ServerLevelAccessor
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis")
abstract class HybridAquaticMinionEntity(type: EntityType<out Monster>, world: Level) : Monster(type, world),
    GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var owner: Mob? = null
    private var boundOrigin: BlockPos? = null
    private var hasLimitedLife = false
    private var limitedLifeTicks = 0
    private var attackTick = 0

    override fun tick() {
        super.tick()
        if (this.hasLimitedLife && --this.limitedLifeTicks <= 0) {
            this.limitedLifeTicks = 20
            this.hurt(damageSources().starve(), 1.0f)
        }
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(ATTEMPT_ATTACK, false)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt("AttackTick", this.attackTick)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        this.attackTick = nbt.getInt("AttackTick")
        if (nbt.contains("BoundX")) {
            this.boundOrigin = BlockPos(nbt.getInt("BoundX"), nbt.getInt("BoundY"), nbt.getInt("BoundZ"))
        }

        if (nbt.contains("LifeTicks")) {
            this.setLimitedLife(nbt.getInt("LifeTicks"))
        }
    }

    override fun registerGoals() {
        goalSelector.addGoal(1, MeleeAttackGoal(this, 0.6, true))
        targetSelector.addGoal(1, MinionCopyOwnerTargetGoal(this))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.5))
        goalSelector.addGoal(3, LookAtPlayerGoal(this, Player::class.java, 8.0f))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        super.registerGoals()
    }

    fun getOwner(): Mob? {
        return this.owner
    }

    fun setOwner(owner: Mob?) {
        this.owner = owner
    }

    fun setLimitedLife(limitedLifeTicks: Int) {
        this.hasLimitedLife = true
        this.limitedLifeTicks = limitedLifeTicks
    }

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(DefaultAnimations.genericWalkRunIdleController(this))
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SWING))
    }

    override fun finalizeSpawn(
        level: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        reason: MobSpawnType,
        spawnData: SpawnGroupData?,
        dataTag: CompoundTag?,
    ): SpawnGroupData? {
        val randomsource = level.random
        this.populateDefaultEquipmentSlots(randomsource, difficulty)
        this.populateDefaultEquipmentEnchantments(randomsource, difficulty)
        return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag)
    }

    override fun aiStep() {
        this.updateSwingTime()
        super.aiStep()
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return false
    }

    override fun checkDespawn() {
        if (level().difficulty == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
            discard()
        } else {
            noActionTime = 0
        }
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun isPreventingPlayerRest(player: Player): Boolean {
        return true
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    companion object {

        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticMinionEntity::class.java, EntityDataSerializers.BOOLEAN)

        fun canSpawn(
            type: EntityType<out Monster>,
            world: LevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            return world.isWaterAt(pos)
        }
    }

    internal class MinionCopyOwnerTargetGoal(
        private val minion: HybridAquaticMinionEntity
    ) : TargetGoal(minion, false) {

        private val copyOwnerTargeting: TargetingConditions =
            TargetingConditions.forNonCombat()
                .ignoreLineOfSight()
                .ignoreInvisibilityTesting()

        override fun canUse(): Boolean {
            val owner = minion.getOwner() ?: return false
            val ownerTarget = owner.target ?: return false
            return canAttack(ownerTarget, copyOwnerTargeting)
        }

        override fun start() {
            val owner = minion.getOwner() ?: return
            val ownerTarget = owner.target ?: return
            mob.target = ownerTarget
            super.start()
        }
    }

}