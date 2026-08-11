package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.tag.HAEntityTags
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.water.base.BaseCephalopodEntity
import dev.hybridlabs.hapi.entity.water.base.InkConfiguration
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

@Suppress("DEPRECATION", "UNUSED_PARAMETER")
class FireflySquidEntity(type: EntityType<out FireflySquidEntity>, world: Level) : BaseCephalopodEntity(type, world) {

    override fun getTargetConfig() = TARGET_CONFIG

    override val inkConfig: InkConfiguration = InkConfiguration.GLOW

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAEntityTags.ALL_CRUSTACEANS
            ),
            listOf(
                HAEntityTags.ALL_SHARKS
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }

        fun canSpawn(
            type: EntityType<out FireflySquidEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val nightSpawn = (seaLevel - 16)..<seaLevel
            val daySpawn = (seaLevel - 256)..(seaLevel - 48)

            val newMoon = world.moonPhase == 4

            val spawnY = if ((newMoon) && !world.level.isDay) nightSpawn else daySpawn

            return pos.y in spawnY && world.isWaterAt(pos)
        }
    }
}
