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

class StarfishEntity(entityType: EntityType<out StarfishEntity>, world: World) :
    HybridAquaticCritterEntity(entityType, world, variants = hashMapOf(
        "blue" to CritterVariant.biomeVariant("blue", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "orange" to CritterVariant.biomeVariant("orange", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "green" to CritterVariant.biomeVariant("green", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "purple" to CritterVariant.biomeVariant("purple", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "red" to CritterVariant.biomeVariant("red", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "yellow" to CritterVariant.biomeVariant("yellow", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_blue" to CritterVariant.biomeVariant("medium_blue", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_green" to CritterVariant.biomeVariant("medium_green", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_orange" to CritterVariant.biomeVariant("medium_orange", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_purple" to CritterVariant.biomeVariant("medium_purple", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_red" to CritterVariant.biomeVariant("medium_red", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_yellow" to CritterVariant.biomeVariant("medium_yellow", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_knobbed_blue" to CritterVariant.biomeVariant("medium_knobbed_blue", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_knobbed_green" to CritterVariant.biomeVariant("medium_knobbed_green", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_knobbed_orange" to CritterVariant.biomeVariant("medium_knobbed_orange", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_knobbed_purple" to CritterVariant.biomeVariant("medium_knobbed_purple", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_knobbed_red" to CritterVariant.biomeVariant("medium_knobbed_red", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "medium_knobbed_yellow" to CritterVariant.biomeVariant("medium_knobbed_yellow", HybridAquaticBiomeTags.WARM_OCEANS,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "crown_of_thorns" to CritterVariant.biomeVariant("crown_of_thorns", HybridAquaticBiomeTags.REEF),
        "brittlestar_black" to CritterVariant.biomeVariant("brittlestar_black", HybridAquaticBiomeTags.ALL_DEEP_OCEANS),
        "brittlestar_yellow" to CritterVariant.biomeVariant("brittlestar_yellow", HybridAquaticBiomeTags.ALL_DEEP_OCEANS),
        "brittlestar_white" to CritterVariant.biomeVariant("brittlestar_white", HybridAquaticBiomeTags.ALL_DEEP_OCEANS),)) {

        companion object {
            fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 1.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.1)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 100.0)
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
