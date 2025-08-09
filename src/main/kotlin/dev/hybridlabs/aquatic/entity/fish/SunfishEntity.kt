package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import net.minecraft.world.World

class SunfishEntity(entityType: EntityType<out SunfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "ocean" to FishVariant.biomeVariant("ocean", HybridAquaticBiomeTags.DEEP_WARM_OCEANS),
        "hoodwinker" to FishVariant.biomeVariant("hoodwinker", HybridAquaticBiomeTags.WARM_OCEANS),
        "sharptail" to FishVariant.biomeVariant("sharptail", HybridAquaticBiomeTags.OCEAN)),
        HybridAquaticEntityTags.SUNFISH_PREY, HybridAquaticEntityTags.SUNFISH_PREDATOR) {

    override fun getLimitPerChunk(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 10.0)
        }
    }
}
