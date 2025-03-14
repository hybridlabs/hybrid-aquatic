package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier
import net.minecraft.world.World


class CarpEntity(entityType: EntityType<out CarpEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "koi_ai_goromo" to FishVariant.biomeVariant("koi_ai_goromo", listOf(HybridAquaticBiomeTags.CHERRY),
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        "koi_hajiro" to FishVariant.biomeVariant("koi_hajiro", listOf(HybridAquaticBiomeTags.CHERRY),
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        "koi_platinum" to FishVariant.biomeVariant("koi_platinum", listOf(HybridAquaticBiomeTags.CHERRY),
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        "koi_tancho" to FishVariant.biomeVariant("koi_tancho", listOf(HybridAquaticBiomeTags.CHERRY),
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        "common" to FishVariant.biomeVariant("common", listOf(HybridAquaticBiomeTags.RIVERS),
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
    ),
        listOf(
            HybridAquaticEntityTags.NONE),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK)) {

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "koi_ai_goromo" -> Identifier("hybrid-aquatic", "gameplay/koi")
            "koi_hajiro" -> Identifier("hybrid-aquatic", "gameplay/koi")
            "koi_platinum" -> Identifier("hybrid-aquatic", "gameplay/koi")
            "koi_tancho" -> Identifier("hybrid-aquatic", "gameplay/koi")
            "common" -> Identifier("hybrid-aquatic", "entity/carp")
            else -> super.getLootTableId()
        }
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }
    }
}