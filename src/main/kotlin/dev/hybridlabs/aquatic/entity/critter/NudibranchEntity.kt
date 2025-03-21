package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier
import net.minecraft.world.World
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.event.predicate.AnimationEvent

class NudibranchEntity(entityType: EntityType<out NudibranchEntity>, world: World) :
    HybridAquaticCritterEntity(entityType, world, variants = hashMapOf(
        "pyjama" to CritterVariant.biomeVariant("pyjama", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "bullock" to CritterVariant.biomeVariant("bullock", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "sagami" to CritterVariant.biomeVariant("sagami", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "festiva" to CritterVariant.biomeVariant("festiva", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "magnificent" to CritterVariant.biomeVariant("magnificent", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "kubaryana" to CritterVariant.biomeVariant("kubaryana", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "yonowae" to CritterVariant.biomeVariant("yonowae", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "kuniei" to CritterVariant.biomeVariant("kuniei", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "baba" to CritterVariant.biomeVariant("baba", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        )) {

    public override fun getLootTableId(): Identifier {
        return Identifier("hybrid-aquatic", "entities/nudibranch")
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2.0)
        }
    }

    override fun <E : IAnimatable> predicate(event: AnimationEvent<E>): PlayState {
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
