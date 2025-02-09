package dev.hybridlabs.aquatic.entity.miscellaneous

import net.minecraft.entity.EntityType
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.world.World

/**
 * Describes a projectile which can lodge within a block. Very basic default behavior which just embeds
 * itself within blocks.
 */
class LodgedProjectileEntity(entityType: EntityType<out ProjectileEntity>?, world: World?) : ProjectileEntity(entityType, world) {

    var inGround: Boolean = false;

    override fun tick() {
        super.tick()

        var velocity = velocity
        val pos = blockPos
        val state = world!!.getBlockState(pos)

        if (!state.isAir) {
            val selectionShape = state.getCollisionShape(world,pos)

            if (!selectionShape.isEmpty) {
                val subPos = getPos()
                val boundingBoxes = selectionShape.boundingBoxes

                for (box in boundingBoxes) {
                    if (box.offset(pos).contains(subPos)) {
                        this.inGround = true;
                    }
                }
            }
        }

        movementTick()
    }

    protected fun movementTick() {}

    override fun initDataTracker() {
        TODO("Not yet implemented")
    }
}