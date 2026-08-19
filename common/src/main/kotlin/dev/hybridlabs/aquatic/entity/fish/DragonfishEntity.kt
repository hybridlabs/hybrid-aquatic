package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

@Suppress("UNUSED_PARAMETER", "DEPRECATION")
class DragonfishEntity(type: EntityType<out DragonfishEntity>, world: Level) :
    BaseFishEntity(type, world) {

    override fun getTargetConfig() = TARGET_CONFIG

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAPIEntityTags.SMALL_CREATURES
            ),
            listOf(
                HAPIEntityTags.MEDIUM_CREATURES,
                HAPIEntityTags.LARGE_CREATURES,
                HAPIEntityTags.ALL_SHARKS
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        fun canSpawn(
            type: EntityType<out DragonfishEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val nightSpawn = (seaLevel - 16)..<seaLevel
            val daySpawn = (seaLevel - 256)..(seaLevel - 48)

            val spawnY = if (!world.level.isDay) nightSpawn else daySpawn

            return pos.y in spawnY && world.isWaterAt(pos)
        }
    }
}