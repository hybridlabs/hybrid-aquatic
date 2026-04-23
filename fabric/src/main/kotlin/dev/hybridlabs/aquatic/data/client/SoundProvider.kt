package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.data.builder.FabricSoundsProvider
import dev.hybridlabs.aquatic.data.builder.SoundTypeBuilder
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class SoundProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>
): FabricSoundsProvider(output, registriesFuture) {

    override fun configure(exporter: SoundExporter) {
        exporter.add(HASoundEvents.SHELL_BEAST_SHOOT.get(),
            SoundTypeBuilder.of(HASoundEvents.SHELL_BEAST_SHOOT.get())
                .subtitle("shoot")
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(CommonClass.locate("entity/shell_beast_shoot")))
                .replace(true)
        )
    }

    override fun getName(): String {
        return "Hybrid Aquatic sound events"
    }

}