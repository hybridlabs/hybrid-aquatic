package dev.hybridlabs.aquatic.entity.ai.control

import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.phys.Vec3
import kotlin.math.max

// credit to Fowl Play for the code

class FloatControl(mob: BaseWaterAnimal) : MoveControl(mob) {
    override fun tick() {
        var deltaMovement: Vec3 = this.mob.deltaMovement
        if ((this.mob as BaseWaterAnimal).isBelowWaterline()) {
            this.mob.deltaMovement = deltaMovement.add(0.0, 0.025, 0.0)
            if (this.mob.isUnderWater)
                deltaMovement = this.mob.deltaMovement
                this.mob.deltaMovement = deltaMovement.add(0.0, 0.025, 0.0)
            deltaMovement = this.mob.deltaMovement
            this.mob.setDeltaMovement(deltaMovement.x, max(deltaMovement.y, 0.0), deltaMovement.z)
        }
        super.tick()
    }
}