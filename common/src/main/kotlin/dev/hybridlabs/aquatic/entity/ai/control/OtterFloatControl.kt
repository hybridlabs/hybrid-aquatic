package dev.hybridlabs.aquatic.entity.ai.control

import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.phys.Vec3
import kotlin.math.max

// credit to fowl play for the code

class OtterFloatControl(otter: OtterEntity) : MoveControl(otter) {
    override fun tick() {
        var deltaMovement: Vec3 = this.mob.deltaMovement
        if ((this.mob as OtterEntity).isBelowWaterline()) {
            this.mob.deltaMovement = deltaMovement.add(0.0, 0.05, 0.0)
            if (this.mob.isUnderWater)
                deltaMovement = this.mob.deltaMovement
                this.mob.deltaMovement = deltaMovement.add(0.0, 0.05, 0.0)
            deltaMovement = this.mob.deltaMovement
            this.mob.setDeltaMovement(deltaMovement.x, max(deltaMovement.y, 0.0), deltaMovement.z)
        }
        super.tick()
    }
}