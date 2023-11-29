package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class YetiCrabEntity(entityType: EntityType<out HybridAquaticCritterEntity>, world: World) :
    HybridAquaticCrabEntity(entityType, world) {

    private var targetVentPos: BlockPos? = null

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
        }
    }

    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5

    }

    override fun isSneaking(): Boolean {
        return true
    }

    override fun tick() {
        super.tick()

        if (targetVentPos == null || world.getBlockState(targetVentPos).isOf(HybridAquaticBlocks.HYDROTHERMAL_VENT)) {
            targetVentPos = findNearbyVentBlock()
        }

        if (targetVentPos != null) {
            val distanceToVent = distanceTo(targetVentPos!!)

            if (distanceToVent > 5.0) {
                navigateToVent(targetVentPos!!)
            }
        }
    }

    private fun navigateToVent(ventBlockPos: BlockPos) {
        this.navigation.startMovingTo(ventBlockPos.x.toDouble(), ventBlockPos.y.toDouble(), ventBlockPos.z.toDouble(), this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED))
    }

    private fun findNearbyVentBlock(): BlockPos? {
        for (i in -5..5) {
            for (j in -5..5) {
                for (k in -5..5) {
                    val blockPos = BlockPos((x + i).toInt(), (y + j).toInt(), (z + k).toInt())
                    val blockState = world.getBlockState(blockPos)

                    if (blockState.isOf(HybridAquaticBlocks.HYDROTHERMAL_VENT)) {
                        return blockPos
                    }
                }
            }
        }
        return null
    }

    private fun distanceTo(pos: BlockPos): Double {
        return this.blockPos.getSquaredDistance(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
    }
}