package dev.hybridlabs.aquatic.entity.miscellaneous

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.projectile.thrown.ThrownItemEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.world.World

/**
 *
 */
class ThrowingStarEntity(entityType: EntityType<out ThrownItemEntity>?, world: World?) :
        ThrownItemEntity(entityType, world) {

    var DisplayItem : ItemStack = Items.COOKED_COD.defaultStack;

    override fun initDataTracker() {}

    override fun tick() {
        super.tick()
    }

    override fun isCollidable(): Boolean {
        return super.isCollidable()
    }

    override fun getDefaultItem(): Item {
        return Items.COOKED_COD;
    }

    companion object {
        fun applyVelocity(player : Entity, magnitude: Float) {
        }
    }
}