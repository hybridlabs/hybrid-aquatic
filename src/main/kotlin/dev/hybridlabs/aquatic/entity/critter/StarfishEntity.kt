package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class StarfishEntity(entityType: EntityType<out StarfishEntity>, world: World) :
    HybridAquaticCritterEntity(
        entityType, world, variants = hashMapOf(
            "blue" to CritterVariant.biomeVariant(
                "blue",
                listOf(
                    HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS,
                    HybridAquaticBiomeTags.REEF,
                    HybridAquaticBiomeTags.SANDY_BEACHES
                ),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "orange" to CritterVariant.biomeVariant(
                "orange",
                listOf(
                    HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS,
                    HybridAquaticBiomeTags.REEF,
                    HybridAquaticBiomeTags.SANDY_BEACHES
                ),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "green" to CritterVariant.biomeVariant(
                "green",
                listOf(
                    HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS,
                    HybridAquaticBiomeTags.REEF,
                    HybridAquaticBiomeTags.SANDY_BEACHES
                ),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "purple" to CritterVariant.biomeVariant(
                "purple",
                listOf(
                    HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS,
                    HybridAquaticBiomeTags.REEF,
                    HybridAquaticBiomeTags.SANDY_BEACHES
                ),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "red" to CritterVariant.biomeVariant(
                "red",
                listOf(
                    HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS,
                    HybridAquaticBiomeTags.REEF,
                    HybridAquaticBiomeTags.SANDY_BEACHES
                ),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "yellow" to CritterVariant.biomeVariant(
                "yellow",
                listOf(
                    HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS,
                    HybridAquaticBiomeTags.REEF,
                    HybridAquaticBiomeTags.SANDY_BEACHES
                ),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_blue" to CritterVariant.biomeVariant(
                "medium_blue", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_green" to CritterVariant.biomeVariant(
                "medium_green", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_orange" to CritterVariant.biomeVariant(
                "medium_orange", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_purple" to CritterVariant.biomeVariant(
                "medium_purple", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_red" to CritterVariant.biomeVariant(
                "medium_red", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_yellow" to CritterVariant.biomeVariant(
                "medium_yellow", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_knobbed_blue" to CritterVariant.biomeVariant(
                "medium_knobbed_blue", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_knobbed_green" to CritterVariant.biomeVariant(
                "medium_knobbed_green", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_knobbed_orange" to CritterVariant.biomeVariant(
                "medium_knobbed_orange", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_knobbed_purple" to CritterVariant.biomeVariant(
                "medium_knobbed_purple", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_knobbed_red" to CritterVariant.biomeVariant(
                "medium_knobbed_red", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_knobbed_yellow" to CritterVariant.biomeVariant(
                "medium_knobbed_yellow", listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "crown_of_thorns" to CritterVariant.biomeVariant("crown_of_thorns", listOf(HybridAquaticBiomeTags.REEF)),
            "brittlestar_black" to CritterVariant.biomeVariant("brittlestar_black", listOf(BiomeTags.IS_DEEP_OCEAN)),
            "brittlestar_yellow" to CritterVariant.biomeVariant("brittlestar_yellow", listOf(BiomeTags.IS_DEEP_OCEAN)),
            "brittlestar_white" to CritterVariant.biomeVariant("brittlestar_white", listOf(BiomeTags.IS_DEEP_OCEAN)),
        )
    ) {

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
        }
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater) {
            event.controller.setAnimation(WALK_ANIMATION)
        }
        return PlayState.CONTINUE
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        if (super.damage(source, amount)) {

            val attacker = source.attacker
            if (this.variant?.variantName == "crown_of_thorns" && attacker is LivingEntity && attacker.mainHandStack.isEmpty) {
                attacker.damage(this.damageSources.thorns(this), 2.0f)
                attacker.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 200, 1))
            }

            return true
        }

        return false
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}