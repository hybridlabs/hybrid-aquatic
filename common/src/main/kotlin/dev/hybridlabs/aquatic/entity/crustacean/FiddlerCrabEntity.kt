package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class FiddlerCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: Level) :
    HybridAquaticCrustaceanEntity(
        entityType, world, true, variants = hashMapOf(
            "blue" to CrustaceanVariant.biomeVariant(
                "blue", HybridAquaticBiomeTags.SWAMP,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "red" to CrustaceanVariant.biomeVariant(
                "red", HybridAquaticBiomeTags.SWAMP,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "purple" to CrustaceanVariant.biomeVariant(
                "purple", HybridAquaticBiomeTags.SWAMP,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            )
        )
    ) {

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}