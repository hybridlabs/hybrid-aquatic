package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.entity.ai.control.OtterFloatControl
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.tags.FluidTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.level.Level

class OtterEntity(entityType: EntityType<out OtterEntity>, world: Level) :
    HybridAquaticMammalEntity(entityType, world,
        listOf(
            HybridAquaticEntityTags.CRUSTACEAN,
            HybridAquaticEntityTags.SMALL_PREY),
        listOf(
            HybridAquaticEntityTags.NONE)) {

    init {
        moveControl = OtterFloatControl(this)
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    fun isBelowWaterline(): Boolean {
        return this.isUnderWater || this.getFluidHeight(FluidTags.WATER) > this.getWaterline()
    }

    private fun getWaterline(): Float {
        return 0.4f
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }
    }
}