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

class NudibranchEntity(entityType: EntityType<out NudibranchEntity>, world: World) :
    HybridAquaticCritterEntity(entityType, world, variants = hashMapOf(
        "pyjama" to CritterVariant.biomeVariant("pyjama", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "bullock" to CritterVariant.biomeVariant("bullock", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "sagami" to CritterVariant.biomeVariant("sagami", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "festiva" to CritterVariant.biomeVariant("festiva", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "magnificent" to CritterVariant.biomeVariant("magnificent", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "kubaryana" to CritterVariant.biomeVariant("kubaryana", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "yonowae" to CritterVariant.biomeVariant("yonowae", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "kuniei" to CritterVariant.biomeVariant("kuniei", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "baba" to CritterVariant.biomeVariant("baba", HybridAquaticBiomeTags.REEF,
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
            event.controller.setAnimation(WALK_ANIMATION)
        } else {
            event.controller.setAnimation(FLOP_ANIMATION)
        }
        return PlayState.CONTINUE
    }
    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}
