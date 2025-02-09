package dev.hybridlabs.aquatic.entity.miscellaneous

import net.minecraft.entity.EntityType
import net.minecraft.entity.FlyingItemEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.projectile.ArrowEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.entity.projectile.thrown.ThrownItemEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.world.World

/**
 *
 */
class ThrowingStarEntity(entityType: EntityType<out LodgedProjectileEntity>?, world: World?) :
        LodgedProjectileEntity(entityType, world), FlyingItemEntity {

    var displayItem : ItemStack = Items.COOKED_COD.defaultStack;

    // Create a basic expiration date for the projectile. This will be renewed upon landing.
    private var expirationDate : Long = world!!.time + INITIAL_LIFETIME;

    override fun initDataTracker() {
        super.initDataTracker()

        dataTracker.startTracking(INITIAL_MOVEMENT_VEC, 0.0f)
        dataTracker.startTracking(EXPIRATION_DATE, 0)
    }

    override fun tick() {
        super.tick()

        if (world.time > expirationDate && !world.isClient) {
            this.remove(RemovalReason.DISCARDED)
        }
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound?) {
        super.writeCustomDataToNbt(nbt!!)
        nbt.putLong("expirationDate", expirationDate);
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound?) {
        super.readCustomDataFromNbt(nbt!!)
        nbt.getLong("expirationDate")
    }

    override fun movementTick() {

    }

    override fun isCollidable(): Boolean {
        return super.isCollidable()
    }

    companion object {
        val INITIAL_MOVEMENT_VEC : TrackedData<Float> =
                DataTracker.registerData(ThrowingStarEntity::class.java, TrackedDataHandlerRegistry.FLOAT)

        val EXPIRATION_DATE : TrackedData<Long> =
                DataTracker.registerData(ThrowingStarEntity::class.java, TrackedDataHandlerRegistry.LONG)

        const val INITIAL_LIFETIME = (20 * 10)  // Lives for 10 seconds in the air
        const val EMBEDDED_LIFETIME = (20 * 30)  // Lives for 20 seconds upon landing

    }

    override fun getStack(): ItemStack {
        return displayItem
    }

}