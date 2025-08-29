package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class TetraEntity(entityType: EntityType<out TetraEntity>, world: Level) :
    HybridAquaticSchoolingFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        ),
        variants = hashMapOf(
            "neon" to FishVariant.biomeVariant(
                "neon",
                listOf(
                    HybridAquaticBiomeTags.JUNGLE,
                    HybridAquaticBiomeTags.MANGROVES,
                    HybridAquaticBiomeTags.MARSHES,
                    HybridAquaticBiomeTags.TROPICAL_RIVERS
                ),
                ignore = listOf(FishVariant.Ignore.ANIMATION, FishVariant.Ignore.MODEL)
            ),
            "cave" to FishVariant.biomeVariant(
                "cave", listOf(HybridAquaticBiomeTags.CAVES),
                ignore = listOf(FishVariant.Ignore.ANIMATION, FishVariant.Ignore.MODEL)
            )
        )
    ) {

    override fun getMaxSpawnClusterSize(): Int {
        return 3
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }
}