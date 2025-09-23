package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.StayDeepGoal
import dev.hybridlabs.aquatic.entity.ai.goal.StayNearSurfaceGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

class OarfishEntity(entityType: EntityType<out OarfishEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.CEPHALOPOD,
            HybridAquaticEntityTags.SMALL_PREY
        ),
        listOf(
            HybridAquaticEntityTags.SHARK
        )
    ) {

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        super.registerGoals()
        if (level().isDay) {
            goalSelector.addGoal(1, StayDeepGoal(this, 1.0, 1, 16))
        } else {
            goalSelector.addGoal(1, StayNearSurfaceGoal(this, 1.0, 1, 4))
        }
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        fun canSpawn(
            type: EntityType<out OarfishEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val shallowSpawn = (world.seaLevel - 12)..(world.seaLevel - 2)
            val deepSpawn = (world.seaLevel - 128)..(world.seaLevel - 24)

            val spawnY = if (world.level.isThundering) shallowSpawn else deepSpawn

            return pos.y in spawnY && world.isWaterAt(pos)
        }
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }
}