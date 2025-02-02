package dev.hybridlabs.aquatic.entity.miscellaneous

import net.minecraft.entity.EntityType
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.world.World

/**
 *
 */
class ThrowingStarEntity(type: EntityType<out ProjectileEntity>, world: World) : ProjectileEntity(type, world) {
    override fun initDataTracker(builder: DataTracker.Builder) {
    }
}
