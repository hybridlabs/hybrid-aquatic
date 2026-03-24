package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

@Suppress("DEPRECATION", "unused")
class GiantSquidEntity(type: EntityType<out GiantSquidEntity>, world: Level) : HybridAquaticCephalopodEntity(type, world) {
    override fun getTargetConfig() = TARGET_CONFIG

    override val inkConfig: InkConfiguration = InkConfiguration.DEFAULT

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HybridAquaticEntityTags.SMALL_CREATURES,
                HybridAquaticEntityTags.MEDIUM_CREATURES,
            ),
            listOf(
                HybridAquaticEntityTags.ALL_SHARKS,
                HybridAquaticEntityTags.WHALE,
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

            return pos.y in (world.level.chunkSource.generator.seaLevel - 256)..(world.level.chunkSource.generator.seaLevel - 48) && world.isWaterAt(pos)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}