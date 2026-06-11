package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.base.HACrustaceanEntity
import dev.hybridlabs.aquatic.world.WorldHelper
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

@Suppress("UNUSED_PARAMETER", "DEPRECATION")
class HorseshoeCrabEntity(entityType: EntityType<out HACrustaceanEntity>, world: Level) :
    HACrustaceanEntity(entityType, world, false) {
    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        fun canSpawn(
            type: EntityType<out HorseshoeCrabEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val shallowSpawn = (seaLevel - 8)..(seaLevel + 4)
            val deepSpawn = (seaLevel - 256)..(seaLevel - 16)

            val fullMoon = world.moonPhase == 0
            val newMoon = world.moonPhase == 4

            val spawnY = if ((fullMoon || newMoon) && !world.level.isDay) shallowSpawn else deepSpawn

            return pos.y in spawnY &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isWaterAt(pos) &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}