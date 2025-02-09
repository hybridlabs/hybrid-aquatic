package dev.hybridlabs.aquatic.entity.fish.carp

enum class CarpBaseColor(val id: Int) {
    DEFAULT(0),
    WHITE(1),
    RED(2),
    BLACK(3);

    companion object {
        private val BY_ID = entries.associateBy { it.id }

        fun byId(id: Int): CarpBaseColor = BY_ID[id % BY_ID.size] ?: DEFAULT
    }
}