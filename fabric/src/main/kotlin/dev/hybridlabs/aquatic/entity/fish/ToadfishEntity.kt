package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import java.util.function.Predicate

class ToadfishEntity(entityType: EntityType<out ToadfishEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world, emptyMap(),
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, InflateGoal())
        goalSelector.addGoal(4, RandomSwimmingGoal(this, 1.0, 10))
    }

    var inflateTicks = 0
    var deflateTicks = 0

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(PUFF_STATE, NOT_PUFFED)
    }

    fun getPuffState(): Int {
        return entityData.get(PUFF_STATE)
    }

    private fun setPuffState(state: Int) {
        entityData.set(PUFF_STATE, state)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt("PuffState", getPuffState())
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        setPuffState(nbt.getInt("PuffState").coerceAtMost(FULLY_PUFFED))
    }

    override fun tick() {
        super.tick()
        if (!level().isClientSide && isAlive && isEffectiveAi) {
            when {
                inflateTicks > 0 -> handleInflation()
                getPuffState() != NOT_PUFFED -> handleDeflation()
            }
        }
    }

    private fun handleInflation() {
        if (getPuffState() == NOT_PUFFED) {
            playSound(SoundEvents.PUFFER_FISH_BLOW_UP, soundVolume, voicePitch)
            setPuffState(SEMI_PUFFED)
        } else if (inflateTicks > 40 && getPuffState() == SEMI_PUFFED) {
            playSound(SoundEvents.PUFFER_FISH_BLOW_UP, soundVolume, voicePitch)
            setPuffState(FULLY_PUFFED)
        }
        inflateTicks++
    }

    private fun handleDeflation() {
        if (deflateTicks > 60 && getPuffState() == FULLY_PUFFED) {
            playSound(SoundEvents.PUFFER_FISH_BLOW_OUT, soundVolume, voicePitch)
            setPuffState(SEMI_PUFFED)
        } else if (deflateTicks > 100 && getPuffState() == SEMI_PUFFED) {
            playSound(SoundEvents.PUFFER_FISH_BLOW_OUT, soundVolume, voicePitch)
            setPuffState(NOT_PUFFED)
        }
        deflateTicks++
    }

    override fun aiStep() {
        super.aiStep()
        if (isAlive && getPuffState() > 0) {
            val nearbyEntities = level().getEntitiesOfClass(Mob::class.java, boundingBox.inflate(0.3)) {
                BLOW_UP_TARGET_PREDICATE.test(this, it)
            }
            nearbyEntities.forEach { sting(it) }
        }
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (super.hurt(source, amount)) {

            val attacker = source.directEntity
            if (attacker is LivingEntity && attacker.mainHandItem.isEmpty) {
                attacker.addEffect(MobEffectInstance(MobEffects.POISON, 200, 1))
            }

            return true
        }

        return false
    }

    private fun sting(mob: Mob) {
        val puffLevel = getPuffState()
        val damageSource = this.damageSources().mobAttack(this)
        if (mob.hurt(damageSource, (1 + puffLevel).toFloat())) {
            mob.addEffect(MobEffectInstance(MobEffects.POISON, 60 * puffLevel, 0), this)
            playSound(SoundEvents.PUFFER_FISH_STING, 1.0f, 1.0f)
        }
    }

    override fun playerTouch(player: Player) {
        val puffLevel = getPuffState()
        if (puffLevel > 0 && player.hurt(this.damageSources().mobAttack(this), (1 + puffLevel).toFloat())) {
            player.addEffect(MobEffectInstance(MobEffects.POISON, 60 * puffLevel, 0), this)
        }
    }

    private inner class InflateGoal : Goal() {
        override fun canUse(): Boolean {
            val nearbyEntities = level().getEntitiesOfClass(LivingEntity::class.java, boundingBox.inflate(2.0)) {
                BLOW_UP_TARGET_PREDICATE.test(this@ToadfishEntity, it)
            }
            return nearbyEntities.isNotEmpty()
        }

        override fun start() {
            inflateTicks = 1
            deflateTicks = 0
        }

        override fun stop() {
            inflateTicks = 0
        }
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        private val PUFF_STATE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(ToadfishEntity::class.java, EntityDataSerializers.INT)
        private val BLOW_UP_FILTER: Predicate<LivingEntity> = Predicate { entity ->
            if (entity is Player && entity.isCreative) false else entity.mobType != MobType.WATER
        }
        private val BLOW_UP_TARGET_PREDICATE: TargetingConditions =
            TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight()
                .selector(BLOW_UP_FILTER)

        const val NOT_PUFFED = 0
        const val SEMI_PUFFED = 1
        const val FULLY_PUFFED = 2
    }
}