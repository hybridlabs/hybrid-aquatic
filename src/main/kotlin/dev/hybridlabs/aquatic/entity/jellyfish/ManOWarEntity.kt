package dev.hybridlabs.aquatic.entity.jellyfish

import dev.hybridlabs.aquatic.entity.Floater
import dev.hybridlabs.aquatic.entity.ai.FloatMoveControl
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ManOWarEntity(entityType: EntityType<out ManOWarEntity>, world: World) :
    HybridAquaticJellyfishEntity(entityType, world, true, 1), Floater {

    override fun getLimitPerChunk(): Int {
        return 2
    }

    init {
        moveControl = FloatMoveControl(this)
    }

    override val isFloating: Boolean
        get() {
            val maxWaterHeight = 0.4
            val blockPos = BlockPos.ofFloored(this.x, this.y + maxWaterHeight, this.z)
            val waterHeight =
                (this.blockPos.y + world.getFluidState(blockPos).getHeight(this.world, blockPos)).toDouble()
            return this.isSubmergedInWater || waterHeight > this.y + maxWaterHeight
        }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}