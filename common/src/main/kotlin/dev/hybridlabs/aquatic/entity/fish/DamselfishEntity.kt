package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.boids.BoidGoal
import dev.hybridlabs.aquatic.entity.ai.goal.boids.LimitSpeedAndLookInVelocityDirectionGoal
import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

class DamselfishEntity(entityType: EntityType<out DamselfishEntity>, world: Level) :
    HybridAquaticSchoolingFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, BoidGoal(this, 0.1f, 0.3f, 8/20f, 1/20f))
        goalSelector.addGoal(3, StayInWaterGoal(this))
        goalSelector.addGoal(2, LimitSpeedAndLookInVelocityDirectionGoal(this, 0.1f, 0.25f))
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 12
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(FISHCOUNT, ONE_FISH)
    }

    override fun onSyncedDataUpdated(key: EntityDataAccessor<*>) {
        super.onSyncedDataUpdated(key)
        if (key == FISHCOUNT) {
            refreshDimensions()
        }
    }

    fun getFishCount(): Int {
        return entityData.get(FISHCOUNT)
    }

    private fun setFishCount(state: Int) {
        entityData.set(FISHCOUNT, state)
        refreshDimensions()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt("FishCount", getFishCount())
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        setFishCount(nbt.getInt("FishCount").coerceAtMost(THREE_FISH))
    }

    override fun tick() {
        super.tick()

        if (!level().isClientSide) {
            when (health.toInt()) {
                3 -> setFishCount(THREE_FISH)
                2 -> setFishCount(TWO_FISH)
                1 -> setFishCount(ONE_FISH)
            }
        }
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        val startingHealth = this.random.nextIntBetweenInclusive(1, 3)
        this.health = startingHealth.toFloat()

        when (startingHealth) {
            3 -> setFishCount(THREE_FISH)
            2 -> setFishCount(TWO_FISH)
            1 -> setFishCount(ONE_FISH)
        }

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getDimensions(pose: Pose): EntityDimensions {
        val scale = when (getFishCount()) {
            ONE_FISH -> 1.0f
            TWO_FISH -> 1.5f
            THREE_FISH -> 2.5f
            else -> 1.0f
        }

        return super.getDimensions(pose).scale(scale)
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    override fun canCollideWith(entity: Entity): Boolean {
        if (entity is DamselfishEntity) {
            return false
        }
        return super.canCollideWith(entity)
    }

    override fun doPush(entity: Entity) {
        if (entity is DamselfishEntity) {
            return
        }
        super.doPush(entity)
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        val oldHealth = this.health.toInt()
        val result = super.hurt(source, amount)
        val newHealth = this.health.toInt()

        if (result && !level().isClientSide) {
            if (oldHealth - newHealth == 1 && newHealth > 0) {
                spawnAtLocation(HybridAquaticItems.SERGEANT_MAJOR.get())
            }
        }
        return result
    }

    companion object {
        private val FISHCOUNT: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(DamselfishEntity::class.java, EntityDataSerializers.INT)

        const val ONE_FISH = 1
        const val TWO_FISH = 2
        const val THREE_FISH = 3

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }
}