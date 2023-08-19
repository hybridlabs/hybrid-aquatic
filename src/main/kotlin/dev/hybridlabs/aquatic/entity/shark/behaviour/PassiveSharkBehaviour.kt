package dev.hybridlabs.aquatic.entity.shark.behaviour

class PassiveSharkBehaviour(
    override val isCannibalistic: Boolean = false
) : SharkBehaviour {
    override val isPassive: Boolean = true
    override val attackIfPlayerClose: Boolean = false
    override val attackIfPlayerTookDamage: Boolean = false
    override val attackIfPlayerAttacked: Boolean = false
}