package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level


class CarpEntity(entityType: EntityType<out CarpEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world, variants = hashMapOf(
            "koi_ai_goromo" to FishVariant.biomeVariant(
                "koi_ai_goromo", listOf(HybridAquaticBiomeTags.CHERRY),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "koi_hajiro" to FishVariant.biomeVariant(
                "koi_hajiro", listOf(HybridAquaticBiomeTags.CHERRY),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "koi_platinum" to FishVariant.biomeVariant(
                "koi_platinum", listOf(HybridAquaticBiomeTags.CHERRY),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "koi_tancho" to FishVariant.biomeVariant(
                "koi_tancho", listOf(HybridAquaticBiomeTags.CHERRY),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "common" to FishVariant.biomeVariant(
                "common", listOf(HybridAquaticBiomeTags.RIVERS),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
        ),
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    public override fun getDefaultLootTable(): ResourceLocation {
        return when (this.variant?.variantName) {
            "koi_ai_goromo" -> ResourceLocation("hybrid-aquatic", "gameplay/koi")
            "koi_hajiro" -> ResourceLocation("hybrid-aquatic", "gameplay/koi")
            "koi_platinum" -> ResourceLocation("hybrid-aquatic", "gameplay/koi")
            "koi_tancho" -> ResourceLocation("hybrid-aquatic", "gameplay/koi")
            "common" -> ResourceLocation("hybrid-aquatic", "entity/carp")
            else -> super.getDefaultLootTable()
        }
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
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