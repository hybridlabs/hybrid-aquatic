package dev.hybridlabs.aquatic.entity.miscellaneous

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.world.World

/**
 * Describes a projectile which can lodge within a block. Very basic default behavior which just embeds
 * itself within blocks.
 */
abstract class LodgedProjectileEntity(entityType: EntityType<out ProjectileEntity>?, world: World?) : ProjectileEntity(entityType, world) {

    var inGround: Boolean = false;
    var lodgeTime : Long = 0;
    var lodgedBlockState : BlockState = Blocks.AIR.defaultState;

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
                        this.lodgeTime = world.time
                    }
                }
            }
        }

        movementTick()
    }

    override fun onBlockHit(blockHitResult: BlockHitResult?) {
        this.lodgedBlockState = this.world.getBlockState(blockHitResult!!.blockPos)
        super.onBlockHit(blockHitResult)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound?) {
        super.writeCustomDataToNbt(nbt!!)
        nbt.putBoolean("inGround",inGround)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound?) {
        super.readCustomDataFromNbt(nbt!!)
        nbt.getBoolean("inGround")
    }

    /**
     * Get total time spent lodged in ground.
     */
    fun getTimeInGround() : Long {
        return world.time - this.lodgeTime;
    }

    abstract fun movementTick()


    override fun initDataTracker() {
        this.dataTracker.startTracking(PROJECTILE_FLAGS, 0)
    }

    companion object {
        val PROJECTILE_FLAGS : TrackedData<Byte> =
                DataTracker.registerData(LodgedProjectileEntity::class.java, TrackedDataHandlerRegistry.BYTE)
    }
}