package dev.hybridlabs.aquatic.client.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.registry.Registries
import net.minecraft.text.Text

object RandomFishCommand {
    fun register(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        dispatcher.register(
            ClientCommandManager.literal("randomhybridfish")
                .executes(RandomFishCommand::execute)
        )
    }

    private fun execute(context: CommandContext<FabricClientCommandSource>): Int {
        val types = Registries.ENTITY_TYPE.filter { type ->
            val id = Registries.ENTITY_TYPE.getId(type)
            id.namespace == HybridAquatic.MOD_ID
        }

        types.randomOrNull()?.also { type ->
            val name = type.name
            context.source.sendFeedback(Text.literal("Fishy time! ").append(name))
        }

        return 1
    }
}
