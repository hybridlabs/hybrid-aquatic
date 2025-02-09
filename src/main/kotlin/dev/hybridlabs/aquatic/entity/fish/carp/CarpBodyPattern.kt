package dev.hybridlabs.aquatic.entity.fish.carp

enum class CarpBodyPattern(val id: Int) {
    DEFAULT(0),
    WHITE_INAZUMA(1),
    RED_INAZUMA(2),
    BLACK_INAZUMA(3),
    WHITE_GOTENZAKURA(4),
    RED_GOTENZAKURA(5),
    BLACK_GOTENZAKURA(6),
    WHITE_YONDAN(7),
    RED_YONDAN(8),
    BLACK_YONDAN(9);

    companion object {
        private val BY_ID = entries.associateBy { it.id }

        fun byId(id: Int): CarpBodyPattern = BY_ID[id % BY_ID.size] ?: DEFAULT
    }
}