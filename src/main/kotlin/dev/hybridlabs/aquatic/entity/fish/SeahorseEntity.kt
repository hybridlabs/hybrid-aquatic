package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class SeahorseEntity(entityType: EntityType<out SeahorseEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, 6) {

    private var targetCoralPos: BlockPos? = null

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    override fun tick() {
        super.tick()

        if (targetCoralPos == null || world.getBlockState(targetCoralPos).isIn(BlockTags.CORAL_PLANTS)) {
            targetCoralPos = findNearbyCoralBlock()
        }

        if (targetCoralPos != null) {
            val distanceToCoral = distanceTo(targetCoralPos!!)

            if (distanceToCoral > 5.0) {
                navigateToCoral(targetCoralPos!!)
            }
        }
    }

    private fun navigateToCoral(coralBlockPos: BlockPos) {
        this.navigation.startMovingTo(coralBlockPos.x.toDouble(), coralBlockPos.y.toDouble(), coralBlockPos.z.toDouble(), this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED))
    }

    private fun findNearbyCoralBlock(): BlockPos? {
        for (i in -16..16) {
            for (j in -16..16) {
                for (k in -16..16) {
                    val blockPos = BlockPos((x + i).toInt(), (y + j).toInt(), (z + k).toInt())
                    val blockState = world.getBlockState(blockPos)

                    if (blockState.isIn(BlockTags.CORAL_PLANTS)) {
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