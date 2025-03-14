package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier
import net.minecraft.world.World

class LobsterEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: World) :
    HybridAquaticCrustaceanEntity(
        entityType, world, false, variants = hashMapOf(
            "american" to CrustaceanVariant.biomeVariant("american", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
            "california_spiny" to CrustaceanVariant.biomeVariant("california_spiny", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
            "ornate_spiny" to CrustaceanVariant.biomeVariant("ornate_spiny", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
            "regal_slipper" to CrustaceanVariant.biomeVariant("regal_slipper", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.ANIMATION)),
            )
    ) {

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "american" -> Identifier("hybrid-aquatic", "gameplay/clawed_lobster")
            "california_spiny" -> Identifier("hybrid-aquatic", "gameplay/clawless_lobster")
            "ornate_spiny" -> Identifier("hybrid-aquatic", "gameplay/clawless_lobster")
            "regal_slipper" -> Identifier("hybrid-aquatic", "gameplay/clawless_lobster")
            else -> super.getLootTableId()
        }
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }
    }
    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}