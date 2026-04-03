package dev.hybridlabs.aquatic.entity.crustacean

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
class GhostCrabEntity(entityType: EntityType<out HACrustaceanEntity>, world: Level) :
    HACrustaceanEntity(entityType, world, true) {

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
            type: EntityType<out GhostCrabEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            return pos.y <= seaLevel + 4 &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isEmptyBlock(pos) &&
                    !world.level.isDay &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }
    }
}