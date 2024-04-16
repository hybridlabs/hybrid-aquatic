package dev.hybridlabs.aquatic.entity.critter

import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class StarfishEntity(entityType: EntityType<out StarfishEntity>, world: World) :
    HybridAquaticCritterEntity(entityType, world, 7) {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100.0)
        }
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater) {
            event.controller.setAnimation(WALK_ANIMATION)
        }
        return PlayState.CONTINUE
    }

    override fun tick() {
        super.tick()
        if (!isWet) {
            this.speed = 0.01F
        }
    }
    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}