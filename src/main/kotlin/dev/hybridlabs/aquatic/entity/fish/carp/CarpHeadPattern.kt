package dev.hybridlabs.aquatic.entity.fish.carp

enum class CarpHeadPattern(val id: Int) {
    DEFAULT(0),
    WHITE_TANCHO(1),
    RED_TANCHO(2),
    BLACK_TANCHO(3),
    WHITE_MENKABURL(4),
    RED_MENKABURL(5),
    BLACK_MENKABURL(6),
    WHITE_KUCHLBENL(7),
    RED_KUCHLBENL(8),
    BLACK_KUCHLBENL(9);

    companion object {
        private val BY_ID = entries.associateBy { it.id }

        fun byId(id: Int): CarpHeadPattern = BY_ID[id % BY_ID.size] ?: DEFAULT
    }
}