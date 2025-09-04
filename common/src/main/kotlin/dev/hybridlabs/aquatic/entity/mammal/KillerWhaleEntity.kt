package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.world.World

class KillerWhaleEntity(entityType: EntityType<out KillerWhaleEntity>, world: Level) :
    HybridAquaticDolphinEntity(entityType, world,
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY),
        listOf(
            HybridAquaticEntityTags.NONE)) {

    override fun getSpawnClusterSize(): Int {
        return 2
    }

    override fun getMaxSize(): Int {
        return 3
    }

    override fun getMinSize(): Int {
        return -3
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 80.0)
                .add(Attributes.MOVEMENT_SPEED, 1.5)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }
    }
}