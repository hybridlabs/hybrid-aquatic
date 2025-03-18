package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class SeaCucumberEntity(entityType: EntityType<out SeaCucumberEntity>, world: World) :
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
        if (!world.isClient && this.isDead) {
            if (world.random.nextInt(4) == 0) {
                val text = this.customName
                val isAiDisabled = this.isAiDisabled
                val spawnCount = 1 + world.random.nextInt(2)

                for (l in 0 until spawnCount) {
                    val offsetX = (world.random.nextFloat() - 0.5f) * 2.0f
                    val offsetZ = (world.random.nextFloat() - 0.5f) * 2.0f
                    val pearlfishEntity = HybridAquaticEntityTypes.PEARLFISH.create(world)

                    pearlfishEntity?.let {
                        it.customName = text
                        it.isAiDisabled = isAiDisabled
                        it.isInvulnerable = this.isInvulnerable
                        it.refreshPositionAndAngles(
                            this.x + offsetX,
                            this.y + 0.5,
                            this.z + offsetZ,
                            world.random.nextFloat() * 360.0f,
                            0.0f
                        )

                        world.spawnEntity(it)
                    }
                }
            }
        }

        super.remove(reason)
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

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater) {
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