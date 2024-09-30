package dev.hybridlabs.aquatic.component

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.SeaMessage
import net.minecraft.component.ComponentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import java.util.function.UnaryOperator

object HybridAquaticDataComponentTypes {
    val BOTTLE: ComponentType<BottleComponent> = register("bottle") { builder -> builder.codec(BottleComponent.CODEC).packetCodec(BottleComponent.PACKET_CODEC).cache() }
    val SEA_MESSAGE: ComponentType<SeaMessage> = register("sea_messge") { builder -> builder.codec(SeaMessage.CODEC).packetCodec(SeaMessage.PACKET_CODEC).cache() }

    private fun <T> register(id: String, builderOperator: UnaryOperator<ComponentType.Builder<T>>): ComponentType<T> {
        val type = builderOperator.apply(ComponentType.builder()).build()
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(HybridAquatic.MOD_ID, id), type)
    }
}
