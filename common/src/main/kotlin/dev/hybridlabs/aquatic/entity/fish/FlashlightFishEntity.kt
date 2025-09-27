package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

class FlashlightFishEntity(entityType: EntityType<out FlashlightFishEntity>, world: Level) :
    HybridAquaticSchoolingFishEntity(
        entityType, world,
        listOf(HybridAquaticEntityTags.NONE),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    var isLightOn: Boolean = true

    override fun tick() {
        super.tick()
        checkNearbyEntities()
    }

    private fun checkNearbyEntities() {
        val detectionRadius = 4.0
        val nearbyEntities =
            level().getEntitiesOfClass(LivingEntity::class.java, boundingBox.inflate(detectionRadius)) { it is Player }

        isLightOn = nearbyEntities.isEmpty()
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        fun canSpawn(
            type: EntityType<out FlashlightFishEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val nightSpawn = (world.seaLevel - 16)..(world.seaLevel - 1)
            val daySpawn = (world.seaLevel - 128)..(world.seaLevel - 24)

            val spawnY = if (!world.level.isDay) nightSpawn else daySpawn

            return pos.y in spawnY && world.isWaterAt(pos)
        }
    }
}