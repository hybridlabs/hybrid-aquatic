package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class LobsterEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: Level) :
    HybridAquaticCrustaceanEntity(
        entityType, world, false, variants = hashMapOf(
            "american" to CrustaceanVariant.biomeVariant(
                "american", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "california_spiny" to CrustaceanVariant.biomeVariant(
                "california_spiny", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "ornate_spiny" to CrustaceanVariant.biomeVariant(
                "ornate_spiny", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)
            ),
            "regal_slipper" to CrustaceanVariant.biomeVariant(
                "regal_slipper", HybridAquaticBiomeTags.REEF,
                ignore = listOf(CrustaceanVariant.Ignore.ANIMATION)
            ),
        )
    ) {

    public override fun getDefaultLootTable(): ResourceLocation {
        return when (this.variant?.variantName) {
            "american" -> ResourceLocation("hybrid-aquatic", "gameplay/clawed_lobster")
            "california_spiny" -> ResourceLocation("hybrid-aquatic", "gameplay/clawless_lobster")
            "ornate_spiny" -> ResourceLocation("hybrid-aquatic", "gameplay/clawless_lobster")
            "regal_slipper" -> ResourceLocation("hybrid-aquatic", "gameplay/clawless_lobster")
            else -> super.getDefaultLootTable()
        }
    }

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