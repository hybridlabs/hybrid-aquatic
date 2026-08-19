package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCephalopodEntity
import dev.hybridlabs.hapi.entity.base.aquatic.InkConfiguration
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

@Suppress("DEPRECATION", "unused")
class GiantSquidEntity(type: EntityType<out GiantSquidEntity>, world: Level) : BaseCephalopodEntity(type, world) {
    override fun getTargetConfig() = TARGET_CONFIG

    override val inkConfig: InkConfiguration = InkConfiguration.DEFAULT

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAPIEntityTags.SMALL_CREATURES,
                HAPIEntityTags.MEDIUM_CREATURES,
            ),
            listOf(
                HAPIEntityTags.ALL_SHARKS,
                HAPIEntityTags.WHALE,
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 24.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }

        fun canSpawn(
            type: EntityType<out GiantSquidEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            return pos.y in (seaLevel - 256)..(seaLevel - 48) && world.isWaterAt(pos)
        }
    }
}