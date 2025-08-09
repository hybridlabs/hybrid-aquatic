package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class NeedlefishEntity(entityType: EntityType<out NeedlefishEntity>, world: World) :
    HybridAquaticSchoolingFishEntity(entityType, world, HybridAquaticEntityTags.NEEDLEFISH_PREY, HybridAquaticEntityTags.NEEDLEFISH_PREDATOR) {

    override fun getLimitPerChunk(): Int {
        return 4
    }

        companion object {
            fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
    override fun speedModifier(): Double {
        return 0.005
    }
}
