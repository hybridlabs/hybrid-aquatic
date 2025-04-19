package dev.hybridlabs.aquatic.entity.ai

import dev.hybridlabs.aquatic.entity.Floater
import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.entity.ai.control.MoveControl


class FloatMoveControl(entity: HybridAquaticJellyfishEntity) : MoveControl(entity) {

    override fun tick() {
        if ((this.entity as Floater).isFloating) {
            this.entity.velocity = this.entity.velocity.add(0.0, 0.05, 0.0)
        }
        super.tick()
    }
}