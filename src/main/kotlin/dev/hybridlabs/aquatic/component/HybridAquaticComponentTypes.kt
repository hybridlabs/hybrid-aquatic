package dev.hybridlabs.aquatic.component

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import net.minecraft.component.ComponentType
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.nbt.NbtOps
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticComponentTypes {
    val BOTTLE_VARIANT = register("bottle_variant") { builder -> builder.codec(MessageInABottleBlock.Variant.CODEC).packetCodec(MessageInABottleBlock.Variant.PACKET_CODEC).cache() }
    val BOTTLE_MESSAGE = register("bottle_message") { builder -> builder.codec(ItemStack.CODEC).packetCodec(ItemStack.PACKET_CODEC).cache() }

    val ENTITY = register("entity") { builder ->
        val codec = Registries.ENTITY_TYPE.codec
        builder.codec(codec)
            .packetCodec(PacketCodecs.codec(codec))
            .cache()
    }

    fun <T> register(id: String, builder: (ComponentType.Builder<T>) -> ComponentType.Builder<T>): ComponentType<T> {
        val component = builder.invoke(ComponentType.builder()).build()
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(HybridAquatic.MOD_ID, id), component)
    }

    fun <T> NbtCompound.putEncoded(key: String, codec: Codec<T>, obj: T) {
        codec.encodeStart(NbtOps.INSTANCE, obj).ifSuccess { put(key, it) }
    }

    fun <T> NbtCompound.getEncoded(key: String, codec: Codec<T>): T? {
        val element = this[key] ?: return null
        return codec
            .decode(NbtOps.INSTANCE, element)
            .map(Pair<T, NbtElement>::getFirst)
            .result()
            .orElse(null)
    }
}