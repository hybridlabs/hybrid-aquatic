package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSharkEntity
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class LanternSharkEntity(type: EntityType<out LanternSharkEntity>, world: Level) :
    BaseSharkEntity(type, world) {

    override fun getTargetConfig() = TARGET_CONFIG

    override val isPassive: Boolean = false
    override val closePlayerAttack: Boolean = false

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAPIEntityTags.SMALL_CREATURES
            ),
            listOf(
                HAPIEntityTags.MEDIUM_CREATURES,
                HAPIEntityTags.LARGE_CREATURES,
                HAPIEntityTags.MEDIUM_SHARK,
                HAPIEntityTags.LARGE_SHARK
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }
}
