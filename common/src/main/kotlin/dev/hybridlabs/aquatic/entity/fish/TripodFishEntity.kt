package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.base.HybridAquaticWaterAnimal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

class TripodFishEntity(type: EntityType<out TripodFishEntity>, world: Level) :
    HybridAquaticFishEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HybridAquaticEntityTags.MEDIUM_CREATURES,
        HybridAquaticEntityTags.LARGE_CREATURES,
        HybridAquaticEntityTags.ALL_SHARKS
    )

    override fun createNavigation(level: Level): PathNavigation {
        super.createNavigation(level)

        moveControl = BottomDwellerMoveControl(this, 85, 5, 0.02F, 0.1F, false)
        lookControl = SmoothSwimmingLookControl(this, 10)

        return WaterBoundPathNavigation(this, level)
    }

    override fun canSit(): Boolean = true

    override fun registerGoals() {
        goalSelector.addGoal(3, BottomDwellerSwimmingGoal(this, 1.0, 10))
        super.registerGoals()
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 1.1f
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }

        fun canSpawn(
            type: EntityType<out HybridAquaticWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            return pos.y in (world.level.chunkSource.generator.seaLevel - 256)..(world.level.chunkSource.generator.seaLevel - 72) &&
                    world.isWaterAt(pos)
        }
    }
}