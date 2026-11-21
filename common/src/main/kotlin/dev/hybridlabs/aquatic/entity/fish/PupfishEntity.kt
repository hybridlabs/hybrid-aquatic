package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSchoolingFishEntity
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

class PupfishEntity(type: EntityType<out PupfishEntity>, world: Level) :
    BaseSchoolingFishEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HAPIEntityTags.MEDIUM_CREATURES,
        HAPIEntityTags.LARGE_CREATURES,
        HAPIEntityTags.ALL_SHARKS
    )

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        fun canSpawn(
            type: EntityType<out PupfishEntity>,
            world: ServerLevelAccessor,
            reason: EntitySpawnReason,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {

            return pos.y in world.minY ..< world.maxY &&
                    world.isWaterAt(pos)
        }
    }
}
