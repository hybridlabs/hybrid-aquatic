package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

@Suppress("DEPRECATION", "UNUSED_PARAMETER")
class FireflySquidEntity(entityType: EntityType<out FireflySquidEntity>, world: Level) :
    HybridAquaticCephalopodEntity(
        entityType,
        world,
        HybridAquaticEntityTags.CRUSTACEAN,
        listOf(
            HybridAquaticEntityTags.SHARK
        ),
        true,
        true
    ) {

    companion object {
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
            val nightSpawn = (world.seaLevel - 16)..<world.seaLevel
            val daySpawn = (world.seaLevel - 128)..(world.seaLevel - 48)

            val newMoon = world.moonPhase == 4

            val spawnY = if ((newMoon) && !world.level.isDay) nightSpawn else daySpawn

            return pos.y in spawnY && world.isWaterAt(pos)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}