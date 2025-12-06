package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class MorayEelEntity(entityType: EntityType<out MorayEelEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.SMALL_PREY,
            HybridAquaticEntityTags.CRUSTACEAN,
            HybridAquaticEntityTags.CEPHALOPOD
        ),
        listOf(
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }
}