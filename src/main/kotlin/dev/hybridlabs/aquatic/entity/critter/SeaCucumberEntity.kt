package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.animation.PlayState

class SeaCucumberEntity(entityType: EntityType<out SeaCucumberEntity>, world: World) :
    HybridAquaticCritterEntity(entityType, world, variants = hashMapOf(
        "sea_pig" to CritterVariant.biomeVariant("sea_pig", HybridAquaticBiomeTags.ALL_DEEP_OCEANS,
            ignore = listOf(CritterVariant.Ignore.ANIMATION)),
        "black" to CritterVariant.biomeVariant("black", HybridAquaticBiomeTags.ALL_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "red" to CritterVariant.biomeVariant("red", HybridAquaticBiomeTags.ALL_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        )) {

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 2.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.1)
        }
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater) {
            event.controller.setAnimation(IDLE_ANIMATION)
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
