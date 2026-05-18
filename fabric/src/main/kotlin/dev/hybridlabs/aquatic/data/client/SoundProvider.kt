package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.data.builder.FabricSoundsProvider
import dev.hybridlabs.aquatic.data.builder.SoundTypeBuilder
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import java.util.concurrent.CompletableFuture

class SoundProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>
): FabricSoundsProvider(output, registriesFuture) {

    override fun configure(exporter: SoundExporter) {
        mapOf(
            HASoundEvents.OMINOUS_CONCH_BLOWS to CommonClass.locate("item/ominous_conch_blows"),

            HASoundEvents.MANATEE_AMBIENT to CommonClass.locate("entity/sirenian_ambient"),
            HASoundEvents.MANATEE_DIE to CommonClass.locate("entity/sirenian_hurt"),
            HASoundEvents.MANATEE_HURT to CommonClass.locate("entity/sirenian_die"),
            HASoundEvents.MANATEE_SWIM to CommonClass.locate("entity/sirenian_swim"),
            HASoundEvents.MANATEE_SPLASH to CommonClass.locate("entity/sirenian_splash"),

            HASoundEvents.DUGONG_AMBIENT to CommonClass.locate("entity/sirenian_ambient"),
            HASoundEvents.DUGONG_DIE to CommonClass.locate("entity/sirenian_hurt"),
            HASoundEvents.DUGONG_HURT to CommonClass.locate("entity/sirenian_die"),
            HASoundEvents.DUGONG_SWIM to CommonClass.locate("entity/sirenian_swim"),
            HASoundEvents.DUGONG_SPLASH to CommonClass.locate("entity/sirenian_splash"),

            HASoundEvents.KARKINOS_AMBIENT to CommonClass.locate("entity/karkinos_ambient"),
            HASoundEvents.KARKINOS_DIE to CommonClass.locate("entity/karkinos_die"),
            HASoundEvents.KARKINOS_HURT to CommonClass.locate("entity/karkinos_hurt"),

            HASoundEvents.KARCINOMA_AMBIENT to SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.KARCINOMA_DIE to SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.KARCINOMA_HURT to SoundEvents.TURTLE_EGG_CRACK,

            HASoundEvents.KARCINOGEN_AMBIENT to SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.KARCINOGEN_DIE to SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.KARCINOGEN_HURT to SoundEvents.TURTLE_EGG_CRACK,

            HASoundEvents.SHELL_BEAST_AMBIENT to CommonClass.locate("entity/shell_beast_ambient"),
            HASoundEvents.SHELL_BEAST_DIE to CommonClass.locate("entity/shell_beast_die"),
            HASoundEvents.SHELL_BEAST_HURT to CommonClass.locate("entity/shell_beast_hurt"),

            HASoundEvents.SHELL_BEAST_SHOOT to CommonClass.locate("entity/shell_beast_shoot"),

            HASoundEvents.HYPNAUTILUS_AMBIENT to SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.HYPNAUTILUS_DIE to SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.HYPNAUTILUS_HURT to SoundEvents.TURTLE_EGG_CRACK,

            HASoundEvents.BEAKLING_AMBIENT to CommonClass.locate("entity/beakling_ambient"),
            HASoundEvents.BEAKLING_DIE to SoundEvents.TURTLE_EGG_CRACK,
            HASoundEvents.BEAKLING_HURT to SoundEvents.TURTLE_EGG_CRACK,

        ).forEach { (soundEvent, soundPath) ->
            exporter.add(soundEvent.get(), SoundTypeBuilder.of(soundEvent.get())
                .subtitle("subtitles.${soundEvent.get().location.namespace}.${soundEvent.get().location.path}")
                .sound(when (soundPath) {
                        is SoundEvent -> SoundTypeBuilder.RegistrationBuilder.ofEvent(soundPath)
                        is ResourceLocation -> SoundTypeBuilder.RegistrationBuilder.ofFile(soundPath)
                        is Holder<*> -> SoundTypeBuilder.RegistrationBuilder.ofEvent(soundPath.value() as SoundEvent)
                        is RegistryObject<*> -> SoundTypeBuilder.RegistrationBuilder.ofEvent(soundPath.get() as SoundEvent)
                        else -> SoundTypeBuilder.RegistrationBuilder.ofEvent(SoundEvents.EMPTY)
                    }
                )
            )
        }
    }

    override fun getName(): String {
        return "Hybrid Aquatic sound events"
    }

}