package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class ShinerEntity(type: EntityType<out ShinerEntity>, world: Level) : HybridAquaticSchoolingFishEntity(type, world) {
    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HybridAquaticEntityTags.MEDIUM_PREY,
        HybridAquaticEntityTags.LARGE_PREY,
        HybridAquaticEntityTags.SHARK
    )

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }
}
