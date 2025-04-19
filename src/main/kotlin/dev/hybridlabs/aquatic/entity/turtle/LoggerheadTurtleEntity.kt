package dev.hybridlabs.aquatic.entity.turtle

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World

class LoggerheadTurtleEntity(entityType: EntityType<out LoggerheadTurtleEntity>, world: World) :
    HybridAquaticTurtleEntity(entityType, world,
        listOf(
            HybridAquaticEntityTags.NONE),
        listOf(
            HybridAquaticEntityTags.SHARK),
        HybridAquaticBlocks.LOGGERHEAD_TURTLE_EGG
    ) {

    override fun createChild(world: ServerWorld, entity: PassiveEntity): PassiveEntity? {
        return HybridAquaticEntityTypes.LOGGERHEAD_TURTLE.create(world)
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }
    }
}