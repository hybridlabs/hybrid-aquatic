package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.TargetPredicate
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.ai.goal.SwimAroundGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.tag.EntityTypeTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class ToadfishEntity(entityType: EntityType<out ToadfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, emptyMap(), HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NONE) {

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        goalSelector.add(0, InflateGoal())
        goalSelector.add(4, SwimAroundGoal(this, 1.0, 10))
    }

    var inflateTicks = 0
    var deflateTicks = 0

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(PUFF_STATE, NOT_PUFFED)
    }

    fun getPuffState(): Int {
        return dataTracker.get(PUFF_STATE)
    }

    private fun setPuffState(state: Int) {
        dataTracker.set(PUFF_STATE, state)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("PuffState", getPuffState())
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        setPuffState(nbt.getInt("PuffState").coerceAtMost(FULLY_PUFFED))
    }

    override fun tick() {
        super.tick()
        if (!world.isClient && isAlive && canMoveVoluntarily()) {
            when {
                inflateTicks > 0 -> handleInflation()
                getPuffState() != NOT_PUFFED -> handleDeflation()
            }
        }
    }

    private fun handleInflation() {
        if (getPuffState() == NOT_PUFFED) {
            playSound(SoundEvents.ENTITY_PUFFER_FISH_BLOW_UP, soundVolume, soundPitch)
            setPuffState(SEMI_PUFFED)
        } else if (inflateTicks > 40 && getPuffState() == SEMI_PUFFED) {
            playSound(SoundEvents.ENTITY_PUFFER_FISH_BLOW_UP, soundVolume, soundPitch)
            setPuffState(FULLY_PUFFED)
        }
        inflateTicks++
    }

    private fun handleDeflation() {
        if (deflateTicks > 60 && getPuffState() == FULLY_PUFFED) {
            playSound(SoundEvents.ENTITY_PUFFER_FISH_BLOW_OUT, soundVolume, soundPitch)
            setPuffState(SEMI_PUFFED)
        } else if (deflateTicks > 100 && getPuffState() == SEMI_PUFFED) {
            playSound(SoundEvents.ENTITY_PUFFER_FISH_BLOW_OUT, soundVolume, soundPitch)
            setPuffState(NOT_PUFFED)
        }
        deflateTicks++
    }

    override fun tickMovement() {
        super.tickMovement()
        val world = world
        if (world is ServerWorld) {
            if (isAlive && getPuffState() > 0) {
                val nearbyEntities = world.getEntitiesByClass(MobEntity::class.java, boundingBox.expand(0.3)) {
                    BLOW_UP_TARGET_PREDICATE.test(this, it)
                }
                nearbyEntities.forEach { sting(it) }
            }
        }
    }

    private fun sting(mob: MobEntity) {
        val world = world
        if (world is ServerWorld) {
            val puffLevel = getPuffState()
            val damageSource = this.damageSources.mobAttack(this)
            if (mob.damage(damageSource, (1 + puffLevel).toFloat())) {
                mob.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 60 * puffLevel, 0), this)
                playSound(SoundEvents.ENTITY_PUFFER_FISH_STING, 1.0f, 1.0f)
            }
        }
    }

    override fun onPlayerCollision(player: PlayerEntity) {
        val world = world
        if (world is ServerWorld) {
            val puffLevel = getPuffState()
            if (puffLevel > 0 && player.damage(this.damageSources.mobAttack(this), (1 + puffLevel).toFloat())) {
                player.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 60 * puffLevel, 0), this)
            }
        }
    }

    private inner class InflateGoal : Goal() {
        override fun canStart(): Boolean {
            val world = world
            if (world is ServerWorld) {
                val nearbyEntities = world.getEntitiesByClass(LivingEntity::class.java, boundingBox.expand(2.0)) {
                    BLOW_UP_TARGET_PREDICATE.test(this@ToadfishEntity, it)
                }
                return nearbyEntities.isNotEmpty()
            }

            return false
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
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }

        private val PUFF_STATE: TrackedData<Int> = DataTracker.registerData(ToadfishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        private val BLOW_UP_FILTER: TargetPredicate.EntityPredicate = TargetPredicate.EntityPredicate { entity, _ ->
            if (entity is PlayerEntity && entity.isCreative) false else !entity.type.isIn(EntityTypeTags.AQUATIC)
        }
        private val BLOW_UP_TARGET_PREDICATE: TargetPredicate = TargetPredicate.createNonAttackable().ignoreDistanceScalingFactor().ignoreVisibility().setPredicate(BLOW_UP_FILTER)

        const val NOT_PUFFED = 0
        const val SEMI_PUFFED = 1
        const val FULLY_PUFFED = 2
    }
}
