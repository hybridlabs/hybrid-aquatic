package dev.hybridlabs.aquatic.entity.base

import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.pathfinder.BlockPathTypes

abstract class HybridAquaticWaterAnimal protected constructor(entityType: EntityType<out HybridAquaticWaterAnimal>, level: Level) :
    AgeableMob(entityType, level) {
    init {
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    override fun checkSpawnObstruction(level: LevelReader): Boolean {
        return level.isUnobstructed(this)
    }

    override fun getAmbientSoundInterval(): Int {
        return 120
    }

    override fun getExperienceReward(): Int {
        return 1 + this.level().random.nextInt(3)
    }

    protected open fun handleAirSupply(airSupply: Int) {
        if (this.isAlive && !this.isInWaterOrBubble) {
            this.airSupply = airSupply - 1
            if (this.airSupply == -20) {
                this.airSupply = 0
                this.hurt(this.damageSources().drown(), 2.0f)
            }
        } else {
            this.airSupply = 300
        }
    }

    override fun baseTick() {
        val i = this.airSupply
        super.baseTick()
        this.handleAirSupply(i)
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun canBeLeashed(player: Player): Boolean {
        return false
    }
}