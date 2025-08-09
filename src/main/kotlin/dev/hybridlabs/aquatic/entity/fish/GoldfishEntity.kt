package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import net.minecraft.world.World

class GoldfishEntity(entityType: EntityType<out GoldfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "shubunkin" to FishVariant.biomeVariant("shubunkin", HybridAquaticBiomeTags.CHERRY,
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        ),
        HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NONE) {

    public override fun getLootTableId(): Identifier? {
        return when (this.variant.variantName) {
            "shubunkin" -> Identifier.of("hybrid-aquatic", "gameplay/goldfish")
            else -> super.getLootTableId()
        }
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0)
        }
    }
}
