package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.HybridAquatic.MOD_ID
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.ANEMONE
import dev.hybridlabs.aquatic.config.HybridAquaticConfig
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.Animation
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import java.util.*

class BullSharkEntity(entityType: EntityType<out BullSharkEntity>, world: World) :
    HybridAquaticShark(entityType, world, PREY_TAG, false) {
    companion object {
        val PREY_TAG = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier(MOD_ID, "prey/bull_shark"))!!
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, HybridAquaticConfig.CLOWNFISH_HEALTH.toDouble())
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)

        }
    }
}
