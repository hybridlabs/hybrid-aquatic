package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.config.HybridAquaticConfig
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Yellow
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.Animation
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import java.util.EnumSet
class FlashlightFishEntity(entityType: EntityType<out FlashlightFishEntity>, world: World) : HybridAquaticFishEntity(entityType, world) {
    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        return if (event.isMoving && !this.isSubmergedInWater) {
            event.controller.setAnimation(RawAnimation.begin().then("flop", Animation.LoopType.LOOP))
            PlayState.CONTINUE
        } else PlayState.STOP

    }
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)

        }
    }
}