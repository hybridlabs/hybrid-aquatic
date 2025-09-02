package dev.hybridlabs.aquatic.client.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import dev.hybridlabs.aquatic.Constants
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component

object RandomFishCommand {
    fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(
            ClientCommandManager.literal("randomhybridfish")
                .executes(RandomFishCommand::execute)
        )
    }

    private fun execute(context: CommandContext<FabricClientCommandSource>): Int {
        val types = BuiltInRegistries.ENTITY_TYPE.filter { type ->
            val id = BuiltInRegistries.ENTITY_TYPE.getKey(type)
            id.namespace == Constants.MOD_ID
        }

        types.randomOrNull()?.also { type ->
            val name = type.description
            context.source.sendFeedback(Component.literal("Fishy time! ").append(name))
        }

        return 1
    }
}
