package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.hapi.tag.HAPIBiomeTags
import dev.hybridlabs.aquatic.world.WorldHelper
import dev.hybridlabs.hapi.entity.water.base.BaseCrustaceanEntity
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

@Suppress("UNUSED_PARAMETER", "DEPRECATION")
class SpiderCrabEntity(entityType: EntityType<out SpiderCrabEntity>, world: Level) :
    BaseCrustaceanEntity(entityType, world, false) {

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
            type: EntityType<out SpiderCrabEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val shallowSpawn = (seaLevel - 24)..(seaLevel - 4)
            val deepSpawn = (seaLevel - 256)..(seaLevel - 25)

            val fullMoon = world.moonPhase == 0
            val newMoon = world.moonPhase == 4

            val spawnY = if ((fullMoon || newMoon) && !world.level.isDay) shallowSpawn else deepSpawn

            return pos.y in spawnY &&
                    world.isWaterAt(pos) &&
                    world.getBlockState(pos.below()).isSolid &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }
    }

    override fun getMaxSize(): Int {
        val level = this.level()
        val biome = level.getBiome(this.blockPosition())

        return if (biome.`is`(HAPIBiomeTags.ALL_TRENCHES)) {
            8
        } else {
            3
        }
    }

    override fun getMinSize(): Int {
        val level = this.level()
        val biome = level.getBiome(this.blockPosition())

        return if (biome.`is`(HAPIBiomeTags.ALL_TRENCHES)) {
            0
        } else {
            -3
        }
    }
}