package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.Heightmap
import net.minecraft.world.World

class RatfishEntity(entityType: EntityType<out RatfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, 2) {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)

        }
    }

    private fun isInDeepWater(): Boolean {
        return isSubmergedInWater && isBlockInDeepWater(blockPos)
    }

    private fun isInShallowWater(): Boolean {
        return isSubmergedInWater && !isBlockInDeepWater(blockPos)
    }

    private fun findNearestDeepWater(): BlockPos? {
        val searchRadius = 32
        val searchBox = boundingBox.expand(searchRadius.toDouble(), searchRadius.toDouble(), searchRadius.toDouble())
        return world.getEntitiesByClass(Entity::class.java, searchBox) { entity -> entity.isSubmergedInWater }
            .filterIsInstance<Entity>()
            .mapNotNull { entity -> entity.blockPos }
            .filter { blockPos -> isBlockInDeepWater(blockPos) }
            .minByOrNull { blockPos -> blockPos.getSquaredDistance(x, y, z) }
    }

    private fun findNearestSurface(): BlockPos? {
        val searchRadius = 48
        val searchBox = boundingBox.expand(searchRadius.toDouble(), searchRadius.toDouble(), searchRadius.toDouble())
        return world.getEntitiesByClass(Entity::class.java, searchBox) { entity -> entity.isSubmergedInWater }
            .filterIsInstance<Entity>()
            .mapNotNull { entity -> entity.blockPos }
            .filter { blockPos -> isBlockAboveWaterSurface(blockPos) }
            .minByOrNull { blockPos -> blockPos.getSquaredDistance(x, y, z) }
    }

    private fun isBlockInDeepWater(blockPos: BlockPos): Boolean {
        val waterSurfaceY = world.getTopY(Heightmap.Type.WORLD_SURFACE, blockPos.x, blockPos.z)
        val waterTopY = world.getTopY(Heightmap.Type.OCEAN_FLOOR, blockPos.x, blockPos.z)
        val waterDepth = waterSurfaceY - waterTopY
        return waterDepth >= 22
    }

    private fun isBlockAboveWaterSurface(blockPos: BlockPos): Boolean {
        val waterSurfaceY = world.getTopY(Heightmap.Type.WORLD_SURFACE, blockPos.x, blockPos.z)
        return blockPos.y >= waterSurfaceY
    }

    private fun getBoundingBoxForWaterCheck(): Box {
        val collisionBox = boundingBox.contract(0.1)
        val yOffset = 0.2
        val minY = collisionBox.minY + yOffset
        val maxY = collisionBox.maxY
        return Box(collisionBox.minX, minY, collisionBox.minZ, collisionBox.maxX, maxY, collisionBox.maxZ)
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}