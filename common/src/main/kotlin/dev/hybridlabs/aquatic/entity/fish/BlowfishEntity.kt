package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundGameEventPacket
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import java.util.function.Predicate

class BlowfishEntity(entityType: EntityType<out BlowfishEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(HybridAquaticEntityTags.NONE),
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
    }

    var inflateTicks = 0
    var deflateTicks = 0

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(PUFF_STATE, NOT_PUFFED)
    }

    override fun onSyncedDataUpdated(key: EntityDataAccessor<*>) {
        super.onSyncedDataUpdated(key)
        if (key == PUFF_STATE) {
            refreshDimensions()
        }
    }

    fun getPuffState(): Int {
        return entityData.get(PUFF_STATE)
    }

    private fun setPuffState(state: Int) {
        entityData.set(PUFF_STATE, state)
        refreshDimensions()
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
        if (!level().isClientSide && isAlive && hasSelfControl()) {
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
            nearbyEntities.forEach { touch(it) }
        }
    }

    private fun touch(mob: Mob) {
        val i = this.getPuffState()
        if (mob.hurt(damageSources().mobAttack(this), (1 + i).toFloat())) {
            mob.addEffect(
                MobEffectInstance(MobEffects.POISON, 60 * i, 0),
                this
            )
            this.playSound(SoundEvents.PUFFER_FISH_STING, 1.0f, 1.0f)
        }
    }

    override fun playerTouch(entity: Player) {
        val i = this.getPuffState()
        if (entity is ServerPlayer && i > 0 && entity.hurt(damageSources().mobAttack(this), (1 + i).toFloat())) {
            if (!this.isSilent) {
                entity.connection.send(ClientboundGameEventPacket(ClientboundGameEventPacket.PUFFER_FISH_STING, 0.0f))
            }

            entity.addEffect(
                MobEffectInstance(MobEffects.POISON, 60 * i, 0),
                this
            )
        }
    }

    private inner class InflateGoal : Goal() {
        override fun canUse(): Boolean {
            val nearbyEntities = level().getEntitiesOfClass(LivingEntity::class.java, boundingBox.inflate(2.0)) {
                BLOW_UP_TARGET_PREDICATE.test(this@BlowfishEntity, it)
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

    override fun getDimensions(pose: Pose): EntityDimensions {
        val scale = when (getPuffState()) {
            NOT_PUFFED -> 0.5f
            SEMI_PUFFED -> 0.7f
            FULLY_PUFFED -> 5.0f
            else -> 1.0f
        }
        return super.getDimensions(pose).scale(scale)
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
            SynchedEntityData.defineId(BlowfishEntity::class.java, EntityDataSerializers.INT)
        private val BLOW_UP_FILTER: Predicate<LivingEntity> =
            Predicate { entity -> if (entity is Player && entity.isCreative) false else entity.mobType != MobType.WATER }
        private val BLOW_UP_TARGET_PREDICATE: TargetingConditions =
            TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight()
                .selector(BLOW_UP_FILTER)

        const val NOT_PUFFED = 0
        const val SEMI_PUFFED = 1
        const val FULLY_PUFFED = 2
    }
}