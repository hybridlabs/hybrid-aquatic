package dev.hybridlabs.aquatic.entity.shark.behaviour

interface SharkBehaviour {
    val isPassive: Boolean
    val isCannibalistic: Boolean
    val attackIfPlayerClose: Boolean
    val attackIfPlayerTookDamage: Boolean
    val attackIfPlayerAttacked: Boolean
}