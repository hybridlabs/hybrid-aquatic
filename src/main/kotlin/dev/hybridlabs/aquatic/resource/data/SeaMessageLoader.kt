package dev.hybridlabs.aquatic.resource.data

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.resource.ResourceManager
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class SeaMessageLoader : SimpleSynchronousResourceReloadListener {
    override fun reload(manager: ResourceManager) {
        manager.allNamespaces.forEach { namespace ->
            manager.getResource(Identifier(namespace, SEA_MESSAGES_LOCATION)).ifPresent { resource ->
                val json = resource.reader.use(JsonParser::parseReader)
                if (json is JsonArray) {
                    json.map(JsonElement::getAsString).map(Text.Serializer::fromJson)
                }
            }
        }
    }

    override fun getFabricId(): Identifier {
        return LOADER_ID
    }

    companion object {
        private val LOADER_ID = Identifier(HybridAquatic.MOD_ID, "sea_messages")

        /**
         * The location of the sea messages file in each namespace.
         */
        private const val SEA_MESSAGES_LOCATION = "sea_messages.json"
    }
}
