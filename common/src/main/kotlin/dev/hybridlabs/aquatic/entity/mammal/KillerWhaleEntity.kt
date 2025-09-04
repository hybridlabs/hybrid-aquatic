package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.world.World

class KillerWhaleEntity(entityType: EntityType<out KillerWhaleEntity>, world: World) :
    HybridAquaticDolphinEntity(entityType, world,
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY),
        listOf(
            HybridAquaticEntityTags.NONE)) {

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun getMaxSize(): Int {
        return 3
    }

    override fun getMinSize(): Int {
        return -3
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 80.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
        }
    }
}