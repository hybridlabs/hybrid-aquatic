package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.TargetPredicate
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.entity.effect.MobEffectInstance
import net.minecraft.entity.effect.MobEffects
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.player.Player
import net.minecraft.nbt.CompoundTag
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World
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

    override fun getSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, InflateGoal())
    }

    var inflateTicks = 0
    var deflateTicks = 0

    override fun initSynchedEntityData() {
        super.initSynchedEntityData()
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
        if (!world.isClientSide && isAlive && canMoveVoluntarily()) {
            when {
                inflateTicks > 0 -> handleInflation()
                getPuffState() != NOT_PUFFED -> handleDeflation()
            }
        }
    }

    private fun handleInflation() {
        if (getPuffState() == NOT_PUFFED) {
            playSound(SoundEvents._PUFFER_FISH_BLOW_UP, soundVolume, soundPitch)
            setPuffState(SEMI_PUFFED)
        } else if (inflateTicks > 40 && getPuffState() == SEMI_PUFFED) {
            playSound(SoundEvents._PUFFER_FISH_BLOW_UP, soundVolume, soundPitch)
            setPuffState(FULLY_PUFFED)
        }
        inflateTicks++
    }

    private fun handleDeflation() {
        if (deflateTicks > 60 && getPuffState() == FULLY_PUFFED) {
            playSound(SoundEvents._PUFFER_FISH_BLOW_OUT, soundVolume, soundPitch)
            setPuffState(SEMI_PUFFED)
        } else if (deflateTicks > 100 && getPuffState() == SEMI_PUFFED) {
            playSound(SoundEvents._PUFFER_FISH_BLOW_OUT, soundVolume, soundPitch)
            setPuffState(NOT_PUFFED)
        }
        deflateTicks++
    }

    override fun aiStep() {
        super.aiStep()
        if (isAlive && getPuffState() > 0) {
            val nearbyEntities = world.getEntitiesByClass(MobEntity::class.java, boundingBox.expand(0.3)) {
                BLOW_UP_TARGET_PREDICATE.test(this, it)
            }
            nearbyEntities.forEach { sting(it) }
        }
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        if (super.damage(source, amount)) {

            val attacker = source.attacker
            if (attacker is LivingEntity && attacker.mainHandStack.isEmpty) {
                attacker.addMobEffect(MobEffectInstance(MobEffects.POISON, 200, 1))
            }

            return true
        }

        return false
    }

    private fun sting(mob: MobEntity) {
        val puffLevel = getPuffState()
        val damageSource = this.damageSources.mobAttack(this)
        if (mob.damage(damageSource, (1 + puffLevel).toFloat())) {
            mob.addMobEffect(MobEffectInstance(MobEffects.POISON, 60 * puffLevel, 0), this)
            playSound(SoundEvents._PUFFER_FISH_STING, 1.0f, 1.0f)
        }
    }

    override fun onPlayerCollision(player:Player) {
        val puffLevel = getPuffState()
        if (puffLevel > 0 && player.damage(this.damageSources.mobAttack(this), (1 + puffLevel).toFloat())) {
            player.addMobEffect(MobEffectInstance(MobEffects.POISON, 60 * puffLevel, 0), this)
        }
    }

    private inner class InflateGoal : Goal() {
        override fun canUse(): Boolean {
            val nearbyEntities = world.getEntitiesByClass(LivingEntity::class.java, boundingBox.expand(2.0)) {
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

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        private val PUFF_STATE: EntityDataAccessor<Int> = SynchedEntityData.defineId(BlowfishEntity::class.java, EntityDataSerializers.INTEGER)
        private val BLOW_UP_FILTER: Predicate<LivingEntity> = Predicate { entity -> if (entity isPlayer && entity.isCreative) false else entity.group != EntityGroup.AQUATIC }
        private val BLOW_UP_TARGET_PREDICATE: TargetPredicate = TargetPredicate.createNonAttackable().ignoreDistanceScalingFactor().ignoreVisibility().setPredicate(BLOW_UP_FILTER)

        const val NOT_PUFFED = 0
        const val SEMI_PUFFED = 1
        const val FULLY_PUFFED = 2
    }
}