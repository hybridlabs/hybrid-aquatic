package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.entity.EntityType
import net.minecraft.entity.MobSpawnType
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.entity.mob.Monster
import net.minecraft.entity.player.Player
import net.minecraft.nbt.CompoundTag
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis", "UNUSED_PARAMETER", "DEPRECATION")
abstract class HybridAquaticMinibossEntity(type: EntityType<out Monster>, world: Level) : Monster(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    private var attackTick = 0

    override fun initSynchedEntityData() {
        super.initSynchedEntityData()
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

    override fun tick() {
        super.tick()
        if .isNoAi) {
            return
        }
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

    override fun isPreventingPlayerRest(player:Player?): Boolean {
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
            random: Random
        ): Boolean {
            val topY = world.seaLevel
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }
    }
}