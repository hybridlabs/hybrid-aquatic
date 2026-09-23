package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.target.TargetGoal
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import java.util.*

class OtterOwnerHurtByTargetGoal(private val otter: OtterEntity) : TargetGoal(otter, false) {

    private var ownerAttacker: LivingEntity? = null
    private var timestamp = 0

    init {
        this.flags = EnumSet.of(Flag.TARGET)
    }

    override fun canUse(): Boolean {
        if (!otter.isTame() || otter.isSitting()) return false

        val owner = otter.owner ?: return false
        val attacker = owner.lastHurtByMob ?: return false
        if (attacker === owner || otter.isOwnedBy(attacker)) return false

        ownerAttacker = attacker
        return owner.lastHurtByMobTimestamp != timestamp &&
                canAttack(attacker, TargetingConditions.DEFAULT)
    }

    override fun start() {
        mob.target = ownerAttacker
        otter.owner?.let { timestamp = it.lastHurtByMobTimestamp }
        super.start()
    }
}
