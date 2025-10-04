package dev.hybridlabs.aquatic.entity.crustacean

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.BreathAirGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.BlockPathTypes

class GiantIsopodEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: Level) :
    HybridAquaticCrustaceanEntity(entityType, world, false) {


    val waterNavigation: PathNavigation = WaterBoundPathNavigation(this, world)
    val groundNavigation: PathNavigation = AmphibiousPathNavigation(this,world)
    val swimControl = SmoothSwimmingMoveControl(this, 85, 5, 1.0f, 0.2f, true)
    val walkControl: MoveControl = moveControl

    init {
        navigation = groundNavigation
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 1.0, 10))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    fun wantsToSwim(): Boolean {
        return level().isDay
    }

    override fun updateSwimming() {
        if (!this.level().isClientSide) {
            if (this.isEffectiveAi && this.isInWater && this.wantsToSwim() && !isSwimming) {
                isSwimming = true
                navigation.stop()
                navigation = waterNavigation
                moveControl = swimControl
                navigation.recomputePath()
                setPathfindingMalus(BlockPathTypes.WALKABLE, -1.0f)
            } else if (!wantsToSwim() && isSwimming) {
                isSwimming = false
                navigation.stop()
                navigation = groundNavigation
                moveControl = walkControl
                navigation.recomputePath()
                setPathfindingMalus(BlockPathTypes.WALKABLE, 1.0f)
            }
        }
    }


}