package dev.hybridlabs.aquatic.entity.ai

import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.mob.MobEntity


class WallClimbingLookControl(entity: MobEntity, yawAdjustThreshold: Int) :
    YawAdjustingLookControl(entity, yawAdjustThreshold) {
    override fun tick() {
        if (!entity.isClimbing) {
            super.tick()
        }
    }
}