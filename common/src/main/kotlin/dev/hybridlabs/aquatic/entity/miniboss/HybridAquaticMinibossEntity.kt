package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis", "UNUSED_PARAMETER")
abstract class HybridAquaticMinibossEntity(type: EntityType<out Monster>, world: Level) :
    Monster(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var attackTick = 0

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
            SynchedEntityData.defineId(HybridAquaticMinibossEntity::class.java, EntityDataSerializers.BOOLEAN)

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
}