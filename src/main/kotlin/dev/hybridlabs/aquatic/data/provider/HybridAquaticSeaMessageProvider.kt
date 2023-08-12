package dev.hybridlabs.aquatic.data.provider

import com.google.gson.JsonArray
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.data.DataOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.DataWriter
import net.minecraft.text.Text
import java.nio.file.Path
import java.util.concurrent.CompletableFuture

/**
 * Extend this class and implement [generateMessages].
 * Register an instance of the class with [FabricDataGenerator.Pack.addProvider] in a [DataGeneratorEntrypoint].
 */
abstract class HybridAquaticSeaMessageProvider(private val output: FabricDataOutput) : DataProvider {
    abstract fun generateMessages(generator: (Text) -> Unit)

    override fun run(writer: DataWriter): CompletableFuture<*> {
        val json = JsonArray().apply {
            generateMessages { text -> add(Text.Serializer.toJson(text)) }
        }

        return DataProvider.writeToPath(writer, json, getPath())
    }

    private fun getPath(): Path {
        return output
            .resolvePath(DataOutput.OutputType.DATA_PACK)
            .resolve("${output.modId}/sea_messages.json")
    }

    override fun getName(): String {
        return "Sea Messages"
    }
}
