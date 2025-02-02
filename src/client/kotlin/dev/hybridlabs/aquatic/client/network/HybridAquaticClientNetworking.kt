package dev.hybridlabs.aquatic.client.network

object HybridAquaticClientNetworking {
    init {
        // Receives custom lure item and applies it to the bobber
        /*ClientPlayNetworking.registerGlobalReceiver(HybridAquaticNetworking.FISHING_BOBBER_LURE) { client, handler, buf, responseSender ->
            val id: Int = buf.readInt()
            val itemStack: ItemStack = buf.readItemStack()

            val foundEntity = handler.world.getEntityById(id)
            if (foundEntity != null && foundEntity is FishingBobberEntity) {
                val additionalBobberData = foundEntity as CustomFishingBobberEntityData

                additionalBobberData.`hybrid_aquatic$setLureItem`(itemStack)
            }
        } TODO*/
    }
}
