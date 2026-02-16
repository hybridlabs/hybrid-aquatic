package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.boids.BoidGoal
import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

class HerringEntity(type: EntityType<out HerringEntity>, world: Level) : HybridAquaticSchoolingFishEntity(type, world) {
    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HybridAquaticEntityTags.MEDIUM_CREATURES,
        HybridAquaticEntityTags.LARGE_CREATURES,
        HybridAquaticEntityTags.ALL_SHARKS
    )

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, BoidGoal(this, 0.25f, 0.5f, 8 / 20f, 1 / 20f))
        goalSelector.addGoal(3, StayInWaterGoal(this))
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

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt("FishCount", getFishCount())
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        setFishCount(compound.getInt("FishCount").coerceAtMost(THREE_FISH))
    }

    override fun tick() {
        super.tick()

        if (!level().isClientSide) {
            val maxHp = getAttributeValue(Attributes.MAX_HEALTH).toFloat()
            val fraction = health / maxHp

            when {
                fraction > 2f / 3f -> setFishCount(THREE_FISH)
                fraction > 1f / 3f -> setFishCount(TWO_FISH)
                else -> setFishCount(ONE_FISH)
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
        val maxHp = getAttributeValue(Attributes.MAX_HEALTH).toFloat()
        val startingFraction = this.random.nextFloat()
        val startingHealth = (maxHp * startingFraction.coerceAtLeast(0.34f))

        this.health = startingHealth

        val fraction = health / maxHp
        when {
            fraction > 2f / 3f -> setFishCount(THREE_FISH)
            fraction > 1f / 3f -> setFishCount(TWO_FISH)
            else -> setFishCount(ONE_FISH)
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
        if (entity is HerringEntity) {
            return false
        }
        return super.canCollideWith(entity)
    }

    override fun doPush(entity: Entity) {
        if (entity is HerringEntity) {
            return
        }
        super.doPush(entity)
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        val maxHp = getAttributeValue(Attributes.MAX_HEALTH).toFloat()
        val oldFraction = health / maxHp

        val result = super.hurt(source, amount)

        if (result && !level().isClientSide) {
            val newFraction = health / maxHp

            val oldFishCount = when {
                oldFraction > 2f / 3f -> MackerelEntity.THREE_FISH
                oldFraction > 1f / 3f -> MackerelEntity.TWO_FISH
                else -> MackerelEntity.ONE_FISH
            }

            val newFishCount = when {
                newFraction > 2f / 3f -> MackerelEntity.THREE_FISH
                newFraction > 1f / 3f -> MackerelEntity.TWO_FISH
                else -> MackerelEntity.ONE_FISH
            }

            if (newFishCount in 1..<oldFishCount) {
                spawnAtLocation(HybridAquaticItems.HERRING.get())
            }
        }

        return result
    }

    companion object {
        private val FISHCOUNT: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HerringEntity::class.java, EntityDataSerializers.INT)

        const val ONE_FISH = 1
        const val TWO_FISH = 2
        const val THREE_FISH = 3

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }
}
