package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.tags.BiomeTags
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class StarfishEntity(entityType: EntityType<out StarfishEntity>, world: Level) :
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
                "medium_knobbed_blue",
                listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_knobbed_green" to CritterVariant.biomeVariant(
                "medium_knobbed_green",
                listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_knobbed_orange" to CritterVariant.biomeVariant(
                "medium_knobbed_orange",
                listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_knobbed_purple" to CritterVariant.biomeVariant(
                "medium_knobbed_purple",
                listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_knobbed_red" to CritterVariant.biomeVariant(
                "medium_knobbed_red",
                listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "medium_knobbed_yellow" to CritterVariant.biomeVariant(
                "medium_knobbed_yellow",
                listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "crown_of_thorns" to CritterVariant.biomeVariant("crown_of_thorns", listOf(HybridAquaticBiomeTags.REEF)),
            "brittlestar_black" to CritterVariant.biomeVariant("brittlestar_black", listOf(BiomeTags.IS_DEEP_OCEAN)),
            "brittlestar_yellow" to CritterVariant.biomeVariant("brittlestar_yellow", listOf(BiomeTags.IS_DEEP_OCEAN)),
            "brittlestar_white" to CritterVariant.biomeVariant("brittlestar_white", listOf(BiomeTags.IS_DEEP_OCEAN)),
        )
    ) {

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isUnderWater) {
            event.controller.setAnimation(WALK_ANIMATION)
        }
        return PlayState.CONTINUE
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (super.hurt(source, amount)) {

            val attacker = source.directEntity
            if (this.variant?.variantName == "crown_of_thorns" && attacker is LivingEntity && attacker.mainHandItem.isEmpty) {
                attacker.hurt(this.damageSources().thorns(this), 2.0f)
                attacker.addEffect(MobEffectInstance(MobEffects.POISON, 200, 1))
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