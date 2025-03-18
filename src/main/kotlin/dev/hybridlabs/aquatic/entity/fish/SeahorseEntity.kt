package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier
import net.minecraft.world.World

class SeahorseEntity(entityType: EntityType<out SeahorseEntity>, world: World) :
    HybridAquaticFishEntity(
        entityType, world, variants = hashMapOf(
            "common" to FishVariant.biomeVariant(
                "common", listOf(HybridAquaticBiomeTags.REEF),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
            "big_belly" to FishVariant.biomeVariant(
                "big_belly", listOf(HybridAquaticBiomeTags.REEF),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
            "thorny" to FishVariant.biomeVariant(
                "thorny", listOf(HybridAquaticBiomeTags.REEF),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
            "pygmy" to FishVariant.biomeVariant(
                "pygmy", listOf(HybridAquaticBiomeTags.REEF),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
        ),
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.SMALL_PREY,
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.CEPHALOPOD,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    public override fun getLootTableId(): Identifier {
        return Identifier("hybrid-aquatic", "entities/seahorse")
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}