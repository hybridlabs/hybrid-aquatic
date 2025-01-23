package dev.hybridlabs.aquatic.entity.miscellaneous

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.world.World

/**
 *
 */
class ThrowingStarEntity(entityType: EntityType<out ProjectileEntity>, world: World?) :
    ProjectileEntity(
                entityType,
                world
    ) {

    var DisplayItem : ItemStack = Items.COOKED_COD.defaultStack;

    override fun initDataTracker() {}

    override fun tick() {

        super.tick()
    }

    companion object {
        fun spawnThrowingStar(player : Entity, magnitude: Float) {

        }
    }
}