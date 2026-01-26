package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

@Suppress("DEPRECATION", "UNUSED_PARAMETER")
class NautilusEntity(type: EntityType<out NautilusEntity>, world: Level) : HybridAquaticCephalopodEntity(type, world) {

    override val predator: List<TagKey<EntityType<*>>> = listOf(
        HybridAquaticEntityTags.SHARK
    )

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
            type: EntityType<out NautilusEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val nightSpawn = (world.seaLevel - 24)..(world.seaLevel - 8)
            val daySpawn = (world.seaLevel - 128)..(world.seaLevel - 48)

            val spawnY = if (!world.level.isDay) nightSpawn else daySpawn

            return pos.y in spawnY && world.isWaterAt(pos)
        }
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.SHULKER_CLOSE
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.SHULKER_HURT_CLOSED
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}
