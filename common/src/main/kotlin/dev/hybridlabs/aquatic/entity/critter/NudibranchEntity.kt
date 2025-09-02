package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class NudibranchEntity(entityType: EntityType<out NudibranchEntity>, world: Level) :
    HybridAquaticCritterEntity(
        entityType, world, variants = hashMapOf(
            "pyjama" to CritterVariant.biomeVariant(
                "pyjama", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "bullock" to CritterVariant.biomeVariant(
                "bullock", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "sagami" to CritterVariant.biomeVariant(
                "sagami", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "festiva" to CritterVariant.biomeVariant(
                "festiva", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "magnificent" to CritterVariant.biomeVariant(
                "magnificent", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "kubaryana" to CritterVariant.biomeVariant(
                "kubaryana", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "yonowae" to CritterVariant.biomeVariant(
                "yonowae", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "kuniei" to CritterVariant.biomeVariant(
                "kuniei", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "baba" to CritterVariant.biomeVariant(
                "baba", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
        )
    ) {

    public override fun getDefaultLootTable(): ResourceLocation {
        return ResourceLocation("hybrid-aquatic", "entities/nudibranch")
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 2.0)
        }
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isUnderWater) {
            event.controller.setAnimation(WALK_ANIMATION)
        } else {
            event.controller.setAnimation(FLOP_ANIMATION)
        }
        return PlayState.CONTINUE
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}