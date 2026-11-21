package dev.hybridlabs.aquatic.entity.base

import net.minecraft.server.level.ServerLevel

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import net.minecraft.core.BlockPos
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER")
abstract class HAMinibossEntity(type: EntityType<out Monster>, world: Level) :
    Monster(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var attackTick = 0

    //#region Data
    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(ATTEMPT_ATTACK, false)
    }

    override fun addAdditionalSaveData(output: ValueOutput) {
        super.addAdditionalSaveData(output)
        output.putInt("AttackTick", this.attackTick)
    }

    override fun readAdditionalSaveData(input: ValueInput) {
        super.readAdditionalSaveData(input)
        this.attackTick = input.getIntOr("AttackTick", 0)
    }
    //#endregion

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

    override fun isPreventingPlayerRest(level: ServerLevel, player: Player): Boolean {
        return true
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    companion object {

        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HAMinibossEntity::class.java, EntityDataSerializers.BOOLEAN)

        fun canSpawn(
            type: EntityType<out Monster>,
            world: ServerLevelAccessor,
            reason: EntitySpawnReason,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            return world.isWaterAt(pos)
        }
    }
}