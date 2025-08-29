package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.tags.BiomeTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class SeaCucumberEntity(entityType: EntityType<out SeaCucumberEntity>, world: Level) :
    HybridAquaticCritterEntity(
        entityType, world, variants = hashMapOf(
            "sea_pig" to CritterVariant.biomeVariant(
                "sea_pig", listOf(BiomeTags.IS_DEEP_OCEAN),
                ignore = listOf(CritterVariant.Ignore.ANIMATION)
            ),
            "black" to CritterVariant.biomeVariant(
                "black",
                listOf(
                    HybridAquaticBiomeTags.TROPICAL_OCEANS,
                    HybridAquaticBiomeTags.TEMPERATE_OCEANS,
                    HybridAquaticBiomeTags.REEF
                ),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
            "red" to CritterVariant.biomeVariant(
                "red",
                listOf(
                    HybridAquaticBiomeTags.TROPICAL_OCEANS,
                    HybridAquaticBiomeTags.TEMPERATE_OCEANS,
                    HybridAquaticBiomeTags.REEF
                ),
                ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)
            ),
        )
    ) {

    override fun remove(reason: RemovalReason) {
        if (!level().isClientSide && this.isDeadOrDying) {
            if (level().random.nextInt(4) == 0) {
                val text = this.customName
                val isNoAi = this.isNoAi
                val spawnCount = 1 + level().random.nextInt(2)

                for (l in 0 until spawnCount) {
                    val offsetX = (level().random.nextFloat() - 0.5f) * 2.0f
                    val offsetZ = (level().random.nextFloat() - 0.5f) * 2.0f
                    val pearlfishEntity = HybridAquaticEntityTypes.PEARLFISH.get().create(level())

                    pearlfishEntity?.let {
                        it.customName = text
                        it.isNoAi = isNoAi
                        it.isInvulnerable = this.isInvulnerable
                        it.moveTo(
                            this.x + offsetX,
                            this.y + 0.5,
                            this.z + offsetZ,
                            level().random.nextFloat() * 360.0f,
                            0.0f
                        )

                        level().addFreshEntity(it)
                    }
                }
            }
        }

        super.remove(reason)
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
            event.controller.setAnimation(IDLE_ANIMATION)
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