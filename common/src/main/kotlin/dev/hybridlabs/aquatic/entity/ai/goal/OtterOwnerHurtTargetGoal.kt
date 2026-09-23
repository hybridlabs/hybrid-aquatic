package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.target.TargetGoal
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import java.util.*

class OtterOwnerHurtTargetGoal(private val otter: OtterEntity) : TargetGoal(otter, false) {

    private var ownerTarget: LivingEntity? = null
    private var timestamp = 0

    init {
        this.flags = EnumSet.of(Flag.TARGET)
    }

    override fun canUse(): Boolean {
        if (!otter.isTame() || otter.isSitting()) return false

        val owner = otter.owner ?: return false
        val target = owner.lastHurtMob ?: return false
        if (target === owner || otter.isOwnedBy(target)) return false

        ownerTarget = target
        return owner.lastHurtMobTimestamp != timestamp &&
                canAttack(target, TargetingConditions.DEFAULT)
    }

    override fun start() {
        mob.target = ownerTarget
        otter.owner?.let { timestamp = it.lastHurtMobTimestamp }
        super.start()
    }
}
