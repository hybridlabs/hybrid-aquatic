package dev.hybridlabs.aquatic.entity.shark.behaviour

class HostileSharkBehaviour(
    override val isCannibalistic: Boolean,
    override val attackIfPlayerClose: Boolean,
    override val attackIfPlayerTookDamage: Boolean,
    override val attackIfPlayerAttacked: Boolean
): SharkBehaviour {
    override val isPassive: Boolean = false
}